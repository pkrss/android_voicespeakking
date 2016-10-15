package com.pkrss.voicespeakking.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SpeakItemContentEntity {

    @Id
    private Long id;
    private String content;

    @Generated(hash = 1617456884)
    public SpeakItemContentEntity(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Generated(hash = 1944189793)
    public SpeakItemContentEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
