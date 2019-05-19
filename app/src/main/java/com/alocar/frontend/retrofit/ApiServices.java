package com.alocar.frontend.retrofit;

import com.alocar.frontend.models.SignUpRequest;

import org.springframework.http.ResponseEntity;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {
    @POST("signup")
    Call<String> signUp(@Body SignUpRequest signUpRequest);
}
