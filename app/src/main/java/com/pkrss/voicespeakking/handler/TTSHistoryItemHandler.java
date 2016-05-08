package com.pkrss.voicespeakking.handler;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.pkrss.voicespeakking.db.dao.SpeakItemContentDao;
import com.pkrss.voicespeakking.db.dao.SpeakItemDao;
import com.pkrss.voicespeakking.db.model.SpeakItem;
import com.pkrss.voicespeakking.db.model.SpeakItemContent;
import com.pkrss.voicespeakking.db.util.DbCore;
import com.pkrss.voicespeakking.model.ContentModel;
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
        Long id = ttsHistoryItemModel.getSpeakItem().getId();
        DbCore.getDaoSession().getSpeakItemContentDao().deleteByKey(id);
        DbCore.getDaoSession().getSpeakItemDao().deleteByKey(id);
    }

    public void clickItem(View view){
        SpeakItem speakItem = ttsHistoryItemModel.getSpeakItem();
        if(speakItem != null) {
            SpeakItemContent speakItemContent = DbCore.getDaoSession().getSpeakItemContentDao().load(speakItem.getId());

            if (speakItemContent != null && speakItemContent.getContent() != null)
                ContentModel.getInstance().setContent(speakItemContent.getContent());
        }
        ttsHistoryItemModel.getActivity().finish();
    }

}
