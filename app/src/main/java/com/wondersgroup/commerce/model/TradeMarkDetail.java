package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by admin on 2016/2/1.
 */
public class TradeMarkDetail {

    private Basic tmBasicInfo;   //	商标基本信息,//商标申请人信息
    private Entity tmRegister;   //	商标主体信息
    private Agent agent;   //	商标代理人信息
    private Priority tmPriorityInfo;   //	商标优先权信息
    private Other tmOther;   //	商标其他信息
    private Pledge tmPledgeList;   //	商标质押信息
    private List<ProductService> tmProductList;//	商标商品服务信息
    private List<Commmer> commonerList;//	商标共有人信息
    private List<RegInternational> tmRegInternationalInfoList;//	国际注册信息
    private List<BrandPermit> permitList;   //	商标许可信息
    private List<ContactPerson> contactPersonList;//	商标联络人信息


    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<Commmer> getCommonerList() {
        return commonerList;
    }

    public void setCommonerList(List<Commmer> commonerList) {
        this.commonerList = commonerList;
    }

    public List<ContactPerson> getContactPersonList() {
        return contactPersonList;
    }

    public void setContactPersonList(List<ContactPerson> contactPersonList) {
        this.contactPersonList = contactPersonList;
    }

    public List<BrandPermit> getPermitList() {
        return permitList;
    }

    public void setPermitList(List<BrandPermit> permitList) {
        this.permitList = permitList;
    }

    public Basic getTmBasicInfo() {
        return tmBasicInfo;
    }

    public void setTmBasicInfo(Basic tmBasicInfo) {
        this.tmBasicInfo = tmBasicInfo;
    }

    public Other getTmOther() {
        return tmOther;
    }

    public void setTmOther(Other tmOther) {
        this.tmOther = tmOther;
    }

    public Pledge getTmPledgeList() {
        return tmPledgeList;
    }

    public void setTmPledgeList(Pledge tmPledgeList) {
        this.tmPledgeList = tmPledgeList;
    }

    public Priority getTmPriorityInfo() {
        return tmPriorityInfo;
    }

    public void setTmPriorityInfo(Priority tmPriorityInfo) {
        this.tmPriorityInfo = tmPriorityInfo;
    }

    public List<ProductService> getTmProductList() {
        return tmProductList;
    }

    public void setTmProductList(List<ProductService> tmProductList) {
        this.tmProductList = tmProductList;
    }
    public List<RegInternational> getTmRegInternationalInfoList() {
        return tmRegInternationalInfoList;
    }

    public void setTmRegInternationalInfoList(List<RegInternational> tmRegInternationalInfoList) {
        this.tmRegInternationalInfoList = tmRegInternationalInfoList;
    }

    public Entity getTmRegister() {
        return tmRegister;
    }

    public void setTmRegister(Entity tmRegister) {
        this.tmRegister = tmRegister;
    }

    //商标基本信息
    public class Basic{
        private String brandName;   //	商标名称
        private String brandNameTrans;   //	商标名称意译
        private String regNo;   //	商标注册证号
        private String tmType;   //	商标类型
        private String classId;   //	商标类别
        private String formType;   //	商标形式类型
        private String isCommon;   //	是否共有商标
        private String solidRemark;   //	是否立体商标
        private String geographyRemark;   //	是否地理标志商标
        private String existStatus;   //	商标状态
        private String famousId;   //	是否知名著名商标
        private String reputedRemark;   //	是否驰名商标
//        待定	商标形式类型
        private String firstTrailIssue;   //	初审公告期号
        private String firstIssueDate;   //	初审公告日期
        private String regIssue;   //	注册公告期号
        private String regAppDate;   //	注册公告日期
        private String appDate;   //	申请日期
        private String specialStartDate;   //
        private String specialEndDate;   //	专用权有效期
        private String Color;   //（颜色组合商标/非颜色组合商标）判断改值是否为0	指定颜色说明
        private String colorNum;   //	组合颜色数量
        private String protect;   //	放弃专用权说明
        private String memo;   //	备注
        private String imageFilePath;   //	商标图片附件id
        private String enProposer;   //	外文名称
        private String enProposerAddr;   //	外文地址

        public String getEnProposer() {
            return enProposer;
        }

        public void setEnProposer(String enProposer) {
            this.enProposer = enProposer;
        }

        public String getEnProposerAddr() {
            return enProposerAddr;
        }

        public void setEnProposerAddr(String enProposerAddr) {
            this.enProposerAddr = enProposerAddr;
        }

        public String getAppDate() {
            return appDate;
        }

        public void setAppDate(String appDate) {
            this.appDate = appDate;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandNameTrans() {
            return brandNameTrans;
        }

        public void setBrandNameTrans(String brandNameTrans) {
            this.brandNameTrans = brandNameTrans;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getColor() {
            return Color;
        }

        public void setColor(String color) {
            Color = color;
        }

        public String getColorNum() {
            return colorNum;
        }

        public void setColorNum(String colorNum) {
            this.colorNum = colorNum;
        }

        public String getExistStatus() {
            return existStatus;
        }

        public void setExistStatus(String existStatus) {
            this.existStatus = existStatus;
        }

        public String getFamousId() {
            return famousId;
        }

        public void setFamousId(String famousId) {
            this.famousId = famousId;
        }

        public String getFirstIssueDate() {
            return firstIssueDate;
        }

        public void setFirstIssueDate(String firstIssueDate) {
            this.firstIssueDate = firstIssueDate;
        }

        public String getFirstTrailIssue() {
            return firstTrailIssue;
        }

        public void setFirstTrailIssue(String firstTrailIssue) {
            this.firstTrailIssue = firstTrailIssue;
        }

        public String getFormType() {
            return formType;
        }

        public void setFormType(String formType) {
            this.formType = formType;
        }

        public String getGeographyRemark() {
            return geographyRemark;
        }

        public void setGeographyRemark(String geographyRemark) {
            this.geographyRemark = geographyRemark;
        }

        public String getImageFilePath() {
            return imageFilePath;
        }

        public void setImageFilePath(String imageFilePath) {
            this.imageFilePath = imageFilePath;
        }

        public String getIsCommon() {
            return isCommon;
        }

        public void setIsCommon(String isCommon) {
            this.isCommon = isCommon;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getProtect() {
            return protect;
        }

        public void setProtect(String protect) {
            this.protect = protect;
        }

        public String getRegAppDate() {
            return regAppDate;
        }

        public void setRegAppDate(String regAppDate) {
            this.regAppDate = regAppDate;
        }

        public String getRegIssue() {
            return regIssue;
        }

        public void setRegIssue(String regIssue) {
            this.regIssue = regIssue;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getReputedRemark() {
            return reputedRemark;
        }

        public void setReputedRemark(String reputedRemark) {
            this.reputedRemark = reputedRemark;
        }

        public String getSolidRemark() {
            return solidRemark;
        }

        public void setSolidRemark(String solidRemark) {
            this.solidRemark = solidRemark;
        }

        public String getSpecialEndDate() {
            return specialEndDate;
        }

        public void setSpecialEndDate(String specialEndDate) {
            this.specialEndDate = specialEndDate;
        }

        public String getSpecialStartDate() {
            return specialStartDate;
        }

        public void setSpecialStartDate(String specialStartDate) {
            this.specialStartDate = specialStartDate;
        }

        public String getTmType() {
            return tmType;
        }

        public void setTmType(String tmType) {
            this.tmType = tmType;
        }
    }

    //商标主体信息
    public class Entity{
        private String registerType;   // (01企业，02个体)	主体类型
        private String registerName;   //	企业/个体名称
        private String registerRegNo;   //	注册号
        private String registerUniScid;   //	统一社会信用代码
        private String name;   //	联系人
        private String phone;   //	联系电话
        private String address;   //	联系地址
        private String postcode;   //	邮编
        private String regOrganId;   //	登记机关
        private String existStatus;   //	企业状态

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getExistStatus() {
            return existStatus;
        }

        public void setExistStatus(String existStatus) {
            this.existStatus = existStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getRegisterName() {
            return registerName;
        }

        public void setRegisterName(String registerName) {
            this.registerName = registerName;
        }

        public String getRegisterRegNo() {
            return registerRegNo;
        }

        public void setRegisterRegNo(String registerRegNo) {
            this.registerRegNo = registerRegNo;
        }

        public String getRegisterType() {
            return registerType;
        }

        public void setRegisterType(String registerType) {
            this.registerType = registerType;
        }

        public String getRegisterUniScid() {
            return registerUniScid;
        }

        public void setRegisterUniScid(String registerUniScid) {
            this.registerUniScid = registerUniScid;
        }

        public String getRegOrganId() {
            return regOrganId;
        }

        public void setRegOrganId(String regOrganId) {
            this.regOrganId = regOrganId;
        }
    }

    //商标代理人信息
    public class Agent{
        private String chName;   //	中文名称
        private String chAddress;   //	中文地址
        private String enName;   //	外文名称
        private String enAddress;   //	外文地址
        private String contact;   //	联系人
        private String postalCode;   //	邮编
        private String addr;   //	地址
        private String phone;   //	电话
        private String mobile;   //	手机号码
        private String fax;   //	传真
        private String email;   //	E-Mail
        private String agentType;   //	代理人类型
        private String economyType;   //	经济性质
        private String cetfName;   //	证件名称
        private String cetfNo;   //	证件编号
        private String districtName;   //	行政区划
        private String state;   //	代理人状态
        private String memo;   //	备注

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAgentType() {
            return agentType;
        }

        public void setAgentType(String agentType) {
            this.agentType = agentType;
        }

        public String getCetfName() {
            return cetfName;
        }

        public void setCetfName(String cetfName) {
            this.cetfName = cetfName;
        }

        public String getCetfNo() {
            return cetfNo;
        }

        public void setCetfNo(String cetfNo) {
            this.cetfNo = cetfNo;
        }

        public String getChAddress() {
            return chAddress;
        }

        public void setChAddress(String chAddress) {
            this.chAddress = chAddress;
        }

        public String getChName() {
            return chName;
        }

        public void setChName(String chName) {
            this.chName = chName;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getEconomyType() {
            return economyType;
        }

        public void setEconomyType(String economyType) {
            this.economyType = economyType;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEnAddress() {
            return enAddress;
        }

        public void setEnAddress(String enAddress) {
            this.enAddress = enAddress;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    //商标优先权信息
    public class Priority{
        private String priorityNum;   //	优先权编号
        private String priorityType;   //	优先权种类
        private String priorityDate;   //	优先权日期
        private String priorityCtry;   //	优先权国家
        private String priorityGoods;   //	优先权商品
        private String priorityValidInfo;   //	是否有效

        public String getPriorityCtry() {
            return priorityCtry;
        }

        public void setPriorityCtry(String priorityCtry) {
            this.priorityCtry = priorityCtry;
        }

        public String getPriorityDate() {
            return priorityDate;
        }

        public void setPriorityDate(String priorityDate) {
            this.priorityDate = priorityDate;
        }

        public String getPriorityGoods() {
            return priorityGoods;
        }

        public void setPriorityGoods(String priorityGoods) {
            this.priorityGoods = priorityGoods;
        }

        public String getPriorityNum() {
            return priorityNum;
        }

        public void setPriorityNum(String priorityNum) {
            this.priorityNum = priorityNum;
        }

        public String getPriorityType() {
            return priorityType;
        }

        public void setPriorityType(String priorityType) {
            this.priorityType = priorityType;
        }

        public String getPriorityValidInfo() {
            return priorityValidInfo;
        }

        public void setPriorityValidInfo(String priorityValidInfo) {
            this.priorityValidInfo = priorityValidInfo;
        }
    }

    //商标其他信息
    public class Other{
        private String isMingpai;   //	是否名牌产品
        private String mingpaiDate;   //	获得年份
        private String isDibiao;   //	是否地标产品
        private String dibiaoDate;   //	获得年份
        private String isGaoxin;   //	是否高新技术企业
        private String gaoxinDate;   //	获得年份
        private String isZhonghua;   //	是否中华老字号
        private String zhonghuaDate;   //	获得年份
        private String isShengshi;   //	是否省市老字号
        private String shengshiDate;   //	获得年份
        private String isYuanqu;   //	是否园区企业
        private String yuanquName;   //	园区名称
        private String memo;   //	备注

        public String getDibiaoDate() {
            return dibiaoDate;
        }

        public void setDibiaoDate(String dibiaoDate) {
            this.dibiaoDate = dibiaoDate;
        }

        public String getGaoxinDate() {
            return gaoxinDate;
        }

        public void setGaoxinDate(String gaoxinDate) {
            this.gaoxinDate = gaoxinDate;
        }

        public String getIsDibiao() {
            return isDibiao;
        }

        public void setIsDibiao(String isDibiao) {
            this.isDibiao = isDibiao;
        }

        public String getIsGaoxin() {
            return isGaoxin;
        }

        public void setIsGaoxin(String isGaoxin) {
            this.isGaoxin = isGaoxin;
        }

        public String getIsMingpai() {
            return isMingpai;
        }

        public void setIsMingpai(String isMingpai) {
            this.isMingpai = isMingpai;
        }

        public String getIsShengshi() {
            return isShengshi;
        }

        public void setIsShengshi(String isShengshi) {
            this.isShengshi = isShengshi;
        }

        public String getIsYuanqu() {
            return isYuanqu;
        }

        public void setIsYuanqu(String isYuanqu) {
            this.isYuanqu = isYuanqu;
        }

        public String getIsZhonghua() {
            return isZhonghua;
        }

        public void setIsZhonghua(String isZhonghua) {
            this.isZhonghua = isZhonghua;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getMingpaiDate() {
            return mingpaiDate;
        }

        public void setMingpaiDate(String mingpaiDate) {
            this.mingpaiDate = mingpaiDate;
        }

        public String getShengshiDate() {
            return shengshiDate;
        }

        public void setShengshiDate(String shengshiDate) {
            this.shengshiDate = shengshiDate;
        }

        public String getYuanquName() {
            return yuanquName;
        }

        public void setYuanquName(String yuanquName) {
            this.yuanquName = yuanquName;
        }

        public String getZhonghuaDate() {
            return zhonghuaDate;
        }

        public void setZhonghuaDate(String zhonghuaDate) {
            this.zhonghuaDate = zhonghuaDate;
        }
    }

    //商标质押信息
    public class Pledge{
        private String pledgor;   //	出质人
        private String pledgee;   //	质权人
        private String pledgeReason;   //	质押原因
        private String pledgePeriod;   //	质押期限
        private String pledgeNoticeDate;   //	质押公告日期

        public String getPledgee() {
            return pledgee;
        }

        public void setPledgee(String pledgee) {
            this.pledgee = pledgee;
        }

        public String getPledgeNoticeDate() {
            return pledgeNoticeDate;
        }

        public void setPledgeNoticeDate(String pledgeNoticeDate) {
            this.pledgeNoticeDate = pledgeNoticeDate;
        }

        public String getPledgePeriod() {
            return pledgePeriod;
        }

        public void setPledgePeriod(String pledgePeriod) {
            this.pledgePeriod = pledgePeriod;
        }

        public String getPledgeReason() {
            return pledgeReason;
        }

        public void setPledgeReason(String pledgeReason) {
            this.pledgeReason = pledgeReason;
        }

        public String getPledgor() {
            return pledgor;
        }

        public void setPledgor(String pledgor) {
            this.pledgor = pledgor;
        }
    }

    //商标商品服务信息
    public class ProductService{
        private String key;     //	类似群
        private String value;   //	商品

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    //商标共有人信息
    public class Commmer{
        private String commonerName;   //	中文名称
        private String commonerEnName;   //	外文名称
        private String commonerAddr;   //	中文地址
        private String commonerEnAddr;   //	外文地址

        public String getCommonerAddr() {
            return commonerAddr;
        }
        public void setCommonerAddr(String commonerAddr) {
            this.commonerAddr = commonerAddr;
        }
        public String getCommonerEnAddr() {
            return commonerEnAddr;
        }
        public void setCommonerEnAddr(String commonerEnAddr) {
            this.commonerEnAddr = commonerEnAddr;
        }
        public String getCommonerEnName() {
            return commonerEnName;
        }
        public void setCommonerEnName(String commonerEnName) {
            this.commonerEnName = commonerEnName;
        }
        public String getCommonerName() {
            return commonerName;
        }
        public void setCommonerName(String commonerName) {
            this.commonerName = commonerName;
        }
    }

    //国际注册信息
    public class RegInternational{
        private String brandName;   //	国际注册名称	均为String
        private String internationalRegNo;   //	国际注册号	均为String
        private String classId;   //	国际分类号	均为String
        private String regType;   //	注册途径	均为String
        private String regCountryCode;   //	注册国家	均为String
        private String regDate;   //	注册日期	均为String

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getInternationalRegNo() {
            return internationalRegNo;
        }

        public void setInternationalRegNo(String internationalRegNo) {
            this.internationalRegNo = internationalRegNo;
        }

        public String getRegCountryCode() {
            return regCountryCode;
        }

        public void setRegCountryCode(String regCountryCode) {
            this.regCountryCode = regCountryCode;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        public String getRegType() {
            return regType;
        }

        public void setRegType(String regType) {
            this.regType = regType;
        }
    }

    //商标许可信息
    public class BrandPermit{
        private String license;   //	被许可人
        private String permitType;   //	许可类型
        private String permitProduct;   //	许可使用商品
        private String startDate;   //	许可期限(起)
        private String endDate;   //	许可期限(止)

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getPermitProduct() {
            return permitProduct;
        }

        public void setPermitProduct(String permitProduct) {
            this.permitProduct = permitProduct;
        }

        public String getPermitType() {
            return permitType;
        }

        public void setPermitType(String permitType) {
            this.permitType = permitType;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }

    //商标联络人信息
    public class ContactPerson{
        private String contactName;   //	联络人名称
        private String contactTel;   //	联络人电话
        private String contactMobile;   //	联络人手机
        private String contactAddress;   //	联络人地址

        public String getContactAddress() {
            return contactAddress;
        }

        public void setContactAddress(String contactAddress) {
            this.contactAddress = contactAddress;
        }

        public String getContactMobile() {
            return contactMobile;
        }

        public void setContactMobile(String contactMobile) {
            this.contactMobile = contactMobile;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }
    }
}
