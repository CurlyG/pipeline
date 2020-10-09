package com.iRecipeNew.iRecipeNew.controller;

import com.google.gson.Gson;
import com.iRecipeNew.iRecipeNew.domain.Difficulty;
import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.domain.Role;
import com.iRecipeNew.iRecipeNew.domain.User;
import com.iRecipeNew.iRecipeNew.repository.RecipeRepository;
import com.iRecipeNew.iRecipeNew.repository.UserRepository;
import com.iRecipeNew.iRecipeNew.service.RecipeServiceImpl;
import com.iRecipeNew.iRecipeNew.service.UserServiceImpl;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @MockBean
    private UserServiceImpl userService;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(userRepository).isNotNull();
        assertThat(userService).isNotNull();
    }


    private List<User> existingUsers;
    private User user;
    private User updatedUser;
    private String results;
    private String result;
    private String updatedResult;
    private Gson gson;

    @BeforeEach
    public void setUpBeforeClass() {

        gson = new Gson();

        Set<Role> roles = new HashSet<Role>();
        List<Recipe> recipes = new ArrayList<>();

        existingUsers = new ArrayList<>();
        user = new User();
        user.setUsername("Ruzanna");
        user.setEmail("ruzanna@gmail.com");
        user.setId(1L);
        user.setRoles(roles);
        user.setPassword("1234");
        user.setRecipes(recipes);
        existingUsers.add(user);
        results = gson.toJson(existingUsers);
        result = gson.toJson(user);

        updatedUser = new User();
        updatedUser.setUsername("Marina");
        updatedUser.setId(2L);
        updatedUser.setEmail("marina@gmail.com");
        updatedUser.setRoles(roles);
        updatedUser.setPassword("12345");
        updatedUser.setRecipes(recipes);
        updatedResult = gson.toJson(updatedUser);
    }

    @Test
    public void getUsersTest() throws Exception {
        given(userService.getAllUsers()).willReturn(existingUsers);

        mockMvc.perform(
                get("http://localhost:8080/api/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(results));

    }


    @Test
    public void getUserByIdTest() throws Exception {
        given(userService.getUserById(1L)).willReturn(Optional.of(user));

        mockMvc.perform(get("http://localhost:8080/api/v1/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));

    }

    @Test
    public void getUserByIncorrectIdTest() throws Exception {
        given(userService.getUserById(5L)).willReturn(Optional.of(user));

        mockMvc.perform(get("http://localhost:8080/api/v1/users/11").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    @Test
    public void createUserTest() throws Exception{

        mockMvc.perform(post("http://localhost:8080/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andExpect(status().isCreated());

    }


    @Test
    public void deleteUserByIdTest() throws Exception {
        given(userService.deleteUserById(1L)).willReturn(true);
        mockMvc.perform(delete("http://localhost:8080/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void deleteUserByIncorrectIdTest() throws Exception {
        given(userService.deleteUserById(11L)).willReturn(false);
        mockMvc.perform(delete("http://localhost:8080/api/v1/users/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void putUserByIdTest() throws Exception {
        given(userService.getUserById(1L)).willReturn(Optional.of(user));

        mockMvc.perform(put("http://localhost:8080/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedResult))
                .andExpect(content().string("User successfully updated"));

    }

    @Test
    public void putUserByIncorrectIdTest() throws Exception {
        given(userService.getUserById(15L)).willReturn(Optional.of(user));

        mockMvc.perform(put("http://localhost:8080/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedResult))
                .andExpect(status().isNotFound());

    }

}
