package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by Gra_m on 2022 03 28
 */

@Slf4j
@Controller
public class IndexController {

    final RecipeServiceImpl RECIPE_SERVICE_IMPL;

    public IndexController(
            RecipeServiceImpl recipe_service_impl) {
        RECIPE_SERVICE_IMPL = recipe_service_impl;
    }


    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model){
       log.debug("IndexC_GET_/index '/' '' ");
       ArrayList<Recipe> recipeArrayList = RECIPE_SERVICE_IMPL.findAll();
      if(recipeArrayList.size() > 0) {
          model.addAttribute("recipes", recipeArrayList);
          return "index";
      }
      return null;
    }
}
