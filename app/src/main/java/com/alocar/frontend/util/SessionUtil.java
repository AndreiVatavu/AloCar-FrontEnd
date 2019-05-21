package com.alocar.frontend.util;

public class SessionUtil {
    private static String authToken;

    public static void setAuthToken(String authToken) {
        SessionUtil.authToken = authToken;
    }

    public static String getAuthToken() {
        return authToken;
    }
}
