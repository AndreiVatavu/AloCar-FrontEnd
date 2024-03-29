package com.alocar.frontend.retrofit.response;

/**
 * Created by Andrei Vatavu on 5/21/2019
 */
public class LoginResponse extends GenericResponse{
    private String authToken;
    private int uid;

    public LoginResponse() {

    }

    public LoginResponse(int code, String message) {
        super(code, message);
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
