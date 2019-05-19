package com.alocar.frontend.retrofit;

import android.content.Context;

import com.alocar.frontend.listeners.RetrofitListener;
import com.alocar.frontend.models.SignUpRequest;
import com.alocar.frontend.util.HttpUtil;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiServiceProvider extends RetrofitBase {
    private static ApiServiceProvider apiServiceProvider = null;
    private ApiServices apiServices;

    private ApiServiceProvider(Context context) {
        super(context);
        apiServices = retrofit.create(ApiServices.class);
    }

    public static ApiServiceProvider getInstance(Context context) {
        if (apiServiceProvider == null) {
            apiServiceProvider = new ApiServiceProvider(context);
        }
        return apiServiceProvider;
    }

    public void signUp(SignUpRequest signUpRequest, final RetrofitListener retrofitListener) {
        Call<String> call = apiServices.signUp(signUpRequest);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                retrofitListener.onResponseSuccess(response.body().toString(), 0);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), t, 0);
            }
        });
    }
}
