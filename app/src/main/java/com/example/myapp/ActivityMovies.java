package com.example.myapp;


import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityMovies extends AppCompatActivity {

    private ArrayList<Ways> genre = new ArrayList<>();
    private ArrayList<WaysWLinks> comedy = new ArrayList<>();
    private ArrayList<WaysWLinks>  love = new ArrayList<>();

    Map<String, String> mapComedy = new HashMap<String, String>() {{
        put("Семейка Аддамс",  "https://www.kinopoisk.ru/film/5293/");
        put("Реквием по мечте", "https://www.kinopoisk.ru/film/367/");
        put("Одинокий мужчина", "https://www.kinopoisk.ru/film/430593/");
    }};

    Map<String, String> mapLove = new HashMap<String, String>() {{
        put("Горбатая гора", "https://www.kinopoisk.ru/film/77647/");
        put("Одинокий мужчина", "https://www.kinopoisk.ru/film/430593/");
    }};


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

        for (String name: mapComedy.keySet()){
            String url = mapComedy.get(name);
            comedy.add(new WaysWLinks(name, url));
        }
        for (String name: mapLove.keySet()){
            String url = mapLove.get(name);
            love.add(new WaysWLinks(name, url));
        }

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
    public void onCreateContextMenu(
            ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.option_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.option_del){
            if (groupPos == 0) {

                mapComedy.remove(comedy.get(childPos).getText());
                comedy.remove(childPos);

                list.upDateContentList("genre", comedy, love);
                adapter.notifyDataSetChanged();
            }
            else{
                mapLove.remove(love.get(childPos).getText());
                love.remove(childPos);
                list.upDateContentList("genre", comedy, love);
                adapter.notifyDataSetChanged();
            }
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

    public void add(View view){
        final Context context = this;
        //Получаем вид с файла adding.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(context);
        final View promptsView = li.inflate(R.layout.adding, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

        //Настраиваем adding.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText wordInput = promptsView.findViewById(R.id.input_text);
        final EditText urlInp = promptsView.findViewById(R.id.input_url);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Добавить в комедии",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                final String word = wordInput.getText().toString();
                                final String url = urlInp.getText().toString();
                                if (word.isEmpty()||url.isEmpty()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setTitle("Error");
                                    builder.setMessage("Fill all fields");
                                    builder.setPositiveButton(
                                            android.R.string.ok,
                                            new DialogInterface.OnClickListener() { // Кнопка ОК
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss(); // Отпускает диалоговое окно
                                        }
                                    });
                                    AlertDialog dialo = builder.create();
                                    dialo.show();
                                }
                                else{
                                    mapComedy.put(word, url);
                                    WaysWLinks add = new WaysWLinks(word, url);
                                    comedy.add(comedy.size(), add);

                                    list.upDateContentList("genre", comedy, love);
                                    adapter.notifyDataSetChanged();
                                }


                            }
                        })
                .setNegativeButton("Добавить в лирику",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                final String word = wordInput.getText().toString();
                                final String url = urlInp.getText().toString();
                                if (word.isEmpty()||url.isEmpty()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setTitle("Error");
                                    builder.setMessage("Fill all fields");
                                    builder.setPositiveButton(
                                            android.R.string.ok,
                                            new DialogInterface.OnClickListener() { // Кнопка ОК
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss(); // Отпускает диалоговое окно
                                        }
                                    });
                                    AlertDialog dialo = builder.create();
                                    dialo.show();
                                }
                                else {
                                    mapLove.put(word, url);
                                    WaysWLinks add = new WaysWLinks(word, url);
                                    love.add(love.size(), add);

                                    list.upDateContentList("genre", comedy, love);
                                    adapter.notifyDataSetChanged();
                                }

                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();
    }

}
