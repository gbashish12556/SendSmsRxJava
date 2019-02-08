package com.test.ashish.sendsms.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Message {

    @SerializedName("name")
    public String name;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("message")
    public String message;
    @SerializedName("sms_time")
    public String sms_time;

}

