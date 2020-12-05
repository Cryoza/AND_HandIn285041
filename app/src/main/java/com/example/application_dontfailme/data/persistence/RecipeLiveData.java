package com.example.application_dontfailme.data.persistence;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.application_dontfailme.data.model.Recipe;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecipeLiveData extends LiveData<List<Recipe>> implements EventListener<QuerySnapshot> {

    private ListenerRegistration listenerRegistration;
    private Query reference;

    public RecipeLiveData(Query reference) {
        this.reference = reference;
    }

    @Override
    protected void onActive() {
        super.onActive();
        listenerRegistration = reference.addSnapshotListener(this);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        listenerRegistration.remove();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot snap, @Nullable FirebaseFirestoreException error) {
        if (snap != null) {
            List<Recipe> recipes = new ArrayList<>();
            for (QueryDocumentSnapshot doc : snap) {
                Recipe wubbalubbadubdub = doc.toObject(Recipe.class);
                wubbalubbadubdub.setId(doc.getId());
                recipes.add(wubbalubbadubdub);
            }
            setValue(recipes);
        } else if (error != null) {
            throw new RuntimeException(error);
        }
    }
}
