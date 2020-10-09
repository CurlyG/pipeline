package com.iRecipeNew.iRecipeNew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@ComponentScan//({ "com.iRecipeNew.iRecipeNew.controller.CategoryController, com.iRecipeNew.iRecipeNew.controller.CommentController, com.iRecipeNew.iRecipeNew.controller.RecipeController, com.iRecipeNew.iRecipeNew.controller.UserController"})
	public class IRecipeNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(IRecipeNewApplication.class, args);

	}

}
