package com.example.foodapp;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Recipe{
    public String recipeName;
    public String mainIngredient;
    public String secondaryIngredient;
    public float cookingTime;
    public String servings;
    public float caloricValue;
    public String mealTime;
    public ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
    public ArrayList<String> instructions = new ArrayList<String>();
    private File recipeFile;
    private String imagePath;


    public Recipe(String recipePath, String imagePath){
        this.recipeFile = new File(recipePath);
        this.imagePath = imagePath;
        read_file(recipeFile);
    }

    private void read_file(File fileName){
        Scanner scan;
        ArrayList<String> lines = new ArrayList<String>();
        try{
            scan = new Scanner(fileName);
        }
        catch(Exception e){
            System.out.println("File not found... exiting");
            return;
        }
        while (scan.hasNextLine()){
            lines.add(scan.nextLine());
        }
        for (String l : lines){
            System.out.println("I am line: " + l);
            System.out.println(lines.size());
        }
        this.recipeName = lines.get(0);
        this.mainIngredient = lines.get(1);
        this.secondaryIngredient = lines.get(2);
        this.cookingTime = Float.parseFloat(lines.get(3));
        this.servings = lines.get(4);
        this.caloricValue = Float.parseFloat(lines.get(5));
        this.mealTime = lines.get(6);
        String[] ingredients = lines.get(7).split(",");
        for(String ingredient : ingredients){
            String[] parsed = ingredient.split(" ");
            ingredientList.add(new Ingredient(parsed[0], parsed[1], parsed[3], Float.parseFloat(parsed[2])));
        }
        for(int i = 8; i < lines.size(); i++){
            instructions.add(lines.get(i));
        }
        scan.close();
    }
    public void print_recipe(){
        System.out.println("TITLE: " + this.recipeName);
        System.out.println("MAIN INGREDIENT: " + this.mainIngredient);
        System.out.println("SECONDARY INGREDIENT: " + this.secondaryIngredient);
        System.out.println("COOKING TIME: " + this.cookingTime + " Hours");
        System.out.println("Servings: " + this.servings);
        System.out.println("CALORIC VALUE PER SERVING: " + this.caloricValue);
        System.out.println("MEAL TIMES: " + mealTime);
        System.out.println("INGREDIENTS:");
        for (Ingredient i : this.ingredientList){
            System.out.print(i.name);
            System.out.print(i.amount.amount);
            System.out.println(i.amount.type);
        }
        for(String l : instructions){
            System.out.println(l);
        }
    }
    public void add_ingredient(Ingredient i){
        ingredientList.add(i);
    }
    public void add_instruction(String line){
        instructions.add(line);
    }
    public String get_image_path(){
        return this.imagePath;
    }
}
