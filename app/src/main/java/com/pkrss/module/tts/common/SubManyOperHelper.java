package com.pkrss.module.tts.common;

public final class SubManyOperHelper {

    private Boolean mStartedStatus = false;
    private int mCurTextPos = -1;
    private String mText = "";
    private String mCurTxt = "";

    private static final int LEN = 256;

//	private Timer mTimer;
//	private TimerTask mTimerTask;

    public interface ICallback {
        void onProgress(int pos);

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

    public boolean start(String fullText) {

        mCurTextPos = 0;
        mText = fullText;

        mStartedStatus = true;

        return true;
    }

    public Boolean doNext() {

        do {
            if (mText == null ||
                    mText.length() == 0 ||
                    mCurTextPos < 0 ||
                    (mCurTextPos >= mText.length()))
                break;

            mCurTxt = "";
            if (mText.length() > mCurTextPos + LEN) {
                mCurTxt = mText.substring(mCurTextPos, mCurTextPos + LEN);

                if (_callBack != null)
                    _callBack.onProgress(mCurTextPos);

                mCurTextPos += LEN;
            } else {
                mCurTxt = mText.substring(mCurTextPos);
                mCurTextPos = mText.length();

                if (_callBack != null)
                    _callBack.onProgress(mCurTextPos - 1);
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

    public int getCurSpeakPos() {
        if (mText == null || mText.length() == 0)
            return 0;
        int len = mText.length();
        if (len > 0)
            return mCurTextPos * 100 / len;
        return 0;
    }

    public boolean setCurSpeakPos(int pos) {

        if (mText == null || mText.length() == 0)
            return false;

        if (pos < 0 || pos > 100)
            return false;

        int len = mText.length();
        mCurTextPos = pos * len / 100;

        doNext();

        return true;
    }

    public void stop() {

        mText = "";
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

    public String getText() {
        return mText;
    }

    public boolean isStarted(){
        return mStartedStatus;
    }
}