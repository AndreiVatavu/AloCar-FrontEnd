package com.alocar.frontend.models;

public class UserCredentials {
    private String email;

    private String password;

    public UserCredentials() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean valid() {
        return email.length() > 0 && password.length() > 0;
    }
}