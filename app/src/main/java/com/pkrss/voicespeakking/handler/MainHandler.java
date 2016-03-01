package com.pkrss.voicespeakking.handler;

import com.pkrss.voicespeakking.model.MainModel;

/**
 * Created by liand on 2016/2/11.
 */
public final class MainHandler {
    private MainModel mainModel;
    public MainHandler(MainModel mainModel){
        this.mainModel = mainModel;
    }

    private PlayerBarHandler playerBarHandler;

    public PlayerBarHandler getPlayerBarHandler() {
        if(playerBarHandler==null)
            playerBarHandler = new PlayerBarHandler(mainModel);
        return playerBarHandler;
    }
    private ContentHandler contentHandler;

    public ContentHandler getContentHandler() {
        if(contentHandler==null)
            contentHandler = new ContentHandler(mainModel);
        return contentHandler;
    }
}
