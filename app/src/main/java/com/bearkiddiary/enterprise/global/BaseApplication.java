package com.bearkiddiary.enterprise.global;

import android.app.Application;
import android.util.Log;


import com.bearkiddiary.enterprise.ui.activity.BaseActivity;

import java.util.Stack;


/**
 * Created by admin on 2016/7/4.
 */
public class BaseApplication extends Application {

    Stack<BaseActivity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        activityStack = new Stack<>();
    }

    /**
     * 退出整个程序
     * 在这里写释放SDK资源等代码
     */
    public void exit() {
        //TODO: 释放SDK的资源
        while (activityStack.size() != 0) {
            activityStack.peek().finish();
        }
        System.exit(0);
    }

    public void pushActivity(BaseActivity activity) {
        activityStack.push(activity);
        Log.i("zy", "Activity Stack push " + activityStack.size());
    }

    public void popActivity() {
        activityStack.pop();
        Log.i("zy", "Activity Stack pop " + activityStack.size());
    }
}
