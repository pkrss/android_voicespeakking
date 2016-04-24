package com.pkrss.voicespeakking.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.pkrss.common.base.BaseBackActivity;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.ActivityAboutUsBinding;
import com.pkrss.voicespeakking.handler.AboutUsHandler;

public final class AboutUsActivity extends BaseBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAboutUsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about_us);

        AboutUsHandler aboutUsHandler = new AboutUsHandler();
        binding.setAboutUsHandler(aboutUsHandler);
    }

}
