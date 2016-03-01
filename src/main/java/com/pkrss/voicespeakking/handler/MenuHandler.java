package com.pkrss.voicespeakking.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.pkrss.voicespeakking.activity.CustomFeedbackActivity;

/**
 * Created by liand on 2016/2/10.
 */
public final class MenuHandler {
    public static void clickFeedback(View view){
        Context context = view.getContext();
        Intent intent = new Intent();
        intent.setClass(context, CustomFeedbackActivity.class);
        context.startActivity(intent);
    }
}
