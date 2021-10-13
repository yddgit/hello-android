package com.my.project.android;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleListViewSample extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view_sample);

        listView = findViewById(R.id.lv_in_simple_list_view_sample);

        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("avatar", R.drawable.avatar);
            data.put("name", "Person " + (i+1));
            data.put("description", "My personal NO. is " + (i+1));
            items.add(data);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this, items, R.layout.simple_list_view_item_layout,
                new String[] {"avatar", "name", "description"},
                new int[] {R.id.lv_item_avatar, R.id.lv_item_name, R.id.lv_item_description});
        listView.setAdapter(adapter);
    }
}