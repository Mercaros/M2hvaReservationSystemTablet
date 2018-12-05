package com.example.lifesopriceless.myapplication.repository;

import android.content.Context;

import com.example.lifesopriceless.myapplication.Reservation;
import com.example.lifesopriceless.myapplication.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ReservationRepo extends Repo<Reservation>{

    public ReservationRepo(Context context) {
        super(context);
    }

    @Override
    public List<Reservation> getList() {
        final List<Reservation> reservationList = new ArrayList<>();
        ValueEventListener reservationListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Reservation reservation = dataSnapshot.getValue(Reservation.class);
                reservationList.add(reservation);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Timber.w(databaseError.toException());
            }
        };
        //Read data once
        getReference().addListenerForSingleValueEvent(reservationListener);
        return reservationList;
    }

    @Override
    public DatabaseReference getReference() {
        return super.getReference();
    }
}
