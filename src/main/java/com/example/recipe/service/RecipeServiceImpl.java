package com.example.recipe.service;


import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.exceptions.NotFoundException;
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
        if(!recipeRepository.findById(id).isPresent()){
            throw new NotFoundException("Recipe With ID " + id + " Not Found");
        }
        return recipeRepository.findById(id).get();
    }

    @Override
    @Transactional
    public RecipeCommand saveOrUpdateRecipe(RecipeCommand recipeCommand) {
        if(recipeCommand.getId() != null){
            Recipe dbRecipe = this.getRecipeById(recipeCommand.getId());
            recipeCommand.setImage(dbRecipe.getImage());
            recipeCommand.setImageString(dbRecipe.getImageString());
        }
        Recipe recipe = recipeMapper.commandToEntity(recipeCommand);
        recipeCommand.getIngredients().forEach(recipe::addIngredient);
        Recipe saveRecipe = recipeRepository.save(recipe);
        RecipeCommand savedObject = recipeMapper.entityToCommand(saveRecipe);
        return savedObject;
    }

    @Override
    public RecipeCommand getRecipeCommonObjectById(Long id) {
        Recipe recipe = this.getRecipeById(id);
        if(recipe == null) throw new RuntimeException("Recipe is Not Saved");
        return recipeMapper.entityToCommand(recipe);
    }

}
