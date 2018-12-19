package com.example.lifesopriceless.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.repository.RoomRepository;

import java.util.List;

public class ChooseRoomViewModel extends ViewModel {

    private RoomRepository mRepository = new RoomRepository();
    private LiveData<List<Room>> rooms;

    public void init() {
        if (this.rooms != null) {
            return;
        }
        rooms = new MutableLiveData<>();
        rooms = mRepository.getRooms();
    }

    public LiveData<List<Room>> getRooms() {
        return rooms;
    }

}
