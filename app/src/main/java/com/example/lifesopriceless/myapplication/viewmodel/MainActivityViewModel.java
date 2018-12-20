package com.example.lifesopriceless.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.lifesopriceless.myapplication.models.Reservation;
import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.repository.ReservationRepository;
import com.example.lifesopriceless.myapplication.repository.RoomRepository;

public class MainActivityViewModel extends ViewModel {

    private RoomRepository mRoomRepository = new RoomRepository();
    private ReservationRepository mReservationRepository = new ReservationRepository();

    private LiveData<Room> room;
    private LiveData<Reservation> currentReservation;




    public void init(String roomName) {
        if (this.room != null) {
            return;
        }
        room = mRoomRepository.getRoom(roomName);
        currentReservation = mReservationRepository.getReservation(roomName);
    }

    public LiveData<Room> getRoom() {
        return room;
    }

    public LiveData<Reservation> getRoomStatus() {
        return currentReservation;
    }
}
