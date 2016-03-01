package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.pkrss.voicespeakking.BR;

/**
 * Created by liand on 2016/2/11.
 */
public final class ContentModel extends BaseObservable {
    private String content = "菜单布局，main_menu.xml，三种菜单都是用这个菜单布局填充的";

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(content == null)
            content = "";
        if(this.content.equals(content))
            return;
        this.content = content;
        notifyPropertyChanged(BR.content);
    }
}
