package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.domain.Recipe;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

    @InjectMocks
    ImageController controllerUnderTest;

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;
    Recipe recipe1;
    Long recipe1_id = 1L;
    RecipeCommand recipe1Command;



    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.controllerUnderTest}).build();
        this.recipe1 = new Recipe();
        this.recipe1.setId(this.recipe1_id);
        this.recipe1Command = new RecipeCommand();
        this.recipe1Command.setId(recipe1_id);

    }

    @Test
    void testServeImageUpdateForm() throws Exception {
        //given recipe1 exists
        //when
        when(recipeService.getRecipeCommandById(recipe1_id)).thenReturn(recipe1Command);
        //then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/image"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/image-upload-form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
        verify(recipeService, times(1)).getRecipeCommandById(anyLong());
    }

    @Test
    void testHandleImageUploadOrUpdate() {
    }
}