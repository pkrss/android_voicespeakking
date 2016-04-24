package com.pkrss.voicespeakking.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pkrss.common.base.BaseBackActivity;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.ActivityTtshistoryBinding;
import com.pkrss.voicespeakking.databinding.ActivityTtstabBinding;
import com.pkrss.voicespeakking.handler.TTSHistoryHandler;
import com.pkrss.voicespeakking.handler.TTSTabHandler;
import com.pkrss.voicespeakking.model.TTSHistoryModel;
import com.pkrss.voicespeakking.model.TTSTabModel;

public final class TTSHistoryActivity extends BaseBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttshistory);

        ActivityTtshistoryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ttshistory);

        TTSHistoryModel ttsHistoryModel = new TTSHistoryModel();
        TTSHistoryHandler ttsTabHandler = new TTSHistoryHandler();
        binding.setTtsHistoryModel(ttsHistoryModel);
        binding.setTtsHistoryHandler(ttsTabHandler);

    }
}
