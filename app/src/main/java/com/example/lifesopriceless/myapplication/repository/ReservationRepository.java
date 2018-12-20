package com.example.lifesopriceless.myapplication.repository;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.lifesopriceless.myapplication.models.Reservation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservationRepository {
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    public ReservationRepository() {
        database = FirebaseDatabase.getInstance();
    }

    public MutableLiveData<Reservation> getReservation() {

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        myRef = database.getReference("reservations");
        final MutableLiveData<Reservation> data = new MutableLiveData<>();



        // Read from the database
        myRef.orderByChild("date").equalTo("20-12-2018").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    if (snapshot.child("reservationRoom").child("name").getValue().equals("Zoo")) {
                        data.setValue(snapshot.getValue(Reservation.class));
                        //Log.d("hallo", "onDataChange: " + snapshot.getValue(Reservation.class));
                    }
                }
                Log.d("hallo", "onDataChange: -------------------------------------");
                //data.setValue(list);
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
