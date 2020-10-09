package com.iRecipeNew.iRecipeNew.service;

import com.iRecipeNew.iRecipeNew.domain.Category;
import com.iRecipeNew.iRecipeNew.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category updateCategoryById(Long id, Category category) {
        Category updatedCategory = getCategoryById(id).get();

        if(category.getId()!=null){
            updatedCategory.setId(category.getId());
        }if(category.getName()!=null){
            updatedCategory.setName(category.getName());
        }if(category.getRecipes()!=null) {
            updatedCategory.setRecipes(category.getRecipes());
        }

        return this.categoryRepository.save(updatedCategory);

    }

    @Override
    public boolean deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);

    }
}
