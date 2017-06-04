package com.example.greendaotest;

import android.app.Application;
import android.content.Context;

/**
 * Created by rhm on 2017/6/4.
 */


//获取全局的Context
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return  context;
    }
}
