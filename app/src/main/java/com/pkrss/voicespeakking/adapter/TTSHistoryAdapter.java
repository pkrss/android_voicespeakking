package com.pkrss.voicespeakking.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.ChildTtshistoryItemBinding;
import com.pkrss.voicespeakking.db.model.SpeakItem;
import com.pkrss.voicespeakking.handler.TTSHistoryItemHandler;
import com.pkrss.voicespeakking.model.TTSHistoryItemModel;

import java.util.List;

/**
 * Created by liand on 2016/2/13.
 */
public final class TTSHistoryAdapter extends ArrayAdapter<SpeakItem> {

    private final LayoutInflater mInflater;
    private Activity activity;

    public TTSHistoryAdapter(Activity activity) {
        super(activity,  R.layout.child_ttshistory_item, 0);
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            ChildTtshistoryItemBinding binding = DataBindingUtil.inflate(mInflater, R.layout.child_ttshistory_item, parent, false);

            SpeakItem speakItem = getItem(position);
            TTSHistoryItemModel ttsHistoryItemModel = new TTSHistoryItemModel(activity, speakItem);
            TTSHistoryItemHandler ttsHistoryItemHandler = new TTSHistoryItemHandler(ttsHistoryItemModel);
            binding.setTtsHistoryItemModel(ttsHistoryItemModel);
            binding.setTtsHistoryItemHandler(ttsHistoryItemHandler);

            return binding.getRoot();
        }

        return convertView;
    }
}
