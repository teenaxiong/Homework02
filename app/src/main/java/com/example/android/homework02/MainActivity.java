package com.example.android.homework02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ExecutorService threadpool;
    TextView threadCount;
    TextView threadLenth;
    TextView asyncCount;
    TextView asyncLenth;
    SeekBar threadCountSeekBar;
    SeekBar threadLengthSeekBar;
    SeekBar asyncCountSeekBar;
    SeekBar asyncLengthSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   threadpool = Executors.newFixedThreadPool(2);

        threadCountSeekBar = findViewById(R.id.threadCountSeekBar);
        threadLengthSeekBar = findViewById(R.id.threadLabelSeekBar);
        asyncCountSeekBar = findViewById(R.id.asyncCountSeekBar);
        asyncLengthSeekBar = findViewById(R.id.asyncLengthSeekBar);
        threadCount = findViewById(R.id.threadCountTextView);
        threadLenth = findViewById(R.id.threadLengthTextView);
        asyncCount = findViewById(R.id.asyncCountTextView);
        asyncLenth = findViewById(R.id.asyncLengthTextView);


        threadCountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i + 1;
                threadCount.setText(String.valueOf(i));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        threadLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i + 7;
                threadLenth.setText(String.valueOf(i));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        asyncCountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i + 1;
                asyncCount.setText(String.valueOf(i));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        asyncLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i + 7;
                asyncLenth.setText(String.valueOf(i));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //threadpool.execute();
            }
        });
    }

}
