package com.pkrss.common.helper;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by liand on 2016/4/9.
 */
public final class ManifestHelper {
    public static String getApplicationMeta(Context context, String key) {
        try {

            do {
                PackageManager pm = context.getPackageManager();

                ApplicationInfo applicationInfo = pm
                        .getApplicationInfo(context.getPackageName(),
                                PackageManager.GET_META_DATA);

                if (applicationInfo == null || applicationInfo.metaData == null)
                    break;

                Bundle metaData = applicationInfo.metaData;

                Object o = metaData.get(key);
                if (o != null)
                    return o.toString();
            } while (false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
