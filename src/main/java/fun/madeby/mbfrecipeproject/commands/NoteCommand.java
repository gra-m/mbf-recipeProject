package fun.madeby.mbfrecipeproject.commands;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Gra_m on 2022 04 16
 */

@Setter
@Getter
@NoArgsConstructor
public class NoteCommand {
    private Long id;
    private String recipeNote;

}
