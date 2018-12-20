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

public class ReservationRepository {
    private final FirebaseDatabase database;
    private DatabaseReference myRef;
    private Executor executor;


    public ReservationRepository() {
        database = FirebaseDatabase.getInstance();

    }

    public MutableLiveData<Reservation> getReservation(final String roomName) {
        final MutableLiveData<Reservation> data = new MutableLiveData<>();
        myRef = database.getReference("reservations");

        myRef.orderByChild("startTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("reservationRoom").child("name").getValue().equals(roomName)) {
                        Log.d("hallo", "onDataChange: " + snapshot.getValue(Reservation.class));

                        if (DateUtils.isNowInInterval(
                                snapshot.getValue(Reservation.class).getStartTime(),
                                snapshot.getValue(Reservation.class).getEndTime())) {
                            Log.d("hallo", "currentReservation: " + snapshot.getValue(Reservation.class));
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
