package com.wondersgroup.commerce.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2016/2/2.
 */
public class CaseInvestigateDetail {

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

        String addr;
        String age;
        String appDate;
        String appPerson;
        String assort;
        String caseCedistrict;      //所在区划
        String caseName;            //案件名称
        String caseReason;
        String caseSpot;            //案发地点
        String caseTime;            //案发时间
        String cerNo;
        String cerType;
        String clueType;            //案件来源
        List<DataVolume> dataVoList = new ArrayList<>();//主要当事人信息
        String depOpi;
        String exaDate;
        String exaPer;
        String house;
        String lerep;
        List<LitigtVolume> litigtList = new ArrayList<LitigtVolume>();//其他当事人列表
        String name;
        String occupation;
        String organOpi;
        String postalcode;
        String pripId;
        String regNo;
        String sex;
        String tel;
        String toexaDate;
        String userIdToexa;
        String workunit;
        List<LitigtVolume> simLitigtList;       //当事人基本情况
        List<ExtendPerson> extendPersonVoList;          //承办人情况
        String caseType;    //主要案件类型
        String secondaryCaseType;   //次要案件类型
        String topicName;   //专项工作
        String verchdepopi;     //法制机构审核意见
        String verifyUserName;  //法治审核人员
        String verchdate;   //法治审核日期
        String undertakeName;//承办人

        public String getUndertakeName() {
            return undertakeName;
        }

        public void setUndertakeName(String undertakeName) {
            this.undertakeName = undertakeName;
        }

        public String getCaseType() {
            return caseType;
        }

        public void setCaseType(String caseType) {
            this.caseType = caseType;
        }

        public String getSecondaryCaseType() {
            return secondaryCaseType;
        }

        public void setSecondaryCaseType(String secondaryCaseType) {
            this.secondaryCaseType = secondaryCaseType;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getVerchdepopi() {
            return verchdepopi;
        }

        public void setVerchdepopi(String verchdepopi) {
            this.verchdepopi = verchdepopi;
        }

        public String getVerifyUserName() {
            return verifyUserName;
        }

        public void setVerifyUserName(String verifyUserName) {
            this.verifyUserName = verifyUserName;
        }

        public String getVerchdate() {
            return verchdate;
        }

        public void setVerchdate(String verchdate) {
            this.verchdate = verchdate;
        }

        public List<LitigtVolume> getSimLitigtList() {
            return simLitigtList;
        }

        public void setSimLitigtList(List<LitigtVolume> simLitigtList) {
            this.simLitigtList = simLitigtList;
        }

        public List<ExtendPerson> getExtendPersonVoList() {
            return extendPersonVoList;
        }

        public void setExtendPersonVoList(List<ExtendPerson> extendPersonVoList) {
            this.extendPersonVoList = extendPersonVoList;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAppDate() {
            return appDate;
        }

        public void setAppDate(String appDate) {
            this.appDate = appDate;
        }

        public String getAppPerson() {
            return appPerson;
        }

        public void setAppPerson(String appPerson) {
            this.appPerson = appPerson;
        }


        public String getAssort() {
            return assort;
        }

        public void setAssort(String assort) {
            this.assort = assort;
        }

        public String getCaseCedistrict() {
            return caseCedistrict;
        }

        public void setCaseCedistrict(String caseCedistrict) {
            this.caseCedistrict = caseCedistrict;
        }

        public String getCaseName() {
            return caseName;
        }

        public void setCaseName(String caseName) {
            this.caseName = caseName;
        }

        public String getCaseReason() {
            return caseReason;
        }

        public void setCaseReason(String caseReason) {
            this.caseReason = caseReason;
        }

        public String getCaseSpot() {
            return caseSpot;
        }

        public void setCaseSpot(String caseSpot) {
            this.caseSpot = caseSpot;
        }

        public String getCaseTime() {
            return caseTime;
        }

        public void setCaseTime(String caseTime) {
            this.caseTime = caseTime;
        }

        public String getCerNo() {
            return cerNo;
        }

        public void setCerNo(String cerNo) {
            this.cerNo = cerNo;
        }

        public String getCerType() {
            return cerType;
        }

        public void setCerType(String cerType) {
            this.cerType = cerType;
        }

        public String getClueType() {
            return clueType;
        }

        public void setClueType(String clueType) {
            this.clueType = clueType;
        }

        public List<DataVolume> getDataVoList() {
            return dataVoList;
        }

        public void setDataVoList(List<DataVolume> dataVoList) {
            this.dataVoList = dataVoList;
        }

        public String getDepOpi() {
            return depOpi;
        }

        public void setDepOpi(String depOpi) {
            this.depOpi = depOpi;
        }

        public String getExaDate() {
            return exaDate;
        }

        public void setExaDate(String exaDate) {
            this.exaDate = exaDate;
        }

        public String getExaPer() {
            return exaPer;
        }

        public void setExaPer(String exaPer) {
            this.exaPer = exaPer;
        }

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
        }

        public String getLerep() {
            return lerep;
        }

        public void setLerep(String lerep) {
            this.lerep = lerep;
        }

        public List<LitigtVolume> getLitigtList() {
            return litigtList;
        }

        public void setLitigtList(List<LitigtVolume> litigtList) {
            this.litigtList = litigtList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getOrganOpi() {
            return organOpi;
        }

        public void setOrganOpi(String organOpi) {
            this.organOpi = organOpi;
        }

        public String getPostalcode() {
            return postalcode;
        }

        public void setPostalcode(String postalcode) {
            this.postalcode = postalcode;
        }

        public String getPripId() {
            return pripId;
        }

        public void setPripId(String pripId) {
            this.pripId = pripId;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getToexaDate() {
            return toexaDate;
        }

        public void setToexaDate(String toexaDate) {
            this.toexaDate = toexaDate;
        }

        public String getUserIdToexa() {
            return userIdToexa;
        }

        public void setUserIdToexa(String userIdToexa) {
            this.userIdToexa = userIdToexa;
        }

        public String getWorkunit() {
            return workunit;
        }

        public void setWorkunit(String workunit) {
            this.workunit = workunit;
        }

        public class LitigtVolume{
            String assort;          //类型编码
            String assortName;      //类型名
            String litigtName;      //当事人名称
            String litigantId;      //当事人id
            String isPrime;         //是否主要当事人

            public String getAssort() {
                return assort;
            }

            public void setAssort(String assort) {
                this.assort = assort;
            }

            public String getAssortName() {
                return assortName;
            }

            public void setAssortName(String assortName) {
                this.assortName = assortName;
            }

            public String getLitigtName() {
                return litigtName;
            }

            public void setLitigtName(String litigtName) {
                this.litigtName = litigtName;
            }

            public String getLitigantId() {
                return litigantId;
            }

            public void setLitigantId(String litigantId) {
                this.litigantId = litigantId;
            }

            public String getIsPrime() {
                return isPrime;
            }

            public void setIsPrime(String isPrime) {
                this.isPrime = isPrime;
            }
        }

        public class ExtendPerson{
            @SerializedName("undertakeDeptName")
            String deptName;

            @SerializedName("undertakeUserName")
            String userName;

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }

}
