package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by Gra_m on 2022 04 20
 */

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
