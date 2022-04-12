package fun.madeby.mbfrecipeproject.services.h2;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Gra_m on 2022 04 05
 */


// Todo when playing with tests I saw that JT had this inheriting from a common RecipeService, I played with this notion but, today is about testing..
@Service
public class RecipeServiceImpl {
    private final RecipeRepository RECIPE_REPOSITORY;

    public RecipeServiceImpl(RecipeRepository recipe_repository) {
        RECIPE_REPOSITORY = recipe_repository;
    }

    public Optional<Recipe> findRecipeByTitle(String title) {
        return RECIPE_REPOSITORY.findRecipeByTitle(title);
    }

    public ArrayList<Recipe> findAll() {
        try {
            return (ArrayList<Recipe>) RECIPE_REPOSITORY.findAll();
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Added so there is some business logic to test.
    public Set<Recipe> getRecipes() {
        try {
            return new HashSet<>((Collection<? extends Recipe>) RECIPE_REPOSITORY.findAll());
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }


    public Recipe saveRecipe(Recipe recipe) {
        Recipe persistedRecipe = RECIPE_REPOSITORY.save(recipe);
        return persistedRecipe;
    }

    public void saveAll(List<Recipe> recipeList) {
        RECIPE_REPOSITORY.saveAll(recipeList);
    }
}
