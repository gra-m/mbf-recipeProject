package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.RecipeCommand;
import fun.madeby.mbfrecipeproject.converters.RecipeToRecipeCommand;
import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.repositories.RecipeRepository;
import fun.madeby.mbfrecipeproject.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class RecipeServiceImpl_IT {

    final String NEW_DESCRIPTION =  "asdf!!NewDescription ";
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    RecipeService recipeService;

    @Transactional
    @Test
    void testConvertingSaveWithNewDescription() throws Exception {
        //given
        Iterable<Recipe> recipeIterable = recipeRepository.findAll();
        Recipe firstRecipe = recipeIterable.iterator().next();
        RecipeCommand detachedRecipeCommand = recipeToRecipeCommand.convert(firstRecipe);
        //when
        detachedRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.savedRecipeCommand(detachedRecipeCommand);
        //then
        assertNotNull(savedRecipeCommand);
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(firstRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(firstRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
        assertEquals(firstRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
   }
}