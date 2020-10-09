package com.iRecipeNew.iRecipeNew.service;

import com.iRecipeNew.iRecipeNew.domain.Category;
import com.iRecipeNew.iRecipeNew.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);

    Category updateCategoryById(Long id, Category category);
    boolean deleteCategoryById(Long id);
    void createCategory(Category category);



}
