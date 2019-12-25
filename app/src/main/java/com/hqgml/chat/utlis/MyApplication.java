package com.hqgml.chat.utlis;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.xuexiang.xui.XUI;

public class MyApplication extends Application {
  
    private static MyApplication myApplication;  
  
    @Override  
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
        myApplication = this;  
    }  
  
    public static MyApplication getInstance(){  
        return myApplication;  
    }  
}  