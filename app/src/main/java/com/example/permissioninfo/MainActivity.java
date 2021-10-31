package com.example.permissioninfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //this method gets invoked when "GET PERMISSIONS" button is pressed
    public void onButtonClick(View view){
        String permission_names=" ";
        int found=0;
        EditText app_names_view=(EditText) findViewById(R.id.appNames);
        String app_names= app_names_view.getText().toString();

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {

            if(app_names.equals(pm.getApplicationLabel(applicationInfo))) {
                found=1;
                permission_names=permission_names.concat("PERMISSIONS for ");
                permission_names = permission_names.concat((String) pm.getApplicationLabel(applicationInfo));
                permission_names = permission_names.concat(" APP\n\n");
            }

            try {
                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);

                //Get Permissions
                String[] requestedPermissions = packageInfo.requestedPermissions;

                if(requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {

                        if(app_names.equals(pm.getApplicationLabel(applicationInfo))) {
                            permission_names = permission_names.concat(requestedPermissions[i]);
                            permission_names = permission_names.concat("\n");
                        }

                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

       Intent intent=new Intent(this,appInfo2.class);

        intent.putExtra("permission_names",permission_names);
        startActivity(intent);
    }
}