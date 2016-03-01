package com.pkrss.voicespeakking.handler;

import android.widget.RadioGroup;

import com.pkrss.voicespeakking.R;
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

    public RadioGroup.OnCheckedChangeListener ttsEnginesChanged = new RadioGroup.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if(checkedId == R.id.local){
                ttsTabModel.setEttsEngineIdenty(ETTSEngineIdenty.local);
            }else if(checkedId == R.id.pkrss){
                ttsTabModel.setEttsEngineIdenty(ETTSEngineIdenty.pkrss);
            }else if(checkedId == R.id.ifly){
                ttsTabModel.setEttsEngineIdenty(ETTSEngineIdenty.ifly);
            }

            ttsTabModel.getViewPager().setCurrentItem(1, true);
        }
    };
}
