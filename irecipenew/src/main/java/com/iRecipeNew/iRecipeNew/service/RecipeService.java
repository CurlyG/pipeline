package com.iRecipeNew.iRecipeNew.service;

import com.iRecipeNew.iRecipeNew.domain.Recipe;

import java.util.List;
import java.util.Optional;


public interface RecipeService {

    List<Recipe> getAllRecipes();
    Optional<Recipe> getRecipeById(Long id);

    Recipe updateRecipeById(Long id, Recipe recipe);
    boolean deleteRecipeById(Long id);
    void createRecipe(Recipe recipe);


}
