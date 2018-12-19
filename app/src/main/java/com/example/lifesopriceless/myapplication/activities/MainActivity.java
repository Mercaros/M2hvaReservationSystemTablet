package com.example.lifesopriceless.myapplication.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.lifesopriceless.myapplication.R;
import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.viewmodel.MainActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mp_title)
    TextView mTextViewTitle;


    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initViewModel();

    }

    private void initViewModel() {
        Bundle extras = getIntent().getExtras();
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mViewModel.init(extras.getString("room_name"));
        mViewModel.getRoom().observe(this, new Observer<Room>() {
            @Override
            public void onChanged(@Nullable Room room) {
                mTextViewTitle.setText(room.getName());
            }
        });


    }
}

