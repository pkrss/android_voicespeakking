package com.pkrss.voicespeakking.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.pkrss.voicespeakking.db.model.RemoteCacheData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "REMOTE_CACHE_DATA".
*/
public class RemoteCacheDataDao extends AbstractDao<RemoteCacheData, Long> {

    public static final String TABLENAME = "REMOTE_CACHE_DATA";

    /**
     * Properties of entity RemoteCacheData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Url = new Property(1, String.class, "url", false, "URL");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property CreateTime = new Property(3, java.util.Date.class, "createTime", false, "CREATE_TIME");
    };

    private DaoSession daoSession;


    public RemoteCacheDataDao(DaoConfig config) {
        super(config);
    }
    
    public RemoteCacheDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"REMOTE_CACHE_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"URL\" TEXT," + // 1: url
                "\"CONTENT\" TEXT," + // 2: content
                "\"CREATE_TIME\" INTEGER);"); // 3: createTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"REMOTE_CACHE_DATA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RemoteCacheData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(2, url);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        java.util.Date createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindLong(4, createTime.getTime());
        }
    }

    @Override
    protected void attachEntity(RemoteCacheData entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public RemoteCacheData readEntity(Cursor cursor, int offset) {
        RemoteCacheData entity = new RemoteCacheData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // url
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // content
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)) // createTime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, RemoteCacheData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCreateTime(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(RemoteCacheData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(RemoteCacheData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}