package com.example.atmf.daggerdemo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
* @author edz
*
* @temi 2018/12/11  10:52
*
* @describe:
*/
public class TestsService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("tag", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("tag", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("tag", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag", "onDestroy");
    }
}
