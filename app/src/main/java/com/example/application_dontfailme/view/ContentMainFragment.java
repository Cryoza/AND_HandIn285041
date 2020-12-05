package com.example.application_dontfailme.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.application_dontfailme.databinding.FragmentContentMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class ContentMainFragment extends Fragment {

    private FragmentContentMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContentMainBinding.inflate(inflater, container, false);

        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);

        binding.viewPager2.setAdapter(new FragPagerAdapter(this));
        String[] titles = new String[]{"Journal", "Recipes"};
        new TabLayoutMediator(binding.tabs, binding.viewPager2,
                (tab, pos) -> tab.setText(titles[pos])).attach();


        return binding.getRoot();
    }
}