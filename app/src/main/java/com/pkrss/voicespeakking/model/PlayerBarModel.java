package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.pkrss.voicespeakking.BR;

/**
 * Created by liand on 2016/2/11.
 */
public final class PlayerBarModel extends BaseObservable {
    private boolean playing = false;
    private int progress;
    private int max;

    private PlayerBarModel(){

    }

    private static PlayerBarModel instance;

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

    @Bindable
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if(this.progress == progress)
            return;
        this.progress = progress;
        notifyPropertyChanged(BR.progress);
    }

    @Bindable
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        if(this.max == max)
            return;
        this.max = max;
        notifyPropertyChanged(BR.max);
    }
}
