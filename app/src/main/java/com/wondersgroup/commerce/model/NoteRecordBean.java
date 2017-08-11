package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by Lee on 2016/2/4.
 * 笔录结构体（包括现场笔录、询问笔录）
 */
public class NoteRecordBean {

    private String message;
    private String code;
    private Result result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{
        private List<NoteRecordEnquire> enquireList;
        private List<NoteRecordSpot> inspectVoList;

        public List<NoteRecordEnquire> getEnquireList() {
            return enquireList;
        }

        public void setEnquireList(List<NoteRecordEnquire> enquireList) {
            this.enquireList = enquireList;
        }

        public List<NoteRecordSpot> getInspectVoList() {
            return inspectVoList;
        }

        public void setInspectVoList(List<NoteRecordSpot> inspectVoList) {
            this.inspectVoList = inspectVoList;
        }
    }
}
