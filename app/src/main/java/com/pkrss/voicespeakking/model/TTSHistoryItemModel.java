package com.pkrss.voicespeakking.model;

import android.app.Activity;
import android.databinding.BaseObservable;

import com.pkrss.voicespeakking.db.entity.SpeakItemEntity;

public final class TTSHistoryItemModel extends BaseObservable {
    private SpeakItemEntity speakItem;
    private Activity activity;

    public TTSHistoryItemModel(Activity activity, SpeakItemEntity speakItem){
        this.activity = activity;
        this.speakItem = speakItem;
    }

    public SpeakItemEntity getSpeakItem() {
        return speakItem;
    }

    public String getBrief(){
        if(this.speakItem==null || this.speakItem.getBrief()==null)
            return "--?--";
        return this.speakItem.getBrief();
    }

    public String getLine2(){
        return "--2--";
    }

    public Activity getActivity() {
        return activity;
    }
}
