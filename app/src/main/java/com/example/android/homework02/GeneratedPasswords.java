package com.example.android.homework02;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class GeneratedPasswords extends AppCompatActivity {

    List list = new ArrayList();
    ArrayAdapter arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_passwords);

        listView = (ListView) findViewById(R.id.list_view);
        list.add("test1");
        list.add("test2");
        list.add("test3");
        arrayAdapter = new ArrayAdapter(GeneratedPasswords.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
    }
}
