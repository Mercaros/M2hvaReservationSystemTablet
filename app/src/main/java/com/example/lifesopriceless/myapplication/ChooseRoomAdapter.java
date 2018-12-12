package com.example.lifesopriceless.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseRoomAdapter extends RecyclerView.Adapter<ChooseRoomAdapter.MyViewHolder> {

    private List<Room> roomList;

    public ChooseRoomAdapter(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.title.setText(room.getName());

    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.roomTitle)
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick(R.id.roomTitle)
        public void onClickRoom() {
            //Intent to mainactivity
        }

    }


    public void swapList(List<Room> newList) {
        roomList = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            notifyDataSetChanged();
        }
    }
}
