package com.pkrss.voicespeakking.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.pkrss.voicespeakking.BR;
import com.pkrss.voicespeakking.handler.ContentHandler;

/**
 * Created by liand on 2016/2/11.
 */
public final class ContentModel extends BaseObservable {
    private String content = "This is sample, you can replace this text in left panel,and click edit menu.";

    private ContentModel(){

    }

    private static ContentModel contentModel;
    public static ContentModel getInstance(){
        if(contentModel == null)
            contentModel = new ContentModel();
        return contentModel;
    }

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

        ContentHandler.checkContentAndSaveToDb(content);

        notifyPropertyChanged(BR.content);
    }
}
