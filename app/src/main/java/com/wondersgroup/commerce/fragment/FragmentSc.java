package com.wondersgroup.commerce.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.MenuAdapter;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.adapter.FirstPageAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Ggcx;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.model.MenuInfo;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.tztg.GGDetailActivity;
import com.wondersgroup.commerce.teamwork.tztg.TZTGActivity;
import com.wondersgroup.commerce.utils.FragmentHelper;
import com.wondersgroup.commerce.widget.MyProgressDialog;

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
 * Created by bjy on 2017/6/29.
 * 四川--首页
 */

public class FragmentSc extends Fragment implements View.OnClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_gg)
    TextView tvGG;
    @BindView(R.id.tv_option)
    TextView tvOption;
    @BindView(R.id.img_mine)
    ImageView imgMine;

    private List<MenuInfo> data;
    private MenuAdapter adapter;
    private TotalLoginBean loginBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sc, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        data = Hawk.get(Constants.MENU_SC);
        if (data == null)
            data = new ArrayList<>();

        adapter = new MenuAdapter(getActivity(), data, 2);
        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        getTZGG();
        tvOption.setOnClickListener(this);
        imgMine.setOnClickListener(this);
    }

    private void getTZGG() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010001");
        map.put("userId", loginBean.getResult().getUserId());
        map.put("deptId", loginBean.getResult().getDeptId());
        map.put("organId", loginBean.getResult().getOrganId());
        Call<Ggcx> call = ApiManager.oaApi.getBulletinList(map);
        call.enqueue(new Callback<Ggcx>() {
            @Override
            public void onResponse(Response<Ggcx> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    Ggcx ggcx = response.body();
                    if ((null == ggcx) || (null == ggcx.getResult())) {
                        return;
                    }
                    if (ggcx.getResult().getBulletinInfo() != null) {
                        if (ggcx.getResult().getBulletinInfo().size() != 0) {
                            Ggcx.Result.ggmsg data = ggcx.getResult().getBulletinInfo().get(0);
                            tvGG.setText(data.getTitle());
                            tvGG.setTag(data);
                            tvGG.setOnClickListener(FragmentSc.this);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_option:
                intent = new Intent(getActivity(), TZTGActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_gg:
                Ggcx.Result.ggmsg data = (Ggcx.Result.ggmsg) v.getTag();
                intent = new Intent(getActivity(), GGDetailActivity.class);
                intent.putExtra("bulletinId", data.getBulletinId());
                startActivity(intent);
                break;
            case R.id.img_mine:
                FragmentSetting fragmentSetting = new FragmentSetting();
                Bundle bundle = new Bundle();
                bundle.putBoolean("IS_SC", true);//是否是四川
                fragmentSetting.setArguments(bundle);
                FragmentHelper.replaceFragment(getFragmentManager(), fragmentSetting, "", null, R.id.layout_fragment);
                break;
        }
    }
}
