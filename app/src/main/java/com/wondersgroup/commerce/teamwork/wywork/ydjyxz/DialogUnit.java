package com.wondersgroup.commerce.teamwork.wywork.ydjyxz;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2016/5/10.
 */
public class DialogUnit {


    /**
     * 选择对话框
     * @param mContext
     * @param iconId 图标
     * @param title 标题
     * @param datas 选项列表
     * @param onClickDialog 响应事件
     */
    public static void chooseDialog(Context mContext,int iconId,String title,String[] datas,
            DialogInterface.OnClickListener onClickDialog){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if(iconId!=0) {
            builder.setIcon(iconId);
        }
        if(title!=null) {
            builder.setTitle(title);
        }
        builder.setItems(datas, onClickDialog);
        builder.show();
    }
}
