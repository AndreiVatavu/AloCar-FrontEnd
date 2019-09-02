package com.alocar.frontend.util;

public class SessionUtil {
    private static String authToken;
    private static String uid;

    public static void setAuthToken(String authToken) {
        SessionUtil.authToken = authToken;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        SessionUtil.uid = uid;
    }
}
