package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.pkrss.voicespeakking.adapter.TTSHistoryAdapter;
import com.pkrss.voicespeakking.databinding.ViewAdapter;
import com.pkrss.voicespeakking.db.model.SpeakItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liand on 2016/2/11.
 */
public final class TTSHistoryModel extends BaseObservable {

    private SwipeRefreshLayout swipeRefreshLayout;

    private View loadMoreCtl;

    private ListView listViewCtl;

//    private List<SpeakItem> allSpeakItem = new ArrayList<SpeakItem>();
    private TTSHistoryAdapter ttsHistoryAdapter;
    private List<SpeakItem> speakItemList;

    private int curPage = 0;

    public static final int pageSize = 20;

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public ViewAdapter.ISetHosterListener swipeRefreshLayoutHosterListener = new ViewAdapter.ISetHosterListener(){
        @Override
        public void setHoster(View view) {
            swipeRefreshLayout = (SwipeRefreshLayout) view;
        }
    };

    public ListView getListViewCtl() {
        return listViewCtl;
    }

    public ViewAdapter.ISetHosterListener listViewHosterListener = new ViewAdapter.ISetHosterListener(){
        @Override
        public void setHoster(View view) {
            listViewCtl = (ListView) view;
            if(listViewCtl == null)
                return;

            if(ttsHistoryAdapter == null){
                speakItemList = new ArrayList<SpeakItem>();
                ttsHistoryAdapter = new TTSHistoryAdapter(view.getContext(), speakItemList);
            }
            listViewCtl.setAdapter(ttsHistoryAdapter);
        }
    };

    public View getLoadMoreCtl() {
        return loadMoreCtl;
    }

    public ViewAdapter.ISetHosterListener loadMoreCtlHosterListener = new ViewAdapter.ISetHosterListener(){
        @Override
        public void setHoster(View view) {
            loadMoreCtl = view;
        }
    };

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void addAll(List<SpeakItem> items){
        if(items==null || items.size()==0)
            return;
        speakItemList.addAll(items);
        ttsHistoryAdapter.notifyDataSetChanged();
        // notify
        // ...
//        if(ttsHistoryAdapter!=null)
//            ttsHistoryAdapter.notifyDataSetChanged();
    }
}
