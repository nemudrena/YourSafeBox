package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class ActivityTaste extends AppCompatActivity {
    private Ways[] groups = new Ways[] { new Ways("Сладкое"), new Ways("Соленое") };
    private Ways[] sweet = new Ways[] { new Ways("Шоколад"), new Ways("Зефирка") };
    private Ways[] salt = new Ways[] {
            new Ways("Твои слезы при виде списка билетов по матану"),
            new Ways("***") };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taste);

        ListOfWays list = new ListOfWays(groups, sweet, salt);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewTas);
        expandableListView.setAdapter(adapter);
    }
}
