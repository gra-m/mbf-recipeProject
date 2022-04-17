package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.CategoryCommand;
import fun.madeby.mbfrecipeproject.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Gra_m on 2022 04 17
 */

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null)
            return null;

        final CategoryCommand command = new CategoryCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());

        return command;
    }
}
