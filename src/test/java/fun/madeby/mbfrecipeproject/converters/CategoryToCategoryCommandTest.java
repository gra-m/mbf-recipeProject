package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.CategoryCommand;
import fun.madeby.mbfrecipeproject.domain.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryToCategoryCommandTest {

    static final Long ID = 1L;
    static final String DESCRIPTION = "description";
    // recipes left out for now

    CategoryToCategoryCommand converterUnderTest;

    @BeforeAll
    void setUp() {
        converterUnderTest = new CategoryToCategoryCommand();
    }

    @Test
    void testNullReturnedIfPassed() {
        Category givenCategory;
        givenCategory = null;
        Object whenObject = converterUnderTest.convert(givenCategory);
        //then
        assertNull(whenObject);
    }

    @Test
    public void testEmptyObjectNotReturnedAsNull() {
        Category givenCategory = new Category();
        Object whenObject = converterUnderTest.convert(givenCategory);
        //then
        assertNotNull(whenObject);
    }



    @Test
    void testConvertDomainToCommand() {
        Category givenCategory = new Category();
        givenCategory.setId(1L);
        givenCategory.setDescription("description");
        //givenCategory.setRecipes(); leave for now

        CategoryCommand whenCommand = converterUnderTest.convert(givenCategory);

        //then
        assertNotNull(whenCommand);
        assertEquals(ID, whenCommand.getId());
        assertEquals(DESCRIPTION, whenCommand.getDescription());

    }
}