package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.pkrss.voicespeakking.BR;
import com.pkrss.voicespeakking.data.SpData;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSTabPkrssModel extends BaseObservable {

    private String ttsId = SpData.getTTSPkrssId();

    public String getTtsId() {
        return ttsId;
    }

    public void setTtsId(String ttsId) {
        if(ttsId.equals(this.ttsId))
            return;

        this.ttsId = ttsId;
        SpData.setTTSPkrssId(ttsId);
    }
}
