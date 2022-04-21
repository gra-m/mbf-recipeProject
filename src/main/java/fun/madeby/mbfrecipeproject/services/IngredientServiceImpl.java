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

import java.math.BigDecimal;
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

        IngredientCommand retrievedSavedIngredientCommand;
        Ingredient ingredientBeingUpdated;
        Optional<Recipe> recipeIngredientCommandBelongsTo = RECIPE_REPOSITORY.findById(command.getRecipe_id());

        if (recipeIngredientCommandBelongsTo.isEmpty()) {
            log.error("Ingredient command is detatched recipe " + command.getRecipe_id() + " cannot be found");
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeIngredientCommandBelongsTo.get();

            Optional<Ingredient> ingredientOptionalToUpdate = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptionalToUpdate.isPresent()) {
                ingredientBeingUpdated = ingredientOptionalToUpdate.get();
                ingredientBeingUpdated.setDescription(command.getDescription());
                ingredientBeingUpdated.setAmount(command.getAmount());
                //CHECK back to source, not direct from command object
                Optional<UnitOfMeasure> confirmedUnitOfMeasureExists = UOM_REPOSITORY.findById(command.getUom().getId());
                if (confirmedUnitOfMeasureExists.isPresent()) {
                    ingredientBeingUpdated.setUom(confirmedUnitOfMeasureExists.get());
                } else {
                    throw new RuntimeException("UOM NOT FOUND");
                }
            } else {
                Ingredient commandConvertedToIngredient = INGREDIENT_COMMAND_TO_INGREDIENT.convert(command);
                assert commandConvertedToIngredient != null;
                recipe.addIngredient(commandConvertedToIngredient);
            }

            Recipe savedRecipe = RECIPE_REPOSITORY.save(recipe);

            /*retrievedSavedIngredientCommand = INGREDIENT_TO_INGREDIENT_COMMAND.convert(savedRecipe.getIngredients()
                    .stream()
                    .filter(ingredients -> ingredients.getId().equals(command.getId()))
                    .findFirst()
                    .get());*/

            Ingredient ingredientFound = new Ingredient();
            for(Ingredient ingredient : savedRecipe.getIngredients()) {
                if(ingredient.getId().equals(command.getId())){
                    ingredientFound = ingredient;
                    break;
                }
            }
            ingredientFound.setId(3L);
            ingredientFound.setDescription("Why");
            ingredientFound.setAmount(new BigDecimal(3));

            retrievedSavedIngredientCommand =  INGREDIENT_TO_INGREDIENT_COMMAND.convert(ingredientFound);



        }

        if(retrievedSavedIngredientCommand != null)
            return retrievedSavedIngredientCommand;
        else
            throw new RuntimeException("ERROR @ SAVE - IngredientCommand could not be retrieved from saved Recipe");
    }






}
