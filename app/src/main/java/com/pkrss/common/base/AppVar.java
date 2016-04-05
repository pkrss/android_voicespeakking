package com.pkrss.common.base;

import android.content.Context;
import android.os.Handler;


/**
 * Created by liand on 2016/4/4.
 */
public final class AppVar {
    private static Handler handler;

    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    public static void setAppContext(Context appContext) {
        handler = new Handler();
        AppVar.appContext = appContext;
    }

    public static Handler getHandler() {
        return handler;
    }
}
