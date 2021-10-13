package com.my.project.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

public class ThreadSample extends AppCompatActivity implements Handler.Callback {

    private static final int NOW = 1;
    private static final int DELAY = 2;
    private static final int WITH_ARGS = 3;

    private Handler handler;
    private Button button1;
    private int count1;
    private Button button2;
    private int count2;
    private Button button3;
    private int count3;
    private Button button4;
    private int count4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_sample);

        button1 = findViewById(R.id.btn1_in_thread_sample);
        // Accessibility content change on non-UI thread. Future Android versions will throw an exception.
        // android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
        button1.setOnClickListener(view -> new Thread(() -> button1.setText("Updated by thread " + (count1++))).start());

        handler = new Handler(this);

        button2 = findViewById(R.id.btn2_in_thread_sample);
        button2.setOnClickListener(view -> handler.sendEmptyMessage(NOW));

        button3 = findViewById(R.id.btn3_in_thread_sample);
        button3.setOnClickListener(view -> handler.sendEmptyMessageDelayed(DELAY, 1000));

        button4 = findViewById(R.id.btn4_in_thread_sample);
        button4.setOnClickListener(view -> {
            Message msg = new Message();
            msg.what = WITH_ARGS;
            msg.obj = System.currentTimeMillis();
            handler.sendMessage(msg);
        });
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case NOW:
                button2.setText("Updated by handler immediately " + (count2++));
                break;
            case DELAY:
                button3.setText("Updated by handler after 1s " + (count3++));
                break;
            case WITH_ARGS:
                button4.setText("Updated by handler with args " + msg.obj + " " + (count4++));
                break;
            default:
                break;
        }
        return false;
    }
}