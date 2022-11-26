package com.example.foodapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity
{
    private int Tab=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //TAKES AWAY TOP BAR
        try
        {
            this.getSupportActionBar().hide();
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        final LinearLayout homeLayout=findViewById(R.id.homeLayout);
        final LinearLayout FavLayout=findViewById(R.id.FavLayout);
        final LinearLayout RecipeLayout=findViewById(R.id.recipeLayout);
        final LinearLayout ProfileLayout=findViewById(R.id.profileLayout);




        final ImageView homeImage=findViewById(R.id.homeImage);
        final ImageView FavImage=findViewById(R.id.FavImage);
        final ImageView RecipeImage=findViewById(R.id.recipeImage);
        final ImageView ProfileImage=findViewById(R.id.profileImage);

        final TextView homeText=findViewById(R.id.homeText);
        final TextView FavText=findViewById(R.id.FavText);
        final TextView RecipeText=findViewById(R.id.recipeText);
        final TextView ProfileText=findViewById(R.id.profileText);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer,HomeFragment.class,null)
                .commit();

        // in clicklistener check if home or not
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Tab !=1){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,HomeFragment.class,null)
                            .commit();
                    //select other tabs except the home tab if the tabs are not 1
                    FavText.setVisibility(View.GONE);
                    RecipeText.setVisibility(View.GONE);
                    ProfileText.setVisibility(View.GONE);

                    FavImage.setImageResource(R.drawable.scanning_black);
                    RecipeImage.setImageResource(R.drawable.recipe_black);
                    ProfileImage.setImageResource(R.drawable.profile_black);

                    FavLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    RecipeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ProfileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //select home tab
                    homeText.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.home);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    //Creation of the animation effect
                    ScaleAnimation scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    //set tab as default
                    Tab=1;

                }
            }
        });
        FavLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Tab !=2){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,FavoriteFragment.class,null)
                            .commit();

                    //select other tabs except the fave tab if the tabs are not 1
                    homeText.setVisibility(View.GONE);
                    RecipeText.setVisibility(View.GONE);
                    ProfileText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home_black);
                    RecipeImage.setImageResource(R.drawable.recipe_black);
                    ProfileImage.setImageResource(R.drawable.profile_black);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    RecipeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ProfileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //select home tab
                    FavText.setVisibility(View.VISIBLE);
                    FavImage.setImageResource(R.drawable.scanning);
                    FavLayout.setBackgroundResource(R.drawable.round_back_fave_100);

                    //Creation of the animation effect
                    ScaleAnimation scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    FavLayout.startAnimation(scaleAnimation);

                    //set tab as default
                    Tab=2;

                }

            }
        });

        RecipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Tab !=3){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,RecipeFragment.class,null)
                            .commit();
                    //select other tabs except the fave tab if the tabs are not 1
                    homeText.setVisibility(View.GONE);
                    FavText.setVisibility(View.GONE);
                    ProfileText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home_black);
                    FavImage.setImageResource(R.drawable.scanning_black);
                    ProfileImage.setImageResource(R.drawable.profile_black);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    FavLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    ProfileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //select home tab
                    RecipeText.setVisibility(View.VISIBLE);
                    RecipeImage.setImageResource(R.drawable.recipe);
                    RecipeLayout.setBackgroundResource(R.drawable.round_back_recipe_100);

                    //Creation of the animation effect
                    ScaleAnimation scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    RecipeLayout.startAnimation(scaleAnimation);

                    //set tab as default
                    Tab=3;

                }

            }
        });

        ProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Tab !=4){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,ProfileFragment.class,null)
                            .commit();
                    //select other tabs except the fave tab if the tabs are not 1
                    homeText.setVisibility(View.GONE);
                    FavText.setVisibility(View.GONE);
                    RecipeText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home_black);
                    FavImage.setImageResource(R.drawable.scanning_black);
                    RecipeImage.setImageResource(R.drawable.recipe_black);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    FavLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    RecipeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //select home tab
                    ProfileText.setVisibility(View.VISIBLE);
                    ProfileImage.setImageResource(R.drawable.profile);
                    ProfileLayout.setBackgroundResource(R.drawable.round_back_profile_100);

                    //Creation of the animation effect
                    ScaleAnimation scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    ProfileLayout.startAnimation(scaleAnimation);

                    //set tab as default
                    Tab=4;

                }

            }
        });


    }
}
