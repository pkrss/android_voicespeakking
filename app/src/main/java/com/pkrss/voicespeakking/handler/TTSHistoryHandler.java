package com.pkrss.voicespeakking.handler;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

public final class TTSHistoryHandler {

    public SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener(){

        @Override
        public void onRefresh() {
//            tv.setText("正在刷新");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    tv.setText("刷新完成");
//                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 6000);
        }
    };
}
