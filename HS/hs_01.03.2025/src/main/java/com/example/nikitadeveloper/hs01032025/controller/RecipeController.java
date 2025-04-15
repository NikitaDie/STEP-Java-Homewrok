package com.example.nikitadeveloper.hs01032025.controller;

import com.example.nikitadeveloper.hs01032025.model.MealCategory;
import com.example.nikitadeveloper.hs01032025.model.Recipe;
import com.example.nikitadeveloper.hs01032025.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("/{id}/summary")
    public String getShortInfo(@PathVariable Long id) {
        return service.getRecipeById(id).getDescription();
    }

    @GetMapping("/{id}")
    public Recipe getFullRecipe(@PathVariable Long id) {
        return service.getRecipeById(id);
    }

    @GetMapping("/with-ingredient")
    public List<Recipe> getByIngredient(@RequestParam String ingredient) {
        return service.getRecipesByIngredient(ingredient);
    }

    @GetMapping("/random")
    public Recipe getRandom() {
        return service.getRandomRecipe();
    }

    @GetMapping("/categories")
    public List<MealCategory> getCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/top3")
    public List<Recipe> getTop3(@RequestParam String category) {
        return service.getTop3ByCategory(MealCategory.valueOf(category));
    }

    @GetMapping("/dinner")
    public List<Recipe> getRandomDinner() {
        return service.getRandomDinner();
    }

    @GetMapping("/breakfast")
    public List<Recipe> getRandomBreakfast() {
        return service.getRandomBreakfast();
    }

    @GetMapping("/exclude")
    public List<Recipe> excludeIngredient(@RequestParam String ingredient) {
        return service.getRecipesWithoutIngredient(ingredient);
    }

    @GetMapping("/top3/cuisine")
    public List<Recipe> top3ByCuisine(@RequestParam String cuisine) {
        return service.getTop3ByCuisine(cuisine);
    }

    @GetMapping("/top3/cuisines")
    public List<String> topCuisines() {
        return service.getTop3Cusine();
    }

    @GetMapping("/top3/categories")
    public List<MealCategory> topCategories() {
        return service.getTop3Category();
    }
}