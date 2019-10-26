package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainwindow);
    }

    public void to_window_ways(View view){
        Intent intent = new Intent(this, ActivityWays.class);
        startActivity(intent);
    }

    public void to_window_pics(View view){
        Intent intent = new Intent(this, ActivityPics.class);
        startActivity(intent);
    }
}
