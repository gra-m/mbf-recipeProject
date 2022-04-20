package fun.madeby.mbfrecipeproject.services;

import com.sun.istack.NotNull;
import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.converters.IngredientToIngredientCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import fun.madeby.mbfrecipeproject.repositories.IngredientRepository;
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

    @InjectMocks
    IngredientServiceImpl ingredientServiceImpl;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;

    Ingredient ingredient1;
    Long ing1_Id = 4L;
    Long ing2_Id = 5L;
    IngredientCommand ingredientCommand1;


    @BeforeEach
    void setUp() {

        ingredient1 = new Ingredient();
        ingredient1.setId(ing1_Id);
        ingredient1.setDescription("Just playing this does nothing");
        ingredient1.setAmount(new BigDecimal(3));
        ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(ingredient1.getId());
        ingredientCommand1.setDescription(ingredient1.getDescription());

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