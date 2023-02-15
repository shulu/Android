package com.example.basketballcounter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> teamACounter;
    private MutableLiveData<Integer> teamBCounter;

    public MutableLiveData<Integer> getTeamACounter() {
        if (teamACounter == null){
            teamACounter = new MutableLiveData<>();
            teamACounter.setValue(0);
        }
        return teamACounter;
    }

    public MutableLiveData<Integer> getTeamBCounter() {
        if (teamBCounter == null){
            teamBCounter = new MutableLiveData<>();
            teamBCounter.setValue(0);
        }
        return teamBCounter;
    }

    public void setCountA(Integer n){
        this.teamACounter.setValue(this.teamACounter.getValue()+n);
    }

    public void setCountB(Integer n){
        this.teamBCounter.setValue(this.teamBCounter.getValue()+n);
    }

    public void clearScore() {
        this.teamACounter.setValue(0);
        this.teamBCounter.setValue(0);
    }
}
