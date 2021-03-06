package com.example.user.hidenapp;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Comparator;

public class packageinfo {
    public static interface AppFilter {
        public void init();
        public boolean filterApp(ApplicationInfo info);
    }

    // 아이콘
    public Drawable mIcon = null;
    // 어플리케이션 이름
    public String mAppNaem = null;
    // 패키지 명
    public String mAppPackge = null;

    /**
     * 서드파티 필터
     */
    public static final AppFilter THIRD_PARTY_FILTER = new AppFilter() {
        public void init() {
        }
        @Override
        public boolean filterApp(ApplicationInfo info) {
            if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                return true;
            } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                return true;
            }
            return false;
        }
    };

    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<packageinfo> ALPHA_COMPARATOR = new Comparator<packageinfo>() {
        private final Collator sCollator = Collator.getInstance();
        @Override
        public int compare(packageinfo object1, packageinfo object2) {
            return sCollator.compare(object1.mAppNaem, object2.mAppNaem);
        }
    };
}