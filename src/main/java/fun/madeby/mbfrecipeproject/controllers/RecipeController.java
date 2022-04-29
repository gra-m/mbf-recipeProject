package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.exceptions.NotFoundException;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Gra_m on 2022 04 16
 */
@Slf4j
@Controller
public class RecipeController {
    private static final String NOT_FOUND_404 = "404error";
    private final RecipeService RECIPE_SERVICE;


    public RecipeController(RecipeService recipe_service) {
        RECIPE_SERVICE = recipe_service;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model) {
        log.debug("RC_GET_/recipe/{id}/show");
        model.addAttribute("recipe", RECIPE_SERVICE.getRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipeForm(Model model) {
        log.debug("RC_GET_/recipe/new");
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipe-form";
    }

    @GetMapping("/recipe/{id}/update")
    public String getUpdateView(@PathVariable String id, Model model) {
        log.debug("RC_GET_/recipe/{id}/update");
        model.addAttribute("recipe", RECIPE_SERVICE.getRecipeCommandById(Long.valueOf(id)));
        return "recipe/recipe-form";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        log.debug("RC_GET_/recipe/{id}/delete");

        RECIPE_SERVICE.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping("/recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command) {
        log.debug("RC_POST_/recipe");

        RecipeCommand savedRecipeCommand = RECIPE_SERVICE.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundError(Exception e){

        log.error("Handling not found exception");
        log.error(e.getMessage());


        ModelAndView notFoundViewAndExceptionModel = new ModelAndView();
        notFoundViewAndExceptionModel.setViewName(NOT_FOUND_404);
        notFoundViewAndExceptionModel.addObject("exception", e);

        return notFoundViewAndExceptionModel;
    }


}
