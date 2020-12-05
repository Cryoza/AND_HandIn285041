package com.example.application_dontfailme.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application_dontfailme.R;
import com.example.application_dontfailme.databinding.FragmentRecipeBinding;
import com.example.application_dontfailme.view.RecipeListAdapter;
import com.example.application_dontfailme.viewmodel.RecipeVM;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecipeFragment extends Fragment {


    private RecipeVM vm;
    private FragmentRecipeBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(
                Navigation.findNavController(requireActivity(), R.id.nav_host).getBackStackEntry(R.id.nav_graph),
                getDefaultViewModelProviderFactory())
                .get(RecipeVM.class);

        RecyclerView rv = binding.recycViewR;
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(new RecipeListAdapter(vm,requireActivity()));


        binding.fab.setOnClickListener(fab -> {
            vm.createRecipe();
            Navigation.findNavController(requireActivity(), R.id.nav_host).navigate(R.id.action_contentMainFragment_to_recipeDetailFragment);
        });




    }
}