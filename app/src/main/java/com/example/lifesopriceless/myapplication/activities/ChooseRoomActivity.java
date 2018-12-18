package com.example.lifesopriceless.myapplication.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.lifesopriceless.myapplication.adapters.ChooseRoomAdapter;
import com.example.lifesopriceless.myapplication.R;
import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.viewmodel.ChooseRoomViewModel;

import java.util.List;


public class ChooseRoomActivity extends AppCompatActivity {
    private ChooseRoomViewModel mViewModel;

    private RecyclerView mRecyclerView;
    private ChooseRoomAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);

        mRecyclerView = findViewById(R.id.recyclerView);
        mViewModel = ViewModelProviders.of(this).get(ChooseRoomViewModel.class);
        mViewModel.init();

        mViewModel.getRooms().observe(this, new Observer<List<Room>>() {
            @Override
            public void onChanged(@Nullable List<Room> rooms) {
                mAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();


    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ChooseRoomAdapter(mViewModel.getRooms().getValue(), ChooseRoomActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}


