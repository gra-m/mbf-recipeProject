package fun.madeby.mbfrecipeproject.bootstrap;

import fun.madeby.mbfrecipeproject.domain.*;
import fun.madeby.mbfrecipeproject.services.h2.CategoryServiceImpl;
import fun.madeby.mbfrecipeproject.services.h2.RecipeServiceImpl;
import fun.madeby.mbfrecipeproject.services.h2.UnitOfMeasureServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 05
 */

@Component
public class DataLoader implements CommandLineRunner {

    private final UnitOfMeasureServiceImpl UNIT_OF_MEASURE_SERVICE;
    private final CategoryServiceImpl CATEGORY_SERVICE;
    private final RecipeServiceImpl RECIPE_SERVICE;

    public DataLoader(
            UnitOfMeasureServiceImpl unitOfMeasureService,
            CategoryServiceImpl category_service,
            RecipeServiceImpl recipe_service) {
        UNIT_OF_MEASURE_SERVICE = unitOfMeasureService;
        CATEGORY_SERVICE = category_service;
        RECIPE_SERVICE = recipe_service;
    }

    @Override
    public void run(String... args) throws Exception {

        List<UnitOfMeasure> uOMSet = (List<UnitOfMeasure>) UNIT_OF_MEASURE_SERVICE.findAll();

        try {
            if (uOMSet.size() == 0)
                throw new RuntimeException("data.sql has not initialised");
                BootstrapData();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void BootstrapData() {
        System.out.println("Bootstrapping");
       //GUACOMOLE
        Recipe recipe = new Recipe();

        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        Ingredient ingredient3 = new Ingredient();
        Ingredient ingredient4 = new Ingredient();
        Ingredient ingredient5 = new Ingredient();
        Ingredient ingredient6 = new Ingredient();
        Ingredient ingredient7 = new Ingredient();
        Ingredient ingredient8 = new Ingredient();
        Ingredient ingredient9 = new Ingredient();
        ingredient1.setDescription("poblano peppers (approx 6\" long)");
        ingredient2.setDescription("serrano peppers (3-4\" long)");
        ingredient3.setDescription("jalapeño peppers (3-4\" long)");
        ingredient4.setDescription("ripe avocados");
        ingredient5.setDescription("lime juice");
        ingredient6.setDescription("diced red onion");
        ingredient7.setDescription("chopped cilantro");
        ingredient8.setDescription("ground cumin");
        ingredient9.setDescription("kosher salt");
        ingredient1.setAmount(new BigDecimal(2));
        ingredient2.setAmount(new BigDecimal(4));
        ingredient3.setAmount(new BigDecimal(2));
        ingredient4.setAmount(new BigDecimal(4));
        ingredient5.setAmount(new BigDecimal(1.5));
        ingredient6.setAmount(new BigDecimal(.25));
        ingredient7.setAmount(new BigDecimal(.25));
        ingredient8.setAmount(new BigDecimal(1));
        ingredient9.setAmount(new BigDecimal(.75));
        ingredient1.setUom(UNIT_OF_MEASURE_SERVICE.findUnitOfMeasurementByDescription("").get());
        ingredient2.setUom(UNIT_OF_MEASURE_SERVICE.findUnitOfMeasurementByDescription("").get());
        ingredient3.setUom(UNIT_OF_MEASURE_SERVICE.findUnitOfMeasurementByDescription("").get());
        ingredient4.setUom(UNIT_OF_MEASURE_SERVICE.findUnitOfMeasurementByDescription("").get());
        ingredient5.setUom(UNIT_OF_MEASURE_SERVICE.findUnitOfMeasurementByDescription("teaspoon").get());
        ingredient6.setUom(UNIT_OF_MEASURE_SERVICE.findUnitOfMeasurementByDescription("cup").get());
        ingredient7.setUom(UNIT_OF_MEASURE_SERVICE.findUnitOfMeasurementByDescription("cup").get());
        ingredient8.setUom(UNIT_OF_MEASURE_SERVICE.findUnitOfMeasurementByDescription("teaspoon").get());
        ingredient9.setUom(UNIT_OF_MEASURE_SERVICE.findUnitOfMeasurementByDescription("teaspoon").get());
        ingredient1.setRecipe(recipe);
        ingredient2.setRecipe(recipe);
        ingredient3.setRecipe(recipe);
        ingredient4.setRecipe(recipe);
        ingredient5.setRecipe(recipe);
        ingredient6.setRecipe(recipe);
        ingredient7.setRecipe(recipe);
        ingredient8.setRecipe(recipe);
        ingredient9.setRecipe(recipe);
        Set<Ingredient> guacamoleIngredientSet = new HashSet<>();
        guacamoleIngredientSet.add(ingredient1);
        guacamoleIngredientSet.add(ingredient2);
        guacamoleIngredientSet.add(ingredient3);
        guacamoleIngredientSet.add(ingredient4);
        guacamoleIngredientSet.add(ingredient5);
        guacamoleIngredientSet.add(ingredient6);
        guacamoleIngredientSet.add(ingredient7);
        guacamoleIngredientSet.add(ingredient8);
        guacamoleIngredientSet.add(ingredient9);

        Set<Category> guacamoleCategorySet = new HashSet<>();
        guacamoleCategorySet.add(CATEGORY_SERVICE.findCategoryByDescription("Mexican").get());

        Note note = new Note("If I could give away my most valuable tip on how to be successful in creating " +
                "dishes, that tip would be: Don’t be afraid to experiment with flavor pairings.\n" +
                "Case in point: this Spicy Three-Chile Guacamole went from “Oh, that’s nice,” to " +
                "“Where has this been all my life?!?!”—all it took was a combination of peppers and one humble spice.\n" +
                "While a three-chile guacamole may seem a bit fiery to your taste buds, rest assured, the creaminess of " +
                "the avocado base and the smokiness that the charring imparts tones down the heat. Flecks of crisp red " +
                "onion and the brightness of the cilantro round out the flavors in this dip to ensure your guacamole " +
                "has a kiss of heat that’s not too overwhelming.\n" +
                "Which Chiles Are Best?\n" +
                "A balance of mild and spicy chiles was essential in making this guacamole spicy, but still edible. " +
                "For me, there’s nothing worse than a dish being so spicy that it hurts you to eat it.\n" +
                "I used serrano, poblano, and jalapeño chiles. Each has a distinct flavor and varying levels of heat.\n" +
                "How a Pepper’s Heat Is Measured\n" +
                "When it comes to how hot a pepper is, the authoritative guide is the Scoville Heat Unit (SHU). " +
                "With zero being no heat at all and one billion being “I want to die,” the chart is a manual for picking " +
                "the right heat to use in your recipes.\n" +
                "The mildest pepper in this Spicy Three-Chile Guacamole is the poblano, with an average of 1,250 SHU. " +
                "Jalapeños and serranos are spicier, averaging 6,750 SHU and 10,000 SHU, respectively. (Here's a guide " +
                "to picking the best jalapeños for your spice level!)\n" +
                "TIP: If you’re just dipping your toes in the chile pool and want to start out mildly, I would recommend" +
                "using just one of the hotter chiles (either the jalapeño or the serrano) and substitute the other, hotter" +
                "chile with an Anaheim or ancho, in addition to the poblano.\n" +
                "These milder chiles have the lowest SHU, coming in at only 1,000. On the flip side, if you’re a chile" +
                "daredevil and want to ramp up the heat, increase the spice with chile de arbol (15,000 SHU) or pequin" +
                "chiles (40,000 SHU). You can add these fiery peppers to the milder poblano, but I wouldn’t recommend " +
                "pairing them with the jalapeños or serranos.\n",
                recipe);

        recipe.setTitle("Spicy Three-Chile Guacomole");

        recipe.setDescription("This is not too long, @Lob not touching H2?dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        recipe.setDirections("This is not too long either now..");
        recipe.setPrepTime(20);
        recipe.setCookTime(0);
        recipe.setServings(6);
        recipe.setSource("5");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_three_chile_guacamole/");

        recipe.setDifficulty(Difficulty.EASY);
        recipe.setIngredients(guacamoleIngredientSet);
        recipe.setCategories(guacamoleCategorySet);
        //recipe.setImage("10);
        recipe.setNote(note);

        RECIPE_SERVICE.saveRecipe(recipe);

    }
}


/*recipe.setDescription(
                "Take your guacamole game from “nice” to “not sharing” with a combination of serrano, poblano," +
                        " and jalapeño peppers! You'll be so happy after one bite of this spicy, smoky dip. " +
                        "Doing cartwheels in the middle of a party will seem like a perfectly reasonable thing to do.\n");
        recipe.setDirections("Preheat your grill or broiler:\n" +
                "Set your oven’s broiler or your grill’s searing compartment to high. Roast the peppers for 7-8 minutes until the skins are charred and blistered, turning the peppers as they begin to shake, and the seeds inside make snapping noises.\n" +
                "\n" +
                "Guacamole with Chili Peppers - peppers on the grill\n" +
                "Guacamole with Chili Peppers - charred peppers on the grill\n" +
                "Steam the peppers and remove the skins:\n" +
                "Once the peppers have been charred, remove them from the heat. Put the peppers into a shallow dish or bowl and cover them with a towel to steam.\n" +
                "\n" +
                "After the peppers steam, their skins are easy to remove. Use the back of your knife to scrape the charred, papery skins from the peppers, then discard.\n" +
                "\n" +
                "Dice the peppers:\n" +
                "Once the skins have been removed, slice off the stem end of each pepper. Cut the poblano and jalapeños in half lengthwise, then scrape away the white membrane and the seeds with the back side of your knife (this helps prevent scraping away valuable pepper flesh).\n" +
                "\n" +
                "Because serrano peppers are so skinny, rather than slice them in half, use the back of your knife blade and drag it down the length of the pepper to push the seeds out from the cut end.\n" +
                "\n" +
                "Dice the peppers into 1/4-inch pieces.\n" +
                "\n" +
                "Mash the avocados:\n" +
                "Cut the avocados in half and remove, then discard the pits. Scoop the avocado flesh into the mixing bowl and use a fork or potato masher to smash the avocado into a semi-chunky paste.\n" +
                "\n" +
                "Gently stir in the lime juice to help delay the browning of the avocado. Try to avoid stirring too much as it will break up the avocado.\n" +
                "\n" +
                "Finish the guacamole:\n" +
                "Fold in the chiles, red onion, cilantro, cumin, and salt until the ingredients are well incorporated. Taste the guacamole and adjust the salt to suit your preference.\n" +
                "\n" +
                "Allow the flavors to meld:\n" +
                "Press a piece of plastic wrap against the surface of the guacamole and cover your bowl with a lid. Refrigerate the guacamole for at least 15 minutes to allow the flavors of the chiles and the cumin to meld and deepen.\n" +
                "\n" +
                "Enjoy the guacamole within 3 days. Store leftovers covered in the refrigerator. If the guacamole browns, scrape off the brown layer and discard before serving.");
                        */
