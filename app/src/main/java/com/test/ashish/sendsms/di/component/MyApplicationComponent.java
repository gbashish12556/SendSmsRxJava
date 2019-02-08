package com.test.ashish.sendsms.di.component;

import android.content.Context;

import com.test.ashish.sendsms.MyApplication;
import com.test.ashish.sendsms.di.module.MyApplicationContextModule;
import com.test.ashish.sendsms.di.qualifier.ApplicationContext;
import com.test.ashish.sendsms.di.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {MyApplicationContextModule.class})
public interface MyApplicationComponent {

    @ApplicationContext
    public Context getContext();

    public void injectApplication(MyApplication myApplication);
}
