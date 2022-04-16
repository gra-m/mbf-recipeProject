package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Gra_m on 2022 04 16
 */

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source == null)
            return null;

        final UnitOfMeasureCommand UOMC = new UnitOfMeasureCommand();
        UOMC.setId(source.getId());
        UOMC.setDescription(source.getDescription());
        return UOMC;
    }
}
