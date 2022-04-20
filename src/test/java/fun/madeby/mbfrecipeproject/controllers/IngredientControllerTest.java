package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.converters.IngredientToIngredientCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.services.IngredientService;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

//1 Bring in:
@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @InjectMocks
    IngredientController controllerUnderTest;

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;


    MockMvc mockMvc;
    Recipe recipe1;
    Set<Ingredient> ingredientSet;
    Ingredient ingredient1;
    IngredientCommand ingredientCommand1;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controllerUnderTest)
                .build();

        ingredientSet = new HashSet<>(2);
        recipe1 = new Recipe();
        recipe1.setId(1L);
        ingredient1 = new Ingredient();
        ingredientCommand1 = new IngredientCommand();
        ingredientSet.add(ingredient1);
        recipe1.setIngredients(ingredientSet);
    }

    @Test
    void testListRecipeIngredients() throws Exception {

        //wnen
        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe1);




        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredients/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

    }

    @Test
    void testShowIngredient() throws Exception {

        //wnen
        when(ingredientService.getIngredientById(anyLong())).thenReturn(ingredientCommand1);


        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/ingredients/1/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredients/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"));

    }

}