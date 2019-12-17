package com.example.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityMusic extends AppCompatActivity {
    private ArrayList<Ways> genre = new ArrayList<>();
    private ArrayList<WaysWLinks> rock = new ArrayList<>();
    private ArrayList<WaysWLinks>  pop = new ArrayList<>();

    Map<String, String> mapRock = new HashMap<String, String>() {{
        put("Звери", "https://music.yandex.ru/artist/156427");
        put("Placebo", "https://music.yandex.ru/artist/36830");
        put("Powerwolf", "https://music.yandex.ru/artist/1529749");
    }};

    Map<String, String> mapPop = new HashMap<String, String>() {{
        put("Lady Gaga", "https://music.yandex.ru/artist/1438");
        put("Adele", "https://music.yandex.ru/artist/37027");
    }};

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

        for (String name: mapRock.keySet()){
            String url = mapRock.get(name);
            rock.add(new WaysWLinks(name, url));
        }
        for (String name: mapPop.keySet()){
            String url = mapPop.get(name);
            pop.add(new WaysWLinks(name, url));
        }

//        rock.add(new WaysWLinks("Звери", "https://music.yandex.ru/artist/156427"));
//        rock.add(new WaysWLinks("Placebo", "https://music.yandex.ru/artist/36830"));
//        rock.add(new WaysWLinks("Powerwolf", "https://music.yandex.ru/artist/1529749"));
//
//        pop.add(new WaysWLinks("Lady Gaga", "https://music.yandex.ru/artist/1438"));
//        pop.add(new WaysWLinks("Adele", "https://music.yandex.ru/artist/37027"));

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
                mapRock.remove(rock.get(childPos).getText());
                rock.remove(childPos);
                list.upDateContentList("genre", rock, pop);
                adapter.notifyDataSetChanged();
            }
            else{
                mapPop.remove(pop.get(childPos).getText());
                pop.remove(childPos);
                list.upDateContentList("genre", rock, pop);
                adapter.notifyDataSetChanged();
            }
        }

        if (item.getItemId() == R.id.option_link){
            if (groupPos == 0) {
                WaysWLinks link = rock.get(childPos);
                String url = link.getLink();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
            else{
                WaysWLinks link = pop.get(childPos);
                String url = link.getLink();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        }
        return super.onContextItemSelected(item);
    }

    public void addMusic(View view){

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
                .setPositiveButton("Добавить в рок",
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
                                    mapRock.put(word, url);
                                    WaysWLinks add = new WaysWLinks(word, url);
                                    rock.add(rock.size(), add);

                                    list.upDateContentList("genre", rock, pop);
                                    adapter.notifyDataSetChanged();
                                }


                            }
                        })
                .setNegativeButton("Добавить в поп",
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
                                    mapPop.put(word, url);
                                    WaysWLinks add = new WaysWLinks(word, url);
                                    pop.add(pop.size(), add);

                                    list.upDateContentList("genre", rock, pop);
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
