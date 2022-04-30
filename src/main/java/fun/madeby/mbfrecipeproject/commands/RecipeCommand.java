package fun.madeby.mbfrecipeproject.commands;

import fun.madeby.mbfrecipeproject.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min =1, max=255)
    private String title;

    @NotBlank
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(999)
    private Integer servings;

    @NotBlank
    private String directions;

    private String source;
    private String url;
    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private NoteCommand note;
    private Set<CategoryCommand> categories = new HashSet<>();

}
