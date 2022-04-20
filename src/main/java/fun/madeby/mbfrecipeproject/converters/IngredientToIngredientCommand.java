package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Gra_m on 2022 04 17
 */
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand UOM_CONVERTER;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand UOM_CONVERTER) {
        this.UOM_CONVERTER = UOM_CONVERTER;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null)
            return null;

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setUom(UOM_CONVERTER.convert(source.getUom()));
        // recipe_id added to IngredientCommand whenever recipe not null (should only ever be null in testing)
        if (source.getRecipe() != null)
            ingredientCommand.setRecipe_id(source.getRecipe().getId());

        return ingredientCommand;
    }
}
