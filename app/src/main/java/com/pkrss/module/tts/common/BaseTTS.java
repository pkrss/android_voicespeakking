package com.pkrss.module.tts.common;

import android.app.Service;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.pkrss.module.StatAnalytics;
import com.pkrss.module.TTSModule;


public abstract class BaseTTS implements TTSModule.ITtsWorker {

    protected String mutteranceId;

    SubManyOperHelper _subManyOperHelper = new SubManyOperHelper();

    @Override
    public void play(String content) {
        StatAnalytics.log(this.getClass().getName() + " prepare sayText");

        _subManyOperHelper.start(content);

        _speakNext();
    }

    protected void _speakNext() {
        _subManyOperHelper.doNext();

        StatAnalytics.log(this.getClass().getName() + " sayNext");
    }


    @Override
    public void stop() {
        if(!_subManyOperHelper.isStarted())
            return;

        _subManyOperHelper.stop();
        TTSModule.onCompleted_triggerEvent();
        _child_onDoStop();
    }

    protected abstract void _child_onDoSub(String text);

    protected abstract void _child_onDoStop();

    // phone checked start
    SubManyOperHelper.ICallback _subManyOperHelperCallBack = new SubManyOperHelper.ICallback() {

        @Override
        public void onProgress(int pos) {
             pos += _subManyOperHelper.getCurSpeakPos();

            TTSModule.onProgress_triggerEvent(pos);
        }

        @Override
        public void onStop() {
            stop();
        }

        @Override
        public void onDoSub(String text) {
            _child_onDoSub(text);
        }

    };


    @Override
    public boolean init(Context parent) {

        _subManyOperHelper = new SubManyOperHelper();
        _subManyOperHelper.setCallBack(_subManyOperHelperCallBack);

        return true;
    }

    @Override
    public boolean destroy() {

        try {
            this.stop();

            StatAnalytics.log(this.getClass().getName() + " destory");
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String getPlayingString() {
        return _subManyOperHelper.getText();
    }
}
