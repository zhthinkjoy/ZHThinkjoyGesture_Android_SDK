package com.thinkjoy.zhthtinkjoygesturedetect;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by thinkjoy on 17-9-19.
 */

public class StartActivity extends Activity {

    private static String PERMISSIONS_CAMERA = "android.permission.CAMERA";
    private static String[] PERMISSIONS = {
            "android.permission.CAMERA"
    };
    private static int REQUEST_PERSSION = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);


        verifyPermissions(StartActivity.this);

    }

    public  void verifyPermissions(Activity activity) {
        try {
            //检测是否有写的权限
            if (
                    ActivityCompat.checkSelfPermission(activity, PERMISSIONS_CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS,REQUEST_PERSSION);
            } else {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_PERSSION) {
//            if (allRequestsPermitted(grantResults)) {
//                Toast.makeText(this, " Permission Granted!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(StartActivity.this, MainActivity.class));
//            }
//            else {
//                Toast.makeText(this, " Permission Denied!", Toast.LENGTH_SHORT).show();
//            }
//        }
    }
    private boolean allRequestsPermitted(int[] grantResults) {
        for (int result : grantResults)
            if (result == PackageManager.PERMISSION_DENIED)
                return false;
        return true;
    }
}
