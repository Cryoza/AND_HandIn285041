package com.example.application_dontfailme.data.persistence;

import com.example.application_dontfailme.data.model.Recipe;

public interface RecipeDetailRepository {
    RecipeDetailLiveData getRecipeById(String id);
    RecipeLiveData getAllRecipes();
    RecipeDetailLiveData updateRecipes(Recipe recipe);
    RecipeDetailLiveData addRecipe(Recipe recipe);
    void removeRecipe(String id);
}
