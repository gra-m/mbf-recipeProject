package fun.madeby.mbfrecipeproject.repositories;

import fun.madeby.mbfrecipeproject.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * Created by Gra_m on 2022 04 20
 */

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Optional<Ingredient> findIngredientById(Long id);
    Optional<Ingredient> findIngredientByDescription(String description);
}
