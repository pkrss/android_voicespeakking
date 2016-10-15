package com.pkrss.voicespeakking.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SpeakItemEntity {

    @Id
    private Long id;
    private Integer lastPos;
    private String brief;
    private Date createTime;
    private Date updateTime;

    @Generated(hash = 693198881)
    public SpeakItemEntity(Long id, Integer lastPos, String brief, Date createTime,
            Date updateTime) {
        this.id = id;
        this.lastPos = lastPos;
        this.brief = brief;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 1487409277)
    public SpeakItemEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLastPos() {
        return lastPos;
    }

    public void setLastPos(Integer lastPos) {
        this.lastPos = lastPos;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
