package com.wondersgroup.commerce.teamwork.wywork.ydjyxz.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 */
public class YDJYTreeBean {
    public class DoorType {
        private String name;

        private String id;

        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }

    }
    public class Children {
        private String value;

        private String key;

        private List<Children> children;

        public List<Children> getChildren() {
            return children;
        }

        public void setChildren(List<Children> children) {
            this.children = children;
        }

        public void setValue(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }
        public void setKey(String key){
            this.key = key;
        }
        public String getKey(){
            return this.key;
        }

    }
    public class OrganAreaId {
        private String value;

        private String key;

        private List<Children> children ;

        public void setValue(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }
        public void setKey(String key){
            this.key = key;
        }
        public String getKey(){
            return this.key;
        }
        public void setChildren(List<Children> children){
            this.children = children;
        }
        public List<Children> getChildren(){
            return this.children;
        }

    }
    public class Values {
        private List<DoorType> doorType ;

        private List<OrganAreaId> organAreaId ;

        public void setDoorType(List<DoorType> doorType){
            this.doorType = doorType;
        }
        public List<DoorType> getDoorType(){
            return this.doorType;
        }
        public void setOrganAreaId(List<OrganAreaId> organAreaId){
            this.organAreaId = organAreaId;
        }
        public List<OrganAreaId> getOrganAreaId(){
            return this.organAreaId;
        }

    }
        private String msg;

        private Values values;

        private String result;

        public void setMsg(String msg){
            this.msg = msg;
        }
        public String getMsg(){
            return this.msg;
        }
        public void setValues(Values values){
            this.values = values;
        }
        public Values getValues(){
            return this.values;
        }
        public void setResult(String result){
            this.result = result;
        }
        public String getResult(){
            return this.result;
        }
}
