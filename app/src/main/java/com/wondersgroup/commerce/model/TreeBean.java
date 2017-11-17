package com.wondersgroup.commerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bjy on 2017/9/19.
 */

public class TreeBean implements Parcelable, Cloneable{
    @SerializedName(value = "id", alternate = {"deptId"})
    private String id;

    private String pId;

    @SerializedName(value = "name", alternate = {"deptName"})
    private String name;

    private List<TreeBean> childs;
    private boolean selected;
    private int rank;
    private boolean hasChild;

    public TreeBean(){

    }

    public TreeBean(String id, String name, List<TreeBean> childs) {
        this.id = id;
        this.name = name;
        this.childs = childs;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeBean> getChilds() {
        return childs;
    }

    public void setChilds(List<TreeBean> childs) {
        this.childs = childs;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    protected TreeBean(Parcel in) {
        id = in.readString();
        pId = in.readString();
        name = in.readString();
        childs = in.createTypedArrayList(TreeBean.CREATOR);
        selected = in.readByte() != 0;
        rank = in.readInt();
        hasChild = in.readByte() != 0;
    }

    public static final Creator<TreeBean> CREATOR = new Creator<TreeBean>() {
        @Override
        public TreeBean createFromParcel(Parcel in) {
            return new TreeBean(in);
        }

        @Override
        public TreeBean[] newArray(int size) {
            return new TreeBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(pId);
        dest.writeString(name);
        dest.writeTypedList(childs);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeInt(rank);
        dest.writeByte((byte) (hasChild ? 1 : 0));
    }
}
