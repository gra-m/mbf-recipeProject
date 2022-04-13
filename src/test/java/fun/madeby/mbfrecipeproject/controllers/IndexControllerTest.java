package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.domain.Recipe;
import fun.madeby.mbfrecipeproject.services.h2.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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

    @Mock
    Recipe recipeOne;

    @Mock
    Recipe recipeTwo;

    @BeforeEach
    void setUp() {
        recipeOne = new Recipe();
        recipeTwo = new Recipe(); // not currently used issue in course
        recipeTwo.setId(4L);
    }


    @Test
    public void givenControllerIsIndexRequestIsForwardSlashAndReturnIsIndex() throws Exception {

        //Given this MockMvc stand alone (unit test) controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        // and given this happy path, return is not empty list
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipeOne);
        when(recipeServiceImpl.findAll()).thenReturn(recipeArrayList);

        //When
        mockMvc.perform(MockMvcRequestBuilders.get("/")) //Then:
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));

    }




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
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipeOne);
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
    void callsToAddAttributeEqualOneWithCorrectListContentsAndSize() {

        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipeOne);
        when(recipeServiceImpl.findAll()).thenReturn(recipeArrayList);
        controller.getIndex(model);

        //Rather than anyList()/anyXXX ensure with:
        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // add attribute only called when list > 0 [happy] be sure to use matcher eq("string").
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        List<Recipe> listInController = argumentCaptor.getValue();
        assertEquals(1, listInController.size());

    }



}