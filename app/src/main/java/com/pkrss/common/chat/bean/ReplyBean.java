package com.pkrss.common.chat.bean;

import com.pkrss.common.chat.common.ReplyConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liand on 2015/11/20.
 */
public final class ReplyBean {
    /**
     * 话题id
     */
    private String topicId;

    /**
     * 回复id
     */
    private String replyId;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 回复类型
     * 如在反馈中可为设备（作者）、用户两种类型
     */
    private String replyType;

    /**
     * 回复内容类型
     */
    private String contentType;

    /**
     * 如果回复肉类是音频，则为音频时间
     */
    private float audioDuration;

    /**
     * 创建时间
     * 时间 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(ReplyBean.createdTime));
     */
    private long createdTime;

    /**
     * 回复状态
     */
    private ReplyConstants.UserReplyStatus userReplystatus;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public float getAudioDuration() {
        return audioDuration;
    }

    public void setAudioDuration(float audioDuration) {
        this.audioDuration = audioDuration;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public ReplyConstants.UserReplyStatus getUserReplystatus() {
        return userReplystatus;
    }

    public void setUserReplystatus(ReplyConstants.UserReplyStatus userReplystatus) {
        this.userReplystatus = userReplystatus;
    }

    public String getCreateTimeString(){
        try {
            return SimpleDateFormat.getDateTimeInstance().format(new Date(this.createdTime));
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
