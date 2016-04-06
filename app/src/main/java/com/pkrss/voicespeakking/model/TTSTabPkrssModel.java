package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.SpinnerAdapter;

import com.pkrss.voicespeakking.BR;
import com.pkrss.voicespeakking.data.SpData;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSTabPkrssModel extends BaseObservable {

    private String ttsId = SpData.getTTSPkrssId();

    /**
     * 区域下拉选择
     */
    private SpinnerAdapter spinnerLocalAdapter;

    /**
     * 语音引擎下拉选择
     */
    private SpinnerAdapter spinnerEngineAdapter;

    public String getTtsId() {
        return ttsId;
    }

    public void setTtsId(String ttsId) {
        if(ttsId.equals(this.ttsId))
            return;

        this.ttsId = ttsId;
        SpData.setTTSPkrssId(ttsId);
    }


    public SpinnerAdapter getSpinnerLocalAdapter() {
        return spinnerLocalAdapter;
    }

    public void setSpinnerLocalAdapter(SpinnerAdapter spinnerLocalAdapter) {
        this.spinnerLocalAdapter = spinnerLocalAdapter;
    }


    public SpinnerAdapter getSpinnerEngineAdapter() {
        return spinnerEngineAdapter;
    }

    public void setSpinnerEngineAdapter(SpinnerAdapter spinnerEngineAdapter) {
        this.spinnerEngineAdapter = spinnerEngineAdapter;
    }
}
