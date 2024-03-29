package com.alocar.frontend.retrofit;

import android.content.Context;

import com.alocar.frontend.listeners.RetrofitListener;
import com.alocar.frontend.models.SignUpRequest;
import com.alocar.frontend.models.UserCredentials;
import com.alocar.frontend.recycleview.Contact;
import com.alocar.frontend.retrofit.response.GenericResponse;
import com.alocar.frontend.retrofit.response.LoginResponse;
import com.alocar.frontend.util.HttpUtil;
import com.alocar.frontend.util.SessionUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Call<GenericResponse> call = apiServices.signUp(signUpRequest);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                retrofitListener.onResponseSuccess(response.body(), 0);
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), t, 0);
            }
        });
    }

    public void login(UserCredentials userCredentials, final RetrofitListener retrofitListener) {
        Call<LoginResponse> call = apiServices.login(userCredentials);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                retrofitListener.onResponseSuccess(response.body(), 0);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), t, 0);
            }
        });
    }

    public void logout(final RetrofitListener retrofitListener) {
        Call<GenericResponse> call = apiServices.logout(SessionUtil.getAuthToken());
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                retrofitListener.onResponseSuccess(response.body(), MessengerFlags.LOGOUT.getFlag());
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), t, MessengerFlags.LOGOUT.getFlag());
            }
        });
    }

    public void search(final RetrofitListener retrofitListener) {
        Call<List<Contact>> call = apiServices.search();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                retrofitListener.onResponseSuccess(response.body(), MessengerFlags.SEARCH.getFlag());
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), t, MessengerFlags.SEARCH.getFlag());
            }
        });
    }
}
