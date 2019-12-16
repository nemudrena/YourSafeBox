package com.example.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListOfWays {
    private Map<String, String> map;
    private ArrayList<Map<String, String>> titleDataList;
    private ArrayList<ArrayList<Map<String, String>>> expListDetail;

    public ListOfWays(ArrayList<Ways> titles, ArrayList<Ways> one, ArrayList<Ways> two){
        titleDataList = formArrayList("groupName", titles);

        expListDetail = formContentList("genre", one, two);

    }

    public ArrayList<Map<String, String>> formArrayList(String key, ArrayList<Ways> way){
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        for (Ways item : way) {
            map = new HashMap<>();
            map.put(key, item.getText());
            dataList.add(map);
        }

        return dataList;
    }

    public ArrayList<ArrayList<Map<String, String>>>
    formContentList(String key, ArrayList<Ways> one, ArrayList<Ways> two){

        ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();

        ArrayList<Map<String, String>> childDataItemList = formArrayList(key, one);
        childDataList.add(childDataItemList);

        childDataItemList = formArrayList(key, two);
        childDataList.add(childDataItemList);


        return childDataList;
    }

    public void upDateContentList(String key, ArrayList<Ways> one, ArrayList<Ways> two){
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
