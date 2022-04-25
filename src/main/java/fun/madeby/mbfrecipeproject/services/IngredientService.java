package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;


/**
 * Created by Gra_m on 2022 04 20
 */

public interface IngredientService {

    IngredientCommand getIngredientById(Long id);
    IngredientCommand saveOrUpdateIngredientCommand(IngredientCommand command);
    Ingredient saveIngredient(Ingredient ingredient);
    void deleteIngredientById(Long id);
}
