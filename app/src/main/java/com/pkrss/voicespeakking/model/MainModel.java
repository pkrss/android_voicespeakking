package com.pkrss.voicespeakking.model;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.support.v4.view.ViewPager;

/**
 * Created by liand on 2016/2/11.
 */
public final class MainModel extends BaseObservable {
    private Activity activity;

    public PlayerBarModel getPlayerBarModel() {
        return PlayerBarModel.getInstance();
    }

    public ContentModel getContentModel() {
        return ContentModel.getInstance();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
