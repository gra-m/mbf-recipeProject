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
    static final Byte[] IMAGE_BYTE_ARRAY = new Byte[10];
    static final Note NOTE = new Note();
    static final Set<Ingredient> INGREDIENT_SET= new HashSet<>();
    static final Set<Category> CATEGORY_SET = new HashSet<>();
    static final Long ING_1_ID = 111L;
    static final Long ING_2_ID = 112L;
    static final Long CAT_1_ID = 140L;
    static final Long CAT_2_ID = 141L;
    static final Long NOTE_ID = 1L;

    RecipeToRecipeCommand converterUnderTest;
    Recipe testDomainObject;
    Recipe testDomainObjectWithNullsAndEmptySets;

    @BeforeAll
    void setUp() {
    converterUnderTest = new RecipeToRecipeCommand(new NoteToNoteCommand(),
            new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
            new CategoryToCategoryCommand());

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
    testDomainObject.setImage(IMAGE_BYTE_ARRAY);
    NOTE.setId(NOTE_ID);
    testDomainObject.setNote(NOTE);
    Ingredient ingredient1 = new Ingredient();
    Ingredient ingredient2 = new Ingredient();
    ingredient1.setId(ING_1_ID);
    ingredient2.setId(ING_2_ID);
    INGREDIENT_SET.add(ingredient1);
    INGREDIENT_SET.add(ingredient2);
    testDomainObject.setIngredients(INGREDIENT_SET);
    Category category1 = new Category();
    Category category2 = new Category();
        category1.setId(CAT_1_ID);
        category2.setId(CAT_2_ID);
        CATEGORY_SET.add(category1);
        CATEGORY_SET.add(category2);
    testDomainObject.setCategories(CATEGORY_SET);


        testDomainObjectWithNullsAndEmptySets = new Recipe();
        testDomainObjectWithNullsAndEmptySets.setId(ID);
        testDomainObjectWithNullsAndEmptySets.setTitle(TITLE);
        testDomainObjectWithNullsAndEmptySets.setDescription(DESCRIPTION);
        testDomainObjectWithNullsAndEmptySets.setPrepTime(PREP_TIME);
        testDomainObjectWithNullsAndEmptySets.setCookTime(COOK_TIME);
        testDomainObjectWithNullsAndEmptySets.setServings(SERVINGS);
        testDomainObjectWithNullsAndEmptySets.setSource(SOURCE);
        testDomainObjectWithNullsAndEmptySets.setUrl(URL);
        testDomainObjectWithNullsAndEmptySets.setDirections(DIRECTIONS);
        testDomainObjectWithNullsAndEmptySets.setDifficulty(DIFFICULTY);
        testDomainObjectWithNullsAndEmptySets.setImage(IMAGE_BYTE_ARRAY);

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
        assertEquals(2, whenCommand.getIngredients().size());
        assertEquals(IMAGE_BYTE_ARRAY, whenCommand.getImage());
        assertEquals(NOTE.getId(), whenCommand.getNote().getId());
        assertEquals(2, whenCommand.getCategories().size());
    }

    @Test
    void testConvertCommandToDomainWithNullsAndEmptySets() {
        Recipe givenTestCommandObject = testDomainObjectWithNullsAndEmptySets;
        RecipeCommand whenCommand = converterUnderTest.convert(givenTestCommandObject);

        //then
        assertNotNull(whenCommand);
        assertNull(whenCommand.getNote());
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
        assertEquals(IMAGE_BYTE_ARRAY, whenCommand.getImage());
        assertEquals(0, whenCommand.getIngredients().size());
        assertEquals(0, whenCommand.getCategories().size());
    }




}