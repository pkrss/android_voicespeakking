package com.pkrss.common.data;

import android.content.Context;

/**
 * 配置接口
 * Created by 1 on 2015/11/2.
 */
public interface IData {
    /**
     * 初始化
     * @param context 应用程序上下文
     */
    void init(Context context);

    void putString(String key, String value);
    String getString(String key, String defValue);

    void putInt(String key, int value);
    int getInt(String key, int defValue);

    void putBoolean(String key, boolean value);
    boolean getBoolean(String key, boolean defValue);

    void remove(String key);

    boolean contains(String key);

    void registerOnDataChanged(IDataChanged listener);
    void unregisterOnDataChanged(IDataChanged listener);
}
