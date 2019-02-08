package com.test.ashish.sendsms.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.test.ashish.sendsms.di.qualifier.MainActivityContext;
import com.test.ashish.sendsms.di.scope.MainActivityScope;
import com.test.ashish.sendsms.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {
    public MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @MainActivityScope
    @MainActivityContext
    public Context provideContext() {
        return context;
    }

}