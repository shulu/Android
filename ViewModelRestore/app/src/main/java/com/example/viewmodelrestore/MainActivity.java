package com.example.viewmodelrestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.viewmodelrestore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    MyViewModel myViewModel;
    public final static String KEY_NUMBER = "my_number";
    private final static String TAG = "myTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        activityMainBinding.setData(myViewModel);
        activityMainBinding.setLifecycleOwner(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: this is destroy");
    }
}