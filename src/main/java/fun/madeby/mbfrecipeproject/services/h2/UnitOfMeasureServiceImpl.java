package fun.madeby.mbfrecipeproject.services.h2;

import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import fun.madeby.mbfrecipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Created by Gra_m on 2022 04 05
 */
@Service
public class UnitOfMeasureServiceImpl {
    private final UnitOfMeasureRepository UOM_REPOSITORY;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uom_repository) {
        UOM_REPOSITORY = uom_repository;
    }

    public Optional<UnitOfMeasure> findUnitOfMeasurementByDescription(String description){
       return UOM_REPOSITORY.findUnitOfMeasureByDescription(description);
    }

    public Iterable<UnitOfMeasure> findAll() {
       return UOM_REPOSITORY.findAll();
    }


}
