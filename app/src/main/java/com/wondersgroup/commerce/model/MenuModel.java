package com.wondersgroup.commerce.model;

import java.util.ArrayList;

/**
 * Created by kangrenhui on 2016/1/18.
 */
public class MenuModel {
    private String name;
    private ArrayList<FunctionModel> functionList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<FunctionModel> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(ArrayList<FunctionModel> functionList) {
        this.functionList = functionList;
    }
}
