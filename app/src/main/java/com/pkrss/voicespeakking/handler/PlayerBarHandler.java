package com.pkrss.voicespeakking.handler;

import android.view.View;

import com.pkrss.module.TTSModule;
import com.pkrss.module.ifly.TtsHelper;
import com.pkrss.voicespeakking.model.MainModel;
import com.pkrss.voicespeakking.model.PlayerBarModel;

/**
 * Created by liand on 2016/2/11.
 */
public final class PlayerBarHandler {

    private MainModel mainModel;

    public PlayerBarHandler(MainModel mainModel){
        this.mainModel = mainModel;
    }

    private TTSModule ttsModule;
    public void clickPlayBtn(View view){
        if(ttsModule == null){
            ttsModule = TTSModule.getInstance();
//            ttsHelper.play();
            return;
        }

        TTSModule.ITtsWorker ttsWorker = ttsModule.getCurWorker();
        if(ttsWorker == null)
            return;

        String content = mainModel.getContentModel().getContent();
        String speakingString = ttsWorker.getPlayingString();
        if(content == speakingString || content.equals(speakingString)) {
            if (ttsWorker.isSpeaking()) {
                ttsWorker.pause();
                mainModel.getPlayerBarModel().setPlaying(false);
            } else {
                ttsWorker.resume();
                mainModel.getPlayerBarModel().setPlaying(true);
            }
        }else{
            ttsWorker.play(content);
            mainModel.getPlayerBarModel().setPlaying(true);
        }
    }
}
