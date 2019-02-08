package com.test.ashish.sendsms.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.ashish.sendsms.MyApplication;
import com.test.ashish.sendsms.R;
import com.test.ashish.sendsms.di.component.DaggerMainActivityComponent;
import com.test.ashish.sendsms.di.component.DaggerSendMessageActivityComponent;
import com.test.ashish.sendsms.di.component.MyApplicationComponent;
import com.test.ashish.sendsms.di.component.SendMessageActivityComponent;
import com.test.ashish.sendsms.di.module.MainActivityContextModule;
import com.test.ashish.sendsms.di.module.SendMessageActivityContextModule;
import com.test.ashish.sendsms.pojo.Contacts;
import com.test.ashish.sendsms.pojo.UpdateResponse;
import com.test.ashish.sendsms.retrofit.APIInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessageActivity extends Activity {

    SendMessageActivityComponent  activityComponent;

    @Inject
    APIInterface apiInterface;

    String userName = "", userMobile = "", otp = "", message = "";

    EditText messageEditText;
    Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        messageEditText = findViewById(R.id.message);
        sendMessageButton = findViewById(R.id.sendButton);

        if(getIntent() != null){
            userName = getIntent().getStringExtra("user_name");
            userMobile = getIntent().getStringExtra("user_mobile");
        }

        Log.d("userMobile", userMobile);

        Random rand = new Random();

        otp = String.valueOf(rand.nextInt(10000));
        message = "Hi your otp is : "+otp;
        messageEditText.setText(message);

        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                message = editable.toString();
            }

        });

        MyApplicationComponent myApplicationComponent = MyApplication.get(this).getMyApplicationComponent();

        activityComponent = DaggerSendMessageActivityComponent.builder()
                .sendMessageActivityContextModule(new SendMessageActivityContextModule(this))
                .myApplicationComponent(myApplicationComponent)
                .build();

        activityComponent.injectSendMessageActivity(this);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

    }
    public void  sendMessage(){

        apiInterface.updateMessage(userName,userMobile,message).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                Toast.makeText(SendMessageActivity.this,response.body().data,Toast.LENGTH_LONG).show();
                if(Integer.parseInt(response.body().status)  == 1) {
                    startActivity(new Intent(SendMessageActivity.this, MainActivity.class));
                }
            }
            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {

            }
        });

    }

}
