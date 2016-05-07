package com.pkrss.voicespeakking.databinding;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by liand on 2016/4/24.
 */
public final class ListViewAdapter {

    @BindingAdapter("android:listViewAdapter")
    public static void setAdapter(ListView listView, ListAdapter oldValue, ListAdapter newValue){
        if (oldValue != newValue) {
            listView.setAdapter(newValue);
        }
    }
}
