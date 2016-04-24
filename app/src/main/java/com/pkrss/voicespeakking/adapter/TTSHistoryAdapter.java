package com.pkrss.voicespeakking.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.databinding.ChildTtshistoryItemBinding;
import com.pkrss.voicespeakking.db.model.SpeakItem;
import com.pkrss.voicespeakking.handler.TTSHistoryItemHandler;
import com.pkrss.voicespeakking.model.TTSHistoryItemModel;

/**
 * Created by liand on 2016/2/13.
 */
public final class TTSHistoryAdapter extends ArrayAdapter<SpeakItem> {

    private int mResource;

    private final LayoutInflater mInflater;

    public TTSHistoryAdapter(Context context) {
        this(context, android.R.layout.simple_spinner_item);
    }

    public TTSHistoryAdapter(Context context, int resource) {
        super(context, resource);
        mResource = resource;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView text;
        SpeakItem speakItem = null;

        if (convertView == null) {
            ChildTtshistoryItemBinding binding = DataBindingUtil.inflate(mInflater, R.layout.child_ttshistory_item, parent, false);

            speakItem = getItem(position);
            TTSHistoryItemModel ttsHistoryItemModel = new TTSHistoryItemModel(speakItem);
            TTSHistoryItemHandler ttsHistoryItemHandler = new TTSHistoryItemHandler(ttsHistoryItemModel);
            binding.setTtsHistoryItemModel(ttsHistoryItemModel);
            binding.setTtsHistoryItemHandler(ttsHistoryItemHandler);

            view = binding.getRoot();
            view.setTag(speakItem);
        } else {
            view = convertView;
//            speakItem = (SpeakItem) view.getTag();
        }

        return view;
    }
}
