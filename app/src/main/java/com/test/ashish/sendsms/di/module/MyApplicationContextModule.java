package com.test.ashish.sendsms.di.module;


import android.content.Context;

import com.test.ashish.sendsms.di.qualifier.ApplicationContext;
import com.test.ashish.sendsms.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MyApplicationContextModule {

    public Context context;

    public MyApplicationContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}