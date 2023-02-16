package com.example.basketballcounter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> teamACounter;
    private MutableLiveData<Integer> teamBCounter;

    private int aBack, bBack;

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
        aBack = teamACounter.getValue();
        bBack = teamBCounter.getValue();
        this.teamACounter.setValue(this.teamACounter.getValue()+n);
    }

    public void setCountB(Integer n){
        aBack = teamACounter.getValue();
        bBack = teamBCounter.getValue();
        this.teamBCounter.setValue(this.teamBCounter.getValue()+n);
    }

    public void clearScore() {
        aBack = teamACounter.getValue();
        bBack = teamBCounter.getValue();
        this.teamACounter.setValue(0);
        this.teamBCounter.setValue(0);
    }

    public void undo() {
        this.teamACounter.setValue(aBack);
        this.teamBCounter.setValue(bBack);
    }
}
