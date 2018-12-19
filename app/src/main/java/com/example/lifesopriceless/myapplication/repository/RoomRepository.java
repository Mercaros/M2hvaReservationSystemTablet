package com.example.lifesopriceless.myapplication.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.lifesopriceless.myapplication.models.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private FirebaseDatabase database;
    private DatabaseReference myRef;



    public RoomRepository() {
        database = FirebaseDatabase.getInstance();
    }

    public MutableLiveData<List<Room>> getRooms() {
        myRef = database.getReference("rooms");
        final MutableLiveData<List<Room>> data = new MutableLiveData<>();
        final List<Room> list = new ArrayList<>();


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    list.add(snapshot.getValue(Room.class));
                }
                data.setValue(list);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });

        return data;
    }


    public LiveData<Room> getRoom(final String roomName) {
        myRef = database.getReference("rooms");
        final MutableLiveData<Room> data = new MutableLiveData<>();
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("name").getValue().equals(roomName)) {
                        data.setValue(snapshot.getValue(Room.class));
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
        return data;
    }
}
