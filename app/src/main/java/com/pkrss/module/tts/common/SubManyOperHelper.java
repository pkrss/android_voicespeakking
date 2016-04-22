package com.pkrss.module.tts.common;

import com.pkrss.module.tts.TTSSubPos;

public final class SubManyOperHelper {

    private Boolean mStartedStatus = false;
    private int mCurTextPos = -1;
    private String mCurTxt = "";

    private static final int LEN = 256;

//	private Timer mTimer;
//	private TimerTask mTimerTask;

    public interface ICallback {
        void onProgressPercent(int progressPercent);

        void onStop();

        void onDoSub(String text);
    }

    ICallback _callBack = null;

    public void setCallBack(ICallback cb) {
        _callBack = cb;
    }

//	public void onPause(){
//		mPauseStatus = true;
//	}
//	
//	public void onResume(){
//		mPauseStatus = false;
//	}

    public boolean start() {

        mCurTextPos = 0;

        mStartedStatus = true;

        return true;
    }

    public Boolean doNext() {

        do {
            String text = TTSSubPos.getText();
            if (text == null ||
                    text.length() == 0 ||
                    mCurTextPos < 0)
                break;

            if (mCurTextPos >= text.length()) {
                if (_callBack != null) {
                    _callBack.onProgressPercent(100);
                }
                break;
            }

            mCurTxt = "";
            if (text.length() > mCurTextPos + LEN) {
                mCurTxt = text.substring(mCurTextPos, mCurTextPos + LEN);

                if (_callBack != null) {
                    _callBack.onProgressPercent(getProgressPercent(mCurTextPos,text.length()));
                }
                mCurTextPos += LEN;
            } else {
                mCurTxt = text.substring(mCurTextPos);

                if (_callBack != null) {
                    _callBack.onProgressPercent(getProgressPercent(mCurTextPos-1,text.length()));
                }

                mCurTextPos = text.length();
            }
            if (mCurTxt == null || mCurTxt.length() == 0)
                break;

            if (_callBack != null)
                _callBack.onDoSub(mCurTxt);

            return true;

        } while (false);

        stop();

        return false;
    }

    private static int getProgressPercent(int curPos,int totalLength){
        int progressPercent = 0;
        if(curPos>=totalLength)
            return 100;
        if(curPos<=0)
            return 0;
        return (curPos * 100) / totalLength;
    }

    public int getCurSpeakPos() {
        String text = TTSSubPos.getText();
        if (text == null || text.length() == 0)
            return 0;
        int len = text.length();
        if (len > 0)
            return mCurTextPos * 100 / len;
        return 0;
    }

    public void stop() {

        mCurTxt = "";

        mCurTextPos = -1;

//			if(mTimer!=null)
//				mTimer.cancel();

        if (mStartedStatus) {
            mStartedStatus = false;

            if (_callBack != null)
                _callBack.onStop();
        }
    }

    public boolean isStarted(){
        return mStartedStatus;
    }
}