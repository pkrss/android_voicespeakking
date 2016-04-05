package com.pkrss.voicespeakking.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.pkrss.module.tts.ifly.ApkInstaller;
import com.pkrss.voicespeakking.model.TTSTabPkrssModel;

public final class TTSTabLocalHandler {

    public static void clickSetting(View v){
        try{
            Intent intent = new Intent();
            intent.setAction("com.android.settings.TTS_SETTINGS");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            v.getContext().startActivity(intent);
        }catch(Exception e){
            Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
