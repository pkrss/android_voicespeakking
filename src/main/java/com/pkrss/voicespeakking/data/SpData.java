package com.pkrss.voicespeakking.data;

import android.content.Context;

import com.pkrss.common.data.DataLocal;
import com.pkrss.voicespeakking.common.ETTSEngineIdenty;

/**
 * Created by liand on 2016/2/13.
 */
public final class SpData {

    private SpData() {
    }

    private static DataLocal dataLocal;
    public static void init(Context context){
        dataLocal = new DataLocal();
        dataLocal.init(context);
    }

    private static final String KEY_TTSEngineIdenty = "TTSEngineIdenty";
    public static ETTSEngineIdenty getTTSEngineIdenty(){
        int v = dataLocal.getInt(KEY_TTSEngineIdenty,0);
        if(v == 1)
            return ETTSEngineIdenty.pkrss;
        if(v == 2)
            return ETTSEngineIdenty.ifly;
        return ETTSEngineIdenty.local;
    }
    public static void setTTSEngineIdenty(ETTSEngineIdenty v){
        int i = 0;
        if(v == ETTSEngineIdenty.pkrss)
            i = 1;
        else if(v == ETTSEngineIdenty.ifly)
            i = 2;
        dataLocal.putInt(KEY_TTSEngineIdenty, i);
    }

    private static final String KEY_TTSIFLYONLINE = "TTSIFlyOnline";
    public static boolean getTTSIFlyOnline(){
        return dataLocal.getBoolean(KEY_TTSIFLYONLINE, false);
    }
    public static void setTTSIFlyOnline(boolean v){
        dataLocal.putBoolean(KEY_TTSIFLYONLINE, v);
    }

    private static final String KEY_TTSIFLYVOICER = "TTSIFlyVoicer";
    public static String getTTSIFlyVoicer(){
        return dataLocal.getString(KEY_TTSIFLYVOICER, "xiaoyan");
    }
    public static void setTTSIFlyVoicer(String v){
        dataLocal.putString(KEY_TTSIFLYVOICER, v);
    }

    private static final String KEY_TTSPKRSSID = "TTSPkrssId";
    public static String getTTSPkrssId(){
        return dataLocal.getString(KEY_TTSPKRSSID, null);
    }
    public static void setTTSPkrssId(String v){
        dataLocal.putString(KEY_TTSPKRSSID, v);
    }
}
