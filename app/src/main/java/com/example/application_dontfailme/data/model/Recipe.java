package com.example.application_dontfailme.data.model;

public class Recipe {
    private String id;
    private long duration;
    private String instructions;
    private String name;

    public Recipe() {}

    public Recipe(String id, long duration, String instructions, String name) {
        this.duration = duration;
        this.instructions = instructions;
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
