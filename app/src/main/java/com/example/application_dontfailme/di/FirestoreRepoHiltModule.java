package com.example.application_dontfailme.di;

import android.app.Application;

import com.example.application_dontfailme.data.persistence.FirestoreJournalDetailRepository;
import com.example.application_dontfailme.data.persistence.FirestoreRecipeDetaiReposlitory;
import com.example.application_dontfailme.data.persistence.JournalDetailRepository;
import com.example.application_dontfailme.data.persistence.RecipeDetailRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.components.FragmentComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class FirestoreRepoHiltModule {

    @Binds
    public abstract JournalDetailRepository bindJournalRepo(FirestoreJournalDetailRepository impl);
    @Binds
    public abstract RecipeDetailRepository bindRecipeRepo(FirestoreRecipeDetaiReposlitory impl);
}
