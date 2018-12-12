package com.example.lifesopriceless.myapplication.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.lifesopriceless.myapplication.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import timber.log.Timber;

public class RoomRepo extends Repo<Room> {

    public RoomRepo(Context context) {
        super(context);
    }


    @Override
    public List<Room> getList() {
        final List<Room> roomList = new ArrayList<>();
        getReference().getDatabase().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot nextDS : dataSnapshot.getChildren()) {
                    Room room = nextDS.getValue(Room.class);
                    roomList.add(room);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Timber.w(databaseError.toException());
            }
        });
        return roomList;
    }

    @Override
    public DatabaseReference getReference() {
        return super.getReference();
    }
}
