package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.services.IngredientService;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import fun.madeby.mbfrecipeproject.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Gra_m on 2022 04 20
 */

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService RECIPE_SERVICE;
    private final IngredientService INGREDIENT_SERVICE;
    private final UnitOfMeasureService UOM_SERVICE;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.RECIPE_SERVICE = recipeService;
        INGREDIENT_SERVICE = ingredientService;
        UOM_SERVICE = uomService;
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

    @GetMapping
    @RequestMapping("/recipe/ingredients/{id}/update")
    public String updateRecipeIngredient(@PathVariable String id, Model model){

        IngredientCommand commandMustKnowRecipe = INGREDIENT_SERVICE.getIngredientById(Long.valueOf(id));
        log.debug("recipe/ingredients/{id}/update - command knows itself: " + commandMustKnowRecipe.getId());
        log.debug("COMMAND DOES KNOW RECIPE" + commandMustKnowRecipe.getRecipe_id());

        model.addAttribute("ingredient", commandMustKnowRecipe);


        //

        model.addAttribute("uomList", UOM_SERVICE.listAllUoms());
        return "recipe/ingredients/ingredient-form";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){


        try {
            Long commandId = command.getId();
            if(commandId == null)
                throw new RuntimeException();
        }catch (RuntimeException e) {
           e.printStackTrace();
            System.out.println(e + "THE COMMAND ID IS NULL");
        }

        try {
            Long commandRecipeId = command.getRecipe_id();
            if(commandRecipeId == null)
                throw new RuntimeException();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("DOES NOT KNOW RECIPE ID");
        }


        IngredientCommand savedCommand = new IngredientCommand();
        log.info("recipe/{recipeId}/ingredient.. It has Id: " + command.getRecipe_id());

        try {
            savedCommand = INGREDIENT_SERVICE.saveOrUpdateIngredientCommand(command);
        }catch (RuntimeException e){
            System.out.println("command has Id of: " + command.getId() + "\ncommand has recipe value of:" +
                    command.getRecipe_id());
        }


        try {
            Long commandId = savedCommand.getId();
            if(commandId == null)
                throw new RuntimeException();
        }catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println(e + "AFTER SAVING THE COMMAND ID IS NULL");
        }

        try {
            Long commandRecipeId = savedCommand.getRecipe_id();
            if(commandRecipeId == null)
                throw new RuntimeException();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("AFTER SAVING DOES NOT KNOW RECIPE ID");
        }

        log.info("saved recipe id:" + savedCommand.getRecipe_id());
        log.info("saved ingredient id:" + savedCommand.getId());

        //return "redirect:/recipe/ingredients/" + savedCommand.getId() + "/show";

        return "redirect:/recipe/ingredients/" + savedCommand.getId() + "/show";
    }

}
