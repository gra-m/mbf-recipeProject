package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 20
 */

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
    UnitOfMeasureCommand getOrCreateBlankDescriptionUnitOfMeasureCommand();
    Optional<UnitOfMeasure> getUnitOfMeasureByDescription(String description);
    UnitOfMeasure saveUnitOfMeasure(UnitOfMeasure newUnitOfMeasure);
}
