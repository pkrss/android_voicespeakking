package com.pkrss.voicespeakking.adapter;

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
public final class TTSHistoryAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;

    private List<SpeakItem> speakItemList;

    public TTSHistoryAdapter(Context context, List<SpeakItem> speakItemList) {
        mInflater = LayoutInflater.from(context);
        this.speakItemList = speakItemList;
    }

    @Override
    public int getCount(){
        return speakItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return speakItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return speakItemList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView text;
        SpeakItem speakItem = null;

        if (convertView == null) {
            ChildTtshistoryItemBinding binding = DataBindingUtil.inflate(mInflater, R.layout.child_ttshistory_item, parent, false);

            speakItem = speakItemList.get(position);
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
