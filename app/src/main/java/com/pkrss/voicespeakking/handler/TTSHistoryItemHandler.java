package com.pkrss.voicespeakking.handler;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.pkrss.voicespeakking.db.model.SpeakItem;
import com.pkrss.voicespeakking.db.util.DbCore;
import com.pkrss.voicespeakking.model.TTSHistoryItemModel;
import com.pkrss.voicespeakking.model.TTSHistoryModel;

import java.util.ArrayList;
import java.util.List;

public final class TTSHistoryItemHandler {

    private TTSHistoryItemModel ttsHistoryItemModel;

    public TTSHistoryItemHandler(TTSHistoryItemModel ttsHistoryItemModel){
        this.ttsHistoryItemModel = ttsHistoryItemModel;
    }

    public void clickDelete(View view){

    }

    public void clickItem(View view){

    }

}
