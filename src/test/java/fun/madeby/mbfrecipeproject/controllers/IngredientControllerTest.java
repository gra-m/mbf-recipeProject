package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.services.IngredientService;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import fun.madeby.mbfrecipeproject.services.UnitOfMeasureService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

//1 Bring in:
@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @InjectMocks
    IngredientController controllerUnderTest;

    @Mock
    RecipeService recipeService;

    @Mock
    UnitOfMeasureService uomService;

    @Mock
    IngredientService ingredientService;


    MockMvc mockMvc;
    Recipe recipe1;
    Long recipe1_id = 1L;
    Set<Ingredient> ingredientSet;
    Ingredient ingredient1;
    IngredientCommand ingredientCommand1;
    Long ingC1_id = 444L;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controllerUnderTest)
                .build();

        ingredientSet = new HashSet<>(2);
        recipe1 = new Recipe();
        recipe1.setId(recipe1_id);
        ingredient1 = new Ingredient();
        ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(ingC1_id);
        ingredientCommand1.setRecipe_id(recipe1_id);
        ingredientSet.add(ingredient1);
        recipe1.setIngredients(ingredientSet);
    }

    @Test
    void testListRecipeIngredients() throws Exception {
        //wnen
        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe1);
        //then
       mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredients/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
        verify(recipeService, times(1)).getRecipeById(anyLong());
    }

    @Test
    void testShowIngredient() throws Exception {
        //wnen
        when(ingredientService.getIngredientById(anyLong())).thenReturn(ingredientCommand1);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/ingredients/1/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredients/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"));
        verify(ingredientService, times(1)).getIngredientById(anyLong());
    }

    @Test
    void saveOrUpdateIngredientToRecipe() throws Exception {
        //when
        when(ingredientService.saveOrUpdateIngredientCommand(any(IngredientCommand.class))).thenReturn(ingredientCommand1);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/1/ingredient"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/ingredients/" + ingC1_id + "/show"));
        verify(ingredientService, times(1)).saveOrUpdateIngredientCommand(any(IngredientCommand.class));
    }


    @Test
    void updateRecipeIngredient() throws Exception {
        //wnen
        Set<UnitOfMeasureCommand> unitOfMeasureSet = new HashSet<>();
        when(ingredientService.getIngredientById(anyLong())).thenReturn(ingredientCommand1);
        when(uomService.listAllUoms()).thenReturn(unitOfMeasureSet);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/ingredients/1/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredients/ingredient-form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("uomList"));
        verify(ingredientService, times(1)).getIngredientById(anyLong());
        verify(uomService, times(1)).listAllUoms();
    }

}