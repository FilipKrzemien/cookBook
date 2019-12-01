package com.user.cookbook.db.model;

import java.io.Serializable;

public class Step implements Serializable {
    private long recipeId;

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    private int stepNumber;
    private String description;

    public long getRecipeId() {
        return recipeId;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public Step(long recipe_id, int stepNumber, String description) {
        this.recipeId = recipe_id;
        this.stepNumber = stepNumber;
        this.description = description;
    }

    public Step(int stepNumber, String description) {
        this.stepNumber = stepNumber;
        this.description = description;
    }
}
