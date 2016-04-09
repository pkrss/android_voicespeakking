package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.support.v4.view.ViewPager;

/**
 * Created by liand on 2016/2/11.
 */
public final class MainModel extends BaseObservable {

    public PlayerBarModel getPlayerBarModel() {
        return PlayerBarModel.getInstance();
    }
    private ContentModel contentModel;

    public ContentModel getContentModel() {
        if(contentModel == null)
            contentModel = new ContentModel();
        return contentModel;
    }
}
