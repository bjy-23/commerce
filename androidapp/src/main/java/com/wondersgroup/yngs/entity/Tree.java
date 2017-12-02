package com.wondersgroup.yngs.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 薛定猫 on 2016/1/27.
 */
public class Tree {
    private int id;
    private int pId;
    private String name;
    private List<TreeItem> children;

    public Tree() {
        this.id=-1;
        this.name="";
        this.children=new ArrayList<>();
        this.pId=-1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeItem> getChildren() {
        return children;
    }

    public void setChildren(List<TreeItem> children) {
        this.children = children;
    }

    /*public static Tree generateTree(List<TreeItem> items,int rootId){
        Tree root=new Tree();
        for (TreeItem i :
                items) {
            int pId = i.getpId();
            if(pId==rootId){
                root.setId(i.getId());
                root.setName(i.getName());
                break;
            }
        }
        for(int i=0;i<items.size();i++){
            if(items.get(i).getpId()==root.getId()){
                Tree child=generateTree(items,items.get(i).getId());
                child.setParent(root);
                root.children.add(child);
            }
        }
        return root;
    }*/
}
