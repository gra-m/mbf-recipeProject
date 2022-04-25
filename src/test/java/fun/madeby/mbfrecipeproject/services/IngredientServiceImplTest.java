package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.converters.IngredientToIngredientCommand;
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



    Recipe recipe1;
    Long recipe1_id = 4L;

    Ingredient ingredient1;
    Long ing1_Id = 3L;
    IngredientCommand ingredientCommand1;
    Long ingredientCommand1RecipeId = 4L;

    IngredientCommand ingredientCommand2;
    Long ingC2_Id = 500L;

    UnitOfMeasure unitOfMeasure1;
    Long unitOfMeasure1_Id = 33L;

    UnitOfMeasureCommand unitOfMeasure1Command;
    Ingredient ingredient2;

    @BeforeEach
    void setUp() {

        unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(unitOfMeasure1_Id);
        unitOfMeasure1.setDescription("I am the unit of measure");

        unitOfMeasure1Command = new UnitOfMeasureCommand();
        unitOfMeasure1Command.setId(unitOfMeasure1_Id);
        unitOfMeasure1Command.setDescription("I am the unit of measure command");

        ingredient1 = new Ingredient();
        ingredient1.setId(ing1_Id);
        ingredient1.setDescription("Just playing this does nothing");
        ingredient1.setAmount(new BigDecimal(3));
        ingredient1.setUom(unitOfMeasure1);

        ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(ingredient1.getId());
        ingredientCommand1.setDescription(ingredient1.getDescription());
        ingredientCommand1.setRecipe_id(ingredientCommand1RecipeId);
        ingredientCommand1.setUom(unitOfMeasure1Command);

        recipe1 = new Recipe();
        recipe1.setId(recipe1_id);
        recipe1.setDescription("description");
        recipe1.getIngredients().add(ingredient1);

        ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(ingC2_Id);
        ingredientCommand2.setRecipe_id(recipe1_id);
        ingredientCommand2.setAmount(new BigDecimal(4));
        ingredientCommand2.setUom(unitOfMeasure1Command);

        ingredient2 = new Ingredient();
        ingredient2.setId(ingredientCommand2.getId());
        ingredient2.setAmount(ingredientCommand2.getAmount());
        ingredient2.setUom(unitOfMeasure1);
    }

    //region SAVE OR UPDATE IngredientCommand

    @Test
    void TestStandardSaveIngredient(){
        // given
        Ingredient ingredientToBeSaved = ingredient2;
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredientToBeSaved);

        //when
        Ingredient savedIngredient = ingredientServiceImpl.saveIngredient(ingredient2);

        assertNotNull(savedIngredient);
        assertEquals(ingredient2.getId(), savedIngredient.getId());
        assertEquals(ingredient2.getDescription(), savedIngredient.getDescription());
        assertEquals(ingredient2.getAmount(), savedIngredient.getAmount());
        assertEquals(ingredient2.getUom(), savedIngredient.getUom());
    }

    @Test
    void testSaveOrUpdateIngredientCommand_UpdatePath(){
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ing1_Id); //Id match
        command.setRecipe_id(recipe1_id);
        command.setUom(unitOfMeasure1Command);

        Optional<Recipe> recipeOptional = Optional.of(recipe1);
        Optional<UnitOfMeasure> uomOptional = Optional.of(unitOfMeasure1);

        Recipe savedRecipe = recipe1;
        savedRecipe.getIngredients().iterator().next().setId(ing1_Id); //Id match


        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(unitOfMeasureRepository.findById(anyLong()))
                .thenReturn(uomOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);
        // bypassed retrieval of recipe below == own test.
        when(ingredientToIngredientCommand.convert(any(Ingredient.class)))
                .thenReturn(command);

        //when
        IngredientCommand savedCommand = ingredientServiceImpl.saveOrUpdateIngredientCommand(command);

        //then
        assertNotNull(savedCommand);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
        verify(ingredientToIngredientCommand, times(1)).convert(any(Ingredient.class));
        assertEquals(ing1_Id, savedCommand.getId());
        assertEquals(recipe1_id, savedCommand.getRecipe_id());

    }

    @Test
    void testSaveOrUpdateIngredientCommand_NewPath() {
        //given


        //when


        //then
    }

    @Test
    void retrieveIngredient() {
        //given
        Recipe savedRecipe = recipe1;

        //when
        Ingredient retrievedIngredient = ingredientServiceImpl.retrieveIngredient(savedRecipe, ing1_Id);

        assertNotNull(retrievedIngredient);
        assertEquals(ing1_Id, retrievedIngredient.getId());

    }

    //region SAVE OR UPDATE IngredientCommand ERROR PATHS

    @Test
    void testSaveOrUpdateIngredientCommand_RecipeDoesNotExist() {
        //given
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

    //endregion
    //endregion

    @Test
    void testGetIngredientById() {
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

    @Test
    void testdeleteIngredientById() {
        //given ingredient id exists
        //when
        ingredientServiceImpl.deleteIngredientById(ing1_Id);

        //then
        verify(ingredientRepository, times(1)).deleteById(anyLong());
    }
}