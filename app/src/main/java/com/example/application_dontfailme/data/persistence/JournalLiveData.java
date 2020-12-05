package com.example.application_dontfailme.data.persistence;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.application_dontfailme.data.model.JournalEntry;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class JournalLiveData extends LiveData<List<JournalEntry>> implements EventListener<QuerySnapshot> {

    private ListenerRegistration listenerRegistration;
    private Query reference;

    public JournalLiveData(Query reference) {
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
            List<JournalEntry> entries = new ArrayList<>();
            for (QueryDocumentSnapshot doc : snap) {
                JournalEntry wubbalubbadubdub = doc.toObject(JournalEntry.class);
                wubbalubbadubdub.setId(doc.getId());
                entries.add(wubbalubbadubdub);
            }
            setValue(entries);
        } else if (error != null) {
            throw new RuntimeException(error);
        }
    }
}
