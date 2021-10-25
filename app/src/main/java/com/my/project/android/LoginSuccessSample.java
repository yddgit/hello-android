package com.my.project.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginSuccessSample extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText inputName;
    private List<Dog> dogs;
    private List<Dog> viewedDogs;
    private MyAdapter<Dog> adapter;

    private Button filter;
    private PopupWindow popupWindow;
    private List<Dog> popupDogs;
    private MyAdapter<Dog> popupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success_sample);
        recyclerView = findViewById(R.id.rv_in_login_success_sample);
        inputName = findViewById(R.id.et_input_name);
        dogs = new ArrayList<>();
        viewedDogs = new ArrayList<>();

        String[] types = { "阿拉斯加", "哈士奇", "萨摩耶", "狼狗", "柴犬"};
        for (int i = 0; i < 30; i++) {
            Dog dog = new Dog("狗狗" + i, types[ i % types.length]);
            dogs.add(dog);
        }
        viewedDogs.addAll(dogs);

        adapter = new MyAdapter<>(viewedDogs);
        recyclerView.setAdapter(adapter);

        // 必须设置滑动方向
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        // 分隔线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        adapter.notifyDataSetChanged();

        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("Dog Name Input", "before text changed!");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("Dog Name Input", "on text changed!");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("Dog Name Input", "after text changed!");
                viewedDogs.clear();
                String input = inputName.getText().toString();
                if(input.isEmpty()) {
                    viewedDogs.addAll(dogs);
                } else {
                    for (Dog dog : dogs) {
                        if(dog.name.contains(input)) {
                            viewedDogs.add(dog);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        filter = findViewById(R.id.btn_filter);
        popupDogs = new ArrayList<>();
        popupDogs.addAll(dogs);
        popupAdapter = new MyAdapter<>(popupDogs, (Dog data) -> {
            if(popupWindow != null && popupWindow.isShowing()) {
                inputName.setText(data.name);
                popupWindow.dismiss();
            }
        });
        filter.setOnClickListener(button -> show());
    }

    private void show() {
        popupDogs.clear();
        popupDogs.addAll(dogs);

        View view = getLayoutInflater().inflate(R.layout.activity_login_success_sample, null, false);
        view.setBackgroundColor(Color.YELLOW);

        RecyclerView popupRecyclerView = view.findViewById(R.id.rv_in_login_success_sample);
        view.findViewById(R.id.btn_filter).setVisibility(View.GONE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        popupRecyclerView.setLayoutManager(layoutManager);
        popupRecyclerView.setAdapter(popupAdapter);
        popupRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(700);
        popupWindow.setHeight(1200);
        popupWindow.showAtLocation(this.filter, Gravity.CENTER, 0, 0);

        EditText filterInput = view.findViewById(R.id.et_input_name);
        filterInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("Dog Name Input", "before text changed!");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("Dog Name Input", "on text changed!");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("Dog Name Input", "after text changed!");
                popupDogs.clear();
                String input = filterInput.getText().toString();
                if(input.isEmpty()) {
                    popupDogs.addAll(dogs);
                } else {
                    for (Dog dog : dogs) {
                        if(dog.name.contains(input)) {
                            popupDogs.add(dog);
                        }
                    }
                }
                popupAdapter.notifyDataSetChanged();
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout;
        private final TextView name;
        private final TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.layout = itemView.findViewById(R.id.rv_item_layout);
            this.name = itemView.findViewById(R.id.rv_item_dog_name);
            this.type = itemView.findViewById(R.id.rv_item_dog_type);
        }
    }

    class MyAdapter<T extends ViewModel<ViewHolder>> extends RecyclerView.Adapter<ViewHolder> {

        private final List<T> viewData;
        private final ItemClickListener<T> itemClickListener;

        public MyAdapter(List<T> viewData) {
            this.viewData = viewData;
            this.itemClickListener = null;
        }

        public MyAdapter(List<T> viewData, ItemClickListener<T> itemClickListener) {
            this.viewData = viewData;
            this.itemClickListener = itemClickListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.login_success_list_item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ViewModel<ViewHolder> data = viewData.get(position);
            if(itemClickListener != null) {
                holder.layout.setOnClickListener(v -> itemClickListener.onClick((T)data));
            } else {
                holder.layout.setOnClickListener(v ->
                        Toast.makeText(LoginSuccessSample.this, "Clicked " + position, Toast.LENGTH_SHORT).show());
            }
            data.render(holder);
        }

        @Override
        public int getItemCount() {
            return viewData == null ? 0 : viewData.size();
        }
    }

    static class Dog implements ViewModel<ViewHolder> {
        String name;
        String type;

        public Dog(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public void render(ViewHolder holder) {
            holder.name.setText(this.name);
            holder.type.setText(this.type);
        }
    }

    @FunctionalInterface
    interface ViewModel<T extends RecyclerView.ViewHolder> {
        /**
         * 数据如何渲染
         * @param holder view holder
         */
        void render(T holder);
    }

    @FunctionalInterface
    interface ItemClickListener<T extends ViewModel<ViewHolder>> {
        /**
         * 列表项点击事件
         * @param data 被点击的数据
         */
        void onClick(T data);
    }
}