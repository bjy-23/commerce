package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by 1229 on 2015/12/3.
 */
public class Ggcx {

    private Result result;

    /**
     *
     * @return
     * The result
     */
    public Result getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        private List<ggmsg> bulletinInfo;

        public List<ggmsg> getBulletinInfo() {
            return bulletinInfo;
        }

        public void setBulletinInfo(List<ggmsg> bulletinInfo) {
            this.bulletinInfo = bulletinInfo;
        }

        public class ggmsg {
            private String bulletinId;
            private String title;
            private String organName;
            private String deptname;
            private String openrange;
            private String regdate;

            public String getBulletinId() {
                return bulletinId;
            }

            public void setBulletinId(String bulletinId) {
                this.bulletinId = bulletinId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getOrganName() {
                return organName;
            }

            public void setOrganName(String organName) {
                this.organName = organName;
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

            public String getRegdate() {
                return regdate;
            }

            public void setRegdate(String regdate) {
                this.regdate = regdate;
            }
        }
    }
}
