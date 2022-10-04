package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity
{
    //BUTTON LINKING TO CREATE LOGIN SCREEN
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //START UP DEFAULT -> DO NOT DELETE
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TAKES AWAY TOP BAR
        try
        {
            this.getSupportActionBar().hide();
        }
        catch(Exception e){};

        //CODE TO LINK TO NEW USER
        signUpButton = (Button) findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openNewUserActivity();
            }
        });

        //ANIMATIONS WITH YoYo LIBRARY -> CHECK DEPENDENCIES
        YoYo.with(Techniques.Bounce)
                .duration(1800)
                .repeat(100)
                .playOn(findViewById(R.id.signup_button));

    }

    //CODE TO LINK TO NEW USER
    public void openNewUserActivity()
    {
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }


}