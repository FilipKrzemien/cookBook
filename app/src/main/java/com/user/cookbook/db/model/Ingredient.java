package com.user.cookbook.db.model;

public class Ingredient {
    private int id;
    private int recipeID;
    private String name;
    private double amount;
    private String unit;

    public int getRecipeID() {
        return recipeID;
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

    public Ingredient(String name, int recipeID, double amount, String unit) {
        this.name = name;
        this.recipeID = recipeID;
        this.amount = amount;
        this.unit = unit;
    }
    public Ingredient(int recipeID, String name, double amount, String unit ,int id) {
        this.id = id;
        this.recipeID = recipeID;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

}
