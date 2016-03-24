package com.pkrss.module.tts.local;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import com.pkrss.module.TTSModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by liand on 2016/3/24.
 */

public class LocalTTSWorker implements TTSModule.ITtsWorker

    private TextToSpeech mSpeech;
    private Context mcontext;
    private Boolean mInited = false;
    private HashMap<String, String> mparams = new HashMap<String, String>();

    Boolean mManualSetEngineName = false;
    Boolean mCurrentTTSLangOk = false;
    int mCurrentTTSEngineIndex = 0;
    TextToSpeech.OnInitListener mTTSLisntener;
    int mCecreateTTS = 0;

    @Override
    public String getShowName(){
        return "_local";
    }

    @Override
    public boolean init(Context context){

        mcontext = context.getApplicationContext();

        return _initAfterTTSChecked();
    }

    public boolean _initAfterTTSChecked(){
        try{
            mparams.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"stringId");

            mTTSLisntener = new TextToSpeech.OnInitListener() {

                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {

                        if(!mManualSetEngineName && !changeLang()){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
                                if(changeEngine())
                                    return;
                            }

                            Toast.makeText(mcontext, mcontext.getString(R.string.error_not_found_tts_data), Toast.LENGTH_LONG).show();
                        }else{
                            mCurrentTTSLangOk = true;
                        }

                        // only once time to call init complete event.
//	                	if(!mInited){

                        mInited = true;
                        //mSpeech.setSpeechRate((float) .5);


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
                            onInitStep_CompletedEvent();
                        else
                            onInitStep_CompletedOldEvent();
//	                	}

                        _speakNext();
                    }

                    onInitStep_triggerEvent(status);
                }
            };

            recreateTTS();

            _initLocalTTSLocales();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void recreateTTS(){
        releaseSpeech();

        if(mcurEngineName==null || mcurEngineName.length()==0 || (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH))
            mSpeech = new TextToSpeech(mcontext, mTTSLisntener);
        else
            _createEngineByName(mcurEngineName);
    }

    private void _initLocalTTSLocales(){
        if(engineInfos == null) {
            // init tts locale list
            Locale[] sysLocales = Locale.getAvailableLocales();
            Map<String, List<Locale>> locales = new HashMap<String, List<Locale>>();
            for (Locale loc : sysLocales) {
                String tmp = loc.getCountry();
                if (!WebHelper.IsNullOrEmpty(loc.getLanguage()))
                    tmp += "_" + loc.getLanguage();
                List<Locale> a = new ArrayList<Locale>();
                a.add(loc);
                locales.put(tmp, a);
            }
            new LocalTTSHelper().loadEngines(locales, new TTSEngineInfosEventHandler() {

                @Override
                public void onCompleted(List<TTSEngineInfo> engineInfos) {
                    LocalTTS.this.engineInfos = engineInfos;
                    _init_locale_and_engine_cb();
                }

            });
        }else{
            _init_locale_and_engine_cb();
        }
    }

    String mcurEngineName;

    private static int mCurPlayingUtteranceIdInt = 0;
    private static String mCurPlayingUtteranceId = "";

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void onInitStep_CompletedEvent(){
        mSpeech.setOnUtteranceProgressListener(new android.speech.tts.UtteranceProgressListener(){

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
                onError_triggerEvent(mutteranceId);
            }

        });
    }

    @SuppressWarnings("deprecation")
    private void onInitStep_CompletedOldEvent(){
        mSpeech.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener(){

            @Override
            public void onUtteranceCompleted(String arg0) {
                if(mCurPlayingUtteranceId.equals(arg0))
                    _speakNext();
            }

        });
    }

    @Override
    public boolean destroy(){

        mInited = false;

        releaseSpeech();

        return true;
    }

    private void releaseSpeech(){
        if (mSpeech != null) {
            mSpeech.stop();
            mSpeech.shutdown();
            mSpeech = null;
        }
    }


    @Override
    public void stop() {
        if(mSpeech == null)
            return;

        mCurPlayingUtteranceId = "EOF";
        if(mSpeech.isSpeaking())
            mSpeech.stop();
    }

    protected void _onDoSub(String text) {
        mCurPlayingUtteranceId = "" + (mCurPlayingUtteranceIdInt++);
        mparams.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, mCurPlayingUtteranceId);
        if(mSpeech!=null)

            if(TextToSpeech.ERROR == mSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, mparams)){
                if(++mCecreateTTS < 3)
                    recreateTTS();
            }
    }
    @Override
    public boolean isSpeaking(){

        if(mSpeech == null)
            return false;
        return mSpeech.isSpeaking();
    }

}
