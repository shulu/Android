package com.example.viewmodelsp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {

    SavedStateHandle handle;
    String shp_name;
    String shp_key;
    SharedPreferences shp;
    SharedPreferences.Editor editor;


    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        shp_name = getApplication().getResources().getString(R.string.shp_name);
        shp_key = getApplication().getResources().getString(R.string.shp_key);
        shp = getApplication().getSharedPreferences(shp_name, Context.MODE_PRIVATE);
        editor = shp.edit();
        if (!handle.contains(shp_key)){
            load();
        }
    }

    public LiveData<Integer> getCounter(){
        return handle.getLiveData(shp_key);
    }

    public void save(){
        editor.putInt(shp_key, getCounter().getValue());
        editor.apply();
    }

    public void load(){
        int x = shp.getInt(shp_key, 0);
        handle.set(shp_key, x);
    }

    public void plus(Integer x){
        handle.set(shp_key, getCounter().getValue() + x);
    }
}
