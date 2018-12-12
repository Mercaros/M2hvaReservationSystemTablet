package com.example.lifesopriceless.myapplication;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private static RoomRepository instance;
    private ArrayList<Room> data = new ArrayList<>();


    public static RoomRepository getInstance() {
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }


    // Sample get data from a webservice
    public MutableLiveData<List<Room>> getRooms() {
        setRooms();
        MutableLiveData<List<Room>> dataSet = new MutableLiveData<>();
        dataSet.setValue(data);
        return dataSet;
    }


    public MutableLiveData<Room> getRoom(int roomId) {
        setRooms();
        MutableLiveData<Room> roomData = new MutableLiveData<>();
        roomData.setValue(data.get(roomId));
        return roomData;
    }


    private void setRooms() {
        data.add(new Room("Room 1"));
        data.add(new Room("Room 2"));
        data.add(new Room("Room 3"));
        data.add(new Room("Room 4"));
        data.add(new Room("Room 5"));
    }
}
