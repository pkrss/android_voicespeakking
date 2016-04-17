package com.pkrss.voicespeakking.bean;

import android.view.View;

/**
 * Created by liand on 2016/3/23.
 */
public final class RadioItemBean {
    private int id;
    private String title;
    private View.OnClickListener onClickListener;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
