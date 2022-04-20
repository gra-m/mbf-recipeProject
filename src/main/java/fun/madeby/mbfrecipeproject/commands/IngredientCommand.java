package fun.madeby.mbfrecipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Gra_m on 2022 04 16
 */

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private RecipeCommand recipe;
    private Long recipe_id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
}
