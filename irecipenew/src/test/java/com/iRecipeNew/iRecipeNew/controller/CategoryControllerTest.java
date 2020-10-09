package com.iRecipeNew.iRecipeNew.controller;

import com.google.gson.Gson;
import com.iRecipeNew.iRecipeNew.domain.Category;
import com.iRecipeNew.iRecipeNew.domain.Difficulty;
import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.repository.CategoryRepository;
import com.iRecipeNew.iRecipeNew.repository.RecipeRepository;
import com.iRecipeNew.iRecipeNew.service.CategoryServiceImpl;
import com.iRecipeNew.iRecipeNew.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CategoryRepository categoryRepository;

    @MockBean
    private CategoryServiceImpl categoryService;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(categoryRepository).isNotNull();
        assertThat(categoryService).isNotNull();
    }


    private List<Category> existingCategories;
    private Category category;
    private Category updatedCategory;
    private String results;
    private String result;
    private String updatedResult;
    private Gson gson;

    @BeforeEach
    public void setUpBeforeClass() {
        List<Recipe> recipes = new ArrayList<>();


        gson = new Gson();

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


    }

    @Test
    public void getCategoriesTest() throws Exception {
        given(categoryService.getAllCategories()).willReturn(existingCategories);

        mockMvc.perform(
                get("http://localhost:8080/api/v1/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(results));

    }


    @Test
    public void getCategoryByIdTest() throws Exception {
        given(categoryService.getCategoryById(5L)).willReturn(Optional.of(category));

        mockMvc.perform(get("http://localhost:8080/api/v1/categories/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));

    }

    @Test
    public void getCategoryByIncorrectIdTest() throws Exception {
        given(categoryService.getCategoryById(5L)).willReturn(Optional.of(category));

        mockMvc.perform(get("http://localhost:8080/api/v1/categories/4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    @Test
    public void createCategoryTest() throws Exception{

        mockMvc.perform(post("http://localhost:8080/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andExpect(status().isCreated());

    }


    @Test
    public void deleteCategoryByIdTest() throws Exception {
        given(categoryService.deleteCategoryById(5L)).willReturn(true);
        mockMvc.perform(delete("http://localhost:8080/api/v1/categories/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }
    @Test
    public void deleteCategoryByIncorrectIdTest() throws Exception {
        given(categoryService.deleteCategoryById(55L)).willReturn(false);
        mockMvc.perform(delete("http://localhost:8080/api/v1/categories/55")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    @Test
    public void patchCategoryByIdTest() throws Exception {
        given(categoryService.getCategoryById(5L)).willReturn(Optional.of(category));

        mockMvc.perform(patch("http://localhost:8080/api/v1/categories/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedResult))
                .andExpect(content().string("Category successfully updated"));
    }


    @Test
    public void patchCategoryByIncorrectIdTest() throws Exception {
        given(categoryService.getCategoryById(55L)).willReturn(Optional.of(category));

        mockMvc.perform(patch("http://localhost:8080/api/v1/categories/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedResult))
                .andExpect(status().isNotFound());
    }

}
