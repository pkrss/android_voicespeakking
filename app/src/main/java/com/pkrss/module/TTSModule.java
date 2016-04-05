package com.pkrss.module;

import android.app.Service;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.pkrss.module.tts.ifly.IflyTTSWorker;
import com.pkrss.module.tts.local.LocalTTSWorker;
import com.pkrss.voicespeakking.data.SpData;
import com.pkrss.voicespeakking.model.PlayerBarModel;

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
         * @return @see: com.pkrss.voicespeakking.common.ETTSEngineIdenty
         */
        int getId();

        /**
         * get show name
         * @return
         */
        String getShowName();

        /**
         *  iniitalize when want to prepare speak.
         *  call destroy,will match this init();
         * @param context
         * @return
         */
        boolean init(Context context);

        /**
         * destroy tts engine
         * @return
         */
        boolean destroy();

        /**
         * is tts speaking
         * @return
         */
        boolean isSpeaking();

        /**
         * get playing string
         * @return
         */
        String getPlayingString();

        /**
         * pause speak
         */
        void pause();

        /**
         * resume speak
         */
        void resume();

        /**
         * play string
         * @param content
         */
        void play(String content);

        /**
         * 不实现此函数 用play实现
         * @param pos
         * @return
         */
//        boolean setCurSpeakPos(int pos);

        /**
         * not call
         */
        void stop();

        /**
         * get option fragment
         * @return
         */
        Fragment createOptionFragment();
    }

    private Context _context;

    private ITtsWorker curWorker;

    private List<ITtsWorker> ttsWorkerList = new ArrayList<ITtsWorker>();

    private static TTSModule instance;
    public static TTSModule getInstance(){
        if(instance==null) {
            instance = new TTSModule();
        }
        return instance;
    }

    private TelephonyManager _tm = null;

    public boolean init(Context context){

        try{
            _tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
            if(_tm!=null)
                _tm.listen(_tm_listener, PhoneStateListener.LISTEN_CALL_STATE);

            StatAnalytics.log(this.getClass().getName() + " init");

        }catch(Exception e){

        }

        if(ttsWorkerList.size()==0) {
            ttsWorkerList.add(new IflyTTSWorker());
            ttsWorkerList.add(new LocalTTSWorker());
            ttsWorkerList.add(new IflyTTSWorker());
        }

        _context = context;
        return recreateTTSWorker();
    }

    public boolean recreateTTSWorker(){
        TTSModule.ITtsWorker worker = null;

        int ettsEngineIdenty = SpData.getTTSEngineIdenty();

        for (TTSModule.ITtsWorker i : ttsWorkerList) {
            if(i.getId()==ettsEngineIdenty){
                worker = i;
                break;
            }
        }

        if(curWorker!=null &&  curWorker.getId()== worker.getId())
            return true;

        if(curWorker!=null){
            curWorker.destroy();
            curWorker = null;
        }

        curWorker = worker;
        return curWorker.init(_context);
    }

    public void uninit(){
        if(_tm!=null) {
            _tm.listen(_tm_listener, PhoneStateListener.LISTEN_NONE);
            _tm = null;
        }
        if(curWorker!=null){
            curWorker.destroy();
            curWorker = null;
        }
    }

    private PhoneStateListener _tm_listener = new PhoneStateListener(){

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            super.onCallStateChanged(state, incomingNumber);

            switch(state){
                case TelephonyManager.CALL_STATE_IDLE:
                    if(curWorker!=null)
                        curWorker.resume();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if(curWorker!=null)
                        curWorker.pause();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    if(curWorker!=null)
                        curWorker.pause();
                    break;
            }
        }
    };

    public ITtsWorker getCurWorker() {
        return curWorker;
    }

    public List<ITtsWorker> getTtsWorkerList() {
        return ttsWorkerList;
    }

    /**
     * call by tts
     * @param pos
     */
    public static void onProgress_triggerEvent(int pos){
        PlayerBarModel.getInstance().setProgress(pos);
    }

    /**
     * call by tts
     * @param utteranceId
     */
    public static void onCompleted_triggerEvent(String utteranceId){
        PlayerBarModel.getInstance().setPlaying(false);
    }

    /**
     * call by tts
     * @param utteranceId
     */
    public static void onError_triggerEvent(String utteranceId){
        PlayerBarModel.getInstance().setPlaying(false);
    }
}
