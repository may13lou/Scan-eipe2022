package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    /* INITIALIZE */
    private Button signUpButtonMain;
    private Button loginButtonMain;
    private EditText emailMain;
    private EditText passwordMain;

    private FirebaseAuth mAuth;

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
        
            emailMain = findViewById(R.id.stringEmail);
            passwordMain= findViewById(R.id.stringPassword);
            loginButtonMain = findViewById(R.id.login_main_button);
            signUpButtonMain = findViewById(R.id.signup_button);
            mAuth = FirebaseAuth.getInstance();


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
                    //Getting Text from Fields
                    String emailString = emailMain.getText().toString().trim();
                    String passwordString = passwordMain.getText().toString().trim();
 
                    validateLogin(emailString,passwordString);
                }
            });
        /* END OF LINKING TO PAGES */
    }



    /* FUNCTIONS */
    //LOGIN VALIDATION FUNCTION 2.0
    private void validateLogin(String email, String pass)
    {
        //CHECKS FOR EMPTY EMAIL FIELD AND VALID FORMAT
        if(email.isEmpty())
        {
            emailMain.setError("Please enter a email.");
            emailMain.requestFocus();
            return;
        }
        if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) //does not match email pattern
        {
            emailMain.setError("Please enter a VALID email.");
            emailMain.requestFocus();
            return;
        }

        //CHECKS FOR EMPTY PASSWORD FIELD
        if(pass.isEmpty())
        {
            passwordMain.setError("Please enter a password.");
            passwordMain.requestFocus();
            return;
        }

        //NOTE: CHECKS WITH DATABASE TO SEE IF LOGIN IS VALID
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    openHomeActivity();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Username or Password is incorrect. Please try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //FUNCTION TO LINK TO NEW USER ACTIVITY
    public void openNewUserActivity()
    {
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }

    //FUNCTION TO LINK TO HOME SCREEN ACTIVITY  NOTE: FOR NOW FOR LINKING PURPOSES I LINKED IT TO LANDING PAGE
    public void openHomeActivity()
    {
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
    }

}