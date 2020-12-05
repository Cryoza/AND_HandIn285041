package com.example.application_dontfailme.data.persistence;


import com.example.application_dontfailme.data.model.JournalEntry;

public interface JournalDetailRepository {
    JournalDetailLiveData getJournalEntryById(String id);
    JournalLiveData getAllEntries();
    JournalDetailLiveData updateJournalEntry(JournalEntry entry);
    JournalDetailLiveData addJournalEntry(JournalEntry entry);
    void removeJournalEntry(String id);
}
