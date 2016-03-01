package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.support.v4.view.ViewPager;

/**
 * Created by liand on 2016/2/11.
 */
public final class MainModel extends BaseObservable {

    private ViewPager viewPager;

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    private PlayerBarModel playerBarModel;

    public PlayerBarModel getPlayerBarModel() {
        if(playerBarModel == null)
            playerBarModel = new PlayerBarModel();
        return playerBarModel;
    }
    private ContentModel contentModel;

    public ContentModel getContentModel() {
        if(contentModel == null)
            contentModel = new ContentModel();
        return contentModel;
    }
}
