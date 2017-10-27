package com.wondersgroup.commerce.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.Dispatcher;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.activity.ViewPagerActivity;
import com.wondersgroup.commerce.adapter.LcGraphAdapter;
import com.wondersgroup.commerce.adapter.TextWpicAdapter;
import com.wondersgroup.commerce.adapter.Title4RowAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.BacklogListBean;
import com.wondersgroup.commerce.model.LcGraphItem;
import com.wondersgroup.commerce.model.ReceiveDetailBean;
import com.wondersgroup.commerce.model.SendDetailBean;
import com.wondersgroup.commerce.model.TextWpicItem;
import com.wondersgroup.commerce.model.Title4RowItem;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.ccjc.CCOpnnResult;
import com.wondersgroup.commerce.model.yn.CaseBean;
import com.wondersgroup.commerce.model.yn.CnOpinion;
import com.wondersgroup.commerce.model.yn.ToDoBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
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
 * Use the {@link RecyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerFragment extends Fragment {
    @BindView(R.id.fragment_recycler)
    RecyclerView recycler;
    @BindView(R.id.view_error)
    View viewError;
    @BindView(R.id.tv_error)
    TextView tvError;

    private static final String ARG_TYPE = "type";
    private static final String ARG_VIEWTYPE = "viewType";
    private String type;
    private String viewType;
    private boolean needFetch = false, isLoaded = false;

    private Title4RowAdapter title4RowAdapter;
    private List<Title4RowItem> title4RowItems;
    private TextWpicAdapter textWpicAdapter;
    private List<TextWpicItem> textWpicItems;
    private LcGraphAdapter lcGraphAdapter;
    private List<LcGraphItem> lcGraphItems;

    private List<Map<String, String>> gwData = null;
    private RootAppcation app;
    private TotalLoginBean loginBean;
    private String userId,deptId;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type Parameter 1.
     * @return A new instance of fragment RecyclerFragment.
     */
    public static RecyclerFragment newInstance(String type, String viewType) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        args.putString(ARG_VIEWTYPE, viewType);
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
        app = (RootAppcation) getActivity().getApplication();
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        userId = loginBean.getResult().getUserId();
        deptId = loginBean.getResult().getDeptId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setItemAnimator(new DefaultItemAnimator());
        init();
        if (needFetch) {
            fetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (getView() != null && !isLoaded) {
                fetchData();
                needFetch = false;
            } else {
                needFetch = true;
            }
        }
    }

    private void init() {
        if ("Title4Row".equals(viewType)) {
            title4RowItems = new ArrayList<>();
            title4RowAdapter = new Title4RowAdapter(title4RowItems);
            recycler.setAdapter(title4RowAdapter);
        } else if ("TestWp".equals(viewType)) {
            gwData = new ArrayList<>();
            textWpicItems = new ArrayList<>();
            textWpicAdapter = new TextWpicAdapter(textWpicItems);
            recycler.setAdapter(textWpicAdapter);
        } else if ("LCGraph".equals(viewType)) {
            recycler.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.whiter_gray));
            lcGraphItems = new ArrayList<>();
            int itemSize = 5;
            if ("LZJL".equals(type)) itemSize = 7;
            lcGraphAdapter = new LcGraphAdapter(lcGraphItems, itemSize);
            recycler.setAdapter(lcGraphAdapter);
        }
    }

    private void fetchData() {
        if ("TSDB".equals(type)) {
            getTSJB("1");
        } else if ("JBDB".equals(type)) {
            getTSJB("2");
        } else if ("DBLB".equals(type)) {
            getGwData();
        } else if ("DYLB".equals(type)) {
            getGwData();
        } else if ("PYLC".equals(type)) {
            getLcData();
        } else if ("GWJSLC".equals(type)) {
            getLcData();
        } else if ("LZJL".equals(type)) {
            getLZJL();
        } else if ("BLYJ".equals(type)) {
            getBLYJ();
        }
        setListener();
    }

    private void setListener() {
        if ("Title4Row".equals(viewType)) {
            title4RowAdapter.setOnItemClickListener(new Title4RowAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                    intent.putExtra("title", title4RowItems.get(position).getTitle());
                    if ("TSDB".equals(type)) intent.putExtra("type", "TSXQCL");
                    else intent.putExtra("type", "JBXQCL");
                    Hawk.put("TSJBXQ_caseId", title4RowItems.get(position).getTitle());
                    startActivity(intent);
                }
            });
        } else if ("TestWp".equals(viewType)) {
            textWpicAdapter.setOnItemClickListener(new TextWpicAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    //Toast.makeText(getActivity(), "HAHAHA", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                    intent.putExtra("title", gwData.get(position).get("title"));
                    intent.putExtra("type", "SPLCYL");
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("flowUuid", gwData.get(position).get("flowUuid"));
                    map.put("activityId", gwData.get(position).get("activityId"));
                    map.put("activityUuid", gwData.get(position).get("activityUuid"));
                    Hawk.put("docType", gwData.get(position).get("type"));
                    Hawk.put("actName", gwData.get(position).get("activityName"));
                    Hawk.put("InfoMap", map);
                    startActivity(intent);
                }
            });
        }
    }

    private void getBLYJ() {
        int curSize = lcGraphItems.size();
        lcGraphItems.clear();
        lcGraphAdapter.notifyItemRangeRemoved(0, curSize);
        String checkId = Hawk.get("CCJC_CheckId");
        Map<String, String> body = new HashMap<>();
        body.put("wsCodeReq", "03010016");
        body.put("checkId", checkId);
        Call<CCOpnnResult> call;
        if ("湖南".equals(app.getVersion())) {
            call = ApiManager.hnApi.ccOpnn(body);
        } else {
            call = ApiManager.ccjcApi.ccOpnn(body);
        }

        call.enqueue(new Callback<CCOpnnResult>() {
            @Override
            public void onResponse(Response<CCOpnnResult> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    CCOpnnResult result = response.body();
                    if ("200".equals(result.getCode())) {
                        recycler.setVisibility(View.VISIBLE);
                        for (CCOpnnResult.Result r :
                                result.getResult()) {
                            LcGraphItem item = new LcGraphItem();
                            List<String> title = new ArrayList<>();
                            List<String> content = new ArrayList<>();
                            title.add("环节：");
                            title.add("操作结果：");
                            title.add("操作部门：");
                            title.add("操作时间：");
                            title.add("操作意见：");
                            content.add(r.getResultName());
                            content.add(r.getActionName());
                            content.add(r.getStaffName());
                            content.add(r.getStaffDate());
                            content.add(r.getOpnnText());
                            item.setTitleList(title);
                            item.setContentList(content);
                            lcGraphItems.add(item);
                        }
                        lcGraphAdapter.notifyItemRangeInserted(0, lcGraphItems.size());
                    } else {
                        viewError.setVisibility(View.VISIBLE);
                        recycler.setVisibility(View.GONE);
                        //Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "获取办理意见失败", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "获取办理意见失败", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getTSJB(final String infoType) {
        int curSize = title4RowItems.size();
        title4RowItems.clear();
        title4RowAdapter.notifyItemRangeRemoved(0, curSize);
        ApiManager.getInstance().ynInit();
        TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
        String userId = loginBean.getResult().getUserId();
        String organId = loginBean.getResult().getOrganId();
        String deptId = loginBean.getResult().getDeptId();
        String pageSize = "25";
        Call<ToDoBean> call = ApiManager.ynApi.getTodo(infoType, userId, organId, deptId, "1", pageSize);
        call.enqueue(new Callback<ToDoBean>() {
            @Override
            public void onResponse(Response<ToDoBean> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    ArrayList<ToDoBean.Result_> arrayList = (ArrayList<ToDoBean.Result_>) response.body().getResult().getResult();
                    if (arrayList!=null){
                        if (arrayList.size()!=0){
                            for (ToDoBean.Result_ b : arrayList) {
                                Title4RowItem tmp = new Title4RowItem();
                                tmp.setTitle(b.getCaseId());
                                tmp.setRowOneTitle("消费者");
                                tmp.setRowOneContent(b.getBasicName());
                                if ("1".equals(infoType)) {
                                    tmp.setRowTwoTitle("被诉单位");
                                } else {
                                    tmp.setRowTwoTitle("被举报单位");
                                }
                                tmp.setRowTwoContent(b.getAccuseName());
                                tmp.setRowThrTitle("接收方式");
                                tmp.setRowThrContent(b.getIncForm());
                                tmp.setRowForTitle("登记日期");
                                tmp.setRowForContent(b.getRegDate());

                                title4RowItems.add(tmp);
                            }
                            title4RowAdapter.notifyItemRangeInserted(0, title4RowItems.size());
                        }else {
                            viewError.setVisibility(View.VISIBLE);
                            tvError.setText(Constants.NO_DB);
                        }
                    }else {
                        viewError.setVisibility(View.VISIBLE);
                        tvError.setText(Constants.NO_DB);
                    }
                } else {
                    viewError.setVisibility(View.VISIBLE);
                    tvError.setText(Constants.NO_DB);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                viewError.setVisibility(View.VISIBLE);
                tvError.setText(Constants.NO_DB);
            }
        });
    }

    private void getGwData() {
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(getActivity()).build();
        loadingDialog.show();
        gwData.clear();
        int curSize = textWpicItems.size();
        textWpicItems.clear();
        textWpicAdapter.notifyItemRangeRemoved(0, curSize);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010005");
        map.put("userId", userId);
        map.put("deptId", deptId);
        map.put("pageNo", "1");
        map.put("pageSize", "50");
        if ("DBLB".equals(type)) {
            map.put("businessType", "1");
            Call<BacklogListBean> call = ApiManager.oaApi.apiBacklogList(map);
//            Dispatcher
            call.enqueue(new Callback<BacklogListBean>() {
                @Override
                public void onResponse(Response<BacklogListBean> response, Retrofit retrofit) {
                    loadingDialog.dismiss();
                    isLoaded = true;
                    if (response.body() != null) {
                        if (response.body().getResult() != null) {
                            List<BacklogListBean.Result.BackLogVoList> dataList = response.body().getResult().getBackLogVoList();
                            if (dataList != null) {
                                for (int i = 0; i < dataList.size(); i++) {
                                    Map<String, String> temp = new HashMap<String, String>();
                                    TextWpicItem item = new TextWpicItem();
                                    temp.put("activityId", dataList.get(i).getActivityId());
                                    temp.put("activityName", dataList.get(i).getActivityName());
                                    temp.put("title", dataList.get(i).getTitle());
                                    //temp.put("createAt", dataList.get(i).getCreateAt());
                                    //temp.put("flowHandleUserName", dataList.get(i).getFlowHandleUserName());
                                    temp.put("type", dataList.get(i).getType());
                                    temp.put("flowUuid", dataList.get(i).getFlowUuid());
                                    temp.put("activityUuid", dataList.get(i).getActivityUuid());
                                    //temp.put("isUrgency", dataList.get(i).getIsUrgency());
                                    gwData.add(temp);
                                    item.setTitle(dataList.get(i).getTitle());
                                    item.setRowTwoText(dataList.get(i).getCreateAt());
                                    item.setPicId(("收文管理".equals(dataList.get(i).getType())) ? R.mipmap.icons_receive_file : R.mipmap.icons_send_file);
                                    item.setPicText(dataList.get(i).getType());
                                    item.setIsUrgency(dataList.get(i).getIsUrgency());
                                    textWpicItems.add(item);
                                }
                                textWpicAdapter.notifyItemRangeInserted(0, textWpicItems.size());
                            }
                        } else {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    loadingDialog.dismiss();
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                }
            });

        } else if ("DYLB".equals(type)) {
            map.put("businessType", "2");
            Call<BacklogListBean> call = ApiManager.oaApi.apiBacklogList(map);
            call.enqueue(new Callback<BacklogListBean>() {
                @Override
                public void onResponse(Response<BacklogListBean> response, Retrofit retrofit) {
                    loadingDialog.dismiss();
                    if (response.body() != null) {
                        if (response.body().getResult() != null) {
                            List<BacklogListBean.Result.BackLogVoList> dataList = response.body().getResult().getBackLogVoList();
                            if (dataList != null) {
                                for (int i = 0; i < dataList.size(); i++) {
                                    Map<String, String> temp = new HashMap<String, String>();
                                    TextWpicItem item = new TextWpicItem();
                                    temp.put("activityId", dataList.get(i).getActivityId());
                                    temp.put("activityName", dataList.get(i).getActivityName());
                                    temp.put("title", dataList.get(i).getTitle());
                                    //temp.put("createAt", dataList.get(i).getCreateAt());
                                    //temp.put("flowHandleUserName", dataList.get(i).getFlowHandleUserName());
                                    temp.put("type", dataList.get(i).getType());
                                    temp.put("flowUuid", dataList.get(i).getFlowUuid());
                                    temp.put("activityUuid", dataList.get(i).getActivityUuid());
                                    gwData.add(temp);
                                    item.setTitle(dataList.get(i).getTitle());
                                    item.setRowTwoText(dataList.get(i).getCreateAt());
                                    item.setPicId(R.mipmap.icons_normal);
                                    item.setPicText("一般文件");
                                    textWpicItems.add(item);
                                }
                                textWpicAdapter.notifyItemRangeInserted(0, textWpicItems.size());
                            }
                        } else {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    loadingDialog.dismiss();
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void getLcData() {
        int curSize = lcGraphItems.size();
        lcGraphItems.clear();
        lcGraphAdapter.notifyItemRangeRemoved(0, curSize);
        viewError.setVisibility(View.GONE);
        String docType = "";
        if ("PYLC".equals(type)) docType = Hawk.get("docType");
        else if ("GWJSLC".equals(type)) docType = Hawk.get("gwType");

        if ("收文管理".equals(docType) && ReceiveDetailBean.receiveDetailBean != null
                && ReceiveDetailBean.receiveDetailBean.getResult().getOpnnList() != null) {
            for (int i = 0; i < ReceiveDetailBean.receiveDetailBean.getResult().getOpnnList().size(); i++) {
                LcGraphItem item = new LcGraphItem();
                List<String> title = new ArrayList<>();
                List<String> content = new ArrayList<>();
                title.add("办理结果：");
                title.add("环节：");
                title.add("操作人：");
                title.add("操作时间：");
                title.add("操作意见：");
                content.add(ReceiveDetailBean.receiveDetailBean.getResult().getOpnnList().get(i).getOperationName());
                content.add(ReceiveDetailBean.receiveDetailBean.getResult().getOpnnList().get(i).getActivityName());
                content.add(ReceiveDetailBean.receiveDetailBean.getResult().getOpnnList().get(i).getUserName());
                content.add(ReceiveDetailBean.receiveDetailBean.getResult().getOpnnList().get(i).getUpdateAt());
                content.add(ReceiveDetailBean.receiveDetailBean.getResult().getOpnnList().get(i).getOpinion());
                item.setTitleList(title);
                item.setContentList(content);
                lcGraphItems.add(item);
            }
        } else if ("发文管理".equals(docType) && SendDetailBean.sendDetailBean != null
                && SendDetailBean.sendDetailBean.getResult().getOpnnList() != null) {
            for (int i = 0; i < SendDetailBean.sendDetailBean.getResult().getOpnnList().size(); i++) {
                LcGraphItem item = new LcGraphItem();
                List<String> title = new ArrayList<>();
                List<String> content = new ArrayList<>();
                title.add("办理结果：");
                title.add("环节：");
                title.add("操作人：");
                title.add("操作时间：");
                title.add("操作意见：");
                content.add(SendDetailBean.sendDetailBean.getResult().getOpnnList().get(i).getOperationName());
                content.add(SendDetailBean.sendDetailBean.getResult().getOpnnList().get(i).getActivityName());
                content.add(SendDetailBean.sendDetailBean.getResult().getOpnnList().get(i).getUserName());
                content.add(SendDetailBean.sendDetailBean.getResult().getOpnnList().get(i).getUpdateAt());
                content.add(SendDetailBean.sendDetailBean.getResult().getOpnnList().get(i).getOpinion());
                item.setTitleList(title);
                item.setContentList(content);
                lcGraphItems.add(item);
            }
        }
        lcGraphAdapter.notifyItemRangeInserted(0, lcGraphItems.size());
        if(lcGraphItems.size()==0){
            viewError.setVisibility(View.VISIBLE);
        }
    }

    private void getLZJL() {
        int curSize = lcGraphItems.size();
        lcGraphItems.clear();
        lcGraphAdapter.notifyItemRangeRemoved(0, curSize);
        String caseId = Hawk.get("TSJBXQ_caseId");
        Call<CaseBean> call = ApiManager.ynApi.getCaseInfo(caseId, "1");
        call.enqueue(new Callback<CaseBean>() {
            @Override
            public void onResponse(Response<CaseBean> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    CaseBean result = response.body();
                    if (result.getCode() == 200) {
                        for (CnOpinion cn :
                                result.getResult().getCnOpinions()) {
                            LcGraphItem item = new LcGraphItem();
                            List<String> title = new ArrayList<>();
                            List<String> content = new ArrayList<>();
                            title.add("环节：");
                            title.add("操作结果：");
                            title.add("操作机关：");
                            title.add("操作部门：");
                            title.add("操作人：");
                            title.add("操作时间：");
                            title.add("操作意见：");
                            content.add(cn.getActnId());
                            content.add(cn.getResultId());
                            content.add(cn.getOrganId());
                            content.add(cn.getDeptName());
                            content.add(cn.getPer());
                            content.add(cn.getDealDate());
                            content.add(cn.getResultName());
                            item.setTitleList(title);
                            item.setContentList(content);
                            lcGraphItems.add(item);
                        }
                        lcGraphAdapter.notifyItemRangeInserted(0, lcGraphItems.size());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
