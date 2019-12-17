package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

        expandableListView.setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {
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

        getMenuInflater().inflate(R.menu.option_menu1, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.option_del1){
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
    public void addAroma(View view){
        final Context context = this;
        //Получаем вид с файла adding.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(context);
        final View promptsView = li.inflate(R.layout.adding_without_links, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

        //Настраиваем adding.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText wordInput = promptsView.findViewById(R.id.input_text);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Добавить в Сладкие",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                final String word = wordInput.getText().toString();

                                if (word.isEmpty()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setTitle("Error");
                                    builder.setMessage("Fill all fields");
                                    builder.setPositiveButton(
                                            android.R.string.ok,
                                            new DialogInterface.OnClickListener() { // Кнопка ОК
                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog, int which) {
                                                    dialog.dismiss(); // Отпускает диалоговое окно
                                                }
                                            });
                                    AlertDialog dialo = builder.create();
                                    dialo.show();
                                }
                                else{
                                    Ways add = new Ways(word);
                                    sweet.add(sweet.size(), add);

                                    list.upDateContentList("genre", sweet, flower, 0);
                                    adapter.notifyDataSetChanged();
                                }


                            }
                        })
                .setNegativeButton("Добавить в Цветы",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                final String word = wordInput.getText().toString();
                                if (word.isEmpty()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setTitle("Error");
                                    builder.setMessage("Fill all fields");
                                    builder.setPositiveButton(
                                            android.R.string.ok,
                                            new DialogInterface.OnClickListener() { // Кнопка ОК
                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog, int which) {
                                                    dialog.dismiss(); // Отпускает диалоговое окно
                                                }
                                            });
                                    AlertDialog dialo = builder.create();
                                    dialo.show();
                                }
                                else {
                                    Ways add = new Ways(word);
                                    flower.add(flower.size(), add);

                                    list.upDateContentList("genre", sweet, flower, 0);
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
