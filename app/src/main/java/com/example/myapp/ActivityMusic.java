package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMusic extends AppCompatActivity {
    private Ways[] genre = new Ways[]{new Ways("Rock"), new Ways("Pop")};
    private Ways[] rock = new Ways[]{new Ways("Звери"), new Ways("Placebo"),
            new Ways("Powerwolf")};
    private Ways[] pop = new Ways[]{new Ways("Lady Gaga"), new Ways("Adele")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        final ListOfWays list = new ListOfWays(genre, rock, pop);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewMus);
        expandableListView.setAdapter(adapter);

    }
}
