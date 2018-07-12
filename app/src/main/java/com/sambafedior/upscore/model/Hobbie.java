package com.sambafedior.upscore.model;

import java.util.List;

public class Hobbie {

    public static final int SECTION_TYPE = 0;
    public static final int TAG_TYPE = 1;

    private String section;
    private int type;
    private String tag;

    public Hobbie() {
    }


    public Hobbie(String section, int type, String tag) {
        this.section = section;
        this.type = type;
        this.tag = tag;
    }

    public Hobbie(String section, int type) {
        this.section = section;
        this.type = type;
    }

    public Hobbie(int type, String tag) {
        this.type = type;
        this.tag = tag;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
