package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPageActivity extends AppCompatActivity
{
    private Button lpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        //TAKES AWAY TOP BAR
        try
        {
            this.getSupportActionBar().hide();
        }
        catch(Exception e){}

        //CODE TO SIGNUP BUTTON LINK TO NEW USER
        lpButton = findViewById(R.id.login_landing_button);
        lpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openTransitionActivity();
            }
        });

    }

    //FUNCTION TO LINK TO NEW USER ACTIVITY
    public void openTransitionActivity()
    {
        Intent intent = new Intent(this, Transition.class);
        startActivity(intent);
    }
}