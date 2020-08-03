package com.example.recipe.repositories;

import com.example.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {

    Optional<Recipe> findByDescription(String description);
}
