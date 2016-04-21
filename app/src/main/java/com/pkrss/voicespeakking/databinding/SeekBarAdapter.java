package com.pkrss.voicespeakking.databinding;

import android.databinding.BindingAdapter;
import android.widget.SeekBar;

import com.pkrss.voicespeakking.handler.PlayerBarHandler;

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

    public interface ISetHosterListener{
        void setHoster(SeekBar seekBar);
    }

    @BindingAdapter("android:setHosterListener")
    public static void setHosterListener(SeekBar seekBar, ISetHosterListener oldValue, ISetHosterListener newValue){
        if(newValue!=null)
            newValue.setHoster(seekBar);
    }
}
