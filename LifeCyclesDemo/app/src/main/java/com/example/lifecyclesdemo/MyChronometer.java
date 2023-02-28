package com.example.lifecyclesdemo;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public class MyChronometer extends Chronometer implements LifecycleEventObserver {


    private long elapseTime;

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        switch (event){
            case ON_PAUSE:
                elapseTime = SystemClock.elapsedRealtime() - getBase();
                stop();
                break;
            case ON_RESUME:
                setBase(SystemClock.elapsedRealtime() - elapseTime);
                start();
                break;
        }
    }
}
