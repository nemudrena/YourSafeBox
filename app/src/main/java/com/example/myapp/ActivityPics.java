package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.io.OptionalDataException;
import java.util.Random;

public class ActivityPics extends AppCompatActivity {
    private ImageView imageView;
    int position = 0;
    private int[] mImageIds = { R.drawable.happy1, R.drawable.happy2,
            R.drawable.happy3, R.drawable.happy4, R.drawable.happy5 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_pics);

        imageView = findViewById(R.id.ivImage);
        imageView.setImageResource(mImageIds[0]);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRandomPosotion();
                imageView.setImageResource(mImageIds[position]);
            }
        });
    }

    private int getRandomPosotion() {
        Random rand = new Random();
        int max = mImageIds.length;
        int min = 0;
        position = rand.nextInt((max - min)) + min;
        return position;
    }
}
