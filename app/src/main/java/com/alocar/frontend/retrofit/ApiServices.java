package com.alocar.frontend.retrofit;

import com.alocar.frontend.models.SignUpRequest;
import com.alocar.frontend.models.UserCredentials;
import com.alocar.frontend.recycleview.Contact;
import com.alocar.frontend.retrofit.response.GenericResponse;
import com.alocar.frontend.retrofit.response.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiServices {
    @POST("/signup/")
    Call<GenericResponse> signUp(@Body SignUpRequest signUpRequest);

    @POST("/login/")
    Call<LoginResponse> login (@Body UserCredentials userCredentials);

    @POST("/logout/")
    Call<GenericResponse> logout(@Body String authToken);

    @GET("/searchYouTube/")
    Call<List<Contact>> search(@Header("query") String query);

    @POST("/saveFavorite/")
    Call<GenericResponse> saveToFavorite(@Body Contact contact);

    @GET("/getFavoriteSongs/")
    Call<List<Contact>> getFavoriteSongs(@Header("userId") String query);
}
