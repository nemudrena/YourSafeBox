package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;

public class ActivityAroma extends AppCompatActivity {
    private ArrayList<Ways> smells = new ArrayList<>();
    private ArrayList<Ways> sweet = new ArrayList<>();
    private ArrayList<Ways>  flower = new ArrayList<>();

    private int groupPos;
    private int childPos;
    private SimpleExpandableListAdapter adapter;
    private ListOfWays list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aroma);

        smells.add(new Ways("Сладкие"));
        smells.add(new Ways("Цветы"));

        sweet.add(new Ways("Корица"));
        sweet.add(new Ways("Ваниль"));

        flower.add(new Ways("Роза"));
        flower.add(new Ways("Лаванда"));

        list = new ListOfWays(smells, sweet, flower, 0);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewAr);
        expandableListView.setAdapter(adapter);

        registerForContextMenu(expandableListView);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                groupPos = groupPosition;
                childPos = childPosition;
                v.showContextMenu();
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.option_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.option_del){
            if (groupPos == 0) {

                sweet.remove(childPos);
                list.upDateContentList("genre", sweet, flower, 0);
                adapter.notifyDataSetChanged();
            }
            else{
                flower.remove(childPos);
                list.upDateContentList("genre", sweet, flower, 0);
                adapter.notifyDataSetChanged();
            }
        }

        return super.onContextItemSelected(item);
    }
}
