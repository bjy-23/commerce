package com.wondersgroup.commerce.model.yn;

/**
 * Created by chan on 7/28/17.
 */

public class Version {
    private String code;
    private String message;

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

    public ApkInfo getApkInfo() {
        return apkInfo;
    }

    public void setApkInfo(ApkInfo apkInfo) {
        this.apkInfo = apkInfo;
    }

//    public IpaInfo getIpaInfo() {
//        return ipaInfo;
//    }
//
//    public void setIpaInfo(IpaInfo ipaInfo) {
//        this.ipaInfo = ipaInfo;
//    }

    private ApkInfo apkInfo;
//    private IpaInfo ipaInfo;

    public class ApkInfo {
        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String versionName;
        private String versionCode;
        private String url;

        public String[] getUpdateList() {
            return updateList;
        }

        public void setUpdateList(String[] updateList) {
            this.updateList = updateList;
        }

        private String[] updateList;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        private String size;
    }

    public class IpaInfo {
        private String versionName;

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String versionCode;
        private String url;
    }
}
