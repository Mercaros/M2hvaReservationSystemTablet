package com.example.lifesopriceless.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.lifesopriceless.myapplication.Room;
import com.example.lifesopriceless.myapplication.RoomRepository;

import java.util.List;

public class ChooseRoomViewModel extends ViewModel {


    private RoomRepository mRepo;
    private MutableLiveData<List<Room>> rooms;


    public void init() {
        if (rooms != null) {
            return;
        }
        mRepo = RoomRepository.getInstance();
        rooms = mRepo.getRooms();
    }

    public LiveData<List<Room>> getRooms() {
        return rooms;
    }

}
