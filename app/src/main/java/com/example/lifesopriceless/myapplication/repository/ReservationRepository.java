package com.example.lifesopriceless.myapplication.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.util.Log;

import com.example.lifesopriceless.myapplication.utils.DateUtils;
import com.example.lifesopriceless.myapplication.models.Reservation;
import com.example.lifesopriceless.myapplication.utils.TimeService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReservationRepository {
    private final FirebaseDatabase database;
    private DatabaseReference myRef;

    private String currentTime;


    public ReservationRepository() {
        database = FirebaseDatabase.getInstance();
    }

    public LiveData<Reservation> getReservation(final String roomName) {

        myRef = database.getReference("reservations");
        final MutableLiveData<Reservation> data = new MutableLiveData<>();


        myRef.orderByChild("reservationRoom/name").equalTo(roomName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (DateUtils.getCurrentDate().equalsIgnoreCase(snapshot.getValue(Reservation.class).getDate())) {

                        if (DateUtils.isHourInInterval(DateUtils.getCurrentHour(),
                                snapshot.getValue(Reservation.class).getStartTime(),
                                snapshot.getValue(Reservation.class).getEndTime())) {
                            data.setValue(snapshot.getValue(Reservation.class));

                        } else {
                            data.setValue(new Reservation());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hallo", "Failed to read value.", error.toException());
            }
        });

        return data;
    }


}
