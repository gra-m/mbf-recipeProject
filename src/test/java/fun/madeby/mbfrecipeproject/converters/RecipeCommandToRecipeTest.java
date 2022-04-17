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
        static final Set<IngredientCommand> INGREDIENT_SET= new HashSet<>();
        static final Byte[] IMAGE_BYTE_ARRAY = new Byte[10];
        static final NoteCommand NOTE = new NoteCommand();
        static final Set<CategoryCommand> CATEGORY_SET = new HashSet<>();


        RecipeCommandToRecipe converterUnderTest;
        RecipeCommand testCommandObject;

        @BeforeAll
        void setUp() {
            converterUnderTest = new RecipeCommandToRecipe();

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
            testCommandObject.setIngredients(INGREDIENT_SET);
            testCommandObject.setImage(IMAGE_BYTE_ARRAY);
            testCommandObject.setNote(NOTE);
            testCommandObject.setCategories(CATEGORY_SET);

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
            //assertEquals(INGREDIENT_SET, whenDomain.getIngredients()); ??
            assertEquals(IMAGE_BYTE_ARRAY, whenDomain.getImage());
            //assertEquals(NOTE, whenDomain.getNote()); ??
            //assertEquals(CATEGORY_SET, whenDomain.getCategories()); ??
        }
    }




