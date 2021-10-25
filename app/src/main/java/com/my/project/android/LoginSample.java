package com.my.project.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginSample extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sample);
        // 隐藏ActionBar
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        } else {
            getActionBar().hide();
        }

        username = findViewById(R.id.et_login_username);
        password = findViewById(R.id.et_login_password);
        login = findViewById(R.id.btn_login);

        login.setOnClickListener(button -> {
            if(username.getText().toString().isEmpty()) {
                Toast.makeText(this, "账号必须输入！", Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.getText().toString().isEmpty()) {
                Toast.makeText(this, "密码必须输入！", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(this, LoginSuccessSample.class));
        });
    }
}