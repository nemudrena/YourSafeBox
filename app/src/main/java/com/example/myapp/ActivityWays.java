package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityWays extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_ways);
    }
    public void to_window_music(View view){
        Intent intent = new Intent(this, ActivityMusic.class);
        startActivity(intent);
    }
    public void to_window_movies(View view){
        Intent intent = new Intent(this, ActivityMovies.class);
        startActivity(intent);
    }
    public void to_window_places(View view){
        Intent intent = new Intent(this, ActivityPlaces.class);
        startActivity(intent);
    }
    public void to_window_taste(View view){
        Intent intent = new Intent(this, ActivityTaste.class);
        startActivity(intent);
    }
    public void to_window_aroma(View view){
        Intent intent = new Intent(this, ActivityAroma.class);
        startActivity(intent);
    }
    public void to_window_sence(View view){
        Intent intent = new Intent(this, ActivitySence.class);
        startActivity(intent);
    }


}