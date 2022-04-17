package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    static final String DESCRIPTION = "description";
    static final Long LONG_VALUE = 1L;
    static UnitOfMeasureCommandToUnitOfMeasure converterUnderTest;

    @BeforeAll
    public static void setUp() {
        converterUnderTest = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullReturnedIfPassed() {
       //given converterUnderTest is @Nullable and returns null when passed null as a source

       //when
       Object object = converterUnderTest.convert(null);

       // then null should be returned to object
       assertNull(object);
    }

    @Test
    public void testEmptyObjectNotReturnedAsNull() {
        //given converterUnderTest is returns an empty object when passed one

        //when
        Object object = converterUnderTest.convert(new UnitOfMeasureCommand());

        // then null NOT returned to object
        assertNotNull(object);
    }

    @Test
    void testConvertCommandToDomain() {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure whenDomain = converterUnderTest.convert(command);

        //then
        assertNotNull(whenDomain);
        assertEquals(LONG_VALUE, whenDomain.getId());
        assertEquals(DESCRIPTION, whenDomain.getDescription());
    }
}