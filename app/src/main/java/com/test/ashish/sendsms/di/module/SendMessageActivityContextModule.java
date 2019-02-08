package com.test.ashish.sendsms.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.test.ashish.sendsms.di.qualifier.MainActivityContext;
import com.test.ashish.sendsms.di.qualifier.SendMessageActivityContext;
import com.test.ashish.sendsms.di.scope.MainActivityScope;
import com.test.ashish.sendsms.di.scope.SendMessageActivityScope;
import com.test.ashish.sendsms.ui.SendMessageActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class SendMessageActivityContextModule {

    public SendMessageActivity sendMessageActivity;

    public Context context;

    public SendMessageActivityContextModule(SendMessageActivity sendMessageActivity) {
        this.sendMessageActivity = sendMessageActivity;
        context = sendMessageActivity;
    }

    @Provides
    @SendMessageActivityScope
    public SendMessageActivity providesSendMessageActivity() {
        return sendMessageActivity;
    }

    @Provides
    @SendMessageActivityScope
    @SendMessageActivityContext
    public Context provideContext() {
        return context;
    }

}