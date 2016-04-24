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

    private int curPage = 0;

    public static final int pageSize = 20;

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public ViewAdapter.ISetHosterListener setSwipeRefreshLayoutHosterListener = new ViewAdapter.ISetHosterListener(){
        @Override
        public void setHoster(View view) {
            swipeRefreshLayout = (SwipeRefreshLayout) view;
        }
    };

    public ListView getListViewCtl() {
        return listViewCtl;
    }

    public ViewAdapter.ISetHosterListener setListViewHosterListener = new ViewAdapter.ISetHosterListener(){
        @Override
        public void setHoster(View view) {
            listViewCtl = (ListView) view;
            if(listViewCtl == null)
                return;

            if(ttsHistoryAdapter == null){
                ttsHistoryAdapter = new TTSHistoryAdapter(view.getContext());
            }
            listViewCtl.setAdapter(ttsHistoryAdapter);
        }
    };

    public View getLoadMoreCtl() {
        return loadMoreCtl;
    }

    public ViewAdapter.ISetHosterListener setLoadMoreCtlHosterListener = new ViewAdapter.ISetHosterListener(){
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
        ttsHistoryAdapter.addAll(items);

        // notify
        // ...
//        if(ttsHistoryAdapter!=null)
//            ttsHistoryAdapter.notifyDataSetChanged();
    }
}
