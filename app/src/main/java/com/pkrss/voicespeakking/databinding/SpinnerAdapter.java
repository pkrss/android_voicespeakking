package com.pkrss.voicespeakking.databinding;

import android.databinding.BindingAdapter;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.pkrss.voicespeakking.bean.RadioItemBean;

import java.util.List;

/**
 * Created by liand on 2016/4/5.
 */
public final class SpinnerAdapter {

//    @BindingAdapter("android:spinnerAdapter")
//    public static void setSpinnerSelection(Spinner spinner, int nSel){
//        if(spinner == null || nSel<0 || spinner.getAdapter()==null || spinner.getAdapter().getCount()<=nSel)
//            return;
//        spinner.setSelection(nSel,true);
//        AdapterView.OnItemSelectedListener listener = spinner.getOnItemSelectedListener();
//        if(listener!=null)
//            listener.onItemSelected(spinner,null, nSel, 0);
//    }

    @BindingAdapter("android:spinnerAdapter")
    public static void setSpinnerAdapter(Spinner spinner, android.widget.SpinnerAdapter oldValue, android.widget.SpinnerAdapter newValue){
        if (oldValue != newValue) {
            spinner.setAdapter(newValue);
        }
    }
}
