package com.pkrss.voicespeakking.databinding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Created by liand on 2016/2/12.
 */
public final class RadioGroupAdapter {
    @BindingAdapter("android:onCheckedChangeListener")
    public static void setOnCheckedChangeListener(RadioGroup radioGroup, RadioGroup.OnCheckedChangeListener oldValue, RadioGroup.OnCheckedChangeListener newValue){
        if (oldValue != newValue) {
            radioGroup.setOnCheckedChangeListener(newValue);
        }
    }
}
