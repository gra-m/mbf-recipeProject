package fun.madeby.mbfrecipeproject.commands;

import fun.madeby.mbfrecipeproject.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 16
 */
@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;
    private String title;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private NoteCommand note;
    private Set<CategoryCommand> categories = new HashSet<>();

}
