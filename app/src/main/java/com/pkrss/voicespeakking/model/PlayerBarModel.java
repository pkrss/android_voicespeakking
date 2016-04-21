package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.SeekBar;

import com.pkrss.module.tts.TTSSubPos;
import com.pkrss.voicespeakking.BR;
import com.pkrss.voicespeakking.databinding.SeekBarAdapter;

/**
 * Created by liand on 2016/2/11.
 */
public final class PlayerBarModel extends BaseObservable {
    private boolean playing = false;
    private SeekBar seekBarCtl;

    private static PlayerBarModel instance;

    private PlayerBarModel(){

    }

//    @Bindable
    public int getProgressPercent() {
        return TTSSubPos.getProgressPercent();
    }

    public void setProgressPercent(int progressPercent) {
        int newProgressPercent = TTSSubPos.setProgressPercent(progressPercent);
        if(newProgressPercent == progressPercent)
            return;
//        notifyPropertyChanged(BR.progressPercent);

        if(seekBarCtl==null)
            return;
        seekBarCtl.setProgress(newProgressPercent);
    }

    public static PlayerBarModel getInstance() {
        if(instance == null)
            instance = new PlayerBarModel();
        return instance;
    }

    @Bindable
    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        if(this.playing == playing)
            return;
        this.playing = playing;
        notifyPropertyChanged(BR.playing);
    }

    public SeekBarAdapter.ISetHosterListener hosterListener = new SeekBarAdapter.ISetHosterListener(){

        @Override
        public void setHoster(SeekBar seekBar) {
            seekBarCtl = seekBar;
        }
    };
}
