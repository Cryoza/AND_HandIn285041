package com.example.application_dontfailme.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application_dontfailme.data.persistence.JournalLiveData;
import com.example.application_dontfailme.data.model.JournalEntry;
import com.example.application_dontfailme.data.persistence.JournalDetailLiveData;
import com.example.application_dontfailme.data.persistence.FirestoreJournalDetailRepository;

import java.util.Date;
import java.util.List;

import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class JournalVM extends ViewModel {

    private FirestoreJournalDetailRepository repository;

    private JournalLiveData listEntries;
    private JournalDetailLiveData entry;

    @ViewModelInject
    public JournalVM(FirestoreJournalDetailRepository jdr) {
        this.repository = jdr;
        listEntries = jdr.getAllEntries();
    }


    public JournalDetailLiveData getEntry() {
        return entry;
    }

    public void setEntry(String id) {
        this.entry = repository.getJournalEntryById(id);
    }

    public LiveData<List<JournalEntry>> getListEntries() {
        return listEntries;
    }

    public void updateSelected() {
        repository.updateJournalEntry(entry.getValue());
    }

    public void createEntry() {
        JournalEntry e = new JournalEntry();
        e.setDate(new Date());
        entry = repository.addJournalEntry(e);
    }

    public void removeEntry() {
        repository.removeJournalEntry(entry.getValue().getId());
    }
}
