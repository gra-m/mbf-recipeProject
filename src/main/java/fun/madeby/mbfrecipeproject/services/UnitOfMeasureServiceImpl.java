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
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureRepository UOM_REPOSITORY;
    private final UnitOfMeasureToUnitOfMeasureCommand UOM_To_UOM_COMMAND;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uom_repository,
                                    UnitOfMeasureToUnitOfMeasureCommand uom_to_uom_command) {
        UOM_REPOSITORY = uom_repository;
        UOM_To_UOM_COMMAND = uom_to_uom_command;
    }


    @Override
    public Iterable<UnitOfMeasure> getAllUomsAsIterable() {
        return UOM_REPOSITORY.findAll();
    }

    @Override
    @Transactional
    public Set<UnitOfMeasureCommand> listAllUoms() {
       return StreamSupport.stream(UOM_REPOSITORY.findAll()
               .spliterator(), false)
               .map(UOM_To_UOM_COMMAND::convert)
               .collect(Collectors.toSet());
    }

    /**Created as wanted to reuse the ingredient form but was wary
     * of a situation where a blank Uom would not be available.
     * @return UnitOfMeasureCommand
     */
    @Override
    @Transactional
    public UnitOfMeasureCommand getOrCreateBlankDescriptionUnitOfMeasureCommand() {
        UnitOfMeasure savedBlankUom = new UnitOfMeasure();

        Optional<UnitOfMeasure> blankAlreadyExistsInDb = getUnitOfMeasureByDescription("");

        if(blankAlreadyExistsInDb.isPresent())
            return UOM_To_UOM_COMMAND.convert(blankAlreadyExistsInDb.get());
        else {
            UnitOfMeasure createdBlankUom = new UnitOfMeasure();
            createdBlankUom.setDescription("");
            savedBlankUom = saveUnitOfMeasure(createdBlankUom);
            return UOM_To_UOM_COMMAND.convert(savedBlankUom);
        }
    }

    @Override
    public Optional<UnitOfMeasure> getUnitOfMeasureByDescription(String description) {
        return UOM_REPOSITORY.findUnitOfMeasureByDescription(description);
    }

    @Override
    public UnitOfMeasure saveUnitOfMeasure(UnitOfMeasure newUnitOfMeasure) {
        return UOM_REPOSITORY.save(newUnitOfMeasure);
    }

}
