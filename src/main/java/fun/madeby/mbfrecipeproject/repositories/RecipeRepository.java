package fun.madeby.mbfrecipeproject.repositories;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Gra_m on 2022 04 05
 */
@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Optional<Recipe> findRecipeByTitle(String title);
    Iterable<Recipe> findAll();
    Optional<Recipe> findById(Long aLong);

}
