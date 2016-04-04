package com.pkrss.common.base;

import android.content.Context;

/**
 * Created by liand on 2016/4/4.
 */
public final class AppVar {
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    public static void setAppContext(Context appContext) {
        AppVar.appContext = appContext;
    }
}
