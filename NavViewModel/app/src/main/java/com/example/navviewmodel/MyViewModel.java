package com.example.navviewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    public MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        return number;
    }

    public void add(int x){
        number.setValue(number.getValue() + x);
    }
}
