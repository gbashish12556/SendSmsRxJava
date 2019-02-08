package com.test.ashish.sendsms.di.module;

import android.content.Context;

import com.test.ashish.sendsms.adapter.ContactsRecyclerViewAdapter;
import com.test.ashish.sendsms.di.qualifier.ContactFragmentContext;
import com.test.ashish.sendsms.di.qualifier.MessageFragmentContext;
import com.test.ashish.sendsms.di.scope.ContactFragmentScope;
import com.test.ashish.sendsms.di.scope.MessageFragmentScope;
import com.test.ashish.sendsms.ui.ContactFragment;
import com.test.ashish.sendsms.ui.MessageFragment;

import dagger.Module;
import dagger.Provides;



@Module
public class ContactFragmentContextModule {

    public ContactFragment contactFragment;
    public Context context;

    public ContactFragmentContextModule(ContactFragment contactFragment) {
        this.contactFragment = contactFragment;
        this.context = contactFragment.getContext();
    }

    @Provides
    @ContactFragmentScope
    public ContactFragment providesFragmennt() {
        return contactFragment;
    }


    @Provides
    @ContactFragmentScope
    public ContactsRecyclerViewAdapter.ClickListener providesContactListener() {
        return contactFragment;
    }


    @Provides
    @ContactFragmentScope
    @ContactFragmentContext
    public Context provideContext() {
        return context;
    }

}
