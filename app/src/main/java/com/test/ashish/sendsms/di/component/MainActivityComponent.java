package com.test.ashish.sendsms.di.component;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.test.ashish.sendsms.di.module.MainActivityContextModule;
import com.test.ashish.sendsms.di.module.RetrofitModule;
import com.test.ashish.sendsms.di.qualifier.MainActivityContext;
import com.test.ashish.sendsms.di.scope.MainActivityScope;
import com.test.ashish.sendsms.retrofit.APIInterface;

import dagger.Component;

@MainActivityScope
@Component(modules = {MainActivityContextModule.class, RetrofitModule.class}, dependencies = MyApplicationComponent.class)
public interface MainActivityComponent {

    public APIInterface getApiInterface();

    @MainActivityContext
    public Context getContext();


    public void injectActivity(AppCompatActivity activity);

}
