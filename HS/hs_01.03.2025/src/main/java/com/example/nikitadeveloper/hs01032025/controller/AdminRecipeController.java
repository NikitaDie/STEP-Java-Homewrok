package com.example.nikitadeveloper.hs01032025.controller;

import com.example.nikitadeveloper.hs01032025.model.Recipe;
import com.example.nikitadeveloper.hs01032025.repository.RecipeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/recipes")
public class AdminRecipeController {
    private final RecipeRepository repo;

    public AdminRecipeController(RecipeRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/add")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return repo.save(recipe);
    }

    @PutMapping("/edit/{id}")
    public Recipe update(@PathVariable Long id, @RequestBody Recipe updated) {
        Recipe recipe = repo.findById(id).orElseThrow();
        recipe.setTitle(updated.getTitle());
        return repo.save(recipe);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
