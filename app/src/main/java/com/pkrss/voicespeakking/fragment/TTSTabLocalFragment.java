package com.pkrss.voicespeakking.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.FragmentTtstabIflyBinding;
import com.pkrss.voicespeakking.databinding.FragmentTtstabLocalBinding;
import com.pkrss.voicespeakking.handler.TTSTabIFlyHandler;
import com.pkrss.voicespeakking.model.TTSTabIFlyModel;

/**
 * Created by liand on 2016/2/13.
 */
public final class TTSTabLocalFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentTtstabLocalBinding binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_ttstab_local, container, false);
//        binding.setTtsTabIFlyModel(ttsTabIFlyModel);
//        binding.setTtsTabIFlyHandler(ttsTabIFlyHandler);
        View rootView = binding.getRoot();

        return rootView;
    }
}
