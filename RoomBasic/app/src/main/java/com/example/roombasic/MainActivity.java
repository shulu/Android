package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordViewModel wordViewModel;
    Switch aSwitch;
    Button btnInsert, btnUpdate, btnClear, btnDelete;
    RecyclerView recyclerView;
    MyAdapter myAdapterNormal, myAdapterCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        recyclerView = findViewById(R.id.rv);
        myAdapterNormal = new MyAdapter(false, wordViewModel);
        myAdapterCard = new MyAdapter(true, wordViewModel);

        aSwitch = findViewById(R.id.aswitch_view);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    recyclerView.setAdapter(myAdapterCard);
                }else{
                    recyclerView.setAdapter(myAdapterNormal);
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapterNormal);

        wordViewModel.getAllWordLive().observe(this, new Observer<List<Word>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Word> words) {
                int temp = myAdapterNormal.getItemCount();
                myAdapterNormal.setAllWords(words);
                myAdapterCard.setAllWords(words);
                if (temp != words.size()){
                    myAdapterNormal.notifyDataSetChanged();
                    myAdapterCard.notifyDataSetChanged();
                }
            }
        });

        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] english = {
                        "Hello",
                        "World",
                        "Android",
                        "Google",
                        "Studio",
                        "Project",
                        "Database",
                        "Recycler",
                        "View",
                        "String",
                        "Value",
                        "Integer"
                };
                String[] chinese = {
                        "你好",
                        "世界",
                        "安卓系统",
                        "谷歌公司",
                        "工作室",
                        "项目",
                        "数据库",
                        "回收站",
                        "视图",
                        "字符串",
                        "价值",
                        "整数类型"
                };
                for (int i = 0; i < english.length; i++) {
                    wordViewModel.insertWords(new Word(english[i], chinese[i]));
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordViewModel.deleteAllWord();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi", "你好啊！");
                word.setId(9);
                wordViewModel.updateWords(word);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("", "");
                word.setId(7);
                wordViewModel.deleteWords(word);
            }
        });
    }

}