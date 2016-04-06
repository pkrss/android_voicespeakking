package com.pkrss.voicespeakking.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkrss.module.tts.pkrss.PkrssTTSWorker;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.adapter.PkrssTTSAdapter;
import com.pkrss.voicespeakking.databinding.FragmentTtstabPkrssBinding;
import com.pkrss.voicespeakking.handler.TTSTabPkrssHandler;
import com.pkrss.voicespeakking.model.TTSTabPkrssModel;

/**
 * Created by liand on 2016/2/13.
 */
public final class TTSTabPkrssFragment extends Fragment {

    private TTSTabPkrssModel ttsTabPkrssModel;
    private TTSTabPkrssHandler ttsTabPkrssHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ttsTabPkrssModel = new TTSTabPkrssModel();
        ttsTabPkrssHandler = new TTSTabPkrssHandler(getContext(), ttsTabPkrssModel);

        FragmentTtstabPkrssBinding binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_ttstab_pkrss, container, false);
        binding.setTtsTabPkrssModel(ttsTabPkrssModel);
        binding.setTtsTabPkrssHandler(ttsTabPkrssHandler);
        View rootView = binding.getRoot();

        PkrssTTSAdapter pkrssTTSLocaleAdapter = new PkrssTTSAdapter(getContext());
        ttsTabPkrssModel.setSpinnerLocalAdapter(pkrssTTSLocaleAdapter);
        PkrssTTSWorker.initTTSListEngine(pkrssTTSLocaleAdapter);

        return rootView;
    }
}
