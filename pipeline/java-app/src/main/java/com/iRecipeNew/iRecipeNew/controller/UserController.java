package com.iRecipeNew.iRecipeNew.controller;


import com.iRecipeNew.iRecipeNew.domain.User;
import com.iRecipeNew.iRecipeNew.service.UserService;
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
@RequestMapping("api/v1/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable long id){
        Optional<User> user = this.userService.getUserById(id);

        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(user);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody User user){

        this.userService.createUser(user);

        return "User sucessfully created!";
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteUserById(@PathVariable long id){

        if (this.userService.deleteUserById(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(value= HttpStatus.ACCEPTED)
    public ResponseEntity<String> putUserById(@PathVariable long id, @RequestBody User user){

        if (!userService.getUserById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }else{
            this.userService.updateUserById(user, id);
            return ResponseEntity.ok().body("User successfully updated");
        }
    }


}
