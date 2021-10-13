package com.my.project.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ViewSample extends AppCompatActivity implements Handler.Callback {

    private ProgressBar progressBar;
    private int progress;
    private int secondaryProgress;
    private Handler handler;

    private AlertDialog alertDialogCustomize;

    private List<Person> spinnerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sample);

        ScrollView scrollViewGlobal = findViewById(R.id.sv_global);
        Button scrollToBottom = findViewById(R.id.btn_to_bottom);
        scrollToBottom.setOnClickListener(button ->
                scrollViewGlobal.fullScroll(ScrollView.FOCUS_DOWN));

        // 将焦点定位到TextView上以显示跑马灯的效果
        TextView focusable = findViewById(R.id.tv_focusable);
        focusable.requestFocus();

        Button btn = findViewById(R.id.btn_in_view_sample);
        btn.setOnClickListener(view ->
                Toast.makeText(this, "It works!", Toast.LENGTH_SHORT).show());

        RadioGroup radioGroup = findViewById(R.id.radio_group_in_view_sample);
        radioGroup.setOnCheckedChangeListener((radio, checked) -> {
            RadioButton radioButton = findViewById(checked);
            Toast.makeText(this, "You checked: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
        });

        CheckBox checkBox = findViewById(R.id.cb_in_view_sample);
        checkBox.setOnCheckedChangeListener((check, checked) ->
                Toast.makeText(this, checked ? "Checked" : "Unchecked", Toast.LENGTH_SHORT).show());

        ImageView imageView = findViewById(R.id.iv_in_view_sample);
        Glide.with(this)
                .load("https://www.bing.com/th?id=OHR.SWColorado_ZH-CN2381176407_1920x1200.jpg&rf=LaDigue_1920x1200.jpg")
                .placeholder(R.drawable.loading)
                .into(imageView);

        ToggleButton toggleButton = findViewById(R.id.tbtn_in_view_sample);
        toggleButton.setOnCheckedChangeListener(((buttonView, isChecked) ->
                Toast.makeText(this, "Toggle button: " + isChecked, Toast.LENGTH_SHORT).show()));

        Switch sw = findViewById(R.id.sw_in_view_sample);
        sw.setOnCheckedChangeListener(((switchView, isChecked) ->
                Toast.makeText(this, "Toggle button: " + isChecked, Toast.LENGTH_SHORT).show()));

        progressBar = findViewById(R.id.pb_in_view_sample);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        progressBar.setSecondaryProgress(0);
        handler = new Handler(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 50);
                handler.sendEmptyMessage(1);
            }
        }, 0);

        SeekBar seekBar = findViewById(R.id.sb_in_view_sample);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println("=====SeekBar progress: " + progress + ", from user: " + fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(ViewSample.this, "Tracking start: " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(ViewSample.this, "Tracking end: " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        RatingBar ratingBar = findViewById(R.id.rb_in_view_sample);
        ratingBar.setOnRatingBarChangeListener((ratingBarView, rating, fromUser) ->
                Toast.makeText(this, "Rating: " + ratingBarView.getRating() + ", from user: " + fromUser, Toast.LENGTH_SHORT).show());

        HorizontalScrollView horizontalScrollView = findViewById(R.id.hsv_in_view_sample);
        Button horizontalScrollLeft = findViewById(R.id.hsv_btn_left);
        horizontalScrollLeft.setOnClickListener(button ->
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_LEFT));
        Button horizontalScrollRight = findViewById(R.id.hsv_btn_right);
        horizontalScrollRight.setOnClickListener(button ->
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT));

        NestedScrollView scrollView = findViewById(R.id.sv_in_view_sample);
        Button scrollTop = findViewById(R.id.sv_btn_top);
        scrollTop.setOnClickListener(button ->
                scrollView.fullScroll(NestedScrollView.FOCUS_UP));
        Button scrollBottom = findViewById(R.id.sv_btn_bottom);
        scrollBottom.setOnClickListener(button ->
                scrollView.fullScroll(NestedScrollView.FOCUS_DOWN));

        Button alert1 = findViewById(R.id.btn_alert_1);
        alert1.setOnClickListener(button -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Info")
                    .setMessage("Some infromation")
                    // dialog icon
                    .setIcon(getResources().getDrawable(R.drawable.ic_launcher_background))
                    .create().show();
        });
        Button alert2 = findViewById(R.id.btn_alert_2);
        alert2.setOnClickListener(button -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            DialogInterface.OnClickListener listener = (dialog, which) -> Toast.makeText(this, "Button " + which + " clicked!", Toast.LENGTH_SHORT).show();
            AlertDialog alertDialog = builder
                    .setTitle("Confirm")
                    .setMessage("Confirm to delete?")
                    .setNegativeButton("No", listener)
                    .setPositiveButton("Yes", listener)
                    .setNeutralButton("Cancel", listener)
                    // button icon
                    .setNeutralButtonIcon(getResources().getDrawable(R.drawable.ic_launcher_foreground))
                    .setCancelable(false) // 设置不能通过点击空白关闭窗口
                    .create();
            alertDialog.setOnShowListener(dialog -> {
                Button yes = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                yes.setTextColor(Color.GREEN);
                Button no = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                no.setTextColor(Color.RED);
                Button cancel = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                cancel.setBackgroundColor(Color.LTGRAY);
                cancel.setTextColor(Color.BLUE);
            });
            alertDialog.show();
        });
        Button alert3 = findViewById(R.id.btn_alert_3);
        alert3.setOnClickListener(button -> {
            CharSequence[] items = new CharSequence[] { "First", "Second", "Third" };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Select One")
                    .setItems(items, ((dialog, which) -> {
                        Toast.makeText(this, "List item " + items[which] + " clicked!", Toast.LENGTH_SHORT).show();
                        if(dialog != null) {
                            dialog.dismiss(); // 关闭dialog
                        }
                    }))
                    .setNeutralButton("Cancel", (dialog, which) -> System.out.println("=====Do nothing"))
                    .setCancelable(false)
                    .create().show();
        });
        Button alert4 = findViewById(R.id.btn_alert_4);
        alert4.setOnClickListener(button -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.alert_dialog_customize_view_layout, null, false);
            Button button1 = view.findViewById(R.id.btn1_in_alert_dialog_view);
            Button button2 = view.findViewById(R.id.btn2_in_alert_dialog_view);
            View.OnClickListener listener = buttonInDialog -> {
                Toast.makeText(this, ((TextView)buttonInDialog).getText() + " clicked", Toast.LENGTH_SHORT).show();
                if(alertDialogCustomize != null && alertDialogCustomize.isShowing()) {
                    alertDialogCustomize.dismiss();
                }
            };
            button1.setOnClickListener(listener);
            button2.setOnClickListener(listener);
            alertDialogCustomize = builder
                    .setTitle("Customize View")
                    .setView(view)
                    .setNegativeButton("No", (dialog, which) -> System.out.println("=====Do nothing"))
                    .setPositiveButton("Yes", (dialog, which) -> System.out.println("=====Do nothing"))
                    .setCancelable(false)
                    .create();
            alertDialogCustomize.show();
        });

        Button listViewSample = findViewById(R.id.btn_list_view_sample);
        listViewSample.setOnClickListener(button ->
                startActivity(new Intent(this, ListViewSample.class)));

        Button simpleListViewSample = findViewById(R.id.btn_simple_list_view_sample);
        simpleListViewSample.setOnClickListener(button ->
                startActivity(new Intent(this, SimpleListViewSample.class)));

        Button customListViewSample = findViewById(R.id.btn_custom_list_view_sample);
        customListViewSample.setOnClickListener(button ->
                startActivity(new Intent(this, CustomListViewSample.class)));

        Button recyclerViewSample = findViewById(R.id.btn_recycler_view_sample);
        recyclerViewSample.setOnClickListener(button ->
                startActivity(new Intent(this, RecyclerViewSample.class)));

        Button popupAsDropDown = findViewById(R.id.btn_popup_as_drop_down_sample);
        popupAsDropDown.setOnClickListener(button -> {
            PopupWindow popupWindow = createPopupWindow();
            popupWindow.showAsDropDown(popupAsDropDown, 20, 20);
        });

        Button popupAtCenter = findViewById(R.id.btn_popup_at_center_sample);
        popupAtCenter.setOnClickListener(button -> {
            PopupWindow popupWindow = createPopupWindow();
            popupWindow.showAtLocation(popupAtCenter, Gravity.CENTER, 0, 0);
        });

        Spinner spinner = findViewById(R.id.spinner_sample);
        spinnerItems = new ArrayList<>();
        spinnerItems.add(new Person(R.drawable.avatar, "Tom"));
        spinnerItems.add(new Person(R.drawable.avatar, "Jerry"));
        spinnerItems.add(new Person(R.drawable.avatar, "Hans"));
        BaseAdapter adapter = new MyAdapter();
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewSample.this, "Clicked " + ((TextView)(view.findViewById(R.id.sp_item_name))).getText() + " " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ViewSample.this, "No item clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class Person {
        public Person(Integer avatar, String name) {
            this.avatar = avatar;
            this.name = name;
        }
        Integer avatar;
        String name;
    }

    static class ViewHolder {
        ImageView avatar;
        TextView name;
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return spinnerItems == null ? 0 : spinnerItems.size();
        }

        @Override
        public Object getItem(int position) {
            return spinnerItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.spinner_item_layout, parent, false);
                holder = new ViewHolder();
                holder.avatar = view.findViewById(R.id.sp_item_avatar);
                holder.name = view.findViewById(R.id.sp_item_name);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            Person data = spinnerItems.get(position);
            holder.avatar.setImageDrawable(getResources().getDrawable(data.avatar));
            holder.name.setText(data.name);
            return view;
        }
    }

    private PopupWindow createPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.popup_window_view_layout, null, false);
        PopupWindow popupWindow = new PopupWindow(this);
        Button close = view.findViewById(R.id.btn_close_in_popup_window);
        close.setOnClickListener(c -> {
            if(popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 点击空白处或返回键时可以关闭弹窗
        popupWindow.setFocusable(true);
        return popupWindow;
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case 1:
                if(progress == 100) {
                    progress = 0;
                } else {
                    progress = progress + 1;
                }
                if(secondaryProgress == 100) {
                    secondaryProgress = 0;
                } else {
                    secondaryProgress = secondaryProgress + 2;
                }
                progressBar.setProgress(progress);
                progressBar.setSecondaryProgress(secondaryProgress);
                break;
            default:
                break;
        }
        return false;
    }
}