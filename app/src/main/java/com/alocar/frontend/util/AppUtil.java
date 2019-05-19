package com.alocar.frontend.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alocar.frontend.R;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppUtil {
    private static Logger logger = Logger.getLogger(AppUtil.class.getSimpleName());
    public static final String APP_VERSION = "v.1.0.0";
    public static final String APP_NAME = "AloCar";
    public static final String NAME = "AloCar";

    public static String getApplicationVersionName(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            logger.info(e.getMessage());
            logger.info(Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            logger.log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        }
        return context.getString(R.string.not_available);
    }

    /**
     * Retrieve Application Version code for sending in the header of api calls
     *
     * @param context
     * @return App Code
     */
    public static int getApplicationVersionCode(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            logger.log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            logger.log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        }
        return -1;
    }
}
