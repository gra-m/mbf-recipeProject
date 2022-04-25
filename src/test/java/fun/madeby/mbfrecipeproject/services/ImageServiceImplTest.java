package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    ImageService imageService = new ImageServiceImpl(recipeRepository);

    /*@InjectMocks
    ImageServiceImpl imageService;*/

    Recipe recipe1;
    Long recipe1_id = 1L;

    @BeforeEach
    void setUp() {


        this.recipe1 = new Recipe();
        this.recipe1.setId(this.recipe1_id);
    }

    @Test
    void getImages() {
    }

    @Test
    void getImageById() {
    }

    @Test
    void testSaveImage() throws IOException {
        //given
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile",
                        "testing.txt", "text/plain", "Made By Funk".getBytes());
        Optional<Recipe> optionalOfRecipe1 = Optional.of(recipe1);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalOfRecipe1);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        //when
        imageService.saveImage(multipartFile, recipe1_id);

        //then
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }

    @Test
    void deleteImageById() {
    }
}