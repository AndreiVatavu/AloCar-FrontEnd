package com.alocar.frontend.listeners;


import com.alocar.frontend.models.ErrorObject;
import com.alocar.frontend.recycleview.Contact;
import com.alocar.frontend.retrofit.response.GenericResponse;

import java.util.List;

public interface RetrofitListener {
    void onResponseSuccess(GenericResponse responseBody, int apiFlag);

    void onResponseSuccess(List<Contact> responseBody, int apiFlag);

    void onResponseError(ErrorObject errorObject, Throwable throwable, int apiFlag);

}
