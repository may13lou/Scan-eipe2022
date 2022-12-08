package com.example.foodapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


public class ReceipePop extends Activity
{
    //Initialize
    public TextView popupInstructions;
    public TextView title;
    private Recipe rClass;
    private String[] recipeText;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //START UP DEFAULT -> DO NOT DELETE
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String RecipeType = bundle.getString("RecipeType");

        //SWTICH FOR RECIPES
        switch(RecipeType)
        {
            case "Beef Wellington":
                setContentView(R.layout.receipe_popup_beefwellington);
                break;

            case "Bell Pepper Keto Nachos":
                setContentView(R.layout.receipe_popup_bell);
                break;

            case "Vegetable Stir Fry":
                setContentView(R.layout.receipe_popup_stirfry);
                break;

            case "Camera":
                setContentView(R.layout.camera_comingsoon);
                break;

            default:
                setContentView(R.layout.receipe_comingsoon);
                break;

        }
        //setContentView(R.layout.receipe_popup_beefwellington);
        //rClass = new Recipe(this.recipeText);
        //rClass.getInfoFromRecipe();

        //Pop-Up Window for LAYOUT
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        DisplayMetrics DM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(DM);
        int width = DM.widthPixels;
        int height = DM.heightPixels;
        getWindow().setLayout((int)(width*0.9),(int)(height*0.735));

        //INITIALIZE
        popupInstructions = findViewById(R.id.popup_instructions);
        title = findViewById(R.id.popup_receipe_title);

    }

    public String getInfoFromRecipe()
    {
        return rClass.getInfoFromRecipe();
    }



}
