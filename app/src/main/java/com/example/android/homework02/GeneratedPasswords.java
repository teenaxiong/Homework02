package com.example.android.homework02;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;


public class GeneratedPasswords extends MainActivity {

    ScrollView scrollView;
    LinearLayout linearLayout;
    ArrayList <String> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_passwords);
        scrollView = (ScrollView) findViewById(R.id.asyncScrollView);

    }

    private void displayUsingAsync() {

    }
}
