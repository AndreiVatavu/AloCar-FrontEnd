package com.alocar.frontend.retrofit;

import android.content.Context;

import com.alocar.frontend.BuildConfig;
import com.alocar.frontend.util.AppUtil;
import com.alocar.frontend.util.ConfigUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBase {
    protected Retrofit retrofit;
    protected Context context;


    public RetrofitBase(Context context) {
        this.context = context;


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder().addInterceptor(interceptor);
        addVersioningHeaders(httpClientBuilder, context);
        OkHttpClient httpClient = httpClientBuilder.build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.SERVER_URL + ":"
                        + ConfigUtils.SERVER_PORT + "/"
                        + ConfigUtils.APPLICATION_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    private void addVersioningHeaders(OkHttpClient.Builder builder, Context context) {
        final int version = AppUtil.getApplicationVersionCode(context);
        builder.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader(AppUtil.APP_VERSION, String.valueOf(version))
                        .addHeader(AppUtil.APP_NAME, AppUtil.NAME)
                        .build();
                return chain.proceed(request);
            }
        });
    }
}
