# QuickPermission
Enable permissions runtime while inside app  using QuickPermission library.

[![](https://jitpack.io/v/com.queensherainfotech/QuickPermission.svg)](https://jitpack.io/#com.queensherainfotech/QuickPermission)


Project level gradle
------
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```


App level gradle
------
```
dependencies {
    ...
    implementation 'com.queensherainfotech:QuickPermission:1.0.0'
}
```

Usage
------

To begin using QuickPermission, have your `Activity` (or `Fragment`) override the `onRequestPermissionsResult` method and write the code below as below:

```java
public class MainActivity extends AppCompatActivity {

    private static final String[] ALL_PERMISSIONS = {
            Manifest.permission.READ_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    QuickPermission permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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
                
        if(!permission.hasPermission(ALL_PERMISSIONS)) {
            permission.request(ALL_PERMISSIONS);
        } else {
            Toast.makeText(MainActivity.this, "all permissions already granted", Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.permission.onRequestPermissionsResult(permissions, grantResults);
    }
}
```

# Happy Coding :)
