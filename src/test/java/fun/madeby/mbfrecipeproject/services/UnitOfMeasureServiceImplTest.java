package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import fun.madeby.mbfrecipeproject.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @InjectMocks
    UnitOfMeasureServiceImpl unitOfMeasureServiceImpl;

    Set<UnitOfMeasure> unitOfMeasureSet;
    List<UnitOfMeasure> unitOfMeasureList = new ArrayList<>();
    UnitOfMeasure unitOfMeasure1;
    UnitOfMeasure unitOfMeasure2;
    UnitOfMeasureCommand unitOfMeasure2Command;
    Long uom1Id = 1L;
    Long uom2Id = 3L;

    @BeforeEach
    void setup()  {
        unitOfMeasureSet = new HashSet<>(2);
        unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure1.setId(uom1Id);
        unitOfMeasure2.setId(uom2Id);
        unitOfMeasure2.setDescription("");
        unitOfMeasureSet.add(unitOfMeasure1);
        unitOfMeasureSet.add(unitOfMeasure2);

        unitOfMeasure2Command = new UnitOfMeasureCommand();
        unitOfMeasure2Command.setId(uom2Id);
        unitOfMeasure2Command.setDescription("");

        unitOfMeasureList.add(unitOfMeasure1);
        unitOfMeasureList.add(unitOfMeasure2);
    }

    @Test
    void testListAllUOMs() {
        // This test was failing unitsOfMeasure == null for ages as I forgot to mock in necessary converter.
       //given unitsOfMeasure Setup and:
       when(unitOfMeasureRepository.findAll())
               .thenReturn(unitOfMeasureSet);

      //when
        Set<UnitOfMeasureCommand> returnedCommandSet =  unitOfMeasureServiceImpl.listAllUoms();

        assertNotNull(returnedCommandSet);
        verify(unitOfMeasureRepository, times(1)).findAll();
        assertEquals(1, returnedCommandSet.size());
    }


    @Test
    void testGetAllUomsAsIterable() {
        //given
        Iterable<UnitOfMeasure> unitOfMeasureIterable = unitOfMeasureList;
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasureIterable);

        //when
        Iterable<UnitOfMeasure> returnedUomIterable = unitOfMeasureServiceImpl.getAllUomsAsIterable();
        List<UnitOfMeasure> intoList = (List<UnitOfMeasure>) returnedUomIterable;

        //then
        assertNotNull(intoList);
        verify(unitOfMeasureRepository, times(1)).findAll();
        assertEquals(2, intoList.size());



    }

    //region BlankUom Creation ExistsAlready/noBlankUom

    @Test
    void testGetOrCreateBlankDescriptionUnitOfMeasureCommand_BlankUomAlreadyExists() {
        //given
        Optional<UnitOfMeasure> blankAlreadyExistsinDb = Optional.of(unitOfMeasure1);
        when(unitOfMeasureRepository.findUnitOfMeasureByDescription("")).thenReturn(blankAlreadyExistsinDb);
        when(unitOfMeasureToUnitOfMeasureCommand.convert(any(UnitOfMeasure.class))).thenReturn(unitOfMeasure2Command);

        //when
        UnitOfMeasureCommand returnedFoundBlank = unitOfMeasureServiceImpl.getOrCreateBlankDescriptionUnitOfMeasureCommand();

        //then
        assertNotNull(returnedFoundBlank);
        assertEquals(uom2Id, returnedFoundBlank.getId());
        assertEquals("", returnedFoundBlank.getDescription());

    }

    @Test
    void testGetOrCreateBlankDescriptionUnitOfMeasureCommand_NoBlankUom() {
        //given
        Optional<UnitOfMeasure> blankDoesNotExist = Optional.empty();
        when(unitOfMeasureRepository.findUnitOfMeasureByDescription("")).thenReturn(blankDoesNotExist);
        when(unitOfMeasureRepository.save(any(UnitOfMeasure.class))).thenReturn(unitOfMeasure2);
        when(unitOfMeasureToUnitOfMeasureCommand.convert(any(UnitOfMeasure.class))).thenReturn(unitOfMeasure2Command);

        //when
        UnitOfMeasureCommand returnedFoundBlank = unitOfMeasureServiceImpl.getOrCreateBlankDescriptionUnitOfMeasureCommand();

        //then
        assertNotNull(returnedFoundBlank);
        assertEquals(uom2Id, returnedFoundBlank.getId());
        assertEquals("", returnedFoundBlank.getDescription());
        verify(unitOfMeasureRepository, times(1)).findUnitOfMeasureByDescription(anyString());
        verify(unitOfMeasureRepository, times(1)).save(any(UnitOfMeasure.class));
    }



    //endregion

    @Test
    void testGetUnitOfMeasureByDescription() {
    }

    @Test
    void testSaveUnitOfMeasure() {
    }
}