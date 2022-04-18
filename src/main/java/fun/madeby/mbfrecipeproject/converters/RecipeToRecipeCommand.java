package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.domain.Category;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.Set;

/**
 * Created by Gra_m on 2022 04 17
 */

public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NoteToNoteCommand NOTE_TO_NOTE_COMMAND;
    private final IngredientToIngredientCommand INGREDIENT_TO_INGREDIENT_COMMAND;
    private final CategoryToCategoryCommand CATEGORY_TO_CATEGORY_COMMAND;

    public RecipeToRecipeCommand(NoteToNoteCommand noteToNoteCommand,
                                 IngredientToIngredientCommand ingredient_to_ingredient_command,
                                 CategoryToCategoryCommand category_to_category_command) {
        NOTE_TO_NOTE_COMMAND = noteToNoteCommand;
        INGREDIENT_TO_INGREDIENT_COMMAND = ingredient_to_ingredient_command;
        CATEGORY_TO_CATEGORY_COMMAND = category_to_category_command;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null)
            return null;

        final Set<Ingredient> INGREDIENT_SET = source.getIngredients();
        final Set<Category> CATEGORY_SET = source.getCategories();

        final RecipeCommand RECIPE_COMMAND = new RecipeCommand();
        RECIPE_COMMAND.setId(source.getId());
        RECIPE_COMMAND.setTitle(source.getTitle());
        RECIPE_COMMAND.setDescription(source.getDescription());
        RECIPE_COMMAND.setPrepTime(source.getPrepTime());
        RECIPE_COMMAND.setCookTime(source.getCookTime());
        RECIPE_COMMAND.setServings(source.getServings());
        RECIPE_COMMAND.setSource(source.getSource());
        RECIPE_COMMAND.setUrl(source.getUrl());
        RECIPE_COMMAND.setDirections(source.getDirections());
        RECIPE_COMMAND.setDifficulty(source.getDifficulty());
        RECIPE_COMMAND.setImage(source.getImage());
        RECIPE_COMMAND.setNote(NOTE_TO_NOTE_COMMAND.convert(source.getNote()));

        if(INGREDIENT_SET != null && INGREDIENT_SET.size() > 0)
            INGREDIENT_SET
                    .forEach(ingredient -> RECIPE_COMMAND
                            .getIngredients()
                            .add(INGREDIENT_TO_INGREDIENT_COMMAND
                                    .convert(ingredient)));

       if(CATEGORY_SET != null && CATEGORY_SET.size() >0)
           CATEGORY_SET
                   .forEach(category -> RECIPE_COMMAND
                           .getCategories()
                           .add(CATEGORY_TO_CATEGORY_COMMAND
                                   .convert(category)));

       return RECIPE_COMMAND;
    }
}
