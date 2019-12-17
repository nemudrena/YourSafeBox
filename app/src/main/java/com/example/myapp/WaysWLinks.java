package com.example.myapp;

public class WaysWLinks extends Ways{

    private String link;

    public WaysWLinks(){ text = ""; link = "";}

    public WaysWLinks(String word, String site){ text = word; link = site;}

    public WaysWLinks(WaysWLinks other) {text = other.getText(); link = other.link;}
    public void setNewLink(String other) {link = other;}
    public String getLink() {return this.link;}
}
