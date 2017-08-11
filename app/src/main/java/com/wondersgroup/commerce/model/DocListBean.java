package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangrenhui on 2015/12/8.
 */
public class DocListBean {
    private List<Result1> result1 = new ArrayList<Result1>();
    private Integer currPageNo;
    private Integer pageCount;
    private Integer pageSize;
    private Integer totalRecord;
    private Condition condition;

    /**
     * @return The result1
     */
    public List<Result1> getResult1() {
        return result1;
    }

    /**
     * @param result1 The result1
     */
    public void setResult1(List<Result1> result1) {
        this.result1 = result1;
    }

    /**
     * @return The currPageNo
     */
    public Integer getCurrPageNo() {
        return currPageNo;
    }

    /**
     * @param currPageNo The currPageNo
     */
    public void setCurrPageNo(Integer currPageNo) {
        this.currPageNo = currPageNo;
    }

    /**
     * @return The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount The pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return The pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize The pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return The totalRecord
     */
    public Integer getTotalRecord() {
        return totalRecord;
    }

    /**
     * @param totalRecord The totalRecord
     */
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    /**
     * @return The condition
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * @param condition The condition
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public class Condition {

        private String addStamp;
        private Boolean archQuery;
        private String archstatus;
        private String copyfor;
        private Object createtimeEnd;
        private Object createtimeStart;
        private String deptId;
        private String deptname;
        private String docId;
        private String docclass;
        private String docclassname;
        private String docno;
        private String docnoend;
        private String docnomid;
        private String docnostart;
        private Integer docnum;
        private String doctype;
        private List<Object> doctypes = new ArrayList<Object>();
        private Object draftdateEnd;
        private Object draftdateStart;
        private String draftdept;
        private String draftdeptname;
        private String drafter;
        private String drafterid;
        private String history;
        private String isdel;
        private String isfinish;
        private String ismanual;
        private String isnorm;
        private String isopen;
        private String isurgency;
        private Object lastupdateEnd;
        private Object lastupdateStart;
        private String mainfor;
        private String opentype;
        private String orgId;
        private String orgname;
        private Integer pageNo;
        private Integer pageSize;
        private String prtnumber;
        private String regorg;
        private String remark;
        private Object request;
        private String seclevel;
        private String sectime;
        private Object signdateEnd;
        private Object signdateStart;
        private String signer;
        private String signerId;
        private String themeword;
        private String title;
        private String userId;
        private String username;
        private String yearno;

        /**
         * @return The addStamp
         */
        public String getAddStamp() {
            return addStamp;
        }

        /**
         * @param addStamp The addStamp
         */
        public void setAddStamp(String addStamp) {
            this.addStamp = addStamp;
        }

        /**
         * @return The archQuery
         */
        public Boolean getArchQuery() {
            return archQuery;
        }

        /**
         * @param archQuery The archQuery
         */
        public void setArchQuery(Boolean archQuery) {
            this.archQuery = archQuery;
        }

        /**
         * @return The archstatus
         */
        public String getArchstatus() {
            return archstatus;
        }

        /**
         * @param archstatus The archstatus
         */
        public void setArchstatus(String archstatus) {
            this.archstatus = archstatus;
        }

        /**
         * @return The copyfor
         */
        public String getCopyfor() {
            return copyfor;
        }

        /**
         * @param copyfor The copyfor
         */
        public void setCopyfor(String copyfor) {
            this.copyfor = copyfor;
        }

        /**
         * @return The createtimeEnd
         */
        public Object getCreatetimeEnd() {
            return createtimeEnd;
        }

        /**
         * @param createtimeEnd The createtimeEnd
         */
        public void setCreatetimeEnd(Object createtimeEnd) {
            this.createtimeEnd = createtimeEnd;
        }

        /**
         * @return The createtimeStart
         */
        public Object getCreatetimeStart() {
            return createtimeStart;
        }

        /**
         * @param createtimeStart The createtimeStart
         */
        public void setCreatetimeStart(Object createtimeStart) {
            this.createtimeStart = createtimeStart;
        }

        /**
         * @return The deptId
         */
        public String getDeptId() {
            return deptId;
        }

        /**
         * @param deptId The deptId
         */
        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        /**
         * @return The deptname
         */
        public String getDeptname() {
            return deptname;
        }

        /**
         * @param deptname The deptname
         */
        public void setDeptname(String deptname) {
            this.deptname = deptname;
        }

        /**
         * @return The docId
         */
        public String getDocId() {
            return docId;
        }

        /**
         * @param docId The docId
         */
        public void setDocId(String docId) {
            this.docId = docId;
        }

        /**
         * @return The docclass
         */
        public String getDocclass() {
            return docclass;
        }

        /**
         * @param docclass The docclass
         */
        public void setDocclass(String docclass) {
            this.docclass = docclass;
        }

        /**
         * @return The docclassname
         */
        public String getDocclassname() {
            return docclassname;
        }

        /**
         * @param docclassname The docclassname
         */
        public void setDocclassname(String docclassname) {
            this.docclassname = docclassname;
        }

        /**
         * @return The docno
         */
        public String getDocno() {
            return docno;
        }

        /**
         * @param docno The docno
         */
        public void setDocno(String docno) {
            this.docno = docno;
        }

        /**
         * @return The docnoend
         */
        public String getDocnoend() {
            return docnoend;
        }

        /**
         * @param docnoend The docnoend
         */
        public void setDocnoend(String docnoend) {
            this.docnoend = docnoend;
        }

        /**
         * @return The docnomid
         */
        public String getDocnomid() {
            return docnomid;
        }

        /**
         * @param docnomid The docnomid
         */
        public void setDocnomid(String docnomid) {
            this.docnomid = docnomid;
        }

        /**
         * @return The docnostart
         */
        public String getDocnostart() {
            return docnostart;
        }

        /**
         * @param docnostart The docnostart
         */
        public void setDocnostart(String docnostart) {
            this.docnostart = docnostart;
        }

        /**
         * @return The docnum
         */
        public Integer getDocnum() {
            return docnum;
        }

        /**
         * @param docnum The docnum
         */
        public void setDocnum(Integer docnum) {
            this.docnum = docnum;
        }

        /**
         * @return The doctype
         */
        public String getDoctype() {
            return doctype;
        }

        /**
         * @param doctype The doctype
         */
        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

        /**
         * @return The doctypes
         */
        public List<Object> getDoctypes() {
            return doctypes;
        }

        /**
         * @param doctypes The doctypes
         */
        public void setDoctypes(List<Object> doctypes) {
            this.doctypes = doctypes;
        }

        /**
         * @return The draftdateEnd
         */
        public Object getDraftdateEnd() {
            return draftdateEnd;
        }

        /**
         * @param draftdateEnd The draftdateEnd
         */
        public void setDraftdateEnd(Object draftdateEnd) {
            this.draftdateEnd = draftdateEnd;
        }

        /**
         * @return The draftdateStart
         */
        public Object getDraftdateStart() {
            return draftdateStart;
        }

        /**
         * @param draftdateStart The draftdateStart
         */
        public void setDraftdateStart(Object draftdateStart) {
            this.draftdateStart = draftdateStart;
        }

        /**
         * @return The draftdept
         */
        public String getDraftdept() {
            return draftdept;
        }

        /**
         * @param draftdept The draftdept
         */
        public void setDraftdept(String draftdept) {
            this.draftdept = draftdept;
        }

        /**
         * @return The draftdeptname
         */
        public String getDraftdeptname() {
            return draftdeptname;
        }

        /**
         * @param draftdeptname The draftdeptname
         */
        public void setDraftdeptname(String draftdeptname) {
            this.draftdeptname = draftdeptname;
        }

        /**
         * @return The drafter
         */
        public String getDrafter() {
            return drafter;
        }

        /**
         * @param drafter The drafter
         */
        public void setDrafter(String drafter) {
            this.drafter = drafter;
        }

        /**
         * @return The drafterid
         */
        public String getDrafterid() {
            return drafterid;
        }

        /**
         * @param drafterid The drafterid
         */
        public void setDrafterid(String drafterid) {
            this.drafterid = drafterid;
        }

        /**
         * @return The history
         */
        public String getHistory() {
            return history;
        }

        /**
         * @param history The history
         */
        public void setHistory(String history) {
            this.history = history;
        }

        /**
         * @return The isdel
         */
        public String getIsdel() {
            return isdel;
        }

        /**
         * @param isdel The isdel
         */
        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        /**
         * @return The isfinish
         */
        public String getIsfinish() {
            return isfinish;
        }

        /**
         * @param isfinish The isfinish
         */
        public void setIsfinish(String isfinish) {
            this.isfinish = isfinish;
        }

        /**
         * @return The ismanual
         */
        public String getIsmanual() {
            return ismanual;
        }

        /**
         * @param ismanual The ismanual
         */
        public void setIsmanual(String ismanual) {
            this.ismanual = ismanual;
        }

        /**
         * @return The isnorm
         */
        public String getIsnorm() {
            return isnorm;
        }

        /**
         * @param isnorm The isnorm
         */
        public void setIsnorm(String isnorm) {
            this.isnorm = isnorm;
        }

        /**
         * @return The isopen
         */
        public String getIsopen() {
            return isopen;
        }

        /**
         * @param isopen The isopen
         */
        public void setIsopen(String isopen) {
            this.isopen = isopen;
        }

        /**
         * @return The isurgency
         */
        public String getIsurgency() {
            return isurgency;
        }

        /**
         * @param isurgency The isurgency
         */
        public void setIsurgency(String isurgency) {
            this.isurgency = isurgency;
        }

        /**
         * @return The lastupdateEnd
         */
        public Object getLastupdateEnd() {
            return lastupdateEnd;
        }

        /**
         * @param lastupdateEnd The lastupdateEnd
         */
        public void setLastupdateEnd(Object lastupdateEnd) {
            this.lastupdateEnd = lastupdateEnd;
        }

        /**
         * @return The lastupdateStart
         */
        public Object getLastupdateStart() {
            return lastupdateStart;
        }

        /**
         * @param lastupdateStart The lastupdateStart
         */
        public void setLastupdateStart(Object lastupdateStart) {
            this.lastupdateStart = lastupdateStart;
        }

        /**
         * @return The mainfor
         */
        public String getMainfor() {
            return mainfor;
        }

        /**
         * @param mainfor The mainfor
         */
        public void setMainfor(String mainfor) {
            this.mainfor = mainfor;
        }

        /**
         * @return The opentype
         */
        public String getOpentype() {
            return opentype;
        }

        /**
         * @param opentype The opentype
         */
        public void setOpentype(String opentype) {
            this.opentype = opentype;
        }

        /**
         * @return The orgId
         */
        public String getOrgId() {
            return orgId;
        }

        /**
         * @param orgId The orgId
         */
        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        /**
         * @return The orgname
         */
        public String getOrgname() {
            return orgname;
        }

        /**
         * @param orgname The orgname
         */
        public void setOrgname(String orgname) {
            this.orgname = orgname;
        }

        /**
         * @return The pageNo
         */
        public Integer getPageNo() {
            return pageNo;
        }

        /**
         * @param pageNo The pageNo
         */
        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        /**
         * @return The pageSize
         */
        public Integer getPageSize() {
            return pageSize;
        }

        /**
         * @param pageSize The pageSize
         */
        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        /**
         * @return The prtnumber
         */
        public String getPrtnumber() {
            return prtnumber;
        }

        /**
         * @param prtnumber The prtnumber
         */
        public void setPrtnumber(String prtnumber) {
            this.prtnumber = prtnumber;
        }

        /**
         * @return The regorg
         */
        public String getRegorg() {
            return regorg;
        }

        /**
         * @param regorg The regorg
         */
        public void setRegorg(String regorg) {
            this.regorg = regorg;
        }

        /**
         * @return The remark
         */
        public String getRemark() {
            return remark;
        }

        /**
         * @param remark The remark
         */
        public void setRemark(String remark) {
            this.remark = remark;
        }

        /**
         * @return The request
         */
        public Object getRequest() {
            return request;
        }

        /**
         * @param request The request
         */
        public void setRequest(Object request) {
            this.request = request;
        }

        /**
         * @return The seclevel
         */
        public String getSeclevel() {
            return seclevel;
        }

        /**
         * @param seclevel The seclevel
         */
        public void setSeclevel(String seclevel) {
            this.seclevel = seclevel;
        }

        /**
         * @return The sectime
         */
        public String getSectime() {
            return sectime;
        }

        /**
         * @param sectime The sectime
         */
        public void setSectime(String sectime) {
            this.sectime = sectime;
        }

        /**
         * @return The signdateEnd
         */
        public Object getSigndateEnd() {
            return signdateEnd;
        }

        /**
         * @param signdateEnd The signdateEnd
         */
        public void setSigndateEnd(Object signdateEnd) {
            this.signdateEnd = signdateEnd;
        }

        /**
         * @return The signdateStart
         */
        public Object getSigndateStart() {
            return signdateStart;
        }

        /**
         * @param signdateStart The signdateStart
         */
        public void setSigndateStart(Object signdateStart) {
            this.signdateStart = signdateStart;
        }

        /**
         * @return The signer
         */
        public String getSigner() {
            return signer;
        }

        /**
         * @param signer The signer
         */
        public void setSigner(String signer) {
            this.signer = signer;
        }

        /**
         * @return The signerId
         */
        public String getSignerId() {
            return signerId;
        }

        /**
         * @param signerId The signerId
         */
        public void setSignerId(String signerId) {
            this.signerId = signerId;
        }

        /**
         * @return The themeword
         */
        public String getThemeword() {
            return themeword;
        }

        /**
         * @param themeword The themeword
         */
        public void setThemeword(String themeword) {
            this.themeword = themeword;
        }

        /**
         * @return The title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title The title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return The userId
         */
        public String getUserId() {
            return userId;
        }

        /**
         * @param userId The userId
         */
        public void setUserId(String userId) {
            this.userId = userId;
        }

        /**
         * @return The username
         */
        public String getUsername() {
            return username;
        }

        /**
         * @param username The username
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * @return The yearno
         */
        public String getYearno() {
            return yearno;
        }

        /**
         * @param yearno The yearno
         */
        public void setYearno(String yearno) {
            this.yearno = yearno;
        }

    }

    public class Result1 {

        private String createAt;
        private String docId;
        private String flowUuid;
        private String title;
        private String type;

        /**
         * @return The createAt
         */
        public String getCreateAt() {
            return createAt;
        }

        /**
         * @param createAt The createAt
         */
        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        /**
         * @return The docId
         */
        public String getDocId() {
            return docId;
        }

        /**
         * @param docId The docId
         */
        public void setDocId(String docId) {
            this.docId = docId;
        }

        /**
         * @return The flowUuid
         */
        public String getFlowUuid() {
            return flowUuid;
        }

        /**
         * @param flowUuid The flowUuid
         */
        public void setFlowUuid(String flowUuid) {
            this.flowUuid = flowUuid;
        }

        /**
         * @return The title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title The title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return The type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type The type
         */
        public void setType(String type) {
            this.type = type;
        }

    }
}
