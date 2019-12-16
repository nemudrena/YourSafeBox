package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;

public class ActivitySence extends AppCompatActivity {
    private ArrayList<Ways> groups = new ArrayList<>();
    private ArrayList<Ways> computer = new ArrayList<>();
    private ArrayList<Ways> hand = new ArrayList<>();

    private int groupPos;
    private int childPos;
    private SimpleExpandableListAdapter adapter;
    private ListOfWays list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sence);

        groups.add(new Ways("Компьютерные"));
        groups.add(new Ways("Руками"));

        computer.add(new Ways("Ведьмак"));
        computer.add(new Ways("Тетрис"));

        hand.add(new Ways("Обними медведя"));
        hand.add(new Ways("Деградни и покрути спиннер"));

        list = new ListOfWays(groups, computer, hand);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewSc);
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

                computer.remove(childPos);
                list.upDateContentList("genre", computer, hand);
                adapter.notifyDataSetChanged();
            }
            else{
                hand.remove(childPos);
                list.upDateContentList("genre", computer, hand);
                adapter.notifyDataSetChanged();
            }
        }

        return super.onContextItemSelected(item);
    }
}
