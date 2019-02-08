package com.test.ashish.sendsms.di.component;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.test.ashish.sendsms.di.module.MainActivityContextModule;
import com.test.ashish.sendsms.di.module.RetrofitModule;
import com.test.ashish.sendsms.di.module.SendMessageActivityContextModule;
import com.test.ashish.sendsms.di.qualifier.MainActivityContext;
import com.test.ashish.sendsms.di.qualifier.SendMessageActivityContext;
import com.test.ashish.sendsms.di.scope.MainActivityScope;
import com.test.ashish.sendsms.di.scope.SendMessageActivityScope;
import com.test.ashish.sendsms.retrofit.APIInterface;
import com.test.ashish.sendsms.ui.SendMessageActivity;

import dagger.Component;


@SendMessageActivityScope
@Component(modules = {SendMessageActivityContextModule.class, RetrofitModule.class}, dependencies = MyApplicationComponent.class)
public interface SendMessageActivityComponent {

    public APIInterface getApiInterface();

    @SendMessageActivityContext
    public Context getContext();


    public void injectSendMessageActivity(SendMessageActivity activity);

}
