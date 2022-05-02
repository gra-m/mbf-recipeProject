package fun.madeby.mbfrecipeproject.commands;

import fun.madeby.mbfrecipeproject.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;
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

    @URL
    private String url;

    @Valid
    private NoteCommand note;


    private String source;

    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private Set<CategoryCommand> categories = new HashSet<>();

}
