package com.wondersgroup.yngs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.adapter.MainPagerAdapter;
import com.wondersgroup.yngs.entity.DicResult;
import com.wondersgroup.yngs.fragment.BurNingFragment;
import com.wondersgroup.yngs.fragment.ViewPagerFragment;
import com.wondersgroup.yngs.service.ApiManager;
import com.wondersgroup.yngs.service.MsgIntentService;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements BurNingFragment.HomeButtonClick{

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.main_tab) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;

    private Toolbar toolbar2;

    private MainPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        for(int i=0; i<MainPagerAdapter.NUM; i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }
        tabLayout.getTabAt(0).getCustomView().setSelected(true);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
                getSupportActionBar().setTitle(pagerAdapter.getPageTitle(tab.getPosition()));
                if (tab.getPosition() == 0) {
                    //toolbar.setVisibility(View.GONE);
                    getSupportActionBar().hide();
                } else {
                    //toolbar.setVisibility(View.VISIBLE);
                    getSupportActionBar().show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        SharedPreferences sp=getSharedPreferences("Default",MODE_PRIVATE);
        int selection=sp.getInt("selection", 0);
        Hawk.put("organId",sp.getString("organId","").split(",")[selection]);
        boolean receivePush=sp.getBoolean("receivePush", true);
        if(receivePush)MsgIntentService.startActionMsg(this,5);
        if(Hawk.get("Dic")==null){
            Map<String,String>body =new HashMap<>();
            body.put("deptId",sp.getString("deptId","").split(",")[selection]);
            body.put("organId",sp.getString("organId","").split(",")[selection]);
            body.put("wsCodeReq",ApiManager.getWsCodeReq());
            Call<DicResult> call = ApiManager.yunNanApi.getDic(body);
            call.enqueue(new Callback<DicResult>() {
                @Override
                public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        DicResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            Hawk.put("Dic",result.getResult());
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    @Override
    public void onHomeBtnClicked(int id) {
        Intent intent=new Intent(ViewPagerFragment.ACTION_INTENT);
        switch (id){
            case R.id.home_navi_first:
                tabLayout.getTabAt(1).select();
                intent.putExtra("selectPage",0);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                break;
            case R.id.home_navi_second:
                tabLayout.getTabAt(1).select();
                intent.putExtra("selectPage", 1);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                break;
            case R.id.home_navi_third:
                tabLayout.getTabAt(1).select();
                intent.putExtra("selectPage", 2);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                break;
        }
    }
}
