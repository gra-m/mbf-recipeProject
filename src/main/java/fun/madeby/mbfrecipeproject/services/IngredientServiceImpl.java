package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.converters.IngredientToIngredientCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import fun.madeby.mbfrecipeproject.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Created by Gra_m on 2022 04 20
 */

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientRepository INGREDIENT_REPOSITORY;
    private final IngredientToIngredientCommand INGREDIENT_TO_INGREDIENT_COMMAND;

    public IngredientServiceImpl(IngredientRepository ingredientsRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.INGREDIENT_REPOSITORY = ingredientsRepository;
        this.INGREDIENT_TO_INGREDIENT_COMMAND = ingredientToIngredientCommand;
    }

    @Override
    @Transactional
    public IngredientCommand getIngredientById(Long id) {
        Ingredient ingredient = new Ingredient();


        Optional<Ingredient> optionalIngredient = INGREDIENT_REPOSITORY.findIngredientById(id);
        if (optionalIngredient.isPresent()) {
            ingredient = optionalIngredient.get();
        } else {
            log.debug("error, ingredient Optional is empty");
            // TODO: error handling
        }
        return INGREDIENT_TO_INGREDIENT_COMMAND.convert(ingredient);
    }
}
