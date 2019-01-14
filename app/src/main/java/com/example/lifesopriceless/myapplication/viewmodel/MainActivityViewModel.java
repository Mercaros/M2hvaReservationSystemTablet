package com.example.lifesopriceless.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.lifesopriceless.myapplication.models.Reservation;
import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.repository.ReservationRepository;
import com.example.lifesopriceless.myapplication.repository.RoomRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {


    private LiveData<Room> mRoom;
    private LiveData<List<Reservation>> mReservations;


    public void init(String roomName) {
        if (this.mRoom != null) {
            return;
        }
        RoomRepository mRoomRepository = new RoomRepository();
        ReservationRepository mReservationRepository = new ReservationRepository();

        mRoom = mRoomRepository.getRoom(roomName);
        mReservations = mReservationRepository.getReservations(roomName);
    }

    public LiveData<Room> getRoom() {
        return mRoom;
    }

    public LiveData<List<Reservation>> getReservations() {
        return mReservations;
    }
}
