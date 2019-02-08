package com.test.ashish.sendsms.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.ashish.sendsms.R;
import com.test.ashish.sendsms.adapter.MessageRecyclerViewAdapter;

import com.test.ashish.sendsms.di.component.DaggerMessageFragmentComponent;
import com.test.ashish.sendsms.di.component.MainActivityComponent;
import com.test.ashish.sendsms.di.component.MessageFragmentComponent;
import com.test.ashish.sendsms.di.module.MessageFragmentContextModule;
import com.test.ashish.sendsms.pojo.Message;
import com.test.ashish.sendsms.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    MessageFragmentComponent messageFragmentComponent;
    @Inject
    MessageRecyclerViewAdapter recyclerViewAdapter;
//
    @Inject
    APIInterface apiInterface;
//
    RecyclerView recyclerView;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

//    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        MainActivityComponent activityComponent = MainActivity.get(this).getActivityComponent();
        messageFragmentComponent = DaggerMessageFragmentComponent.builder()
                .mainActivityComponent(activityComponent)
                .messageFragmentContextModule(new MessageFragmentContextModule(this))
                .build();
        messageFragmentComponent.injectMessageFragment(this);
//
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(recyclerViewAdapter);

        apiInterface.getMessages().enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                populateRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });

    }
//
    private void populateRecyclerView(List<Message> response) {
        recyclerViewAdapter.setData(response);
    }

}
