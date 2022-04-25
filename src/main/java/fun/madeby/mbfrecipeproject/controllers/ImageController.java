package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Gra_m on 2022 04 25
 */
@Slf4j
@Controller
public class ImageController {
    private final RecipeService RECIPE_SERVICE;

    public ImageController(RecipeService recipe_service) {
        RECIPE_SERVICE = recipe_service;
    }


    @GetMapping("/recipe/{id}/image")
    public String serveImageUpdateForm(@PathVariable String id, Model model) {
        log.debug("/recipe/{id}/image -->GET");
        model.addAttribute("recipe", RECIPE_SERVICE.getRecipeCommandById(Long.valueOf(id)));

        return "recipe/image-upload-form";
    }


    @PostMapping("recipe/{id}/image")
    public String handleImageUploadOrUpdate() {
        log.debug("/recipe/{id}/image -->POST");
        return "hello";
    }
}
