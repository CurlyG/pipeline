package com.iRecipeNew.iRecipeNew.controller;

import com.iRecipeNew.iRecipeNew.domain.Category;
import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.service.CategoryService;
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
@RequestMapping("api/v1//categories")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getCategories(){

        return categoryService.getAllCategories();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable(required = true) long id){
        Optional<Category> category = this.categoryService.getCategoryById(id);

        if(!category.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(category);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createRecipe(@RequestBody Category category){

        this.categoryService.createCategory(category);

        return "Category sucessfully created!";
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteCategoryById(@PathVariable long id){

        if (this.categoryService.deleteCategoryById(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("/{id}")
    @ResponseStatus(value= HttpStatus.ACCEPTED)
    public ResponseEntity<String> patchCategoryById(@PathVariable long id, @RequestBody Category category) {

        if (!categoryService.getCategoryById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            this.categoryService.updateCategoryById(id, category);
            return ResponseEntity.ok().body("Category successfully updated");
        }
    }



}
