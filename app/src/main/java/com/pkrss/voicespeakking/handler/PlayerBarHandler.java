package com.pkrss.voicespeakking.handler;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.pkrss.module.TTSModule;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.model.MainModel;

/**
 * Created by liand on 2016/2/11.
 */
public final class PlayerBarHandler {

    private MainModel mainModel;

    public PlayerBarHandler(MainModel mainModel){
        this.mainModel = mainModel;
    }

    private TTSModule ttsModule;

    private DrawerLayout drawer;

    public void clickLeftBtn(View view){
        if(drawer == null)
            drawer = (DrawerLayout)mainModel.getActivity().findViewById(R.id.drawer_layout);
        if(drawer == null)
            return;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            drawer.openDrawer(GravityCompat.START);
        }
    }

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
