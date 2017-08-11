package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by yclli on 2016/3/5.
 */
public class LcGraphItem {

    private List<String> titleList;
    private List<String> contentList;

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }
}
