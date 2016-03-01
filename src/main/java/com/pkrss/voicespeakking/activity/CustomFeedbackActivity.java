package com.pkrss.voicespeakking.activity;

import android.content.Context;
import android.os.Bundle;

import com.pkrss.common.base.BaseActivity;
import com.pkrss.common.chat.bean.ReplyBean;
import com.pkrss.common.chat.callback.ReplyListener;
import com.pkrss.common.chat.common.ReplyConstants;
import com.pkrss.common.chat.view.ReplyView;
import com.pkrss.voicespeakking.R;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.SyncListener;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Reply;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liand on 2015/11/7.
 */
public final class CustomFeedbackActivity extends BaseActivity {

    private FeedbackAgent mAgent;
    private Conversation mComversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReplyListener replyListener = new ReplyListener(){

            @Override
            public void syncCallback(final com.pkrss.common.chat.callback.SyncListener listener) {
                mComversation.sync(new SyncListener(){

                    @Override
                    public void onReceiveDevReply(List<Reply> list) {
                        if(listener!=null)
                            listener.onReceiveDevReply(toReplyBeanList(mComversation.getReplyList()));
                    }

                    @Override
                    public void onSendUserReply(List<Reply> list) {
                        if(listener!=null)
                            listener.onSendUserReply(toReplyBeanList(mComversation.getReplyList()));
                    }
                });
            }

            @Override
            public void initData() {
                mAgent = new FeedbackAgent(CustomFeedbackActivity.this);
                mComversation = mAgent.getDefaultConversation();
            }

            @Override
            public String getDevReplyType() {
                return Reply.TYPE_DEV_REPLY;
            }

            @Override
            public boolean addUserMessage(Context context,String content) {
                mComversation.addUserReply(content);
                return true;
            }

            /**
             * 是否支持下拉刷新
             * @return
             */
            @Override
            public boolean supportSwipeFresh(){
                return true;
            }
        };

        ReplyView view = new ReplyView();
        setContentView(view.createView(this, replyListener, R.string.feedback));
    }


    private static List<ReplyBean> toReplyBeanList(List<Reply> list){
        if(list == null)
            return null;
        List<ReplyBean> ret = new ArrayList<ReplyBean>();
        for(Reply r : list){
            ReplyBean bean = new ReplyBean();
            bean.setReplyId(r.reply_id);
            bean.setTopicId(r.feedback_id);
            bean.setReplyContent(r.content);
            bean.setReplyType(r.type);
            bean.setContentType(r.content_type);
            bean.setAudioDuration(r.audio_duration);
            bean.setCreatedTime(r.created_at);

            if(Reply.STATUS_NOT_SENT.equals(r.status))
                bean.setUserReplystatus(ReplyConstants.UserReplyStatus.NotSent);
            else if(Reply.STATUS_SENDING.equals(r.status))
                bean.setUserReplystatus(ReplyConstants.UserReplyStatus.Sending);
            else if(Reply.STATUS_SENT.equals(r.status))
                bean.setUserReplystatus(ReplyConstants.UserReplyStatus.Sent);
            else if(Reply.STATUS_WILL_SENT.equals(r.status))
                bean.setUserReplystatus(ReplyConstants.UserReplyStatus.WillSend);

            ret.add(bean);
        }

        return ret;
    }
}
