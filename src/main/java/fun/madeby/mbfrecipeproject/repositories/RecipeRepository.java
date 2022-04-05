package fun.madeby.mbfrecipeproject.repositories;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Gra_m on 2022 04 05
 */

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
