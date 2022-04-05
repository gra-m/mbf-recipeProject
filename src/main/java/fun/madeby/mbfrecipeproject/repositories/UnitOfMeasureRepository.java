package fun.madeby.mbfrecipeproject.repositories;

import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Gra_m on 2022 04 05
 */

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findUnitOfMeasureByDescription(String description);

}
