package com.test.ashish.sendsms.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.ashish.sendsms.R;
import com.test.ashish.sendsms.di.qualifier.ApplicationContext;
import com.test.ashish.sendsms.retrofit.APIInterface;

import javax.inject.Inject;

public class ContactDetailActivity extends AppCompatActivity {

    @Inject
    public Context mContext;

    TextView userNameTextView;
    TextView userMobileTextView;
    Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        userNameTextView = findViewById(R.id.userName);
        userMobileTextView = findViewById(R.id.userMobile);
        sendMessageButton = findViewById(R.id.sendButton);

        if(getIntent() != null){
            userNameTextView.setText(getIntent().getStringExtra("user_name"));
            userMobileTextView.setText(getIntent().getStringExtra("user_mobile"));
        }

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactDetailActivity.this, SendMessageActivity.class);
                intent.putExtra("user_name",userNameTextView.getText().toString());
                intent.putExtra("user_mobile", userMobileTextView.getText().toString());
                startActivity(intent);
            }
        });

    }
}
