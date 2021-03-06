package com.pkrss.module.tts.local;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.app.Fragment;
import android.util.Log;

import com.pkrss.module.tts.common.BaseTTS;
import com.pkrss.voicespeakking.common.ETTSEngineIdenty;
import com.pkrss.voicespeakking.fragment.TTSTabLocalFragment;

import java.util.HashMap;

/**
 * Created by liand on 2016/3/24.
 */

public final class LocalTTSWorker extends BaseTTS {

    private static final String LOGTAG = LocalTTSWorker.class.getSimpleName();

    private TextToSpeech _speech;
    private Context _context;
    private HashMap<String, String> _tts_params;
    private TextToSpeech.OnInitListener _ttsLisntener;

    /**
     * curreent tts engine name
     */
    private String _engineName;

    /**
     * current speak id
     */
    private int mCurPlayingUtteranceIdInt = 0;

    /**
     * current speak status
     */
    private String mCurPlayingUtteranceId = "";

    int mCecreateTTS = 0;

    private UtteranceProgressListener utteranceProgressListener = new UtteranceProgressListener(){

        @Override
        public void onStart(String utteranceId) {

        }

        @Override
        public void onDone(String utteranceId) {
            Log.d(LOGTAG, "onDone:" + utteranceId);
            if(mCurPlayingUtteranceId.equals(utteranceId))
                _speakNext();
        }

        @Override
        public void onError(String utteranceId) {
            Log.d(LOGTAG, "onError:" + utteranceId);
            stop();
//                        onError_triggerEvent(mutteranceId);
        }

    };

    @Override
    public int getId() {
        return ETTSEngineIdenty.local;
    }

    @Override
    public String getShowName(){
        return "_local";
    }

    @Override
    public boolean init(Context context){

        super.init(context);

        _context = context.getApplicationContext();

        try{
            if(_tts_params == null) {
                _tts_params = new HashMap<String, String>();
                _tts_params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "stringId");
            }

            if(_ttsLisntener == null) {
                _ttsLisntener = new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            _speakNext();
                        }
                    }
                };
            }

            recreateTTS();

//            _initLocalTTSLocales();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void recreateTTS(){
        releaseSpeech();

        _speech = new TextToSpeech(_context, _ttsLisntener, _engineName);

        _speech.setOnUtteranceProgressListener(utteranceProgressListener);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void onInitStep_CompletedEvent(){

    }

    @SuppressWarnings("deprecation")
    private void onInitStep_CompletedOldEvent(){
        _speech.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {

            @Override
            public void onUtteranceCompleted(String arg0) {
                if (mCurPlayingUtteranceId.equals(arg0))
                    _speakNext();
            }

        });
    }

    @Override
    public boolean destroy(){

        releaseSpeech();

        return true;
    }

    private void releaseSpeech(){
        if (_speech != null) {
            _speech.setOnUtteranceProgressListener(null);
            _speech.stop();
            _speech.shutdown();
            _speech = null;
        }
    }


    @Override
    protected void _child_onDoSub(String text) {
        mCurPlayingUtteranceId = "" + (mCurPlayingUtteranceIdInt++);
        _tts_params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, mCurPlayingUtteranceId);
        if(_speech!=null)

            if(TextToSpeech.ERROR == _speech.speak(text, TextToSpeech.QUEUE_FLUSH, _tts_params)){
                if(++mCecreateTTS < 3)
                    recreateTTS();
            }
    }

    @Override
    protected void _child_onDoStop() {
        if(_speech == null)
            return;

        mCurPlayingUtteranceId = "EOF";
        if(_speech.isSpeaking())
            _speech.stop();
    }

    @Override
    public boolean isSpeaking(){

        if(_speech == null)
            return false;
        return _speech.isSpeaking();
    }


    @Override
    public void pause() {
        super.pause();

        if(_speech == null)
            return;
        _speech.stop();
    }

    @Override
    public void resume() {
        super.resume();

        if(_speech == null)
            return;

        _speakNext();
    }
    @Override
    public Fragment createOptionFragment(){
        return new TTSTabLocalFragment();
    }
}
