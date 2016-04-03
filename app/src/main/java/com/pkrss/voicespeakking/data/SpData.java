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
    public static int getTTSEngineIdenty(){
        return dataLocal.getInt(KEY_TTSEngineIdenty, ETTSEngineIdenty.local);
    }
    public static void setTTSEngineIdenty(int v){
        dataLocal.putInt(KEY_TTSEngineIdenty, v);
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

    private static final String KEY_TTS_PKSS_Voicer = "TTSPkrssVoicer";
    public static String getTTSPkrssVoicer(){
        return dataLocal.getString(KEY_TTS_PKSS_Voicer, null);
    }
    public static void setTTSPkrssVoicer(String v){
        dataLocal.putString(KEY_TTS_PKSS_Voicer, v);
    }
}
