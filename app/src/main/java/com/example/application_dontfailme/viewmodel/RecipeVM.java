package com.example.application_dontfailme.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application_dontfailme.data.persistence.RecipeDetailLiveData;
import com.example.application_dontfailme.data.model.Recipe;
import com.example.application_dontfailme.data.persistence.FirestoreRecipeDetaiReposlitory;
import com.example.application_dontfailme.data.persistence.RecipeLiveData;

import java.util.List;

import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class RecipeVM extends ViewModel {


    private FirestoreRecipeDetaiReposlitory repository;

    private RecipeLiveData recipeList;
    private RecipeDetailLiveData recipe;

    @ViewModelInject
    public RecipeVM(FirestoreRecipeDetaiReposlitory rdr) {
        this.repository = rdr;
        recipeList = rdr.getAllRecipes();
    }

    public RecipeDetailLiveData getRecipe() { return recipe; }
    public void setRecipe(String id) {
        this.recipe = repository.getRecipeById(id);
    }
    public LiveData<List<Recipe>> getRecipeList() {
        return recipeList;
    }
    public void updateSelected() { repository.updateRecipes(recipe.getValue());}

    public void createRecipe() {
        Recipe r = new Recipe();
        recipe = repository.addRecipe(r);

    }

    public void removeRecipe() {
        repository.removeRecipe(recipe.getValue().getId());
    }



}
