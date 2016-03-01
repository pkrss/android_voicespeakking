package com.pkrss.voicespeakking.db.model;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "SPEAK_ITEM".
 */
public class SpeakItem {

    private Long id;
    private Integer lastPos;
    private String brief;
    private String content;
    private java.util.Date createTime;
    private java.util.Date updateTime;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public SpeakItem() {
    }

    public SpeakItem(Long id) {
        this.id = id;
    }

    public SpeakItem(Long id, Integer lastPos, String brief, String content, java.util.Date createTime, java.util.Date updateTime) {
        this.id = id;
        this.lastPos = lastPos;
        this.brief = brief;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
