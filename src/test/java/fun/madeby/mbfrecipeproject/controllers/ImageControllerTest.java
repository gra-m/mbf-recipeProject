package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.services.ImageService;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {
    private static final String NUMBER_FORMAT_CAUSING_STRING = "asdfl;j";
    private static final String BAD_REQUEST_400 = "400error";

    @InjectMocks
    ImageController controllerUnderTest;

    @Mock
    RecipeService recipeService;

    @Mock
    ImageService imageService;

    MockMvc mockMvc;
    Recipe recipe1;
    Long recipe1_id = 1L;
    RecipeCommand recipe1Command;



    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
        this.recipe1 = new Recipe();
        this.recipe1.setId(this.recipe1_id);
        this.recipe1Command = new RecipeCommand();
        this.recipe1Command.setId(recipe1_id);

    }

    @Test
    @DisplayName("Bad Request returns 400 BAD_REQUEST")
    public void testGetImageNumberFormatException() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + NUMBER_FORMAT_CAUSING_STRING + "/image"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name(BAD_REQUEST_400))
                .andExpect(model().attributeExists("exception"));
    }



    @Test
    void testServeImageUpdateForm() throws Exception {
        //given recipe1 exists
        //when
        when(recipeService.getRecipeCommandById(recipe1_id)).thenReturn(recipe1Command);
        //then
        this.mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/image-upload-form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
        verify(recipeService, times(1)).getRecipeCommandById(anyLong());
    }

    @Test
    void testHandleImageUploadOrUpdate() throws Exception {
        //given
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile",
                "testing.txt", "text/plain", "Made By Funk".getBytes());

        //then

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/image"));
        verify(imageService, times(1)).saveImage(any(), anyLong());


    }

    @Test
    public void testRenderImageFromDb() throws Exception {
        int i = 0;
        //given

        String fakeImageText = "fakeImageText";

        Byte[] bytesBoxedfakeImage = new Byte[fakeImageText.getBytes().length];

        for(byte primByte : fakeImageText.getBytes())
            bytesBoxedfakeImage[i++] = primByte;

        recipe1Command.setImage(bytesBoxedfakeImage);

        when(recipeService.getRecipeCommandById(anyLong())).thenReturn(recipe1Command);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/" + recipe1_id + "/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();
        assertEquals(bytesBoxedfakeImage.length, responseBytes.length);



    }
}