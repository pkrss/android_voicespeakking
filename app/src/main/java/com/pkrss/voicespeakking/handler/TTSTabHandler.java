package com.pkrss.voicespeakking.handler;

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
