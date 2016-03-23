package com.pkrss.module;

import android.content.Context;

import com.pkrss.module.ifly.TtsHelper;
import com.pkrss.voicespeakking.common.ETTSEngineIdenty;

import java.util.ArrayList;
import java.util.List;

/**
 * tts module
 */
public final class TTSModule {

    /**
     * tts function worker
     */
    public interface ITtsWorker{
        /**
         * get worker id
         * @return
         */
        int getId();

        /**
         * get show name
         * @return
         */
        String getShowName();

        boolean init(Context context);

        boolean destroy();

        boolean isSpeaking();

        String getPlayingString();

        void pause();

        void resume();

        void play(String content);

        void stop();
    }

    private ITtsWorker curWorker;

    private List<ITtsWorker> ttsWorkerList = new ArrayList<ITtsWorker>();

    private static TTSModule instance;
    public static TTSModule getInstance(){
        if(instance==null) {
            instance = new TTSModule();
        }
        return instance;
    }

    public boolean init(Context context){
        curWorker = new TtsHelper();

        ttsWorkerList.add(curWorker);

        return curWorker.init(context);
    }

    public ITtsWorker getCurWorker() {
        return curWorker;
    }

    public List<ITtsWorker> getTtsWorkerList() {
        return ttsWorkerList;
    }
}
