package com.test.ashish.sendsms.di.component;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.test.ashish.sendsms.di.module.MessageFragmentContextModule;
import com.test.ashish.sendsms.di.scope.MessageFragmentScope;
import com.test.ashish.sendsms.di.qualifier.MessageFragmentContext;
import com.test.ashish.sendsms.ui.MessageFragment;

import dagger.Component;

@MessageFragmentScope
@Component(modules = MessageFragmentContextModule.class, dependencies = MainActivityComponent.class)
public interface MessageFragmentComponent {

    @MessageFragmentContext
    public Context getContext();


    public void injectMessageFragment(MessageFragment fragment);
}

