package com.pkrss.voicespeakking.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class RemoteCacheDataEntity {

    @Id
    private Long id;
    private String url;
    private String content;
    private Date createTime;

    @Generated(hash = 1447011476)
    public RemoteCacheDataEntity(Long id, String url, String content,
            Date createTime) {
        this.id = id;
        this.url = url;
        this.content = content;
        this.createTime = createTime;
    }

    @Generated(hash = 1585167620)
    public RemoteCacheDataEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
