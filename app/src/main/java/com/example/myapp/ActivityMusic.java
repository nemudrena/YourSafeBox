package com.example.myapp;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityMusic extends AppCompatActivity {
    private ArrayList<Ways> genre = new ArrayList<>();
    private ArrayList<Ways> rock = new ArrayList<>();
    private ArrayList<Ways>  pop = new ArrayList<>();

    private int groupPos;
    private int childPos;
    private SimpleExpandableListAdapter adapter;
    private ListOfWays list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        genre.add(new Ways("Rock"));
        genre.add(new Ways("Pop"));

        rock.add(new Ways("Звери"));
        rock.add(new Ways("Placebo"));
        rock.add(new Ways("Powerwolf"));

        pop.add(new Ways("Lady Gaga"));
        pop.add(new Ways("Adele"));

        list = new ListOfWays(genre, rock, pop);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        final ExpandableListView expandableListView = findViewById(R.id.expListViewMus);
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

        //есть надежда что из этого получится сделать долгое нажатие
        /*
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                v.showContextMenu();
                return false;
            }
        }); */

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

                rock.remove(childPos);
                list.upDateContentList("genre", rock, pop);
                adapter.notifyDataSetChanged();
            }
            else{
                pop.remove(childPos);
                list.upDateContentList("genre", rock, pop);
                adapter.notifyDataSetChanged();
            }
        }

        return super.onContextItemSelected(item);
    }
}
