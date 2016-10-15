package com.pkrss.voicespeakking.handler;

import com.pkrss.voicespeakking.common.TTSConstants;
import com.pkrss.voicespeakking.db.dao.SpeakItemContentEntityDao;
import com.pkrss.voicespeakking.db.dao.SpeakItemEntityDao;
import com.pkrss.voicespeakking.db.entity.SpeakItemContentEntity;
import com.pkrss.voicespeakking.db.entity.SpeakItemEntity;
import com.pkrss.voicespeakking.db.util.DbCore;

import java.util.Date;

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

        SpeakItemEntityDao speakItemDao = DbCore.getDaoSession().getSpeakItemEntityDao();
        SpeakItemContentEntity speakItemContent = DbCore.getDaoSession().getSpeakItemContentEntityDao().queryBuilder().where(SpeakItemContentEntityDao.Properties.Content.eq(content)).unique();
        Date now = new Date();
        if(speakItemContent==null){
            SpeakItemEntity speakItem = new SpeakItemEntity();
            speakItem.setLastPos(0);
            speakItem.setBrief(content.length()>TTSConstants.HISTORY_BIREF_LENGTH ? content.substring(0, TTSConstants.HISTORY_BIREF_LENGTH) : content);

            speakItem.setCreateTime(now);
            speakItem.setUpdateTime(now);


            speakItemContent = new SpeakItemContentEntity();
            speakItemContent.setId(speakItemDao.insert(speakItem));

            if(speakItemContent.getId()>0) {
                speakItemContent.setContent(content);
                DbCore.getDaoSession().getSpeakItemContentEntityDao().insert(speakItemContent);
            }
        }else{
            SpeakItemEntity speakItem = DbCore.getDaoSession().getSpeakItemEntityDao().load(speakItemContent.getId());
            if(speakItem!=null) {
                speakItem.setUpdateTime(now);
                speakItemDao.update(speakItem);
            }
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
