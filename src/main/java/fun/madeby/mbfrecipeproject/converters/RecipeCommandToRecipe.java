package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.CategoryCommand;
import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Gra_m on 2022 04 17
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    final NoteCommandToNote NOTE_COMMAND_TO_NOTE;
    final CategoryCommandToCategory CATEGORY_COMMAND_TO_CATEGORY;
    final IngredientCommandToIngredient INGREDIENT_COMMAND_TO_INGREDIENT;

    public RecipeCommandToRecipe(NoteCommandToNote note_command_to_note,
                                 CategoryCommandToCategory category_command_to_category,
                                 IngredientCommandToIngredient ingredient_command_to_ingredient) {
        NOTE_COMMAND_TO_NOTE = note_command_to_note;
        CATEGORY_COMMAND_TO_CATEGORY = category_command_to_category;
        INGREDIENT_COMMAND_TO_INGREDIENT = ingredient_command_to_ingredient;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null)
            return null;

        final Recipe RECIPE = new Recipe();
        Set<CategoryCommand> categoryCommandSet = source.getCategories();
        Set<IngredientCommand> ingredientCommandSet = source.getIngredients();
        RECIPE.setId(source.getId());
        RECIPE.setTitle(source.getTitle());
        RECIPE.setDescription(source.getDescription());
        RECIPE.setPrepTime(source.getPrepTime());
        RECIPE.setCookTime(source.getCookTime());
        RECIPE.setServings(source.getServings());
        RECIPE.setSource(source.getSource());
        RECIPE.setUrl(source.getUrl());
        RECIPE.setDirections(source.getDirections());
        RECIPE.setDifficulty(source.getDifficulty());
        RECIPE.setImage(source.getImage());
        RECIPE.setNote(NOTE_COMMAND_TO_NOTE.convert(source.getNote()));

        if (categoryCommandSet != null && categoryCommandSet.size() > 0)
            categoryCommandSet.forEach(categoryCommand -> RECIPE
                    .getCategories()
                    .add(CATEGORY_COMMAND_TO_CATEGORY
                            .convert(categoryCommand)));
        if(ingredientCommandSet != null && ingredientCommandSet.size() > 0)
            ingredientCommandSet.forEach(ingredientCommand -> RECIPE
                    .getIngredients()
                    .add(INGREDIENT_COMMAND_TO_INGREDIENT.convert(ingredientCommand)));

        return RECIPE;

    }
}
