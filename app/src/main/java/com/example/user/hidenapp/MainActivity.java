package com.example.user.hidenapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ApplicationInfo> mPackageApps = new ArrayList<ApplicationInfo>();

    EditText editText = (EditText)findViewById(R.id.editText);
    Button button = (Button)findViewById(R.id.button);
    String  appName;
    Context context;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pkgm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> AppInfos = pkgm.queryIntentActivities(intent, 0);
        for (ResolveInfo info : AppInfos) {
            ActivityInfo ai = info.activityInfo;
            Log.i("GUNMAN: APP TITLE", ai.loadLabel(pkgm).toString());
            Log.i("GUNMAN: APP Package Name", ai.packageName);
            Log.i("GUNMAN: APP Class Name", ai.name);
            int resId = ai.applicationInfo.icon; //App. Icon
        }

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appName = editText.getText().toString();

            }
        });
    }
}


