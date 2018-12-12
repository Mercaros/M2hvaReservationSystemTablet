package com.example.lifesopriceless.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lifesopriceless.myapplication.repository.RoomRepo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ChooseRoomActivity extends AppCompatActivity {

    private RecyclerView roomListView;
    private List<Room> roomList;
    private ChooseRoomAdapter chooseRoomAdapter;
    RoomRepo roomRepo = new RoomRepo(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);
        roomListView = findViewById(R.id.recyclerView);
        loadData();
    }

    private void updateUI() {
        if (chooseRoomAdapter == null) {
            chooseRoomAdapter = new ChooseRoomAdapter(roomList);
            roomListView.setLayoutManager(new LinearLayoutManager(ChooseRoomActivity.this));
            roomListView.setAdapter(chooseRoomAdapter);
        } else {
            chooseRoomAdapter.swapList(roomList);
        }
    }

    private void loadData() {
        Observable.just(roomRepo.getList()).subscribe(new Observer<List<Room>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Room> rooms) {
            roomList = rooms;
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                updateUI();
            }
        });

    }
}


