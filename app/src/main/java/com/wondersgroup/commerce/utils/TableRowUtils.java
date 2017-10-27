package com.wondersgroup.commerce.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.ItemAddActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.AttachBean;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.widget.TableRow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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

    private TableRow newTableRow(final int i) {
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
            case "4":
                builder.title(data.getName());
                if (data.getRemark() != null && data.getRemark().size() != 0){
                    builder.content(data.getRemark().get(0).getValue())
                            .textColor(R.color.blue)
                            .tag(data.getRemark().get(0).getKey());
                }
                break;
            case "7":
                builder.title(data.getName())
                        .content(data.getValue())
                        .input("请输入");
                if ("1".equals(data.getRequired()))
                    builder.required();
                break;
            case "8":
                builder.title(data.getName())
                        .content(data.getValue())
                        .textColor(R.color.blue)
                        .tag(data.getRemark());
                break;
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
            case "4":
//                tableRow.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        getDoc(tableRow.getTag().toString());
//                    }
//                });
                if (tableRow.getTag() != null){
                    tableRow.setSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            getDoc(tableRow.getTag().toString());
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
            case "8":
                tableRow.setSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        Intent intent = new Intent(context, ItemAddActivity.class);
                        intent.putExtra(Constants.TYPE, "dataVolume");
                        intent.putExtra("list", (Serializable) tableRow.getTag());
                        context.startActivity(intent);
                    }
                });
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

    public void getDoc(String attachId){
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(context).build();
        loadingDialog.show();
        HashMap<String, String> param = new HashMap<>();
        param.put(Constants.WS_CODE_REQ, "03010016");
        param.put("attachId", attachId);
        String url = CaseApi.URL_CASE_1 + CaseApi.DOWNLOAD_WRIT_FILE_BY_POST;
        Call<Result<AttachBean>> call = ApiManager.caseApi.downloadDoc(url, param);
        call.enqueue(new Callback<Result<AttachBean>>() {
            @Override
            public void onResponse(Response<Result<AttachBean>> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                if(200 == response.body().getCode()){
                    FileUtils fileUtils = new FileUtils();
                    try {
                        fileUtils.decoderBase64File(context,
                                response.body().getObject().getAttachFileStr(),
                                context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                                        + "/"+response.body().getObject().getAttachName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
