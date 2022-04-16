package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gra_m on 2022 04 16
 */

@Controller
public class RecipeController {
    private final RecipeService RECIPE_SERVICE;

    public RecipeController(RecipeService recipe_service) {
        RECIPE_SERVICE = recipe_service;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", RECIPE_SERVICE.findById(Long.valueOf(id)));

        return "recipe/show";
    }
}
