package com.pkrss.voicespeakking.databinding;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.RadioGroup;

/**
 * Created by liand on 2016/4/24.
 */
public final class SwipeRefreshLayoutAdapter {

    @BindingAdapter("android:setOnRefreshListener")
    public static void setOnRefreshListener(SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener oldValue, SwipeRefreshLayout.OnRefreshListener newValue){
        if (oldValue != newValue) {
            swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
            swipeRefreshLayout.setOnRefreshListener(newValue);
        }
    }
}
