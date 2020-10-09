package com.iRecipeNew.iRecipeNew.service;


import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();

    }


    @Override
    public Optional<Recipe> getRecipeById(Long id) {

        return recipeRepository.findById(id);
    }

    @Override
    public Recipe updateRecipeById(Long id, Recipe recipe) {

        Recipe updatedRecipe = getRecipeById(id).get();


        if(recipe.getId()!=null) {
            updatedRecipe.setId(recipe.getId());

        }if(recipe.getCategory()!=null){
            updatedRecipe.setCategory(recipe.getCategory());
        }if(recipe.getComments()!=null){
            updatedRecipe.setComments(recipe.getComments());
        }if(recipe.getCookTimeInMin()!=null){
            updatedRecipe.setCookTimeInMin(recipe.getCookTimeInMin());
        }if(recipe.getCuisine()!=null){
            updatedRecipe.setCuisine(recipe.getCuisine());
        }if(recipe.getDifficulty()!=null){
            updatedRecipe.setDifficulty(recipe.getDifficulty());
        }if(recipe.getDirections()!=null){
            updatedRecipe.setDirections(recipe.getDirections());
        }if(recipe.getName()!=null){
            updatedRecipe.setName(recipe.getName());
        }if(recipe.getPrepTimeInMin()!=null){
            updatedRecipe.setPrepTimeInMin(recipe.getPrepTimeInMin());
        }if(recipe.getServings()!=null){
            updatedRecipe.setServings(recipe.getServings());
        }if(recipe.getUser()!=null){
            updatedRecipe.setUser(recipe.getUser());
        }

       return this.recipeRepository.save(updatedRecipe);
    }



    @Override
    public boolean deleteRecipeById(Long id) {

        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void createRecipe(Recipe recipe)
    {
        recipeRepository.save(recipe);
        }
}
