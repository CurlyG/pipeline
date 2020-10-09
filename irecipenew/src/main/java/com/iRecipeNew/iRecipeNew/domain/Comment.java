package com.iRecipeNew.iRecipeNew.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@JsonIgnoreProperties({"recipe"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("text")
    private String text;
    @JsonProperty("authorName")
    private String authorName;


    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonProperty("recipe")
    private Recipe recipe;

    public Comment(){}


}
