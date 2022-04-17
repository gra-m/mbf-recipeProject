package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.CategoryCommand;
import fun.madeby.mbfrecipeproject.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by Gra_m on 2022 04 17
 */

public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        return null;
    }
}
