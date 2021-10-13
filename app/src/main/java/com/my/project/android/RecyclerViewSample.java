package com.my.project.android;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewSample extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Person> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_sample);

        recyclerView = findViewById(R.id.rv_in_recycler_view_sample);

        items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Person data = new Person();
            data.checked = false;
            data.avatar = R.drawable.avatar;
            data.name = "Person " + (i+1);
            data.description = "My personal NO. is " + (i+1);
            items.add(data);
        }

        recyclerView.setAdapter(new MyAdapter());

        // 必须设置滑动方向
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        // 分隔线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // 更换布局管理器
        //GridLayoutManager manager = new GridLayoutManager(this, 3);
        //recyclerView.setLayoutManager(manager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.VERTICAL));

        // 瀑布流(自动占满空间)
        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(manager);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout;
        private final CheckBox checked;
        private final ImageView avatar;
        private final TextView name;
        private final TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.layout = itemView.findViewById(R.id.rv_item);
            this.checked = itemView.findViewById(R.id.rv_item_checked);
            this.avatar = itemView.findViewById(R.id.rv_item_avatar);
            this.name = itemView.findViewById(R.id.rv_item_name);
            this.description = itemView.findViewById(R.id.rv_item_description);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.recycler_view_item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.layout.setOnClickListener(v ->
                    Toast.makeText(RecyclerViewSample.this, "Clicked " + position, Toast.LENGTH_SHORT).show());
            Person data = items.get(position);
            holder.checked.setOnCheckedChangeListener(null);
            holder.checked.setChecked(data.checked);
            holder.checked.setOnCheckedChangeListener(((checkBox, isChecked) -> data.checked = isChecked));
            holder.avatar.setImageDrawable(getResources().getDrawable(data.avatar));
            holder.name.setText(data.name);
            holder.description.setText(data.description);
        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }
    }

    static class Person {
        Boolean checked;
        Integer avatar;
        String name;
        String description;
    }
}