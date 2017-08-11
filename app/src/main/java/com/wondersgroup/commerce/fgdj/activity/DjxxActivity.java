package com.wondersgroup.commerce.fgdj.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.adapter.DjxxAdapter;
import com.wondersgroup.commerce.fgdj.bean.BaseInfoBean;
import com.wondersgroup.commerce.fgdj.bean.LeagueMem;
import com.wondersgroup.commerce.fgdj.bean.PartyMem;
import com.wondersgroup.commerce.fgdj.fragment.BaseInfoEditFragment;
import com.wondersgroup.commerce.fgdj.fragment.BaseInfoFragment;
import com.wondersgroup.commerce.fgdj.fragment.DangzuzhiEditFragment;
import com.wondersgroup.commerce.fgdj.fragment.DangzuzhiFragment;
import com.wondersgroup.commerce.fgdj.fragment.LeagueMemFragment;
import com.wondersgroup.commerce.fgdj.fragment.PartyMemFragment;
import com.wondersgroup.commerce.fgdj.fragment.TuanzuzhiEditFragment;
import com.wondersgroup.commerce.fgdj.fragment.TuanzuzhiFragment;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.utils.DWZH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DjxxActivity extends AppCompatActivity implements BaseInfoEditFragment.FragmentListener{
    @Bind(R.id.layout_tab)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.move_bg)
    View moveBg;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_op)
    ImageView imgOp;
    @Bind(R.id.img_back)
    ImageView imgBack;

    private DjxxAdapter djxxAdapter;
    private FragmentManager fm;
    private List<Fragment> fragments;
    private String entId,entType;
    private String operation;
    private TotalLoginBean loginBean;
    private boolean isAddParty,isAddLeague;
    private BaseInfoBean baseInfoBean;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_djxx);
        ButterKnife.bind(this);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        entId = getIntent().getStringExtra(Constants.ENT_ID);
        entType = getIntent().getStringExtra(Constants.ENT_TYPE);
        isAddParty = getIntent().getBooleanExtra(Constants.ADD_PARTY,false);
        isAddLeague = getIntent().getBooleanExtra(Constants.ADD_LEAGUE,false);
        operation = getIntent().getStringExtra(Constants.OPERATION);
        baseInfoBean = getIntent().getParcelableExtra(Constants.BASE_INFO);
        fm = getSupportFragmentManager();
        fragments = new ArrayList<>();
        if (operation.equals(Constants.LOOK)){
            BaseInfoFragment baseInfoFragment = new BaseInfoFragment();
            Bundle bundleBase = new Bundle();
            bundleBase.putString(Constants.ENT_ID,entId);
            bundleBase.putString(Constants.ENT_TYPE,entType);
            bundleBase.putString(Constants.TITLE,"基本");
            bundleBase.putParcelable(Constants.ENT_BASE_INFO,baseInfoBean.getEntBaseInfo());
            bundleBase.putParcelable(Constants.LEADER_INFO,baseInfoBean.getLeaderInfo());
            baseInfoFragment.setArguments(bundleBase);
            fragments.add(baseInfoFragment);

            PartyMemFragment partyMemFragment = new PartyMemFragment();
            Bundle bundlePartyMem = new Bundle();
            bundlePartyMem.putString(Constants.ENT_ID,entId);
            bundlePartyMem.putString(Constants.TITLE,"党员");
            bundlePartyMem.putString(Constants.OPERATION, Constants.LOOK);
            bundlePartyMem.putParcelableArrayList(Constants.PARTY_MEMS,(ArrayList<PartyMem>) baseInfoBean.getPartyMems());
            partyMemFragment.setArguments(bundlePartyMem);
            fragments.add(partyMemFragment);

            LeagueMemFragment leagueMemFragment = new LeagueMemFragment();
            Bundle bundleLeagueMem = new Bundle();
            bundleLeagueMem.putString(Constants.ENT_ID,entId);
            bundleLeagueMem.putString(Constants.TITLE,"团员");
            bundleLeagueMem.putString(Constants.OPERATION, Constants.LOOK);
            bundleLeagueMem.putParcelableArrayList(Constants.LEAGUE_MEMS,(ArrayList<LeagueMem>) baseInfoBean.getLeagueMems());
            leagueMemFragment.setArguments(bundleLeagueMem);
            fragments.add(leagueMemFragment);

            if (isAddParty){
                DangzuzhiFragment dangzuzhiFragment = new DangzuzhiFragment();
                Bundle bundleParty = new Bundle();
                bundleParty.putString(Constants.ENT_ID,entId);
                bundleParty.putString(Constants.TITLE,"党组织");
                bundleParty.putParcelable(Constants.PARTY_INFO,baseInfoBean.getPartyInfo());
                dangzuzhiFragment.setArguments(bundleParty);
                fragments.add(1,dangzuzhiFragment);
            }

            if (isAddLeague){
                TuanzuzhiFragment tuanzuzhiFragment = new TuanzuzhiFragment();
                Bundle bundleLeague = new Bundle();
                bundleLeague.putString(Constants.ENT_ID,entId);
                bundleLeague.putString(Constants.TITLE,"团组织");
                bundleLeague.putParcelable(Constants.LEAGUE_INFO,baseInfoBean.getLeagueInfo());
                tuanzuzhiFragment.setArguments(bundleLeague);

                fragments.add(fragments.size()-1,tuanzuzhiFragment);
            }

        }else {
            imgOp.setVisibility(View.VISIBLE);

            BaseInfoEditFragment baseInfoEditFragment = new BaseInfoEditFragment();
            baseInfoEditFragment.setFragmentListener(this);
            Bundle bundleBase = new Bundle();
            bundleBase.putString(Constants.ENT_ID,entId);
            bundleBase.putString(Constants.ENT_TYPE,entType);
            bundleBase.putString(Constants.TITLE,"基本");
            bundleBase.putParcelable(Constants.ENT_BASE_INFO,baseInfoBean.getEntBaseInfo());
            bundleBase.putParcelable(Constants.LEADER_INFO,baseInfoBean.getLeaderInfo());
            baseInfoEditFragment.setArguments(bundleBase);
            fragments.add(baseInfoEditFragment);

            PartyMemFragment partyMemFragment = new PartyMemFragment();
            Bundle bundlePartyMem = new Bundle();
            bundlePartyMem.putString(Constants.ENT_ID,entId);
            bundlePartyMem.putString(Constants.TITLE,"党员");
            bundlePartyMem.putString(Constants.OPERATION, Constants.EDIT);
            bundlePartyMem.putParcelableArrayList(Constants.PARTY_MEMS,(ArrayList<PartyMem>) baseInfoBean.getPartyMems());
            partyMemFragment.setArguments(bundlePartyMem);
            fragments.add(partyMemFragment);

            LeagueMemFragment leagueMemFragment = new LeagueMemFragment();
            Bundle bundleLeagueMem = new Bundle();
            bundleLeagueMem.putString(Constants.ENT_ID,entId);
            bundleLeagueMem.putString(Constants.TITLE,"团员");
            bundleLeagueMem.putString(Constants.OPERATION, Constants.EDIT);
            bundleLeagueMem.putParcelableArrayList(Constants.LEAGUE_MEMS,(ArrayList<LeagueMem>) baseInfoBean.getLeagueMems());
            leagueMemFragment.setArguments(bundleLeagueMem);
            fragments.add(leagueMemFragment);

            if (isAddParty){
                DangzuzhiEditFragment dangzuzhiEditFragment = new DangzuzhiEditFragment();
                Bundle bundleParty = new Bundle();
                bundleParty.putString(Constants.ENT_ID,entId);
                bundleParty.putString(Constants.TITLE,"党组织");
                bundleParty.putParcelable(Constants.PARTY_INFO,baseInfoBean.getPartyInfo());
                dangzuzhiEditFragment.setArguments(bundleParty);
                fragments.add(1,dangzuzhiEditFragment);
            }

            if (isAddLeague){
                TuanzuzhiEditFragment tuanzuzhiEditFragment = new TuanzuzhiEditFragment();
                Bundle bundleLeague = new Bundle();
                bundleLeague.putString(Constants.ENT_ID,entId);
                bundleLeague.putString(Constants.TITLE,"团组织");
                bundleLeague.putParcelable(Constants.LEAGUE_INFO,baseInfoBean.getLeagueInfo());
                tuanzuzhiEditFragment.setArguments(bundleLeague);
                fragments.add(fragments.size()-1,tuanzuzhiEditFragment);
            }
        }

        djxxAdapter = new DjxxAdapter(fm,fragments);
        viewPager.setAdapter(djxxAdapter);
        viewPager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        imgOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view  = LayoutInflater.from(DjxxActivity.this).inflate(R.layout.popup_op,null,false);
                View layoutSave = view.findViewById(R.id.layout_save);
                layoutSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleData(0);
                    }
                });

                View layoutSubmit = view.findViewById(R.id.layout_submit);
                layoutSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleData(1);
                    }
                });
                popupWindow = new PopupWindow(view, DWZH.dp(140), DWZH.dp(91),true);
                popupWindow.showAsDropDown(imgOp);
                popupWindow.setTouchable(true);
            }
        });

        tvTitle.setText("非公党建信息");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void addParty() {
        if (!(fragments.get(1) instanceof DangzuzhiEditFragment)){
            viewPager.removeAllViewsInLayout();
            DangzuzhiEditFragment dangzuzhiEditFragment = new DangzuzhiEditFragment();
            Bundle bundleParty = new Bundle();
            bundleParty.putString(Constants.ENT_ID,entId);
            bundleParty.putString(Constants.TITLE,"党组织");
            bundleParty.putParcelable(Constants.PARTY_INFO,baseInfoBean.getPartyInfo());
            dangzuzhiEditFragment.setArguments(bundleParty);

            fragments.add(1,dangzuzhiEditFragment);
            djxxAdapter = new DjxxAdapter(fm,fragments);
            viewPager.setAdapter(djxxAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void delParty() {
        if (fragments.get(1) instanceof DangzuzhiEditFragment){
            viewPager.removeAllViewsInLayout();
            fragments.remove(1);
            djxxAdapter = new DjxxAdapter(fm,fragments);
            viewPager.setAdapter(djxxAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void addLeague() {
        if (!(fragments.get(fragments.size()-2) instanceof TuanzuzhiEditFragment)){
            viewPager.removeAllViewsInLayout();
            TuanzuzhiEditFragment tuanzuzhiEditFragment = new TuanzuzhiEditFragment();
            Bundle bundleLeague = new Bundle();
            bundleLeague.putString(Constants.ENT_ID,entId);
            bundleLeague.putString(Constants.TITLE,"团组织");
            bundleLeague.putParcelable(Constants.LEAGUE_INFO,baseInfoBean.getLeagueInfo());
            tuanzuzhiEditFragment.setArguments(bundleLeague);

            fragments.add(fragments.size()-1,tuanzuzhiEditFragment);
            djxxAdapter = new DjxxAdapter(fm,fragments);
            viewPager.setAdapter(djxxAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void delLeague() {
        if (fragments.get(fragments.size()-2) instanceof TuanzuzhiEditFragment){
            viewPager.removeAllViewsInLayout();
            fragments.remove(fragments.size()-2);
            djxxAdapter = new DjxxAdapter(fm,fragments);
            viewPager.setAdapter(djxxAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    public void handleData(final int type){
        BaseInfoBean baseInfoBean = new BaseInfoBean();
        BaseInfoEditFragment baseInfoEditFragment = (BaseInfoEditFragment) djxxAdapter.getItem(0);
        if (type == 1 && !baseInfoEditFragment.checkSubmit())
            return;
        baseInfoBean.setEntBaseInfo(baseInfoEditFragment.getEntBaseInfo());
        baseInfoBean.setLeaderInfo(baseInfoEditFragment.getLeaderInfo());

        LeagueMemFragment leagueMemFragment = (LeagueMemFragment) djxxAdapter.getItem(djxxAdapter.getCount()-1);
        baseInfoBean.setLeagueMems(leagueMemFragment.getLeagueMems());

        if (djxxAdapter.getCount() == 3){
            PartyMemFragment partyMemFragment = (PartyMemFragment) djxxAdapter.getItem(1);
            baseInfoBean.setPartyMems(partyMemFragment.getPartyMems());
        }else if (djxxAdapter.getCount() == 4){
            if((djxxAdapter.getItem(1)) instanceof DangzuzhiEditFragment){
                DangzuzhiEditFragment dangzuzhiEditFragment = (DangzuzhiEditFragment) djxxAdapter.getItem(1);
                if (type == 1 && !dangzuzhiEditFragment.checkSubmit())
                    return;
                baseInfoBean.setPartyInfo(dangzuzhiEditFragment.getPartyInfo());

                PartyMemFragment partyMemFragment = (PartyMemFragment) djxxAdapter.getItem(2);
                baseInfoBean.setPartyMems(partyMemFragment.getPartyMems());
            }else {
                PartyMemFragment partyMemFragment = (PartyMemFragment) djxxAdapter.getItem(1);
                baseInfoBean.setPartyMems(partyMemFragment.getPartyMems());

                TuanzuzhiEditFragment tuanzuzhiEditFragment = (TuanzuzhiEditFragment) djxxAdapter.getItem(2);
                if (type == 1 && !tuanzuzhiEditFragment.checkSubmit())
                    return;
                baseInfoBean.setLeagueInfo(tuanzuzhiEditFragment.getLeagueInfo());
            }
        }else {
            DangzuzhiEditFragment dangzuzhiEditFragment = (DangzuzhiEditFragment) djxxAdapter.getItem(1);
            if (type == 1 && !dangzuzhiEditFragment.checkSubmit())
                return;
            baseInfoBean.setPartyInfo(dangzuzhiEditFragment.getPartyInfo());

            PartyMemFragment partyMemFragment = (PartyMemFragment) djxxAdapter.getItem(2);
            baseInfoBean.setPartyMems(partyMemFragment.getPartyMems());

            TuanzuzhiEditFragment tuanzuzhiEditFragment = (TuanzuzhiEditFragment) djxxAdapter.getItem(3);
            if (type == 1 && !tuanzuzhiEditFragment.checkSubmit())
                return;
            baseInfoBean.setLeagueInfo(tuanzuzhiEditFragment.getLeagueInfo());
        }

        Gson gson = new Gson();
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.JSON,gson.toJson(baseInfoBean));
        hashMap.put(Constants.ENT_ID,entId);
        hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
        hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
        hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
        hashMap.put(Constants.TYPE,type+"");

        Call<Result> call = ApiManager.fgdjApi.saveFgdjEnt(hashMap);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Response<Result> response, Retrofit retrofit) {
                if (response.body().getCode() == 0){
                    popupWindow.dismiss();
                    if (type == 0)
                        Toast.makeText(DjxxActivity.this,"暂存成功",Toast.LENGTH_SHORT).show();
                    else{
                        Toast.makeText(DjxxActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(DjxxActivity.this,"连接服务器失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
