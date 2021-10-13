package com.my.project.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class IntentFormSample extends AppCompatActivity {

    public static final String ACTION_ADD = "com.my.project.android.IntentFormSample.ADD";
    public static final String ACTION_EDIT = "com.my.project.android.IntentFormSample.EDIT";

    public static final int RES_FAIL = 0;
    public static final int RES_SUCCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_form_sample);

        setResult(RES_FAIL);

        Intent intent = getIntent();
        String requestData = intent.getStringExtra("data");
        TextView request = findViewById(R.id.tv_request_in_intent_form_sample);
        request.setText(requestData);

        Button returnButton = findViewById(R.id.btn_return_in_intent_form_sample);
        returnButton.setOnClickListener(button -> {
            String result;
            if(ACTION_ADD.equals(intent.getAction())) {
                result = "Add Success";
            } else {
                result = "Edit Success";
            }
            intent.putExtra("result", result);
            setResult(RES_SUCCESS, intent);
            finish();
        });
    }
}