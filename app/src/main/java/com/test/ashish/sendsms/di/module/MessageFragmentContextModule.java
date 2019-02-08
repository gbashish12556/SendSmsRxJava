package com.test.ashish.sendsms.di.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.test.ashish.sendsms.adapter.ContactsRecyclerViewAdapter;
import com.test.ashish.sendsms.di.scope.MessageFragmentScope;
import com.test.ashish.sendsms.di.qualifier.MessageFragmentContext;
import com.test.ashish.sendsms.ui.ContactFragment;
import com.test.ashish.sendsms.ui.MessageFragment;

import dagger.Module;
import dagger.Provides;


@Module
public class MessageFragmentContextModule {

    public MessageFragment contactFragment;
    public Context context;

    public MessageFragmentContextModule(MessageFragment contactFragment) {
        this.contactFragment = contactFragment;
        this.context = contactFragment.getContext();
    }

    @Provides
    @MessageFragmentScope
    public MessageFragment providesFragmennt() {
        return contactFragment;
    }


    @Provides
    @MessageFragmentScope
    @MessageFragmentContext
    public Context provideContext() {
        return context;
    }

}