package com.example.foodapp;

public class Ingredient {
    public String type;
    public String name;
    public Measurement amount;

    public Ingredient(String type, String name, String measurement, float amount){
        this.type = type;
        this.name = name;
        this.amount = new Measurement(measurement, amount);
    }
}
