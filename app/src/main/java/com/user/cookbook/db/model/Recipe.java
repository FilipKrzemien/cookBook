package com.user.cookbook.db.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private int id;
    private String name;
    private double time;
    private String difficulty;
    private ArrayList<Step> steps;
    private ArrayList<Ingredient> ingredients;

    public Recipe(String name, double time, String difficulty) {
        this.name = name;
        this.time = time;
        this.difficulty = difficulty;
    }

    public Recipe(String name, double time, String difficulty, int id) {
        this.name = name;
        this.time = time;
        this.difficulty = difficulty;
        this.id = id;
    }
    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public double getTime() {
        return time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
