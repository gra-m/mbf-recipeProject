package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.exceptions.NotFoundException;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import org.hamcrest.core.IsSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {
    private static final String BAD_REQUEST_400 = "400error";
    private static final String NOT_FOUND_404 = "404error";
    private static final String NUMBER_FORMAT_CAUSING_STRING = "asdfl;j";
    private static final String RECIPE_FORM_URL = "recipe/recipe-form";

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
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Bad Request returns 400 BAD_REQUEST")
    public void testGetRecipeNumberFormatException() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + NUMBER_FORMAT_CAUSING_STRING + "/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name(BAD_REQUEST_400))
                .andExpect(model().attributeExists("exception"));
    }

    @Test
    void testGetRecipeById() throws Exception {
       //given recipe1 exists, recipeService is in the Spring Context
       // and a mockRequest object has been built with this controller.

        when(recipeService.getRecipeById(anyLong()))
                .thenReturn(recipe1);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attribute("recipe", new IsSame(recipe1)));
    }

    @Test
    @DisplayName("RecipeNotFound returns 404 NOT_FOUND")
    public void testGetRecipeNotFound() throws Exception {
        when(recipeService.getRecipeById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/-1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name(NOT_FOUND_404))
                .andExpect(model().attributeExists("exception"));
    }


    @Test
    void testGetNewRecipeForm() throws Exception {
        //given /recipe/new has been requested by user (MockMvcRequestBuilders)

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe-form"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    @DisplayName("VALID Save or update recipe")
    void testSaveOrUpdateRecipe() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(4L);
        when(recipeService.saveRecipeCommand(any()))
                .thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("title", "some string")
                .param("description", "some string")
                .param("directions", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/4/show"));
    }

    @Test
    @DisplayName("INVALID: Save or update recipe")
    void testSaveOrUpdateRecipe_Fail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("title", "")
                        .param("description", "")
                        .param("prepTime", String.valueOf(0))
                        .param("cookTime", String.valueOf(1000))
                        .param("servings", String.valueOf(0))
                        .param("directions", "   "))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name(RECIPE_FORM_URL));
    }

    @Test
    void testGetUpdateView() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(6L);

        when(recipeService.getRecipeCommandById(any()))
                .thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/6/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe-form"))
                .andExpect(model().attributeExists("recipe"));
    }


    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        // deleteById is part of Spring Data JPA so decided to test service that calls it
        Mockito.verify(recipeService, Mockito.times(1)).deleteRecipeById(anyLong());

    }































}