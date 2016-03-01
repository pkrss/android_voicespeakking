package com.pkrss.voicespeakking.handler;

import android.view.View;

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

    private TtsHelper ttsHelper;
    public void clickPlayBtn(View view){
        if(ttsHelper == null){
            ttsHelper = new TtsHelper();
            ttsHelper.init(view.getContext());
//            ttsHelper.play();
            return;
        }

        String context = mainModel.getContentModel().getContent();
        String speakingString = ttsHelper.getPlayingString();
        if(context == speakingString || context.equals(speakingString)) {
            if (ttsHelper.isSpeaking()) {
                ttsHelper.pause();
                mainModel.getPlayerBarModel().setPlaying(false);
            } else {
                ttsHelper.resume();
                mainModel.getPlayerBarModel().setPlaying(true);
            }
        }else{
            ttsHelper.play(context);
            mainModel.getPlayerBarModel().setPlaying(true);
        }
    }
}
