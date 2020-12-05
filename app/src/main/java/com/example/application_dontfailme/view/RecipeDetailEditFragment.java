package com.example.application_dontfailme.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.application_dontfailme.R;
import com.example.application_dontfailme.databinding.FragmentRecipeDetailEditBinding;
import com.example.application_dontfailme.viewmodel.RecipeVM;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecipeDetailEditFragment extends Fragment {

    private RecipeVM vm;
    private FragmentRecipeDetailEditBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentRecipeDetailEditBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(
                Navigation.findNavController(view).getBackStackEntry(R.id.nav_graph),
                getDefaultViewModelProviderFactory())
                .get(RecipeVM.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(vm);

        binding.buttonEdit.setOnClickListener(v -> {
            vm.updateSelected();
            Navigation.findNavController(view).popBackStack();
        });
        binding.buttonRemoveR.setOnClickListener(r -> {
            vm.removeRecipe();
            Navigation.findNavController(view).popBackStack();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
