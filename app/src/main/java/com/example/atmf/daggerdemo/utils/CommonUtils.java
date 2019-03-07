package com.example.atmf.daggerdemo.utils;

import android.content.Context;

public class CommonUtils {
    /**
     * get statusbar height
     *
     * @param context
     * @return statusbar height
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}