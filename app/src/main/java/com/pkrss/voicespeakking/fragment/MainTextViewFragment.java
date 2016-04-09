package com.pkrss.voicespeakking.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.FragmentMainTextviewBinding;
import com.pkrss.voicespeakking.handler.MainHandler;
import com.pkrss.voicespeakking.model.MainModel;

/**
 * Created by liand on 2016/2/13.
 */
public final class MainTextViewFragment extends Fragment {

    private MainModel mainModel;
    private MainHandler mainHandler;

    public MainModel getMainModel() {
        return mainModel;
    }

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public MainHandler getMainHandler() {
        return mainHandler;
    }

    public void setMainHandler(MainHandler mainHandler) {
        this.mainHandler = mainHandler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentMainTextviewBinding binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_main_textview, container, false);
        binding.setMainModel(mainModel);
        binding.setMainHandler(mainHandler);
        View rootView = binding.getRoot();

        return rootView;
    }
}
