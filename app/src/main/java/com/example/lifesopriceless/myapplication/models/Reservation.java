package com.example.lifesopriceless.myapplication.models;


public class Reservation {

    private int attendees;
    private String date;
    private String startTime;
    private String endTime;
    private Room reservationRoom;
    private String creator;
    private String id;

    public Reservation() {

    }

    public Reservation(String id) {
        this.id = id;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Room getReservationRoom() {
        return reservationRoom;
    }

    public void setReservationRoom(Room reservationRoom) {
        this.reservationRoom = reservationRoom;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "attendees=" + attendees +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", reservationRoom=" + reservationRoom +
                ", creator='" + creator + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
