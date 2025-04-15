package com.example.nikitadeveloper.hs01032025.repository;

import com.example.nikitadeveloper.hs01032025.model.MealCategory;
import com.example.nikitadeveloper.hs01032025.model.MealTime;
import com.example.nikitadeveloper.hs01032025.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByIngredientsContaining(String ingredient);
    List<Recipe> findByIngredientsNotContaining(String ingredient);
    List<Recipe> findByCategory(MealCategory category);
    List<Recipe> findByCuisine(String cuisine);
    List<Recipe> findByMealTime(MealTime mealTime);

    List<Recipe> findTop3ByCuisineOrderByPopularityDesc(String cuisine);

    @Query("SELECT r.cuisine FROM Recipe r GROUP BY r.cuisine ORDER BY SUM(r.popularity) DESC LIMIT 3")
    List<String> findTopCuisinesByPopularity();

    @Query("SELECT r.category FROM Recipe r GROUP BY r.category ORDER BY COUNT(r) DESC LIMIT 3")
    List<MealCategory> findTop3CategoriesByRecipeCount();
}