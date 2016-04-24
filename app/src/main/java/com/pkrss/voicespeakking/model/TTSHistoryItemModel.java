package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.pkrss.voicespeakking.db.model.SpeakItem;

public final class TTSHistoryItemModel extends BaseObservable {
    private SpeakItem speakItem;

    public TTSHistoryItemModel(SpeakItem speakItem){
        this.speakItem = speakItem;
    }

    public String getBrief(){
        if(this.speakItem==null || this.speakItem.getBrief()==null)
            return "--?--";
        return this.speakItem.getBrief();
    }

    public String getLine2(){
        return "--2--";
    }
}
