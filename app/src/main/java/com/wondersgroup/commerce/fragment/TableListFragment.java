package com.wondersgroup.commerce.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.UploadResBean;
import com.wondersgroup.commerce.model.ccjc.CCCheckResult;
import com.wondersgroup.commerce.model.ccjc.CCHCResult;
import com.wondersgroup.commerce.model.ccjc.CCXQResult;
import com.wondersgroup.commerce.model.ccjc.DicItem;
import com.wondersgroup.commerce.model.ccjc.DicResult;
import com.wondersgroup.commerce.model.ccjc.DicT;
import com.wondersgroup.commerce.model.yn.CaseBean;
import com.wondersgroup.commerce.model.yn.CnAccuse;
import com.wondersgroup.commerce.model.yn.CnBasic;
import com.wondersgroup.commerce.model.yn.CnContent;
import com.wondersgroup.commerce.model.yn.CnProcess;
import com.wondersgroup.commerce.model.yn.CnUpload;
import com.wondersgroup.commerce.model.yn.DealInfoBean;
import com.wondersgroup.commerce.model.yn.DicBean;
import com.wondersgroup.commerce.model.yn.UserInfoBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.mysupervision.AllDic;
import com.wondersgroup.commerce.utils.BitmapUtil;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.utils.DateUtil;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;
import com.wondersgroup.commerce.widget.ImageTableView;
import com.wondersgroup.commerce.widget.ListDialog;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.widget.TableRow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link TableListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * 抽查检查：企业基本信息、核查结果(实地核查)；投诉举报详情/查询
 */
public class TableListFragment extends Fragment implements ImageTableView.imagePickerListener {
    @BindView(R.id.table_list_root)
    LinearLayout rootLayout;
    @BindView(R.id.nodata_img)
    ImageView nodataImg;
    @BindView(R.id.layout_index)
    LinearLayout layoutIndex;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private static final String ARG_TYPE = "type";
    private static final String ARG_PARAM2 = "param2";

    private String type;
    private String mParam2;
    String hcDate;
    String qkCode;
    String clCode;
    private RootAppcation app;
    private DealInfoBean body;
    private TableRow regPer,jbr,tjjsrq,zyje,whjjss,jspc,jbpc,cljg,accType,tjjg,qqlx,clqd,isqz,xzwsh;
    private TableRow jzzk,sjlx,blbm,xzdw;
    private LinkedHashMap<String, String> isqzMap;

    private CnAccuse cnAccuse;
    private CnProcess cnProcess;
    private List<CnUpload> cnUploads;
    private ImageTableView fj;
    private AllDic allDic;
    private TotalLoginBean loginBean;
    private String userId,organId,deptId,caseId;
    private final static int IMG_PICKER = 10000;

    public TableListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TableListFragment.
     */

    public static TableListFragment newInstance(String type, String param2) {
        TableListFragment fragment = new TableListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        app = (RootAppcation) getActivity().getApplication();

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        userId = loginBean.getResult().getUserId();
        organId = loginBean.getResult().getOrganId();
        deptId = loginBean.getResult().getDeptId();
        caseId = Hawk.get("TSJBXQ_caseId");
        body = new DealInfoBean();
        body.setCnAccuse(new CnAccuse());
        body.getCnAccuse().setCaseId(caseId);
        body.setCnProcess(new CnProcess());
        body.getCnProcess().setCaseId(caseId);
        body.setLoginUserInfo(new UserInfoBean());
        body.getLoginUserInfo().setUserId(userId);
        body.getLoginUserInfo().setOrganId(organId);
        body.getLoginUserInfo().setDeptId(deptId);
        body.setCnUploads(new ArrayList<CnUpload>());

        isqzMap = new LinkedHashMap<String, String>();
        isqzMap.put("1","是");
        isqzMap.put("2","否");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_table_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(type.startsWith("TSXQ")||type.startsWith("JBXQ")) {
            getAllDic();
            getTSJBXQ();
        }else if("TSCL".equals(type)||"JBCL".equals(type)){//投诉处理、举报处理
            getAllDic();
            getTSJBCL();
        }else if("TSCX".equals(type)||"JBCX".equals(type)){
            getAllDic();
            final TableRow cusName=new TableRow.Builder(getContext())
                    .title(type.startsWith("TS")?"消费者":"举报者")
                    .input(type.startsWith("TS")?"请输入消费者姓名":"请输入消费者姓名")
                    .build();
            final TableRow accuName=new TableRow.Builder(getContext())
                    .title(type.startsWith("TS")?"被诉单位":"被举报单位")
                    .input(type.startsWith("TS")?"请输入被投诉单位名称":"请输入被举报单位名称")
                    .build();
            final TableRow caseId=new TableRow.Builder(getContext())
                    .title(type.startsWith("TS")?"投诉编号":"举报编号")
                    .input(type.startsWith("TS")?"请输入投诉编号":"请输入举报编号")
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .build();
            rootLayout.addView(cusName);
            rootLayout.addView(accuName);
            rootLayout.addView(caseId);
            Button btn=new Button(getContext());
            btn.setText("查询");
            btn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            LinearLayout.LayoutParams contentParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            contentParams.setMargins((int) DWZH.dp2pt(getActivity(), 16), (int) DWZH.dp2pt(getActivity(), 50), (int) DWZH.dp2pt(getActivity(), 16), 0);
            btn.setLayoutParams(contentParams);
            btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
            rootLayout.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), RecyclerActivity.class);
                    intent.putExtra("title",type.startsWith("TS")?"投诉进度查询":"举报进度查询");
                    intent.putExtra("type",type);
                    intent.putExtra("TSJBCX_basicName",cusName.getInput());
                    intent.putExtra("TSJBCX_accuseName",accuName.getInput());
                    intent.putExtra("TSJBCX_caseId",caseId.getInput());
                    startActivity(intent);
                }
            });
        }else if ("CLXX".equals(type)){
            getTSJBXQ();
        }else if("QYJBXX".equals(type)){//企业基本信息
            getQYJBXX();
        }else if("CCJG".equals(type) || "SDHC".equals(type)){
            getCCJG();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMG_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                for (ImageItem imageItem : images){
                    Uri uri = Uri.fromFile(new File(imageItem.path));
                    fj.addImage(uri);
                }
            }
        }
    }

    private void getCCJG(){
        String checkId=Hawk.get("CCJC_CheckId");
        Map<String,String> body=new HashMap<>();
        body.put("wsCodeReq","03010016");
        body.put("checkId",checkId);
        Call<CCCheckResult> call = ApiManager.ccjcApi.ccJG(body);
        call.enqueue(new Callback<CCCheckResult>() {
            @Override
            public void onResponse(Response<CCCheckResult> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    CCCheckResult result=response.body();
                    if("200".equals(result.getCode())){
                        nodataImg.setVisibility(View.GONE);
                        if("SDHC".equals(type)) {
                            geneSDHC(result.getResult());
                        }else if("CCJG".equals(type)){
                            addCCJG(result.getResult());
                        }
                    }else {
                        nodataImg.setVisibility(View.VISIBLE);
                    }
                }else {
                    nodataImg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void getQYJBXX(){
        String checkId=Hawk.get("CCJC_CheckId");
        Map<String,String> body=new HashMap<>();
        body.put("wsCodeReq","03010016");
        body.put("checkId",checkId);
        Call<CCXQResult> call=ApiManager.ccjcApi.ccXQ(body);
        call.enqueue(new Callback<CCXQResult>() {
            @Override
            public void onResponse(Response<CCXQResult> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    CCXQResult result=response.body();
                    if("200".equals(result.getCode())){
                        addQYJBXX(result.getResult());
                    }else {
                        Toast.makeText(getContext(),result.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getContext(),"获取企业基本信息失败",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(),"获取企业基本信息失败",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getTSJBXQ() {
        Call<CaseBean> call = ApiManager.ynApi.getCaseInfo(caseId,"0205000101");
        call.enqueue(new Callback<CaseBean>() {
            @Override
            public void onResponse(Response<CaseBean> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    CaseBean result = response.body();
                    if (result.getCode() == 200) {
                        if (type.startsWith("TSXQ")||type.startsWith("JBXQ")) {
                            addConsumer(result.getResult().getCnBasic());
                            addAccuser(result.getResult().getCnAccuse());
                            addContent(result.getResult().getCnAccuse(),result.getResult().getCnContent());
                            //// TODO: 17/8/1 暂时屏蔽
//                            if(result.getResult().getCnUploads()!=null&&type.endsWith("XQ")){
//                                addImage(result.getResult().getCnUploads());
//                            }
                        } else if ("CLXX".equals(type)) {
                            addProcess(result.getResult().getCnProcess(),result.getResult().getCnAccuse());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void geneSDHC(final CCCheckResult.Result r){
        CCCheckResult.CheckWayLocal checkWayLocal = new CCCheckResult().new CheckWayLocal();
        if (r!=null){
            checkWayLocal= r.getCheckWayLocal();
        }
        TableRow title=new TableRow.Builder(getContext())
                .asTitle("抽查信息")
                .build();
        rootLayout.addView(title);
        TableRow checkBatchName = new TableRow.Builder(getContext())
                .title("抽查批次")
                .content(checkWayLocal.getCheckBatchName())
                .build();
        rootLayout.addView(checkBatchName);
        TableRow title2=new TableRow.Builder(getContext())
                .asTitle("抽查结果记录")
                .build();
        rootLayout.addView(title2);
        TableRow checkOrganName = new TableRow.Builder(getContext())
                .title("核查实施机关")
                .content(checkWayLocal.getCheckOrganName())
                .build();
        rootLayout.addView(checkOrganName);
        final TableRow hcr;
        if ("云南".equals(RootAppcation.getInstance().getVersion())){
            hcr = new TableRow.Builder(getContext())
                    .title("核查人")
                    .content(checkWayLocal.getPersonName())
                    .required()
                    .build();
        }else {
            hcr = new TableRow.Builder(getContext())
                    .title("核查人")
                    .content(checkWayLocal.getCheckPerson())
                    .required()
                    .input("请输入核查人")
                    .build();
        }

        final TableRow hcsj = new TableRow.Builder(getContext())
                .title("核查时间")
                .content(checkWayLocal.getCheckDate())
                .required()
                .select("选择核查时间")
                .build();
        final TableRow hcjzr = new TableRow.Builder(getContext())
                .title("实地核查见证人")
                .content(checkWayLocal.getCheckOtherPerson())
                .input("输入实地核查见证人")
                .build();
        final TableRow hcqk = new TableRow.Builder(getContext())
                .title("核查情况")
                .content(checkWayLocal.getCheckResult())
                .required()
                .select("含核查结果")
                .build();
        clqd = new TableRow.Builder(getContext())
                .title("取得材料清单")
                .content("")
                .select("选择材料清单")
                .build();
        final TableRow bz = new TableRow.Builder(getContext())
                .title("备注")
                .content(checkWayLocal.getCheckMemo())
                .input("填写备注信息")
                .build();
        rootLayout.addView(hcr);
        rootLayout.addView(hcsj);
        rootLayout.addView(hcjzr);
        rootLayout.addView(hcqk);
        rootLayout.addView(clqd);
        rootLayout.addView(bz);
        final String append = checkWayLocal.getCheckAppendixList() == null ? "" : checkWayLocal.getCheckAppendixList();
        DicT dic = Hawk.get("Dic");
        if (dic == null) {
            Map<String, String> body = new HashMap<>();
            body.put("organId", organId);
            body.put("wsCodeReq", "020001");
            Call<DicResult> call = ApiManager.ccjcApi.ccDic(body);
            call.enqueue(new Callback<DicResult>() {
                @Override
                public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                    if (response.isSuccess() && response.body() != null) {
                        DicResult result = response.body();
                        if ("200".equals(result.getCode())) {
                            Hawk.put("Dic", result.getResult());
                            List<DicItem> apps = result.getResult().getDicCheckAppendixList();
                            handleDic(append,apps);
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        } else {
            List<DicItem> apps = dic.getDicCheckAppendixList();
            handleDic(append,apps);
        }


        String isOnlyLocal = Hawk.get("CCJC_OnlyLocal");
        Button btn=new Button(getContext());
        Button btn2 = new Button(getContext());
        if ("0".equals(isOnlyLocal)) {
            btn.setText("保存");
        btn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        LinearLayout.LayoutParams contentParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.setMargins((int) DWZH.dp2pt(getActivity(), 16), 0, (int) DWZH.dp2pt(getActivity(), 16),(int) DWZH.dp2pt(getActivity(), 16));
        btn.setLayoutParams(contentParams);
        btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
        rootLayout.addView(btn);
        } else if ("1".equals(isOnlyLocal)) {
            //7.28 暂时屏蔽提交按钮
//            LinearLayout btnLayout = new LinearLayout(getActivity());
//            LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            contentParams.setMargins((int) DWZH.dp2pt(getActivity(), 16), 0, (int) DWZH.dp2pt(getActivity(), 16), (int) DWZH.dp2pt(getActivity(), 16));
//            btnLayout.setLayoutParams(contentParams);
//            btnLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//            btn.setText("保存");
//            btn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
//            LinearLayout.LayoutParams btn1Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
//            btn1Params.setMargins(0, 0, (int) DWZH.dp2pt(getActivity(), 8), 0);
//            btn.setLayoutParams(btn1Params);
//            btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
//            btn2.setText("提交");
//            btn2.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
//            LinearLayout.LayoutParams btn2Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
//            btn2Params.setMargins((int) DWZH.dp2pt(getActivity(), 8), 0, 0, 0);
//            btn2.setLayoutParams(btn2Params);
//            btn2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
//            btnLayout.addView(btn);
//            btnLayout.addView(btn2);
//            rootLayout.addView(btnLayout);
            btn.setText("保存");
            btn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            LinearLayout.LayoutParams contentParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            contentParams.setMargins((int) DWZH.dp2pt(getActivity(), 16), 0, (int) DWZH.dp2pt(getActivity(), 16),(int) DWZH.dp2pt(getActivity(), 16));
            btn.setLayoutParams(contentParams);
            btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
            rootLayout.addView(btn);
        }
        hcsj.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
                CusDatePickerDialog dateDialog=CusDatePickerDialog.newInstance("选择核查时间");
                dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void OnDateSet(String dateString) {
                        hcsj.setContent(dateString);
                        hcDate=dateString;
                    }
                });
                dateDialog.show(getFragmentManager(),"HCSJ");
            }
        });
        hcqk.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
                ListDialog qkDialog=ListDialog.newInsance("请选择核查情况","HCQK");
                qkDialog.setListener(new ListDialog.OnSelected() {
                    @Override
                    public void onSelected(String type, String typeCode) {
                        hcqk.setContent(type);
                        qkCode=typeCode;
                    }
                });
                qkDialog.show(getFragmentManager(),"HCQK");
            }
        });
        clqd.setSelect(new TableRow.SelectCallBack() {
            @Override
            public void onSelect(TableRow row, int which) {
                ListDialog clDialog=ListDialog.newInsance("请选择材料清单","CLQD");
                clDialog.setListener(new ListDialog.OnSelected() {
                    @Override
                    public void onSelected(String type, String typeCode) {
                        clqd.setContent(type);
                        clCode=typeCode;
                    }
                });
                clDialog.show(getFragmentManager(),"CLQD");
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hcsj.getContent().isEmpty()||hcqk.getContent().isEmpty()){
                    Toast.makeText(getContext(),"请填写所有必填项",Toast.LENGTH_LONG).show();
                }else {
                    String checkId=Hawk.get("CCJC_CheckId");
                    String organId=loginBean.getResult().getOrganId().length()>5?loginBean.getResult().getOrganId().substring(0,6):loginBean.getResult().getOrganId();
                    String userId = loginBean.getResult().getUserId();
                    Map<String, String> body = new HashMap<>();
                    body.put("wsCodeReq","03010016");
                    body.put("checkId",checkId);
                    body.put("checkOrganId",organId);
                    body.put("checkPerson",hcr.getContent());
                    body.put("checkDate",hcsj.getContent());
                    body.put("checkOtherPerson",hcjzr.getInput());
                    body.put("checkAppendixList",clCode);
                    body.put("checkMemo",bz.getInput());
                    body.put("checkResult",qkCode);
                    if(clCode!=null && clCode.contains("9")){
                        String[] clqds=clqd.getContent().split(",");
                        body.put("checkAppendixOther",clqds[clqds.length-1]);
                    }
                    body.put("userId", userId);
                    body.put("tempSave", "1");
                    Call<CCHCResult> call;
                    if("湖南".equals(app.getVersion())){
                        call=ApiManager.hnApi.ccSDHC(body);
                    }else {
                        call=ApiManager.ccjcApi.ccSDHC(body);
                    }

                    final MaterialDialog progress=new MaterialDialog.Builder(getContext())
                            .title("实地核查")
                            .content("提交中...")
                            .progress(true, 0)
                            .show();
                    call.enqueue(new Callback<CCHCResult>() {
                        @Override
                        public void onResponse(Response<CCHCResult> response, Retrofit retrofit) {
                            if(response.isSuccess()){
                                progress.dismiss();
                                CCHCResult result=response.body();
                                if("200".equals(result.getCode())){
                                    new MaterialDialog.Builder(getContext())
                                            .title("提交成功")
                                            .content("实地核查保存成功")
                                            .positiveText("确定")
                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                    getActivity().finish();
                                                }
                                            })
                                            .show();
                                } else {
                                    new MaterialDialog.Builder(getContext())
                                            .title("出错")
                                            .content(result.getMessage())
                                            .show();
                                }
                            } else {
                                new MaterialDialog.Builder(getContext())
                                        .title("出错")
                                        .content("服务器出错")
                                        .show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            new MaterialDialog.Builder(getContext())
                                    .title("出错")
                                    .content("连接服务器出错")
                                    .show();
                                                }
                    });
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hcr.getInput().isEmpty() || hcsj.getContent().isEmpty() || hcqk.getContent().isEmpty()) {
                    Toast.makeText(getContext(), "请填写所有必填项", Toast.LENGTH_LONG).show();
                } else {
                    String checkId = Hawk.get("CCJC_CheckId");
                    Map<String, String> body = new HashMap<>();
                    body.put("wsCodeReq", "03010016");
                    body.put("checkId", checkId);
                    body.put("checkOrganId", organId.length()>5?organId.substring(0,6):organId);
                    body.put("checkPerson", hcr.getInput());
                    body.put("checkDate", hcsj.getContent());
                    body.put("checkOtherPerson", hcjzr.getContent());
                    body.put("checkAppendixList", clCode);
                    body.put("checkMemo", bz.getContent());
                    body.put("checkResult", qkCode);
                    if (clCode != null && clCode.contains("9")) {
                        String[] clqds = clqd.getContent().split(",");
                        body.put("checkAppendixOther", clqds[clqds.length - 1]);
                    }
                    body.put("userId", userId);
                    body.put("tempSave", "0");
                    Call<CCHCResult> call;
                    if ("湖南".equals(app.getVersion())) {
                        call = ApiManager.hnApi.ccSDHC(body);
                    } else {
                        call = ApiManager.ccjcApi.ccSDHC(body);
                    }

                    final MaterialDialog progress = new MaterialDialog.Builder(getContext())
                            .title("实地核查")
                            .content("提交中...")
                            .progress(true, 0)
                            .show();
                    call.enqueue(new Callback<CCHCResult>() {
                        @Override
                        public void onResponse(Response<CCHCResult> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                progress.dismiss();
                                CCHCResult result = response.body();
                                if ("200".equals(result.getCode())) {
                                    new MaterialDialog.Builder(getContext())
                                            .title("提交成功")
                                            .content("实地核查提交成功")
                                            .positiveText("确定")
                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                    getActivity().finish();
                                                }
                                            })
                                            .show();
                                }else {
                                    new MaterialDialog.Builder(getContext())
                                            .title("出错")
                                            .content(result.getMessage())
                                            .show();
                                }
                            }else {
                                new MaterialDialog.Builder(getContext())
                                        .title("出错")
                                        .content("服务器出错")
                                        .show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            new MaterialDialog.Builder(getContext())
                                    .title("出错")
                                    .content("连接服务器出错")
                                    .show();
                        }
                    });
                }
            }
        });
    }

    private void addCCJG(final CCCheckResult.Result r){
        CCCheckResult.CheckWayDoc checkWayDoc = r.getCheckWayDoc();
        CCCheckResult.CheckWayLocal checkWayLocal = r.getCheckWayLocal();
        CCCheckResult.CheckWayWeb checkWayWeb = r.getCheckWayWeb();
        ArrayList<TableRow> items = new ArrayList<>();
        if (checkWayDoc!=null){
            TableRow doc = new TableRow.Builder(getContext())
                    .asTitle("书面核查")
                    .build();
            doc.setTag("书面");
            items.add(doc);
            TableRow checkBatchName = new TableRow.Builder(getContext())
                    .title("抽查批次")
                    .content(checkWayDoc.getCheckBatchName())
                    .build();
            TableRow checkOrganName = new TableRow.Builder(getContext())
                    .title("核查实施机关")
                    .content(checkWayDoc.getCheckOrganName())
                    .build();
            TableRow checkPerson = new TableRow.Builder(getContext())
                    .title("核查人")
                    .content(checkWayDoc.getCheckPerson())
                    .build();
            TableRow checkDate = new TableRow.Builder(getContext())
                    .title("核查时间")
                    .content(checkWayDoc.getCheckDate())
                    .build();
            TableRow checkResult = new TableRow.Builder(getContext())
                    .title("检查情况（含抽查结果）")
                    .content(checkWayDoc.getCheckResult())
                    .build();
            TableRow orderNoticeContent = new TableRow.Builder(getContext())
                    .title("责令限期履行公示义务通知书内容")
                    .content(checkWayDoc.getOrderNoticeContent())
                    .build();
            TableRow orderNoticeNo = new TableRow.Builder(getContext())
                    .title("通知书文号")
                    .content(checkWayDoc.getOrderNoticeNo())
                    .build();
            TableRow orderNoticeDate = new TableRow.Builder(getContext())
                    .title("通知书时间")
                    .content(checkWayDoc.getOrderNoticeDate())
                    .build();
            final TableRow checkAppendixList = new TableRow.Builder(getContext())
                    .title("取得清单材料")
                    .content(checkWayDoc.getCheckAppendixList())
                    .build();
            TableRow checkMemo = new TableRow.Builder(getContext())
                    .title("备注")
                    .content(checkWayDoc.getCheckMemo())
                    .build();
            rootLayout.addView(doc);
            rootLayout.addView(checkBatchName);
            rootLayout.addView(checkOrganName);
            rootLayout.addView(checkDate);
            rootLayout.addView(checkPerson);
            rootLayout.addView(checkResult);
            rootLayout.addView(orderNoticeContent);
            rootLayout.addView(orderNoticeNo);
            rootLayout.addView(orderNoticeDate);
            rootLayout.addView(checkAppendixList);
            rootLayout.addView(checkMemo);
        }

        if (checkWayLocal!=null){
            TableRow local = new TableRow.Builder(getContext())
                    .asTitle("实地核查")
                    .build();
            local.setTag("实地");
            items.add(local);
            TableRow checkBatchName = new TableRow.Builder(getContext())
                    .title("抽查批次")
                    .content(checkWayLocal.getCheckBatchName())
                    .build();
            TableRow checkOrganName = new TableRow.Builder(getContext())
                    .title("核查实施机关")
                    .content(checkWayLocal.getCheckOrganName())
                    .build();
            TableRow checkPerson = new TableRow.Builder(getContext())
                    .title("核查人")
                    .content(checkWayLocal.getCheckPerson())
                    .build();
            TableRow checkDate = new TableRow.Builder(getContext())
                    .title("核查时间")
                    .content(checkWayLocal.getCheckDate())
                    .build();
            TableRow checkOtherPerson = new TableRow.Builder(getContext())
                    .title("实地核查见证人")
                    .content(checkWayLocal.getCheckOtherPerson())
                    .build();
            TableRow checkResult = new TableRow.Builder(getContext())
                    .title("核查情况（含抽查结果）")
                    .content(checkWayLocal.getCheckResult())
                    .build();
            TableRow orderNoticeContent = new TableRow.Builder(getContext())
                    .title("责令限期履行公示义务通知书内容")
                    .content(checkWayLocal.getOrderNoticeContent())
                    .build();
            TableRow orderNoticeNo = new TableRow.Builder(getContext())
                    .title("通知书文号")
                    .content(checkWayLocal.getOrderNoticeNo())
                    .build();
            TableRow orderNoticeDate = new TableRow.Builder(getContext())
                    .title("通知书时间")
                    .content(checkWayLocal.getOrderNoticeDate())
                    .build();
            final TableRow checkAppendixList = new TableRow.Builder(getContext())
                    .title("取得清单材料")
                    .content(checkWayLocal.getCheckAppendixList())
                    .build();
            TableRow checkMemo = new TableRow.Builder(getContext())
                    .title("备注")
                    .content(checkWayLocal.getCheckMemo())
                    .build();
            rootLayout.addView(local);
            rootLayout.addView(checkBatchName);
            rootLayout.addView(checkOrganName);
            rootLayout.addView(checkDate);
            rootLayout.addView(checkPerson);
            rootLayout.addView(checkOtherPerson);
            rootLayout.addView(checkResult);
            rootLayout.addView(orderNoticeContent);
            rootLayout.addView(orderNoticeNo);
            rootLayout.addView(orderNoticeDate);
            rootLayout.addView(checkAppendixList);
            rootLayout.addView(checkMemo);
        }

        if (checkWayWeb!=null){

            TableRow web = new TableRow.Builder(getContext())
                    .asTitle("网络核查")
                    .build();
            web.setTag("网络");
            items.add(web);

            TableRow checkOrganName = new TableRow.Builder(getContext())
                    .title("实施机关")
                    .content(checkWayWeb.getCheckOrganName())
                    .build();
            TableRow checkPerson = new TableRow.Builder(getContext())
                    .title("监测人")
                    .content(checkWayWeb.getCheckPerson())
                    .build();
            TableRow checkDate = new TableRow.Builder(getContext())
                    .title("监测时间")
                    .content(checkWayWeb.getCheckDate())
                    .build();
            TableRow resultMemo = new TableRow.Builder(getContext())
                    .title("监测情况")
                    .content(checkWayWeb.getCheckResultMemo())
                    .build();
            TableRow checkResult = new TableRow.Builder(getContext())
                    .title("记录抽查结果")
                    .content(checkWayWeb.getCheckResult())
                    .build();
            TableRow checkMemo = new TableRow.Builder(getContext())
                    .title("备注")
                    .content(checkWayWeb.getCheckMemo())
                    .build();
            rootLayout.addView(web);
            rootLayout.addView(checkOrganName);
            rootLayout.addView(checkPerson);
            rootLayout.addView(checkDate);
            rootLayout.addView(resultMemo);
            rootLayout.addView(checkResult);
            rootLayout.addView(checkMemo);
        }

        if (items.size()>1)
            setLayoutIndex(items);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                final String append=r.getCheckAppendixList();
                final String append="";
                DicT dic=Hawk.get("Dic");
                if(dic==null){
                    Map<String,String> body =new HashMap<>();
                    body.put("organId", organId);
                    body.put("wsCodeReq", "020001");
                    Call<DicResult> call;
                    if("湖南".equals(app.getVersion())){
                        call= ApiManager.hnApi.ccDic(body);
                    }else {
                        call= ApiManager.ccjcApi.ccDic(body);
                    }

                    call.enqueue(new Callback<DicResult>() {
                        @Override
                        public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                            if (response.isSuccess()&&response.body()!=null) {
                                DicResult result = response.body();
                                if ("200".equals(result.getCode())) {
                                    Hawk.put("Dic", result.getResult());
                                    List<DicItem> apps=result.getResult().getDicCheckAppendixList();
                                    String appString="";
                                    for (String s :
                                            append.split(",")) {
                                        for (int i = 0; i < apps.size(); i++) {
                                            if(s.equals(apps.get(i).getName())){
                                                appString+=apps.get(i).getValue()+",";
                                            }
                                        }
                                    }
                                    final String tmp=appString.substring(0,appString.isEmpty()?0:appString.length()-1);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                        }
                                    });
                                } else {

                                }
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                }else {
                    if (append!=null){
                        List<DicItem> apps=dic.getDicCheckAppendixList();
                        String appString="";
                        for (String s :
                                append.split(",")) {
                            for (int i = 0; i < apps.size(); i++) {
                                if(s.equals(apps.get(i).getName())){
                                    appString+=apps.get(i).getValue()+",";
                                }
                            }
                        }
                        final String tmp=appString.substring(0,appString.isEmpty()?0:appString.length()-1);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                }
            }
        });
    }

    public void setLayoutIndex(ArrayList<TableRow> items){
        layoutIndex.setVisibility(View.VISIBLE);
        for (TableRow tableRow : items){
            addTextView(tableRow);
        }
        layoutIndex.getChildAt(layoutIndex.getChildCount()-1).setVisibility(View.GONE);
    }

    public void addTextView(final TableRow tableRow){
        final TextView textView = new TextView(getActivity());
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setPadding(10,10,10,10);
        textView.setText(tableRow.getTag().toString());
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<layoutIndex.getChildCount();i++){
                    if ((i % 2) == 0){
                        ((TextView)layoutIndex.getChildAt(i)).setTextColor(getResources().getColor(R.color.light_gray));
                    }
                }
                textView.setTextColor(getResources().getColor(R.color.blue));
                scrollView.setSmoothScrollingEnabled(true);
//                ObjectAnimator objectAnimator = new ObjectAnimator();
//                objectAnimator.ofInt(scrollView,"scrollY",scrollView.getScrollY(),(int)tableRow.getY());
//                objectAnimator.setDuration(500);
//                objectAnimator.start();
                scrollView.setScrollY((int)tableRow.getY());
            }
        });
        layoutIndex.addView(textView);

        View view = new View(getActivity());
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,2));
        view.setBackgroundColor(getResources().getColor(R.color.blue));
        layoutIndex.addView(view);
    }

    private void addQYJBXX(CCXQResult.Result r){
        TableRow entName = new TableRow.Builder(getContext())
                .title("主体名称")
                .content(r.getEntName())
                .build();
        TableRow uniScid = new TableRow.Builder(getContext())
                .title("统一社会信用代码")
                .content(r.getUniScid())
                .build();
        TableRow regNo = new TableRow.Builder(getContext())
                .title("注册号")
                .content(r.getRegNo())
                .build();
        TableRow leader = new TableRow.Builder(getContext())
                .title("法定代表人/经营者")
                .content(r.getLeader())
                .build();
        TableRow liaisonTelephone = new TableRow.Builder(getContext())
                .title("电话")
                .content(r.getLiaisonTelephone())
                .build();
        TableRow address = new TableRow.Builder(getContext())
                .title("住所")
                .content(r.getAddress())
                //.content("")
                .build();
        rootLayout.addView(entName);
        if (TextUtils.isEmpty(r.getUniScid())){
            rootLayout.addView(regNo);
        }else {
            rootLayout.addView(uniScid);
        }
        rootLayout.addView(leader);
        rootLayout.addView(liaisonTelephone);
        rootLayout.addView(address);
    }

    private void addConsumer(CnBasic cnBasic){
        TableRow title=new TableRow.Builder(getContext())
                .asTitle("消费者信息：")
                .build();
        rootLayout.addView(title);
        if(cnBasic!=null) {
            TableRow name = new TableRow.Builder(getContext())
                    .title("姓名：")
                    .content(cnBasic.getName())
                    .build();
            /*TableRow gender = new TableRow.Builder(getContext())
                    .title("性别")
                    .content(cnBasic.getSex())
                    .build();*/
            TableRow addr = new TableRow.Builder(getContext())
                    .title("地址：")
                    .content(cnBasic.getAddr())
                    .build();
            TableRow fixTel = new TableRow.Builder(getContext())
                    .title("联系电话：")
                    .content(cnBasic.getFixTel())
                    .build();
            /*TableRow tel = new TableRow.Builder(getContext())
                    .title("电话")
                    .content(cnBasic.getTel())
                    .build();*/
            /*TableRow workPlace = new TableRow.Builder(getContext())
                    .title("工作单位")
                    .content(cnBasic.getWorkUnit())
                    .build();
            TableRow handiSign=new TableRow.Builder(getContext())
                    .title("handiSign")
                    .content(cnBasic.getHandiSign())
                    .build();
            TableRow perIde=new TableRow.Builder(getContext())
                    .title("perIde")
                    .content(cnBasic.getPerIde())
                    .build();
            TableRow perType=new TableRow.Builder(getContext())
                    .title("perType")
                    .content(cnBasic.getPerType())
                    .build();*/
            rootLayout.addView(name);
//            if(type.endsWith("LZ")){
                TableRow gender = new TableRow.Builder(getContext())
                        .title("性别：")
                        .content(cnBasic.getSex())
                        .build();
                rootLayout.addView(gender);
//            }
            rootLayout.addView(fixTel);
//            if(type.endsWith("LZ")){
                TableRow perIde=new TableRow.Builder(getContext())
                        .title("人员身份：")
                        .content(cnBasic.getPerIde())
                        .build();
                TableRow perType=new TableRow.Builder(getContext())
                        .title("年龄段：")
                        .content(cnBasic.getPerType())
                        .build();
                rootLayout.addView(perIde);
                rootLayout.addView(perType);
//            }
            rootLayout.addView(addr);
            //rootLayout.addView(gender);
            //rootLayout.addView(tel);
            /*rootLayout.addView(workPlace);
            rootLayout.addView(handiSign);
            rootLayout.addView(perIde);
            rootLayout.addView(perType);*/
        }else {
            TableRow msg=new TableRow.Builder(getContext())
                    .msg("消费者匿名，无法获取消费者信息")
                    .build();
            rootLayout.addView(msg);
        }
    }

    private void addAccuser(CnAccuse cnAccuse) {
        if (type.startsWith("TS")) {
            rootLayout.addView(new TableRow.Builder(getContext()).asTitle("被诉单位信息").build());
        } else {
            rootLayout.addView(new TableRow.Builder(getContext()).asTitle("被举报单位信息").build());
        }
        if (cnAccuse != null) {
            /*TableRow acceptType = new TableRow.Builder(getContext())
                    .title("acceptType")
                    .content(cnAccuse.getAcceptType())
                    .build();*/
            TableRow addr = new TableRow.Builder(getContext())
                    .title("地址：")
                    .content(cnAccuse.getAddr())
                    .build();
            /*TableRow acceptTypeOri = new TableRow.Builder(getContext())
                    .title("acceptTypeOri")
                    .content(cnAccuse.getAcceptTypeOri())
                    .build();*/
            /*TableRow brandName = new TableRow.Builder(getContext())
                    .title("brandName")
                    .content(cnAccuse.getBrandName())
                    .build();
            TableRow invoAm = new TableRow.Builder(getContext())
                    .title("invoAm")
                    .content("" + cnAccuse.getInvoAm())
                    .build();*/
            TableRow invoPt = new TableRow.Builder(getContext())
                    .title("名称：")
                    .content(cnAccuse.getInvoPt())
                    .build();
            /*TableRow mdseName = new TableRow.Builder(getContext())
                    .title("mdseName")
                    .content(cnAccuse.getMdseName())
                    .build();*/
            /*TableRow meaUnit = new TableRow.Builder(getContext())
                    .title("meaUnit")
                    .content(cnAccuse.getMeaUnit())
                    .build();
            TableRow merImpSign = new TableRow.Builder(getContext())
                    .title("merImpSign")
                    .content(cnAccuse.getMerImpSign())
                    .build();
            TableRow obType = new TableRow.Builder(getContext())
                    .title("obType")
                    .content(cnAccuse.getObType())
                    .build();
            TableRow obTypeGb = new TableRow.Builder(getContext())
                    .title("obTypeGb")
                    .content(cnAccuse.getObTypeGb())
                    .build();
            TableRow quan = new TableRow.Builder(getContext())
                    .title("quan")
                    .content("" + cnAccuse.getQuan())
                    .build();
            TableRow saleType = new TableRow.Builder(getContext())
                    .title("saleType")
                    .content(cnAccuse.getSaleType())
                    .build();*/
            TableRow tel = new TableRow.Builder(getContext())
                    .title("联系电话：")
                    .content(cnAccuse.getTel())
                    .build();
            /*TableRow typeSpf = new TableRow.Builder(getContext())
                    .title("typeSpf")
                    .content(cnAccuse.getTypeSpf())
                    .build();
            TableRow ubIndType = new TableRow.Builder(getContext())
                    .title("ubIndType")
                    .content(cnAccuse.getUbIndType())
                    .build();*/
            rootLayout.addView(invoPt);
            rootLayout.addView(tel);
            rootLayout.addView(addr);
            /*rootLayout.addView(acceptType);
            rootLayout.addView(acceptTypeOri);
            rootLayout.addView(brandName);
            rootLayout.addView(invoAm);
            rootLayout.addView(mdseName);
            rootLayout.addView(meaUnit);
            rootLayout.addView(merImpSign);
            rootLayout.addView(obType);
            rootLayout.addView(obTypeGb);
            rootLayout.addView(quan);
            rootLayout.addView(saleType);
            rootLayout.addView(typeSpf);
            rootLayout.addView(ubIndType);*/
        }else {
            rootLayout.addView(new TableRow.Builder(getContext()).msg("无法获取内容").build());
        }
    }

    private void addContent(CnAccuse cnAccuse,CnContent cnContent){
        if (type.startsWith("TS")) {
            rootLayout.addView(new TableRow.Builder(getContext()).asTitle("投诉内容").build());
        } else {
            rootLayout.addView(new TableRow.Builder(getContext()).asTitle("举报内容").build());
        }
        if(cnAccuse!=null&&cnContent!=null) {
            TableRow mdseName = new TableRow.Builder(getContext())
                    .title("商品(服务)名称：")
                    .content(cnAccuse.getMdseName())
                    .build();
            TableRow invoAm = new TableRow.Builder(getContext())
                    .title("消费金额(元)：")
                    .content("" + cnAccuse.getInvoAm())
                    .build();
            /*TableRow accRegPer = new TableRow.Builder(getContext())
                    .title("accRegPer")
                    .content(cnContent.getAccRegPer())
                    .build();
            TableRow accSce = new TableRow.Builder(getContext())
                    .title("accSce")
                    .content(cnContent.getAccSce())
                    .build();
            TableRow accSceDistrict = new TableRow.Builder(getContext())
                    .title("accSceDistrict")
                    .content(cnContent.getAccSceDistrict())
                    .build();
            TableRow accTime = new TableRow.Builder(getContext())
                    .title("accTime")
                    .content(cnContent.getAccTime())
                    .build();
            TableRow acceptDept = new TableRow.Builder(getContext())
                    .title("acceptDept")
                    .content(cnContent.getAcceptDept())
                    .build();
            TableRow acceptOrgan = new TableRow.Builder(getContext())
                    .title("acceptOrgan")
                    .content(cnContent.getAcceptOrgan())
                    .build();*/
            /*TableRow acciLev = new TableRow.Builder(getContext())
                    .title("acciLev")
                    .content(cnContent.getAcciLev())
                    .build();*/
            TableRow applBasQue = new TableRow.Builder(getContext())
                    .title(type.startsWith("TS")?"投诉基本问题：":"举报问题类别：")
                    .content(cnContent.getApplBasQue())
                    .build();
            TableRow applIdiQue = new TableRow.Builder(getContext())
                    .title(type.startsWith("TS")?"投诉具体问题：":"举报具体问题：")
                    .content(cnContent.getApplIdiQue())
                    .build();
            /*TableRow buyDate = new TableRow.Builder(getContext())
                    .title("buyDate")
                    .content(cnContent.getBuyDate())
                    .build();
            TableRow caseId = new TableRow.Builder(getContext())
                    .title("caseId")
                    .content(cnContent.getCaseId())
                    .build();
            TableRow compromiseSign = new TableRow.Builder(getContext())
                    .title("compromiseSign")
                    .content(cnContent.getCompromiseSign())
                    .build();
            TableRow disputeDate = new TableRow.Builder(getContext())
                    .title("disputeDate")
                    .content(cnContent.getDisputeDate())
                    .build();
            TableRow incForm = new TableRow.Builder(getContext())
                    .title("incForm")
                    .content(cnContent.getIncForm())
                    .build();
            TableRow infoOri = new TableRow.Builder(getContext())
                    .title("infoOri")
                    .content(cnContent.getInfoOri())
                    .build();
            TableRow infoType = new TableRow.Builder(getContext())
                    .title("infoType")
                    .content(cnContent.getInfoType())
                    .build();
            TableRow isSecrecy = new TableRow.Builder(getContext())
                    .title("isSecrecy")
                    .content(cnContent.getIsSecrecy())
                    .build();
            TableRow keyword = new TableRow.Builder(getContext())
                    .title("keyword")
                    .content(cnContent.getKeyword())
                    .build();
            TableRow regDep = new TableRow.Builder(getContext())
                    .title("regDep")
                    .content(cnContent.getRegDep())
                    .build();
            TableRow regOrgan = new TableRow.Builder(getContext())
                    .title("regOrgan")
                    .content(cnContent.getRegOrgan())
                    .build();
            TableRow regTime = new TableRow.Builder(getContext())
                    .title("regTime")
                    .content(cnContent.getRegTime())
                    .build();
            TableRow regiNo = new TableRow.Builder(getContext())
                    .title("regiNo")
                    .content(cnContent.getRegiNo())
                    .build();
            TableRow replySign = new TableRow.Builder(getContext())
                    .title("replySign")
                    .content(cnContent.getReplySign())
                    .build();*/

            /*rootLayout.addView(accRegPer);
            rootLayout.addView(accSce);
            rootLayout.addView(accSceDistrict);
            rootLayout.addView(accTime);
            rootLayout.addView(acceptDept);
            rootLayout.addView(acceptOrgan);
            rootLayout.addView(acciLev);*/
            rootLayout.addView(mdseName);
            if(type.endsWith("LZ")) {
                TableRow saleType = new TableRow.Builder(getContext())
                        .title("销售方式：")
                        .content(cnAccuse.getSaleType())
                        .build();
                rootLayout.addView(saleType);
            }else {
                TableRow obType = new TableRow.Builder(getContext())
                        .title("涉及客体类别：")
                        .content(cnAccuse.getObTypeGb())
                        .build();
                rootLayout.addView(obType);
            }
            rootLayout.addView(invoAm);
            if("TSXQ".equals(type)) {
                TableRow accTime = new TableRow.Builder(getContext())
                        .title("事发时间：")
                        .content(cnContent.getAccTime())
                        .build();
                TableRow accSce = new TableRow.Builder(getContext())
                        .title("事发地：")
                        .content(cnContent.getAccSce())
                        .build();
                rootLayout.addView(accTime);
                rootLayout.addView(accSce);
            }
            if(type.endsWith("XQ")) {
                rootLayout.addView(applBasQue);
                rootLayout.addView(applIdiQue);
            }
            /*rootLayout.addView(buyDate);
            rootLayout.addView(caseId);
            rootLayout.addView(compromiseSign);
            rootLayout.addView(disputeDate);
            rootLayout.addView(incForm);
            rootLayout.addView(infoOri);
            rootLayout.addView(infoType);
            rootLayout.addView(isSecrecy);
            rootLayout.addView(keyword);
            rootLayout.addView(regDep);
            rootLayout.addView(regOrgan);
            rootLayout.addView(regTime);
            rootLayout.addView(regiNo);
            rootLayout.addView(replySign);*/
        }else {
            rootLayout.addView(new TableRow.Builder(getContext()).msg("无法获取内容").build());
        }
    }

    private void addImage(List<CnUpload> cnUploads){
        fj=new ImageTableView(getContext());
        fj.setTitle("附件");
        fj.setCanAdd(false);
        fj.setImagePickerListener(this);

        for (CnUpload cnUpload : cnUploads){
            Bitmap bitmap = BitmapUtil.stringtoBitmap(cnUpload.getFilePath());
            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bitmap,null,null));
            fj.addImage(uri);
        }

        rootLayout.addView(fj);
    }

    private void addProcess(CnProcess cnProcess,CnAccuse cnAccuse){
        if(cnProcess!=null) {
            String accType = "";
            if(!TextUtils.isEmpty(cnAccuse.getAcceptTypeOri()) && RootAppcation.getInstance().getAllDic() != null
                    && RootAppcation.getInstance().getAllDic().getAcceptTypeReMap() != null){
                accType = RootAppcation.getInstance().getAllDic().getAcceptTypeReMap().get(cnAccuse.getAcceptTypeOri().trim());
            }
            TableRow acceptType = new TableRow.Builder(getContext())
                    .title("受理类型")
                    .content(accType)
                    .build();
            TableRow dealContent = new TableRow.Builder(getContext())
                    .title("处理结果")
                    .content(cnProcess.getDealContent())
                    .build();
            TableRow handDep = new TableRow.Builder(getContext())
                    .title("办理部门")
                    .content(cnProcess.getHandDep())
                    .build();
            TableRow traner = new TableRow.Builder(getContext())
                    .title("经办人")
                    .content(cnProcess.getTraner())
                    .build();
            /*TableRow aidUnitType = new TableRow.Builder(getContext())
                    .title("aidUnitType")
                    .content(cnProcess.getAidUnitType())
                    .build();
            TableRow caseId = new TableRow.Builder(getContext())
                    .title("caseId")
                    .content(cnProcess.getCaseId())
                    .build();
            TableRow cheatSign = new TableRow.Builder(getContext())
                    .title("cheatSign")
                    .content(cnProcess.getCheatSign())
                    .build();
            TableRow conSecType = new TableRow.Builder(getContext())
                    .title("conSecType")
                    .content(cnProcess.getConSecType())
                    .build();
            TableRow curSign = new TableRow.Builder(getContext())
                    .title("curSign")
                    .content(cnProcess.getCurSign())
                    .build();
            TableRow dealContent = new TableRow.Builder(getContext())
                    .title("dealContent")
                    .content(cnProcess.getDealContent())
                    .build();
            TableRow disAm = new TableRow.Builder(getContext())
                    .title("disAm")
                    .content(""+cnProcess.getDisAm())
                    .build();
            TableRow douAmeAm = new TableRow.Builder(getContext())
                    .title("douAmeAm")
                    .content(""+cnProcess.getDouAmeAm())
                    .build();
            TableRow handDep = new TableRow.Builder(getContext())
                    .title("handDep")
                    .content(cnProcess.getHandDep())
                    .build();
            TableRow handDepOri = new TableRow.Builder(getContext())
                    .title("handDepOri")
                    .content(cnProcess.getHandDepOri())
                    .build();
            TableRow headState = new TableRow.Builder(getContext())
                    .title("headState")
                    .content(cnProcess.getHeadState())
                    .build();
            TableRow hopArea = new TableRow.Builder(getContext())
                    .title("hopArea")
                    .content(""+cnProcess.getHopArea())
                    .build();
            TableRow intEndDate = new TableRow.Builder(getContext())
                    .title("intEndDate")
                    .content(cnProcess.getIntEndDate())
                    .build();
            TableRow intNo = new TableRow.Builder(getContext())
                    .title("intNo")
                    .content(cnProcess.getIntNo())
                    .build();
            TableRow intResult = new TableRow.Builder(getContext())
                    .title("intResult")
                    .content(cnProcess.getIntResult())
                    .build();
            TableRow intResultOri = new TableRow.Builder(getContext())
                    .title("intResultOri")
                    .content(cnProcess.getIntResultOri())
                    .build();
            TableRow redArea = new TableRow.Builder(getContext())
                    .title("redArea")
                    .content(""+cnProcess.getRedArea())
                    .build();
            TableRow redEcoLos = new TableRow.Builder(getContext())
                    .title("redEcoLos")
                    .content(""+cnProcess.getRedEcoLos())
                    .build();
            TableRow serialNo = new TableRow.Builder(getContext())
                    .title("serialNo")
                    .content(cnProcess.getSerialNo())
                    .build();
            TableRow spiAmeAm = new TableRow.Builder(getContext())
                    .title("spiAmeAm")
                    .content(""+cnProcess.getSpiAmeAm())
                    .build();
            TableRow torType = new TableRow.Builder(getContext())
                    .title("torType")
                    .content(cnProcess.getTorType())
                    .build();
            TableRow traner = new TableRow.Builder(getContext())
                    .title("traner")
                    .content(cnProcess.getTraner())
                    .build();*/
            /*rootLayout.addView(aidUnitType);
            rootLayout.addView(caseId);
            rootLayout.addView(cheatSign);
            rootLayout.addView(conSecType);
            rootLayout.addView(curSign);
            rootLayout.addView(dealContent);
            rootLayout.addView(disAm);
            rootLayout.addView(douAmeAm);
            rootLayout.addView(handDep);
            rootLayout.addView(handDepOri);
            rootLayout.addView(headState);
            rootLayout.addView(hopArea);
            rootLayout.addView(intEndDate);
            rootLayout.addView(intNo);
            rootLayout.addView(intResult);
            rootLayout.addView(intResultOri);
            rootLayout.addView(redArea);
            rootLayout.addView(redEcoLos);
            rootLayout.addView(serialNo);
            rootLayout.addView(spiAmeAm);
            rootLayout.addView(torType);
            rootLayout.addView(traner);*/
            rootLayout.addView(acceptType);
            rootLayout.addView(dealContent);
            rootLayout.addView(handDep);
            rootLayout.addView(traner);
        }else {
            rootLayout.addView(new TableRow.Builder(getContext()).msg("无法获取处理信息").build());
        }
    }

    private void getTSJBCL(){
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(getActivity())
                .build();
        loadingDialog.show();
        String caseId= Hawk.get("TSJBXQ_caseId");
        Call<CaseBean> call = ApiManager.ynApi.getCaseInfo(caseId,"0205000106");
        call.enqueue(new Callback<CaseBean>() {
            @Override
            public void onResponse(Response<CaseBean> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                if (response.isSuccess() && response.body() != null) {
                    CaseBean result = response.body();
                    if (result.getCode() == 200) {
                        if (type.startsWith("TSCL")||type.startsWith("JBCL")) {
                            cnAccuse = result.getResult().getCnAccuse();
                            cnProcess = result.getResult().getCnProcess();
                            cnUploads = result.getResult().getCnUploads();

                            geneTSJBCL();
                        } else if ("CLXX".equals(type)) {
                            addProcess(result.getResult().getCnProcess(),result.getResult().getCnAccuse());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void geneTSJBCL(){
        rootLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.white));
        allDic = RootAppcation.getInstance().getAllDic();
        if(type.startsWith("JB")){
            accType=new TableRow.Builder(getContext())
                    .title("受理举报类型")
                    .select("请选择受理举报类型")
                    .required()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            LinkedHashMap headStateMap = allDic.getAcceptTypeReMap();

                            createChoices(row,headStateMap);
                        }
                    })
                    .build();
            if (cnAccuse!=null&&cnAccuse.getAcceptType()!=null){
                accType.setContent(cnAccuse.getAcceptType());
            }

            regPer=new TableRow.Builder(getContext())
                    .title("反馈登记人")
                    .content(loginBean.getResult().getUserName())
                    .required()
                    .build();
            if (cnProcess!=null&&cnProcess.getFeeRegPer()!=null){
                regPer.setContent(cnProcess.getFeeRegPer());
            }

            jzzk=new TableRow.Builder(getContext())
                    .title("应急处理进展状况")
                    .select("请选择应急处理进展状况")
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            LinkedHashMap headStateMap = RootAppcation.getInstance().getAllDic().getHeadStateMap();
                            Iterator<Map.Entry> iterator = headStateMap.entrySet().iterator();

                            createChoices(row,headStateMap);
                        }
                    })
                    .build();
            if (cnProcess!=null&&cnProcess.getHeadState()!=null){
                jzzk.setContent(cnProcess.getHeadState());
            }

            blbm=new TableRow.Builder(getContext())
                    .title("办理部门")
                    .content(loginBean.getResult().getDeptName())
                    .build();


            jbr=new TableRow.Builder(getContext())
                    .title("经办人")
                    .multiInput("请输入经办人","其他经办人")
                    .required()
                    .build();
            if (cnProcess!=null&&cnProcess.getTraner()!=null){
                String [] people = cnProcess.getTraner().split(",");
                if (people!=null){
                    if (people.length == 1){
                        jbr.setMultiOne(people[0]);
                    }else {
                        jbr.setMultiOne(people[0]);
                        jbr.setMultiTwo(people[1]);
                    }
                }
            }

            xzdw=new TableRow.Builder(getContext())
                    .title("协助单位类型")
                    .select("请选择协助单位类型")
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            LinkedHashMap aidUnitTypeMap = RootAppcation.getInstance().getAllDic().getAidUnitTypeMap();
                            Iterator<Map.Entry> iterator = aidUnitTypeMap.entrySet().iterator();

                            createChoices(row,aidUnitTypeMap);
                        }
                    })
                    .build();
            if (cnProcess!=null&&cnProcess.getAidUnitType()!=null){
                xzdw.setContent(cnProcess.getAidUnitType());
            }


            cljg=new TableRow.Builder(getContext())
                    .title("处理结果")
                    .input("请输入处理结果")
                    .required()
                    .build();
            if (cnProcess!=null && cnProcess.getDealContent()!=null){
                cljg.setInput(cnProcess.getDealContent());
            }
            
            //// TODO: 17/8/1 暂时屏蔽
//            fj=new ImageTableView(getContext());
//            fj.setTitle("附件");
//            fj.setCanAdd(true);
//            fj.setImagePickerListener(this);
//            if (cnUploads!=null){
//                for (CnUpload cnUpload : cnUploads){
//                    Bitmap bitmap = BitmapUtil.stringtoBitmap(cnUpload.getFilePath());
//                    Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bitmap,null,null));
//                    fj.addImage(uri);
//                }
//            }

            rootLayout.addView(accType);
            rootLayout.addView(regPer);
            rootLayout.addView(jzzk);
            rootLayout.addView(blbm);
            rootLayout.addView(jbr);
            rootLayout.addView(xzdw);
            rootLayout.addView(cljg);
//            rootLayout.addView(fj);
        }else {
            accType=new TableRow.Builder(getContext())
                    .title("受理投诉类型")
                    .select("请选择受理投诉类型")
                    .required()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            LinkedHashMap acceptTypeApplMap = RootAppcation.getInstance().getAllDic().getAcceptTypeApplMap();

                            createChoices(row,acceptTypeApplMap);
                        }
                    })
                    .build();
            if (cnAccuse!=null && cnAccuse.getAcceptType()!=null){
                accType.setContent(cnAccuse.getAcceptType());
            }

            jzzk=new TableRow.Builder(getContext())
                    .title("应急处理进展状况")
                    .select("请选择应急处理进展状况")
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            LinkedHashMap headStateMap = allDic.getHeadStateMap();

                            createChoices(row,headStateMap);
                        }
                    })
                    .build();
            if (cnProcess!=null && cnProcess.getHeadState()!=null){
                if (allDic!=null){
                    jzzk.setContent(allDic.getHeadStateMap().get(cnProcess.getHeadState()));
                }
            }

            sjlx=new TableRow.Builder(getContext())
                    .title("消费安全事件类型")
                    .select("请选择消费安全事件类型")
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            LinkedHashMap conSecMap = allDic.getConSecTypeMap();

                            createChoices(row,conSecMap);
                        }
                    })
                    .build();
            if (cnProcess!=null && cnProcess.getConSecType()!=null){
                if (allDic!=null){
                    sjlx.setContent(allDic.getConSecTypeMap().get(cnProcess.getConSecType()));
                }
            }

            blbm=new TableRow.Builder(getContext())
                    .title("办理部门")
                    .content(loginBean.getResult().getDeptName())
                    .build();

            jbr=new TableRow.Builder(getContext())
                    .title("经办人")
                    .multiInput("请输入经办人","其他经办人")
                    .required()
                    .build();
            if (cnProcess!=null&&cnProcess.getTraner()!=null){
                String [] people = cnProcess.getTraner().split(",");
                if (people!=null){
                    if (people.length == 1){
                        jbr.setMultiOne(people[0]);
                    }else {
                        jbr.setMultiOne(people[0]);
                        jbr.setMultiTwo(people[1]);
                    }
                }
            }

             tjjg=new TableRow.Builder(getContext())
                    .title("调解结果")
                    .select("选择调解结果")
                    .required()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            LinkedHashMap intResultMap = allDic.getIntResultMap();

                            createChoices(row,intResultMap);
                        }
                    })
                    .build();
            if (cnProcess!=null && cnProcess.getIntResult()!=null){
                tjjg.setContent(cnProcess.getIntResult());
            }

            tjjsrq=new TableRow.Builder(getContext())
                    .title("调解结束日期")
                    .select("选择调解结束日期")
                    .required()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            createTime(row);
                        }
                    })
                    .build();
            if (cnProcess!=null && cnProcess.getIntEndDate()!=null){
                tjjsrq.setContent(cnProcess.getIntEndDate());
            }

            xzdw=new TableRow.Builder(getContext())
                    .title("协助单位类型")
                    .select("请选择协助单位类型")
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            LinkedHashMap aidUnitTypeMap = allDic.getAidUnitTypeMap();

                            createChoices(row,aidUnitTypeMap);
                        }
                    })
                    .build();
            if (cnProcess!=null && cnProcess.getAidUnitType()!=null){
                if (allDic!=null){
                    xzdw.setContent(allDic.getAidUnitTypeMap().get(cnProcess.getAidUnitType()));
                }
            }

            qqlx=new TableRow.Builder(getContext())
                    .title("侵权类型")
                    .select("请选择侵权类型")
                    .required()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            LinkedHashMap torTypeMap = allDic.getTorTypeMap();

                            createChoices(row,torTypeMap);
                        }
                    })
                    .build();
            if (cnProcess!=null && cnProcess.getTorType()!=null){
                if (allDic!=null){
                    qqlx.setContent(allDic.getTorTypeMap().get(cnProcess.getTorType()));
                }
            }

            isqz=new TableRow.Builder(getContext())
                    .title("是否欺诈")
                    .select("请选择是否欺诈")
                    .required()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            createChoices(row, isqzMap);
                        }
                    })
                    .build();
            if (cnProcess!=null && cnProcess.getCheatSign()!=null){
                if (isqzMap!=null){
                    isqz.setContent(isqzMap.get(cnProcess.getCheatSign()));
                }
            }

            zyje=new TableRow.Builder(getContext())
                    .title("争议金额(元)")
                    .input("0")
                    .required()
                    .build();
            zyje.setInput("0");


            whjjss=new TableRow.Builder(getContext())
                    .title("挽回经济损失(元)")
                    .input("0")
                    .required()
                    .build();
            whjjss.setInput("0");


            jspc=new TableRow.Builder(getContext())
                    .title("精神赔偿金额(元)")
                    .input("0")
                    .required()
                    .build();
            jspc.setInput("0");


            jbpc=new TableRow.Builder(getContext())
                    .title("加倍赔偿金额(元)")
                    .input("0")
                    .required()
                    .build();
            jbpc.setInput("0");

            xzwsh = new TableRow.Builder(getContext())
                    .title("行政文书号")
                    .input("请输入行政文书号")
                    .required()
                    .build();
            xzwsh.setInput(caseId);

            cljg=new TableRow.Builder(getContext())
                    .title("处理结果")
                    .input("请输入处理结果")
                    .required()
                    .build();
            if (cnProcess!=null && cnProcess.getDealContent()!=null){
                cljg.setInput(cnProcess.getDealContent());
            }

            //// TODO: 17/8/1 暂时屏蔽 
//            fj=new ImageTableView(getContext());
//            fj.setTitle("附件");
//            fj.setCanAdd(true);
//            fj.setImagePickerListener(this);
//            if (cnUploads!=null){
//                for (CnUpload cnUpload : cnUploads){
//                    Bitmap bitmap = BitmapUtil.stringtoBitmap(cnUpload.getFilePath());
//                    Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bitmap,null,null));
//                    fj.addImage(uri);
//                }
//            }

            rootLayout.addView(accType);
            rootLayout.addView(jzzk);
            rootLayout.addView(sjlx);
            rootLayout.addView(blbm);
            rootLayout.addView(jbr);
            rootLayout.addView(tjjg);
            rootLayout.addView(tjjsrq);
            rootLayout.addView(xzdw);
            rootLayout.addView(qqlx);
            rootLayout.addView(isqz);
            rootLayout.addView(zyje);
            rootLayout.addView(whjjss);
            rootLayout.addView(jspc);
            rootLayout.addView(jbpc);
            rootLayout.addView(xzwsh);
            rootLayout.addView(cljg);
//            rootLayout.addView(fj);
        }
        Button btn=new Button(getContext());
        btn.setText("提交");
        btn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        LinearLayout.LayoutParams contentParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.setMargins((int) DWZH.dp2pt(getActivity(), 16), 0, (int) DWZH.dp2pt(getActivity(), 16),(int) DWZH.dp2pt(getActivity(), 16));
        btn.setLayoutParams(contentParams);
        btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitData();
            }
        });
        rootLayout.addView(btn);
    }

    private void getAllDic(){
        Call<DicBean> call = ApiManager.ynApi.getAllDic();
        call.enqueue(new Callback<DicBean>() {
            @Override
            public void onResponse(Response<DicBean> response, Retrofit retrofit) {
                if (200==response.body().getCode()){
                    RootAppcation.getInstance().setAllDic(response.body().getReslut());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void createChoices(final TableRow row, final LinkedHashMap hashMap){
        Iterator<Map.Entry> iterator = hashMap.entrySet().iterator();
        final ArrayList<String> data = new ArrayList<>();
        while (iterator.hasNext()){
            data.add(iterator.next().getValue().toString());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setSingleChoiceItems(data.toArray(new String[data.size()]), -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String value = data.get(which);
                row.setContent(value);

                String key = "";
                for (Object getKey : hashMap.keySet()){
                    if (hashMap.get(getKey).equals(value)){
                        key = getKey.toString();
                        break;
                    }
                }

                switch (row.getTitle()){
                    case "受理举报类型":
                        body.getCnAccuse().setAcceptType(key);
                        break;
                    case "应急处理进展状况":
                        body.getCnProcess().setHeadState(key);
                        break;
                    case "协助单位类型":
                        body.getCnProcess().setAidUnitType(key);
                        break;
                    case "消费安全事件类型":
                        body.getCnProcess().setConSecType(key);
                        break;
                    case "调解结果":
                        body.getCnProcess().setIntResult(key);
                        break;
                    case "侵权类型":
                        body.getCnProcess().setTorType(key);
                        break;
                    case "是否欺诈":
                        body.getCnProcess().setCheatSign(key);
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void createTime(final TableRow row){
        TimePickerView timePickerView = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                row.setContent(DateUtil.getYMD(date));
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .build();
        timePickerView.show();
    }

    //提交数据
    public void commitData(){
        if(type.startsWith("JB")){
            if (!checkJB())
                return;
            body.getCnAccuse().setAcceptType(getKey(allDic.getAcceptTypeReMap(),accType.getContent()));
            body.getCnProcess().setFeeRegPer(regPer.getContent());
            if(!"".equals(jzzk.getContent()))
                body.getCnProcess().setHeadState(getKey(allDic.getHeadStateMap(), jzzk.getContent()));
            body.getCnProcess().setHandDep(loginBean.getResult().getDeptName());
            body.getCnProcess().setTraner(jbr.getMultiOne()+","+jbr.getMultiTwo());
            if(!"".equals(xzdw.getContent()))
                body.getCnProcess().setAidUnitType(getKey(allDic.getAidUnitTypeMap(), xzdw.getContent()));
            body.getCnProcess().setDealContent(cljg.getInput());
            //// TODO: 17/8/1 暂时屏蔽
//            List<Uri> imgList = fj.getImgSrcs();
//            List<CnUpload> fileList = body.getCnUploads();
//            for(int i=0; i<imgList.size()-1; i++){
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imgList.get(i));
//                    CnUpload temp = new CnUpload();
//                    temp.setFileName(caseId+"_"+(i+1)+".png");
//                    temp.setFilePath(BitmapUtil.bitmapToBase64(bitmap));
//                    fileList.add(temp);
//                    bitmap.recycle();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }else {
            if(!checkTS())
                return;
            body.getCnAccuse().setAcceptType(getKey(allDic.getAcceptTypeApplMap(),accType.getContent()));
            if(!"".equals(jzzk.getContent()))
                body.getCnProcess().setHeadState(getKey(allDic.getHeadStateMap(), jzzk.getContent()));
            if(!"".equals(sjlx.getContent()))
                body.getCnProcess().setConSecType(getKey(allDic.getConSecTypeMap(), sjlx.getContent()));
            body.getCnProcess().setHandDep(loginBean.getResult().getDeptName());
            body.getCnProcess().setTraner(jbr.getMultiOne()+","+jbr.getMultiTwo());
            body.getCnProcess().setIntResult(getKey(allDic.getIntResultMap(), tjjg.getContent()));
            body.getCnProcess().setIntEndDate(tjjsrq.getContent());
            if(!"".equals(xzdw.getContent()))
                body.getCnProcess().setAidUnitType(getKey(allDic.getAidUnitTypeMap(), xzdw.getContent()));
            body.getCnProcess().setCheatSign(getKey(isqzMap, isqz.getContent()));
            body.getCnProcess().setTorType(getKey(allDic.getTorTypeMap(), qqlx.getContent()));
            body.getCnProcess().setDisAm(zyje.getInput());
            body.getCnProcess().setRedEcoLos(whjjss.getInput());
            body.getCnProcess().setSpiAmeAm(jspc.getInput());
            body.getCnProcess().setDouAmeAm(jbpc.getInput());
            body.getCnProcess().setIntNo(xzwsh.getInput());
            body.getCnProcess().setDealContent(cljg.getInput());
            //// TODO: 17/8/1 暂时屏蔽
//            List<Uri> imgList = fj.getImgSrcs();
//            List<CnUpload> fileList = body.getCnUploads();
//            for(int i=0; i<imgList.size()-1; i++){
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imgList.get(i));
//                    CnUpload temp = new CnUpload();
//                    temp.setFileName(caseId+"_"+(i+1)+".png");
//                    temp.setFilePath(BitmapUtil.bitmapToBase64(bitmap));
//                    fileList.add(temp);
//                    bitmap.recycle();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(getActivity())
                .build();
        loadingDialog.show();
        Call<UploadResBean> call = ApiManager.ynApi.saveDealInfo(body);
        call.enqueue(new Callback<UploadResBean>() {
            @Override
            public void onResponse(Response<UploadResBean> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                if(response!=null &&response.body()!=null){
                    if(response.body().getCode()==200){
                        Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }else{
                        Toast.makeText(getActivity(), "提交失败", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(getActivity(), "网络错误，请重试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(getActivity(), "网络错误，请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkTS(){
        //受理投诉类型
        if ("".equals(accType.getContent())){
            Toast.makeText(getActivity(),"受理投诉类型不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //经办人
        if ("".equals(jbr.getMultiOne()) || "".equals(jbr.getMultiTwo())){
            Toast.makeText(getActivity(),"经办人不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //调解结果
        if ("".equals(tjjg.getContent())){
            Toast.makeText(getActivity(),"调解结果不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //调解结束日期
        if ("".equals(tjjsrq.getContent())){
            Toast.makeText(getActivity(),"调解结束日期不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //侵权类型
        if ("".equals(qqlx.getContent())){
            Toast.makeText(getActivity(),"侵权类型不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //是否欺诈
        if ("".equals(isqz.getContent())){
            Toast.makeText(getActivity(),"是否欺诈不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //争议金额
        if ("".equals(zyje.getInput())){
            Toast.makeText(getActivity(),"争议金额不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if(!zyje.getInput().matches("^\\d+$")){
            Toast.makeText(getActivity(),"争议金额只能输入非负整数",Toast.LENGTH_SHORT).show();
            return false;
        }

        //挽回经济损失
        if ("".equals(whjjss.getInput())){
            Toast.makeText(getActivity(),"挽回经济损失不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if(!whjjss.getInput().matches("^\\d+$")){
            Toast.makeText(getActivity(),"挽回经济损失只能输入非负整数",Toast.LENGTH_SHORT).show();
            return false;
        }

        //精神赔偿金额
        if ("".equals(jspc.getInput())){
            Toast.makeText(getActivity(),"精神赔偿金额不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if(!jspc.getInput().matches("^\\d+$")){
            Toast.makeText(getActivity(),"精神赔偿金额只能输入非负整数",Toast.LENGTH_SHORT).show();
            return false;
        }

        //加倍赔偿金额
        if ("".equals(jbpc.getInput())){
            Toast.makeText(getActivity(),"加倍赔偿金额不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if(!jbpc.getInput().matches("^\\d+$")){
            Toast.makeText(getActivity(),"加倍赔偿金额只能输入非负整数",Toast.LENGTH_SHORT).show();
            return false;
        }
        //行政文书号
        if ("".equals(xzwsh.getInput())){
            Toast.makeText(getActivity(),"行政文书号不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //处理结果
        if ("".equals(cljg.getInput())){
            Toast.makeText(getActivity(),"处理结果不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkJB(){
        //受理投诉类型
        if ("".equals(accType.getContent())){
            Toast.makeText(getActivity(),"受理投诉类型不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //反馈登记人
        if("".equals(regPer.getContent())){
            Toast.makeText(getActivity(),"反馈登记人不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //经办人
        if ("".equals(jbr.getMultiOne()) || "".equals(jbr.getMultiTwo())){
            Toast.makeText(getActivity(),"经办人不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //处理结果
        if ("".equals(cljg.getInput())){
            Toast.makeText(getActivity(),"处理结果不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void imagePicker() {
        getPermission();
    }

    private void getPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
            this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,1);
        }else {
           actionPic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                actionPic();
            }
        }
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return super.shouldShowRequestPermissionRationale(permission);
    }

    public void  actionPic(){
        Intent intent = new Intent(getActivity(), ImageGridActivity.class);
        startActivityForResult(intent,IMG_PICKER);
    }

    public void handleDic(String append,List<DicItem> list){
        String appString = "";
        for (String s : append.split(",")) {
            for (int i = 0; i < list.size(); i++) {
                if (s.equals(list.get(i).getName())) {
                    appString += list.get(i).getValue() + ",";
                }
            }
        }
        final String tmp = appString.substring(0, appString.isEmpty() ? 0 : appString.length() - 1);
        clqd.setContent(tmp);
    }

    //通过value获取key
    private String getKey(LinkedHashMap<String, String> map, String value){
        for(String key:map.keySet()){
            if(map.get(key).equals(value))
                return key;
        }
        return "";
    }
}