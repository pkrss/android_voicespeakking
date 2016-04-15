package com.pkrss.voicespeakking.fragment;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.FragmentTtstabIflyBinding;
import com.pkrss.voicespeakking.handler.TTSTabIFlyHandler;
import com.pkrss.voicespeakking.model.TTSTabIFlyModel;

/**
 * Created by liand on 2016/2/13.
 */
public final class TTSTabIFlyFragment extends Fragment {

    private TTSTabIFlyModel ttsTabIFlyModel;
    private TTSTabIFlyHandler ttsTabIFlyHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Context context = inflater.getContext();
        ttsTabIFlyModel = new TTSTabIFlyModel();
        ttsTabIFlyHandler = new TTSTabIFlyHandler(context, ttsTabIFlyModel);

        FragmentTtstabIflyBinding binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_ttstab_ifly, container, false);
        binding.setTtsTabIFlyModel(ttsTabIFlyModel);
        binding.setTtsTabIFlyHandler(ttsTabIFlyHandler);
        View rootView = binding.getRoot();

        return rootView;
    }
}
