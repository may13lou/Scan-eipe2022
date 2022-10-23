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
        String[] splitContent = content.split("\n");
        return splitContent;
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
        searchBar = new RecipeSearch(recipes, requireContext());
        searchBar.print_hash();
        System.out.println(searchBar.recipeHashMap.get("vegetable"));

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