package com.alocar.frontend.retrofit;

public enum MessengerFlags {
    LOGOUT(1),
    SEARCH(2),
    SAVE_FAVORITE(3),
    GET_FAVORITE(4);

    int flag;

    MessengerFlags(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }
}
