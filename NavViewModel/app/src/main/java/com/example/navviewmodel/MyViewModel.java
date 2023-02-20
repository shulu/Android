package com.example.navviewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends AndroidViewModel {

    public MutableLiveData<Integer> number;

    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getNumber() {
        if (number == null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void add(int x){
        int now_val = getNumber().getValue() + x;
        Context context = getApplication().getApplicationContext();
        if (now_val > 10){
            Toast.makeText(context, "Number Cannot > 10", Toast.LENGTH_SHORT).show();
        }else{
            number.setValue(number.getValue() + x);
            if (number.getValue() < 0){
                number.setValue(0);
            }
        }
    }
}
