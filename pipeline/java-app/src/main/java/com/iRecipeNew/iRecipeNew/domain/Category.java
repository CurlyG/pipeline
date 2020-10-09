package com.iRecipeNew.iRecipeNew.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
//@JsonIgnoreProperties({"recipes"})
public class  Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonProperty("recipes")
    private List<Recipe> recipes;


    public Category(){}


}
