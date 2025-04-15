package com.example.nikitadeveloper.hs01032025.service;

import com.example.nikitadeveloper.hs01032025.model.MealCategory;
import com.example.nikitadeveloper.hs01032025.model.MealTime;
import com.example.nikitadeveloper.hs01032025.model.Recipe;
import com.example.nikitadeveloper.hs01032025.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class RecipeService {
    private final RecipeRepository repo;

    public RecipeService(RecipeRepository repo) {
        this.repo = repo;
    }

    public Recipe getRecipeById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Recipe getRandomRecipe() {
        List<Recipe> allRecipes = repo.findAll();
        return allRecipes.get(new Random().nextInt(allRecipes.size()));
    }

    public List<Recipe> getRecipesByIngredient(String ingredient) {
        return repo.findByIngredientsContaining(ingredient);
    }

    public List<Recipe> getRecipesWithoutIngredient(String ingredient) {
        return repo.findByIngredientsNotContaining(ingredient);
    }

    public List<MealCategory> getAllCategories() {
        return repo.findAll().stream()
            .map(Recipe::getCategory)
            .distinct()
            .toList();
    }

    public List<Recipe> getTop3ByCategory(MealCategory category) {
        return repo.findByCategory(category).stream()
            .limit(3)
            .toList();
    }

    public List<Recipe> getRandomDinner() {
        List<Recipe> allRecipes = repo.findByMealTime(MealTime.DINNER);
        Collections.shuffle(allRecipes);
        return allRecipes.stream().limit(3).toList();
    }

    public List<Recipe> getRandomBreakfast() {
        List<Recipe> allRecipes = repo.findByMealTime(MealTime.BREAKFAST);
        Collections.shuffle(allRecipes);
        return allRecipes.stream().limit(2).toList();
    }

    public List<Recipe> getTop3ByCuisine(String cuisine) {
        return repo.findTop3ByCuisineOrderByPopularityDesc(cuisine);
    }

    public List<String> getTop3Cusine() {
        return repo.findTopCuisinesByPopularity();
    }

    public Recipe saveRecipe(Recipe recipe) {
        return repo.save(recipe);
    }

    public void deleteRecipe(Long id) {
        repo.deleteById(id);
    }

    public List<MealCategory> getTop3Category() {
        return repo.findTop3CategoriesByRecipeCount();
    }
}