package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Gra_m on 2022 04 16
 */

@Controller
public class RecipeController {
    private final RecipeService RECIPE_SERVICE;

    public RecipeController(RecipeService recipe_service) {
        RECIPE_SERVICE = recipe_service;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", RECIPE_SERVICE.getRecipeById(Long.valueOf(id)));

        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String newRecipeForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipe-form";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/update")
    public String getUpdateView(@PathVariable String id, Model model) {

        model.addAttribute("recipe", RECIPE_SERVICE.getRecipeCommandById(Long.valueOf(id)));

        return "recipe/recipe-form";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {

        RECIPE_SERVICE.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedRecipeCommand = RECIPE_SERVICE.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }
}
