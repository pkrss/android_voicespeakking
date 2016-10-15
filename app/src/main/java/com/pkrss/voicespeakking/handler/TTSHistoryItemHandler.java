package com.pkrss.voicespeakking.handler;

import android.view.View;

import com.pkrss.voicespeakking.db.entity.SpeakItemContentEntity;
import com.pkrss.voicespeakking.db.entity.SpeakItemEntity;
import com.pkrss.voicespeakking.db.util.DbCore;
import com.pkrss.voicespeakking.model.ContentModel;
import com.pkrss.voicespeakking.model.TTSHistoryItemModel;

public final class TTSHistoryItemHandler {

    private TTSHistoryItemModel ttsHistoryItemModel;

    public TTSHistoryItemHandler(TTSHistoryItemModel ttsHistoryItemModel){
        this.ttsHistoryItemModel = ttsHistoryItemModel;
    }

    public void clickDelete(View view){
        Long id = ttsHistoryItemModel.getSpeakItem().getId();
        DbCore.getDaoSession().getSpeakItemContentEntityDao().deleteByKey(id);
        DbCore.getDaoSession().getSpeakItemEntityDao().deleteByKey(id);
    }

    public void clickItem(View view){
        SpeakItemEntity speakItem = ttsHistoryItemModel.getSpeakItem();
        if(speakItem != null) {
            SpeakItemContentEntity speakItemContent = DbCore.getDaoSession().getSpeakItemContentEntityDao().load(speakItem.getId());

            if (speakItemContent != null && speakItemContent.getContent() != null)
                ContentModel.getInstance().setContent(speakItemContent.getContent());
        }
        ttsHistoryItemModel.getActivity().finish();
    }

}
