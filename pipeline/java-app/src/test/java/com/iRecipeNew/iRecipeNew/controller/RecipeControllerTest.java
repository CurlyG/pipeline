package com.iRecipeNew.iRecipeNew.controller;

import com.google.gson.Gson;
import com.iRecipeNew.iRecipeNew.domain.Comment;
import com.iRecipeNew.iRecipeNew.domain.Difficulty;
import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.repository.RecipeRepository;
import com.iRecipeNew.iRecipeNew.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@AutoConfigureMockMvc
@SpringBootTest
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RecipeRepository recipeRepository;

    @MockBean
    private RecipeServiceImpl recipeService;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(recipeRepository).isNotNull();
        assertThat(recipeService).isNotNull();
    }


    private List<Recipe> existingRecipes;
    private Recipe recipe;
    private Recipe updatedRecipe;
    private String results;
    private String result;
    private String updatedResult;
    private Gson gson;

    @BeforeEach
    public void setUpBeforeClass() {

        List<Comment> comments = new ArrayList<>();
      //  Category category = new Category();
     //   User user = new User();
      //  Cuisine cuisin = new Cuisine();
        gson = new Gson();

        existingRecipes = new ArrayList<>();
        recipe = new Recipe();
        recipe.setName("Spaghetti");
        recipe.setId(5L);
        recipe.setCookTimeInMin(20);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setDirections("Place into the oven");
        recipe.setPrepTimeInMin(15);
        recipe.setServings(3);
        recipe.setComments(comments);
      //  recipe.setCategory(category);
       // recipe.setUser(user);
      //  recipe.setCuisine(cuisin);
        existingRecipes.add(recipe);
        results = gson.toJson(existingRecipes);
        result = gson.toJson(recipe);

        updatedRecipe = new Recipe();
        updatedRecipe.setName("Macaroni");
        updatedRecipe.setId(4L);
        updatedRecipe.setCookTimeInMin(40);
        updatedRecipe.setDifficulty(Difficulty.HARD);
        updatedRecipe.setDirections("Place into the oven");
        updatedRecipe.setPrepTimeInMin(25);
        updatedRecipe.setServings(6);
        updatedRecipe.setComments(comments);
     //   updatedRecipe.setCategory(category);
    //    updatedRecipe.setUser(user);
     //   updatedRecipe.setCuisine(cuisin);
        updatedResult = gson.toJson(updatedRecipe);


   }

    @Test
    public void getRecipesTest() throws Exception {
       given(recipeService.getAllRecipes()).willReturn(existingRecipes);

        mockMvc.perform(
                get("http://localhost:8080/api/v1/recipes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(results));

    }


    @Test
    public void getRecipesByIdTest() throws Exception {
    given(recipeService.getRecipeById(5L)).willReturn(Optional.of(recipe));

    mockMvc.perform(get("http://localhost:8080/api/v1/recipes/5").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(result));

    }

    @Test
    public void getRecipesByIncorrectIdTest() throws Exception {
        given(recipeService.getRecipeById(5L)).willReturn(Optional.of(recipe));

        mockMvc.perform(get("http://localhost:8080/api/v1/recipes/4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    @Test
    public void createRecipesTest() throws Exception{

        mockMvc.perform(post("http://localhost:8080/api/v1/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andExpect(status().isCreated());

    }


    @Test
    public void deleteRecipeByIdTest() throws Exception {
        given(recipeService.deleteRecipeById(5L)).willReturn(true);
        mockMvc.perform(delete("http://localhost:8080/api/v1/recipes/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void deleteRecipeByIncorrectIdTest() throws Exception {
        given(recipeService.deleteRecipeById(11L)).willReturn(false);
        mockMvc.perform(delete("http://localhost:8080/api/v1/recipes/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void putRecipeByIdTest() throws Exception {
        given(recipeService.getRecipeById(5L)).willReturn(Optional.of(recipe));


        mockMvc.perform(put("http://localhost:8080/api/v1/recipes/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedResult))
                .andExpect(content().string("Recipe successfully updated"));

    }

    @Test
    public void putRecipeByIncorrectIdTest() throws Exception {
        given(recipeService.getRecipeById(55L)).willReturn(Optional.of(recipe));

        mockMvc.perform(put("http://localhost:8080/api/v1/recipes/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedResult))
                .andExpect(status().isNotFound());

    }








}
