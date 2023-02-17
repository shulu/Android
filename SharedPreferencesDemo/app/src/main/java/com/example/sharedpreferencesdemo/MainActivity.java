package com.example.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "mytag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyData myData = new MyData(getApplicationContext());
        myData.number = 1000;
        myData.save();
        int x = myData.load();

//        SharedPreferences shp = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences shp = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = shp.edit();
//        editor.putInt("MY_DATA", 600);
//        editor.apply();
//
//       //int x = shp.getInt("NUMBER", 0);
//        int x = shp.getInt("MY_DATA", 0);

        Log.d(TAG, "onCreate: "+x);
    }
}