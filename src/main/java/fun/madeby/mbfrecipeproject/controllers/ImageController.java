package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.services.ImageService;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Gra_m on 2022 04 25
 */
@Slf4j
@Controller
public class ImageController {
    private final RecipeService RECIPE_SERVICE;
    private final ImageService IMAGE_SERVICE;

    public ImageController(RecipeService recipe_service, ImageService imageService) {
        RECIPE_SERVICE = recipe_service;
        IMAGE_SERVICE = imageService;
    }


    @GetMapping("/recipe/{id}/image")
    public String serveImageUpdateForm(@PathVariable String id, Model model) {
        log.debug("/recipe/{id}/image -->GET");
        model.addAttribute("recipe", RECIPE_SERVICE.getRecipeCommandById(Long.valueOf(id)));

        return "recipe/image-upload-form";
    }


    @PostMapping("/recipe/{id}/image")
    public String handleImageUploadOrUpdate(@PathVariable String id,
                                            @RequestParam("imagefile") MultipartFile imageFile) {
        log.debug("/recipe/{id}/image -->POST");

        IMAGE_SERVICE.saveImage(imageFile, Long.valueOf(id));


        return "redirect:/recipe/" + id + "/image";
    }


    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDb(@PathVariable String id, HttpServletResponse response) {
        log.debug("/recipe/{id}/recipeimage");
        int i = 0;

        try {
            RecipeCommand recipeCommand = RECIPE_SERVICE.getRecipeCommandById(Long.valueOf(id));

            byte[] byteArray = new byte[recipeCommand.getImage().length];

            for(Byte wrappedByte: recipeCommand.getImage())
                byteArray[i++] = wrappedByte;

            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch(NullPointerException e){
            log.error("Known issue when db initialised without images");

            e.printStackTrace();
        } catch (IOException e) {
            log.error("Error copying inputstream to outputstream");

            e.printStackTrace();
        } catch(Exception e) {
            log.error("Error with recipeCommand/Byteconversion");

            e.printStackTrace();
        }
    }


}
