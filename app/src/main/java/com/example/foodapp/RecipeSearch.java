package com.example.foodapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeSearch {
    public ArrayList<Recipe> recipes;
    public HashMap<String, ArrayList<Recipe>> recipeHashMap;
    public Context context;

    public RecipeSearch(ArrayList<Recipe> recipes, Context context){
        this.context = context;
        this.recipes = recipes;
        this.recipeHashMap = make_map(this.recipes);
    }

    private HashMap<String, ArrayList<Recipe>> make_map(ArrayList<Recipe> recipes){
        HashMap<String, ArrayList<Recipe>> temp = new HashMap<String, ArrayList<Recipe>>();
        ArrayList<String> listOfIngredients = new ArrayList<String>();

        for (Recipe r : recipes){
            if (!listOfIngredients.contains(r.mainIngredient)){
                listOfIngredients.add(r.mainIngredient);
            }
            if (!listOfIngredients.contains(r.secondaryIngredient)){
                listOfIngredients.add(r.secondaryIngredient);
            }
        }
        for(String i : listOfIngredients){
            ArrayList<Recipe> tempList = new ArrayList<Recipe>();
            for(Recipe r : recipes){
                if(i.equals(r.mainIngredient) || i.equals(r.secondaryIngredient)){
                    tempList.add(r);
                }
            }
            //System.out.println(i);
            //for(Recipe r : tempList){
            //    r.print_recipe();
            //}
            temp.put(i, tempList);
        }
        return temp;
    }

    public ArrayList<Recipe> search_recipe(String ingredient){
        return this.recipeHashMap.get(ingredient);
    }
    public Recipe search_recipe_title(String title){
        for (Recipe r : this.recipes){
            if (title.equals(r.recipeName)){
                return r;
            }
        }
        return null;
    }
    public void print_hash(){
        this.recipeHashMap.forEach((k, v) -> System.out.println(k + " " + v.get(0).recipeName));
    }
}