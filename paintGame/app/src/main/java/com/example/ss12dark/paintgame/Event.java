package com.example.ss12dark.paintgame;

public class Event {

    private String name;
    private String description;
    private float price;
    private int type;
    private int type2;
    private String location;

    public Event (){

    }

    public Event(String name, String description, float price, int type, int type2, String location) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.type2 = type2;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType2() {
        return type2;
    }


    public void setType2(int type2) {
        this.type2 = type2;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
