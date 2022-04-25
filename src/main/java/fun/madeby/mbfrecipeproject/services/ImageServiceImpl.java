package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * Created by Gra_m on 2022 04 25
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private final RecipeRepository RECIPE_REPOSITORY;

    public ImageServiceImpl(RecipeRepository recipe_repository) {
        RECIPE_REPOSITORY = recipe_repository;
    }

    @Override
    public Set<MultipartFile> getImages() {
        return null;
    }

    @Override
    public MultipartFile getImageById(Long aLong) {
        return null;
    }

    @Override
    @Transactional
    public void saveImage(MultipartFile image, Long id) {

        try {
            Recipe retrievedRecipe = RECIPE_REPOSITORY.findById(id).get();

            Byte[] byteObjects = new Byte[image.getBytes().length];

            int i = 0;

            for(byte b: image.getBytes())
                byteObjects[i++] = b;

            retrievedRecipe.setImage(byteObjects);

            RECIPE_REPOSITORY.save(retrievedRecipe);
        }catch(Exception e) {
            log.error("Error occurred", e);

            e.printStackTrace();
        }
    }

    @Override
    public void deleteImageById(Long id) {

    }
}
