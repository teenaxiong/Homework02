//Teena Xiong
// Shikha Bhattarai
//
//hw 02
//Password Generator
package com.example.android.homework02;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
    int countasync;
    int lengthasync;
    int countthread;
    int lengththread;
    Handler handler;
    ProgressBar bar;
    ArrayList<String> asyncArrayList;
    ArrayList<String> threadArrayList;
    static String THREAD_KEY = "THREAD";
    static String ASYNC_KEY = "ASYNC";
    Intent intent;
    boolean completeTask = false;
    int threadCountInteger;
    int asyncCountInteger;
    SeekBar.OnSeekBarChangeListener seekListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asyncArrayList = new ArrayList<>();
        threadArrayList = new ArrayList<>();
        threadpool = Executors.newFixedThreadPool(2);

        threadCountSeekBar = findViewById(R.id.threadCountSeekBar);
        threadLengthSeekBar = findViewById(R.id.threadLabelSeekBar);
        asyncCountSeekBar = findViewById(R.id.asyncCountSeekBar);
        asyncLengthSeekBar = findViewById(R.id.asyncLengthSeekBar);
        threadCount = findViewById(R.id.threadCountTextView);
        threadLenth = findViewById(R.id.threadLengthTextView);
        asyncCount = findViewById(R.id.asyncCountTextView);
        asyncLenth = findViewById(R.id.asyncLengthTextView);
        intent = new Intent(MainActivity.this, GeneratedPasswords.class);

      /*  threadCountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        });*/

        seekListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch(seekBar.getId()){
                    case R.id.threadCountSeekBar:
                        i = i + 1;
                        threadCount.setText(String.valueOf(i));
                        countthread = i;
                        break;
                    case R.id.threadLabelSeekBar:
                        i = i + 7;
                        threadLenth.setText(String.valueOf(i));
                        lengththread = i;
                        break;
                    case R.id.asyncCountSeekBar:
                        i = i + 1;
                        asyncCount.setText(String.valueOf(i));
                        countasync = i;
                        break;
                    case R.id.asyncLengthSeekBar:
                        i = i + 7;
                        asyncLenth.setText(String.valueOf(i));
                        lengthasync = i;
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
                String threadCountString = threadCount.getText().toString();
                String threadLengthString = threadLenth.getText().toString();
                String asyncCountString = asyncCount.getText().toString();
                String asyncLengthString = asyncLenth.getText().toString();
                if (threadCountString.equals("") || threadLengthString.equals("") || asyncCountString.equals("") || asyncLengthString.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please slide the bar for each one.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    bar = findViewById(R.id.pbar);
                    bar.setProgress(0);
                    threadCountInteger = Integer.parseInt(threadCountString);
                    asyncCountInteger = Integer.parseInt(asyncCountString);
                    bar.setMax(asyncCountInteger + threadCountInteger);
                    //setMax is the total count from both asyncCountSeekBar and threadCount


                    findViewById(R.id.threadTitle).setVisibility(View.INVISIBLE);
                    findViewById(R.id.asyncTitle).setVisibility(View.INVISIBLE);
                    findViewById(R.id.asyncLengthTextView).setVisibility(View.INVISIBLE);
                    findViewById(R.id.asyncCountTextView).setVisibility(View.INVISIBLE);
                    findViewById(R.id.threadPWcountLabel).setVisibility(View.INVISIBLE);
                    findViewById(R.id.asyncPWcountLabel).setVisibility(View.INVISIBLE);
                    findViewById(R.id.threadPWlengthLabel).setVisibility(View.INVISIBLE);
                    findViewById(R.id.asyncPWlengthLabel).setVisibility(View.INVISIBLE);
                    findViewById(R.id.threadCountTextView).setVisibility(View.INVISIBLE);
                    findViewById(R.id.threadLengthTextView).setVisibility(View.INVISIBLE);
                    findViewById(R.id.threadCountSeekBar).setVisibility(View.INVISIBLE);
                    findViewById(R.id.threadLabelSeekBar).setVisibility(View.INVISIBLE);
                    findViewById(R.id.asyncCountSeekBar).setVisibility(View.INVISIBLE);
                    findViewById(R.id.asyncLengthSeekBar).setVisibility(View.INVISIBLE);
                    findViewById(R.id.generateButton).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pbar).setVisibility(View.VISIBLE);


                    new MyBar().execute();
                    // new Thread(new ThreadGenerator()).start();

                    handler = new Handler(new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message message) {

                            switch(message.obj.toString()){
                                case "update":
                                    int i = bar.getProgress();
                                    bar.setProgress(i + 1);
                                    bar.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    threadArrayList = (ArrayList) message.obj;
                                    intent.putStringArrayListExtra(THREAD_KEY, threadArrayList);
                                    if (completeTask) {
                                    bar.setVisibility(View.INVISIBLE);
                                    startActivity(intent);
                                } else completeTask = true;
                            }
                            return false;
                        }
                    });
                    threadpool.execute(new ThreadGenerator());
                }
            }
        });//end of onlick button

    }//end of oncreate

    private class MyBar extends AsyncTask<Integer, Integer, ArrayList> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected ArrayList doInBackground(Integer... params) {
            for (int i = 0; i < countasync; i++) {
                String s = Util.getPassword(lengthasync);
                asyncArrayList.add(s);
                publishProgress(1);
            }
            return asyncArrayList;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int i = bar.getProgress();
            bar.setProgress(i + values[0]);
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList s) {
            intent.putStringArrayListExtra(ASYNC_KEY, s);
            if(completeTask){
                bar.setVisibility(View.INVISIBLE);
                startActivity(intent);
            }else completeTask = true;
        }
    }

    class ThreadGenerator implements Runnable {

        @Override
        public void run() {
            Message message = new Message();
            Util util = new Util();
            for (int i = 0; i < countthread; i++) {
                String s = util.getPassword(lengththread);
                threadArrayList.add(s);
                Message barMessage = new Message();
                barMessage.obj = "update";
                handler.sendMessage(barMessage);
            }
            message.obj = threadArrayList;
            handler.sendMessage(message);
        }
    }
}
