package com.test.ashish.sendsms.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.ashish.sendsms.R;
import com.test.ashish.sendsms.adapter.ContactsRecyclerViewAdapter;
import com.test.ashish.sendsms.di.component.ContactFragmentComponent;
import com.test.ashish.sendsms.di.component.DaggerContactFragmentComponent;
import com.test.ashish.sendsms.di.component.MainActivityComponent;
import com.test.ashish.sendsms.di.component.MessageFragmentComponent;

import com.test.ashish.sendsms.di.module.ContactFragmentContextModule;
import com.test.ashish.sendsms.di.module.MessageFragmentContextModule;
import com.test.ashish.sendsms.pojo.Contacts;
import com.test.ashish.sendsms.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements ContactsRecyclerViewAdapter.ClickListener {


    ContactFragmentComponent contactFragmentComponent;

    @Inject
    ContactsRecyclerViewAdapter recyclerViewAdapter;

    @Inject
    APIInterface apiInterface;

    RecyclerView recyclerView;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }


//    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivityComponent activityComponent = MainActivity.get(this).getActivityComponent();
        contactFragmentComponent = DaggerContactFragmentComponent.builder()
                .mainActivityComponent(activityComponent)
                .contactFragmentContextModule(new ContactFragmentContextModule(this))
                .build();
        contactFragmentComponent.injectContactFragment(this);
//
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(recyclerViewAdapter);

        apiInterface.getContacts().enqueue(new Callback<List<Contacts>>() {
            @Override
            public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {
                populateRecyclerView(response.body());
            }
            @Override
            public void onFailure(Call<List<Contacts>> call, Throwable t) {

            }
        });
    }
    private void populateRecyclerView(List<Contacts> response) {
        recyclerViewAdapter.setData(response);
    }

    @Override
    public void launchIntent(String name , String mobile) {

        Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
        intent.putExtra("user_name",name);
        intent.putExtra("user_mobile", mobile);
        startActivity(intent);
    }

}
