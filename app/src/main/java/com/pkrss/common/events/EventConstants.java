package com.pkrss.common.events;

/**
 * Created by liand on 2015/11/22.
 */
public final class EventConstants {

    /**
     * activity event
     */
    public static final class MainActivity{
        /**
         * param: Activity
         */
        public static final String Create = "mainactivity_create";
        /**
         * param: Activity
         */
        public static final String Destroy = "mainactivity_destroy";
        /**
         * param: Activity
         */
        public static final String Pause = "mainactivity_pause";
        /**
         * param: Activity
         */
        public static final String Resume = "mainactivity_resume";
        /**
         * param: Activity
         */
        public static final String Start = "mainactivity_start";
        /**
         * param: Activity
         */
        public static final String Stop = "mainactivity_stop";
        public static final String onBackPressed = "mainactivity_onBackPressed";
    }

    /**
     * activity event
     */
    public static final class Activity{
        /**
         * param: Activity
         */
        public static final String Create = "activity_create";
        /**
         * param: Activity
         */
        public static final String Destroy = "activity_destroy";
        /**
         * param: Activity
         */
        public static final String Pause = "activity_pause";
        /**
         * param: Activity
         */
        public static final String Resume = "activity_resume";
        public static final String onBackPressed = "activity_onBackPressed";
    }

    /**
     * activity event
     * param is page fragment
     */
    public static final class Fragment{
//        public static final String Create = "fragment_create";
//        public static final String Destroy = "fragment_destroy";
        public static final String Pause = "fragment_pause";
        public static final String Resume = "fragment_resume";
    }

    /**
     * Stat Analytics
     */
    public static final class StatAnalytics{
        public static final String log_exception = "log_exception";
        public static final String log_url = "log_url";
        public static final String log_click = "log_click";
        public static final String log_log = "log_log";
    }


    /**
     * push event names
     * called by Dmk
     */
    public static final class Push{
//        public static final String Init = "push_init";
        public static final String Start = "push_start";
        public static final String Destroy = "push_destroy";
        public static final String Pause = "push_pause";
        public static final String Resume = "push_resume";
        public static final String sendToAll = "push_sendToAll";
    }

    public static final class Dmk{
        public static final String AddLocalText = "dmk_addlocaltext";
        public static final String createUI = "dmk_createUI";
//        public static final String Destroy = "dmk_destroy";
//        public static final String Pause = "dmk_pause";
//        public static final String Resume = "dmk_resume";
//        public static final String onBackPressed = "dmk_onBackPressed";
        public static final String openChatWindow = "dmk_openChatWindow";
        public static final String sendToAll = "dmk_sendToAll";
    }



    public static final class Feedback{
        /**
         * param: Activity context
         */
        public static final String openWindow = "feedback_openWindow";
    }
}
