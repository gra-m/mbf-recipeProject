package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.NoteCommand;
import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.domain.Note;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NoteCommandToNoteTest {

        static final Long ID = 1L;
        static final String RECIPE_NOTE = "this is a rather long note that is not worthy of reading yet you if you got this far you did.";
        static final RecipeCommand RECIPE = new RecipeCommand();

        NoteCommandToNote converterUnderTest;
        NoteCommand testCommandObject;

        @BeforeAll
        void setUp() {
            converterUnderTest = new NoteCommandToNote();

            testCommandObject = new NoteCommand();
            testCommandObject.setId(ID);
            testCommandObject.setRecipeNote(RECIPE_NOTE);
            testCommandObject.setRecipe(RECIPE);
        }


        @Test
        void testNullReturnedIfPassed() {
            NoteCommand givenNote;
            givenNote = null;
            Object whenObject = converterUnderTest.convert(givenNote);
            //then
            assertNull(whenObject);
        }

        @Test
        public void testEmptyObjectNotReturnedAsNull() {
            NoteCommand givenNote = new NoteCommand();
            Object whenObject = converterUnderTest.convert(givenNote);
            //then
            assertNotNull(whenObject);
        }

        @Test
        void testConvertCommandToDomain() {
            NoteCommand givenTestCommandObject = testCommandObject;

            Note whenDomain = converterUnderTest.convert(givenTestCommandObject);

            //then
            assertNotNull(whenDomain);
            assertEquals(ID, whenDomain.getId());
            assertEquals(RECIPE_NOTE, whenDomain.getRecipeNote());
            //assertEquals(RECIPE, whenDomain.getRecipe()); ??
        }
    }