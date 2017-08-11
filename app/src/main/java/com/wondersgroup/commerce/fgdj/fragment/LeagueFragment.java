package com.wondersgroup.commerce.fgdj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.activity.LeagueMemAddActivity;
import com.wondersgroup.commerce.fgdj.adapter.LeagueMemAdapter;
import com.wondersgroup.commerce.fgdj.bean.BaseInfoBean;
import com.wondersgroup.commerce.fgdj.bean.LeagueMem;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/4/27.
 */

public class LeagueFragment extends Fragment{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.layout_add)
    ViewGroup layoutAdd;
    @Bind(R.id.layout_add_bg)
    ViewGroup layoutAddBg;

    private LeagueMemAdapter adapter;
    private List<LeagueMem> mData;
    private String entId;
    private String operation;
    private TotalLoginBean loginBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuan_yuan,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        entId = getArguments().getString(Constants.ENT_ID);
        operation = getArguments().getString(Constants.OPERATION);
        if (Constants.EDIT.equals(operation))
            layoutAddBg.setVisibility(View.VISIBLE);
        layoutAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LeagueMemAddActivity.class);
                startActivityForResult(intent, Constants.REQUEST_ADD);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mData = new ArrayList();
        adapter = new LeagueMemAdapter(mData,getActivity());
        recyclerView.setAdapter(adapter);

        getData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((Constants.REQUEST_ADD == requestCode) && (Constants.RESPONSE_ADD == resultCode)){
            LeagueMem leagueMem = data.getParcelableExtra(Constants.LEAGUE_MEM);
            mData.add(0,leagueMem);
            adapter.notifyItemChanged(0);
        }
    }

    private void getData(){
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.ENT_ID,entId);
        hashMap.put(Constants.MODULE_ID,"05");
        hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
        hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
        hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());

        Call<Result<BaseInfoBean>> call = ApiManager.fgdjApi.getFgdjEntDetail(hashMap);
        call.enqueue(new Callback<Result<BaseInfoBean>>() {
            @Override
            public void onResponse(Response<Result<BaseInfoBean>> response, Retrofit retrofit) {
                if (response.body().getCode() == 0) {
                    mData.addAll(response.body().getObject().getLeagueMems());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
