package com.pkrss.common.chat.callback;

import com.pkrss.common.chat.bean.ReplyBean;

import java.util.List;

public interface SyncListener {
    void onReceiveDevReply(List<ReplyBean> var1);

    void onSendUserReply(List<ReplyBean> var1);
}