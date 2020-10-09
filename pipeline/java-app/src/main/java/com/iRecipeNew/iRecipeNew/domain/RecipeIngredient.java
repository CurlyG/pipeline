package com.iRecipeNew.iRecipeNew.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//Using this composite key class(RecipeIngredientKey), I create the entity class, which models the join table
@Entity
public class RecipeIngredient {

    @EmbeddedId //to mark primary key
    RecipeIngredientKey id;

    //@MapsId - means that the key is a foreign in manytomany relationship
    @ManyToOne
    @MapsId("recipe_id")
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    @ManyToOne
    @MapsId("ingredient_id")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;


    int quantity;

}
