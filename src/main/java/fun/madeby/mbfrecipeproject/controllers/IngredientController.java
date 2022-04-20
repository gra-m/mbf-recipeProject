package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.services.IngredientService;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gra_m on 2022 04 20
 */

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService RECIPE_SERVICE;
    private final IngredientService INGREDIENT_SERVICE;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService) {
        this.RECIPE_SERVICE = recipeService;
        INGREDIENT_SERVICE = ingredientService;
    }


    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listRecipeIngredients(@PathVariable String id, Model model) {
        log.debug("/recipe/id/ingredients endpoint reached");

        model.addAttribute("recipe", RECIPE_SERVICE.getRecipeById(Long.valueOf(id)));

        return "recipe/ingredients/list";
    }

    @GetMapping
    @RequestMapping("/recipe/ingredients/{id}/show")
    public String showIngredient(@PathVariable String id, Model model) {
        log.debug("/recipe/{recipe_id}/ingredients/{id}/show endpoint reached");

        model.addAttribute("ingredient", INGREDIENT_SERVICE.getIngredientById(Long.valueOf(id)));

        return "recipe/ingredients/show";


    }
}
