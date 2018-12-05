package com.example.lifesopriceless.myapplication.repository;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by khaled on 07-02-18.
 */

public abstract class Repo<T> {
    private Context context;
    private DatabaseReference mDatabase;

    public Repo(Context context) {
        this.context = context;
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public Context getContext() {
        return context;
    }

    public DatabaseReference getReference() {
        return mDatabase;
    }

    public abstract List<T> getList();
}
