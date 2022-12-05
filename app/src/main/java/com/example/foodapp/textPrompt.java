package com.example.foodapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class textPrompt extends Activity {

    private EditText ingredientPrompt;
    private Button submitButton;
    private Recipe rClass;
    private ArrayList<Recipe> arrayList;
    private Button exitButton;
    private ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        recipeList = (ArrayList<Recipe>) bundle.get("recipeList");

        System.out.println("Map in fragment: "+recipeList.toString());
        /*
        setContentView(R.layout.activity_text_prompt);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        DisplayMetrics DM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(DM);
        int width = DM.widthPixels;
        int height = DM.heightPixels;
        getWindow().setLayout((int)(width*0.9),(int)(height*0.735));

        submitButton = (Button) findViewById(R.id.ingredientPromptSubmitButton);
        exitButton = (Button) findViewById(R.id.ingredientPromptBoxExitButton);
        ingredientPrompt = (EditText) findViewById(R.id.ingredientPromptTextBox);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = ingredientPrompt.getText().toString();

                System.out.println(inputText);
            }
        });
        */
    }
}