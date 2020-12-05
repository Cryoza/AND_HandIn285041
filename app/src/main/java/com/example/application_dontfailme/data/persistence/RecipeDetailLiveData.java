package com.example.application_dontfailme.data.persistence;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.application_dontfailme.data.model.Recipe;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class RecipeDetailLiveData extends LiveData<Recipe> implements EventListener<DocumentSnapshot> {
    private ListenerRegistration listenerRegistration;
    private DocumentReference reference;

    public RecipeDetailLiveData(DocumentReference reference) {
        this.reference = reference;
    }

    @Override
    public void onActive() {
        super.onActive();
        listenerRegistration = reference.addSnapshotListener(this);
    }

    @Override
    public void onInactive() {
        super.onInactive();
        listenerRegistration.remove();
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot snap, @Nullable FirebaseFirestoreException error) {
        if (snap != null && snap.exists()) {
            Recipe wubbalubbadubdub = snap.toObject(Recipe.class);
            wubbalubbadubdub.setId(snap.getId());
            setValue(wubbalubbadubdub);
        } else if (error != null) {
            throw new RuntimeException(error);
        }
    }
}
