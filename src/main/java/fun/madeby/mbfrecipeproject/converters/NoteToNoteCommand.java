package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.NoteCommand;
import fun.madeby.mbfrecipeproject.domain.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Gra_m on 2022 04 17
 */
@Component
public class NoteToNoteCommand implements Converter <Note, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note source) {
        if (source == null)
            return null;

       final NoteCommand command = new NoteCommand();
       command.setId(source.getId());
       command.setRecipeNote(source.getRecipeNote());

       return command;
    }
}
