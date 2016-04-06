package com.pkrss.voicespeakking.handler;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.pkrss.module.tts.ifly.ApkInstaller;
import com.pkrss.voicespeakking.model.TTSTabPkrssModel;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSTabPkrssHandler {
    private Context context;
    private TTSTabPkrssModel ttsTabPkrssModel;
    // 语记安装助手类
    ApkInstaller mInstaller ;

    public TTSTabPkrssHandler(Context context, TTSTabPkrssModel ttsTabPkrssModel){
        this.context = context;
        this.ttsTabPkrssModel = ttsTabPkrssModel;
    }

    public AdapterView.OnItemSelectedListener onLocaleItemSelected = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public AdapterView.OnItemSelectedListener onEngineItemSelected = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
