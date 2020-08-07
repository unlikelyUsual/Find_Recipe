package com.example.recipe.service;


import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.mappers.RecipeMapper;
import com.example.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add );
        return recipeSet;
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public RecipeCommand saveOrUpdateRecipe(RecipeCommand recipeCommand) {
        Recipe recipe = recipeMapper.commandToEntity(recipeCommand);
        Recipe saveRecipe = recipeRepository.save(recipe);
        RecipeCommand savedObject = recipeMapper.entityToCommand(saveRecipe);
        return savedObject;
    }

}
