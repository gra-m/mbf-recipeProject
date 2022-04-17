package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.CategoryCommand;
import fun.madeby.mbfrecipeproject.domain.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryCommandToCategoryTest {

    //Create constants each field to be tested
    static final Long ID = 1L;
    static final String DESCRIPTION = "description";
    // recipes left out for now

    //Bring in the converter to be tested and other converters necessary for test
    CategoryCommandToCategory  converterUnderTest;

    @BeforeAll
    void setUp() {
        converterUnderTest = new CategoryCommandToCategory();
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
        Object object = converterUnderTest.convert(new CategoryCommand());
        // then null NOT returned to object
        assertNotNull(object);
    }


    @Test
    void testConvertCommandToDomain() {
        CategoryCommand givenCommand = new CategoryCommand();
        givenCommand.setId(1L);
        givenCommand.setDescription("description");
        //givenCommand.setRecipes()


        Category whenCategory = converterUnderTest.convert(givenCommand);

        //then
        assertNotNull(whenCategory);
        assertEquals(ID, whenCategory.getId());
        assertEquals(DESCRIPTION, whenCategory.getDescription());

    }
}