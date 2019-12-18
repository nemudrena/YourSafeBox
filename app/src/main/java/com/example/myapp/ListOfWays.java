package com.example.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListOfWays {
    private Map<String, String> map;
    private ArrayList<Map<String, String>> titleDataList;
    private ArrayList<ArrayList<Map<String, String>>> expListDetail;

    //конструктор формирующий лист для контента и лист для заголовков для ways
    public ListOfWays(ArrayList<Ways> titles, ArrayList<Ways> one, ArrayList<Ways> two, int i){

        titleDataList = formArrayList("groupName", titles, i);

        expListDetail = formContentList("genre", one, two, i);

    }
    //конструктор формирующий лист для контента и лист для заголовков для waysWlinks
    public ListOfWays(
            ArrayList<Ways> titles, ArrayList<WaysWLinks> one, ArrayList<WaysWLinks> two){
        titleDataList = formArrayList("groupName", titles, 0);

        expListDetail = formContentList("genre", one, two);

    }

    //функция формирующая список заголовков для Ways
    public ArrayList<Map<String, String>> formArrayList(String key, ArrayList<Ways> way, int i){
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        for (Ways item : way) {
            map = new HashMap<>();
            map.put(key, item.getText());
            dataList.add(map);
        }
        return dataList;
    }

    //функция формирующая список заголовков для waysWLinks
    public ArrayList<Map<String, String>> formArrayList(String key, ArrayList<WaysWLinks> wayWLinks)
    {
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        for (WaysWLinks item : wayWLinks) {
            map = new HashMap<>();
            map.put(key, item.getText());
            dataList.add(map);
        }

        return dataList;
    }

    //функция формирующая список контента для ways
    public ArrayList<ArrayList<Map<String, String>>>
    formContentList(String key, ArrayList<Ways> one, ArrayList<Ways> two, int i){

        ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();

        ArrayList<Map<String, String>> childDataItemList = formArrayList(key, one, i);
        childDataList.add(childDataItemList);

        childDataItemList = formArrayList(key, two, i);
        childDataList.add(childDataItemList);


        return childDataList;
    }

    //функция формирующая список контента для waysWLinks
    public ArrayList<ArrayList<Map<String, String>>>
    formContentList(String key, ArrayList<WaysWLinks> one, ArrayList<WaysWLinks> two){

        ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();

        ArrayList<Map<String, String>> childDataItemList = formArrayList(key, one);
        childDataList.add(childDataItemList);

        childDataItemList = formArrayList(key, two);
        childDataList.add(childDataItemList);


        return childDataList;
    }

    //функция обновляющая список контента для ways
    public void upDateContentList(String key, ArrayList<Ways> one, ArrayList<Ways> two, int i){
        expListDetail.clear();

        ArrayList<Map<String, String>> childDataItemList = formArrayList(key, one, i);
        expListDetail.add(childDataItemList);

        childDataItemList = formArrayList(key, two, i);
        expListDetail.add(childDataItemList);

    }

    //функция обновляющая список контента для waysWLinks
    public void upDateContentList(String key, ArrayList<WaysWLinks> one, ArrayList<WaysWLinks> two){
        expListDetail.clear();

        ArrayList<Map<String, String>> childDataItemList = formArrayList(key, one);
        expListDetail.add(childDataItemList);

        childDataItemList = formArrayList(key, two);
        expListDetail.add(childDataItemList);

    }



    public ArrayList<Map<String, String>> getTitleList(){
        return titleDataList;
    }

    public ArrayList<ArrayList<Map<String, String>>> getData(){
        return expListDetail;
    }

}
