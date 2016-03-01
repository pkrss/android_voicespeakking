package com.pkrss.voicespeakking.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.FragmentTtstabBinding;
import com.pkrss.voicespeakking.handler.TTSTabHandler;
import com.pkrss.voicespeakking.model.TTSTabModel;

/**
 * Created by liand on 2016/2/13.
 */
public final class TTSTabFirstFragment extends Fragment {

    private TTSTabModel ttsTabModel;
    private TTSTabHandler ttsTabHandler;

    public TTSTabModel getTtsTabModel() {
        return ttsTabModel;
    }

    public void setTtsTabModel(TTSTabModel ttsTabModel) {
        this.ttsTabModel = ttsTabModel;
    }

    public TTSTabHandler getTtsTabHandler() {
        return ttsTabHandler;
    }

    public void setTtsTabHandler(TTSTabHandler ttsTabHandler) {
        this.ttsTabHandler = ttsTabHandler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentTtstabBinding binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_ttstab, container, false);
        binding.setTtsTabModel(ttsTabModel);
        binding.setTtsTabHandler(ttsTabHandler);
        View rootView = binding.getRoot();

        return rootView;
    }
}
