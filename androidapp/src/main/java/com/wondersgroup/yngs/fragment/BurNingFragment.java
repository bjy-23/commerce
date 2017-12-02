
package com.wondersgroup.yngs.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.activity.LoginActivity;
import com.wondersgroup.yngs.activity.ToDoActivity;
import com.wondersgroup.yngs.adapter.BurNingRecyclerAdapter;
import com.wondersgroup.yngs.entity.BurNingItem;
import com.wondersgroup.yngs.entity.BurNingResult;
import com.wondersgroup.yngs.service.ApiManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/*
* 首页
* */


public class BurNingFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.buring_recycler)RecyclerView burNing;
    @Bind(R.id.net_area)RelativeLayout netArea;
    @Bind(R.id.net_msg)TextView netMsg;
    @Bind(R.id.home_navi_first)Button btnF;
    @Bind(R.id.home_navi_second)Button btnS;
    @Bind(R.id.home_navi_third)Button btnT;
    private static final String ARG_PARAM1 = "param1";
    private BurNingRecyclerAdapter adapter;
    private List<BurNingItem> items;
    private boolean needFetch=false;

    // TODO: Rename and change types of parameters
    private String mParam1;

    private String deptId;
    private String deptName;
    private String organId;
    private String organName;
    private String userId;
    private int selection=-1;
    private SharedPreferences sp;
    //private List<String> deptNames;

    public interface HomeButtonClick{
        void onHomeBtnClicked(int id);
    }

    HomeButtonClick mCallback;

    public BurNingFragment() {
        // Required empty public constructor
    }

    public static BurNingFragment newInstance(String param1) {
        BurNingFragment fragment = new BurNingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bur_ning, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sp=getContext().getSharedPreferences("Default", Context.MODE_PRIVATE);
        userId=sp.getString("userId", "");
        deptId=sp.getString("deptId", "");
        deptName=sp.getString("deptName", "");
        organId=sp.getString("organId", "");
        organName=sp.getString("organName", "");
        selection=sp.getInt("selection", 0);

        burNing.setHasFixedSize(true);
        RecyclerView.LayoutManager manager= new LinearLayoutManager(getContext());
        burNing.setLayoutManager(manager);
        burNing.setItemAnimator(new SlideInUpAnimator());
        items=new ArrayList<>();
        btnF.setOnClickListener(this);
        btnS.setOnClickListener(this);
        btnT.setOnClickListener(this);
        adapter=new BurNingRecyclerAdapter(items);
        adapter.setOnItemClickListener(new BurNingRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(getContext(), ToDoActivity.class);
                HashMap<String, String> body = new HashMap<>();
                body.put("userId", userId);
                body.put("deptId", deptId.split(",")[selection]);
                body.put("organId", organId.split(",")[selection]);
                body.put("flowStatus", items.get(position).getFlowStatus());
                intent.putExtra("body", body);
                intent.putExtra("title", items.get(position).getTitle());
                startActivity(intent);
            }
        });
        burNing.setAdapter(adapter);

        /*deptNames=new ArrayList<>();
        deptNames.addAll(Arrays.asList(deptName.split(",")));
        spinnerAdapter=new ArrayAdapter<>(getContext(),R.layout.view_spinner_text,deptNames);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(selection);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = position;
                SharedPreferences.Editor ed = sp.edit();
                ed.putInt("selection", position);
                ed.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getUserInfo();*/
        if(needFetch){
            fetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            if (getView()!=null) {
                fetchData();
                needFetch=false;
            }else {
                needFetch=true;
            }
        }
    }
    public void fetchData(){
        if(selection!=-1&&deptId!=null){
            Map<String,String> body =new HashMap<>();
            body.put("userId",userId);
            body.put("deptId", deptId.split(",")[selection]);
            body.put("organId", organId.split(",")[selection]);
            body.put("wsCodeReq",ApiManager.getWsCodeReq());
            Call<BurNingResult> call=ApiManager.yunNanApi.getToDo(body);
            call.enqueue(new Callback<BurNingResult>() {
                @Override
                public void onResponse(final Response<BurNingResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        final BurNingResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            netArea.setVisibility(View.GONE);
                            int curSize=adapter.getItemCount();
                            items.clear();
                            adapter.notifyItemRangeRemoved(0, curSize);
                            items.addAll(result.getResult());
                            adapter.notifyItemRangeInserted(0, items.size());
                        }else if("403".equals(result.getResultCode())) {
                            new MaterialDialog.Builder(getContext())
                                    .title("登录过期")
                                    .content("请重新登录")
                                    .positiveText("确定")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            SharedPreferences sp=getContext().getSharedPreferences("Default",Context.MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.clear();
                                            ed.apply();
                                            startActivity(new Intent(getContext(), LoginActivity.class));
                                            getActivity().finish();
                                        }
                                    })
                                    .cancelable(false)
                                    .show();
                        }else {
                            netArea.setVisibility(View.VISIBLE);
                            netMsg.setText(result.getMessage());
                        }
                    }else {
                        netArea.setVisibility(View.VISIBLE);
                        netMsg.setText("加载失败");
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    netArea.setVisibility(View.VISIBLE);
                    netMsg.setText("加载失败");
                }
            });
        }

    }
    /*
    public void getUserInfo(){
        Map<String,String> body=new HashMap<>();
        //final SharedPreferences sp=getContext().getSharedPreferences("Default", Context.MODE_PRIVATE);
        *//*userId=sp.getString("userId","");*//*
        body.put("userId", userId);
        body.put("wsCodeReq",ApiManager.getWsCodeReq());
        //selection=sp.getInt("selection",-1);
        Call<UserResult> call= ApiManager.yunNanApi.getUserInfo(body);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Response<UserResult> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    UserResult result=response.body();
                    if("200".equals(result.getResultCode())){
                        userName.setText(result.getResult().getUserName());
                        Log.e("BurNing/Name",result.getResult().getUserName());
                        if(deptId.equals(result.getResult().getDeptId())) return;

                        deptId=result.getResult().getDeptId();
                        deptName=result.getResult().getDeptName();
                        organId= result.getResult().getOrganId();
                        organName=result.getResult().getOrganName();

                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("deptId",deptId);
                        ed.putString("organId",organId);
                        ed.putString("deptName",deptName);
                        ed.putString("organName",organName);
                        ed.apply();

                        deptNames.clear();
                        deptNames.addAll(Arrays.asList(deptName.split(",")));
                        spinnerAdapter.notifyDataSetChanged();
                        *//*String[] deptNames=result.getResult().getDeptName().split(",");*//*


                        if(deptNames.size()>1){
                            new MaterialDialog.Builder(getContext())
                                    .title("请重新选择部门")
                                    .items(deptName.split(","))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                            spinner.setSelection(which);
                                            selection = which;
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.putInt("selection", which);
                                            ed.apply();
                                            return true;
                                        }
                                    })
                                    .cancelable(false)
                                    .positiveText("确定")
                                    .show();
                        }else if(deptNames.size()==1){
                            if(selection==-1)selection=0;
                            SharedPreferences.Editor ed2=sp.edit();
                            ed2.putInt("selection",selection);
                            ed2.apply();
                        }
                        fetchData();
                    }else if("403".equals(result.getResultCode())){
                        new MaterialDialog.Builder(getContext())
                                .title("登录过期")
                                .content("请重新登录")
                                .positiveText("确定")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        SharedPreferences.Editor ed3=sp.edit();
                                        ed3.clear();
                                        ed3.apply();
                                        startActivity(new Intent(getContext(), LoginActivity.class));
                                        getActivity().finish();
                                    }
                                })
                                .cancelable(false)
                                .show();
                    }else {
                        new MaterialDialog.Builder(getContext())
                                .title("出错")
                                .content(result.getMessage())
                                .positiveText("确定")
                                .show();
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback=(HomeButtonClick)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnF)||v.equals(btnS)||v.equals(btnT)){
            mCallback.onHomeBtnClicked(v.getId());
        }
    }
}
