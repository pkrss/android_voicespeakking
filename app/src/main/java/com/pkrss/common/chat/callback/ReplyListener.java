package com.pkrss.common.chat.callback;

import android.content.Context;

/**
 * Created by liand on 2015/11/21.
 */
public interface ReplyListener {

    /**
     * 聊天类别 系统
     */
    String getDevReplyType();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 添加用户聊天内容
     * @param context
     * @param content
     * @return
     */
    boolean addUserMessage(Context context, String content);

    /**
     * 同步
     * @param listener
     */
    void syncCallback(SyncListener listener);

    /**
     * 是否支持下拉刷新
     * @return
     */
    boolean supportSwipeFresh();

}
