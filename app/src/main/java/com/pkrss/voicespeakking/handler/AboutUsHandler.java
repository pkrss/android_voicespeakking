package com.pkrss.voicespeakking.handler;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.pkrss.common.helper.UtilsHelper;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.activity.AboutUsActivity;
import com.pkrss.voicespeakking.activity.CustomFeedbackActivity;

/**
 * Created by liand on 2016/2/11.
 */
public final class AboutUsHandler {

    public void clickEmailMe(View view){
        UtilsHelper.sendEmail(view.getResources().getString(R.string.title_author_email), null,null,true,null,null,null);
    }

    public void clickDonate(View view){
        UtilsHelper.openUrl(view.getContext(), view.getResources().getString(R.string.url_author_donate));
    }

    public void clickRateApp(View view){
        UtilsHelper.openUrl(view.getContext(), "market://details?id=" + view.getContext().getPackageName());
    }

    public void clickFeedback(View view){
        view.getContext().startActivity(new Intent(view.getContext(), CustomFeedbackActivity.class));
    }

    public void clickOpensource(View view){
        UtilsHelper.openUrl(view.getContext(), view.getResources().getString(R.string.url_author_donate));
    }
}
