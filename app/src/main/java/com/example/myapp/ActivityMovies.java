package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;

public class ActivityMovies extends AppCompatActivity {
    private ArrayList<Ways> genre = new ArrayList<>();
    private ArrayList<WaysWLinks> comedy = new ArrayList<>();
    private ArrayList<WaysWLinks>  love = new ArrayList<>();

    private int groupPos;
    private int childPos;
    private SimpleExpandableListAdapter adapter;
    private ListOfWays list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        genre.add(new Ways("Комедии"));
        genre.add(new Ways("Лирические"));

        comedy.add(new WaysWLinks("Титаник", "https://www.kinopoisk.ru/film/2213/"));
        comedy.add(new WaysWLinks("Хатико", "https://www.kinopoisk.ru/film/387556/"));
        comedy.add(new WaysWLinks("Реквием по мечте", "https://www.kinopoisk.ru/film/367/"));

        love.add(new WaysWLinks("Горбатая гора", "https://www.kinopoisk.ru/film/77647/"));
        love.add(new WaysWLinks("Одинокий мужчина", "https://www.kinopoisk.ru/film/430593/"));

        list = new ListOfWays(genre, comedy, love);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewMov);
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

                comedy.remove(childPos);
                list.upDateContentList("genre", comedy, love);
                adapter.notifyDataSetChanged();
            }
            else{
                love.remove(childPos);
                list.upDateContentList("genre", comedy, love);
                adapter.notifyDataSetChanged();
            }
        }
        else if (item.getItemId() == R.id.option_link){

        }

        if(item.getItemId() == R.id.option_link){
            if (groupPos == 0) {
                WaysWLinks link = comedy.get(childPos);
                String url = link.getLink();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);

            }
            else{
                WaysWLinks link = love.get(childPos);
                String url = link.getLink();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        }

        return super.onContextItemSelected(item);
    }
}
