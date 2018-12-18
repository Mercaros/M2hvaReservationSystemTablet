package com.example.lifesopriceless.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.lifesopriceless.myapplication.Room;
import com.example.lifesopriceless.myapplication.RoomRepository;

import java.util.List;

public class MainPanelViewModel extends ViewModel {


    private RoomRepository mRepo;
    private MutableLiveData<Room> room;


    public void init() {
        if (room != null) {
            return;
        }
        mRepo = RoomRepository.getInstance();
        room = mRepo.getRoom(2);
    }

    public LiveData<Room> getRoom() {
        return room;
    }

}