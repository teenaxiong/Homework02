package com.example.android.homework02;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

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
    int countasync;
    int lengthasync;
    int countthread;
    int lengththread;
    int progressStatus;
    Handler handler;
    ProgressBar bar;
    ProgressBar progressBar;
    ArrayList<String> asyncArrayList;
    ArrayList<String> threadArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asyncArrayList = new ArrayList<>();
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
                countthread = i;

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
                lengththread = i;
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
                countasync = i;

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
                lengthasync = i;

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
                bar = (ProgressBar) findViewById(R.id.pbar);
                bar.setProgress(0);
                bar.setMax(2);
                //setMax is the total count from both asyncCountSeekBar and threadCount
                    new MyBar().execute();
                    new Thread(new ThreadGenerator()).start();

            }
        });
        }
        private class MyBar extends AsyncTask<Integer, Integer, ArrayList<String>> {

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected ArrayList doInBackground(Integer... params) {
                Util util = new Util();
                for (int i = 0; i < countasync; i++) {
                    String s =util.getPassword(lengthasync);
                    asyncArrayList.add(s);
                    publishProgress(1);
                }
                return asyncArrayList;
            }
            @Override
            protected void onProgressUpdate(Integer... values) {
                bar.setVisibility(View.VISIBLE);
                int i= bar.getProgress();
                bar.setProgress(i+values[0]);

            }
            @Override
            protected void onPostExecute(ArrayList s) {
                super.onPostExecute(s);
                Intent intent = new Intent(MainActivity.this, GeneratedPasswords.class);
                startActivity(intent);
                Log.d("demo","list="+asyncArrayList);

            }
        }
    class ThreadGenerator implements Runnable {
        static final int STATUS_START = 0x00;
        static final int STATUS_PROGRESS =0x01;
        static final int STATUS_STOP = 0x02;
        @Override
        public void run(){
            Message message = new Message();
            message.what = STATUS_START;
            handler.sendMessage(message);
            Util util = new Util();
            for (int i = 0; i < countthread; i++) {
                String s = util.getPassword(lengththread);
                threadArrayList.add(s);
            }
            Log.d("demo2","list"+threadArrayList);
            message.obj = threadArrayList;
            handler.sendMessage(message);
        }
    }
}
