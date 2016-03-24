package com.pkrss.voicespeakking.handler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.RadioGroup;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pkrss.module.tts.ifly.ApkInstaller;
import com.pkrss.module.tts.ifly.TtsSettings;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.model.TTSTabIFlyModel;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSTabIFlyHandler {
    private Context context;
    private TTSTabIFlyModel ttsTabIFlyModel;
    // 语记安装助手类
    ApkInstaller mInstaller ;

    public TTSTabIFlyHandler(Context context, TTSTabIFlyModel ttsTabIFlyModel){
        this.context = context;
        this.ttsTabIFlyModel = ttsTabIFlyModel;
        mInstaller = new  ApkInstaller(context);
    }

    public void clickPeoplePronunciation(View v){
        if(ttsTabIFlyModel.isOnline()){

            // 云端发音人名称列表
            final String[] mCloudVoicersEntries = context.getResources().getStringArray(R.array.voicer_cloud_entries);
            final String[] mCloudVoicersValue = context.getResources().getStringArray(R.array.voicer_cloud_values);

            int selectedNum = 0;
            String voicer = ttsTabIFlyModel.getVoicer();
            for(int i=0,c=mCloudVoicersValue.length;i<c;++i){
                if(mCloudVoicersValue[i].equals(voicer)) {
                    selectedNum = i;
                    break;
                }
            }

            new AlertDialog.Builder(context).setTitle(R.string.onlinesynthesisoption)
                    .setSingleChoiceItems(mCloudVoicersEntries, // 单选框有几项,各是什么名字
                            selectedNum, // 默认的选项
                            new DialogInterface.OnClickListener() { // 点击单选框后的处理
                                public void onClick(DialogInterface dialog,
                                                    int which) { // 点击了哪一项
                                    String voicer = mCloudVoicersValue[which];
                                    ttsTabIFlyModel.setVoicer(voicer);
//                                    if ("catherine".equals(voicer) || "henry".equals(voicer) || "vimary".equals(voicer)) {
//                                        ((EditText) findViewById(R.id.tts_text)).setText(R.string.text_tts_source_en);
//                                    }else {
//                                        ((EditText) findViewById(R.id.tts_text)).setText(R.string.text_tts_source);
//                                    }
                                    dialog.dismiss();
                                }
                            }).show();
        }else{
            if (!SpeechUtility.getUtility().checkServiceInstalled()) {
                mInstaller.install();
            }else {
                SpeechUtility.getUtility().openEngineSettings(SpeechConstant.ENG_TTS);
            }
        }
    }

    public void clickSetting(View v){
        if(ttsTabIFlyModel.isOnline()){
            Intent intent = new Intent(context, TtsSettings.class);
            context.startActivity(intent);
        }else{
            // 本地设置跳转到语记中
            if (!SpeechUtility.getUtility().checkServiceInstalled()) {
                mInstaller.install();
            }else {
                SpeechUtility.getUtility().openEngineSettings(null);
            }
        }
    }

    public RadioGroup.OnCheckedChangeListener ttsEnginesChanged = new RadioGroup.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if(checkedId == R.id.local){
                ttsTabIFlyModel.setOnline(false);
            }else if(checkedId == R.id.internet){
                ttsTabIFlyModel.setOnline(true);
            }
        }
    };
}
