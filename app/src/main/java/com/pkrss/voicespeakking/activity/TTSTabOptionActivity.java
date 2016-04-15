package com.pkrss.voicespeakking.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.pkrss.common.base.BaseBackActivity;
import com.pkrss.module.TTSModule;
import com.pkrss.voicespeakking.R;

public class TTSTabOptionActivity extends BaseBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        updateFragment();
    }

    private void updateFragment() {
        Fragment f = TTSModule.getInstance().getCurWorker().createOptionFragment();
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.replace(R.id.content, f);
        ft.commit();
    }
}
