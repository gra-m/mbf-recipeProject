package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IngredientToIngredientCommandTest {
    static final Long ID = 1L;
    static final String DESCRIPTION = "willowbark";
    static final UnitOfMeasure UNIT_OF_MEASURE = new UnitOfMeasure();
    static final BigDecimal AMOUNT = new BigDecimal(3);
    static final Recipe RECIPE = new Recipe();

    IngredientToIngredientCommand converterUnderTest;
    Ingredient testDomainObject;

    @BeforeAll
    void setUp() {
        converterUnderTest = new IngredientToIngredientCommand();

        testDomainObject = new Ingredient();
        testDomainObject.setId(ID);
        testDomainObject.setDescription(DESCRIPTION);
        //testDomainObject.setUom(UNIT_OF_MEASURE); ??
        testDomainObject.setAmount(AMOUNT);
        //testDomainObject.setRecipe(RECIPE); ??
    }


    @Test
    void testNullReturnedIfPassed() {
        Ingredient givenIngredient;
        givenIngredient = null;
        Object whenObject = converterUnderTest.convert(givenIngredient);
        //then
        assertNull(whenObject);
    }

    @Test
    public void testEmptyObjectNotReturnedAsNull() {
        Ingredient givenIngredient = new Ingredient();
        Object whenObject = converterUnderTest.convert(givenIngredient);
        //then
        assertNotNull(whenObject);
    }

    @Test
    void testConvertDomainToCommand() {
        Ingredient givenTestDomainObject = testDomainObject;

        IngredientCommand whenCommand = converterUnderTest.convert(givenTestDomainObject);

        //then
        assertNotNull(whenCommand);
        assertEquals(ID, whenCommand.getId());
        assertEquals(DESCRIPTION, whenCommand.getDescription());
        //assertEquals(UNIT_OF_MEASURE, whenCommand.getUom()); ??
        assertEquals(AMOUNT, whenCommand.getAmount());
        //assertEquals(RECIPE, whenCommand.getRecipe()); ??


    }
}