package com.iRecipeNew.iRecipeNew.service;

import com.google.gson.Gson;
import com.iRecipeNew.iRecipeNew.domain.Difficulty;
import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.domain.Role;
import com.iRecipeNew.iRecipeNew.domain.User;
import com.iRecipeNew.iRecipeNew.repository.RecipeRepository;
import com.iRecipeNew.iRecipeNew.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();



    private List<User> existingUsers;
    private User user;
    private User updatedUser;
    private String results;
    private String result;
    private String updatedResult;
    private Gson gson;

    @BeforeEach
    public void setMockOutput() {
        gson = new Gson();

        Set<Role> roles = new HashSet<Role>();
        List<Recipe> recipes = new ArrayList<>();


        existingUsers = new ArrayList<>();
        user = new User();
        user.setUsername("Ruzanna");
        user.setEmail("ruzanna@gmail.com");
        user.setId(1L);
        user.setPassword("1234");
        user.setRoles(roles);
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


        when(userRepository.findAll()).thenReturn(existingUsers);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(2L)).thenReturn(Optional.of(updatedUser));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);


    }

    @Test
    public void getAllUsersTest(){

        assertEquals(1, userService.getAllUsers().size());

    }

    @Test
    public void getUserByIdTest(){
        assertEquals(Optional.of(user), userService.getUserById(1L));


        Optional<User> test = userService.getUserById(1L);
        assert(test.isPresent());

    }

    @Test
    public void updateUserByIdTest(){

        userService.getUserById(1L);
        userService.updateUserById(updatedUser, 1L );

        assert(userService.getUserById(1L).get().getUsername().equals(updatedUser.getUsername()));
    }

    @Test
    public void updateUserByIdWhenIdIsNullTest(){
        updatedUser.setId(null);
        userService.getUserById(1L);
        userService.updateUserById(updatedUser, 1L );

        assert(userService.getUserById(1L).get().getUsername().equals(updatedUser.getUsername()));
    }

    @Test
    public void updateUserByIdWhenEmailIsNullTest(){
        updatedUser.setEmail(null);
        userService.getUserById(1L);
        userService.updateUserById(updatedUser, 1L );

        assert(userService.getUserById(1L).get().getUsername().equals(updatedUser.getUsername()));
    }

    @Test
    public void updateUserByIdWhenRolesAreNullTest(){
        updatedUser.setRoles(null);
        userService.getUserById(1L);
        userService.updateUserById(updatedUser, 1L );

        assert(userService.getUserById(1L).get().getUsername().equals(updatedUser.getUsername()));
    }

    @Test
    public void updateUserByIdUsernameIsNullTest(){
        updatedUser.setUsername(null);
        userService.getUserById(1L);
        userService.updateUserById(updatedUser, 1L );

        assert(userService.getUserById(1L).get().getEmail().equals(updatedUser.getEmail()));
    }



    @Test
    public void deleteUserByIdTest(){
        given(userService.deleteUserById(1L)).willReturn(true);

        userService.deleteUserById(1L);
        verify(userRepository, times(1)).deleteById(1L);

    }

    @Test
    public void createUserTest(){

        doAnswer((arguments) -> {
            assertEquals(user, arguments.getArgument(0));
            return null;
        }).when(userRepository).save(any(User.class));
        userService.createUser(user);

        verify(userRepository, times(1)).save(user);

    }
}
