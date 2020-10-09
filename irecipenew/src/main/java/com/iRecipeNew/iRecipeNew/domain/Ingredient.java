package com.iRecipeNew.iRecipeNew.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;



@Entity
@Getter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unitOfMeasure;


    @OneToMany(mappedBy = "ingredient")
    List<RecipeIngredient> quantity;

    public Ingredient() {
    }
}
