package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.commands.UnitOfMeasureCommand;
import fun.madeby.mbfrecipeproject.domain.Ingredient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IngredientCommandToIngredientTest {
        static final Long ID = 1L;
        static final String DESCRIPTION = "willowbark";
        static final UnitOfMeasureCommand UNIT_OF_MEASURE = new UnitOfMeasureCommand();
        static final BigDecimal AMOUNT = new BigDecimal(3);
        static final RecipeCommand RECIPE = new RecipeCommand();

        IngredientCommandToIngredient converterUnderTest;
        IngredientCommand testCommandObject;

        @BeforeAll
        void setUp() {
            converterUnderTest = new IngredientCommandToIngredient();

            testCommandObject = new IngredientCommand();
            testCommandObject.setId(ID);
            testCommandObject.setDescription(DESCRIPTION);
            testCommandObject.setUom(UNIT_OF_MEASURE);
            testCommandObject.setAmount(AMOUNT);
            testCommandObject.setRecipe(RECIPE);
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
            assertEquals(ID, whenDomain.getId());
            assertEquals(DESCRIPTION, whenDomain.getDescription());
            //assertEquals(UNIT_OF_MEASURE, whenDomain.getUom()); ??
            assertEquals(AMOUNT, whenDomain.getAmount());
            //assertEquals(RECIPE, whenDomain.getRecipe()); ??


        }
    }
