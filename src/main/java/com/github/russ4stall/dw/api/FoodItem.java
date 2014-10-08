package com.github.russ4stall.dw.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * @author Russ Forstall
 */
public class FoodItem {
    private long id;
    private String title;
    private int calories;
    private Timestamp timestamp;

    public FoodItem() {
    }

    public FoodItem(long id, String title, int calories, Timestamp timestamp) {
        this.id = id;
        this.title = title;
        this.calories = calories;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", calories=" + calories +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @JsonProperty
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
