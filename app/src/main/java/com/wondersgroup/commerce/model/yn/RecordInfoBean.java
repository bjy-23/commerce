package com.wondersgroup.commerce.model.yn;

import java.util.List;

/**
 * Created by kangrenhui on 2016/3/18.
 */
public class RecordInfoBean {

    public class Deal {

    }

    public class EtpsDic {
        private String etpsName;

        private String leader;

        private String regNo;

        private String address;

        private String establishCptl;

        private String etpsTypeGb;

        private String establishDate;

        private String approveDate;

        private String industryCode;

        private String telephone;

        private String investorName;

        private String regOrganId;

        private String areaOrganId;

        private String specialIndustryCode;

        private String specialAreaCode;

        private String existStatus;

        private String superviseType;

        private String focusType;

        private String trdScope;

        public void setEtpsName(String etpsName){
            this.etpsName = etpsName;
        }
        public String getEtpsName(){
            return this.etpsName;
        }
        public void setLeader(String leader){
            this.leader = leader;
        }
        public String getLeader(){
            return this.leader;
        }
        public void setRegNo(String regNo){
            this.regNo = regNo;
        }
        public String getRegNo(){
            return this.regNo;
        }
        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return this.address;
        }
        public void setEstablishCptl(String establishCptl){
            this.establishCptl = establishCptl;
        }
        public String getEstablishCptl(){
            return this.establishCptl;
        }
        public void setEtpsTypeGb(String etpsTypeGb){
            this.etpsTypeGb = etpsTypeGb;
        }
        public String getEtpsTypeGb(){
            return this.etpsTypeGb;
        }
        public void setEstablishDate(String establishDate){
            this.establishDate = establishDate;
        }
        public String getEstablishDate(){
            return this.establishDate;
        }
        public void setApproveDate(String approveDate){
            this.approveDate = approveDate;
        }
        public String getApproveDate(){
            return this.approveDate;
        }
        public void setIndustryCode(String industryCode){
            this.industryCode = industryCode;
        }
        public String getIndustryCode(){
            return this.industryCode;
        }
        public void setTelephone(String telephone){
            this.telephone = telephone;
        }
        public String getTelephone(){
            return this.telephone;
        }
        public void setInvestorName(String investorName){
            this.investorName = investorName;
        }
        public String getInvestorName(){
            return this.investorName;
        }
        public void setRegOrganId(String regOrganId){
            this.regOrganId = regOrganId;
        }
        public String getRegOrganId(){
            return this.regOrganId;
        }
        public void setAreaOrganId(String areaOrganId){
            this.areaOrganId = areaOrganId;
        }
        public String getAreaOrganId(){
            return this.areaOrganId;
        }
        public void setSpecialIndustryCode(String specialIndustryCode){
            this.specialIndustryCode = specialIndustryCode;
        }
        public String getSpecialIndustryCode(){
            return this.specialIndustryCode;
        }
        public void setSpecialAreaCode(String specialAreaCode){
            this.specialAreaCode = specialAreaCode;
        }
        public String getSpecialAreaCode(){
            return this.specialAreaCode;
        }
        public void setExistStatus(String existStatus){
            this.existStatus = existStatus;
        }
        public String getExistStatus(){
            return this.existStatus;
        }
        public void setSuperviseType(String superviseType){
            this.superviseType = superviseType;
        }
        public String getSuperviseType(){
            return this.superviseType;
        }
        public void setFocusType(String focusType){
            this.focusType = focusType;
        }
        public String getFocusType(){
            return this.focusType;
        }
        public void setTrdScope(String trdScope){
            this.trdScope = trdScope;
        }
        public String getTrdScope(){
            return this.trdScope;
        }

    }

    public class EtpsInfoVo {
        private String address;

        private String approveDate;

        private String areaOrganId;

        private String establishDate;

        private String etpsName;

        private String etpsTypeGb;

        private String existStatus;

        private String industryCode;

        private String leader;

        private String regNo;

        private String regOrganId;

        private String telephone;

        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return this.address;
        }
        public void setApproveDate(String approveDate){
            this.approveDate = approveDate;
        }
        public String getApproveDate(){
            return this.approveDate;
        }
        public void setAreaOrganId(String areaOrganId){
            this.areaOrganId = areaOrganId;
        }
        public String getAreaOrganId(){
            return this.areaOrganId;
        }
        public void setEstablishDate(String establishDate){
            this.establishDate = establishDate;
        }
        public String getEstablishDate(){
            return this.establishDate;
        }
        public void setEtpsName(String etpsName){
            this.etpsName = etpsName;
        }
        public String getEtpsName(){
            return this.etpsName;
        }
        public void setEtpsTypeGb(String etpsTypeGb){
            this.etpsTypeGb = etpsTypeGb;
        }
        public String getEtpsTypeGb(){
            return this.etpsTypeGb;
        }
        public void setExistStatus(String existStatus){
            this.existStatus = existStatus;
        }
        public String getExistStatus(){
            return this.existStatus;
        }
        public void setIndustryCode(String industryCode){
            this.industryCode = industryCode;
        }
        public String getIndustryCode(){
            return this.industryCode;
        }
        public void setLeader(String leader){
            this.leader = leader;
        }
        public String getLeader(){
            return this.leader;
        }
        public void setRegNo(String regNo){
            this.regNo = regNo;
        }
        public String getRegNo(){
            return this.regNo;
        }
        public void setRegOrganId(String regOrganId){
            this.regOrganId = regOrganId;
        }
        public String getRegOrganId(){
            return this.regOrganId;
        }
        public void setTelephone(String telephone){
            this.telephone = telephone;
        }
        public String getTelephone(){
            return this.telephone;
        }

    }

    public class CheckMatters {
        private String checkType;

        private String matterId;

        private String matterName;

        public void setCheckType(String checkType){
            this.checkType = checkType;
        }
        public String getCheckType(){
            return this.checkType;
        }
        public void setMatterId(String matterId){
            this.matterId = matterId;
        }
        public String getMatterId(){
            return this.matterId;
        }
        public void setMatterName(String matterName){
            this.matterName = matterName;
        }
        public String getMatterName(){
            return this.matterName;
        }

    }

    public class UserList {
        private String lastLoginSuccessDate;

        private String loginName;

        private String name;

        private String organCode;

        private String organCode2;

        private String password;

        private String removed;

        private String userId;

        private String userType;

        public void setLastLoginSuccessDate(String lastLoginSuccessDate){
            this.lastLoginSuccessDate = lastLoginSuccessDate;
        }
        public String getLastLoginSuccessDate(){
            return this.lastLoginSuccessDate;
        }
        public void setLoginName(String loginName){
            this.loginName = loginName;
        }
        public String getLoginName(){
            return this.loginName;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setOrganCode(String organCode){
            this.organCode = organCode;
        }
        public String getOrganCode(){
            return this.organCode;
        }
        public void setOrganCode2(String organCode2){
            this.organCode2 = organCode2;
        }
        public String getOrganCode2(){
            return this.organCode2;
        }
        public void setPassword(String password){
            this.password = password;
        }
        public String getPassword(){
            return this.password;
        }
        public void setRemoved(String removed){
            this.removed = removed;
        }
        public String getRemoved(){
            return this.removed;
        }
        public void setUserId(String userId){
            this.userId = userId;
        }
        public String getUserId(){
            return this.userId;
        }
        public void setUserType(String userType){
            this.userType = userType;
        }
        public String getUserType(){
            return this.userType;
        }

    }


    public class Result {
        private List<CheckMatters> checkMatters ;

        private String checkedName;

        private String checkedNumber;

        private Deal deal;

        private EtpsDic etpsDic;

        private String etpsId;

        private EtpsInfoVo etpsInfoVo;

        private List<UserList> userList ;

        public void setCheckMatters(List<CheckMatters> checkMatters){
            this.checkMatters = checkMatters;
        }
        public List<CheckMatters> getCheckMatters(){
            return this.checkMatters;
        }
        public void setCheckedName(String checkedName){
            this.checkedName = checkedName;
        }
        public String getCheckedName(){
            return this.checkedName;
        }
        public void setCheckedNumber(String checkedNumber){
            this.checkedNumber = checkedNumber;
        }
        public String getCheckedNumber(){
            return this.checkedNumber;
        }
        public void setDeal(Deal deal){
            this.deal = deal;
        }
        public Deal getDeal(){
            return this.deal;
        }
        public void setEtpsDic(EtpsDic etpsDic){
            this.etpsDic = etpsDic;
        }
        public EtpsDic getEtpsDic(){
            return this.etpsDic;
        }
        public void setEtpsId(String etpsId){
            this.etpsId = etpsId;
        }
        public String getEtpsId(){
            return this.etpsId;
        }
        public void setEtpsInfoVo(EtpsInfoVo etpsInfoVo){
            this.etpsInfoVo = etpsInfoVo;
        }
        public EtpsInfoVo getEtpsInfoVo(){
            return this.etpsInfoVo;
        }
        public void setUserList(List<UserList> userList){
            this.userList = userList;
        }
        public List<UserList> getUserList(){
            return this.userList;
        }

    }


        private int code;

        private String message;

        private Result result;

        public void setCode(int code){
            this.code = code;
        }
        public int getCode(){
            return this.code;
        }
        public void setMessage(String message){
            this.message = message;
        }
        public String getMessage(){
            return this.message;
        }
        public void setResult(Result result){
            this.result = result;
        }
        public Result getResult(){
            return this.result;
        }


}
