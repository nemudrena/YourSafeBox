package com.example.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListOfWays {
    private Map<String, String> map;
    private ArrayList<Map<String, String>> titleDataList;
    private ArrayList<ArrayList<Map<String, String>>> expListDetail;


    public ListOfWays(ArrayList<Ways> titles, ArrayList<Ways> one, ArrayList<Ways> two, int i){

        titleDataList = formArrayList("groupName", titles, 0);

        expListDetail = formContentList("genre", one, two, 0);

    }

    public ListOfWays(
            ArrayList<Ways> titles, ArrayList<WaysWLinks> one, ArrayList<WaysWLinks> two){
        titleDataList = formArrayList("groupName", titles, 0);

        expListDetail = formContentList("genre", one, two);

    }

    public ArrayList<Map<String, String>> formArrayList(String key, ArrayList<Ways> way, int i){
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        for (Ways item : way) {
            map = new HashMap<>();
            map.put(key, item.getText());
            dataList.add(map);
        }
        return dataList;
    }

    public ArrayList<Map<String, String>> formArrayList(String key, ArrayList<WaysWLinks> wayWLinks){
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        for (WaysWLinks item : wayWLinks) {
            map = new HashMap<>();
            map.put(key, item.getText());
            dataList.add(map);
        }

        return dataList;
    }

    public ArrayList<ArrayList<Map<String, String>>>
    formContentList(String key, ArrayList<Ways> one, ArrayList<Ways> two, int i){

        ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();

        ArrayList<Map<String, String>> childDataItemList = formArrayList(key, one, 0);
        childDataList.add(childDataItemList);

        childDataItemList = formArrayList(key, two, 0);
        childDataList.add(childDataItemList);


        return childDataList;
    }

    public ArrayList<ArrayList<Map<String, String>>>
    formContentList(String key, ArrayList<WaysWLinks> one, ArrayList<WaysWLinks> two){

        ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();

        ArrayList<Map<String, String>> childDataItemList = formArrayList(key, one);
        childDataList.add(childDataItemList);

        childDataItemList = formArrayList(key, two);
        childDataList.add(childDataItemList);


        return childDataList;
    }

    public void upDateContentList(String key, ArrayList<Ways> one, ArrayList<Ways> two, int i){
        expListDetail.clear();

        ArrayList<Map<String, String>> childDataItemList = formArrayList(key, one, 0);
        expListDetail.add(childDataItemList);

        childDataItemList = formArrayList(key, two, 0);
        expListDetail.add(childDataItemList);

    }

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
