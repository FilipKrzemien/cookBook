package com.user.cookbook.db.model;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private long id;
    private long recipeID;
    private String name;
    private double amount;
    private String unit;

    public long getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(long recipeID) {
        this.recipeID = recipeID;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public Ingredient(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient(long recipeID, String name, double amount, String unit, long id) {
        this.id = id;
        this.recipeID = recipeID;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

}
