package com.example.user.hidenapp;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list_item);

        dataSetting();
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getApplicationContext().getPackageManager().queryIntentActivities( mainIntent, 0);
        for(int i = 0; i < pkgAppsList.size(); i++) {
            Log.i("img", "dataSetting: " + pkgAppsList.get(i));
        }

        for (int i = 0; i < pkgAppsList.size(); i++) {
            mMyAdapter.addItem(pkgAppsList.get(i).activityInfo.loadIcon(getPackageManager()), pkgAppsList.get(i).activityInfo.loadLabel(getPackageManager()).toString(), pkgAppsList.get(i).activityInfo.name);
          //  mMyAdapter.addItem(pkgAppsList.get(i).activityInfo.getIconResource(), pkgAppsList.get(i).activityInfo.loadLabel(getPackageManager()).toString(), "순서"+i+1);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);

    }
}