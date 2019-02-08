package com.test.ashish.sendsms.di.module;

import android.support.v4.app.Fragment;

import com.test.ashish.sendsms.adapter.ContactsRecyclerViewAdapter;
import com.test.ashish.sendsms.di.scope.MainActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class, MessageFragmentContextModule.class})
public class ContactsRecyclerViewAdapterModule {

    @Provides
    @MainActivityScope
    public ContactsRecyclerViewAdapter getContactList(ContactsRecyclerViewAdapter.ClickListener clickListener) {
        return new ContactsRecyclerViewAdapter(clickListener);
    }


    @Provides
    @MainActivityScope
    public ContactsRecyclerViewAdapter.ClickListener getClickListener(Fragment fragment) {
        return (ContactsRecyclerViewAdapter.ClickListener) fragment;
    }

}