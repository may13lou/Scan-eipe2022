package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Transition extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //TAKES AWAY TOP BAR
        try
        {
            this.getSupportActionBar().hide();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_logo);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(Transition.this,HomeActivity.class);
                intent.putExtra("hasDoneAnimation",true);
                startActivity(intent);
                finish();
            }
        },2100);
    }

}
