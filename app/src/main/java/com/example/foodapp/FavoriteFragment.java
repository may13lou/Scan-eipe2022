package com.example.foodapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //VARIABLES
    FrameLayout constraintLayout;
    AnimationDrawable animationDrawable;
    Button cameraButton;
    Button scanTextButton;
    RelativeLayout relativeLayout;
    int Camera = 0;
    private ArrayList<Recipe> recipeList;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public String[] parse_ingredient_list(String ingredients){
        String[] ingredientList;
        if(ingredients.indexOf(", ") != -1){
            ingredientList = ingredients.split(", ");
        }
        else if(ingredients.indexOf("\n") != -1) {
            ingredientList = ingredients.split("\n");
        }
        else{
            ingredientList = ingredients.split(" ");
        }

        for(int i = 0; i < ingredientList.length; i++){
            if(ingredientList[i].contains("-")){
                int index = ingredientList[i].indexOf("-");
                char[] ingredientsCharArray = ingredientList[i].toCharArray();
                ingredientsCharArray[index] = ' ';
                String newName = String.valueOf(ingredientsCharArray);
                ingredientList[i] = newName;
            }

            ingredientList[i] = ingredientList[i].toLowerCase();
        }


        return  ingredientList;
    }

    public int sum(ArrayList<Integer> numList){
        int sum = 0;
        for (int i : numList){
            sum += i;
        }
        return sum;
    }

    public Recipe classify_ingredients(String[] ingredientList, ArrayList<Recipe> recipeList){
        Recipe result;
        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (Recipe recipe : recipeList){
            ArrayList<Integer> numList = new ArrayList<Integer>();
            for (String ingredient : ingredientList){
                for(Ingredient ing : recipe.ingredientList){
                    if(ingredient.equals(ing.name)){
                        numList.add(1);
                    }
                    else{
                        numList.add(0);
                    }
                }
            }
            scores.add(sum(numList));
        }

        int max = Collections.max(scores);
        int argmax = scores.indexOf(max);

        result = recipeList.get(argmax);

        return result;
    }

    public ArrayList<Ingredient> missing_ingredients(String[] ingredientList, Recipe result){
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();
        missingIngredients.addAll(result.ingredientList);
        ArrayList<Ingredient> haveIngredients = new ArrayList<>();

        for(String ingredient : ingredientList){
            for(Ingredient ing : result.ingredientList){
                if(ingredient.equals(ing.name)){
                    haveIngredients.add(ing);
                    break;
                }
            }
        }
        for(Ingredient ing : haveIngredients){
            missingIngredients.remove(ing);
        }

        return missingIngredients;
    }

    public void promptDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getContext());
        final EditText editText = new EditText(this.getContext());
        alert.setMessage("Enter your ingredients:");
        alert.setTitle("TEXT-TO-INGREDIENTS");

        editText.setLines(10);
        editText.setMaxLines(20);
        editText.setGravity(Gravity.LEFT | Gravity.TOP);
        editText.setPadding(70,10,10,70);


        alert.setView(editText);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
                dialog.dismiss();
            }
        });

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                //OR
                String ingredients = editText.getText().toString();
                missingIngredientsDialog(ingredients);

            }
        });

        alert.show();
    }
    public void missingIngredientsDialog(String ingredients){
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getContext());
        final TextView missingIngredientView = new TextView(this.getContext());
        String[] ingredientList = parse_ingredient_list(ingredients);
        Recipe result = classify_ingredients(ingredientList, recipeList);
        ArrayList<Ingredient> missingIngredients = missing_ingredients(ingredientList, result);

        alert.setTitle("BEST RECIPE");
        alert.setMessage("Closest Recipe to your ingredients: " + result.getInfoFromRecipe());

        String missingIngredientsText = "";

        for (Ingredient ingredient : missingIngredients){
            missingIngredientsText = " " + missingIngredientsText + ingredient.name + "\n\t\t\t\t\t ";
        }

        System.out.println(missingIngredientsText);


        missingIngredientView.setLines(missingIngredients.size());
        missingIngredientView.setText(missingIngredientsText);

        alert.setView(missingIngredientView);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
                dialog.dismiss();
            }
        });

        alert.setPositiveButton("I want to cook!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                //OR

                openIngredientPopUp(result.getInfoFromRecipe());
            }
        });

        alert.show();
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_favorite, container, false);
        recipeList = new ArrayList<>();
        Bundle b = this.getArguments();
        if(b.getSerializable("recipeList") != null)
            recipeList = (ArrayList<Recipe>)b.getSerializable("recipeList");
        // Inflate the layout for this fragment
        System.out.println("Map in fragment: "+recipeList.toString());

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {

        //ASSIGNMENTS
        //constraintLayout = view.findViewById(R.id.Favorite);
        //animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        scanTextButton = (Button) view.findViewById(R.id.textToScan);
        cameraButton = (Button) view.findViewById(R.id.camera);
        //relativeLayout = getActivity().findViewBy




        //CHANGE VALUES FOR BACKGROUND
        //animationDrawable.setEnterFadeDuration(2500);
        //animationDrawable.setExitFadeDuration(5000);
        //animationDrawable.start();


        scanTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openIngredientPrompt();
                promptDialog();
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIngredientPopUp("Camera");
            }
        });
    }

    public void openIngredientPopUp(String RecipeName)
    {
        Intent intent = new Intent(getActivity(), ReceipePop.class);
        intent.putExtra("RecipeType",RecipeName);
        startActivity(intent);
    }


}