package com.iRecipeNew.iRecipeNew.service;

import com.iRecipeNew.iRecipeNew.domain.Recipe;
import com.iRecipeNew.iRecipeNew.domain.User;
import com.iRecipeNew.iRecipeNew.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    @Override
    public User updateUserById(User user, Long id) {

        User updatedUser = getUserById(id).get();


        if(user.getId()!=null){
            updatedUser.setId(user.getId());
        }if(user.getEmail()!=null){
            updatedUser.setEmail(user.getEmail());
        }if(user.getPassword()!=null){
            updatedUser.setPassword(user.getPassword());
        }if(user.getRoles()!=null){
            updatedUser.setRoles(user.getRoles());
        }if(user.getUsername()!=null){
            updatedUser.setUsername(user.getUsername());
        }if(user.getRecipes()!=null){
            user.setRecipes(user.getRecipes());

        }
        return this.userRepository.save(updatedUser);

    }


    @Override
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);

    }
}
