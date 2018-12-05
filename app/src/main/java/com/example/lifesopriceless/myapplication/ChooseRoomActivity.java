package com.example.lifesopriceless.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChooseRoomActivity extends AppCompatActivity {

    private RecyclerView roomListView;
    private List<Room> roomList = new ArrayList<>();
    private ChooseRoomAdapter chooseRoomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);
        roomListView = findViewById(R.id.recyclerView);
        dummyData();
        updateUI();
    }

    private void updateUI() {
        if (chooseRoomAdapter == null) {
            chooseRoomAdapter = new ChooseRoomAdapter(roomList);
            roomListView.setLayoutManager(new LinearLayoutManager(ChooseRoomActivity.this));
            roomListView.setAdapter(chooseRoomAdapter);
        } else {
            //Refresh list

        }
    }

    private void dummyData() {
        roomList.add(new Room("Mammut", "Hoi", "Id", 3));
        roomList.add(new Room("Elephant", "Hoi", "Id", 3));
        roomList.add(new Room("Hunter Room ", "Hoi", "Id", 3));
        roomList.add(new Room("Big Meeting Room", "Hoi", "Id", 3));

    }
}
