package com.example.foodapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Console;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<String[]> recipeTexts = new ArrayList<String[]>();
    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    private RecipeSearch searchBar;
    private ArrayList<Recipe> resultOfSearch;
    private HashMap<String, ArrayList<Recipe>> searchMap;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecipeFragment() {
        // Required empty public constructor
    }

    //loads in the text for the recipes given a path name
    //needs to improve
    public String[] loadTextFileFromAssets(String pathName){
        String content = null;
        AssetManager manager = getContext().getAssets();
        try {

            InputStream is = manager.open(pathName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            content = new String(buffer,"UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        String[] splitContent = content.split("\r\n");
        return splitContent;
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
        //System.out.println(temp);
        return temp;
    }

    public ArrayList<Recipe> search_recipe(String ingredient){
        return this.searchMap.get(ingredient);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(String param1, String param2) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeTexts.add(loadTextFileFromAssets("beefWellington.txt"));
        recipeTexts.add(loadTextFileFromAssets("vegetableStirFry.txt"));
        recipeTexts.add(loadTextFileFromAssets("bellPepperKetoNachos.txt"));

        for(String[] recipe : recipeTexts){
            recipes.add(new Recipe(recipe));
        }
        searchMap = make_map(recipes);
        //System.out.println(searchMap);
        //for(String key : searchMap.keySet()){
          //  System.out.println(key);
            //System.out.println(searchMap.get(key).toString());
        //}
        //System.out.println(searchMap.get("beef\r"));
        //System.out.println(search_recipe("beef"));



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }
}