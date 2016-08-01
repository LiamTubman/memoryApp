package com.thinknz.memoryapp;

public class Meeting {
    public String id;
    public String date;
    public String name;
    public String time;
    public String location;
    public String notes;

    public Meeting(String id, String date, String name, String time, String location, String notes){
        this.id = id;
        this.date = date;
        this.name = name;
        this.time = time;
        this.location = location;
        this.notes = notes;
    }
}
