package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class ActivityAroma extends AppCompatActivity {
    private Ways[] smells = new Ways[] { new Ways("Сладкие"), new Ways("Цветы") };
    private Ways[] sweet = new Ways[] { new Ways("Корица"), new Ways("Ваниль") };
    private Ways[] flower = new Ways[] { new Ways("Роза"), new Ways("Лаванда") };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aroma);

        ListOfWays list = new ListOfWays(smells, sweet, flower);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewAr);
        expandableListView.setAdapter(adapter);
    }
}
