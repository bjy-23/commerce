package com.wondersgroup.commerce.model.ad;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 9/21/17.
 */

public class AdView {
    private int code;
    private String message;
    private AdBuentApp adBuentApp;
    private AdBntappLeader adBntappLeader;
    private List<AdBntappMediaList> adBntappMediaList;
    private List<AdBntappPsn> adBntappPsnList_01;
    private List<AdBntappPsn> adBntappPsnList_02;
    private List<AdBntappPsn> adBntappPsnList_03;
    private List<StuffList> stuffList;

    public List<AdBntappMediaList> getAdBntappMediaList() {
        return adBntappMediaList;
    }

    public void setAdBntappMediaList(List<AdBntappMediaList> adBntappMediaList) {
        this.adBntappMediaList = adBntappMediaList;
    }

    public List<AdBntappPsn> getAdBntappPsnList_01() {
        return adBntappPsnList_01;
    }

    public void setAdBntappPsnList_01(List<AdBntappPsn> adBntappPsnList_01) {
        this.adBntappPsnList_01 = adBntappPsnList_01;
    }

    public List<AdBntappPsn> getAdBntappPsnList_02() {
        return adBntappPsnList_02;
    }

    public void setAdBntappPsnList_02(List<AdBntappPsn> adBntappPsnList_02) {
        this.adBntappPsnList_02 = adBntappPsnList_02;
    }

    public List<AdBntappPsn> getAdBntappPsnList_03() {
        return adBntappPsnList_03;
    }

    public void setAdBntappPsnList_03(List<AdBntappPsn> adBntappPsnList_03) {
        this.adBntappPsnList_03 = adBntappPsnList_03;
    }

    public AdBuentApp getAdBuentApp() {
        return adBuentApp;
    }

    public void setAdBuentApp(AdBuentApp adBuentApp) {
        this.adBuentApp = adBuentApp;
    }

    public AdBntappLeader getAdBntappLeader() {
        return adBntappLeader;
    }

    public void setAdBntappLeader(AdBntappLeader adBntappLeader) {
        this.adBntappLeader = adBntappLeader;
    }


    public List<StuffList> getStuffList() {
        return stuffList;
    }

    public void setStuffList(List<StuffList> stuffList) {
        this.stuffList = stuffList;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 广告发布单位基本信息
     */
    public static class AdBuentApp {
        private String buentName;//单位名称
        private String bunetChar;//单位性质（1、企业法人3、事业法人）
        private String uniScid;//组织机构代码
        private String buentType;//单位类型
        private String adOpeOrg;//主管单位
        private String organId;//登记机关
        private String opLoc;//住所（经营场所）
        private String linkMan;//联系人
        private String tel;//电话
        private String moblie;//移动电话
        private String email;//Email
        private String adAppPerson;//申请联系人（可能多个）
        private String adAppContact;//申请联系电话（同上）
        private String adAppType;//申请方式（{'1':'到登记场所直接申请','9':'其他'}）

        public String getBuentName() {
            return buentName;
        }

        public void setBuentName(String buentName) {
            this.buentName = buentName;
        }

        public String getBunetChar() {
            return bunetChar;
        }

        public void setBunetChar(String bunetChar) {
            this.bunetChar = bunetChar;
        }

        public String getUniScid() {
            return uniScid;
        }

        public void setUniScid(String uniScid) {
            this.uniScid = uniScid;
        }

        public String getBuentType() {
            return buentType;
        }

        public void setBuentType(String buentType) {
            this.buentType = buentType;
        }

        public String getAdOpeOrg() {
            return adOpeOrg;
        }

        public void setAdOpeOrg(String adOpeOrg) {
            this.adOpeOrg = adOpeOrg;
        }

        public String getOrganId() {
            return organId;
        }

        public void setOrganId(String organId) {
            this.organId = organId;
        }

        public String getOpLoc() {
            return opLoc;
        }

        public void setOpLoc(String opLoc) {
            this.opLoc = opLoc;
        }

        public String getLinkMan() {
            return linkMan;
        }

        public void setLinkMan(String linkMan) {
            this.linkMan = linkMan;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMoblie() {
            return moblie;
        }

        public void setMoblie(String moblie) {
            this.moblie = moblie;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAdAppPerson() {
            return adAppPerson;
        }

        public void setAdAppPerson(String adAppPerson) {
            this.adAppPerson = adAppPerson;
        }

        public String getAdAppContact() {
            return adAppContact;
        }

        public void setAdAppContact(String adAppContact) {
            this.adAppContact = adAppContact;
        }

        public String getAdAppType() {
            return adAppType;
        }

        public void setAdAppType(String adAppType) {
            this.adAppType = adAppType;
        }
    }


    /**
     * 广告许可登记申请法定代表人信息
     */
    public static class AdBntappLeader {
        private String leaderName;//姓名
        private String sex;//性别
        private String cerType;//证件类型 1	中华人民共和国居民身份证2	中华人民共和国军官证3	中华人民共和国警官证4	外国（地区）护照 9	其他有效身份证件
        private String cerNo;//证件号码
        private String tel;//联系电话
        private String houseAdd;//住址

        public String getLeaderName() {
            return leaderName;
        }

        public void setLeaderName(String leaderName) {
            this.leaderName = leaderName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getCerType() {
            return cerType;
        }

        public void setCerType(String cerType) {
            this.cerType = cerType;
        }

        public String getCerNo() {
            return cerNo;
        }

        public void setCerNo(String cerNo) {
            this.cerNo = cerNo;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getHouseAdd() {
            return houseAdd;
        }

        public void setHouseAdd(String houseAdd) {
            this.houseAdd = houseAdd;
        }
    }

    /**
     * 广告发布媒介信息
     */
    public static class AdBntappMediaList {
        private String meidaName;//媒介名称

        public String getMeidaName() {
            return meidaName;
        }

        public void setMeidaName(String meidaName) {
            this.meidaName = meidaName;
        }

        public String getValFrom() {
            return valFrom;
        }

        public void setValFrom(String valFrom) {
            this.valFrom = valFrom;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        private String valFrom;//有效期
        private String remark;//备注
    }

    /**
     * 广告机构负责人
     */
    public static class AdBntappPsnList_01 {

    }

    /**
     * 广告从业人员
     */
    public static class AdBntappPsn {
        private String aname;
        private String telephone;
        private String adTrainCertNo;
        private String cerType;
        private String cerNo;
        private String psnType;//人员类型

        public String getPsnType() {
            return psnType;
        }

        public void setPsnType(String psnType) {
            this.psnType = psnType;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getAdTrainCertNo() {
            return adTrainCertNo;
        }

        public void setAdTrainCertNo(String adTrainCertNo) {
            this.adTrainCertNo = adTrainCertNo;
        }

        public String getCerType() {
            return cerType;
        }

        public void setCerType(String cerType) {
            this.cerType = cerType;
        }

        public String getCerNo() {
            return cerNo;
        }

        public void setCerNo(String cerNo) {
            this.cerNo = cerNo;
        }
    }

    /**
     * 广告审查人员
     */
    public static class AdBntappPsnList_03 {

    }

    /**
     * 材料信息
     */
    public static class StuffList {
        private String stuffName;//文件名称
        private String pages;//页数
        private String commited;//是否提交（1、提交2、未提交）
        private String commitDate;//提交时间
        private String remark;//备注
        private String filePath;//文件路径

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        private String fileName;//文件名称

        public String getStuffName() {
            return stuffName;
        }

        public void setStuffName(String stuffName) {
            this.stuffName = stuffName;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public String getCommited() {
            return commited;
        }

        public void setCommited(String commited) {
            this.commited = commited;
        }

        public String getCommitDate() {
            return commitDate;
        }

        public void setCommitDate(String commitDate) {
            this.commitDate = commitDate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

}
