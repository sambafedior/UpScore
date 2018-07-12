package com.sambafedior.upscore.model;

public class Tag {

    private String Tag;
    private int type;

    public Tag() {
    }

    public Tag(String tag, int type) {
        Tag = tag;
        this.type = type;
    }

    public Tag(String tag) {
        Tag = tag;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
