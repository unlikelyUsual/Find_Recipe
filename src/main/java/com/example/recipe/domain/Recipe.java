package com.example.recipe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer serving;

    private String source;

    private String url;

    private String directions;

    @Lob
    private byte[] image;

    //TODO Add Enum for Difficulty
    //private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

}
