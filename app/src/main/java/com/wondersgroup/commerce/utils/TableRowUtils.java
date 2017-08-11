package com.wondersgroup.commerce.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;
import com.wondersgroup.commerce.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yclli on 16/10/26.
 */

public class TableRowUtils {

    private Context context;
    private LinearLayout layout;
    private List<DataVolume> dataList;
    private List<TableRow> tableRowList;
    private boolean isNeedTime;

    public TableRowUtils(Context mContext, LinearLayout parentLayout, List<DataVolume> dataList) {
        this.context = mContext;
        this.layout = parentLayout;
        this.dataList = dataList;
        this.tableRowList = new ArrayList<>();
        this.isNeedTime = true;
    }

    public TableRowUtils(Context mContext, LinearLayout parentLayout, List<DataVolume> dataList, boolean isNeedTime) {
        this.context = mContext;
        this.layout = parentLayout;
        this.dataList = dataList;
        this.tableRowList = new ArrayList<>();
        this.isNeedTime = isNeedTime;
    }

    public void build() {
        if (layout == null || dataList == null || dataList.size() == 0) {
            return;
        }
        for (int i = 0; i < dataList.size(); i++) {
            TableRow temp = newTableRow(i);
            tableRowList.add(temp);
            layout.addView(temp);
        }
    }

    private TableRow newTableRow(int i) {
        final DataVolume data = dataList.get(i);
        final String type = data.getType();
        TableRow.Builder builder = new TableRow.Builder(context);
        switch (type) {
            case "0":
                builder.title(data.getName())
                        .content(data.getValue());
                break;
            case "1":
                builder.title(data.getName())
                        .content(data.getValue())
                        .input("请输入" + data.getName());
                if ("1".equals(data.getRequired()))
                    builder.required();
                break;
            case "2":
            case "5":
                builder.title(data.getName())
                        .content(data.getValue())
                        .select("请选择" + data.getName());
                if ("1".equals(data.getRequired()))
                    builder.required();
                break;
            case "7":
                builder.title(data.getName())
                        .content(data.getValue())
                        .input("请输入");
                if ("1".equals(data.getRequired()))
                    builder.required();
            default:
                builder.title(data.getName())
                        .content(data.getValue());
                if ("1".equals(data.getRequired()))
                    builder.required();
                break;
        }
        final TableRow tableRow = builder.build();
        switch (type) {
            case "2":
                List<DataVolume> remark = data.getRemark();
                if (remark != null && remark.size() != 0) {
                    final String[] items = new String[remark.size()];
                    for (int j = 0; j < remark.size(); j++) {
                        items[j] = remark.get(j).getValue();
                    }
                    tableRow.setSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            new MaterialDialog.Builder(context)
                                    .title("请选择")
                                    .items(items)
                                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            if (text != null && text != "")
                                                tableRow.setContent(text.toString());
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .show();
                        }
                    });
                }
                break;
            case "5":
                tableRow.setSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        CusDatePickerDialog dateDialog = CusDatePickerDialog.newInstance("选择" + data.getName());
                        if (isNeedTime)
                            dateDialog.useTimePicker(true);
                        dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                            @Override
                            public void OnDateSet(String dateString) {
                                tableRow.setContent(dateString);
                            }
                        });
                        dateDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "HCSJ");
                    }
                });
                break;
        }

        return tableRow;
    }

    public String getContent(int pos) {
        if (tableRowList.get(pos) != null) {
            if ("1".equals(dataList.get(pos).getType()) || "7".equals(dataList.get(pos).getType()))
                return tableRowList.get(pos).getInput();
            else return tableRowList.get(pos).getContent();
        } else
            return "";
    }

    public void setContent(int pos, String str) {
        if (tableRowList.get(pos) != null) {
            if (dataList.get(pos).getType().equals("1"))
                tableRowList.get(pos).setInput(str);
            else tableRowList.get(pos).setContent(str);
        }
    }

}
