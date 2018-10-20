package com.example.user.hidenapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText = (EditText)findViewById(R.id.editText);
    Button button = (Button)findViewById(R.id.button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /** 앱 설치여부 체크 및 실행 **/
    // Region Start
    /** 특정 패키지명의 앱 설치 여부 체크업 **/
    public static boolean searchAppPackage(Context context, String packageName){
        boolean bExist = false;

        /** 패키지 정보 리스트 추출 **/
        PackageManager pkgMgr = context.getPackageManager();
        List<ResolveInfo> mAppList;
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mAppList = pkgMgr.queryIntentActivities(mainIntent, 0);

        /** 패키지 리스트 순회하면서 특정 패키지명 검색 **/
        try{
            for(int i=0;i<mAppList.size();i++){
                if(mAppList.get(i).activityInfo.packageName.startsWith(packageName)){
                    bExist = true;
                    break;
                }
            }
        }catch(Exception e){
            bExist = false;
        }
        return bExist;
    }

    /** 특정 패키지명의 앱 실행(설치여부 확인후 실행필요) **/
    public static void executeLocalAppPackage(Context context, String packageName){
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /** 특정 패키지명의 앱의 설치경로(PlayStore) 이동처리 **/
    public static void executeStoreAppPackage(Context context, String packageName){
        String url = "market://details?id=" + packageName;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(i);
    }
    // Region End
}
