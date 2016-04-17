package com.pkrss.voicespeakking.model;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pkrss.module.TTSModule;
import com.pkrss.voicespeakking.BR;
import com.pkrss.voicespeakking.activity.TTSTabOptionActivity;
import com.pkrss.voicespeakking.bean.RadioItemBean;
import com.pkrss.voicespeakking.common.ETTSEngineIdenty;
import com.pkrss.voicespeakking.data.SpData;
import com.pkrss.voicespeakking.databinding.RadioGroupAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSTabModel extends BaseObservable {

    /**
     * 页面切换
     */
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private int ettsEngineIdenty = SpData.getTTSEngineIdenty();

    public int getEttsEngineIdenty() {
        return ettsEngineIdenty;
    }

    public void setEttsEngineIdenty(int ettsEngineIdenty) {
        if(this.ettsEngineIdenty == ettsEngineIdenty)
            return;
        this.ettsEngineIdenty = ettsEngineIdenty;
        SpData.setTTSEngineIdenty(ettsEngineIdenty);
    }

    private RadioGroupAdapter.IRadioGroupAdapter ttsRadioGroupAdapter;

    public RadioGroupAdapter.IRadioGroupAdapter getTtsRadioGroupAdapter() {
        if(ttsRadioGroupAdapter == null)
            ttsRadioGroupAdapter = new TTSRadioGroupAdapter(this);
        return ttsRadioGroupAdapter;
    }

    public static final class TTSRadioGroupAdapter implements RadioGroupAdapter.IRadioGroupAdapter{

        private TTSTabModel ttsTabModel;

        public TTSRadioGroupAdapter(TTSTabModel ttsTabModel){
            this.ttsTabModel = ttsTabModel;
        }

        @Override
        public int getValue() {
            return SpData.getTTSEngineIdenty();
        }

        @Override
        public List<RadioItemBean> getItems() {
            List<RadioItemBean> ret = null;
            List<TTSModule.ITtsWorker> ttsWorkers = TTSModule.getInstance().getTtsWorkerList();
            if(ttsWorkers!=null){
                ret = new ArrayList<RadioItemBean>();
                for(TTSModule.ITtsWorker ttsWorker : ttsWorkers){
                    RadioItemBean bean = new RadioItemBean();
                    bean.setId(ttsWorker.getId());
                    bean.setTitle(ttsWorker.getShowName());
                    bean.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            ttsTabModel.setEttsEngineIdenty(v.getId());

                            TTSModule.getInstance().recreateTTSWorker();

                            ttsTabModel.getActivity().startActivity(new Intent(ttsTabModel.getActivity(), TTSTabOptionActivity.class));;
                        }
                    });
                    ret.add(bean);
                }
            }
            return ret;
        }
    }
}
