package com.example.android.homework02;


import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GeneratedPasswords extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_passwords);
        scrollView = (ScrollView) findViewById(R.id.asyncScrollView);



//        ArrayList <String> array = new ArrayList<>();
//        linearLayout = findViewById(R.id.threadLinearLayout);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        TextView textView;
//
//        array = getIntent().getExtras().getStringArrayList(MainActivity.ARRAY_KEY);
//        for(int x=0; x<array.size(); x++){
//            View view =  inflater.inflate(R.layout.scrollview_text, linearLayout, false);
//
//            textView = view.findViewById(R.id.threadTextView);
//
//            textView.setText(array.get(x));
//            linearLayout.addView(view);
//
//        }addView


    }
}
