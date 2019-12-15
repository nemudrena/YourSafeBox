package com.example.myapp;

public class Ways{

    protected String text;

    public Ways(){ text = ""; }
    public Ways(String otherStr) {this.text = otherStr;}
    public Ways(Ways other) {this.text = other.getText();}
    public void setNewText(String other) {this.text = other;}
    public String getText() {return this.text;}
}

