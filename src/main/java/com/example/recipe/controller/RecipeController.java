package com.example.recipe.controller;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}")
    String getRecipeInfoById(@PathVariable(name = "id") Long id, Model model) {
         if(id == null){
             return  "index";
         }
        Recipe recipe = recipeService.getRecipeById(id);
         model.addAttribute("recipe",recipe);
        return "recipe/viewRecipe";
    }

    @GetMapping("/recipe/create")
    String getRecipeAddPage(Model model) {
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeForm";
    }

    @PostMapping("/recipe/form")
    String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand saveRecipe = recipeService.saveOrUpdateRecipe(recipeCommand);
        return  "redirect:/recipe/"+ saveRecipe.getId();
    }

}
