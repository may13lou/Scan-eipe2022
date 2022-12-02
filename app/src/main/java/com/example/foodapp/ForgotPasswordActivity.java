package com.example.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPasswordActivity extends Activity
{
    //Initialize
    private EditText emailForgot;
    private Button submitButtonForgot;
    private Button exitButtonForgot;
    private ProgressBar progressBarForgot;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //START UP DEFAULT -> DO NOT DELETE
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //Pop-Up Window for LAYOUT
        /*
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        DisplayMetrics DM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(DM);
        int width = DM.widthPixels;
        int height = DM.heightPixels;
        getWindow().setLayout((int)(width*0.9),(int)(height*0.4));
         */

        //INITIALIZE
        emailForgot = findViewById(R.id.forgot_Email);
        submitButtonForgot = findViewById(R.id.forgot_SubmitButton);
        exitButtonForgot = findViewById(R.id.forgot_exit);
        progressBarForgot = findViewById(R.id.progressBar2);
        auth = FirebaseAuth.getInstance();
        progressBarForgot.setVisibility(View.INVISIBLE);

        //FORGOT PASSWORD
        submitButtonForgot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                resetPassword();
            }
        });

        //EXITS WINDOW
        submitButtonForgot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    private void resetPassword()
    {
        String inputEmail = emailForgot.getText().toString().trim();
        if(inputEmail.isEmpty())
        {
            emailForgot.setError("Email is empty. Type in Email!");
            emailForgot.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches())
        {
            emailForgot.setError("Email is not valid. Try again!");
            emailForgot.requestFocus();
            return;
        }
        progressBarForgot.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(inputEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBarForgot.setVisibility(View.INVISIBLE);
                if(task.isSuccessful())
                {
                    Toast.makeText(ForgotPasswordActivity.this, "Sent to Email! Please reset from there.", Toast.LENGTH_LONG).show();

                    //DELAY BACK TO LOGIN AFTER SUCCESSFUL CHANGE
                    Handler delayHandler = new Handler();
                    delayHandler.postDelayed(ForgotPasswordActivity.this::openHomeActivity,2300); //2.3 second wait
                }
                else
                {
                    Toast.makeText(ForgotPasswordActivity.this, "Please try again. Something has gone wrong.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //FUNCTION TO LINK TO HOME SCREEN ACTIVITY  NOTE: FOR NOW FOR LINKING PURPOSES I LINKED IT TO LANDING PAGE
    public void openHomeActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
