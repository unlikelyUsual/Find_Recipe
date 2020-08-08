package com.example.recipe.commands;

import com.example.recipe.domain.Category;
import com.example.recipe.domain.Ingredient;
import com.example.recipe.domain.Notes;
import com.example.recipe.enums.Difficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class RecipeCommand {

    private Long id;

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer serving;

    private String source;

    private String url;

    private String directions;

    private byte[] image;

    private Difficulty difficulty;

    private List<Ingredient> ingredients = new ArrayList<>();

    private Notes notes;

    private Set<Category> categories = new HashSet<>();

    private String imageString;
}
