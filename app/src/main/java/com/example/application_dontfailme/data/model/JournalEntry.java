package com.example.application_dontfailme.data.model;


import java.util.Date;

public class JournalEntry {
    private String id;
    private String entry;
    private Date date;
    private String title;
    private float rating;

    public JournalEntry() {}

    public JournalEntry(String id, String entry, Date date, String title, float rating) {
        this.id = id;
        this.entry = entry;
        this.date = date;
        this.title = title;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
