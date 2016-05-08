package com.pkrss.voicespeakking.model;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.pkrss.voicespeakking.db.model.SpeakItem;

public final class TTSHistoryItemModel extends BaseObservable {
    private SpeakItem speakItem;
    private Activity activity;

    public TTSHistoryItemModel(Activity activity, SpeakItem speakItem){
        this.activity = activity;
        this.speakItem = speakItem;
    }

    public SpeakItem getSpeakItem() {
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
