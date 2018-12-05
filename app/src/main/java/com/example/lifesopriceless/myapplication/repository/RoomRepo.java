package com.example.lifesopriceless.myapplication.repository;

import android.content.Context;
import android.util.Log;

import com.example.lifesopriceless.myapplication.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class RoomRepo extends Repo<Room> {

    public RoomRepo(Context context, DatabaseReference mDatabase) {
        super(context);
    }

    @Override
    public List<Room> getList() {
        final List<Room> roomList = new ArrayList<>();
        ValueEventListener roomListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Room room = dataSnapshot.getValue(Room.class);
                roomList.add(room);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Timber.w(databaseError.toException());
            }
        };
        //Read data once
        getReference().addListenerForSingleValueEvent(roomListener);
        return roomList;
    }

    @Override
    public DatabaseReference getReference() {
        return super.getReference();
    }
}
