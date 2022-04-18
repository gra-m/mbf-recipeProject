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
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand source) {
        if (source == null)
            return null;

        final Note note = new Note();
        note.setId(source.getId());
        note.setRecipeNote(source.getRecipeNote());

        return note;
    }
}
