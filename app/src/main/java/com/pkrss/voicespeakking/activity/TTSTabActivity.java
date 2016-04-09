package com.pkrss.voicespeakking.activity;

import android.app.ActionBar;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pkrss.common.base.BaseActivity;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.ActivityTtstabBinding;
import com.pkrss.voicespeakking.handler.TTSTabHandler;
import com.pkrss.voicespeakking.model.TTSTabModel;

public class TTSTabActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTtstabBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ttstab);

//        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);

        TTSTabModel ttsTabModel = new TTSTabModel();
        ttsTabModel.setActivity(this);
        TTSTabHandler ttsTabHandler = new TTSTabHandler(ttsTabModel);
        binding.setTtsTabModel(ttsTabModel);
        binding.setTtsTabHandler(ttsTabHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.back) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
