package com.example.words;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private final WordDao wordDao;
    private final LiveData<List<Word>> allWordLive;

    public WordRepository(Context context){
        WordDataBase wordDataBase = WordDataBase.getDataBase(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();
        allWordLive = wordDao.getAllWordsLive();
    }

    public LiveData<List<Word>> filterWords(String search){
        return wordDao.filterWords("%"+search+"%");
    }

    public LiveData<List<Word>> getAllWordLive() {
        return allWordLive;
    }

    void insertWords(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updateWords(Word... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words){
        new DeleteAsyncTask(wordDao).execute(words);
    }

    void deleteAllWord(Word... words){
        new ClearAsyncTask(wordDao).execute(words);
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private final WordDao wordDao;

        InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void>{
        private final WordDao wordDao;

        UpdateAsyncTask(WordDao wordDao){ this.wordDao = wordDao;}

        @Override
        protected Void doInBackground(Word... words){
            wordDao.UpdateWords(words);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Word, Void, Void>{
        private final WordDao wordDao;

        ClearAsyncTask(WordDao wordDao){ this.wordDao = wordDao;}

        @Override
        protected Void doInBackground(Word... words){
            wordDao.deleteAllWords();
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void>{
        private final WordDao wordDao;

        DeleteAsyncTask(WordDao wordDao){ this.wordDao = wordDao;}

        @Override
        protected Void doInBackground(Word... words){
            wordDao.deleteWords(words);
            return null;
        }
    }
}
