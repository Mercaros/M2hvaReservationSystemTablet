package com.example.lifesopriceless.myapplication;

public class Room {
    private String name;
    private String description;
    private String calendarID;
    private int capacity;
    private boolean availability;
    private String time;


    public Room() {
    }

    public Room(String name, String description, String calendarID, int capacity) {
        this.name = name;
        this.description = description;
        this.calendarID = calendarID;
        this.time = "10:00";
        this.capacity = capacity;
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

    public String getCalendarID() {
        return calendarID;
    }

    public void setCalendarID(String calendarID) {
        this.calendarID = calendarID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}