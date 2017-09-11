package com.wondersgroup.commerce.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.DownloadListActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.BaseRes;
import com.wondersgroup.commerce.model.FileBean;
import com.wondersgroup.commerce.model.NextPeoListBean;
import com.wondersgroup.commerce.model.ReceiveDetailBean;
import com.wondersgroup.commerce.model.SendDetailBean;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.gwpy.CheckListActivity;
import com.wondersgroup.commerce.teamwork.gwpy.PeopleListActivity;
import com.wondersgroup.commerce.widget.InfoCheckRow;
import com.wondersgroup.commerce.widget.InfoSelectRow;
import com.wondersgroup.commerce.widget.MyProgressDialog;
import com.wondersgroup.commerce.widget.TableRowView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PyFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.pyInfoLayout)LinearLayout pyInfoLayout;
    @Bind(R.id.pyHandleLayout)LinearLayout pyHandleLayout;
    @Bind(R.id.pySendLayout)LinearLayout pySendLayout;
    @Bind(R.id.fragment_py_suggestion) EditText suggestionEdit;
    @Bind(R.id.fragment_py_send) Button sendBtn;

    private static final String ARG_PARAM1 = "type";
    private static final String ARG_PARAM2 = "param2";

    private String type;
    private String mParam2;

    //公文批阅部分：参数
    private Map<String, String> map;
    private String docType;
    private String actName;
    private List<TableRowView> pyInfoRows;
    private String[]  infoNames;
    private InfoCheckRow isUrgencyView = null;
    private InfoCheckRow isOpenView = null;
    private InfoSelectRow resultView = null;
    private InfoSelectRow peoView = null;
    private InfoSelectRow peo2View = null;
    //办理结果选择的位置
    private int pos = -1;
    private String blPeoName = "";
    private String blPeoName2 = "";
    private List<Map<String, String>> chooseList = new ArrayList<Map<String, String>>();
    private List<Map<String, String>> chooseList2 = new ArrayList<Map<String, String>>();

    private TotalLoginBean loginBean;
    private String userId;
    public PyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PyFragment newInstance(String param1, String param2) {
        PyFragment fragment = new PyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        userId = loginBean.getResult().getUserId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_py, container, false);
        ButterKnife.bind(this, view);
        map = Hawk.get("InfoMap");
        if("SPYM".equals(type))
            docType = Hawk.get("docType");
        else if("GWXQ".equals(type))
            docType = Hawk.get("gwType");
        actName = Hawk.get("actName");
        if("收文管理".equals(docType))
            infoNames = getResources().getStringArray(R.array.receive_info_name);
        else
            infoNames = getResources().getStringArray(R.array.send_info_name);
        pyInfoRows = new ArrayList<>();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if("SPYM".equals(type)){
            if("收文管理".equals(docType)){
                pyInfoLayout.addView(TableRowView.newTitleRow(getActivity(), R.color.blue, "收文审批[一般文件]【当前环节：" + actName + "】"));
                for(int i=0; i<infoNames.length-1; i++){
                    TableRowView view = TableRowView.newTableRow(getActivity(), infoNames[i], "", true);
                    pyInfoRows.add(view);
                    pyInfoLayout.addView(view);
                }

                pyHandleLayout.addView(TableRowView.newTitleRow(getActivity(), R.color.blue, "当前环节办理"));
                pySendLayout.setVisibility(View.VISIBLE);

            }else if("发文管理".equals(docType)){
                pyInfoLayout.addView(TableRowView.newTitleRow(getActivity(), R.color.blue, "发文审批【局发文】【当前环节：" + actName + "】"));
                for(int i=0; i<infoNames.length-1; i++){
                    TableRowView view = TableRowView.newTableRow(getActivity(), infoNames[i], "", true);
                    pyInfoRows.add(view);
                    pyInfoLayout.addView(view);
                }
                pyHandleLayout.addView(TableRowView.newTitleRow(getActivity(), R.color.blue, "当前环节办理"));
                pySendLayout.setVisibility(View.VISIBLE);
            }
            getData();
        }else if("GWXQ".equals(type)){
            if("收文管理".equals(docType)){
                for(int i=0; i<infoNames.length-1; i++){
                    TableRowView view = TableRowView.newTableRow(getActivity(), infoNames[i], "", true);
                    pyInfoRows.add(view);
                    pyInfoLayout.addView(view);
                }
            }else if("发文管理".equals(docType)){
                for(int i=0; i<infoNames.length-1; i++){
                    TableRowView view = TableRowView.newTableRow(getActivity(), infoNames[i], "", true);
                    pyInfoRows.add(view);
                    pyInfoLayout.addView(view);
                }
            }
            getData();
        }
    }

    public void getData(){
        if("SPYM".equals(type)) {
            MyProgressDialog.show(getActivity());
            if ("收文管理".equals(docType)) {
                map.put("wsCodeReq", "07010008");
                map.put("userId", loginBean.getResult().getUserId());
                Call<ReceiveDetailBean> call = ApiManager.oaApi.apiHandleReceive(map);
                call.enqueue(new Callback<ReceiveDetailBean>() {
                    @Override
                    public void onResponse(Response<ReceiveDetailBean> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            if (response.body().getResult() != null) {
                                ReceiveDetailBean.receiveDetailBean = response.body();
                                updateData();
                            } else {
                                ReceiveDetailBean.receiveDetailBean = null;
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            MyProgressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                        MyProgressDialog.dismiss();
                    }
                });
            } else if ("发文管理".equals(docType)) {
                map.put("wsCodeReq", "07010006");
                map.put("userId", userId);
                Call<SendDetailBean> call = ApiManager.oaApi.apiHandleSend(map);
                call.enqueue(new Callback<SendDetailBean>() {
                    @Override
                    public void onResponse(Response<SendDetailBean> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            if (response.body().getResult() != null) {
                                SendDetailBean.sendDetailBean = response.body();
                                updateData();
                            } else {
                                SendDetailBean.sendDetailBean = null;
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            MyProgressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                        MyProgressDialog.dismiss();
                    }
                });
            }
        }else if("GWXQ".equals(type)){
            MyProgressDialog.show(getActivity());
            Map<String, String> body = new HashMap<>();
            if ("收文管理".equals(docType)) {
                body.put("wsCodeReq", "07010009");
                body.put("docId", (String) Hawk.get("gwId"));
                Call<ReceiveDetailBean> call = ApiManager.oaApi.apiGetDocReceiveDetail(body);
                call.enqueue(new Callback<ReceiveDetailBean>() {
                    @Override
                    public void onResponse(Response<ReceiveDetailBean> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            if (response.body().getResult() != null) {
                                ReceiveDetailBean.receiveDetailBean = response.body();
                                updateData();
                            } else {
                                ReceiveDetailBean.receiveDetailBean = null;
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            MyProgressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                        MyProgressDialog.dismiss();
                    }
                });
            }else if("发文管理".equals(docType)){
                body.put("wsCodeReq", "07010007");
                body.put("docId", (String) Hawk.get("gwId"));
                Call<SendDetailBean> call = ApiManager.oaApi.apiGetDocSendDetail(body);
                call.enqueue(new Callback<SendDetailBean>() {
                    @Override
                    public void onResponse(Response<SendDetailBean> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            if (response.body().getResult() != null) {
                                SendDetailBean.sendDetailBean = response.body();
                                updateData();
                            } else {
                                SendDetailBean.sendDetailBean = null;
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            MyProgressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                        MyProgressDialog.dismiss();
                    }
                });
            }
        }
    }

    //填充页面数据
    public void updateData(){
        if("SPYM".equals(type)) {
            if ("收文管理".equals(docType)) {
                //信息显示部分
                ReceiveDetailBean.Result data = ReceiveDetailBean.receiveDetailBean.getResult();
                pyInfoRows.get(0).setContent(data.getDocReceiveVo().getOpertype());
                pyInfoRows.get(1).setContent(data.getDocReceiveVo().getUrgencyStatus());
                pyInfoRows.get(2).setContent(data.getDocReceiveVo().getIsindicate());
                pyInfoRows.get(3).setContent(data.getDocReceiveVo().getDocno());
                pyInfoRows.get(4).setContent(data.getDocReceiveVo().getIsMsign());
                pyInfoRows.get(5).setContent(data.getDocReceiveVo().getRecnum());
                pyInfoRows.get(6).setContent(data.getDocReceiveVo().getRecdate().split(" ")[0]);
                pyInfoRows.get(7).setContent(data.getDocReceiveVo().getTitle());
                pyInfoRows.get(8).setContent(data.getDocReceiveVo().getRegorg());
                pyInfoRows.get(9).setContent(data.getDocReceiveVo().getRemark());
                TableRowView fileView = TableRowView.newSelectRow(getActivity(), "正文",
                        data.getDocAttachFile().getAttachName(), false, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getDoc();
                    }
                });
                fileView.setHintColor(ContextCompat.getColor(getActivity(), R.color.blue));
                pyInfoLayout.addView(fileView);

                //审批处理部分
                if("1".equals(data.getIsUrgency())){
                    isUrgencyView = new InfoCheckRow(getActivity());
                    isUrgencyView.setText("加急办理");
                    pyHandleLayout.addView(isUrgencyView);
                    isUrgencyView.setOnClickListener(this);
                }
                if("1".equals(data.getIsOpen())){
                    isOpenView = new InfoCheckRow(getActivity());
                    isOpenView.setText("公开查阅");
                    pyHandleLayout.addView(isOpenView);
                    isOpenView.setOnClickListener(this);
                }
                resultView = new InfoSelectRow(getActivity());
                resultView.setTitle("办理结果");
                resultView.setTextHint("请选择");
                pyHandleLayout.addView(resultView);
                resultView.setOnClickListener(this);

            } else if ("发文管理".equals(docType)) {
                //信息显示部分
                final SendDetailBean.Result data = SendDetailBean.sendDetailBean.getResult();
                pyInfoRows.get(0).setContent(data.getDocSendVo().getDocclassname());
                pyInfoRows.get(1).setContent(data.getDocSendVo().getOpentype());
                if(!"主动公开".equals(data.getDocSendVo().getOpentype()))
                    pyInfoRows.get(2).setContent(data.getDocSendVo().getReason());
                else
                    pyInfoRows.get(2).setVisibility(View.GONE);
                pyInfoRows.get(3).setContent(data.getDocSendVo().getDocnoname());
                pyInfoRows.get(4).setContent(data.getDocSendVo().getPrtnumber());
                pyInfoRows.get(5).setContent(data.getDocSendVo().getUrgencytype());
                pyInfoRows.get(6).setContent(data.getDocSendVo().getIsnorm());
                pyInfoRows.get(7).setContent(data.getDocSendVo().getIsopen());
                pyInfoRows.get(8).setContent(data.getDocSendVo().getSeclevel());
                pyInfoRows.get(9).setContent(data.getDocSendVo().getIsInterpretation());
                pyInfoRows.get(10).setContent(data.getDocSendVo().getNoInterpreReason());
                pyInfoRows.get(11).setContent(data.getDocSendVo().getTitle());
                pyInfoRows.get(12).setContent(data.getDocSendVo().getDraftdeptname());
                pyInfoRows.get(13).setContent(data.getDocSendVo().getDrafter());
                pyInfoRows.get(14).setContent(data.getDocSendVo().getDraftdate());
                pyInfoRows.get(15).setContent(data.getDocSendVo().getSigner());
                pyInfoRows.get(16).setContent(data.getDocSendVo().getSigndate());
                pyInfoRows.get(17).setContent(data.getDocSendVo().getMainfor());
                pyInfoRows.get(18).setContent(data.getDocSendVo().getCopyfor());
                pyInfoRows.get(19).setContent(data.getDocSendVo().getCopyfor2());
                pyInfoRows.get(20).setContent(data.getDocSendVo().getRemark());
                TableRowView fileView = TableRowView.newSelectRow(getActivity(), "正文",
                        data.getDocAttachFile().getAttachName(), false, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getDoc();
                            }
                        });
                fileView.setHintColor(ContextCompat.getColor(getActivity(), R.color.blue));
                pyInfoLayout.addView(fileView);

                InfoSelectRow fileListv1 = new InfoSelectRow(getActivity());
                fileListv1.setTitle("附件");
                fileListv1.setText("共"+data.getDocAttachVoList().size()+"份");
                fileListv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(data.getDocAttachVoList().size()>0) {
                            Intent intent = new Intent(getActivity(), DownloadListActivity.class);
                            Hawk.put("fjlist", data.getDocAttachVoList());
                            intent.putExtra("title", "附件");
                            startActivity(intent);
                        }
                    }
                });
                pyInfoLayout.addView(fileListv1);
                InfoSelectRow fileListv2 = new InfoSelectRow(getActivity());
                fileListv2.setTitle("解读材料");
                fileListv2.setText("共"+data.getDocAttachInterpreVoList().size()+"份");
                fileListv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(data.getDocAttachInterpreVoList().size()>0) {
                            Intent intent = new Intent(getActivity(), DownloadListActivity.class);
                            Hawk.put("fjlist", data.getDocAttachInterpreVoList());
                            intent.putExtra("title", "解读材料");
                            startActivity(intent);
                        }
                    }
                });
                pyInfoLayout.addView(fileListv2);

                //审批处理部分
                if("1".equals(data.getIsUrgency())){
                    isUrgencyView = new InfoCheckRow(getActivity());
                    isUrgencyView.setText("加急办理");
                    pyHandleLayout.addView(isUrgencyView);
                    isUrgencyView.setOnClickListener(this);
                }
                resultView = new InfoSelectRow(getActivity());
                resultView.setTitle("办理结果");
                resultView.setTextHint("请选择");
                pyHandleLayout.addView(resultView);
                resultView.setOnClickListener(this);

            }
        }else if("GWXQ".equals(type)) {
            if ("收文管理".equals(docType)) {
                //信息显示部分
                ReceiveDetailBean.Result data = ReceiveDetailBean.receiveDetailBean.getResult();
                pyInfoRows.get(0).setContent(data.getDocReceiveVo().getOpertype());
                pyInfoRows.get(1).setContent(data.getDocReceiveVo().getUrgencyStatus());
                pyInfoRows.get(2).setContent(data.getDocReceiveVo().getIsindicate());
                pyInfoRows.get(3).setContent(data.getDocReceiveVo().getDocno());
                pyInfoRows.get(4).setContent(data.getDocReceiveVo().getIsMsign());
                pyInfoRows.get(5).setContent(data.getDocReceiveVo().getRecnum());
                pyInfoRows.get(6).setContent(data.getDocReceiveVo().getRecdate().split(" ")[0]);
                pyInfoRows.get(7).setContent(data.getDocReceiveVo().getTitle());
                pyInfoRows.get(8).setContent(data.getDocReceiveVo().getRegorg());
                pyInfoRows.get(9).setContent(data.getDocReceiveVo().getRemark());
                TableRowView fileView = TableRowView.newSelectRow(getActivity(), "正文",
                        data.getDocAttachFile().getAttachName(), false, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getDoc();
                            }
                        });
                fileView.setHintColor(ContextCompat.getColor(getActivity(), R.color.blue));
                pyInfoLayout.addView(fileView);
            }else if ("发文管理".equals(docType)) {
                //信息显示部分
                final SendDetailBean.Result data = SendDetailBean.sendDetailBean.getResult();
                pyInfoRows.get(0).setContent(data.getDocSendVo().getDocclassname());
                pyInfoRows.get(1).setContent(data.getDocSendVo().getOpentype());
                if (!"主动公开".equals(data.getDocSendVo().getOpentype()))
                    pyInfoRows.get(2).setContent(data.getDocSendVo().getReason());
                else
                    pyInfoRows.get(2).setVisibility(View.GONE);
                pyInfoRows.get(3).setContent(data.getDocSendVo().getDocnoname());
                pyInfoRows.get(4).setContent(data.getDocSendVo().getPrtnumber());
                pyInfoRows.get(5).setContent(data.getDocSendVo().getUrgencytype());
                pyInfoRows.get(6).setContent(data.getDocSendVo().getIsnorm());
                pyInfoRows.get(7).setContent(data.getDocSendVo().getIsopen());
                pyInfoRows.get(8).setContent(data.getDocSendVo().getSeclevel());
                pyInfoRows.get(9).setContent(data.getDocSendVo().getIsInterpretation());
                pyInfoRows.get(10).setContent(data.getDocSendVo().getNoInterpreReason());
                pyInfoRows.get(11).setContent(data.getDocSendVo().getTitle());
                pyInfoRows.get(12).setContent(data.getDocSendVo().getDraftdeptname());
                pyInfoRows.get(13).setContent(data.getDocSendVo().getDrafter());
                pyInfoRows.get(14).setContent(data.getDocSendVo().getDraftdate());
                pyInfoRows.get(15).setContent(data.getDocSendVo().getSigner());
                pyInfoRows.get(16).setContent(data.getDocSendVo().getSigndate());
                pyInfoRows.get(17).setContent(data.getDocSendVo().getMainfor());
                pyInfoRows.get(18).setContent(data.getDocSendVo().getCopyfor());
                pyInfoRows.get(19).setContent(data.getDocSendVo().getCopyfor2());
                pyInfoRows.get(20).setContent(data.getDocSendVo().getRemark());
                TableRowView fileView = TableRowView.newSelectRow(getActivity(), "正文",
                        data.getDocAttachFile().getAttachName(), false, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getDoc();
                            }
                        });
                fileView.setHintColor(ContextCompat.getColor(getActivity(), R.color.blue));
                pyInfoLayout.addView(fileView);

                InfoSelectRow fileListv1 = new InfoSelectRow(getActivity());
                fileListv1.setTitle("附件");
                fileListv1.setText("共"+data.getDocAttachVoList().size()+"份");
                fileListv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(data.getDocAttachVoList().size()>0) {
                            Intent intent = new Intent(getActivity(), DownloadListActivity.class);
                            Hawk.put("fjlist", data.getDocAttachVoList());
                            intent.putExtra("title", "附件");
                            startActivity(intent);
                        }
                    }
                });
                pyInfoLayout.addView(fileListv1);
                InfoSelectRow fileListv2 = new InfoSelectRow(getActivity());
                fileListv2.setTitle("解读材料");
                fileListv2.setText("共"+data.getDocAttachInterpreVoList().size()+"份");
                fileListv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(data.getDocAttachInterpreVoList().size()>0) {
                            Intent intent = new Intent(getActivity(), DownloadListActivity.class);
                            Hawk.put("fjlist", data.getDocAttachInterpreVoList());
                            intent.putExtra("title", "解读材料");
                            startActivity(intent);
                        }
                    }
                });
                pyInfoLayout.addView(fileListv2);
            }
        }
    }
    //下载正文
    public void getDoc(){
        Map<String, String> body = new HashMap<>();
        body.put("wsCodeReq", "07010013");
        if("收文管理".equals(docType))
            body.put("attachId", ReceiveDetailBean.receiveDetailBean.getResult().getDocAttachFile().getAttachId());
        else
            body.put("attachId", SendDetailBean.sendDetailBean.getResult().getDocAttachFile().getAttachId());
        Call<FileBean> call = ApiManager.oaApi.apiDownload(body);
        call.enqueue(new Callback<FileBean>() {
            @Override
            public void onResponse(Response<FileBean> response, Retrofit retrofit) {
                if(response!=null && response.body()!=null){
                    if("200".equals(response.body().getCode())){
//                        FileUtils fileUtils = new FileUtils();
//                        try {
//                            fileUtils.decoderBase64File(getActivity(),
//                                    response.body().getResult().getAttachFile().getAttachFileStr(),
//                                    getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
//                                            + "/"+response.body().getResult().getAttachFile().getAttachName());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                    }else{
                        Toast.makeText(getActivity(), "下载失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v==isUrgencyView){
            isUrgencyView.setCheckBox();
        }else if(v==isOpenView){
            isOpenView.setCheckBox();
        }else if(v==resultView){
            intent = new Intent(getActivity(), CheckListActivity.class);
            intent.putExtra("type", "BLJG");
            intent.putExtra("docType", docType);
            startActivityForResult(intent, 1);
        }else if(v==peoView){
            intent = new Intent(getActivity(), PeopleListActivity.class);
            if("发文管理".equals(docType)){
                if(peo2View==null || peo2View.getVisibility()==View.GONE) {
                    intent.putExtra("level", 0);
                    intent.putExtra("authority", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthority());
                    intent.putExtra("userflag", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getUserflag());
                    intent.putExtra("authId", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthId());
                }else {
                    intent.putExtra("level", 0);
                    intent.putExtra("authority", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthority().split(";")[0]);
                    intent.putExtra("userflag", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getUserflag().split(";")[0]);
                    intent.putExtra("authId", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthId().split(";")[0]);
                }
            }else{
                if(peo2View==null || peo2View.getVisibility() == View.GONE) {
                    intent.putExtra("level", 0);
                    intent.putExtra("authority", ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthority());
                    intent.putExtra("userflag", ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getUserflag());
                    intent.putExtra("authId", ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthId());
                }else {
                    intent.putExtra("level", 0);
                    intent.putExtra("authority", ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthority().split(";")[0]);
                    intent.putExtra("userflag", ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getUserflag().split(";")[0]);
                    intent.putExtra("authId", ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthId().split(";")[0]);
                }
            }
            startActivityForResult(intent, 2);
        }else if(v==peo2View){
            intent = new Intent(getActivity(), PeopleListActivity.class);
            if("发文管理".equals(docType)){
                intent.putExtra("level", 0);
                intent.putExtra("isTwo", 1);
                intent.putExtra("authority", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthority().split(";")[1]);
                intent.putExtra("userflag", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getUserflag().split(";")[1]);
                intent.putExtra("authId", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthId().split(";")[1]);
            }else{
                intent.putExtra("level", 0);
                intent.putExtra("isTwo", 1);
                intent.putExtra("authority", ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthority().split(";")[1]);
                intent.putExtra("userflag", ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getUserflag().split(";")[1]);
                intent.putExtra("authId", ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getAuthId().split(";")[1]);
            }
            startActivityForResult(intent, 3);
        }
    }

    @OnClick(R.id.fragment_py_send)
    public void onSendClick(View v){
        if(pos<0){
            Toast.makeText(getActivity(), "请选择办理结果", Toast.LENGTH_SHORT).show();
            return;
        }else if(peoView!=null&&peoView.getVisibility()==View.VISIBLE&&chooseList.size()==0 ||
                peo2View!=null&&peo2View.getVisibility()==View.VISIBLE&&chooseList2.size()==0){
            Toast.makeText(getActivity(), "请选择后续操作对象", Toast.LENGTH_SHORT).show();
            return;
        }else if("".equals(suggestionEdit.getText())){
            Toast.makeText(getActivity(), "请输入办理意见", Toast.LENGTH_SHORT).show();
            return;
        }
        sendBtn.setEnabled(false);
        if("发文管理".equals(docType)){
            SendDetailBean.Transact transact = SendDetailBean.sendDetailBean.getTransact();

            map.remove("wsCodeReq");
            map.put("wsCodeReq", "07010014");
            map.put("deptId", "13000000001");
            if(isUrgencyView!=null && isUrgencyView.getVisibility()==View.VISIBLE && isUrgencyView.getCheckBox()==false){
                map.put("isUrgency", "0");
            }else if(isUrgencyView!=null && isUrgencyView.getVisibility()==View.VISIBLE && isUrgencyView.getCheckBox()==true){
                map.put("isUrgency", "1");
            }
            map.put("transactId", transact.getTransactId());
            map.put("leader", transact.getLeader());
            map.put("nextnodeId", SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getOperationId());
            if(peoView!=null && peoView.getVisibility()==View.VISIBLE){
                String str = "";
                for(int i=0; i<chooseList.size(); i++){
                    str = str + chooseList.get(i).get("id") + ",";
                }

                if(peo2View!=null && peo2View.getVisibility()==View.VISIBLE){
                    str = str.substring(0, str.length()-1) + ";";
                    for(int i=0; i<chooseList2.size(); i++){
                        str = str + chooseList2.get(i).get("id") + ",";
                    }
                }
                map.put("nextuserId", str.substring(0, str.length()-1));
            }

            map.put("transactopinion", suggestionEdit.getText().toString());
            Call<BaseRes> call = ApiManager.oaApi.apiSendSave(map);
            call.enqueue(new Callback<BaseRes>() {
                @Override
                public void onResponse(Response<BaseRes> response, Retrofit retrofit) {
                    if(response.body()!=null){
                        if("200".equals(response.body().getCode())){
                            Toast.makeText(getActivity(), "操作成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            getActivity().setResult(3, intent);
                            getActivity().finish();
                        }else {
                            Toast.makeText(getActivity(), "操作失败，请重试", Toast.LENGTH_SHORT).show();
                            sendBtn.setEnabled(true);
                        }
                    }else{
                        Toast.makeText(getActivity(), "服务器返回数据失败", Toast.LENGTH_SHORT).show();
                        sendBtn.setEnabled(true);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                    sendBtn.setEnabled(true);
                }
            });
        }else{
            ReceiveDetailBean.Result.Transact transact = ReceiveDetailBean.receiveDetailBean.getResult().getTransact();

            map.remove("wsCodeReq");
            map.put("wsCodeReq", "07010015");
            map.put("deptId", "13000000001");
            if(isUrgencyView!=null && isUrgencyView.getVisibility()==View.VISIBLE && isUrgencyView.getCheckBox()==false){
                map.put("isUrgency", "0");
            }else if(isUrgencyView!=null && isUrgencyView.getVisibility()==View.VISIBLE && isUrgencyView.getCheckBox()==true){
                map.put("isUrgency", "1");
            }
            map.put("transactId", transact.getTransactId());
            map.put("leader", transact.getLeader());
            map.put("nextnodeId",   ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getOperationId());
            if(peoView!=null && peoView.getVisibility()==View.VISIBLE){
                String str = "";
                for(int i=0; i<chooseList.size(); i++){
                    str = str + chooseList.get(i).get("id") + ",";
                }

                if(peo2View!=null && peo2View.getVisibility()==View.VISIBLE){
                    str = str.substring(0, str.length()-1) + ";";
                    for(int i=0; i<chooseList2.size(); i++){
                        str = str + chooseList2.get(i).get("id") + ",";
                    }
                }
                map.put("nextuserId", str.substring(0, str.length()-1));
            }
            if(isOpenView!=null && isOpenView.getVisibility()==View.VISIBLE && isOpenView.getCheckBox()==false){
                map.put("isOpen", "0");
            }else if(isOpenView!=null && isOpenView.getVisibility()==View.VISIBLE && isOpenView.getCheckBox()==true){
                map.put("isOpen", "1");
            }
            map.put("transactopinion", suggestionEdit.getText().toString());
            Call<BaseRes> call = ApiManager.oaApi.apiReceiveSave(map);
            call.enqueue(new Callback<BaseRes>() {
                @Override
                public void onResponse(Response<BaseRes> response, Retrofit retrofit) {
                    if(response.body()!=null){
                        if("200".equals(response.body().getCode())){
                            Toast.makeText(getActivity(), "操作成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            getActivity().setResult(3, intent);
                            getActivity().finish();
                        }else {
                            Toast.makeText(getActivity(), "操作失败，请重试", Toast.LENGTH_SHORT).show();
                            sendBtn.setEnabled(true);
                        }
                    }else{
                        Toast.makeText(getActivity(), "服务器返回数据失败", Toast.LENGTH_SHORT).show();
                        sendBtn.setEnabled(true);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                    sendBtn.setEnabled(true);
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(data!=null){
                String result = data.getStringExtra("result");
                resultView.setText(result);
                pos = data.getIntExtra("position", 0);
                if("发文管理".equals(docType)){
                    if("1".equals(SendDetailBean.sendDetailBean.getResult()
                            .getDicFlowOperationList().get(pos).getSelected())){
                        if(peoView==null){
                            peoView = new InfoSelectRow(getActivity());
                            peoView.setTitle("后续操作对象");
                            blPeoName = SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName()+"：";
                            peoView.setTextHint(blPeoName);
                            pyHandleLayout.addView(peoView);
                            peoView.setOnClickListener(this);
                        }else{
                            peoView.setVisibility(View.VISIBLE);
                            blPeoName = SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName()+"：";
                            peoView.setTextHint(blPeoName);
                            peoView.setText("");
                        }
                        if(peo2View!=null)  peo2View.setVisibility(View.GONE);
                    }else if("1;1".equals(SendDetailBean.sendDetailBean.getResult()
                            .getDicFlowOperationList().get(pos).getSelected())){
                        if(peoView==null){
                            peoView = new InfoSelectRow(getActivity());
                            peoView.setTitle("后续操作对象");
                            blPeoName = SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName().split(";")[0]+"：";
                            peoView.setTextHint(blPeoName);
                            pyHandleLayout.addView(peoView);
                            peoView.setOnClickListener(this);
                        }else{
                            peoView.setVisibility(View.VISIBLE);
                            blPeoName = SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName().split(";")[0]+"：";
                            peoView.setTextHint(blPeoName);
                            peoView.setText("");
                        }
                        if(peo2View==null){
                            peo2View = new InfoSelectRow(getActivity());
                            peo2View.setTitle("后续操作对象");
                            blPeoName2 = SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName().split(";")[1]+"：";
                            peo2View.setTextHint(blPeoName2);
                            pyHandleLayout.addView(peo2View);
                            peo2View.setOnClickListener(this);
                        }else{
                            peo2View.setVisibility(View.VISIBLE);
                            blPeoName2 = SendDetailBean.sendDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName().split(";")[1]+"：";
                            peo2View.setTextHint(blPeoName2);
                            peo2View.setText("");
                        }
                    }else{
                        if(peoView!=null) peoView.setVisibility(View.GONE);
                        if(peo2View!=null) peo2View.setVisibility(View.GONE);
                    }
                }else if("收文管理".equals(docType)){
                    if("1".equals(ReceiveDetailBean.receiveDetailBean.getResult()
                            .getDicFlowOperationList().get(pos).getSelected())){
                        if(peoView==null){
                            peoView = new InfoSelectRow(getActivity());
                            peoView.setTitle("后续操作对象");
                            blPeoName = ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName()+"：";
                            peoView.setTextHint(blPeoName);
                            pyHandleLayout.addView(peoView);
                            peoView.setOnClickListener(this);
                        }else{
                            peoView.setVisibility(View.VISIBLE);
                            blPeoName = ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName()+"：";
                            peoView.setTextHint(blPeoName);
                            peoView.setText("");
                        }
                        if(peo2View!=null) peo2View.setVisibility(View.GONE);
                    }else if("1;1".equals(ReceiveDetailBean.receiveDetailBean.getResult()
                            .getDicFlowOperationList().get(pos).getSelected())){
                        if(peoView==null){
                            peoView = new InfoSelectRow(getActivity());
                            peoView.setTitle("后续操作对象");
                            blPeoName = ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName().split(";")[0]+"：";
                            peoView.setTextHint(blPeoName);
                            pyHandleLayout.addView(peoView);
                            peoView.setOnClickListener(this);
                        }else{
                            peoView.setVisibility(View.VISIBLE);
                            blPeoName = ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName().split(";")[0]+"：";
                            peoView.setTextHint(blPeoName);
                            peoView.setText("");
                        }
                        if(peo2View==null){
                            peo2View = new InfoSelectRow(getActivity());
                            peo2View.setTitle("后续操作对象");
                            blPeoName2 = ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName().split(";")[1]+"：";
                            peo2View.setTextHint(blPeoName2);
                            pyHandleLayout.addView(peo2View);
                            peo2View.setOnClickListener(this);
                        }else{
                            peo2View.setVisibility(View.VISIBLE);
                            blPeoName2 = ReceiveDetailBean.receiveDetailBean.getResult().getDicFlowOperationList().get(pos).getTransactName().split(";")[1]+"：";
                            peo2View.setTextHint(blPeoName2);
                            peo2View.setText("");
                        }
                    }else{
                        if(peoView!=null) peoView.setVisibility(View.GONE);
                        if(peo2View!=null) peo2View.setVisibility(View.GONE);
                    }
                }
            }
        }else if(requestCode==2){
            if(data != null) {
                for(int j=0; j< NextPeoListBean.chooseList.size(); j++){
                    Map<String, String> map = NextPeoListBean.chooseList.get(j);
                    boolean isNew = true;
                    for(int k=0; k<chooseList.size(); k++){
                        if(map.get("id").equals(chooseList.get(k).get("id")))
                            isNew = false;
                    }
                    if(isNew == true)
                        chooseList.add(map);
                }
                String temp = "";
                for(int i=0; i<chooseList.size(); i++){
                    temp = temp + chooseList.get(i).get("name")+",";
                }
                if(!"".equals(temp))
                    peoView.setText(blPeoName + temp.substring(0, temp.length() - 1));
            }
        }else if(requestCode==3){
            if(data != null) {
                for(int j=0; j<NextPeoListBean.chooseList2.size(); j++){
                    Map<String, String> map = NextPeoListBean.chooseList2.get(j);
                    boolean isNew = true;
                    for(int k=0; k<chooseList2.size(); k++){
                        if(map.get("id").equals(chooseList2.get(k).get("id")))
                            isNew = false;
                    }
                    if(isNew == true)
                        chooseList2.add(map);
                }
                String temp = "";
                for(int i=0; i<chooseList2.size(); i++){
                    temp = temp + chooseList2.get(i).get("name")+",";
                }
                if(!"".equals(temp))
                    peo2View.setText(blPeoName2 + temp.substring(0, temp.length()-1));
            }
        }
    }
}
