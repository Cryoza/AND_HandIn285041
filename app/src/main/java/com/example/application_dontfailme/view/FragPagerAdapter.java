package com.example.application_dontfailme.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragPagerAdapter extends FragmentStateAdapter {

    public FragPagerAdapter(Fragment activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                return new JournalFragment();
            case 1:
                return new RecipeFragment();
            default:

                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
