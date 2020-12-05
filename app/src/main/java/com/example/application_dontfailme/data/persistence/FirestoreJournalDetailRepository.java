package com.example.application_dontfailme.data.persistence;

import com.example.application_dontfailme.data.model.JournalEntry;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirestoreJournalDetailRepository implements JournalDetailRepository {

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final String COLLECTION_PATH = "journal-entry-collection";

    @Inject
    public FirestoreJournalDetailRepository() {}

    @Override
    public JournalDetailLiveData getJournalEntryById(String id) {
        DocumentReference temp = firestore.collection(COLLECTION_PATH).
                document(id);
        return new JournalDetailLiveData(temp);
    }

    @Override
    public JournalLiveData getAllEntries() {
        return new JournalLiveData(firestore.collection(COLLECTION_PATH).orderBy("date", Query.Direction.DESCENDING));
    }

    @Override
    public JournalDetailLiveData updateJournalEntry(JournalEntry entry) {
        DocumentReference documentReference = firestore.collection(COLLECTION_PATH).document(entry.getId()) ;
        documentReference.set(entry);
        return new JournalDetailLiveData(documentReference);
    }

    @Override
    public JournalDetailLiveData addJournalEntry(JournalEntry entry) {
        DocumentReference documentReference = firestore.collection(COLLECTION_PATH).document();
        entry.setId(documentReference.getId());
        documentReference.set(entry);
        return new JournalDetailLiveData(documentReference);
    }

    @Override
    public void removeJournalEntry(String id) {
        firestore.collection(COLLECTION_PATH).document(id).delete();
    }
}
