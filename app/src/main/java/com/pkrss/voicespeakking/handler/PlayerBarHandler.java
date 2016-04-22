package com.pkrss.voicespeakking.handler;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.SeekBar;

import com.pkrss.module.TTSModule;
import com.pkrss.module.tts.TTSSubPos;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.SeekBarAdapter;
import com.pkrss.voicespeakking.model.MainModel;

/**
 * Created by liand on 2016/2/11.
 */
public final class PlayerBarHandler {

    private MainModel mainModel;

    public PlayerBarHandler(MainModel mainModel){
        this.mainModel = mainModel;
    }

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
        boolean playing = TTSSubPos.play(mainModel.getContentModel().getContent(), 0);
        mainModel.getPlayerBarModel().setPlaying(playing);
    }

    public SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(!fromUser)
                return;

            TTSModule.ITtsWorker ttsWorker = TTSModule.getInstance().getCurWorker();
            if(ttsWorker == null)
                return;

            if(TTSSubPos.getText().length()==0){
                boolean playing = TTSSubPos.play(mainModel.getContentModel().getContent(), progress);
                mainModel.getPlayerBarModel().setPlaying(playing);
            }else {
                boolean playing = TTSSubPos.playProgress(progress);
                mainModel.getPlayerBarModel().setPlaying(playing);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
