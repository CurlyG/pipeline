package com.iRecipeNew.iRecipeNew.service;

import com.google.gson.Gson;
import com.iRecipeNew.iRecipeNew.domain.*;
import com.iRecipeNew.iRecipeNew.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@SpringBootTest
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService = new RecipeServiceImpl();



    private List<Recipe> existingRecipes;
    private Recipe recipe;
    private Recipe updatedRecipe;
    private String results;
    private String result;
    private String updatedResult;
    private Gson gson;

        @BeforeEach
        public void setMockOutput() {
                gson = new Gson();

            List<Comment> comments = new ArrayList<>();
            Category category = new Category();
            User user = new User();
            Cuisine cuisin = new Cuisine();

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
                recipe.setCategory(category);
                recipe.setUser(user);
                recipe.setCuisine(cuisin);
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
                updatedRecipe.setCategory(category);
                updatedRecipe.setUser(user);
                updatedRecipe.setCuisine(cuisin);
                updatedResult = gson.toJson(updatedRecipe);


            when(recipeRepository.findAll()).thenReturn(existingRecipes);
            when(recipeRepository.findById(5L)).thenReturn(Optional.of(recipe));
            when(recipeRepository.findById(4L)).thenReturn(Optional.of(updatedRecipe));
            when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
            when(recipeRepository.save(any(Recipe.class))).thenReturn(updatedRecipe);


        }

        @Test
        public void getAllRecipesTest(){

            assertEquals(1, recipeService.getAllRecipes().size());

        }

    @Test
    public void getRecipeByIdTest(){
        assertEquals(Optional.of(recipe), recipeService.getRecipeById(5L));


        Optional<Recipe> test = recipeService.getRecipeById(5L);
        assert(test.isPresent());

    }

    @Test
    public void updateRecipeByIdTest(){

        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
   }

    @Test
    public void updateRecipeByIdWhenCuisineIsNullTest(){

        updatedRecipe.setCuisine(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
    }

    @Test
    public void updateRecipeByIdWhenCategoryIsNullTest(){

        updatedRecipe.setCategory(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
    }

    @Test
    public void updateRecipeByIdWhenCommentIsNullTest(){

        updatedRecipe.setComments(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
    }

    @Test
    public void updateRecipeByIdWhenDifficultyIsNullTest(){

        updatedRecipe.setDifficulty(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
    }

    @Test
    public void updateRecipeByIdWhenCookTimeIsNullTest(){

        updatedRecipe.setCookTimeInMin(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
    }

    @Test
    public void updateRecipeByIdWhenDirectionsIsNullTest(){

        updatedRecipe.setDirections(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
    }

    @Test
    public void updateRecipeByIdWhenPrepTimeIsNullTest(){

        updatedRecipe.setPrepTimeInMin(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
    }

    @Test
    public void updateRecipeByIdWhenServingsIsNullTest(){

        updatedRecipe.setServings(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
    }

    @Test
    public void updateRecipeByIdWhenUserIsNullTest(){

        updatedRecipe.setUser(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getName().equals(updatedRecipe.getName()));
    }

    @Test
    public void updateRecipeByIdWhenNameIsNullTest(){

        updatedRecipe.setName(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getServings().equals(updatedRecipe.getServings()));
    }

    @Test
    public void updateRecipeByIdWhenIdIsNullTest(){

        updatedRecipe.setId(null);
        recipeService.getRecipeById(5L);
        recipeService.updateRecipeById(5L, updatedRecipe);

        assert(recipeService.getRecipeById(5L).get().getServings().equals(updatedRecipe.getServings()));
    }



    @Test
    public void deleteRecipeByIdTest(){
        given(recipeService.deleteRecipeById(5L)).willReturn(true);

            recipeService.deleteRecipeById(5L);
            verify(recipeRepository, times(1)).deleteById(5L);

    }

    @Test
    public void createRecipeTest(){

        doAnswer((arguments) -> {
            assertEquals(recipe, arguments.getArgument(0));
            return null;
        }).when(recipeRepository).save(any(Recipe.class));
        recipeService.createRecipe(recipe);

        verify(recipeRepository, times(1)).save(recipe);

    }



}
