package com.pkrss.voicespeakking.handler;

import com.pkrss.voicespeakking.db.dao.RemoteCacheDataDao;
import com.pkrss.voicespeakking.db.dao.SpeakItemDao;
import com.pkrss.voicespeakking.db.model.SpeakItem;
import com.pkrss.voicespeakking.db.util.DbCore;

import java.util.Date;
import java.util.List;

//
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.PopupMenu;
//
//import com.pkrss.voicespeakking.R;
//import com.pkrss.voicespeakking.model.MainModel;
//
public final class ContentHandler {
//    private MainModel mainModel;

    public static void checkContentAndSaveToDb(String content){
        if(content == null || content.length()==0)
            return;

        SpeakItemDao speakItemDao = DbCore.getDaoSession().getSpeakItemDao();
        SpeakItem speakItem = speakItemDao.queryBuilder().where(RemoteCacheDataDao.Properties.Content.eq(content)).unique();
        Date now = new Date();
        if(speakItem==null){
            speakItem = new SpeakItem();
            speakItem.setLastPos(0);
            speakItem.setBrief(content.length()>10 ? content.substring(0,10) : content);
            speakItem.setContent(content);
            speakItem.setCreateTime(now);
            speakItem.setUpdateTime(now);
            speakItemDao.insert(speakItem);
        }else{
            speakItem.setUpdateTime(now);
            speakItemDao.update(speakItem);
        }
    }

//
//    public ContentHandler(MainModel mainModel){
//        this.mainModel = mainModel;
//    }
//
//    public void clickCancelBtn(View view){
//        this.mainModel.getViewPager().setCurrentItem(0,true);
//    }
//    public void clickSaveBtn(View view){
//        this.mainModel.getViewPager().setCurrentItem(0,true);
//    }
//
//    public View.OnLongClickListener onLongClickListenerEdit = new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View v) {
//            PopupMenu popup = new PopupMenu(v.getContext(), v);
//            MenuInflater inflater = popup.getMenuInflater();
//            inflater.inflate(R.menu.menu_main_textview, popup.getMenu());
//            popup.show();
//
//            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//                @Override
//                public boolean onMenuItemClick(MenuItem arg0) {
//                    switch (arg0.getItemId()) {
//                        case R.id.edit:
//                            mainModel.getViewPager().setCurrentItem(1, true);
//                            return true;
//                        default:
//                            return false;
//                    }
//                }
//            });
//
//            return false;
//        }
//    };
}
