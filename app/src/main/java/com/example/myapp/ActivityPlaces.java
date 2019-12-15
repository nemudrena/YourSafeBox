package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class ActivityPlaces extends AppCompatActivity {
    private Ways[] groups = new Ways[] { new Ways("Кладбища"), new Ways("МИЭМ") };
    private Ways[] cemetery = new Ways[] { new Ways("Ваганьковское"),
            new Ways("Новодевичье") };
    private Ways[] miem = new Ways[] { new Ways("МИЭМ") };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        ListOfWays list = new ListOfWays(groups, cemetery, miem);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewPl);
        expandableListView.setAdapter(adapter);
    }
}
