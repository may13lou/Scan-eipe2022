package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity
{
    /* INITIALIZE */
    private Button signUpButtonMain;
    private Button loginButtonMain;
    private EditText usernameMain;
    private EditText passwordMain;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        /* MAIN PAGE SET-UP */

            //START UP DEFAULT -> DO NOT DELETE
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //TAKES AWAY TOP BAR
            try
            {
                this.getSupportActionBar().hide();
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            //ANIMATIONS WITH YoYo LIBRARY -> CHECK DEPENDENCIES
            YoYo.with(Techniques.Bounce)
                    .duration(1800)
                    .repeat(100)
                    .playOn(findViewById(R.id.signup_button));

        /* END OF MAIN PAGE SET-UP */



        /* VARIABLES AND INITIALIZATION (MUST BE AFTER PAGE SETUP) */
        
            usernameMain = findViewById(R.id.stringUserName);
            passwordMain= findViewById(R.id.stringPassword);
            loginButtonMain = findViewById(R.id.login_main_button);
            signUpButtonMain = findViewById(R.id.signup_button);

        /* END OF VARIABLES AND INITIALIZATION */



        /* LINKING TO PAGES */

            //CODE TO SIGNUP BUTTON LINK TO NEW USER
            signUpButtonMain.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    openNewUserActivity();
                }
            });

            //CODE TO LOGIN BUTTON TO GET INTO HOME SCREEN
            loginButtonMain.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    checkLogin(usernameMain.getText().toString(),passwordMain.getText().toString());
                }
            });
        /* END OF LINKING TO PAGES */
    }



    /* FUNCTIONS */
    //FUNCTION TO LINK TO NEW USER ACTIVITY
    public void openNewUserActivity()
    {
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }

    //FUNCTION TO LINK TO HOME SCREEN ACTIVITY  NOTE: FOR NOW FOR LINKING PURPOSES I LINKED IT TO LANDING PAGE
    public void openHomeActivity()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    //LOGIN VALIDATION FUNCTION
    public void checkLogin(String userName, String userPassword)
    {
        /* NOTE: user = "admin" and password = "testing" for login to work */
        if((userName.equals("admin")) && (userPassword.equals("testing")))
        {
            openHomeActivity();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Username or Password is incorrect. Please try again.",Toast.LENGTH_SHORT).show();
        }
    }

}