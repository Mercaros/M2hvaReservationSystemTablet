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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChooseRoomActivity extends AppCompatActivity {
    private static final String TAG = "Rtesting";
    private ChooseRoomViewModel mViewModel;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ChooseRoomAdapter mAdapter;


    private List<Room> roomsData = new ArrayList<>();

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
        final Observer<List<Room>> notesObserver = new Observer<List<Room>>() {
            @Override
            public void onChanged(@Nullable List<Room> notes) {
                roomsData.clear();
                roomsData.addAll(notes);


                if (mAdapter == null) {
                    mAdapter = new ChooseRoomAdapter(roomsData, ChooseRoomActivity.this);
                    mRecyclerView.setAdapter(mAdapter);

                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }
        };

        mViewModel = ViewModelProviders.of(this).get(ChooseRoomViewModel.class);
        mViewModel.init();
        mViewModel.getRooms().observe(this, notesObserver);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(), layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(divider);
    }

}


