package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class ActivityMovies extends AppCompatActivity {
    private Ways[] genre = new Ways[] { new Ways("Комедии"),new Ways("Лирические")};
    private Ways[] comedy = new Ways[] { new Ways("Титаник"), new Ways("Хатико"),
            new Ways("Реквием по мечте")};
    private Ways[] love = new Ways[] { new Ways("Горбатая гора"),
            new Ways("Одинокий мужчина")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ListOfWays list = new ListOfWays(genre, comedy, love);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewMov);
        expandableListView.setAdapter(adapter);
    }
}
