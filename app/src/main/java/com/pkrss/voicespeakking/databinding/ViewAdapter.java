package com.pkrss.voicespeakking.databinding;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by liand on 2016/2/12.
 */
public final class ViewAdapter {
    @BindingAdapter("android:onLongClick")
    public static void setOnLongClickListener(View view, View.OnLongClickListener oldValue, View.OnLongClickListener newValue){
        if (oldValue != newValue) {
            view.setOnLongClickListener(newValue);
        }
    }


    public interface ISetHosterListener{
        void setHoster(View view);
    }

    @BindingAdapter("android:setHosterListener")
    public static void setHosterListener(View view, ISetHosterListener oldValue, ISetHosterListener newValue){
        if(newValue!=null)
            newValue.setHoster(view);
    }
}
