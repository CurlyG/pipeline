package com.iRecipeNew.iRecipeNew.service;

import com.google.gson.Gson;
import com.iRecipeNew.iRecipeNew.domain.Category;
import com.iRecipeNew.iRecipeNew.domain.Difficulty;
import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.repository.CategoryRepository;
import com.iRecipeNew.iRecipeNew.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();



    private List<Category> existingCategories;
    private Category category;
    private Category updatedCategory;
    private String results;
    private String result;
    private String updatedResult;
    private Gson gson;

    @BeforeEach
    public void setMockOutput() {
        gson = new Gson();

        List<Recipe> recipes = new ArrayList<>();


        existingCategories = new ArrayList<>();
        category = new Category();
        category.setName("breakfast");
        category.setId(5L);
        category.setRecipes(recipes);

        existingCategories.add(category);
        results = gson.toJson(existingCategories);
        result = gson.toJson(category);

        updatedCategory = new Category();
        updatedCategory.setName("main dish");
        updatedCategory.setId(4L);
        updatedCategory.setRecipes(recipes);
        updatedResult = gson.toJson(updatedCategory);


        when(categoryRepository.findAll()).thenReturn(existingCategories);
        when(categoryRepository.findById(5L)).thenReturn(Optional.of(category));
        when(categoryRepository.findById(4L)).thenReturn(Optional.of(updatedCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
      //  when(categoryRepository.save(any(Category.class))).thenReturn(updatedCategory);


    }

    @Test
    public void getAllCategoriesTest(){

        assertEquals(1, categoryService.getAllCategories().size());

    }

    @Test
    public void getCategoryByIdTest(){
        assertEquals(Optional.of(category), categoryService.getCategoryById(5L));


        Optional<Category> test = categoryService.getCategoryById(5L);
        assert(test.isPresent());

    }

    @Test
    public void updateCategoryByIdTest(){

        categoryService.getCategoryById(5L);
        categoryService.updateCategoryById(5L, updatedCategory);

        assert(categoryService.getCategoryById(5L).get().getName().equals(updatedCategory.getName()));
    }

    @Test
    public void updateCategoryByIdTestWhenNameIsNull(){

        updatedCategory.setName(null);
        categoryService.getCategoryById(5L);
        categoryService.updateCategoryById(5L, updatedCategory);

        assert(categoryService.getCategoryById(5L).get().getId().equals(updatedCategory.getId()));
    }

    @Test
    public void updateCategoryByIdTestWhenIdIsNull(){

        updatedCategory.setId(null);
        categoryService.getCategoryById(5L);
        categoryService.updateCategoryById(5L, updatedCategory);

        assert(categoryService.getCategoryById(5L).get().getName().equals(updatedCategory.getName()));
    }



    @Test
    public void deleteCategoryByIdTest(){
        given(categoryService.deleteCategoryById(5L)).willReturn(true);

        categoryService.deleteCategoryById(5L);
        verify(categoryRepository, times(1)).deleteById(5L);

    }

    @Test
    public void createCategoryTest(){

        doAnswer((arguments) -> {
            assertEquals(category, arguments.getArgument(0));
            return null;
        }).when(categoryRepository).save(any(Category.class));
        categoryService.createCategory(category);

        verify(categoryRepository, times(1)).save(category);

    }


}
