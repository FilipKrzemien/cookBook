package com.user.cookbook.db.model;

public class Step {
    private int recipeId;
    private int stepNumber;
    private String description;

    public int getRecipeId() {
        return recipeId;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public Step(int recipe_id, int step_number, String description) {

        this.recipeId = recipe_id;
        this.stepNumber = step_number;
        this.description = description;
    }
}
