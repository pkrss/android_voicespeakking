package com.pkrss.voicespeakking.db.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.pkrss.voicespeakking.db.model.SpeakItem;
import com.pkrss.voicespeakking.db.model.SpeakItemContent;
import com.pkrss.voicespeakking.db.model.RemoteCacheData;

import com.pkrss.voicespeakking.db.dao.SpeakItemDao;
import com.pkrss.voicespeakking.db.dao.SpeakItemContentDao;
import com.pkrss.voicespeakking.db.dao.RemoteCacheDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig speakItemDaoConfig;
    private final DaoConfig speakItemContentDaoConfig;
    private final DaoConfig remoteCacheDataDaoConfig;

    private final SpeakItemDao speakItemDao;
    private final SpeakItemContentDao speakItemContentDao;
    private final RemoteCacheDataDao remoteCacheDataDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        speakItemDaoConfig = daoConfigMap.get(SpeakItemDao.class).clone();
        speakItemDaoConfig.initIdentityScope(type);

        speakItemContentDaoConfig = daoConfigMap.get(SpeakItemContentDao.class).clone();
        speakItemContentDaoConfig.initIdentityScope(type);

        remoteCacheDataDaoConfig = daoConfigMap.get(RemoteCacheDataDao.class).clone();
        remoteCacheDataDaoConfig.initIdentityScope(type);

        speakItemDao = new SpeakItemDao(speakItemDaoConfig, this);
        speakItemContentDao = new SpeakItemContentDao(speakItemContentDaoConfig, this);
        remoteCacheDataDao = new RemoteCacheDataDao(remoteCacheDataDaoConfig, this);

        registerDao(SpeakItem.class, speakItemDao);
        registerDao(SpeakItemContent.class, speakItemContentDao);
        registerDao(RemoteCacheData.class, remoteCacheDataDao);
    }
    
    public void clear() {
        speakItemDaoConfig.getIdentityScope().clear();
        speakItemContentDaoConfig.getIdentityScope().clear();
        remoteCacheDataDaoConfig.getIdentityScope().clear();
    }

    public SpeakItemDao getSpeakItemDao() {
        return speakItemDao;
    }

    public SpeakItemContentDao getSpeakItemContentDao() {
        return speakItemContentDao;
    }

    public RemoteCacheDataDao getRemoteCacheDataDao() {
        return remoteCacheDataDao;
    }

}
