package fun.madeby.mbfrecipeproject.services.h2;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl recipeService;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this);// Junit4
        //recipeService = new RecipeServiceImpl(recipeRepository); //Junit4
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);

        //Set expectation using injected Recipe Repo
        when(recipeRepository.findAll()).thenReturn(recipeData);

        // test the actual business logic, in this case converting iterable into collection/Set
        Set<Recipe> recipeSet = recipeService.getRecipes();

        // test that return is of expected size (actual 1)
        assertEquals(1, recipeSet.size() );
        //and it was called only once;
        verify(recipeRepository, times(1)).findAll();
    }
}