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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    Long uom1Id = 1L;
    Long uom2Id = 3L;

    @BeforeEach
    void setup()  {
        unitOfMeasureSet = new HashSet<>(2);
        unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure1.setId(uom1Id);
        unitOfMeasure2.setId(uom2Id);
        unitOfMeasureSet.add(unitOfMeasure1);
        unitOfMeasureSet.add(unitOfMeasure2);

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
        verify(unitOfMeasureRepository, times(1)).findAll();
        assertEquals(2, intoList.size());



    }

    @Test
    void testGetOrCreateBlankDescriptionUnitOfMeasureCommand() {
    }

    @Test
    void testGetUnitOfMeasureByDescription() {
    }

    @Test
    void testSaveUnitOfMeasure() {
    }
}