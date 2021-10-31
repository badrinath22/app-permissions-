package com.example.permissioninfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class appInfo2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info2);
        Intent intent=getIntent();
        String permission_names=intent.getStringExtra("permission_names");

        TextView permissions_view=(TextView)findViewById(R.id.permissions_view);
        permissions_view.setText(permission_names);
    }
}