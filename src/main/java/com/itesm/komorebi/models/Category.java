package com.itesm.komorebi.models;

import java.util.List;

public class Category {
    private String categoryName;
    private List<String> availableTags;

    public Category() {}

    public Category(String categoryName, List<String> availableTags) {
        this.categoryName = categoryName;
        this.availableTags = availableTags;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getAvailableTags() {
        return availableTags;
    }
    public void setAvailableTags(List<String> availableTags) {
        this.availableTags = availableTags;
    }
}
