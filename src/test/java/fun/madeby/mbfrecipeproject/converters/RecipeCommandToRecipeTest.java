package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.CategoryCommand;
import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import fun.madeby.mbfrecipeproject.commands.NoteCommand;
import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeCommandToRecipeTest {

        static final Long ID = 1L;
        static final String TITLE = "The name of the recipe";
        static final String DESCRIPTION = "A very long description don't begin unless you have 100 years to spare";
        static final Integer PREP_TIME = 10;
        static final Integer COOK_TIME = 10;
        static final Integer SERVINGS = 3;
        static final String SOURCE = "Gemima Bunnet";
        static final String URL = "https://www.thislink.com";
        static final String DIRECTIONS = "Really long directions stop! Now! Too interesting";
        static final Difficulty DIFFICULTY = Difficulty.MODERATE;
        static final Byte[] IMAGE_BYTE_ARRAY = new Byte[10];
        static final NoteCommand NOTE = new NoteCommand();
        static final Set<CategoryCommand> CATEGORY_SET = new HashSet<>();
        static final CategoryCommand CATEGORY_COMMAND_1 = new CategoryCommand();
        static final CategoryCommand CATEGORY_COMMAND_2 = new CategoryCommand();
        static final Set<IngredientCommand> INGREDIENT_SET= new HashSet<>();
        static final IngredientCommand INGREDIENT_COMMAND_1 = new IngredientCommand();
        static final IngredientCommand INGREDIENT_COMMAND_2 = new IngredientCommand();

        static final Long NOTE_ID = 45L;
        static final Long ING_1_ID = 75L;
        static final Long ING_2_ID = 78L;
        static final Long CAT_1_ID = 44L;
        static final Long CAT_2_ID = 45L;


    RecipeCommandToRecipe converterUnderTest;
        RecipeCommand testCommandObject;
        RecipeCommand testCommandObjectWithNullsAndEmptySets;

        @BeforeAll
        void setUp() {
            converterUnderTest = new RecipeCommandToRecipe(new NoteCommandToNote(),
                    new CategoryCommandToCategory(),
                    new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));

            testCommandObject = new RecipeCommand();
            testCommandObject.setId(ID);
            testCommandObject.setTitle(TITLE);
            testCommandObject.setDescription(DESCRIPTION);
            testCommandObject.setPrepTime(PREP_TIME);
            testCommandObject.setCookTime(COOK_TIME);
            testCommandObject.setServings(SERVINGS);
            testCommandObject.setSource(SOURCE);
            testCommandObject.setUrl(URL);
            testCommandObject.setDirections(DIRECTIONS);
            testCommandObject.setDifficulty(DIFFICULTY);
            testCommandObject.setImage(IMAGE_BYTE_ARRAY);
            NOTE.setId(NOTE_ID);
            testCommandObject.setNote(NOTE);
            CATEGORY_COMMAND_1.setId(CAT_1_ID);
            CATEGORY_COMMAND_2.setId(CAT_2_ID);
            CATEGORY_SET.add(CATEGORY_COMMAND_1);
            CATEGORY_SET.add(CATEGORY_COMMAND_2);
            testCommandObject.setCategories(CATEGORY_SET);
            INGREDIENT_COMMAND_1.setId(ING_1_ID);
            INGREDIENT_COMMAND_2.setId(ING_2_ID);
            INGREDIENT_SET.add(INGREDIENT_COMMAND_1);
            INGREDIENT_SET.add(INGREDIENT_COMMAND_2);
            testCommandObject.setIngredients(INGREDIENT_SET);

            testCommandObjectWithNullsAndEmptySets = new RecipeCommand();
            testCommandObjectWithNullsAndEmptySets.setId(ID);
            testCommandObjectWithNullsAndEmptySets.setTitle(TITLE);
            testCommandObjectWithNullsAndEmptySets.setDescription(DESCRIPTION);
            testCommandObjectWithNullsAndEmptySets.setPrepTime(PREP_TIME);
            testCommandObjectWithNullsAndEmptySets.setCookTime(COOK_TIME);
            testCommandObjectWithNullsAndEmptySets.setServings(SERVINGS);
            testCommandObjectWithNullsAndEmptySets.setSource(SOURCE);
            testCommandObjectWithNullsAndEmptySets.setUrl(URL);
            testCommandObjectWithNullsAndEmptySets.setDirections(DIRECTIONS);
            testCommandObjectWithNullsAndEmptySets.setDifficulty(DIFFICULTY);
            testCommandObjectWithNullsAndEmptySets.setImage(IMAGE_BYTE_ARRAY);
        }


        @Test
        void testNullReturnedIfPassed() {
            RecipeCommand givenRecipeCommand;
            givenRecipeCommand = null;
            Object whenObject = converterUnderTest.convert(givenRecipeCommand);
            //then
            assertNull(whenObject);
        }

        @Test
        public void testEmptyObjectNotReturnedAsNull() {
            RecipeCommand givenRecipeCommand = new RecipeCommand();
            Object whenObject = converterUnderTest.convert(givenRecipeCommand);
            //then
            assertNotNull(whenObject);
        }


        @Test
        void testConvertCommandToDomain() {
            RecipeCommand givenTestCommandObject = testCommandObject;

            Recipe whenDomain = converterUnderTest.convert(givenTestCommandObject);

            //then
            assertNotNull(whenDomain);
            assertEquals(ID, whenDomain.getId());
            assertEquals(TITLE, whenDomain.getTitle());
            assertEquals(DESCRIPTION, whenDomain.getDescription());
            assertEquals(PREP_TIME, whenDomain.getPrepTime());
            assertEquals(COOK_TIME, whenDomain.getCookTime());
            assertEquals(SERVINGS, whenDomain.getServings());
            assertEquals(SOURCE, whenDomain.getSource());
            assertEquals(URL, whenDomain.getUrl());
            assertEquals(DIRECTIONS, whenDomain.getDirections());
            assertEquals(DIFFICULTY, whenDomain.getDifficulty());
            assertEquals(2, whenDomain.getIngredients().size());
            assertEquals(IMAGE_BYTE_ARRAY, whenDomain.getImage());
            assertEquals(NOTE_ID, whenDomain.getNote().getId());
            assertEquals(2, whenDomain.getCategories().size());
        }

    @Test
    void testConvertCommandToDomainWithNullsAndEmptySets() {
        RecipeCommand givenTestCommandObject = testCommandObjectWithNullsAndEmptySets;
        Recipe whenDomain = converterUnderTest.convert(givenTestCommandObject);

        //then
        assertNotNull(whenDomain);
        assertNull(whenDomain.getNote());
        assertEquals(ID, whenDomain.getId());
        assertEquals(TITLE, whenDomain.getTitle());
        assertEquals(DESCRIPTION, whenDomain.getDescription());
        assertEquals(PREP_TIME, whenDomain.getPrepTime());
        assertEquals(COOK_TIME, whenDomain.getCookTime());
        assertEquals(SERVINGS, whenDomain.getServings());
        assertEquals(SOURCE, whenDomain.getSource());
        assertEquals(URL, whenDomain.getUrl());
        assertEquals(DIRECTIONS, whenDomain.getDirections());
        assertEquals(DIFFICULTY, whenDomain.getDifficulty());
        assertEquals(IMAGE_BYTE_ARRAY, whenDomain.getImage());
        assertEquals(0, whenDomain.getIngredients().size());
        assertEquals(0, whenDomain.getCategories().size());
    }
    }




