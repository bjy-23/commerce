package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yclli on 2015/12/8.
 */

public class ReceiveDetailBean {

    public static String flowUuid = "";
    public static String activityUuid = "";
    public static String activityId = "";
    public static String activityName="";
    public static ReceiveDetailBean receiveDetailBean = null;

    private Result result;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        private List<Object> organLeaderOpnnList = new ArrayList<Object>();
        private List<DocAttachVoList> docAttachVoList = new ArrayList<DocAttachVoList>();
        private String isUrgency;
        private List<OpnnList> opnnList = new ArrayList<OpnnList>();
        private DocReceiveVo docReceiveVo;
        private List<DicFlowOperationList> dicFlowOperationList = new ArrayList<DicFlowOperationList>();
        private DocAttachFile docAttachFile;
        private List<DeptLeaderOpnnList> deptLeaderOpnnList = new ArrayList<DeptLeaderOpnnList>();
        private String isOpen;
        private Transact transact;


        public String getIsUrgency() {
            return isUrgency;
        }

        public void setIsUrgency(String isUrgency) {
            this.isUrgency = isUrgency;
        }

        /**
         *
         * @return
         * The organLeaderOpnnList
         */
        public List<Object> getOrganLeaderOpnnList() {
            return organLeaderOpnnList;
        }

        /**
         *
         * @param organLeaderOpnnList
         * The organLeaderOpnnList
         */
        public void setOrganLeaderOpnnList(List<Object> organLeaderOpnnList) {
            this.organLeaderOpnnList = organLeaderOpnnList;
        }

        /**
         *
         * @return
         * The docAttachVoList
         */
        public List<DocAttachVoList> getDocAttachVoList() {
            return docAttachVoList;
        }

        /**
         *
         * @param docAttachVoList
         * The docAttachVoList
         */
        public void setDocAttachVoList(List<DocAttachVoList> docAttachVoList) {
            this.docAttachVoList = docAttachVoList;
        }

        /**
         *
         * @return
         * The opnnList
         */
        public List<OpnnList> getOpnnList() {
            return opnnList;
        }

        /**
         *
         * @param opnnList
         * The opnnList
         */
        public void setOpnnList(List<OpnnList> opnnList) {
            this.opnnList = opnnList;
        }

        /**
         *
         * @return
         * The docReceiveVo
         */
        public DocReceiveVo getDocReceiveVo() {
            return docReceiveVo;
        }

        /**
         *
         * @param docReceiveVo
         * The docReceiveVo
         */
        public void setDocReceiveVo(DocReceiveVo docReceiveVo) {
            this.docReceiveVo = docReceiveVo;
        }

        /**
         *
         * @return
         * The dicFlowOperationList
         */
        public List<DicFlowOperationList> getDicFlowOperationList() {
            return dicFlowOperationList;
        }

        /**
         *
         * @param dicFlowOperationList
         * The dicFlowOperationList
         */
        public void setDicFlowOperationList(List<DicFlowOperationList> dicFlowOperationList) {
            this.dicFlowOperationList = dicFlowOperationList;
        }

        /**
         *
         * @return
         * The docAttachFile
         */
        public DocAttachFile getDocAttachFile() {
            return docAttachFile;
        }

        /**
         *
         * @param docAttachFile
         * The docAttachFile
         */
        public void setDocAttachFile(DocAttachFile docAttachFile) {
            this.docAttachFile = docAttachFile;
        }

        /**
         *
         * @return
         * The deptLeaderOpnnList
         */
        public List<DeptLeaderOpnnList> getDeptLeaderOpnnList() {
            return deptLeaderOpnnList;
        }

        /**
         *
         * @param deptLeaderOpnnList
         * The deptLeaderOpnnList
         */
        public void setDeptLeaderOpnnList(List<DeptLeaderOpnnList> deptLeaderOpnnList) {
            this.deptLeaderOpnnList = deptLeaderOpnnList;
        }

        /**
         *
         * @return
         * The isOpen
         */
        public String getIsOpen() {
            return isOpen;
        }

        /**
         *
         * @param isOpen
         * The isOpen
         */
        public void setIsOpen(String isOpen) {
            this.isOpen = isOpen;
        }

        public class DeptLeaderOpnnList {

            private String transactdate;
            private String transactopinion;
            private String username;

            /**
             *
             * @return
             * The transactdate
             */
            public String getTransactdate() {
                return transactdate;
            }

            /**
             *
             * @param transactdate
             * The transactdate
             */
            public void setTransactdate(String transactdate) {
                this.transactdate = transactdate;
            }

            /**
             *
             * @return
             * The transactopinion
             */
            public String getTransactopinion() {
                return transactopinion;
            }

            /**
             *
             * @param transactopinion
             * The transactopinion
             */
            public void setTransactopinion(String transactopinion) {
                this.transactopinion = transactopinion;
            }

            /**
             *
             * @return
             * The username
             */
            public String getUsername() {
                return username;
            }

            /**
             *
             * @param username
             * The username
             */
            public void setUsername(String username) {
                this.username = username;
            }

        }

        public class DicFlowOperationList {

            private String authId;
            private String authority;
            private String includeDept;
            private String mustSetOpnn;
            private String operationId;
            private String operationName;
            private String selected;
            private String transactName;
            private String userflag;

            /**
             *
             * @return
             * The authId
             */
            public String getAuthId() {
                return authId;
            }

            /**
             *
             * @param authId
             * The authId
             */
            public void setAuthId(String authId) {
                this.authId = authId;
            }

            /**
             *
             * @return
             * The authority
             */
            public String getAuthority() {
                return authority;
            }

            /**
             *
             * @param authority
             * The authority
             */
            public void setAuthority(String authority) {
                this.authority = authority;
            }

            /**
             *
             * @return
             * The includeDept
             */
            public String getIncludeDept() {
                return includeDept;
            }

            /**
             *
             * @param includeDept
             * The includeDept
             */
            public void setIncludeDept(String includeDept) {
                this.includeDept = includeDept;
            }

            /**
             *
             * @return
             * The mustSetOpnn
             */
            public String getMustSetOpnn() {
                return mustSetOpnn;
            }

            /**
             *
             * @param mustSetOpnn
             * The mustSetOpnn
             */
            public void setMustSetOpnn(String mustSetOpnn) {
                this.mustSetOpnn = mustSetOpnn;
            }

            /**
             *
             * @return
             * The operationId
             */
            public String getOperationId() {
                return operationId;
            }

            /**
             *
             * @param operationId
             * The operationId
             */
            public void setOperationId(String operationId) {
                this.operationId = operationId;
            }

            /**
             *
             * @return
             * The operationName
             */
            public String getOperationName() {
                return operationName;
            }

            /**
             *
             * @param operationName
             * The operationName
             */
            public void setOperationName(String operationName) {
                this.operationName = operationName;
            }

            /**
             *
             * @return
             * The selected
             */
            public String getSelected() {
                return selected;
            }

            /**
             *
             * @param selected
             * The selected
             */
            public void setSelected(String selected) {
                this.selected = selected;
            }

            /**
             *
             * @return
             * The transactName
             */
            public String getTransactName() {
                return transactName;
            }

            /**
             *
             * @param transactName
             * The transactName
             */
            public void setTransactName(String transactName) {
                this.transactName = transactName;
            }

            /**
             *
             * @return
             * The userflag
             */
            public String getUserflag() {
                return userflag;
            }

            /**
             *
             * @param userflag
             * The userflag
             */
            public void setUserflag(String userflag) {
                this.userflag = userflag;
            }

        }

        public class DocAttachFile {

            private String attachFileStr;
            private String attachId;
            private String attachName;

            /**
             *
             * @return
             * The attachFileStr
             */
            public String getAttachFileStr() {
                return attachFileStr;
            }

            /**
             *
             * @param attachFileStr
             * The attachFileStr
             */
            public void setAttachFileStr(String attachFileStr) {
                this.attachFileStr = attachFileStr;
            }

            /**
             *
             * @return
             * The attachId
             */
            public String getAttachId() {
                return attachId;
            }

            /**
             *
             * @param attachId
             * The attachId
             */
            public void setAttachId(String attachId) {
                this.attachId = attachId;
            }

            /**
             *
             * @return
             * The attachName
             */
            public String getAttachName() {
                return attachName;
            }

            /**
             *
             * @param attachName
             * The attachName
             */
            public void setAttachName(String attachName) {
                this.attachName = attachName;
            }

        }

        public class DocAttachVoList {

            private String attachFileStr;
            private String attachId;
            private String attachName;

            /**
             *
             * @return
             * The attachFileStr
             */
            public String getAttachFileStr() {
                return attachFileStr;
            }

            /**
             *
             * @param attachFileStr
             * The attachFileStr
             */
            public void setAttachFileStr(String attachFileStr) {
                this.attachFileStr = attachFileStr;
            }

            /**
             *
             * @return
             * The attachId
             */
            public String getAttachId() {
                return attachId;
            }

            /**
             *
             * @param attachId
             * The attachId
             */
            public void setAttachId(String attachId) {
                this.attachId = attachId;
            }

            /**
             *
             * @return
             * The attachName
             */
            public String getAttachName() {
                return attachName;
            }

            /**
             *
             * @param attachName
             * The attachName
             */
            public void setAttachName(String attachName) {
                this.attachName = attachName;
            }

        }

        public class DocReceiveVo {

            private String docno;
            private String isMsign;
            private String isindicate;
            private String opertype;
            private String recId;
            private String recdate;
            private String recnum;
            private String regorg;
            private String remark;
            private String title;
            private String urgencyStatus;

            /**
             *
             * @return
             * The docno
             */
            public String getDocno() {
                return docno;
            }

            /**
             *
             * @param docno
             * The docno
             */
            public void setDocno(String docno) {
                this.docno = docno;
            }

            /**
             *
             * @return
             * The isMsign
             */
            public String getIsMsign() {
                return isMsign;
            }

            /**
             *
             * @param isMsign
             * The isMsign
             */
            public void setIsMsign(String isMsign) {
                this.isMsign = isMsign;
            }

            /**
             *
             * @return
             * The isindicate
             */
            public String getIsindicate() {
                return isindicate;
            }

            /**
             *
             * @param isindicate
             * The isindicate
             */
            public void setIsindicate(String isindicate) {
                this.isindicate = isindicate;
            }

            /**
             *
             * @return
             * The opertype
             */
            public String getOpertype() {
                return opertype;
            }

            /**
             *
             * @param opertype
             * The opertype
             */
            public void setOpertype(String opertype) {
                this.opertype = opertype;
            }

            /**
             *
             * @return
             * The recId
             */
            public String getRecId() {
                return recId;
            }

            /**
             *
             * @param recId
             * The recId
             */
            public void setRecId(String recId) {
                this.recId = recId;
            }

            /**
             *
             * @return
             * The recdate
             */
            public String getRecdate() {
                return recdate;
            }

            /**
             *
             * @param recdate
             * The recdate
             */
            public void setRecdate(String recdate) {
                this.recdate = recdate;
            }

            /**
             *
             * @return
             * The recnum
             */
            public String getRecnum() {
                return recnum;
            }

            /**
             *
             * @param recnum
             * The recnum
             */
            public void setRecnum(String recnum) {
                this.recnum = recnum;
            }

            /**
             *
             * @return
             * The regorg
             */
            public String getRegorg() {
                return regorg;
            }

            /**
             *
             * @param regorg
             * The regorg
             */
            public void setRegorg(String regorg) {
                this.regorg = regorg;
            }

            /**
             *
             * @return
             * The remark
             */
            public String getRemark() {
                return remark;
            }

            /**
             *
             * @param remark
             * The remark
             */
            public void setRemark(String remark) {
                this.remark = remark;
            }

            /**
             *
             * @return
             * The title
             */
            public String getTitle() {
                return title;
            }

            /**
             *
             * @param title
             * The title
             */
            public void setTitle(String title) {
                this.title = title;
            }

            /**
             *
             * @return
             * The urgencyStatus
             */
            public String getUrgencyStatus() {
                return urgencyStatus;
            }

            /**
             *
             * @param urgencyStatus
             * The urgencyStatus
             */
            public void setUrgencyStatus(String urgencyStatus) {
                this.urgencyStatus = urgencyStatus;
            }

        }

        public class OpnnList {

            private String activityName;
            private String operationName;
            private String opinion;
            private String updateAt;
            private String userName;

            /**
             *
             * @return
             * The activityName
             */
            public String getActivityName() {
                return activityName;
            }

            /**
             *
             * @param activityName
             * The activityName
             */
            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }

            /**
             *
             * @return
             * The operationName
             */
            public String getOperationName() {
                return operationName;
            }

            /**
             *
             * @param operationName
             * The operationName
             */
            public void setOperationName(String operationName) {
                this.operationName = operationName;
            }

            /**
             *
             * @return
             * The opinion
             */
            public String getOpinion() {
                return opinion;
            }

            /**
             *
             * @param opinion
             * The opinion
             */
            public void setOpinion(String opinion) {
                this.opinion = opinion;
            }

            /**
             *
             * @return
             * The updateAt
             */
            public String getUpdateAt() {
                return updateAt;
            }

            /**
             *
             * @param updateAt
             * The updateAt
             */
            public void setUpdateAt(String updateAt) {
                this.updateAt = updateAt;
            }

            /**
             *
             * @return
             * The userName
             */
            public String getUserName() {
                return userName;
            }

            /**
             *
             * @param userName
             * The userName
             */
            public void setUserName(String userName) {
                this.userName = userName;
            }

        }

        /**
         *
         * @return
         * The transact
         */
        public Transact getTransact() {
            return transact;
        }

        /**
         *
         * @param transact
         * The transact
         */
        public void setTransact(Transact transact) {
            this.transact = transact;
        }

        public class Transact {

            private String actViewUuid;
            private String addToCommonOpioion;
            private List<String> attributeNames = new ArrayList<String>();
            private String auditorId;
            private String auditorname;
            private String cmd;
            private Object createtime;
            private String deptId;
            private String deptname;
            private Integer execFlowWorkFlag;
            private String isStart;
            private String isattend;
            private Object lastupdate;
            private String leader;
            private String nextnodeId;
            private String nextuserId;
            private String nextusername;
            private String nodeId;
            private String nodeType;
            private String nodename;
            private Object obj;
            private String operationtype;
            private String orgId;
            private String orgname;
            private String primaryKey;
            private String recId;
            private Boolean selected;
            private List<Object> subList = new ArrayList<Object>();
            private String tableName;
            private String transactId;
            private String transactdate;
            private String transactopinion;
            private String userId;
            private String username;
            private WorkFlowParameter workFlowParameter;

            /**
             *
             * @return
             * The actViewUuid
             */
            public String getActViewUuid() {
                return actViewUuid;
            }

            /**
             *
             * @param actViewUuid
             * The actViewUuid
             */
            public void setActViewUuid(String actViewUuid) {
                this.actViewUuid = actViewUuid;
            }

            /**
             *
             * @return
             * The addToCommonOpioion
             */
            public String getAddToCommonOpioion() {
                return addToCommonOpioion;
            }

            /**
             *
             * @param addToCommonOpioion
             * The addToCommonOpioion
             */
            public void setAddToCommonOpioion(String addToCommonOpioion) {
                this.addToCommonOpioion = addToCommonOpioion;
            }

            /**
             *
             * @return
             * The attributeNames
             */
            public List<String> getAttributeNames() {
                return attributeNames;
            }

            /**
             *
             * @param attributeNames
             * The attributeNames
             */
            public void setAttributeNames(List<String> attributeNames) {
                this.attributeNames = attributeNames;
            }

            /**
             *
             * @return
             * The auditorId
             */
            public String getAuditorId() {
                return auditorId;
            }

            /**
             *
             * @param auditorId
             * The auditorId
             */
            public void setAuditorId(String auditorId) {
                this.auditorId = auditorId;
            }

            /**
             *
             * @return
             * The auditorname
             */
            public String getAuditorname() {
                return auditorname;
            }

            /**
             *
             * @param auditorname
             * The auditorname
             */
            public void setAuditorname(String auditorname) {
                this.auditorname = auditorname;
            }

            /**
             *
             * @return
             * The cmd
             */
            public String getCmd() {
                return cmd;
            }

            /**
             *
             * @param cmd
             * The cmd
             */
            public void setCmd(String cmd) {
                this.cmd = cmd;
            }

            /**
             *
             * @return
             * The createtime
             */
            public Object getCreatetime() {
                return createtime;
            }

            /**
             *
             * @param createtime
             * The createtime
             */
            public void setCreatetime(Object createtime) {
                this.createtime = createtime;
            }

            /**
             *
             * @return
             * The deptId
             */
            public String getDeptId() {
                return deptId;
            }

            /**
             *
             * @param deptId
             * The deptId
             */
            public void setDeptId(String deptId) {
                this.deptId = deptId;
            }

            /**
             *
             * @return
             * The deptname
             */
            public String getDeptname() {
                return deptname;
            }

            /**
             *
             * @param deptname
             * The deptname
             */
            public void setDeptname(String deptname) {
                this.deptname = deptname;
            }

            /**
             *
             * @return
             * The execFlowWorkFlag
             */
            public Integer getExecFlowWorkFlag() {
                return execFlowWorkFlag;
            }

            /**
             *
             * @param execFlowWorkFlag
             * The execFlowWorkFlag
             */
            public void setExecFlowWorkFlag(Integer execFlowWorkFlag) {
                this.execFlowWorkFlag = execFlowWorkFlag;
            }

            /**
             *
             * @return
             * The isStart
             */
            public String getIsStart() {
                return isStart;
            }

            /**
             *
             * @param isStart
             * The isStart
             */
            public void setIsStart(String isStart) {
                this.isStart = isStart;
            }

            /**
             *
             * @return
             * The isattend
             */
            public String getIsattend() {
                return isattend;
            }

            /**
             *
             * @param isattend
             * The isattend
             */
            public void setIsattend(String isattend) {
                this.isattend = isattend;
            }

            /**
             *
             * @return
             * The lastupdate
             */
            public Object getLastupdate() {
                return lastupdate;
            }

            /**
             *
             * @param lastupdate
             * The lastupdate
             */
            public void setLastupdate(Object lastupdate) {
                this.lastupdate = lastupdate;
            }

            /**
             *
             * @return
             * The leader
             */
            public String getLeader() {
                return leader;
            }

            /**
             *
             * @param leader
             * The leader
             */
            public void setLeader(String leader) {
                this.leader = leader;
            }

            /**
             *
             * @return
             * The nextnodeId
             */
            public String getNextnodeId() {
                return nextnodeId;
            }

            /**
             *
             * @param nextnodeId
             * The nextnodeId
             */
            public void setNextnodeId(String nextnodeId) {
                this.nextnodeId = nextnodeId;
            }

            /**
             *
             * @return
             * The nextuserId
             */
            public String getNextuserId() {
                return nextuserId;
            }

            /**
             *
             * @param nextuserId
             * The nextuserId
             */
            public void setNextuserId(String nextuserId) {
                this.nextuserId = nextuserId;
            }

            /**
             *
             * @return
             * The nextusername
             */
            public String getNextusername() {
                return nextusername;
            }

            /**
             *
             * @param nextusername
             * The nextusername
             */
            public void setNextusername(String nextusername) {
                this.nextusername = nextusername;
            }

            /**
             *
             * @return
             * The nodeId
             */
            public String getNodeId() {
                return nodeId;
            }

            /**
             *
             * @param nodeId
             * The nodeId
             */
            public void setNodeId(String nodeId) {
                this.nodeId = nodeId;
            }

            /**
             *
             * @return
             * The nodeType
             */
            public String getNodeType() {
                return nodeType;
            }

            /**
             *
             * @param nodeType
             * The nodeType
             */
            public void setNodeType(String nodeType) {
                this.nodeType = nodeType;
            }

            /**
             *
             * @return
             * The nodename
             */
            public String getNodename() {
                return nodename;
            }

            /**
             *
             * @param nodename
             * The nodename
             */
            public void setNodename(String nodename) {
                this.nodename = nodename;
            }

            /**
             *
             * @return
             * The obj
             */
            public Object getObj() {
                return obj;
            }

            /**
             *
             * @param obj
             * The obj
             */
            public void setObj(Object obj) {
                this.obj = obj;
            }

            /**
             *
             * @return
             * The operationtype
             */
            public String getOperationtype() {
                return operationtype;
            }

            /**
             *
             * @param operationtype
             * The operationtype
             */
            public void setOperationtype(String operationtype) {
                this.operationtype = operationtype;
            }

            /**
             *
             * @return
             * The orgId
             */
            public String getOrgId() {
                return orgId;
            }

            /**
             *
             * @param orgId
             * The orgId
             */
            public void setOrgId(String orgId) {
                this.orgId = orgId;
            }

            /**
             *
             * @return
             * The orgname
             */
            public String getOrgname() {
                return orgname;
            }

            /**
             *
             * @param orgname
             * The orgname
             */
            public void setOrgname(String orgname) {
                this.orgname = orgname;
            }

            /**
             *
             * @return
             * The primaryKey
             */
            public String getPrimaryKey() {
                return primaryKey;
            }

            /**
             *
             * @param primaryKey
             * The primaryKey
             */
            public void setPrimaryKey(String primaryKey) {
                this.primaryKey = primaryKey;
            }

            /**
             *
             * @return
             * The recId
             */
            public String getRecId() {
                return recId;
            }

            /**
             *
             * @param recId
             * The recId
             */
            public void setRecId(String recId) {
                this.recId = recId;
            }

            /**
             *
             * @return
             * The selected
             */
            public Boolean getSelected() {
                return selected;
            }

            /**
             *
             * @param selected
             * The selected
             */
            public void setSelected(Boolean selected) {
                this.selected = selected;
            }

            /**
             *
             * @return
             * The subList
             */
            public List<Object> getSubList() {
                return subList;
            }

            /**
             *
             * @param subList
             * The subList
             */
            public void setSubList(List<Object> subList) {
                this.subList = subList;
            }

            /**
             *
             * @return
             * The tableName
             */
            public String getTableName() {
                return tableName;
            }

            /**
             *
             * @param tableName
             * The tableName
             */
            public void setTableName(String tableName) {
                this.tableName = tableName;
            }

            /**
             *
             * @return
             * The transactId
             */
            public String getTransactId() {
                return transactId;
            }

            /**
             *
             * @param transactId
             * The transactId
             */
            public void setTransactId(String transactId) {
                this.transactId = transactId;
            }

            /**
             *
             * @return
             * The transactdate
             */
            public String getTransactdate() {
                return transactdate;
            }

            /**
             *
             * @param transactdate
             * The transactdate
             */
            public void setTransactdate(String transactdate) {
                this.transactdate = transactdate;
            }

            /**
             *
             * @return
             * The transactopinion
             */
            public String getTransactopinion() {
                return transactopinion;
            }

            /**
             *
             * @param transactopinion
             * The transactopinion
             */
            public void setTransactopinion(String transactopinion) {
                this.transactopinion = transactopinion;
            }

            /**
             *
             * @return
             * The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             * The userId
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             * The username
             */
            public String getUsername() {
                return username;
            }

            /**
             *
             * @param username
             * The username
             */
            public void setUsername(String username) {
                this.username = username;
            }

            /**
             *
             * @return
             * The workFlowParameter
             */
            public WorkFlowParameter getWorkFlowParameter() {
                return workFlowParameter;
            }

            /**
             *
             * @param workFlowParameter
             * The workFlowParameter
             */
            public void setWorkFlowParameter(WorkFlowParameter workFlowParameter) {
                this.workFlowParameter = workFlowParameter;
            }

            public class WorkFlowParameter {

                private String appendSql;
                private List<Object> businessAuth = new ArrayList<Object>();
                private String deptId;
                private String deptname;
                private Object endDate;
                private Integer execFlowWorkFlag;
                private String flowIds;
                private String flowVersion;
                private String instDescription;
                private String nextUserId;
                private String opinion;
                private String orderSql;
                private String orgId;
                private String orgname;
                private Integer pageNo;
                private Integer pageSize;
                private Object startDate;
                private String userId;
                private String username;
                private String workflowActivityId;
                private String workflowActivityUuid;
                private String workflowFlowUuid;
                private String workflowJsonDatas;
                private String workflowOperationId;
                private WorkflowSearchConditionMap workflowSearchConditionMap;

                /**
                 *
                 * @return
                 * The appendSql
                 */
                public String getAppendSql() {
                    return appendSql;
                }

                /**
                 *
                 * @param appendSql
                 * The appendSql
                 */
                public void setAppendSql(String appendSql) {
                    this.appendSql = appendSql;
                }

                /**
                 *
                 * @return
                 * The businessAuth
                 */
                public List<Object> getBusinessAuth() {
                    return businessAuth;
                }

                /**
                 *
                 * @param businessAuth
                 * The businessAuth
                 */
                public void setBusinessAuth(List<Object> businessAuth) {
                    this.businessAuth = businessAuth;
                }

                /**
                 *
                 * @return
                 * The deptId
                 */
                public String getDeptId() {
                    return deptId;
                }

                /**
                 *
                 * @param deptId
                 * The deptId
                 */
                public void setDeptId(String deptId) {
                    this.deptId = deptId;
                }

                /**
                 *
                 * @return
                 * The deptname
                 */
                public String getDeptname() {
                    return deptname;
                }

                /**
                 *
                 * @param deptname
                 * The deptname
                 */
                public void setDeptname(String deptname) {
                    this.deptname = deptname;
                }

                /**
                 *
                 * @return
                 * The endDate
                 */
                public Object getEndDate() {
                    return endDate;
                }

                /**
                 *
                 * @param endDate
                 * The endDate
                 */
                public void setEndDate(Object endDate) {
                    this.endDate = endDate;
                }

                /**
                 *
                 * @return
                 * The execFlowWorkFlag
                 */
                public Integer getExecFlowWorkFlag() {
                    return execFlowWorkFlag;
                }

                /**
                 *
                 * @param execFlowWorkFlag
                 * The execFlowWorkFlag
                 */
                public void setExecFlowWorkFlag(Integer execFlowWorkFlag) {
                    this.execFlowWorkFlag = execFlowWorkFlag;
                }

                /**
                 *
                 * @return
                 * The flowIds
                 */
                public String getFlowIds() {
                    return flowIds;
                }

                /**
                 *
                 * @param flowIds
                 * The flowIds
                 */
                public void setFlowIds(String flowIds) {
                    this.flowIds = flowIds;
                }

                /**
                 *
                 * @return
                 * The flowVersion
                 */
                public String getFlowVersion() {
                    return flowVersion;
                }

                /**
                 *
                 * @param flowVersion
                 * The flowVersion
                 */
                public void setFlowVersion(String flowVersion) {
                    this.flowVersion = flowVersion;
                }

                /**
                 *
                 * @return
                 * The instDescription
                 */
                public String getInstDescription() {
                    return instDescription;
                }

                /**
                 *
                 * @param instDescription
                 * The instDescription
                 */
                public void setInstDescription(String instDescription) {
                    this.instDescription = instDescription;
                }

                /**
                 *
                 * @return
                 * The nextUserId
                 */
                public String getNextUserId() {
                    return nextUserId;
                }

                /**
                 *
                 * @param nextUserId
                 * The nextUserId
                 */
                public void setNextUserId(String nextUserId) {
                    this.nextUserId = nextUserId;
                }

                /**
                 *
                 * @return
                 * The opinion
                 */
                public String getOpinion() {
                    return opinion;
                }

                /**
                 *
                 * @param opinion
                 * The opinion
                 */
                public void setOpinion(String opinion) {
                    this.opinion = opinion;
                }

                /**
                 *
                 * @return
                 * The orderSql
                 */
                public String getOrderSql() {
                    return orderSql;
                }

                /**
                 *
                 * @param orderSql
                 * The orderSql
                 */
                public void setOrderSql(String orderSql) {
                    this.orderSql = orderSql;
                }

                /**
                 *
                 * @return
                 * The orgId
                 */
                public String getOrgId() {
                    return orgId;
                }

                /**
                 *
                 * @param orgId
                 * The orgId
                 */
                public void setOrgId(String orgId) {
                    this.orgId = orgId;
                }

                /**
                 *
                 * @return
                 * The orgname
                 */
                public String getOrgname() {
                    return orgname;
                }

                /**
                 *
                 * @param orgname
                 * The orgname
                 */
                public void setOrgname(String orgname) {
                    this.orgname = orgname;
                }

                /**
                 *
                 * @return
                 * The pageNo
                 */
                public Integer getPageNo() {
                    return pageNo;
                }

                /**
                 *
                 * @param pageNo
                 * The pageNo
                 */
                public void setPageNo(Integer pageNo) {
                    this.pageNo = pageNo;
                }

                /**
                 *
                 * @return
                 * The pageSize
                 */
                public Integer getPageSize() {
                    return pageSize;
                }

                /**
                 *
                 * @param pageSize
                 * The pageSize
                 */
                public void setPageSize(Integer pageSize) {
                    this.pageSize = pageSize;
                }

                /**
                 *
                 * @return
                 * The startDate
                 */
                public Object getStartDate() {
                    return startDate;
                }

                /**
                 *
                 * @param startDate
                 * The startDate
                 */
                public void setStartDate(Object startDate) {
                    this.startDate = startDate;
                }

                /**
                 *
                 * @return
                 * The userId
                 */
                public String getUserId() {
                    return userId;
                }

                /**
                 *
                 * @param userId
                 * The userId
                 */
                public void setUserId(String userId) {
                    this.userId = userId;
                }

                /**
                 *
                 * @return
                 * The username
                 */
                public String getUsername() {
                    return username;
                }

                /**
                 *
                 * @param username
                 * The username
                 */
                public void setUsername(String username) {
                    this.username = username;
                }

                /**
                 *
                 * @return
                 * The workflowActivityId
                 */
                public String getWorkflowActivityId() {
                    return workflowActivityId;
                }

                /**
                 *
                 * @param workflowActivityId
                 * The workflowActivityId
                 */
                public void setWorkflowActivityId(String workflowActivityId) {
                    this.workflowActivityId = workflowActivityId;
                }

                /**
                 *
                 * @return
                 * The workflowActivityUuid
                 */
                public String getWorkflowActivityUuid() {
                    return workflowActivityUuid;
                }

                /**
                 *
                 * @param workflowActivityUuid
                 * The workflowActivityUuid
                 */
                public void setWorkflowActivityUuid(String workflowActivityUuid) {
                    this.workflowActivityUuid = workflowActivityUuid;
                }

                /**
                 *
                 * @return
                 * The workflowFlowUuid
                 */
                public String getWorkflowFlowUuid() {
                    return workflowFlowUuid;
                }

                /**
                 *
                 * @param workflowFlowUuid
                 * The workflowFlowUuid
                 */
                public void setWorkflowFlowUuid(String workflowFlowUuid) {
                    this.workflowFlowUuid = workflowFlowUuid;
                }

                /**
                 *
                 * @return
                 * The workflowJsonDatas
                 */
                public String getWorkflowJsonDatas() {
                    return workflowJsonDatas;
                }

                /**
                 *
                 * @param workflowJsonDatas
                 * The workflowJsonDatas
                 */
                public void setWorkflowJsonDatas(String workflowJsonDatas) {
                    this.workflowJsonDatas = workflowJsonDatas;
                }

                /**
                 *
                 * @return
                 * The workflowOperationId
                 */
                public String getWorkflowOperationId() {
                    return workflowOperationId;
                }

                /**
                 *
                 * @param workflowOperationId
                 * The workflowOperationId
                 */
                public void setWorkflowOperationId(String workflowOperationId) {
                    this.workflowOperationId = workflowOperationId;
                }

                /**
                 *
                 * @return
                 * The workflowSearchConditionMap
                 */
                public WorkflowSearchConditionMap getWorkflowSearchConditionMap() {
                    return workflowSearchConditionMap;
                }

                /**
                 *
                 * @param workflowSearchConditionMap
                 * The workflowSearchConditionMap
                 */
                public void setWorkflowSearchConditionMap(WorkflowSearchConditionMap workflowSearchConditionMap) {
                    this.workflowSearchConditionMap = workflowSearchConditionMap;
                }

                public class WorkflowSearchConditionMap {
                }

            }

        }
    }






}





