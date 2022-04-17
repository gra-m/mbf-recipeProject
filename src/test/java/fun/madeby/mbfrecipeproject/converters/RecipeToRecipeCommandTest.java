package fun.madeby.mbfrecipeproject.converters;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeToRecipeCommandTest {

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
    static final Set<Ingredient> INGREDIENT_SET= new HashSet<>();
    static final Byte[] IMAGE_BYTE_ARRAY = new Byte[10];
    static final Note NOTE = new Note();
    static final Set<Category> CATEGORY_SET = new HashSet<>();


    RecipeToRecipeCommand converterUnderTest;
    Recipe testDomainObject;

    @BeforeAll
    void setUp() {
    converterUnderTest = new RecipeToRecipeCommand();

    testDomainObject = new Recipe();
    testDomainObject.setId(ID);
    testDomainObject.setTitle(TITLE);
    testDomainObject.setDescription(DESCRIPTION);
    testDomainObject.setPrepTime(PREP_TIME);
    testDomainObject.setCookTime(COOK_TIME);
    testDomainObject.setServings(SERVINGS);
    testDomainObject.setSource(SOURCE);
    testDomainObject.setUrl(URL);
    testDomainObject.setDirections(DIRECTIONS);
    testDomainObject.setDifficulty(DIFFICULTY);
    testDomainObject.setIngredients(INGREDIENT_SET);
    testDomainObject.setImage(IMAGE_BYTE_ARRAY);
    testDomainObject.setNote(NOTE);
    testDomainObject.setCategories(CATEGORY_SET);

    }


    @Test
    void testNullReturnedIfPassed() {
        Recipe givenRecipe;
        givenRecipe = null;
        Object whenObject = converterUnderTest.convert(givenRecipe);
        //then
        assertNull(whenObject);
    }

    @Test
    public void testEmptyObjectNotReturnedAsNull() {
        Recipe givenRecipe = new Recipe();
        Object whenObject = converterUnderTest.convert(givenRecipe);
        //then
        assertNotNull(whenObject);
    }


    @Test
    void testConvertDomainToCommand() {
        Recipe givenTestDomainObject = testDomainObject;

        RecipeCommand whenCommand = converterUnderTest.convert(givenTestDomainObject);

        //then
        assertNotNull(whenCommand);
        assertEquals(ID, whenCommand.getId());
        assertEquals(TITLE, whenCommand.getTitle());
        assertEquals(DESCRIPTION, whenCommand.getDescription());
        assertEquals(PREP_TIME, whenCommand.getPrepTime());
        assertEquals(COOK_TIME, whenCommand.getCookTime());
        assertEquals(SERVINGS, whenCommand.getServings());
        assertEquals(SOURCE, whenCommand.getSource());
        assertEquals(URL, whenCommand.getUrl());
        assertEquals(DIRECTIONS, whenCommand.getDirections());
        assertEquals(DIFFICULTY, whenCommand.getDifficulty());
        assertEquals(INGREDIENT_SET, whenCommand.getIngredients());
        assertEquals(IMAGE_BYTE_ARRAY, whenCommand.getImage());
        assertEquals(NOTE, whenCommand.getNote());
        assertEquals(CATEGORY_SET, whenCommand.getCategories());
    }
}