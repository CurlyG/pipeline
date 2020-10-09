package com.iRecipeNew.iRecipeNew.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
@JsonIgnoreProperties({"role", "quantity", "user", "category", "cuisine"})
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("prepTimeInMin")
    private Integer prepTimeInMin;
    @JsonProperty("cookTimeInMin")
    private Integer cookTimeInMin;
    @JsonProperty("servings")
    private Integer servings;

    @Lob
    @JsonProperty("directions")
    private String directions;


    @Enumerated(value = EnumType.STRING)
    @JsonProperty("difficulty")
    private Difficulty difficulty;




    @OneToMany(mappedBy = "recipe")
    @JsonProperty("quantity")
    private Set<RecipeIngredient> quantity;

    @OneToMany(mappedBy = "recipe")
    @JsonProperty("comments")
    private List<Comment> comments;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty("user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonProperty("category")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuisine_id")
    @JsonProperty("cuisine")
    private Cuisine cuisine;


    public Recipe(){}



}