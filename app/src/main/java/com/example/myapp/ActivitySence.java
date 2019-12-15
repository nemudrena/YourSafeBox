package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class ActivitySence extends AppCompatActivity {
    private Ways[] groups = new Ways[] { new Ways("Компьютерные"),
            new Ways("Руками") };
    private Ways[] computer = new Ways[] { new Ways("Ведьмак"), new Ways("Тетрис")};
    private Ways[] hand = new Ways[] { new Ways("Обними медведя"),
            new Ways("Деградни и покрути спиннер") };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sence);

        ListOfWays list = new ListOfWays(groups, computer, hand);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewSc);
        expandableListView.setAdapter(adapter);
    }
}
