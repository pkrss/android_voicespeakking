package com.pkrss.module;

import android.net.Uri;
import android.util.Log;

import com.pkrss.common.events.EventConstants;
import com.pkrss.common.events.EventHelper;

/**
 * Created by liand on 2015/11/7.
 */
public final class StatAnalytics{
    public static void click(String label){
        EventHelper.Instance().notify(EventConstants.StatAnalytics.log_click, label);
    }

    public static void url(String url){
        EventHelper.Instance().notify(EventConstants.StatAnalytics.log_url, Uri.parse(url).getHost());
    }

    public static void exception(String message){
        if(message == null || message.length() == 0)
            return;
        Log.e("exception:", message);
        EventHelper.Instance().notify(EventConstants.StatAnalytics.log_exception, message);
    }

    public static void log(String message){
        Log.d("Log:", message);
        EventHelper.Instance().notify(EventConstants.StatAnalytics.log_log, message);
    }
}
