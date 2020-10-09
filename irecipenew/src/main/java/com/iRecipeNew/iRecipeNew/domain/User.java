package com.iRecipeNew.iRecipeNew.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
//@JsonIgnoreProperties({"recipes"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;


    @Column(unique = true, nullable = false, name = "email")
    @Email
    @JsonProperty("email")
    private String email;

    @Size(min = 4, max = 25, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false, name = "username")
    @JsonProperty("username")
    private String username;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonProperty("roles")
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user")
    @JsonProperty("recipes")
    private List<Recipe> recipes;


    public User(){};

    public User(@Email String email, @Size(min = 4, max = 25, message = "Minimum username length: 4 characters") String username, @Size(min = 8, message = "Minimum password length: 8 characters") String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}



