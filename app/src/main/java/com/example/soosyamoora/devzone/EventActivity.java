package com.example.soosyamoora.devzone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    private static final String TAG = "EventActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Log.d(TAG, "onCreate: started");

        initImageBitmaps();
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("http://res.cloudinary.com/soosyamoora/image/upload/v1523700016/fb.svg");
        mNames.add("Developer Circles Kano");

        mImageUrls.add("http://res.cloudinary.com/soosyamoora/image/upload/v1523700016/fb.svg");
        mNames.add("Google Developer Group");

        mImageUrls.add("http://res.cloudinary.com/soosyamoora/image/upload/v1523700016/fb.svg");
        mNames.add("For Loop Kano");

        mImageUrls.add("http://res.cloudinary.com/soosyamoora/image/upload/v1523700016/fb.svg");
        mNames.add("Women Tech Makers");

        mImageUrls.add("http://res.cloudinary.com/soosyamoora/image/upload/v1523700016/fb.svg");
        mNames.add("Women In Tech NG");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        EventRecyclerViewAdapter adapter = new EventRecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
