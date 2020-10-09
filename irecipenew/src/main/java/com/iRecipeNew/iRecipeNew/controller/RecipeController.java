package com.iRecipeNew.iRecipeNew.controller;

import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("api/v1/recipes")
public class RecipeController {
    private final Logger logger = LoggerFactory.getLogger(RecipeController.class);


    @Autowired
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService)
    {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public List<Recipe> getRecipes(){

        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Recipe>> getRecipeById(@PathVariable(required = true) long id){
        Optional<Recipe> recipe = this.recipeService.getRecipeById(id);

        if(!recipe.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(recipe);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createRecipe(@RequestBody Recipe recipe){

        this.recipeService.createRecipe(recipe);

        return "Recipe sucessfully created!";
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteRecipeById(@PathVariable long id){

        if (this.recipeService.deleteRecipeById(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(value= HttpStatus.ACCEPTED)
    public ResponseEntity<String>putRecipeById(@PathVariable long id, @RequestBody Recipe recipe){

        if (!recipeService.getRecipeById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }else{
            this.recipeService.updateRecipeById(id, recipe);
            return ResponseEntity.ok().body("Recipe successfully updated");
        }
   }



}
