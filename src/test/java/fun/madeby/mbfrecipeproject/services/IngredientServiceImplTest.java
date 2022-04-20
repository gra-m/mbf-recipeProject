package fun.madeby.mbfrecipeproject.services;

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
    Ingredient ingredient2;
    Long ing2_Id = 6L;


    @BeforeEach
    void setUp() {

        ingredient1 = new Ingredient();
        ingredient1.setId(ing1_Id);
        ingredient1.setDescription("Just playing this does nothing");
        ingredient1.setAmount(new BigDecimal(3));
        ingredient2 = new Ingredient();
        ingredient2.setId(ing2_Id);

    }

   /* @Test
    void testGetIngredientById() {
        // I've created an ingredientRepository to return ingredient directly by its id rather
        // than streaming/filter/mapping through a returned set from recipe
        //given
        Optional<Ingredient> ingredient1Optional = Optional.of(ingredient1);
        when(ingredientRepository.findById(anyLong()))
                .thenReturn(ingredient1Optional);


        //when
        IngredientCommand ingredientCommand = ingredientServiceImpl.getIngredientById(ing1_Id);

        //then
        assertNotNull(ingredientCommand, "Null IngredientCommand returned");
        verify(ingredientRepository, times(1)).findIngredientById(anyLong());
        assertEquals(ing1_Id, ingredientCommand.getId());







    }*/
}