package com.example.application_dontfailme.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application_dontfailme.R;
import com.example.application_dontfailme.data.model.Recipe;
import com.example.application_dontfailme.databinding.CardRecipeDetailBinding;
import com.example.application_dontfailme.viewmodel.RecipeVM;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private RecipeVM recipeVM;
    private LifecycleOwner lifecycleOwner;

    public RecipeListAdapter(RecipeVM vm, LifecycleOwner lifecycleOwner) {
        this.recipeVM = vm;
        this.lifecycleOwner = lifecycleOwner;

        vm.getRecipeList().observe(lifecycleOwner, e -> notifyDataSetChanged());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardRecipeDetailBinding binding = CardRecipeDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setLifecycleOwner(lifecycleOwner);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setRecipe(recipeVM.getRecipeList().getValue().get(position));
    }

    @Override
    public int getItemCount() {
        List<Recipe> ls = recipeVM.getRecipeList().getValue();
        return ls == null ? 0 : ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardRecipeDetailBinding binding;
        private Recipe recipe;


        public ViewHolder(CardRecipeDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            binding.getRoot().setOnClickListener(v -> {
                recipeVM.setRecipe(recipe.getId());
                Navigation.findNavController(v).navigate(R.id.action_contentMainFragment_to_recipeDetailFragment);
            });

        }

        public Recipe getRecipe() {
            return recipe;
        }

        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
            binding.setRecipe(recipe);
        }
    }
}
