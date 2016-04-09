package com.pkrss.voicespeakking.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.ActivityTtstabBinding;
import com.pkrss.voicespeakking.handler.TTSTabHandler;
import com.pkrss.voicespeakking.model.TTSTabModel;

public class TTSTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTtstabBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ttstab);

        TTSTabModel ttsTabModel = new TTSTabModel();
        ttsTabModel.setActivity(this);
        TTSTabHandler ttsTabHandler = new TTSTabHandler(ttsTabModel);
        binding.setTtsTabModel(ttsTabModel);
        binding.setTtsTabHandler(ttsTabHandler);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_ab_back_holo_dark_am));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
