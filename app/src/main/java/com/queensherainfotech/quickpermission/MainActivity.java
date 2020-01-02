package com.queensherainfotech.quickpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.queensherainfotech.permissionlibrary.OnPermissionListener;
import com.queensherainfotech.permissionlibrary.QuickPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String[] ALL_PERMISSIONS = {
            Manifest.permission.READ_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    Button storage,camera,sms,all;
    QuickPermission permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.btn_camera);
        sms = findViewById(R.id.btn_sms);
        storage = findViewById(R.id.btn_storage);
        all = findViewById(R.id.btn_all);

        permission = new QuickPermission.Builder().with(this)
                .listener(new OnPermissionListener() {
                    @Override public void onAllPermissionsGranted(@NonNull List<String> permissions) {
                    }

                    @Override public void onPermissionsGranted(@NonNull List<String> permissions) {
                    }

                    @Override public void onPermissionsDenied(@NonNull List<String> permissions) {
                    }
                })
                .build();

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!permission.hasPermission(Manifest.permission.CAMERA)) {
                    permission.request(Manifest.permission.CAMERA);
                } else {
                    Toast.makeText(MainActivity.this, Manifest.permission.CAMERA + " already granted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!permission.hasPermission(Manifest.permission.READ_SMS)) {
                    permission.request(Manifest.permission.READ_SMS);
                } else {
                    Toast.makeText(MainActivity.this, Manifest.permission.READ_SMS + " already granted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!permission.hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    permission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    Toast.makeText(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE + " already granted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!permission.hasPermission(ALL_PERMISSIONS)) {
                    permission.request(ALL_PERMISSIONS);
                } else {
                    Toast.makeText(MainActivity.this, "all permissions already granted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.permission.onRequestPermissionsResult(permissions, grantResults);
    }
}
