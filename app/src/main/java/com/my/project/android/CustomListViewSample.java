package com.my.project.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewSample extends AppCompatActivity {

    private ListView listView;
    private List<Person> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view_sample);

        listView = findViewById(R.id.lv_in_custom_list_view_sample);

        items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Person data = new Person();
            data.checked = false;
            data.avatar = R.drawable.avatar;
            data.name = "Person " + (i+1);
            data.description = "My personal NO. is " + (i+1);
            items.add(data);
        }

        listView.setAdapter(new MyAdapter());

        TextView header = new TextView(this);
        header.setText("To Last");
        header.setHeight(150);
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        header.setOnClickListener(view -> listView.setSelection(listView.getCount()));
        listView.addHeaderView(header);

        TextView footer = new TextView(this);
        footer.setText("To First");
        footer.setHeight(150);
        footer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        footer.setOnClickListener(view -> listView.setSelection(0));
        listView.addFooterView(footer);

        listView.setOnItemClickListener(((parent, view, position, id) ->
                Toast.makeText(this,"Clicked " + position,Toast.LENGTH_SHORT).show()));
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            Log.d("CustomListViewSample", "Render " + position);

            ViewHolder holder;
            if(view == null) {
                int layout = (position % 2 == 0) ? R.layout.custom_list_view_even_item_layout : R.layout.custom_list_view_odd_item_layout;
                view = getLayoutInflater().inflate(layout, parent, false);
                holder = new ViewHolder();
                holder.checked = view.findViewById(R.id.lv_item_checked);
                holder.avatar = view.findViewById(R.id.lv_item_avatar);
                holder.name = view.findViewById(R.id.lv_item_name);
                holder.description = view.findViewById(R.id.lv_item_description);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            Person data = items.get(position);
            // [即将显示的行]会复用[已经滑出屏幕的行]的组件对象
            // 如果不清除onCheckedChange事件，则后面一句setChecked方法会触发[已经滑出屏幕的行]的onCheckedChange事件
            // 从而导致[已经滑出屏幕的行]的数据被[即将显示的行]的数据覆盖
            holder.checked.setOnCheckedChangeListener(null);
            holder.checked.setChecked(data.checked);
            holder.checked.setOnCheckedChangeListener(((checkBox, isChecked) -> {

                Log.d("CustomListViewSample", "Checked Changed " + position + ", " + isChecked);

                data.checked = isChecked;
            }));
            holder.avatar.setImageDrawable(getResources().getDrawable(data.avatar));
            holder.name.setText(data.name);
            holder.description.setText(data.description);

            return view;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    static class Person {
        Boolean checked;
        Integer avatar;
        String name;
        String description;
    }

    static class ViewHolder {
        CheckBox checked;
        ImageView avatar;
        TextView name;
        TextView description;
    }
}