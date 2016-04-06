package com.pkrss.voicespeakking.databinding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

/**
 * Created by liand on 2016/2/12.
 */
public final class AdapterViewAdapter {
    @BindingAdapter("android:setOnItemSelectedListener")
    public static <T extends Adapter> void setOnItemSelectedListener(AdapterView<T> view, AdapterView.OnItemSelectedListener oldValue, AdapterView.OnItemSelectedListener newValue){
        if (oldValue != newValue) {
            view.setOnItemSelectedListener(newValue);
        }
    }
}
