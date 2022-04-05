package fun.madeby.mbfrecipeproject.repositories;

import fun.madeby.mbfrecipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Gra_m on 2022 04 05
 */

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findCategoryByDescription(String description);
}
