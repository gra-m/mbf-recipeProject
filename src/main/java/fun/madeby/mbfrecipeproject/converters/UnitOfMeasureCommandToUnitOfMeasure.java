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
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if(source == null)
            return null;


        final UnitOfMeasure UOM = new UnitOfMeasure();
        UOM.setId(source.getId());
        UOM.setDescription(source.getDescription());
        return UOM;
    }
}
