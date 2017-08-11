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
import com.wondersgroup.commerce.fgdj.activity.PartyMemAddActivity;
import com.wondersgroup.commerce.fgdj.adapter.PartyMemAdapter;
import com.wondersgroup.commerce.fgdj.bean.BaseInfoBean;
import com.wondersgroup.commerce.fgdj.bean.PartyMem;
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

public class PartyMemFragment extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.layout_add)
    ViewGroup layoutAdd;
    @Bind(R.id.layout_add_bg)
    ViewGroup layoutAddBg;

    private PartyMemAdapter adapter;
    private List<PartyMem> mData;
    private String entId;
    private String operation;
    private TotalLoginBean loginBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangyuan,container,false);
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
                Intent intent = new Intent(getActivity(),PartyMemAddActivity.class);
                intent.putExtra(Constants.TYPE, Constants.ADD);
                startActivityForResult(intent, Constants.REQUEST_ADD);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        mData = getArguments().getParcelableArrayList(Constants.PARTY_MEMS);
        if (mData == null)
            mData = new ArrayList();
        adapter = new PartyMemAdapter(mData,getActivity());
        adapter.setOnClickListener(new PartyMemAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(),PartyMemAddActivity.class);
                intent.putExtra(Constants.DATA,mData.get(position));
                intent.putExtra(Constants.TYPE, operation);
                intent.putExtra(Constants.POSITION,position);
                startActivityForResult(intent, Constants.REQUEST_EDIT);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((Constants.REQUEST_ADD == requestCode) && (Constants.RESPONSE_ADD == resultCode)){
            PartyMem partyMem = data.getParcelableExtra(Constants.PARTY_MEM);
            mData.add(0,partyMem);
            adapter.notifyItemChanged(0);
        }else if (Constants.REQUEST_EDIT == requestCode && Constants.RESPONSE_EDIT == resultCode){
            int position = data.getIntExtra(Constants.POSITION,0);
            mData.set(position,(PartyMem) data.getParcelableExtra(Constants.PARTY_MEM));
            adapter.notifyItemChanged(position);
        }
    }

    public List<PartyMem> getPartyMems(){
        return mData;
    }
}
