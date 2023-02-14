package com.example.uidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Logger;

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

    String yw,sx,yy = "";

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

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(R.string.btn1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(R.string.btn2);
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    display.setText("On");
                }else{
                    display.setText("Off");
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                if (TextUtils.isEmpty(s)){
                    s = "0";
                }
                progressBar.setProgress(Integer.parseInt(s));
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("Test-sl", String.valueOf(checkedId));
                if (checkedId == R.id.radio_android){
                    imageView.setImageResource(R.drawable.android);
                }else{
                    imageView.setImageResource(R.drawable.apple);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                display.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        checkBoxYW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    yw = "语文";
                }
                display.setText(yw+sx+yy);
            }
        });
        checkBoxSX.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sx = "数学";
                }
                display.setText(yw+sx+yy);
            }
        });
        checkBoxYY.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    yy = "英语";
                }
                display.setText(yw+sx+yy);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), String.valueOf(rating)+"星评价!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}