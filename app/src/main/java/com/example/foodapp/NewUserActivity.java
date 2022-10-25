package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.util.Patterns;
import android.widget.EditText;
import androidx.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewUserActivity extends AppCompatActivity
{
    private Button backLoginButton, createButton;
    private EditText inputUsername, inputPassword, inputEmail;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //START UP DEFAULT -> DO NOT DELETE
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        //TAKES AWAY TOP BAR
        try
        {
            this.getSupportActionBar().hide();
        }
        catch(Exception e){}

        //Initializing
        inputUsername = findViewById(R.id.newUserName);
        inputPassword = findViewById(R.id.newPassword);
        inputEmail = findViewById(R.id.newEmail);
        createButton = findViewById(R.id.create_button);

        //Instance of Firebase database
        mAuth = FirebaseAuth.getInstance();


        //CODE TO SEND DATA WITH CREATE BUTTON TO DATABASE
        createButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Getting Text from Fields
                String userName = inputUsername.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();

                createUser(userName,password,email);
            }
        });


        //CODE TO LINK TO MAIN ACTIVITY
        backLoginButton = (Button) findViewById(R.id.back_login_button);
        backLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openMainActivity();
            }
        });

    }

    //CODE TO LINK TO MAIN ACTIVITY
    public void openMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void createUser(String userName, String password, String email)
    {

        //USERNAME CHECK IF FIELD IS EMPTY
        if(userName.isEmpty())
        {
            inputUsername.setError("Please enter a username.");
            inputUsername.requestFocus();
            return;
        }

        //PASSWORD CHECK IF FIELD IS EMPTY
        if(password.isEmpty())
        {
            inputPassword.setError("Please enter a password.");
            inputPassword.requestFocus();
            return;
        }
        if(password.length()<8)
        {
            inputPassword.setError("Please enter a password more than 8 characters.");
            inputPassword.requestFocus();
            return;
        }

        //EMAIL CHECK IF FIELD IS EMPTY
        if(email.isEmpty())
        {
            inputEmail.setError("Please enter an email.");
            inputEmail.requestFocus();
            return;
        }
        if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) //does not match email pattern
        {
            inputEmail.setError("Please enter a VALID email.");
            inputEmail.requestFocus();
            return;
        }

        //CREATES DATABASE ENTRY AND AUTHENTICATION ENTRY
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    User user = new User(userName,password,email);
                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser()
                                    .getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(NewUserActivity.this, "User is registered! Redirecting back to login...", Toast.LENGTH_LONG).show();

                                //DELAY BACK TO LOGIN AFTER SUCCESSFUL DATABASE ENTRY
                                Handler delayHandler = new Handler();
                                delayHandler.postDelayed(NewUserActivity.this::openMainActivity,2300); //2.3 second wait
                            }
                            else
                            {
                                Toast.makeText(NewUserActivity.this, "Register has failed. Please try again.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(NewUserActivity.this, "Email has already been used. Please try again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}