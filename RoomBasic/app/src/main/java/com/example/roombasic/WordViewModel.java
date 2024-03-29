package com.example.roombasic;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private final WordRepository wordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWordLive() {
        return wordRepository.getAllWordLive();
    }

    void insertWords(Word... words){
       wordRepository.insertWords(words);
    }

    void updateWords(Word... words){
        wordRepository.updateWords(words);
    }

    void deleteWords(Word... words){
        wordRepository.updateWords(words);
    }

    void deleteAllWord(){
        wordRepository.deleteAllWord();
    }


}
