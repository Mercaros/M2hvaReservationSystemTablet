package com.example.lifesopriceless.myapplication.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.lifesopriceless.myapplication.R;
import com.example.lifesopriceless.myapplication.models.Reservation;
import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.viewmodel.MainActivityViewModel;
import com.google.android.gms.common.util.Strings;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mp_title)
    TextView mTextViewTitle;

    @BindView(R.id.mp_status)
    TextView mTextViewStatus;

    @BindView(R.id.background)
    LinearLayout layout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


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
                setBackgroundImage(room.getImage());
                toolbar.setTitle(room.getName());
                setSupportActionBar(toolbar);
                mTextViewTitle.setText(room.getName());
            }
        });

        mViewModel.getRoomStatus().observe(this, new Observer<Reservation>() {
            @Override
            public void onChanged(@Nullable Reservation reservation) {

                if (!Strings.isEmptyOrWhitespace(reservation.getId())){
                    mTextViewStatus.setText("Occupied");
                }else{
                    mTextViewStatus.setText("Available");
                }
            }
        });
    }

    private void setBackgroundImage(String image_url) {
        Glide.with(getBaseContext())
                .load(image_url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        layout.setBackground(resource);
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.switch_rooms:
                Intent intent = new Intent(this, ChooseRoomActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

