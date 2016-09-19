package com.example.qqq34.permissondemo;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.rxpermisson.PermissionAppCompatActivity;

public class MainActivity extends PermissionAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(R.string.base_permission, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(aBoolean -> {
                    if (aBoolean){
                        Toast.makeText(this,"请求权限成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"请求权限失败",Toast.LENGTH_SHORT).show();
                    }
                },throwable -> {
                    Toast.makeText(this,"异常处理",Toast.LENGTH_SHORT).show();
                });
    }
}
