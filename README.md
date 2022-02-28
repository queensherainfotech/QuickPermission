# QuickPermission
Enable permissions runtime while inside app  using QuickPermission library.

<a href="#"><img alt="Android OS" src="https://img.shields.io/badge/OS-Android-3DDC84?style=flat-square&logo=android"></a>
<a href="#"><img alt="Languages-Java" src="https://img.shields.io/badge/Language-Java-1DA1F2?style=flat-square&logo=java"></a>
<a href="#"><img alt="API 21+" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"></a>
<a href="https://jitpack.io/#com.queensherainfotech/QuickPermission"><img alt="jitpack" src="https://jitpack.io/v/com.queensherainfotech/QuickPermission.svg"></a>
<a href="https://github.com/queensherainfotech/QuickPermission/blob/master/LICENSE"><img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-success.svg" target="_blank" /></a>

settings.gradle (Project Settings)
------
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven { url 'https://jitpack.io' }
    }
}
```


build.gradle (app module)
------
```
dependencies {
    ...
    implementation 'com.queensherainfotech:QuickPermission:1.0.1'
}
```

Usage
------

To begin using QuickPermission, have your `Activity` (or `Fragment`) override the `onRequestPermissionsResult` method and write the code below as below:

```java
public class MainActivity extends AppCompatActivity {

    private static final String[] ALL_PERMISSIONS = {
            Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE,
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
//                        Log.e("response","all granted permissions: "+String.valueOf(permissions));
                    }

                    @Override public void onPermissionsGranted(@NonNull List<String> permissions) {
//                        Log.e("response","granted permissions: "+String.valueOf(permissions));
                    }

                    @Override public void onPermissionsDenied(@NonNull List<String> permissions) {
//                        Log.e("response","denied permissions: "+String.valueOf(permissions));
                    }

                    @Override
                    public void onPermissionsPermanentDenied(@NonNull List<String> permissions) {
//                        Log.e("response","permanent denied permissions: "+String.valueOf(permissions));
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
