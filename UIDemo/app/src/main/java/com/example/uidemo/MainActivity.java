package com.example.uidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView display;
    Button btnLeft, btnRight, btn3;
    Switch aSwitch;
    ProgressBar progressBar;
    EditText editText;
    RadioGroup radioGroup;
    ImageView imageView;
    SeekBar seekBar;
    CheckBox checkBoxYW, checkBoxSX, checkBoxYY;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        btnLeft = findViewById(R.id.btn1);
        btnRight = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        aSwitch = findViewById(R.id.sw1);
        progressBar = findViewById(R.id.pb3);
        editText = findViewById(R.id.pb_input);
        radioGroup = findViewById(R.id.rg_logo);
        imageView = findViewById(R.id.iv_logo);
        seekBar = findViewById(R.id.seekBar);
        checkBoxYW = findViewById(R.id.cb1);
        checkBoxSX = findViewById(R.id.cb2);
        checkBoxYY = findViewById(R.id.cb3);
        ratingBar = findViewById(R.id.ratingBar);
    }
}