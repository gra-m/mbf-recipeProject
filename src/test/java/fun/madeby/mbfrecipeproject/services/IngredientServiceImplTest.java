package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import fun.madeby.mbfrecipeproject.repositories.IngredientRepository;
import fun.madeby.mbfrecipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @InjectMocks
    IngredientServiceImpl ingredientServiceImpl;

    @Mock
    IngredientRepository ingredientRepository;

    Ingredient ingredient1;
    Long ing1_Id = 4L;
    Ingredient ingredient2;
    Long ing2_Id = 6L;


    @BeforeEach
    void setUp() {
        ingredient1 = new Ingredient();
        ingredient1.setId(ing1_Id);
        ingredient2 = new Ingredient();
        ingredient2.setId(ing2_Id);

    }

    @Test
    void testGetIngredientById() {
        // I've created an ingredientRepository to return ingredient directly by its id rather
        // than streaming/filter/mapping through a returned set from recipe

        //given
        Optional<Ingredient> ingredientOptional = Optional.of(ingredient1);

        //when
        when(ingredientRepository.findIngredientById(anyLong())).thenReturn(ingredientOptional);
        IngredientCommand ingredientCommand = ingredientServiceImpl.getIngredientById(ing1_Id);

        //then
        assertNotNull(ingredientCommand, "Null IngredientCommand returned");
        verify(ingredientRepository, times(1)).findById(anyLong());
        assertEquals(ing1_Id, ingredientCommand.getId());







    }
}