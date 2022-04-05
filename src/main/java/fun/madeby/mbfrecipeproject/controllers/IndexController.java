package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.services.h2.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gra_m on 2022 03 28
 */

@Controller
public class IndexController {

    final RecipeServiceImpl RECIPE_SERVICE_IMPL;

    public IndexController(
            RecipeServiceImpl recipe_service_impl) {
        RECIPE_SERVICE_IMPL = recipe_service_impl;
    }


    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model){
       ArrayList<Recipe> recipeHashSet = RECIPE_SERVICE_IMPL.findAll();
      if(recipeHashSet.size() > 0) {
          model.addAttribute("recipes", recipeHashSet);
          return "index";
      }
        System.out.println("return");
      return null;
    }
}
