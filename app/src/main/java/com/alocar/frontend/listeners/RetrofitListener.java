package com.alocar.frontend.listeners;


import com.alocar.frontend.models.ErrorObject;

public interface RetrofitListener {
    void onResponseSuccess(String responseBodyString, int apiFlag);

    void onResponseError(ErrorObject errorObject, Throwable throwable, int apiFlag);

}
