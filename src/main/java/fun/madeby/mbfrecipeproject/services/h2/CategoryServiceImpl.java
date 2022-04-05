package fun.madeby.mbfrecipeproject.services.h2;

import fun.madeby.mbfrecipeproject.domain.Category;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import fun.madeby.mbfrecipeproject.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Gra_m on 2022 04 05
 */
@Service
public class CategoryServiceImpl {
    private final CategoryRepository CATEGORY_REPOSITORY;

    public CategoryServiceImpl(CategoryRepository category_repository) {
        CATEGORY_REPOSITORY = category_repository;
    }
    public Optional<Category> findCategoryByDescription(String description){
        return CATEGORY_REPOSITORY.findCategoryByDescription(description);
    }

    public Iterable<Category> findAll() {
        return CATEGORY_REPOSITORY.findAll();
    }

}
