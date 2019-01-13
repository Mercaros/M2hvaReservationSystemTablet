package com.example.lifesopriceless.myapplication.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lifesopriceless.myapplication.R;
import com.example.lifesopriceless.myapplication.adapters.ChooseRoomAdapter;
import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.viewmodel.ChooseRoomViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChooseRoomActivity extends AppCompatActivity implements ChooseRoomAdapter.ItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ChooseRoomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_selection);

        ButterKnife.bind(this);

        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        ChooseRoomViewModel viewModel = ViewModelProviders.of(this).get(ChooseRoomViewModel.class);
        viewModel.getRooms().observe(this, new Observer<List<Room>>() {
            @Override
            public void onChanged(@Nullable List<Room> rooms) {
                mAdapter.setRooms(rooms);
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ChooseRoomAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration divider = new DividerItemDecoration(
                getApplicationContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(divider);
    }

    @Override
    public void onItemClickListener(int itemId) {
    }
}


