package com.pkrss.voicespeakking.handler;

import android.content.Intent;
import android.widget.RadioGroup;

import com.pkrss.module.TTSModule;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.activity.TTSTabActivity;
import com.pkrss.voicespeakking.activity.TTSTabOptionActivity;
import com.pkrss.voicespeakking.common.ETTSEngineIdenty;
import com.pkrss.voicespeakking.model.TTSTabModel;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSTabHandler {

    private TTSTabModel ttsTabModel;

    public TTSTabHandler(TTSTabModel ttsTabModel){
        this.ttsTabModel = ttsTabModel;
    }

//    public RadioGroup.OnCheckedChangeListener ttsEnginesChanged = new RadioGroup.OnCheckedChangeListener(){
//
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//            ttsTabModel.setEttsEngineIdenty(checkedId);
//
//            TTSModule.getInstance().recreateTTSWorker();
//
//            ttsTabModel.getActivity().startActivity(new Intent(ttsTabModel.getActivity(), TTSTabOptionActivity.class));;
//        }
//    };
}
