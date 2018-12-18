package com.example.lifesopriceless.myapplication.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lifesopriceless.myapplication.R;
import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.viewmodel.MainPanelViewModel;

public class MainPanelFragment extends Fragment {
    private static final String TAG = "MainPanelFragment";
    private MainPanelViewModel mViewModel;

    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_panel, container, false);
        title = v.findViewById(R.id.mp_title);
        mViewModel = ViewModelProviders.of(this).get(MainPanelViewModel.class);
        mViewModel.init();


        mViewModel.getRoom().observe(this, new Observer<Room>() {
            @Override
            public void onChanged(@Nullable Room room) {
                title.setText(mViewModel.getRoom().getValue().getName());
            }
        });
        return v;
    }
}
