package com.pkrss.common.chat.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.pkrss.module.StatAnalytics;
import com.pkrss.common.chat.adapter.ReplyAdapter;
import com.pkrss.common.chat.bean.ReplyBean;
import com.pkrss.common.chat.callback.ICloseWinCallback;
import com.pkrss.common.chat.callback.ReplyListener;
import com.pkrss.common.chat.callback.SyncListener;
import com.pkrss.common.chat.common.ReplyConstants;
import com.pkrss.voicespeakking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liand on 2015/11/21.
 */
public final class ReplyView {
    private ReplyListener replyListener;

//    private final static String TAG = ReplyView.class.getSimpleName();
    private ListView mListView;
    private ReplyAdapter adapter;
    private Button sendBtn;
    private EditText inputEdit;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 数据列表
     */
    protected List<ReplyBean> dataList;

    private Handler mHandler;

    private ICloseWinCallback closeWinCallback;

    public ICloseWinCallback getCloseWinCallback() {
        return closeWinCallback;
    }

    public void setCloseWinCallback(ICloseWinCallback closeWinCallback) {
        this.closeWinCallback = closeWinCallback;
        if(adapter!=null)
            adapter.setCloseWinCallback(closeWinCallback);
    }

    public View createView(Context context, ReplyListener replyListener, int titleStrId) {

        this.replyListener = replyListener;

        View parent = LayoutInflater.from(context).inflate(R.layout.chat_activity_custom, null);

        ((TextView) parent.findViewById(R.id.fb_reply_top_title)).setText(titleStrId);

        if(replyListener==null)
            return parent;

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                adapter.notifyDataSetChanged();
            }
        };

        replyListener.initData();

        initView(parent);

        if(dataList == null)
            dataList = new ArrayList<ReplyBean>();
        else
            dataList.clear();

        adapter = new ReplyAdapter(context, dataList, replyListener.getDevReplyType());
        adapter.setCloseWinCallback(closeWinCallback);
        mListView.setAdapter(adapter);
        sync();

        return parent;
    }

    private void initView(View parent) {
        mListView = (ListView) parent.findViewById(R.id.fb_reply_list);
        sendBtn = (Button) parent.findViewById(R.id.fb_send_btn);
        inputEdit = (EditText) parent.findViewById(R.id.fb_send_content);
        inputEdit.setOnKeyListener(new View.OnKeyListener(){

					@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {
						if(keyCode == KeyEvent.KEYCODE_ENTER){
							sendBtn.performClick();
						}
						return false;
					}
					
				});
				
        // 下拉刷新组件
        mSwipeRefreshLayout = (SwipeRefreshLayout) parent.findViewById(R.id.fb_reply_refresh);
        sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StatAnalytics.click("dmk send");

                String content = inputEdit.getText().toString();
                inputEdit.getEditableText().clear();
                if (!TextUtils.isEmpty(content)) {
                    if (content.length() > ReplyConstants.CONTENT_MAX_LENGTH)
                        content = content.substring(0, ReplyConstants.CONTENT_MAX_LENGTH);

                    // 将内容添加到会话列表
                    if (replyListener.addUserMessage(inputEdit.getContext(), content)) {
                        // 刷新新ListView
                        mHandler.sendMessage(new Message());
                        // 数据同步
                        sync();
                    }
                }
            }
        });

        if(this.replyListener.supportSwipeFresh()) {
            // 下拉刷新
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    sync();
                }
            });
        }else{
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    private SyncListener syncListener = new SyncListener(){

        @Override
        public void onSendUserReply(List<ReplyBean> replyList) {
            onReceiveDevReply(replyList);
        }

        @Override
        public void onReceiveDevReply(List<ReplyBean> replyList) {
            // SwipeRefreshLayout停止刷新
            mSwipeRefreshLayout.setRefreshing(false);

            notifyRefresh(replyList);
        }
    };

    // 数据同步
    private void sync() {

        replyListener.syncCallback(syncListener);

        // 更新adapter，刷新ListView
        adapter.notifyDataSetChanged();
    }

    public BaseAdapter getAdapter(){
        return adapter;
    }

    public void notifyRefresh(List<ReplyBean> replyList){
        // 如果开发者没有新的回复数据，则返回
        if (replyList == null || replyList.size() < 1) {
            return;
        }

        dataList.clear();
        for(ReplyBean reply : replyList){
            dataList.add(reply);
        }

        // 发送消息，刷新ListView
        mHandler.sendMessage(new Message());
    }
}
