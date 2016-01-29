package com.basti.gankmvp.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by SHIBW-PC on 2016/1/29.
 */
public class BastiGankClient {

    public static final String HOST = "http://gank.avosapps.com/api/";
    private static BastiGankRetrofit bastiGankRetrofit;
    private static Retrofit retrofit;

    static {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public static BastiGankRetrofit getBastiGankRetrofit(){

        if (bastiGankRetrofit == null){
            synchronized (BastiGankRetrofit.class){
                if (bastiGankRetrofit == null){
                    bastiGankRetrofit = retrofit.create(BastiGankRetrofit.class);
                }
            }
        }

        return bastiGankRetrofit;
    }

}
