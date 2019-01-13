package com.example.lifesopriceless.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lifesopriceless.myapplication.R;
import com.example.lifesopriceless.myapplication.activities.RoomActivity;
import com.example.lifesopriceless.myapplication.models.Room;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChooseRoomAdapter extends RecyclerView.Adapter<ChooseRoomAdapter.MyViewHolder> {

    private List<Room> mRoomList;
    private Context mContext;
    final private ItemClickListener mItemClickListener;


    public ChooseRoomAdapter(Context context, ItemClickListener itemClickListener) {
        mContext = context;
        mItemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_room_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Room room = mRoomList.get(position);
        holder.title.setText(room.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RoomActivity.class);
                intent.putExtra("room_name", room.getName());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mRoomList == null) {
            return 0;
        }
        return mRoomList.size();
    }

    public List<Room> getRooms() {
        return mRoomList;
    }

    public void setRooms(List<Room> roomsData) {
        mRoomList = roomsData;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.roomTitle)
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
