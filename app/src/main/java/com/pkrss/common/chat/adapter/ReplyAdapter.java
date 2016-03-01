package com.pkrss.common.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pkrss.common.chat.bean.ReplyBean;
import com.pkrss.common.chat.callback.ICloseWinCallback;
import com.pkrss.common.chat.common.ReplyConstants;
import com.pkrss.voicespeakking.R;

import java.util.List;

/**
 * Created by liand on 2015/11/20.
 */
public class ReplyAdapter extends BaseAdapter {
    private Context context;

    /**
     * 数据列表
     */
    private List<ReplyBean> dataList;

    /**
     * 聊天类别 系统
     */
    private String devReplyType;

    private ICloseWinCallback closeWinCallback;

    /**
     * 构造函数
     * @param context 上下文
     * @param dataList 数据列表
     * @param devReplyType 聊天类别 系统
     */
    public ReplyAdapter(Context context, List<ReplyBean> dataList,String devReplyType){
        super();

        this.context = context;
        this.dataList = dataList;
        this.devReplyType = devReplyType;
    }

    public ICloseWinCallback getCloseWinCallback() {
        return closeWinCallback;
    }

    public void setCloseWinCallback(ICloseWinCallback closeWinCallback) {
        this.closeWinCallback = closeWinCallback;
    }

    private ReplyBean _getItem(int pos){
        return this.dataList.get(pos);
    }

    @Override
    public int getCount() {
        if(this.dataList == null)
            return 0;
        return this.dataList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return _getItem(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        // 两种不同的Tiem布局
        return ReplyConstants.VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        // 获取单条回复
        ReplyBean reply = _getItem(position);
        if (this.devReplyType.equals(reply.getReplyType())) {
            // 开发者回复Item布局
            return ReplyConstants.VIEW_TYPE_DEV;
        } else {
            // 用户反馈、回复Item布局
            return ReplyConstants.VIEW_TYPE_USER;
        }
    }

//    View.OnClickListener textIOnClickListener = new View.OnClickListener(){
//
//        @Override
//        public void onClick(View v) {
//            TextView tv = (TextView)v;
//            String s = tv.getText().toString();
//            int pos = s.indexOf("http:");
//            if(pos>=0) {
//
//                if(closeWinCallback!=null)
//                    closeWinCallback.close();
//
//                s = s.substring(pos);
//                EventHelper.Instance().notify(EventConstants.WebViewUrl.Change,s);
//            }
//        }
//    };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        // 获取单条回复
        ReplyBean reply = _getItem(position);
        // 开发者的回复 或 用户的反馈、回复
        boolean isDevReply = this.devReplyType.equals(reply.getReplyType());
        if (convertView == null) {
            // 根据Type的类型来加载不同的Item布局
            convertView = LayoutInflater.from(this.context).inflate(isDevReply ? R.layout.chat_custom_dev_reply : R.layout.chat_custom_user_reply, null);

            // 创建ViewHolder并获取各种View
            holder = new ViewHolder();
            holder.replyContent = (TextView) convertView
                    .findViewById(R.id.fb_reply_content);
//            holder.replyContent.setOnClickListener(textIOnClickListener);
            holder.replyProgressBar = (ProgressBar) convertView
                    .findViewById(R.id.fb_reply_progressBar);
            holder.replyStateFailed = (ImageView) convertView
                    .findViewById(R.id.fb_reply_state_failed);
            holder.replyData = (TextView) convertView
                    .findViewById(R.id.fb_reply_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 以下是填充数据
        // 设置Reply的内容
        holder.replyContent.setText(reply.getReplyContent());
        // 在App应用界面，对于开发者的Reply来讲status没有意义
        if (!isDevReply) {
            // 根据Reply的状态来设置replyStateFailed的状态
            if (ReplyConstants.UserReplyStatus.NotSent == reply.getUserReplystatus()) {
                holder.replyStateFailed.setVisibility(View.VISIBLE);
            } else {
                holder.replyStateFailed.setVisibility(View.GONE);
            }

            // 根据Reply的状态来设置replyProgressBar的状态
            if (ReplyConstants.UserReplyStatus.Sending == reply.getUserReplystatus()) {
                holder.replyProgressBar.setVisibility(View.VISIBLE);
            } else {
                holder.replyProgressBar.setVisibility(View.GONE);
            }
        }

        // 回复的时间数据，这里仿照QQ两条Reply之间相差100000ms则展示时间
        if ((position + 1) < this.getCount()) {
            ReplyBean nextReply = this._getItem(position + 1);
//            if (nextReply.getCreatedTime() - reply.getCreatedTime() > 100000) {
                holder.replyData.setText(reply.getCreateTimeString());
                holder.replyData.setVisibility(View.VISIBLE);
//            }
        }
        return convertView;
    }

    private static final class ViewHolder {
        public TextView replyContent;
        public ProgressBar replyProgressBar;
        public ImageView replyStateFailed;
        public TextView replyData;
    }
}
