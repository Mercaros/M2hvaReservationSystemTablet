package com.example.lifesopriceless.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.repository.RoomRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private RoomRepository mRepository = new RoomRepository();
    private LiveData<Room> room;

    public void init(String roomName) {
        if (this.room != null) {
            return;
        }
        room = new MutableLiveData<>();

        room = mRepository.getRoom(roomName);
    }

    public LiveData<Room> getRoom() {
        return room;
    }
}
