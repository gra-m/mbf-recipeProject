package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import org.hamcrest.core.IsSame;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @InjectMocks
    RecipeController controllerUnderTest;
    @Mock
    RecipeService recipeService;
    MockMvc mockMvc;
    Recipe recipe1;
    Recipe recipe2;


    @BeforeEach
    void setUp() {
        recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe2 = new Recipe();
        recipe2.setId(2L);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controllerUnderTest)
                .build();
    }

    @Test
    void testGetRecipeById() throws Exception {
       //given recipe1 exists, recipeService is in the Spring Context
       // and a mockRequest object has been built with this controller.

        when(recipeService.getRecipeById(anyLong()))
                .thenReturn(recipe1);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attribute("recipe", new IsSame(recipe1)));
    }

    @Test
    void testGetNewRecipeForm() throws Exception {
        //given /recipe/new has been requested by user (MockMvcRequestBuilders)

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipe-form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

}