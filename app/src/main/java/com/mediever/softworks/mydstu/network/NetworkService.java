package com.mediever.softworks.mydstu.network;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    public static final String BASE_URL = "http://46.229.215.224:9010/api/";
    private static Retrofit mRetrofit;

    private NetworkService() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public static  NetworkService getInstance() {
        if(mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public ServerAPI getServerApi() {
        return mRetrofit.create(ServerAPI.class);
    }
}
