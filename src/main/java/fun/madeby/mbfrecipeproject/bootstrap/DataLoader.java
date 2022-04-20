package fun.madeby.mbfrecipeproject.bootstrap;

import fun.madeby.mbfrecipeproject.domain.*;
import fun.madeby.mbfrecipeproject.services.CategoryServiceImpl;
import fun.madeby.mbfrecipeproject.services.RecipeServiceImpl;
import fun.madeby.mbfrecipeproject.services.UnitOfMeasureServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 05
 */
@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private List<Recipe> recipes = new ArrayList<>(2);
    private final UnitOfMeasureServiceImpl UNIT_OF_MEASURE_SERVICE_IMPL;
    private final CategoryServiceImpl CATEGORY_SERVICE;
    private final RecipeServiceImpl RECIPE_SERVICE;

    public DataLoader(
            UnitOfMeasureServiceImpl unitOfMeasureServiceImpl,
            CategoryServiceImpl category_service,
            RecipeServiceImpl recipe_service) {
        UNIT_OF_MEASURE_SERVICE_IMPL = unitOfMeasureServiceImpl;
        CATEGORY_SERVICE = category_service;
        RECIPE_SERVICE = recipe_service;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("onApplicationEvent: ContextRefreshedEvent Triggered");
        List<UnitOfMeasure> uOMSet = (List<UnitOfMeasure>) UNIT_OF_MEASURE_SERVICE_IMPL.findAll();
        try {
            if (uOMSet.size() == 0)
                throw new RuntimeException("data.sql has not initialised");
            RECIPE_SERVICE.saveAll(BootstrapData());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


    private List<Recipe> BootstrapData() {
        //GUACOMOLE Original
        Recipe recipe = new Recipe();
        System.out.println("Reassuring message.. Bootstrapping. Prepare for imminent fail..ur..");
        log.debug("01 Bootstrapping first recipe instantiated");
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
        ingredient5.setAmount(new BigDecimal("1.5"));
        ingredient6.setAmount(new BigDecimal(".25"));
        ingredient7.setAmount(new BigDecimal(".25"));
        ingredient8.setAmount(new BigDecimal(1));
        ingredient9.setAmount(new BigDecimal(".75"));
        ingredient1.setUom(UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("").get());
        ingredient2.setUom(UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("").get());
        ingredient3.setUom(UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("").get());
        ingredient4.setUom(UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("").get());
        ingredient5.setUom(UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("teaspoon").get());
        ingredient6.setUom(UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("cup").get());
        ingredient7.setUom(UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("cup").get());
        ingredient8.setUom(UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("teaspoon").get());
        ingredient9.setUom(UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("teaspoon").get());
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

        Note note = new Note(getRecipe1Note(), recipe);
        recipe.setTitle("Spicy Three-Chile Guacomole");
        recipe.setDescription(
                "Take your guacamole game from “nice” to “not sharing” with a combination of serrano, poblano," +
                        " and jalapeño peppers! You'll be so happy after one bite of this spicy, smoky dip. " +
                        "Doing cartwheels in the middle of a party will seem like a perfectly reasonable thing to do.\n");
        recipe.setDirections(getRecipe1Directions());
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
       ///////////////////////////////////////////////////////////////////////////////////
        Recipe recipe2 = new Recipe();
        //Lime Marinade
        Ingredient ingredientA = new Ingredient("lime juice", new BigDecimal(".25"), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("cup").get(), recipe2);
        Ingredient ingredientB = new Ingredient("salt", new BigDecimal(".25"), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("teaspoon").get(), recipe2);
        Ingredient ingredientC = new Ingredient("black pepper", new BigDecimal(1), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("pinch").get(), recipe2);
        Ingredient ingredientD = new Ingredient("olive oil", new BigDecimal(".33"), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("tablespoon").get(), recipe2);
        Ingredient ingredientE = new Ingredient("honey", new BigDecimal(4), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("teaspoon").get(), recipe2);
        Ingredient ingredientF = new Ingredient("coriander/cilantro", new BigDecimal(".5"), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("cup").get(), recipe2);

        //Chicken
        Ingredient ingredientG = new Ingredient("chicken legs (thighs and drumsticks attached)", new BigDecimal(4), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("full").get(), recipe2);
        Ingredient ingredientH = new Ingredient("chicken breasts", new BigDecimal(2), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("full").get(), recipe2);
        Ingredient ingredientI = new Ingredient("salt", new BigDecimal(1), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("teaspoon").get(), recipe2);
        Ingredient ingredientJ = new Ingredient("black pepper", new BigDecimal(".25"), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("teaspoon").get(), recipe2);
        Ingredient ingredientK = new Ingredient("of dukkah, divided", new BigDecimal(5), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("tablespoon").get(), recipe2);
        Ingredient ingredientL = new Ingredient("Oil", new BigDecimal(".01"), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("").get(), recipe2);
        Ingredient ingredientM = new Ingredient("pomegranate seeds, for garnish (optional)", new BigDecimal(".25"), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("cup").get(), recipe2);
        Ingredient ingredientN = new Ingredient("small pita, for serving", new BigDecimal(6), UNIT_OF_MEASURE_SERVICE_IMPL.findUnitOfMeasurementByDescription("round").get(), recipe2);
        Set<Ingredient> limeChickIngredientSet = new HashSet<>();
        limeChickIngredientSet.add(ingredientA);
        limeChickIngredientSet.add(ingredientB);
        limeChickIngredientSet.add(ingredientC);
        limeChickIngredientSet.add(ingredientD);
        limeChickIngredientSet.add(ingredientE);
        limeChickIngredientSet.add(ingredientF);
        limeChickIngredientSet.add(ingredientG);
        limeChickIngredientSet.add(ingredientH);
        limeChickIngredientSet.add(ingredientI);
        limeChickIngredientSet.add(ingredientJ);
        limeChickIngredientSet.add(ingredientK);
        limeChickIngredientSet.add(ingredientL);
        limeChickIngredientSet.add(ingredientM);
        limeChickIngredientSet.add(ingredientN);

        Set<Category> limeChickCategories = new HashSet<>();
        limeChickCategories.add(CATEGORY_SERVICE.findCategoryByDescription("Egyptian").get());
        limeChickCategories.add(CATEGORY_SERVICE.findCategoryByDescription("Middle-East").get());

        Note note2 = new Note(getRecipe2Note(), recipe);
        recipe2.setTitle("Grilled Dukkah-Lime Crusted Chicken");
        recipe2.setDescription(getRecipe2Description());
        recipe2.setDirections(getRecipe2Directions());
        recipe2.setPrepTime(110);
        recipe2.setCookTime(30);
        recipe2.setServings(6);
        recipe2.setSource("5");
        recipe2.setUrl("https://www.simplyrecipes.com/recipes/grilled_dukkah_crusted_chicken_with_lemon_hummus/");
        recipe2.setDifficulty(Difficulty.MODERATE);
        recipe2.setIngredients(limeChickIngredientSet);
        recipe2.setCategories(limeChickCategories);
        //recipe.setImage("10);
        recipe2.setNote(note2);
        ///////////////



        recipes.add(recipe);
        recipes.add(recipe2);
        System.out.println("......e");
        log.debug("02 Bootstrap: Recipes have just been added to the recipes ArrayList");
        return recipes;
    }

    private String getRecipe2Description() {
        log.debug("getRecipe2Description");
        return "Try this Dukkah-Crusted Grilled Chicken! A new spice blend can really be a game changer when dinner time gets boring. Dukkah is a blend of coriander, cumin, and fennel seeds and nuts, and it pairs beautifully with grilled chicken.";
    }

    private String getRecipe2Note() {
        log.debug("getRecipe2Note");
        return "So What Is Dukkah, Exactly?\n" +
                "The word dukkah is derived from the Arabic word for “pound,” as in “to smash.” The ingredients are broken up into small pieces in a mortar to make a crumbly, spicy mix.\n" +
                "\n" +
                "This jumble of spices often includes coriander, cumin, and fennel seeds, which are coarsely crushed and stirred into finely chopped toasted pistachios, hazelnuts, and/or almonds. Sesame seeds, dried mint and either Marash or Aleppo pepper often round out the mix.\n" +
                "\n" +
                "There are no hard and fast rules about dukkah’s ingredients, since each cook favors their own version.\n" +
                "\n" +
                "Know Your Spices: Dukkah\n" +
                "READ MORE:\n" +
                "\n" +
                "Sally Vargas\n" +
                "Where Can I Buy Dukkah?\n" +
                "You can make your own dukkah blend based on the spices mentioned above, or you can buy it. Look for it at Trader Joe’s or order it from few mail order spice companies.\n" +
                "\n" +
                "You might also be able to find it at the grocery store, as McCormick’s makes a blend that contains all the key ingredients, too. It’s worth hunting down.\n" +
                "\n" +
                "What Are Some Ways to Use Dukkah?\n" +
                "Once you discover it, you will want to sprinkle it on everything from sautéed fish fillets to vegetables (imagine it on a pan of roasted cauliflower!), on top of a savory dish of eggs, or simply stirred into yogurt for tangy dip. It is equally good in all of those applications.\n" +
                "\n" +
                "After I encountered dukkah as an appetizer, it made me want to try it in other ways. Chicken popped into my mind as a good contender for grilling: it is quick to prepare with a big flavor payoff, and can be served with toasted pita, hummus, or storebought grape leaves. Any number of Middle Eastern small plates or other salads (Greek salad or fattoush, for example) can go with it to change up the usual grilling routine.\n" +
                "\n" +
                "Grilled Chicken with Dukkah let the chicken rest\n" +
                "Sally Vargas\n" +
                "Make This Grilled Chicken With Dukkah!\n" +
                "This is recipe for grilled chicken with dukkah is an easy recipe for any day of the week. Make a lime marinade and set aside some of it to use as a vinaigrette dress the salad. Use the remainder to marinate the chicken. Sprinkle the chicken with dukkah, marinate and then grill it when you are ready for dinner. The dukkah mingles with the vinaigrette and all that good flavor goes into the chicken.\n" +
                "\n" +
                "Serve the chicken with greens lightly tossed in the marinade you set aside earlier. For extra panache, spread a little hummus on the plate and serve it all with pita bread warmed on the grill.\n" +
                "\n" +
                "On a weeknight, I might buy my favorite hummus at the grocery store to save time. I do love to make my own though, and the one I made for this recipe is loaded with lemony tartness. Sometimes I skip the garlic because raw garlic can easily overpower the hummus as it sits. I just love the lemon all by itself!\n" +
                "\n" +
                "The combination of tart and creamy hummus, crisp greens, and spicy, crunchy dukkah makes a splendid panoply of textures and tastes—in other words, a feast without too much fuss.\n" +
                "\n" +
                "Turn This Into a Make-Ahead Meal\n" +
                "You can make the marinade and hummus up to 3 days ahead, and marinate the chicken for several hours or overnight. Then it’s only a matter of grilling the chicken and adorning it with all the goodies at serving time.";
    }

    private String getRecipe2Directions() {
        log.debug("getRecipe2Directions");
        return "Make the vinaigrette:\n" +
                "In a blender, puree the lime juice, salt, pepper, oil, honey, and cilantro leaves until smooth. Set aside 3 tablespoons to dress the salad.\n" +
                "\n" +
                "Marinate the chicken:\n" +
                "Sprinkle the chicken on both sides with the salt, pepper, and 2 tablespoons of the dukkah. Press the dukkah into the chicken. Reserve the rest of the dukkah for sprinkling on the cooked chicken.\n" +
                "\n" +
                "In a bowl, toss the chicken with the remaining marinade. Cover and refrigerate for 1 hour or up to overnight.\n" +
                "\n" +
                "Chicken on the Grill with Dukkah Spice marinate the chicken\n" +
                "Make the hummus:\n" +
                "In a small bowl, stir together the garlic, salt, and lemon juice. Let it sit for 10 minutes (this takes the raw edge off the garlic).\n" +
                "\n" +
                "In a food processor, puree the garlic and lemon mixture with the tahini until smooth. Add the water and process again. Add the chickpeas, cumin, and oil and puree again for at least a minute, to obtain a smooth texture.\n" +
                "\n" +
                "Taste and add more salt or lemon juice, if you like. Thin with water if necessary to create a spreading consistency.\n" +
                "\n" +
                "Egyptian Chicken with Dukkah Recipe add the chickpeas and spices\n" +
                "Grilled Chicken with Dukkah Egyptian make the hummus\n" +
                "Prepare the grill:\n" +
                "Build a two-level fire by mounding the coals on one side of the grill, and leave the second side empty. When the coals are hot, with a pair of tongs, dip a folded square of paper towel in oil and generously oil the grates.\n" +
                "\n" +
                "If you are using a gas grill, heat one side to high heat and the other to medium-low heat.\n" +
                "\n" +
                "Grill the chicken:\n" +
                "Remove the chicken from the marinade. Place the pieces on the hot side of the grill with the skin sides against the grates. Cook them for a minute or two—just long enough for the skin to show the grill marks. Turn the chicken over and sear briefly on the other side. Exact timing will depend on the heat of your particular grill.\n" +
                "\n" +
                "Move the chicken to the cooler side of the grill. Continue to cook with the skin side up, turning once or twice, until the temperature in thickest part of the chicken registers 165oF.\n" +
                "\n" +
                "Transfer to a platter and let rest for 10 minutes. While it is resting, warm the pita on the grill for a few minutes.\n" +
                "\n" +
                "Grilled Chicken with Dukkah let the chicken rest\n" +
                "Toss the greens with the dressing:\n" +
                "In a bowl, toss the arugula, mint, and cucumber with the reserved 3 tablespoons of the dressing.\n" +
                "\n" +
                "Serve the chicken:\n" +
                "On a platter or on individual plates, spread a spoonful of hummus on one side. Arrange the chicken and greens on the platter. Sprinkle the chicken with more dukkah and the pomegranate seeds, and serve.";
    }

    private String getRecipe1Note() {
        log.debug("getRecipe1Note");
        return "If I could give away my most valuable tip on how to be successful in creating " +
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
                "pairing them with the jalapeños or serranos.\n";
    }

    private String getRecipe1Directions() {
        log.debug("getRecipe1Directions");
        return "Preheat your grill or broiler:\n" +
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
                "Fold in the chillies, red onion, cilantro, cumin, and salt until the ingredients are well incorporated. Taste the guacamole and adjust the salt to suit your preference.\n" +
                "\n" +
                "Allow the flavors to meld:\n" +
                "Press a piece of plastic wrap against the surface of the guacamole and cover your bowl with a lid. Refrigerate the guacamole for at least 15 minutes to allow the flavors of the chiles and the cumin to meld and deepen.\n" +
                "\n" +
                "Enjoy the guacamole within 3 days. Store leftovers covered in the refrigerator. If the guacamole browns, scrape off the brown layer and discard before serving.";
    }
}