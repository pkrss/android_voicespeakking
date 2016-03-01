package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.view.ViewPager;

import com.pkrss.voicespeakking.BR;
import com.pkrss.voicespeakking.common.ETTSEngineIdenty;
import com.pkrss.voicespeakking.data.SpData;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSTabModel extends BaseObservable {

    /**
     * 页面切换
     */
    private ViewPager viewPager;

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    private ETTSEngineIdenty ettsEngineIdenty = SpData.getTTSEngineIdenty();

    public ETTSEngineIdenty getEttsEngineIdenty() {
        return ettsEngineIdenty;
    }

    public void setEttsEngineIdenty(ETTSEngineIdenty ettsEngineIdenty) {
        if(this.ettsEngineIdenty == ettsEngineIdenty)
            return;
        this.ettsEngineIdenty = ettsEngineIdenty;
        SpData.setTTSEngineIdenty(ettsEngineIdenty);
    }
}
