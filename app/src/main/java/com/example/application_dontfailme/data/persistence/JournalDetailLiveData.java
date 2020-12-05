package com.example.application_dontfailme.data.persistence;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.application_dontfailme.data.model.JournalEntry;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class JournalDetailLiveData extends LiveData<JournalEntry> implements EventListener<DocumentSnapshot> {
    private ListenerRegistration listenerRegistration;
    private DocumentReference reference;

    public JournalDetailLiveData(DocumentReference reference) {
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
    public void onEvent(@Nullable DocumentSnapshot snap, @Nullable FirebaseFirestoreException error) {
        if (snap != null && snap.exists()) {
            JournalEntry wubbalubbadubdub = snap.toObject(JournalEntry.class);
            wubbalubbadubdub.setId(snap.getId());
            setValue(wubbalubbadubdub);
        } else if (error != null) {
            throw new RuntimeException(error);
        }
    }
}
