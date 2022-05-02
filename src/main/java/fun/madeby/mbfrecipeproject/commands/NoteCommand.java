package fun.madeby.mbfrecipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Created by Gra_m on 2022 04 16
 */

@Setter
@Getter
@NoArgsConstructor
public class NoteCommand {
    private Long id;
    @NotBlank
    private String recipeNote;

}
