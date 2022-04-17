package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by Gra_m on 2022 04 17
 */

public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        return null;
    }
}
