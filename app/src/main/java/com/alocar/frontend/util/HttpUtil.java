package com.alocar.frontend.util;

import android.content.Context;

import com.alocar.frontend.R;
import com.alocar.frontend.models.ErrorObject;
import com.google.gson.Gson;

import org.json.JSONObject;

public class HttpUtil {

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static JSONObject getServerErrorJsonObject(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.ErrorClass.STATUS, 505);
            jsonObject.put(Constants.ErrorClass.CODE, 3000);
            jsonObject.put(Constants.ErrorClass.MESSAGE, context.getString(R.string.not_available));
            jsonObject.put(Constants.ErrorClass.DEVELOPER_MESSAGE, context.getString(R.string.server_error));
        } catch (Exception e) {

        }
        return jsonObject;
    }

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static ErrorObject getServerErrorPojo(Context context) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(getServerErrorJsonObject(context).toString(), ErrorObject.class);
        } catch (Exception e) {

        }
        return null;
    }
}
