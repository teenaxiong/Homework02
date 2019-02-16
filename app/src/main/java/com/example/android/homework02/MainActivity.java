package com.example.android.homework02;

import android.content.Intent;
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
    SeekBar.OnSeekBarChangeListener seekListener;
    ArrayList<String> threadArrayList;
    ProgressBar progressBar;
    int progressStatus;
    Handler handler;
    static String ARRAY_KEY = "ARRAY";
    Handler gHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        threadpool = Executors.newFixedThreadPool(2);

        threadCountSeekBar = findViewById(R.id.threadCountSeekBar);
        threadLengthSeekBar = findViewById(R.id.threadLabelSeekBar);
        asyncCountSeekBar = findViewById(R.id.asyncCountSeekBar);
        asyncLengthSeekBar = findViewById(R.id.asyncLengthSeekBar);
        threadCount = findViewById(R.id.threadCountTextView);
        threadLenth = findViewById(R.id.threadLengthTextView);
        asyncCount = findViewById(R.id.asyncCountTextView);
        asyncLenth = findViewById(R.id.asyncLengthTextView);
        progressBar = findViewById(R.id.progressBar);
        progressStatus = 0;
        handler = new Handler();
        gHandler = new Handler();

        threadArrayList = new ArrayList<String>();

        seekListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (seekBar.getId()) {
                    case R.id.threadCountSeekBar:
                        i = i + 1;
                        threadCount.setText(String.valueOf(i));
                        break;
                    case R.id.threadLabelSeekBar:
                        i = i + 7;
                        threadLenth.setText(String.valueOf(i));
                        break;
                    case R.id.asyncCountSeekBar:
                        i = i + 1;
                        asyncCount.setText(String.valueOf(i));
                        break;
                    case R.id.asyncLengthSeekBar:
                        i = i + 7;
                        asyncLenth.setText(String.valueOf(i));
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        threadCountSeekBar.setOnSeekBarChangeListener(seekListener);
        threadLengthSeekBar.setOnSeekBarChangeListener(seekListener);
        asyncCountSeekBar.setOnSeekBarChangeListener(seekListener);
        asyncLengthSeekBar.setOnSeekBarChangeListener(seekListener);


        findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                threadpool.execute(new threadGenerator());

                // Update the progress bar
        /*        handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                       // imageView.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.setProgress(message.what);
                        return false;
                    }
                });*/



                handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        threadArrayList = (ArrayList) message.obj;
                        Intent intent = new Intent(MainActivity.this, GeneratedPasswords.class);
                        intent.putStringArrayListExtra(ARRAY_KEY, threadArrayList);
                        startActivity(intent);
                        return false;
                    }
                });

            }
        });
    }

    class threadGenerator implements Runnable {
        @Override
        public void run() {

            /*progressStatus = 0;
            while(progressStatus<100){
                progressStatus++;
                Message m = new Message();
                m.what = progressStatus;
                handler.sendMessage(m);
                try {
                    // Sleep for 30 milliseconds.
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/

            int a = Integer.parseInt(threadLenth.getText().toString());
            int x = 0;
            while (Integer.parseInt(threadCount.getText().toString()) > x) {
                String b = Util.getPassword(a);
                threadArrayList.add(b);
                x++;
            }
            Message message = new Message();
            message.obj = threadArrayList;
            handler.sendMessage(message);
        }
    }
}


