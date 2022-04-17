package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
// Updated Course Style Version with GlobalObject setup
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IngredientCommandToIngredientTest {
    static final Long ID = 1L;
    static final Long UOM_ID = 2L;
    static final String DESCRIPTION = "willowbark";
    static final UnitOfMeasureCommand UNIT_OF_MEASURE = new UnitOfMeasureCommand();
    static final BigDecimal AMOUNT = new BigDecimal(3);

    IngredientCommandToIngredient converterUnderTest;
    IngredientCommand testCommandObject;
    IngredientCommand testCommandObjectNullUom;

    @BeforeAll
    void setUp() {
        converterUnderTest = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        testCommandObject = new IngredientCommand();
        testCommandObject.setId(ID);
        testCommandObject.setDescription(DESCRIPTION);
        UNIT_OF_MEASURE.setId(UOM_ID);
        testCommandObject.setUom(UNIT_OF_MEASURE);
        testCommandObject.setAmount(AMOUNT);
        testCommandObjectNullUom = new IngredientCommand();
        testCommandObjectNullUom.setId(ID);
        testCommandObjectNullUom.setDescription(DESCRIPTION);
        testCommandObjectNullUom.setAmount(AMOUNT);
    }

    @Test
    void testNullReturnedIfPassed() {
        IngredientCommand givenIngredient;
        givenIngredient = null;
        Object whenObject = converterUnderTest.convert(givenIngredient);
        //then
        assertNull(whenObject);
    }

    @Test
    public void testEmptyObjectNotReturnedAsNull() {
        IngredientCommand givenIngredient = new IngredientCommand();
        Object whenObject = converterUnderTest.convert(givenIngredient);
        //then
        assertNotNull(whenObject);
    }

    @Test
    void testConvertCommandToDomain() {
        IngredientCommand givenTestCommandObject = testCommandObject;
        Ingredient whenDomain = converterUnderTest.convert(givenTestCommandObject);
        //then
        assertNotNull(whenDomain);
        assertNotNull(whenDomain.getUom());
        assertEquals(ID, whenDomain.getId());
        assertEquals(DESCRIPTION, whenDomain.getDescription());
        assertEquals(UOM_ID, whenDomain.getUom().getId());
        assertEquals(AMOUNT, whenDomain.getAmount());
    }

    @Test
    void testConvertCommandWithNullUomToDomain() {
        IngredientCommand givenTestCommandObject = testCommandObjectNullUom;
        Ingredient whenDomain = converterUnderTest.convert(givenTestCommandObject);
        //then
        assertNotNull(whenDomain);
        assertNull(whenDomain.getUom());
        assertEquals(ID, whenDomain.getId());
        assertEquals(DESCRIPTION, whenDomain.getDescription());
        assertEquals(AMOUNT, whenDomain.getAmount());
    }
}
