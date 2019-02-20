package com.example.android.homework02;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;


public class GeneratedPasswords extends MainActivity {

    ScrollView scrollView;
    LinearLayout threadLinearLayout;
    LinearLayout asyncLinearLayout;
    ArrayList<String> threadArray;
    ArrayList<String> asyncArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_passwords);
        scrollView = findViewById(R.id.asyncScrollView);
        threadLinearLayout = findViewById(R.id.threadLinearLayout);
        asyncLinearLayout = findViewById(R.id.asyncLinearLayout);

        threadArray = new ArrayList<>();
        threadArray = getIntent().getStringArrayListExtra(MainActivity.THREAD_KEY);

        asyncArray = new ArrayList<>();
        asyncArray = getIntent().getStringArrayListExtra(MainActivity.ASYNC_KEY);

        if(threadArray.size()>0 && asyncArray.size()>0){
            new GetAsyncPassword().execute();
            threadpool.execute(new GetThreadPassword());
        }

        findViewById(R.id.finishedButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneratedPasswords.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    class GetThreadPassword implements Runnable {
        @Override
        public void run() {
            LayoutInflater layoutInflater = LayoutInflater.from(GeneratedPasswords.this);
            for (int x = 0; x < threadArray.size(); x++) {
                View view = layoutInflater.inflate(R.layout.scrollview_text, threadLinearLayout, false);
                TextView textview = view.findViewById(R.id.threadTextView);
                textview.setText(threadArray.get(x));
                threadLinearLayout.addView(view);
            }
        }
    }

    class GetAsyncPassword extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            LayoutInflater layoutInflater = LayoutInflater.from(GeneratedPasswords.this);
            for (int x = 0; x < asyncArray.size(); x++) {
                View view = layoutInflater.inflate(R.layout.scrollview_text, asyncLinearLayout, false);
                TextView textview = view.findViewById(R.id.threadTextView);
                textview.setText(asyncArray.get(x));
                asyncLinearLayout.addView(view);
            }
            return null;
        }
    }
}
