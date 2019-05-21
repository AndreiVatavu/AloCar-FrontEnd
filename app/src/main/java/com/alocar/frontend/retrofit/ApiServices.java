package com.alocar.frontend.retrofit;

import com.alocar.frontend.models.SignUpRequest;
import com.alocar.frontend.models.UserCredentials;
import com.alocar.frontend.retrofit.response.GenericResponse;
import com.alocar.frontend.retrofit.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {
    @POST("/signup/")
    Call<GenericResponse> signUp(@Body SignUpRequest signUpRequest);

    @POST("/login/")
    Call<LoginResponse> login (@Body UserCredentials userCredentials);

    @POST("/logout/")
    Call<GenericResponse> logout(@Body String authToken);
}
