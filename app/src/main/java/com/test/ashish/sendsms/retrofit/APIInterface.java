package com.test.ashish.sendsms.retrofit;

import com.test.ashish.sendsms.pojo.Contacts;
import com.test.ashish.sendsms.pojo.Message;
import com.test.ashish.sendsms.pojo.UpdateResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIInterface {

    @GET("exec?getType=contact")
    Call<List<Contacts>> getContacts();

    @GET("exec?getType=message")
    Call<List<Message>> getMessages();


    @POST("exec")
    @FormUrlEncoded
    Call<UpdateResponse> updateMessage(@Field("user_name") String userName, @Field("user_mobile") String userMobile, @Field("user_message") String userMessage);
}