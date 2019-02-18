package com.example.android.homework02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ExecutorService threadpool;
    TextView threadPWcountLabel;
    TextView threadTitle;
    TextView threadPWlengthLabel;
    TextView asyncTitle;
    TextView pbar;
    TextView asyncPWlengthLabel;
    TextView threadCount;
    TextView threadLenth;
    TextView asyncCount;
    TextView asyncLenth;
    SeekBar threadCountSeekBar;
    SeekBar threadLengthSeekBar;
    SeekBar asyncCountSeekBar;
    SeekBar asyncLengthSeekBar;
    ProgressBar progressBar;
    List list = new ArrayList();

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

//                displayUsingThread = new displayUsingThread();
                new MyBar().execute(1000);

//                Intent intent = new Intent(MainActivity.this, GeneratedPasswords.class);
//                startActivity(intent);
            }
        });
        }
        private class MyBar extends AsyncTask<Integer, Integer, Integer> {
            ProgressBar bar;

            @Override
            protected void onPreExecute() {
                bar = (ProgressBar) findViewById(R.id.pbar);
                bar.setProgress(0);
                bar.setMax(100);
            }

            @Override
            protected Integer doInBackground(Integer... params) {
                int count = params[0];
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < count; j++) {
                    }
                    publishProgress(i + 1);
                }
                return count*100;
            }
            @Override
            protected void onProgressUpdate(Integer... values) {
                threadCount.setVisibility(View.INVISIBLE);
                threadLenth.setVisibility(View.INVISIBLE);
                threadLengthSeekBar.setVisibility(View.INVISIBLE);
                threadCountSeekBar.setVisibility(View.INVISIBLE);
                asyncCount.setVisibility(View.INVISIBLE);
                asyncCountSeekBar.setVisibility(View.INVISIBLE);
                asyncLenth.setVisibility(View.INVISIBLE);
                asyncLengthSeekBar.setVisibility(View.INVISIBLE);
//                threadPWcountLabel.setVisibility(View.INVISIBLE);
//                threadTitle.setVisibility(View.INVISIBLE);
//                threadPWlengthLabel.setVisibility(View.INVISIBLE);
//                asyncTitle.setVisibility(View.INVISIBLE);
//                asyncPWlengthLabel.setVisibility(View.INVISIBLE);
//                pbar.setVisibility(View.INVISIBLE);
                bar.setVisibility(View.VISIBLE);
                bar.setProgress(values[0]);
                Intent intent = new Intent(MainActivity.this, GeneratedPasswords.class);
                startActivity(intent);

            }

            protected void onPostExecute(Void aVoid) {

            }
    }

}
