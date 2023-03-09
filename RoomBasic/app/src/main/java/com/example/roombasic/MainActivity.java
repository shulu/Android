package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordDataBase wordDataBase;
    WordDao wordDao;
    TextView textView;
    Button btnInsert, btnUpdate, btnClear, btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordDataBase = Room.databaseBuilder(this, WordDataBase.class, "word_database")
                .allowMainThreadQueries()
                .build();
        wordDao = wordDataBase.getWordDao();
        textView = findViewById(R.id.textView);
        updateView();

        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hello", "你好！");
                Word word1 = new Word("World", "世界！");
                wordDao.insertWords(word, word1);
                updateView();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.deleteAllWords();
                updateView();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi", "你好啊！");
                word.setId(9);
                wordDao.UpdateWords(word);
                updateView();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("", "");
                word.setId(7);
                wordDao.deleteWords(word);
                updateView();
            }
        });
    }

    void updateView() {
        List<Word> list = wordDao.getAllWords();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Word word = list.get(i);
            text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseMeaning()+"\n");
        }
        textView.setText(text.toString());
    }
}