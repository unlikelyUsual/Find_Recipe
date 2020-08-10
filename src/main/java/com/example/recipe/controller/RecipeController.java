package com.example.recipe.controller;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.exceptions.NotFoundException;
import com.example.recipe.service.IngredientService;
import com.example.recipe.service.RecipeService;
import com.example.recipe.service.UnitOfMeasureService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public RecipeController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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
        model.addAttribute("uomMap",unitOfMeasureService.findAll());
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeForm";
    }

    @PostMapping("/recipe/form")
    String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand saveRecipe = recipeService.saveOrUpdateRecipe(recipeCommand);
        return  "redirect:/recipe/"+ saveRecipe.getId();
    }

    @GetMapping("recipe/modify/{id}")
    String modifyRecipe(@PathVariable(name = "id") Long id , Model model) {
        model.addAttribute("uomMap",unitOfMeasureService.findAll());
        model.addAttribute("recipe",recipeService.getRecipeCommonObjectById(id));
        return "recipe/recipeForm";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    ModelAndView handleNotFoundException(Exception exception){
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("/errors/404");
        modelAndView.addObject("exception",exception.getMessage());
        return modelAndView;
    }

}
