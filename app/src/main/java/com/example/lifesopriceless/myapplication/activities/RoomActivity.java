package com.example.lifesopriceless.myapplication.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.example.lifesopriceless.myapplication.R;
import com.example.lifesopriceless.myapplication.models.Reservation;
import com.example.lifesopriceless.myapplication.models.Room;
import com.example.lifesopriceless.myapplication.viewmodel.MainActivityViewModel;
import com.google.android.gms.common.util.Strings;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class RoomActivity extends AppCompatActivity {
    @BindView(R.id.mp_title)
    TextView mTextViewTitle;

    @BindView(R.id.mp_status)
    TextView mTextViewStatus;

    @BindView(R.id.mp_endTime)
    TextView mTextViewEndTime;

    @BindView(R.id.room_avatar)
    ImageView mRoomAvatar;

    @BindView(R.id.background)
    ImageView mBackground;

    Room mRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        ButterKnife.bind(this);
        initViewModel();

    }

    private void initViewModel() {
        Bundle extras = getIntent().getExtras();
        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.init(extras.getString("room_name"));
        viewModel.getRoom().observe(this, new Observer<Room>() {
            @Override
            public void onChanged(@Nullable Room room) {
                mRoom = room;
                mTextViewTitle.setText(room.getName());
            }
        });
        viewModel.getRoomStatus().observe(this, new Observer<Reservation>() {
            @Override
            public void onChanged(@Nullable Reservation reservation) {
                if (!Strings.isEmptyOrWhitespace(reservation.getId())) {
                    setBackgroundImage(mRoom, true);
                    mTextViewStatus.setText("Occupied");
                    mTextViewEndTime.setText("Until: " + reservation.getEndTime());
                } else {
                    setBackgroundImage(mRoom, false);
                    mTextViewStatus.setText("Available");
                    mTextViewEndTime.setText("");
                }
            }
        });

    }

    private void setBackgroundImage(Room room, Boolean b) {
        Glide.with(getBaseContext())
                .load(room.getImage())
                .apply(bitmapTransform(new GrayscaleTransformation()))
                .into(new DrawableImageViewTarget(mRoomAvatar));

        ColorFilter cf;
        if (!b) {
            cf = duotoneColorFilter(getColor(R.color.room_available_dark), getColor(R.color.room_available_light), 0);
        } else {
            cf = duotoneColorFilter(getColor(R.color.room_booked_dark), getColor(R.color.room_booked_light), 0);
        }
        mRoomAvatar.setColorFilter(cf);

        Glide.with(getBaseContext())
                .load(room.getImage())
                .apply(bitmapTransform(new BlurTransformation(25, 3)))
                .into(new DrawableImageViewTarget(mBackground));

        mBackground.setColorFilter(cf);
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

    public ColorFilter duotoneColorFilter(@ColorInt int colorBlack, @ColorInt int colorWhite, float contrast) {
        ColorMatrix cm = new ColorMatrix();

        ColorMatrix cmBlackWhite = new ColorMatrix();
        float lumR = 0.2125f;
        float lumG = 0.7154f;
        float lumB = 0.0721f;
        float[] blackWhiteArray = new float[]{
                lumR, lumG, lumB, 0, 0,
                lumR, lumG, lumB, 0, 0,
                lumR, lumG, lumB, 0, 0,
                0, 0, 0, 1, 0};
        cmBlackWhite.set(blackWhiteArray);

        ColorMatrix cmContrast = new ColorMatrix();
        float scale = contrast + 1.0f;
        float translate = (-0.5f * scale + 0.5f) * 255f;
        float[] contrastArray = new float[]{
                scale, 0, 0, 0, translate,
                0, scale, 0, 0, translate,
                0, 0, scale, 0, translate,
                0, 0, 0, 1, 0};
        cmContrast.set(contrastArray);

        ColorMatrix cmDuoTone = new ColorMatrix();
        float r1 = Color.red(colorWhite);
        float g1 = Color.green(colorWhite);
        float b1 = Color.blue(colorWhite);
        float r2 = Color.red(colorBlack);
        float g2 = Color.green(colorBlack);
        float b2 = Color.blue(colorBlack);
        float r1r2 = (r1 - r2) / 255f;
        float g1g2 = (g1 - g2) / 255f;
        float b1b2 = (b1 - b2) / 255f;
        float[] duoToneArray = new float[]{
                r1r2, 0, 0, 0, r2,
                g1g2, 0, 0, 0, g2,
                b1b2, 0, 0, 0, b2,
                0, 0, 0, 1, 0};
        cmDuoTone.set(duoToneArray);

        cm.postConcat(cmBlackWhite);
        cm.postConcat(cmContrast);
        cm.postConcat(cmDuoTone);

        return new ColorMatrixColorFilter(cm);
    }
}

