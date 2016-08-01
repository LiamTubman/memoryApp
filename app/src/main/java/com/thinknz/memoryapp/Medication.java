package com.thinknz.memoryapp;

import java.util.ArrayList;
import java.util.List;

public class Medication {
    public String id;
    public String quantity;
    public String endDate;
    public String reminderTime;
    public String reminderMessage;
    public String name;

    public List<MedicationTime> times = new ArrayList<>();

    public Medication(String id, String quantity, String endDate, String reminderTime, String reminderMessage, String name){
        this.id = id;
        this.quantity = quantity;
        this.endDate = endDate;
        this.reminderTime = reminderTime;
        this.reminderMessage = reminderMessage;
        this.name = name;
    }
}
