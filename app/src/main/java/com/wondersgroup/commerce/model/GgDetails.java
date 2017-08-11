package com.wondersgroup.commerce.model;

import java.util.ArrayList;

/**
 * Created by 1229 on 2015/12/4.
 */
public class GgDetails {
    private String code;
    private String message;
    private Result result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{
        private Detail result;
        private ArrayList<DocAttachVoList> docAttachVoList = new ArrayList<DocAttachVoList>();

        public ArrayList<DocAttachVoList> getDocAttachVoList() {
            return docAttachVoList;
        }

        public void setDocAttachVoList(ArrayList<DocAttachVoList> docAttachVoList) {
            this.docAttachVoList = docAttachVoList;
        }

        public Detail getResult() {
            return result;
        }

        public void setResult(Detail result) {
            this.result = result;
        }
    }

    public class Detail{
        private String bulletinId;
        private String content;
        private String deptname;
        private String openrange;
        private String orgname;
        private String regdate;
        private String title;

        public String getBulletinId() {
            return bulletinId;
        }

        public void setBulletinId(String bulletinId) {
            this.bulletinId = bulletinId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDeptname() {
            return deptname;
        }

        public void setDeptname(String deptname) {
            this.deptname = deptname;
        }

        public String getOpenrange() {
            return openrange;
        }

        public void setOpenrange(String openrange) {
            this.openrange = openrange;
        }

        public String getOrgname() {
            return orgname;
        }

        public void setOrgname(String orgname) {
            this.orgname = orgname;
        }

        public String getRegdate() {
            return regdate;
        }

        public void setRegdate(String regdate) {
            this.regdate = regdate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    public class DocAttachVoList {

        private String attachId;
        private String attachName;

        /**
         * @return The attachId
         */
        public String getAttachId() {
            return attachId;
        }

        /**
         * @param attachId The attachId
         */
        public void setAttachId(String attachId) {
            this.attachId = attachId;
        }

        /**
         * @return The attachName
         */
        public String getAttachName() {
            return attachName;
        }

        /**
         * @param attachName The attachName
         */
        public void setAttachName(String attachName) {
            this.attachName = attachName;
        }

    }

}
