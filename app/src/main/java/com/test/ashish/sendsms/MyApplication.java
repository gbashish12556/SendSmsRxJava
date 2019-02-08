package com.test.ashish.sendsms;

import android.app.Activity;
import android.app.Application;

import com.test.ashish.sendsms.di.component.DaggerMyApplicationComponent;
import com.test.ashish.sendsms.di.component.MyApplicationComponent;
import com.test.ashish.sendsms.di.module.MyApplicationContextModule;

public class MyApplication extends Application {

    MyApplicationComponent myApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        myApplicationComponent = DaggerMyApplicationComponent.builder().myApplicationContextModule(new MyApplicationContextModule(this)).build();
        myApplicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public MyApplicationComponent getMyApplicationComponent() {
        return myApplicationComponent;
    }
}