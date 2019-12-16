package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
    private String fullScreenInd;
    int position = 0;
    private int[] mImageIds = { R.drawable.happy1, R.drawable.happy2,
            R.drawable.happy3, R.drawable.happy4, R.drawable.happy5, R.drawable.happy6,
            R.drawable.happy7, R.drawable.happy8, R.drawable.happy9, R.drawable.happy10,
            R.drawable.happy11, R.drawable.happy12, R.drawable.happy13, R.drawable.happy14,
            R.drawable.happy15 };

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
        int result = rand.nextInt((max - min)) + min;
        if (result == position)
            getRandomPosotion();
        else {
            position = result;
        }
        return position;
    }

}
