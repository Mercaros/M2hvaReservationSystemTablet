package com.example.lifesopriceless.myapplication.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.lifesopriceless.myapplication.models.Reservation;
import com.example.lifesopriceless.myapplication.utils.DateUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    private final FirebaseDatabase database;
    private DatabaseReference myRef;


    public ReservationRepository() {
        database = FirebaseDatabase.getInstance();
    }

    public LiveData<List<Reservation>> getReservations(final String roomName) {

        myRef = database.getReference("reservations");
        final MutableLiveData<List<Reservation>> data = new MutableLiveData<>();
        final List<Reservation> list = new ArrayList<>();


        myRef.orderByChild("reservationRoom/name").equalTo(roomName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (DateUtils.getCurrentDate().equalsIgnoreCase(snapshot.getValue(Reservation.class).getDate())) {
                        list.add(snapshot.getValue(Reservation.class));
                    }
                }
                data.setValue(list);
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
