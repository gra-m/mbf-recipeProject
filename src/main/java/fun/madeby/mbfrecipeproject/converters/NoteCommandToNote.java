package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.NoteCommand;
import fun.madeby.mbfrecipeproject.domain.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by Gra_m on 2022 04 17
 */

public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand source) {
        return null;
    }
}
