package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UnitOfMeasureToUnitOfMeasureCommandTest {

    //Create constants each field to be tested
    static final Long ID = 1L;
    static final String DESCRIPTION = "description";

    //Bring in the converter to be tested and other converters necessary for test
    UnitOfMeasureToUnitOfMeasureCommand  converterUnderTest;

    @BeforeAll
    void setUp() {
        converterUnderTest = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullReturnedIfPassed() {
        //given converterUnderTest is @Nullable and returns null when passed null as a source
        //when
        Object object = converterUnderTest.convert(null);
        //then
        assertNull(object);
    }

    @Test
    public void testEmptyObjectNotReturnedAsNull() {
        //given converterUnderTest is returns an empty object when passed one

        //when
        Object object = converterUnderTest.convert(new UnitOfMeasure());

        // then null NOT returned to object
        assertNotNull(object);
    }

    @Test
    void testConvertDomainToCommand() {
       UnitOfMeasure givenUom = new UnitOfMeasure();
       givenUom.setId(1L);
       givenUom.setDescription("description");

       UnitOfMeasureCommand whenCommand = converterUnderTest.convert(givenUom);

       //then
       assertNotNull(whenCommand);
       assertEquals(ID, whenCommand.getId());
       assertEquals(DESCRIPTION, whenCommand.getDescription());
    }
}