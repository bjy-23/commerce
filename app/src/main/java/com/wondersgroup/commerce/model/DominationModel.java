package com.wondersgroup.commerce.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bjy on 2017/5/11.
 */

public class DominationModel{
    private String id;
    private String value;

    public boolean isEndValue() {
        return isEndValue;
    }

    public void setEndValue(boolean endValue) {
        isEndValue = endValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private boolean isEndValue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
