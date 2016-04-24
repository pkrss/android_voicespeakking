package com.pkrss.voicespeakking.databinding;

import android.databinding.BindingAdapter;
import android.widget.SeekBar;

/**
 * Created by liand on 2016/4/21.
 */
public final class SeekBarAdapter {
    @BindingAdapter("android:setOnSeekBarChangeListener")
    public static void setOnSeekBarChangeListener(SeekBar seekBar, SeekBar.OnSeekBarChangeListener oldValue, SeekBar.OnSeekBarChangeListener newValue){
        if (oldValue != newValue) {
            seekBar.setOnSeekBarChangeListener(newValue);
        }
    }
}
