package com.wondersgroup.commerce.ynwq.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.ynwq.adapter.JingBanAdapter;
import com.wondersgroup.commerce.ynwq.adapter.XiaoWeiAdapter;
import com.wondersgroup.commerce.ynwq.bean.JingBanItem;
import com.wondersgroup.commerce.ynwq.bean.JingBanResult;
import com.wondersgroup.commerce.ynwq.bean.XiaoWeiItem;
import com.wondersgroup.commerce.ynwq.bean.XiaoWeiResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProcessFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * 小微企业信息、经办信息
 */
public class ProcessFragment extends Fragment {
    @BindView(R.id.process_recycler)RecyclerView recycler;
    @BindView(R.id.layout_error)
    View layoutError;
    private JingBanAdapter jingBanAdapter;
    private XiaoWeiAdapter xiaoWeiAdapter;

    private List<JingBanItem> jingBanItems;
    private List<XiaoWeiItem> xiaoWeiItems;

    private static final String ARG_TYPE = "type";
    private static final String ARG_ID="queryId";
    private String type;
    private String id;
    private boolean needFetch=false;

    public ProcessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type Parameter 1.
     * @return A new instance of fragment ProcessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProcessFragment newInstance(String type,String id) {
        ProcessFragment fragment = new ProcessFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        args.putString(ARG_ID,id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
            id=getArguments().getString(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_process, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setItemAnimator(new SlideInUpAnimator());
        if(needFetch){
            fetchData();
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
    public void fetchData(){
        if("BLXX".equals(type)) {
            jingBanItems=new ArrayList<>();
            jingBanAdapter=new JingBanAdapter(jingBanItems);
            recycler.setAdapter(jingBanAdapter);
            Map<String,String> body=new HashMap<>();
            body.put("meId",id);
            body.put("wsCodeReq","01100001");
            Call<JingBanResult> call= ApiManager.ynWqApi.getOpFlow(body);
            call.enqueue(new Callback<JingBanResult>() {
                @Override
                public void onResponse(Response<JingBanResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        JingBanResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            int curSize=jingBanAdapter.getItemCount();
                            jingBanItems.clear();
                            jingBanAdapter.notifyItemRangeRemoved(0, curSize);
                            jingBanItems.addAll(result.getResult());
                            jingBanAdapter.notifyItemRangeInserted(0,jingBanItems.size());
                        }else {
                            layoutError.setVisibility(View.VISIBLE);
                        }
                    }else {
                        layoutError.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    layoutError.setVisibility(View.VISIBLE);
                }
            });
            /*jingBanAdapter = new JingBanAdapter(OfflineData.jingBanItems);
            recycler.setAdapter(jingBanAdapter);*/
        }else if("XWXQ".equals(type)||"JBXX".equals(type)){
            xiaoWeiItems=new ArrayList<>();
            xiaoWeiAdapter=new XiaoWeiAdapter(xiaoWeiItems);
            recycler.setAdapter(xiaoWeiAdapter);
            Map<String,String> body=new HashMap<>();
            body.put("etpsId",id);
            body.put("wsCodeReq","01100001");
            Log.e("bjy","bjy");
            Call<XiaoWeiResult> call=ApiManager.ynWqApi.getEntInfo(body);
            call.enqueue(new Callback<XiaoWeiResult>() {
                @Override
                public void onResponse(Response<XiaoWeiResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        XiaoWeiResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            int curSize=xiaoWeiAdapter.getItemCount();
                            xiaoWeiItems.clear();
                            xiaoWeiAdapter.notifyItemRangeRemoved(0, curSize);
                            xiaoWeiItems.addAll(result.getResult());
                            xiaoWeiAdapter.notifyItemRangeInserted(0,xiaoWeiItems.size());
                        }else {
                            layoutError.setVisibility(View.VISIBLE);
                        }
                    }else {
                        layoutError.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    layoutError.setVisibility(View.VISIBLE);
                }
            });
            /*xiaoWeiAdapter=new XiaoWeiAdapter(OfflineData.xiaoWeiItems);
            recycler.setAdapter(xiaoWeiAdapter);*/
        }else if("FCQK".equals(type)){
            xiaoWeiItems=new ArrayList<>();
            xiaoWeiAdapter=new XiaoWeiAdapter(xiaoWeiItems);
            recycler.setAdapter(xiaoWeiAdapter);
            Map<String,String> body=new HashMap<>();
            body.put("supportId",id);
            body.put("wsCodeReq","01100001");
            Call<XiaoWeiResult> call=ApiManager.ynWqApi.getSupportInfo(body);
            call.enqueue(new Callback<XiaoWeiResult>() {
                @Override
                public void onResponse(Response<XiaoWeiResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        XiaoWeiResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            int curSize=xiaoWeiAdapter.getItemCount();
                            xiaoWeiItems.clear();
                            xiaoWeiAdapter.notifyItemRangeRemoved(0,curSize);
                            xiaoWeiItems.addAll(result.getResult());
                            xiaoWeiAdapter.notifyItemRangeInserted(0, xiaoWeiItems.size());
                        }else {
                            layoutError.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    layoutError.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
