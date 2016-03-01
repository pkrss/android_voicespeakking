package com.pkrss.common.data;

import android.util.Log;

/**
 * Created by 1 on 2015/11/6.
 */
abstract class DataAbstract implements IData{
    protected void log(String message){
        Log.d("data", message);
    }

    @Override
    public void putInt(String key, int value) {
        putString(key,"" + value);
    }

    @Override
    public int getInt(String key, int defValue) {
        String s = getString(key, null);
        if(s == null)
            return defValue;
        try {
            return Integer.parseInt(s);
        }catch (Exception e){
            log("sp.getInt " + key + " exception: " + e.getMessage());
        }
        return defValue;
    }

    @Override
    public void putBoolean(String key, boolean value) {
        putString(key,value? "1":"0");
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        String s = getString(key, null);
        if(s == null)
            return defValue;
        return s.equals("1");
    }
}
