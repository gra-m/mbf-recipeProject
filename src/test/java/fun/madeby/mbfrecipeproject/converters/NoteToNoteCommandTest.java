package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.NoteCommand;
import fun.madeby.mbfrecipeproject.domain.Note;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NoteToNoteCommandTest {

    static final Long ID = 1L;
    static final String RECIPE_NOTE = "this is a rather long note that is not worthy of reading yet you if you got this far you did.";

    NoteToNoteCommand converterUnderTest;
    Note testDomainObject;

    @BeforeAll
    void setUp() {
        converterUnderTest = new NoteToNoteCommand();

        testDomainObject = new Note();
        testDomainObject.setId(ID);
        testDomainObject.setRecipeNote(RECIPE_NOTE);
        //testDomainObject.setRecipe(RECIPE); Recipe is unknown to Note
    }


    @Test
    void testNullReturnedIfPassed() {
        Note givenNote;
        givenNote = null;
        Object whenObject = converterUnderTest.convert(givenNote);
        //then
        assertNull(whenObject);
    }

    @Test
    public void testEmptyObjectNotReturnedAsNull() {
        Note givenNote = new Note();
        Object whenObject = converterUnderTest.convert(givenNote);
        //then
        assertNotNull(whenObject);
    }

    @Test
    void testConvertDomainToCommand() {
        Note givenTestDomainObject = testDomainObject;

        NoteCommand whenCommand = converterUnderTest.convert(givenTestDomainObject);

        //then
        assertNotNull(whenCommand);
        assertEquals(ID, whenCommand.getId());
        assertEquals(RECIPE_NOTE, whenCommand.getRecipeNote());
    }
}