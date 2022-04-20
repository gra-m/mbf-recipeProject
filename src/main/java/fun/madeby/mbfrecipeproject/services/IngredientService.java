package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;

/**
 * Created by Gra_m on 2022 04 20
 */

public interface IngredientService {

    IngredientCommand getIngredientById(Long id);

}
