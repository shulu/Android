package com.example.viewmodeldemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MyViewModel(private val savedStateHandle: SavedStateHandle):ViewModel() {
    private val _number = MutableLiveData<Int>() .also {
        if (!savedStateHandle.contains("number"))
        {
            savedStateHandle["number"] = 0
        }
        it.value = savedStateHandle["number"];
    }

    val number : LiveData<Int> = _number
    fun addOne() {
        _number.value = _number.value?.plus(1);
    }
}