package com.pkrss.common.chat.common;

/**
 * Created by liand on 2015/11/20.
 */
public final class ReplyConstants {

    /**
     * 聊天消息最长大度
     */
    public static final int CONTENT_MAX_LENGTH = 256;

    /**
     * 聊天类型有几种
     */
    public static final int VIEW_TYPE_COUNT = 2;

    /**
     * 聊天类型 第1种为用户
     */
    public static final int VIEW_TYPE_USER = 0;

    /**
     * 聊天类型 第2种为系统
     */
    public static final int VIEW_TYPE_DEV = 1;

    /**
     * 用户聊天状态
     */
    public enum UserReplyStatus {
        NotSent, WillSend, Sending, Sent
    }
}
