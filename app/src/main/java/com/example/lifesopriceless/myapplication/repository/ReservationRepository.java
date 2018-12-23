package com.example.lifesopriceless.myapplication.repository;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.lifesopriceless.myapplication.DateUtils;
import com.example.lifesopriceless.myapplication.models.Reservation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReservationRepository {
    private final FirebaseDatabase database;
    private DatabaseReference myRef;
    private Executor executor;
    private String currentTime;


    public ReservationRepository() {
        database = FirebaseDatabase.getInstance();
        executor = Executors.newSingleThreadExecutor();
    }

    public MutableLiveData<Reservation> getReservation(final String roomName) {
        currentTime();
        myRef = database.getReference("reservations");
        final MutableLiveData<Reservation> data = new MutableLiveData<>();


        myRef.orderByChild("reservationRoom/name").equalTo(roomName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (DateUtils.getCurrentDate().equalsIgnoreCase(snapshot.getValue(Reservation.class).getDate())) {

                        if (DateUtils.isHourInInterval(currentTime,
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

    private void currentTime() {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        currentTime = DateUtils.getCurrentHour();
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        });


    }
}
