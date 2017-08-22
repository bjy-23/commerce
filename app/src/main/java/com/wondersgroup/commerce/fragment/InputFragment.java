package com.wondersgroup.commerce.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.interface_.Data;
import com.wondersgroup.commerce.model.FwTypeBean;
import com.wondersgroup.commerce.model.GwjsCondition;
import com.wondersgroup.commerce.model.SwTypeBean;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.teamwork.gwpy.CheckListActivity;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;
import com.wondersgroup.commerce.widget.TableRow;
import com.wondersgroup.commerce.widget.TableRowView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFragment extends Fragment {
    @Bind(R.id.rootLayout)LinearLayout rootLayout;

    private static final String ARG_TYPE = "type";
    private static final String ARG_VIEWTYPE="viewType";
    private String type;
    private String viewType;
    private boolean needFetch=false;

    private TableRow typeView;
    private TableRow dateView;
    private TableRow lbView;
    private TableRow optionView;
    private TableRow deptView;
    private String typeStr;
    private String lbStr;
    private String optionStr;
    private String deptStr;
    private TotalLoginBean loginBean;
    private String userId;
    private String deptId;
    private String organId;



    public InputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type Parameter 1.
     * @return A new instance of fragment RecyclerFragment.
     */
    public static InputFragment newInstance(String type,String viewType) {
        InputFragment fragment = new InputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        args.putString(ARG_VIEWTYPE,viewType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
            viewType = getArguments().getString(ARG_VIEWTYPE);
        }
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        userId = loginBean.getResult().getUserId();
        deptId = loginBean.getResult().getDeptId();
        organId = loginBean.getResult().getOrganId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_input, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(needFetch){
            fetchData();
        }
        if("SWJS".equals(type)){
            final TableRow titleView = new TableRow.Builder(getContext())
                    .title("文件标题")
                    .input("请输入文件标题")
                    .hideBtmLine()
                    .build();
            rootLayout.addView(titleView);
            final TableRow orginView = new TableRow.Builder(getContext())
                    .title("来文机关")
                    .input("请输入来文机关")
                    .hideBtmLine()
                    .build();
            rootLayout.addView(orginView);
            typeView=new TableRow.Builder(getContext())
                    .title("文件类型")
                    .select("请选择文件类型")
                    .hideBtmLine()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            Intent intent = new Intent(getActivity(), CheckListActivity.class);
                            intent.putExtra("title", "文件类型");
                            intent.putExtra("type", "swDocType");
                            startActivityForResult(intent, 1);
                        }
                    })
                    .build();
            rootLayout.addView(typeView);
            dateView=new TableRow.Builder(getContext())
                    .title("来文日期")
                    .multiSelect("起始日期", "截至日期")
                    .multiSeperator("~")
                    .hideBtmLine()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            CusDatePickerDialog dateDialog;
                            if(which == 1){
                                dateDialog = CusDatePickerDialog.newInstance("选择起始时间");
                                dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void OnDateSet(String dateString) {
                                        dateView.setMultiOne(dateString);
                                    }
                                });
                                dateDialog.show(getChildFragmentManager(), "HCSJ");
                            }else{
                                dateDialog = CusDatePickerDialog.newInstance("选择截止时间");
                                dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void OnDateSet(String dateString) {
                                        dateView.setMultiTwo(dateString);
                                    }
                                });
                                dateDialog.show(getChildFragmentManager(), "HCSJ");
                            }
                        }
                    })
                    .build();
            rootLayout.addView(dateView);
            final Button btn=new Button(getContext());
            btn.setText("查询");
            btn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            LinearLayout.LayoutParams contentParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            contentParams.setMargins((int) DWZH.dp2pt(getActivity(), 16), (int) DWZH.dp2pt(getActivity(), 50), (int) DWZH.dp2pt(getActivity(), 16), 0);
            btn.setLayoutParams(contentParams);
            btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RecyclerActivity.class);
                    intent.putExtra("doctype", "收文管理");
                    GwjsCondition gwjsCondition = new GwjsCondition();
                    if (!"".equals(titleView.getContent())) {
                        gwjsCondition.setTitle(titleView.getContent());
                    }
                    if (!"".equals(orginView.getContent())) {
                        gwjsCondition.setDraftdept(orginView.getContent());
                    }
                    gwjsCondition.setDoctype(typeStr);
                    gwjsCondition.setCreatetimeStartStr(dateView.getMultiOne());
                    gwjsCondition.setCreatetimeEndStr(dateView.getMultiTwo());
                    gwjsCondition.setPageNo("1");
                    gwjsCondition.setPageSize("50");
                    Hawk.put("gwCondition", gwjsCondition);
                    startActivity(intent);
                }
            });
            rootLayout.addView(btn);

        }else if("FWJS".equals(type)){
            final TableRow titleView = new TableRow.Builder(getContext())
                    .title("文件标题")
                    .input("请输入文件标题")
                    .hideBtmLine()
                    .build();
            rootLayout.addView(titleView);
            final TableRow peoView = new TableRow.Builder(getContext())
                    .title("拟稿人")
                    .input("请输入拟稿人")
                    .hideBtmLine()
                    .build();
            rootLayout.addView(peoView);
            typeView=new TableRow.Builder(getContext())
                    .title("文件类型")
                    .select("请选择文件类型")
                    .hideBtmLine()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            Intent intent = new Intent(getActivity(), CheckListActivity.class);
                            intent.putExtra("title", "文件类型");
                            intent.putExtra("type", "fwDocType");
                            startActivityForResult(intent, 2);
                        }
                    })
                    .build();
            rootLayout.addView(typeView);
            lbView=new TableRow.Builder(getContext())
                    .title("文件类别")
                    .select("请选择文件类别")
                    .hideBtmLine()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            Intent intent = new Intent(getActivity(), CheckListActivity.class);
                            intent.putExtra("title", "文件类别");
                            intent.putExtra("type", "fwDocClass");
                            startActivityForResult(intent, 3);
                        }
                    })
                    .build();
            rootLayout.addView(lbView);
            optionView=new TableRow.Builder(getContext())
                    .title("公开属性")
                    .select("请选择公开属性")
                    .hideBtmLine()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            Intent intent = new Intent(getActivity(), CheckListActivity.class);
                            intent.putExtra("title", "公开属性");
                            intent.putExtra("type", "fwOpenOp");
                            startActivityForResult(intent, 4);
                        }
                    })
                    .build();
            rootLayout.addView(optionView);
            deptView=new TableRow.Builder(getContext())
                    .title("选择拟稿部门")
                    .select("请选择拟稿部门")
                    .hideBtmLine()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            Intent intent = new Intent(getActivity(), CheckListActivity.class);
                            intent.putExtra("title", "选择拟稿部门");
                            intent.putExtra("type", "fwDept");
                            startActivityForResult(intent, 5);
                        }
                    })
                    .build();
            rootLayout.addView(deptView);
            dateView=new TableRow.Builder(getContext())
                    .title("来文日期")
                    .multiSelect("起始日期", "截至日期")
                    .multiSeperator("~")
                    .hideBtmLine()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            CusDatePickerDialog dateDialog;
                            if(which == 1){
                                dateDialog = CusDatePickerDialog.newInstance("选择起始时间");
                                dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void OnDateSet(String dateString) {
                                        dateView.setMultiOne(dateString);
                                    }
                                });
                                dateDialog.show(getChildFragmentManager(), "HCSJ");
                            }else{
                                dateDialog = CusDatePickerDialog.newInstance("选择截止时间");
                                dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void OnDateSet(String dateString) {
                                        dateView.setMultiTwo(dateString);
                                    }
                                });
                                dateDialog.show(getChildFragmentManager(), "HCSJ");
                            }
                        }
                    })
                    .build();
            rootLayout.addView(dateView);
            final Button btn=new Button(getContext());
            btn.setText("查询");
            btn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            LinearLayout.LayoutParams contentParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            contentParams.setMargins((int) DWZH.dp2pt(getActivity(), 16), (int) DWZH.dp2pt(getActivity(), 50), (int) DWZH.dp2pt(getActivity(), 16), (int) DWZH.dp2pt(getActivity(), 16));
            btn.setLayoutParams(contentParams);
            btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RecyclerActivity.class);
                    intent.putExtra("doctype", "发文管理");
                    GwjsCondition gwjsCondition = new GwjsCondition();
                    if (!"".equals(titleView.getContent())) {
                        gwjsCondition.setTitle(titleView.getContent());
                    }
                    if (!"".equals(peoView.getContent())) {
                        gwjsCondition.setDrafter(peoView.getContent());
                    }
                    gwjsCondition.setDoctype(typeStr);
                    gwjsCondition.setDocclass(lbStr);
                    gwjsCondition.setOpentype(optionStr);
                    gwjsCondition.setDraftdeptname(deptStr);
                    gwjsCondition.setCreatetimeStartStr(dateView.getMultiOne());
                    gwjsCondition.setCreatetimeEndStr(dateView.getMultiTwo());
                    gwjsCondition.setPageNo("1");
                    gwjsCondition.setPageSize("50");
                    Hawk.put("gwCondition", gwjsCondition);
                    startActivity(intent);
                }
            });
            rootLayout.addView(btn);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            if(getView()!=null){
                fetchData();
                needFetch=false;
            }else {
                needFetch=true;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if (data != null){
                String result = data.getStringExtra("result");
                typeView.setContent(result);
                List<SwTypeBean.Result.Doctype> doctypes = Hawk.get("swDocType");
                typeStr = doctypes.get(data.getIntExtra("position", 0)).getKey();
            }
        }else if(requestCode==2){
            if (data != null){
                String result = data.getStringExtra("result");
                typeView.setContent(result);
                List<FwTypeBean.Result.DocTypeItem> docTypeItems = Hawk.get("fwDocType");
                typeStr = docTypeItems.get(data.getIntExtra("position", 0)).getKey();
            }
        }else if(requestCode==3){
            if (data != null){
                String result = data.getStringExtra("result");
                lbView.setContent(result);
                List<FwTypeBean.Result.DocClassItem> docClassItems = Hawk.get("fwDocClass");
                lbStr = docClassItems.get(data.getIntExtra("position", 0)).getKey();
            }
        }else if(requestCode==4){
            if (data != null){
                String result = data.getStringExtra("result");
                optionView.setContent(result);
                int pos = data.getIntExtra("position", 0);
                if(pos==0) optionStr = "2";
                else if(pos==1) optionStr = "1";
                else if(pos==2) optionStr = "3";
                else if(pos==3) optionStr = "";
            }
        }else if(requestCode==5){
            if (data != null){
                String result = data.getStringExtra("result");
                deptView.setContent(result);
                List<FwTypeBean.Result.DeptMap> deptMaps = Hawk.get("fwDept");
                deptStr = deptMaps.get(data.getIntExtra("position", 0)).getKey();
            }
        }
    }

    private void fetchData(){
        Map<String, String> map = new HashMap<>();
        if("SWJS".equals(type)){
            map.put("wsCodeReq", "07010008");
            map.put("businessType", "2");
            map.put("userId", userId);
            map.put("deptId", deptId);
            map.put("organId", organId);
            Call<SwTypeBean> call = ApiManager.oaApi.apiQuerySw(map);
            call.enqueue(new Callback<SwTypeBean>() {
                @Override
                public void onResponse(Response<SwTypeBean> response, Retrofit retrofit) {
                    if(response.body()!=null){
                        if(response.body().getResult()!=null){
                            Hawk.put("swDocType", response.body().getResult().getDoctype());
                        }else{
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                }
            });
        }else if("FWJS".equals(type)){
            map.put("wsCodeReq", "07010008");
            map.put("businessType", "1");
            map.put("userId", userId);
            map.put("deptId", deptId);
            map.put("organId", organId);
            Call<FwTypeBean> call = ApiManager.oaApi.apiQueryFw(map);
            call.enqueue(new Callback<FwTypeBean>() {
                @Override
                public void onResponse(Response<FwTypeBean> response, Retrofit retrofit) {
                    if(response.body()!=null){
                        if(response.body().getResult()!=null){
                            Hawk.put("fwDocType", response.body().getResult().getDocTypeItems());
                            Hawk.put("fwDocClass", response.body().getResult().getDocClassItems());
                            Hawk.put("fwDept", response.body().getResult().getDeptMap());
                            Hawk.put("fwDocNo", response.body().getResult().getDocNoItems());
                        }else{
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
