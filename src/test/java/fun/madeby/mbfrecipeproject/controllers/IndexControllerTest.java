package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.services.h2.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.List;

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
    void getIndexUnhappy() {

        //unhappy path == returned list was empty
        String returnedViewName = controller.getIndex(model);
        assertEquals(null, returnedViewName);
    }


    @Test
    void getIndexHappy() {
        String expectedViewName = "index";

        //happy path == returned list has contents
        Recipe recipe = new Recipe();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipe);
        when(recipeServiceImpl.findAll()).thenReturn(recipeArrayList);
        String returnedViewName = controller.getIndex(model);
        assertEquals(expectedViewName, returnedViewName);
    }

    @Test
    void callsToRecipeServiceImplEqualOne() {

        String returnedViewName = controller.getIndex(model);
        verify(recipeServiceImpl, times(1)).findAll();

    }

    @Test
    void callsToAddAttributeEqualOneWithCorrectListContents() {

        Recipe recipe = new Recipe();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipe);
        when(recipeServiceImpl.findAll()).thenReturn(recipeArrayList);
        controller.getIndex(model);

        //Rather than anyList()/anyXXX ensure with:
        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // add attribute only called when list > 0 [happy] be sure to use matcher eq("string").
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

    }



}