package com.my.project.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewSample extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sample);

        listView = findViewById(R.id.lv_in_list_view_sample);

        String[] items = { "Item1", "Item2", "Item3", "Item4", "Item5" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_expandable_list_item_1, items);
        listView.setAdapter(adapter);
    }
}