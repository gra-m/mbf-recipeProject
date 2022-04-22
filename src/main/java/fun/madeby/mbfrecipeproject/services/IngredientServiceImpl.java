package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.converters.IngredientCommandToIngredient;
import fun.madeby.mbfrecipeproject.converters.IngredientToIngredientCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import fun.madeby.mbfrecipeproject.repositories.IngredientRepository;
import fun.madeby.mbfrecipeproject.repositories.RecipeRepository;
import fun.madeby.mbfrecipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Created by Gra_m on 2022 04 20
 */

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository INGREDIENT_REPOSITORY;
    private final RecipeRepository RECIPE_REPOSITORY;
    private final UnitOfMeasureRepository UOM_REPOSITORY;
    private final IngredientToIngredientCommand INGREDIENT_TO_INGREDIENT_COMMAND;
    private final IngredientCommandToIngredient INGREDIENT_COMMAND_TO_INGREDIENT;


    public IngredientServiceImpl(IngredientRepository ingredientsRepository,
                                 RecipeRepository recipe_repository,
                                 UnitOfMeasureRepository uom_repository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.INGREDIENT_REPOSITORY = ingredientsRepository;
        this.RECIPE_REPOSITORY = recipe_repository;
        this.UOM_REPOSITORY = uom_repository;
        this.INGREDIENT_TO_INGREDIENT_COMMAND = ingredientToIngredientCommand;
        this.INGREDIENT_COMMAND_TO_INGREDIENT = ingredientCommandToIngredient;
    }

    @Override
    @Transactional
    public IngredientCommand getIngredientById(Long id) {
        Ingredient ingredient = new Ingredient();


        Optional<Ingredient> optionalIngredient = INGREDIENT_REPOSITORY.findById(id);
        if (optionalIngredient.isPresent()) {
            ingredient = optionalIngredient.get();
        } else {
            log.debug("error, ingredient Optional is empty");
            // TODO: error handling
        }
        return INGREDIENT_TO_INGREDIENT_COMMAND.convert(ingredient);
    }

    @Override
    @Transactional
    public IngredientCommand saveOrUpdateIngredientCommand(IngredientCommand command) {
        Recipe recipe;
        Ingredient updatingIngredient;
        UnitOfMeasure uomExists;
        Ingredient saveUpdateSuccessful;
        Long newUpdatedIngredientID = command.getId();

        Recipe retrievedRecipeIsVoid = retrieveAndTestRecipe(command);
        if (retrievedRecipeIsVoid.getId() == null) {
            log.error("Ingredient command is detatched recipe " + command.getRecipe_id() + " cannot be found");
            return new IngredientCommand();
        } else
            recipe = retrievedRecipeIsVoid;


        Ingredient isNewSave = retrieveIngredient(recipe, command.getId());
        if (isNewSave.getId() == null) {// todo create saveIngredient in Service
            log.info("converting and adding commandIng to RECIPE");
            Ingredient commandConvertedToIngredient = INGREDIENT_COMMAND_TO_INGREDIENT.convert(command);
            assert commandConvertedToIngredient != null;
            Ingredient savedIngredient = INGREDIENT_REPOSITORY.save(commandConvertedToIngredient);
            newUpdatedIngredientID = savedIngredient.getId();
            recipe.addIngredient(savedIngredient);

        } else {
            updatingIngredient = isNewSave;
                updatingIngredient.setDescription(command.getDescription());
                updatingIngredient.setAmount(command.getAmount());
                UnitOfMeasure uomNotInDb = retrieveAndTestUnitOfMeasure(command.getUom().getId());
                if (uomNotInDb.getId() == null)
                    throw new RuntimeException("UOM NOT FOUND");
                else
                    uomExists = uomNotInDb;
                    updatingIngredient.setUom(uomExists);
            }

            Recipe savedRecipe = RECIPE_REPOSITORY.save(recipe);
            Ingredient retrievalFailed = retrieveIngredient(savedRecipe, newUpdatedIngredientID);

        if (retrievalFailed.getId() == null)
            throw new RuntimeException("ERROR @ SAVE - IngredientCommand could not be retrieved from saved Recipe");
        else
            saveUpdateSuccessful = retrievalFailed;
            return INGREDIENT_TO_INGREDIENT_COMMAND.convert(saveUpdateSuccessful);
    }

    // region HELPER METHODS

    Ingredient retrieveIngredient(Recipe savedRecipe, Long ingredientId) {
        Ingredient retrievalFailed = new Ingredient();
        retrievalFailed.setId(null);

        Optional<Ingredient> ingredientRetrieved = savedRecipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();

        return ingredientRetrieved.orElse(retrievalFailed);
    }

    private UnitOfMeasure retrieveAndTestUnitOfMeasure(Long uomId) {
        UnitOfMeasure uomNotInDb = new UnitOfMeasure();
        uomNotInDb.setId(null);

        Optional<UnitOfMeasure> uomExists = UOM_REPOSITORY.findById(uomId);
        return uomExists.orElse(uomNotInDb);
    }

    private Recipe retrieveAndTestRecipe(IngredientCommand command) {
        Recipe voidRecipe = new Recipe();
        voidRecipe.setId(null);
        if (command.getId() == null && command.getRecipe_id() == null)
            return voidRecipe;

        Optional<Recipe> retrievedOptionalRecipe = RECIPE_REPOSITORY.findById(command.getRecipe_id());
        return retrievedOptionalRecipe.orElse(voidRecipe);
    }

    //endregion

}
