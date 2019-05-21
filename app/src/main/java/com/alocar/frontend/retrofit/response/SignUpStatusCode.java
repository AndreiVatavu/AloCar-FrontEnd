package com.alocar.frontend.retrofit.response;

public enum SignUpStatusCode {
    OK(0),
    FAIL(-1),
    INVALID_FIRST_NAME(1),
    INVALID_LAST_NAME(2),
    INVALID_EMAIL_ADDRESS(3),
    INVALID_PHONE_NUMBER(4),
    INVALID_PASSWORD(5),
    USER_ALREADY_EXIST(6);

    int statusCode;

    SignUpStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    int getStatusCode() {
        return this.statusCode;
    }

    public static SignUpStatusCode getSignUpStatusCode(int statusCode) {
        for (SignUpStatusCode signUpStatusCode : SignUpStatusCode.values()) {
            if (signUpStatusCode.getStatusCode() == statusCode) {
                return signUpStatusCode;
            }
        }
        return null;
    }
}
