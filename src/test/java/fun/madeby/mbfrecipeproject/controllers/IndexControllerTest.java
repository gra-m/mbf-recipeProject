package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.services.h2.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @InjectMocks
    IndexController controller;

    @Mock
    Model model;

    @Mock
    RecipeServiceImpl recipeServiceImpl;


    // This test is ignoring Single responsibility, it's a bit of a sandbox.
    @Test
    void getIndex() {

        //unhappy path
        String expectedViewName = null;
        String returnedViewName = controller.getIndex(model);
        assertEquals(expectedViewName, returnedViewName);

        //happy path
        Recipe recipe = new Recipe();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipe);
        when(recipeServiceImpl.findAll()).thenReturn(recipeArrayList);
        expectedViewName = "index";
        returnedViewName = controller.getIndex(model);
        assertEquals(expectedViewName, returnedViewName);


        verify(recipeServiceImpl, times(2)).findAll();
        // add attribute only called when list > 0 be sure to use matcher eq("string").
        verify(model, times(1)).addAttribute(eq("recipes"), anyList());

    }
}