package com.pkrss.voicespeakking.databinding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pkrss.voicespeakking.bean.RadioItemBean;

import java.util.List;

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

    public interface IRadioGroupAdapter{
        int getValue();
        List<RadioItemBean> getItems();
    }
    @BindingAdapter("android:radioGroupAdapter")
    public static void setRadioGroupAdapter(RadioGroup radioGroup, IRadioGroupAdapter oldValue, IRadioGroupAdapter newValue){
        if (oldValue != newValue) {
            radioGroup.removeAllViews();

            if(newValue!=null && newValue.getItems()!=null){

                List<RadioItemBean> items = newValue.getItems();
                for(int i=0,c=items.size();i<c;++i){
                    RadioItemBean bean = items.get(i);

                    RadioButton radioButton = new RadioButton(radioGroup.getContext());
                    RadioGroup.LayoutParams lparam = new RadioGroup.LayoutParams ( RadioGroup.LayoutParams.MATCH_PARENT , RadioGroup.LayoutParams.WRAP_CONTENT );

                    if(newValue.getValue() == bean.getId())
                        radioButton.setSelected(true);
                    radioButton.setId(bean.getId());
                    radioButton.setText(bean.getTitle());
                    radioGroup.addView ( radioButton,  lparam);
                }
            }
        }
    }
}
