package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.converters.IngredientToIngredientCommand;
import fun.madeby.mbfrecipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import fun.madeby.mbfrecipeproject.repositories.IngredientRepository;
import fun.madeby.mbfrecipeproject.repositories.RecipeRepository;
import fun.madeby.mbfrecipeproject.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {
    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;


    @InjectMocks
    IngredientServiceImpl ingredientServiceImpl;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    Recipe recipe1;
    Long recipe1_id = 1L;

    Ingredient ingredient1;
    Long ing1_Id = 4L;
    Long ing2_Id = 5L;
    IngredientCommand ingredientCommand1;
    Long ingredientCommand1RecipeId = 4L;

    UnitOfMeasure unitOfMeasure1;
    Long unitOfMeasure1_Id = 33L;

    UnitOfMeasureCommand unitOfMeasureCommand1;
    Long unitOfMeasureC1_id = 3L;


    @BeforeEach
    void setUp() {

        unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(unitOfMeasure1_Id);
        unitOfMeasure1.setDescription("I am the unit of measure");

        unitOfMeasureCommand1 = new UnitOfMeasureCommand();
        unitOfMeasureCommand1.setId(unitOfMeasureC1_id);
        unitOfMeasureCommand1.setDescription("I am the unit of measure command");

        ingredient1 = new Ingredient();
        ingredient1.setId(ing1_Id);
        ingredient1.setDescription("Just playing this does nothing");
        ingredient1.setAmount(new BigDecimal(3));
        ingredient1.setUom(unitOfMeasure1);

        ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(ingredient1.getId());
        ingredientCommand1.setDescription(ingredient1.getDescription());
        ingredientCommand1.setRecipe_id(ingredientCommand1RecipeId);
        ingredientCommand1.setUom(unitOfMeasureCommand1);

        recipe1 = new Recipe();
        recipe1.setId(recipe1_id);
        recipe1.setDescription("description");
        recipe1.getIngredients().add(ingredient1);
    }

    @Test
    void testSaveOrUpdateIngredientCommand_RecipeDoesNotExist() {
        //given
        //Optional<Recipe> optionalOfRecipe1 = Optional.of(recipe1);
        Optional<Recipe> emptyRecipeOptional = Optional.empty();
        when(recipeRepository.findById(anyLong()))
                .thenReturn(emptyRecipeOptional);

        //when
        IngredientCommand returnedIngredientCommand = ingredientServiceImpl.saveOrUpdateIngredientCommand(ingredientCommand1);

        //then
        assertNotNull(returnedIngredientCommand, "returned command was null");
        assertNull(returnedIngredientCommand.getId());
        assertNull(returnedIngredientCommand.getDescription());
        assertNull(returnedIngredientCommand.getRecipe());
        assertNull(returnedIngredientCommand.getAmount());
        assertNull(returnedIngredientCommand.getUom());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void testSaveOrUpdateIngredientCommand_UomDoesNotExist() {
        //given the following ensure confirmedUnitOfMeasureExists is empty
        Optional<Recipe> optionalOfRecipe1 = Optional.of(recipe1);
        Optional<UnitOfMeasure> emptyUnitOfMeasure1 = Optional.empty();

        when(recipeRepository.findById(anyLong()))
                .thenReturn(optionalOfRecipe1);
        when(unitOfMeasureRepository.findById(anyLong()))
                .thenReturn(emptyUnitOfMeasure1);
        //when
        Exception exception = assertThrows(RuntimeException.class, () -> {
                    IngredientCommand returnedIngredientCommand = ingredientServiceImpl
                            .saveOrUpdateIngredientCommand(ingredientCommand1);
                });

        String expectedMessage = "UOM NOT FOUND";
        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedMessage), "actualMessage does not contain expected message");

    }



    @Test
    void testGetIngredientById() {
        // I've created an ingredientRepository to return ingredient directly by its id rather
        // than streaming/filter/mapping through a returned set from recipe
        //given
        Optional<Ingredient> ingredient1Optional = Optional.of(ingredient1);
        Ingredient optionalConfirmed = ingredient1Optional.get();
        when(ingredientRepository.findById(anyLong()))
                .thenReturn(ingredient1Optional);
        when(ingredientToIngredientCommand.convert(any(Ingredient.class)))
                .thenReturn(ingredientCommand1);
        when(ingredientServiceImpl.getIngredientById(anyLong())).
                thenReturn(ingredientCommand1);

        //when
        IngredientCommand returnedIngredientCommand = ingredientServiceImpl.getIngredientById(ing1_Id);


        //then
        assertNotNull(returnedIngredientCommand, "command was not returned");
        assertNotNull(ingredient1Optional, "Even though created from ingredient1, ingredient1Optional is null.");
        assertNotNull(ingredientCommand1, "Even though created from ingredient1, ingredientCommand1 is null.");
        verify(ingredientToIngredientCommand, times(1)).convert(optionalConfirmed);
        verify(ingredientRepository, times(1)).findById(ing1_Id);
        assertEquals(ing1_Id, returnedIngredientCommand.getId());

    }









}