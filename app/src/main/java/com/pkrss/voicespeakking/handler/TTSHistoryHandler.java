package com.pkrss.voicespeakking.handler;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import com.pkrss.voicespeakking.db.dao.SpeakItemDao;
import com.pkrss.voicespeakking.db.model.SpeakItem;
import com.pkrss.voicespeakking.db.util.DbCore;
import com.pkrss.voicespeakking.model.TTSHistoryModel;

import java.util.List;

public final class TTSHistoryHandler {

    private TTSHistoryModel ttsHistoryModel;
    private Handler loadMoreResponseHandler;

    public TTSHistoryHandler(TTSHistoryModel ttsHistoryModel){
        this.ttsHistoryModel = ttsHistoryModel;
        loadMoreResponseHandler = new Handler();

    }

    private Runnable _loadMoreRunable = new Runnable() {
        @Override
        public void run() {
            int pageIndex = ttsHistoryModel.getCurPage();
            ttsHistoryModel.setCurPage(pageIndex+1);

            List<SpeakItem> speakItemList = DbCore.getDaoSession().getSpeakItemDao().queryBuilder().orderDesc(SpeakItemDao.Properties.UpdateTime).limit(TTSHistoryModel.pageSize).offset(pageIndex * TTSHistoryModel.pageSize).list();
            loadMoreResponseHandler.post(new MyLoadMoreResponseRunnable(speakItemList));

        }
    };

    private final class MyLoadMoreResponseRunnable implements Runnable{

        List<SpeakItem> speakItemList;

        public MyLoadMoreResponseRunnable(List<SpeakItem> speakItemList){
            this.speakItemList = speakItemList;
        }

        @Override
        public void run() {

//            if(ttsHistoryModel.getLoadMoreCtl()!=null)
//                ttsHistoryModel.getLoadMoreCtl().setVisibility(View.GONE);

            if(speakItemList==null)
                return;

            ttsHistoryModel.addAll(speakItemList);

            // data is not enought
            if(speakItemList.size()==TTSHistoryModel.pageSize){
                if(ttsHistoryModel.getSwipeRefreshLayout()!=null)
                    ttsHistoryModel.getSwipeRefreshLayout().setRefreshing(false);
            }
        }
    };

    private void _startLoadMorePageThread(){
        new Thread(_loadMoreRunable).start();
    }

    public SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener(){

        @Override
        public void onRefresh() {
            // show loading control
//            if(ttsHistoryModel.getLoadMoreCtl()!=null)
//                ttsHistoryModel.getLoadMoreCtl().setVisibility(View.VISIBLE);

            if(ttsHistoryModel.getSwipeRefreshLayout()!=null)
                ttsHistoryModel.getSwipeRefreshLayout().setRefreshing(true);

            // start new thread to work
            _startLoadMorePageThread();
        }
    };
}
