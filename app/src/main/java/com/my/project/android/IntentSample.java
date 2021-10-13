package com.my.project.android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class IntentSample extends AppCompatActivity {

    public static final int REQ_ADD = 1;
    public static final int REQ_EDIT = 2;
    public static final int REQ_CAP = 3;
    public static final int REQ_TAKE_PHOTO = 4;

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sample);

        result = findViewById(R.id.tv_response_in_intent_sample);

        Button add = findViewById(R.id.btn_to_intent_form_add);
        add.setOnClickListener(button -> {
            Intent intent = new Intent(IntentFormSample.ACTION_ADD);
            intent.putExtra("data", "DEFAULT DATA");
            // 目标activity必须setResult
            startActivityForResult(intent, REQ_ADD);
        });

        Button edit = findViewById(R.id.btn_to_intent_form_edit);
        edit.setOnClickListener(button -> {
            Intent intent = new Intent(IntentFormSample.ACTION_EDIT);
            intent.putExtra("data", "ORIGINAL DATA");
            // 目标activity必须setResult
            startActivityForResult(intent, REQ_EDIT);
        });

        // 给10086打电话
        Button tel = findViewById(R.id.btn_to_tel);
        tel.setOnClickListener(button -> {
            Uri uri = Uri.parse("tel:10086");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        });
        // 给10086发送内容为Hello的短信
        Button sendTo = findViewById(R.id.btn_to_send_to);
        sendTo.setOnClickListener(button -> {
            Uri uri = Uri.parse("smsto:10086");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", "Hello");
            startActivity(intent);
        });
        // 发送彩信
        Button send = findViewById(R.id.btn_to_send);
        send.setOnClickListener(button -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra("sms_body", "Hello");
            Uri uri = Uri.parse("content://media/external/images/media/23");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setType("image/png");
            startActivity(intent);
        });
        // 打开百度
        Button view = findViewById(R.id.btn_to_view);
        view.setOnClickListener(button -> {
            Uri uri = Uri.parse("http://www.baidu.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        // 给test@example.com发送邮件
        Button mailTo = findViewById(R.id.btn_to_mail_to);
        mailTo.setOnClickListener(button -> {
            Uri uri = Uri.parse("mailto:test@example.com");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            startActivity(intent);
        });
        // 给test@example.com发送内容为Hello的邮件
        Button mail = findViewById(R.id.btn_to_mail);
        mail.setOnClickListener(button -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, "test@example.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "Hello");
            intent.setType("text/plain");
            startActivity(intent);
        });
        // 给多人发邮件
        Button mailMultiple = findViewById(R.id.btn_to_mail_multiple);
        mailMultiple.setOnClickListener(button -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String[] to = { "to1@example.com", "to2@example.com" };
            String[] cc = { "cc1@example.com", "cc2@example.com" };
            String[] bcc = { "bcc1@example.com", "bcc2@example.com" };
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_CC, cc);
            intent.putExtra(Intent.EXTRA_BCC, bcc);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "Hello Android");
            intent.setType("message/rfc822");
            startActivity(intent);
        });
        // 打开地图坐标
        Button map = findViewById(R.id.btn_to_map);
        map.setOnClickListener(button -> {
            Uri uri = Uri.parse("geo:39.7,116.1");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        // 路径规划
        Button mapPath = findViewById(R.id.btn_to_map_path);
        mapPath.setOnClickListener(button -> {
            Uri uri = Uri.parse("amapuri://route/plan/?sid=&slat=39.9&slon=116.3&sname=A&did=&dlat=39.9&dlon=116.4&dname=B&dev=0&t=0");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        // 播放音乐
        Button music = findViewById(R.id.btn_to_music);
        music.setOnClickListener(button -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("file://" + Environment.getExternalStorageDirectory().getPath() + "/Music/梦然-少年.mp3");
            intent.setDataAndType(uri, "audio/mp3");
            startActivity(intent);
        });
        // 拍照
        Button camera = findViewById(R.id.btn_to_camera);
        camera.setOnClickListener(button -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQ_CAP);
        });
        // 拍照并存储(result中返回的Intent是null)
        Button takePhoto = findViewById(R.id.btn_to_take_photo);
        takePhoto.setOnClickListener(button -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hello-android-" + System.currentTimeMillis() + ".jpg";
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(filePath)));
            startActivityForResult(intent, REQ_TAKE_PHOTO);
        });
        // 进入无线网络设置界面
        Button wifiSet = findViewById(R.id.btn_to_wifi_set);
        wifiSet.setOnClickListener(button -> {
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivity(intent);
        });
        // 安装APK
        Button install = findViewById(R.id.btn_to_install_apk);
        install.setOnClickListener(button -> {
            Uri uri = Uri.fromParts("package", "com.my.project.android", null);
            Intent intent = new Intent(Intent.ACTION_PACKAGE_ADDED, uri);
            startActivity(intent);
        });
        // 卸载APK
        Button uninstall = findViewById(R.id.btn_to_uninstall_apk);
        uninstall.setOnClickListener(button -> {
            Uri uri = Uri.fromParts("package", "com.my.project.android", null);
            Intent intent = new Intent(Intent.ACTION_DELETE, uri);
            startActivity(intent);
        });
        // 打开联系人
        Button contact = findViewById(R.id.btn_to_contact);
        contact.setOnClickListener(button -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Contacts.People.CONTENT_URI);
            startActivity(intent);
        });
        // 查看指定联系人
        Button contactId = findViewById(R.id.btn_to_contact_id);
        contactId.setOnClickListener(button -> {
            //id即为联系人id
            Uri uri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, 1);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
        });
        // 打开另一程序
        Button activity = findViewById(R.id.btn_to_activity);
        activity.setOnClickListener(button -> {
            ComponentName cn = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.SplashActivity");
            Intent intent = new Intent();
            intent.setComponent(cn);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.setAction(Intent.ACTION_MAIN);
            startActivity(intent);
        });
        // 打开录音机
        Button record = findViewById(R.id.btn_to_record);
        record.setOnClickListener(button -> {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            startActivity(intent);
        });
        // 打开录音机
        Button search = findViewById(R.id.btn_to_search);
        search.setOnClickListener(button -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "Android开发");
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String resultData = (data == null) ? "No result!" : data.getStringExtra("result");
        String message = "requestCode=" + requestCode + ", resultCode=" + resultCode + ", " + resultData;
        result.setText(message);
        switch(requestCode) {
            case REQ_ADD:
                if(resultCode == IntentFormSample.RES_FAIL) {
                    Toast.makeText(this, "ADD FAIL: " + message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "ADD SUCCESS: " + message, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQ_EDIT:
                if(resultCode == IntentFormSample.RES_FAIL) {
                    Toast.makeText(this, "EDIT FAIL: " + message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "EDIT SUCCESS: " + message, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQ_CAP:
            case REQ_TAKE_PHOTO:
                Bitmap bitmap = data == null ? null : (Bitmap) data.getExtras().get("data");
                message = bitmap == null ? "No image" : ("Image size: " + bitmap.getWidth() + "x" + bitmap.getHeight());
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}