package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    //@Mock
    //RecipeToRecipeCommand recipeToRecipeCommand

    @InjectMocks
    RecipeServiceImpl recipeServiceImpl;
    Recipe recipe1;
    Recipe recipe2;


    @BeforeEach
    void setUp() {
        recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe2 = new Recipe();
        recipe2.setId(2L);
    }


    @Test
    void testGetRecipeById() {
        //given
        Optional<Recipe> recipe1Optional = Optional.of(recipe1);
        when(recipeRepository.findById(anyLong()))
                .thenReturn(recipe1Optional);
        //when
        Recipe recipeReturned = recipeServiceImpl.getRecipeById(1L);
        //then
        assertNotNull(recipeReturned, "Null recipe returned");
        verify(recipeRepository, times(1))
                .findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }


    @Test
    void testGetRecipesTest() {
        //given
        HashSet<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe1);
        recipeData.add(recipe2);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        // test the actual business logic, in this case converting iterable into collection/Set
        Set<Recipe> recipeSet = recipeServiceImpl.getRecipes();

        assertEquals(2, recipeSet.size() );
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }


    @Test
    void testDeleteRecipeById() {
        //given
        Long idToDelete = 4L;

        //when
        recipeServiceImpl.deleteRecipeById(idToDelete);

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }







}