package com.pkrss.common.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1 on 2015/11/6.
 */
public class DataLocal extends DataAbstract {

    /**
     * 本地SharePreferences
     */
    protected SharedPreferences sp;

    /**
     * 应用程序上下文
     */
    @Override
    public void init(Context context){
        try {
            context = context.getApplicationContext();
            sp = context.getSharedPreferences("default", Context.MODE_PRIVATE);
        } catch (Exception e) {
            log("spLocal.init exception: " + e.getMessage());
        }
    }

    /**
     * 写本地配置
     * @param key
     *      键
     * @param value
     *      值
     */
    @Override
    public void putString(String key, String value) {

        if(sp == null) {
            log("putString sp = null");
            return;
        }

        try{
            sp.edit().putString(key,value).apply();
        }catch (Exception e){
            log("putString " + key + " exception: " + e.getMessage());
        }
    }

    /**
     * 读本地配置
     * @param key
     *      键
     * @return 值
     */
    @Override
    public String getString(String key, String defValue) {
        if(sp == null) {
            log("getString sp = null");
            return defValue;
        }

        try{
            return sp.getString(key,defValue);
        }catch (Exception e){
            log("getString " + key + " exception: " + e.getMessage());
        }

        return defValue;
    }

    @Override
    public void remove(String key) {
        if(sp == null)
            return;

        try{
            sp.edit().remove(key);
        }catch (Exception e){
            log("sp.remove " + key + " exception: " + e.getMessage());
        }
    }

    @Override
    public boolean contains(String key) {
        if(sp == null)
            return false;

        try{
            return sp.contains(key);
        }catch (Exception e){
            log("sp.contains " + key + " exception: " + e.getMessage());
        }
        return false;
    }

    private HashMap<IDataChanged,OnSharedPreferenceChangeListenerBridge> d2l = new HashMap<IDataChanged,OnSharedPreferenceChangeListenerBridge>();
    private final class OnSharedPreferenceChangeListenerBridge implements SharedPreferences.OnSharedPreferenceChangeListener
    {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            for(Map.Entry<IDataChanged, OnSharedPreferenceChangeListenerBridge> kv : d2l.entrySet()){
                if(kv.getValue().equals(sharedPreferences)){
                    kv.getKey().onDataChanged(DataLocal.this, key);
                    break;
                }
            }
        }
    }

    @Override
    public void registerOnDataChanged(IDataChanged listener){
        OnSharedPreferenceChangeListenerBridge listenerBridge = new OnSharedPreferenceChangeListenerBridge();
        d2l.put(listener, listenerBridge);
        sp.registerOnSharedPreferenceChangeListener(listenerBridge);
    }

    @Override
    public void unregisterOnDataChanged(IDataChanged listener){
        OnSharedPreferenceChangeListenerBridge v = d2l.get(listener);
        if(v == null)
            return;
        sp.unregisterOnSharedPreferenceChangeListener(v);
        d2l.remove(listener);
    }
}
