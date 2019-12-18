package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;

public class ActivityTaste extends AppCompatActivity {
    private ArrayList<Ways> groups = new ArrayList<>();
    private ArrayList<Ways> sweet = new ArrayList<>();
    private ArrayList<Ways> salt = new ArrayList<>();

    private int groupPos;
    private int childPos;
    private SimpleExpandableListAdapter adapter;
    private ListOfWays list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taste);

        groups.add(new Ways("Сладкое"));
        groups.add(new Ways("Соленое"));

        sweet.add(new Ways("Шоколадка"));
        sweet.add(new Ways("Зефирка"));

        salt.add(new Ways("Твои слезы при виде списка билетов по матану"));
        salt.add(new Ways("***"));

        list = new ListOfWays(groups, sweet, salt, 0);

        String[] groupFrom = new String[]{"groupName"};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{"genre"};
        int[] childTo = new int[]{android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                this, list.getTitleList(),
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, list.getData(), android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListViewTas);
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
                list.upDateContentList("genre", sweet, salt, 0);
                adapter.notifyDataSetChanged();
            }
            else{
                salt.remove(childPos);
                list.upDateContentList("genre", sweet, salt, 0);
                adapter.notifyDataSetChanged();
            }
        }

        return super.onContextItemSelected(item);
    }

    public void addTaste(View view){
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
                .setCancelable(true)
                .setPositiveButton("Добавить в Сладкое",
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
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss(); // Отпускает диалоговое окно
                                                }
                                            });
                                    AlertDialog dialo = builder.create();
                                    dialo.show();
                                }
                                else{
                                    Ways add = new Ways(word);
                                    sweet.add(sweet.size(), add);

                                    list.upDateContentList("genre", sweet, salt, 0);
                                    adapter.notifyDataSetChanged();
                                }


                            }
                        })
                .setNegativeButton("Добавить в Соленое",
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
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss(); // Отпускает диалоговое окно
                                                }
                                            });
                                    AlertDialog dialo = builder.create();
                                    dialo.show();
                                }
                                else {
                                    Ways add = new Ways(word);
                                    salt.add(salt.size(), add);

                                    list.upDateContentList("genre", sweet, salt, 0);
                                    adapter.notifyDataSetChanged();
                                }

                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();

        Button buttonNeg = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button buttonPos = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);

        if(buttonNeg != null) {
            buttonNeg.setTextColor(Color.parseColor("#000000"));
        }
        if(buttonPos != null) {
            buttonPos.setTextColor(Color.parseColor("#000000"));
        }
    }
}
