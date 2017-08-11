package com.wondersgroup.commerce.model.yn;

import java.util.List;

/**
 * Created by kangrenhui on 2016/3/14.
 */
public class YnLoginBean {
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

    public class DeptIds {

    }


    public class DeptNames {

    }

    public class OrganIds {

    }

    public class OrganNames {

    }

    public class Result {
        private String deptId;



        private String deptName;



        private String loginName;

        private String organId;


        private String organName;



        private String userId;

        private String userName;

        public void setDeptId(String deptId){
            this.deptId = deptId;
        }
        public String getDeptId(){
            return this.deptId;
        }

        public void setDeptName(String deptName){
            this.deptName = deptName;
        }
        public String getDeptName(){
            return this.deptName;
        }


        public void setLoginName(String loginName){
            this.loginName = loginName;
        }
        public String getLoginName(){
            return this.loginName;
        }
        public void setOrganId(String organId){
            this.organId = organId;
        }
        public String getOrganId(){
            return this.organId;
        }

        public void setOrganName(String organName){
            this.organName = organName;
        }
        public String getOrganName(){
            return this.organName;
        }

        public void setUserId(String userId){
            this.userId = userId;
        }
        public String getUserId(){
            return this.userId;
        }
        public void setUserName(String userName){
            this.userName = userName;
        }
        public String getUserName(){
            return this.userName;
        }

    }




}
