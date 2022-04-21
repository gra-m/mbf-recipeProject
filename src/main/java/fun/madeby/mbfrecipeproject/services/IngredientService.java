package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;


/**
 * Created by Gra_m on 2022 04 20
 */

public interface IngredientService {

    //Optional<Recipe> getIngredientById(Long id);
    IngredientCommand getIngredientById(Long id);
    IngredientCommand saveOrUpdateIngredientCommand(IngredientCommand command);
}
