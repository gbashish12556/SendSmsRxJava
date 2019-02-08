package com.test.ashish.sendsms.di.component;

import android.content.Context;

import com.test.ashish.sendsms.di.module.ContactFragmentContextModule;
import com.test.ashish.sendsms.di.qualifier.ContactFragmentContext;
import com.test.ashish.sendsms.di.qualifier.MessageFragmentContext;
import com.test.ashish.sendsms.di.scope.ContactFragmentScope;
import com.test.ashish.sendsms.ui.ContactFragment;
import com.test.ashish.sendsms.ui.MessageFragment;

import dagger.Component;


@ContactFragmentScope
@Component(modules = ContactFragmentContextModule.class, dependencies = MainActivityComponent.class)
public interface ContactFragmentComponent {

    @ContactFragmentContext
    public Context getContext();


    public void injectContactFragment(ContactFragment fragment);
}
