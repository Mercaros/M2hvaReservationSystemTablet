package com.example.lifesopriceless.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.repository.RoomRepository;

import java.util.List;

public class ChooseRoomViewModel extends AndroidViewModel {
    private LiveData<List<Room>> rooms;

    public ChooseRoomViewModel(@NonNull Application application) {
        super(application);
        RoomRepository mRoomRepository = new RoomRepository();
        rooms = mRoomRepository.getRooms();
    }
    public LiveData<List<Room>> getRooms() {
        return rooms;
    }
}
