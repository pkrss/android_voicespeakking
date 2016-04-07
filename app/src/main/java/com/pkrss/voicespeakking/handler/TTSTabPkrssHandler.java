package com.pkrss.voicespeakking.handler;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.pkrss.module.tts.ifly.ApkInstaller;
import com.pkrss.module.tts.pkrss.PkrssTTSWorker;
import com.pkrss.voicespeakking.adapter.PkrssTTSAdapter;
import com.pkrss.voicespeakking.data.SpData;
import com.pkrss.voicespeakking.fragment.TTSTabPkrssFragment;
import com.pkrss.voicespeakking.model.TTSTabPkrssModel;

import java.util.Collection;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSTabPkrssHandler {
    private Context context;
    private TTSTabPkrssModel ttsTabPkrssModel;
    private TTSTabPkrssFragment ttsTabPkrssFragment;
    private String currentEngineId;

    public TTSTabPkrssHandler(Context context, TTSTabPkrssModel ttsTabPkrssModel, TTSTabPkrssFragment ttsTabPkrssFragment){
        this.context = context;
        this.ttsTabPkrssModel = ttsTabPkrssModel;
        this.ttsTabPkrssFragment = ttsTabPkrssFragment;
    }

    public AdapterView.OnItemSelectedListener onLocaleItemSelected = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position<0 || position>=parent.getCount())
                return;
            PkrssTTSAdapter.PkrssTTSModel item = (PkrssTTSAdapter.PkrssTTSModel) parent.getItemAtPosition(position);
            ttsTabPkrssFragment.refresLocaleEngines(item.getId());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public AdapterView.OnItemSelectedListener onEngineItemSelected = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position<0 || position>=parent.getCount())
                return;
            PkrssTTSAdapter.PkrssTTSModel item = (PkrssTTSAdapter.PkrssTTSModel) parent.getItemAtPosition(position);
            currentEngineId = item.getId();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public void onClickApply(View view){
        if(currentEngineId == null || currentEngineId.length()==0)
            return;
        SpData.setTTSPkrssVoicer(currentEngineId);
    }
}
