package com.wondersgroup.commerce.model;

/**
 * Created by 1229 on 2015/11/20.
 */
public class Memo {
    private String user_id ;
    private String memo_content;
    private String memo_time;//时间记录到分钟，用于显示
    private String memo_time0;//时间记录到秒，用于删除
    private String remind_time;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMemo_content() {
        return memo_content;
    }

    public void setMemo_content(String memo_content) {
        this.memo_content = memo_content;
    }

    public String getMemo_time() {
        return memo_time;
    }

    public void setMemo_time(String memo_time) {
        this.memo_time = memo_time;
    }

    public String getMemo_time0() {
        return memo_time0;
    }

    public void setMemo_time0(String memo_time0) {
        this.memo_time0 = memo_time0;
    }

    public String getRemind_time() {
        return remind_time;
    }

    public void setRemind_time(String remind_time) {
        this.remind_time = remind_time;
    }


}
