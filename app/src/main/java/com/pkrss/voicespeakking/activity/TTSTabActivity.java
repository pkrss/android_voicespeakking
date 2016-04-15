package com.pkrss.voicespeakking.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.pkrss.common.base.BaseActivity;
import com.pkrss.common.base.BaseBackActivity;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.ActivityTtstabBinding;
import com.pkrss.voicespeakking.handler.TTSTabHandler;
import com.pkrss.voicespeakking.model.TTSTabModel;

public class TTSTabActivity extends BaseBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTtstabBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ttstab);

        TTSTabModel ttsTabModel = new TTSTabModel();
        ttsTabModel.setActivity(this);
        TTSTabHandler ttsTabHandler = new TTSTabHandler(ttsTabModel);
        binding.setTtsTabModel(ttsTabModel);
        binding.setTtsTabHandler(ttsTabHandler);
    }


}
