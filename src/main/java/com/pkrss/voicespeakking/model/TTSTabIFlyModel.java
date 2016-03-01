package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;

import com.pkrss.voicespeakking.data.SpData;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSTabIFlyModel extends BaseObservable {

    // true:在线合成 false:离线合成
    private boolean online = SpData.getTTSIFlyOnline();

    // 默认发音人
    private String voicer = SpData.getTTSIFlyVoicer();

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        if(this.online == online)
            return;
        this.online = online;
        SpData.setTTSIFlyOnline(online);
    }

    public String getVoicer() {
        return voicer;
    }

    public void setVoicer(String voicer) {
        if(this.voicer.equals(voicer))
            return;

        this.voicer = voicer;
        SpData.setTTSIFlyVoicer(voicer);
    }
}
