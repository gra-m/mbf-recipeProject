package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import fun.madeby.mbfrecipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * Created by Gra_m on 2022 04 05
 */
@Service
public class UnitOfMeasureServiceImpl {
    private final UnitOfMeasureRepository UOM_REPOSITORY;
    private final UnitOfMeasureToUnitOfMeasureCommand UOM_To_UOM_COMMAND;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uom_repository,
                                    UnitOfMeasureToUnitOfMeasureCommand uom_to_uom_command) {
        UOM_REPOSITORY = uom_repository;
        UOM_To_UOM_COMMAND = uom_to_uom_command;
    }

    public Optional<UnitOfMeasure> findUnitOfMeasurementByDescription(String description){
       return UOM_REPOSITORY.findUnitOfMeasureByDescription(description);
    }
    public Iterable<UnitOfMeasure> findAll() {
        return UOM_REPOSITORY.findAll();
    }

    @Transactional
    public Set<UnitOfMeasureCommand> ListAllUOMs() {
       return StreamSupport.stream(UOM_REPOSITORY.findAll()
               .spliterator(), false)
               .map(UOM_To_UOM_COMMAND::convert)
               .collect(Collectors.toSet());
    }


}
