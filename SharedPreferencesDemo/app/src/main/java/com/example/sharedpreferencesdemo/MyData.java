package com.example.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class MyData {

    public int number = 0;
    private final SharedPreferences shp;
    private final SharedPreferences.Editor editor;
    private final String my_key;
    private final Integer default_int;

    public MyData(Context context){
        String name = context.getResources().getString(R.string.my_data);
        my_key = context.getResources().getString(R.string.my_key);
        default_int = context.getResources().getInteger(R.integer.defaultInt);
        shp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = shp.edit();
    }

    public void save(){
        editor.putInt(my_key, number);
        editor.apply();
    }

    public int load(){
        return shp.getInt(my_key, default_int);
    }
}


