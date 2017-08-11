package com.wondersgroup.commerce.teamwork.wywork.jyqx.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.Bean.JyqxdqJbxxBean;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.JyqxdqGlxxAdapter;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.KeyPersonAdapter;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.QYXXActivity;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wy on 2016/4/14.
 */
public class JbxxFragment extends Fragment implements View.OnClickListener{

    private TextView    tvZch;
    private TextView    tvQymc;
    private TextView    tvLx;
    private TextView    tvFddbr;
    private TextView    tvZczb;
    private TextView    tvClrq;
    private TextView    tvDjjg;
    private TextView    tvFzrq;
    private TextView    tvJyqxS;
    private TextView    tvJyqxE;
    private TextView    tvAddress;
    private TextView    tvJyfw;
    private TextView    tvJyzt;
    private ImageView   ivOne;
    private ImageView   ivTwo;
    private ImageView   ivThree;
    private RelativeLayout  rl1;
    private RelativeLayout  rl2;
    private RelativeLayout  rl3;


    private String entityId;
    private JyqxdqJbxxBean  jyqxdqJbxxBean;
    private JyqxdqGlxxAdapter adapter;

    private LinearLayout ll_keyperson;
    private LinearLayout    ll;
    private ListView lvTzr;
    private ListView lvZyry;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view   =   inflater.inflate(R.layout.fragment_qyxx_jbxx,container,false);
        initData(view);
        init();
        return view;
    }

    private void initData(View view) {
        tvZch   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_zch);
        tvQymc   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_qymc);
        tvLx   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_lx);
        tvFddbr   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_fddbr);
        tvZczb   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_zczb);
        tvClrq   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_clrq);
        tvDjjg   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_djjg);
        tvFzrq   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_fzrq);
        tvJyqxS   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_jyqx_start);
        tvJyqxE   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_jyqx_end);
        tvAddress   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_address);
        tvJyfw   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_jyfw);
        tvJyzt   =   (TextView)view.findViewById(R.id.fragment_jbxx_tv_jyzt);

        ivOne   =   (ImageView)view.findViewById(R.id.jbxx_iv_one);
        ivTwo   =   (ImageView)view.findViewById(R.id.jbxx_iv_two);
        ivThree   =   (ImageView)view.findViewById(R.id.jbxx_iv_three);

        ll_keyperson = (LinearLayout)view.findViewById(R.id.ll_keyperson);
        ll  =   (LinearLayout)view.findViewById(R.id.jbxx_ll_one);
        lvTzr   =   (ListView)view.findViewById(R.id.jbxx_tzr_listview);
        lvZyry   =   (ListView)view.findViewById(R.id.jbxx_zyry_listview);

        rl1 =   (RelativeLayout)view.findViewById(R.id.rl_open_close1);
        rl2 =   (RelativeLayout)view.findViewById(R.id.rl_open_close2);
        rl3 =   (RelativeLayout)view.findViewById(R.id.rl_open_close3);

        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
    }

    private void init() {
        entityId = getActivity().getIntent().getStringExtra("entityId");
        callNet();
    }

    private void callNet() {
        MyProgressDialog.show(getActivity());
        HashMap<String,String>  map =  new HashMap<String,String>();
        map.put("entityId", entityId);
        Call<JyqxdqJbxxBean> call = ApiManager.shApi.jyqxdq_jbxx(map);
        call.enqueue(new Callback<JyqxdqJbxxBean>() {
            @Override
            public void onResponse(Response<JyqxdqJbxxBean> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                if (response.isSuccess()) {
                    jyqxdqJbxxBean = response.body();
                    ((QYXXActivity) getActivity()).setUrl(jyqxdqJbxxBean.getValues().getUrl());
                    initTextView();
                    initListView();
                } else {
                    MyProgressDialog.dismiss();
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initListView() {
        //投资人信息
        ArrayList<ArrayList<String>> ass1    =   new ArrayList<>();
        for (int i=0;i<jyqxdqJbxxBean.getValues().getInvestorEnty().size();i++){
            ArrayList<String> as    =   new ArrayList<>();
            as.add("投资人类型" + "##" + jyqxdqJbxxBean.getValues().getInvestorEnty().get(i).getEtpsTypeGb());
            as.add("投资人" + "##" + jyqxdqJbxxBean.getValues().getInvestorEnty().get(i).getInvestorName());
            as.add("证照类型" + "##" + jyqxdqJbxxBean.getValues().getInvestorEnty().get(i).getCetfType());
            as.add("证照号码" + "##" + jyqxdqJbxxBean.getValues().getInvestorEnty().get(i).getCetfId());
            as.add("出资方式" + "##" + "货币");
            as.add("认缴出资总额" + "##" + jyqxdqJbxxBean.getValues().getInvestorEnty().get(i).getCptl());
            as.add("实际出资额" + "##" + jyqxdqJbxxBean.getValues().getInvestorEnty().get(i).getActlInvt());
            as.add("flag");
            ass1.add(as);
        }
        adapter =   new JyqxdqGlxxAdapter(ass1,getActivity());
        lvTzr.setAdapter(adapter);

        //主要人员信息
      /*  ArrayList<ArrayList<String>> ass2    =   new ArrayList<>();
        for (int i=0;i<jyqxdqJbxxBean.getValues().getEtpsMemberEnty().size();i++){
            ArrayList<String> as    =   new ArrayList<>();
            as.add("序号" + "##" + i);
            as.add("姓名" + "##" + jyqxdqJbxxBean.getValues().getEtpsMemberEnty().get(i).getPersonName());
            as.add("职务" + "##" + jyqxdqJbxxBean.getValues().getEtpsMemberEnty().get(i).getHdshGb());
            as.add("flag");
            ass2.add(as);
        }
        adapter =   new JyqxdqGlxxAdapter(ass2,getActivity());
        lvZyry.setAdapter(adapter);
*/
        KeyPersonAdapter keyPersonAdapter = new KeyPersonAdapter(jyqxdqJbxxBean, getActivity());
        lvZyry.setAdapter(keyPersonAdapter);

    }

    private void initTextView() {

        tvZch.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getRegNo());
        tvQymc.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getEtpsName());
        tvLx.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getEtpsTypeGb());
        tvFddbr.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getLeaderName());
        tvZczb.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getPartForm());
        tvClrq.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getEstablishDate());
        tvDjjg.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getRegOrganId());
        tvFzrq.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getCanBranch());
        tvJyqxS.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getTradeStartDate());
        tvJyqxE.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getTradeEndDate());
        tvAddress.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getOperateLocation());
        tvJyfw.setText(jyqxdqJbxxBean.getValues().getEtpsTradeInfoEnty().getTrdScope());
        tvJyzt.setText(jyqxdqJbxxBean.getValues().getEtpsInfoEnty().getOperateState());
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(getActivity(),"主要人员"+lvZyry.getCount()+":"+"投资人"+lvTzr.getCount(),Toast.LENGTH_LONG).show();
        switch (v.getId()){
            case R.id.rl_open_close1:
                VisibleOrGone(ll, ivOne);break;
            case R.id.rl_open_close2:
                if(lvTzr.getCount()>0) {
                    VisibleOrGone(lvTzr, ivOne);
                }else{
                    Toast.makeText(getActivity(),"投资人信息无记录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_open_close3:
                if(lvZyry.getCount()>0) {

                    if(lvZyry.getVisibility()==View.GONE){
                        lvZyry.setVisibility(View.VISIBLE);
                        ivTwo.setBackgroundResource(R.drawable.listview_open);
                        ll_keyperson.setVisibility(View.VISIBLE);
                    }else {
                        lvZyry.setVisibility(View.GONE);
                        ivTwo.setBackgroundResource(R.drawable.listview_close);
                        ll_keyperson.setVisibility(View.GONE);
                    }

                }else{
                    Toast.makeText(getActivity(),"主要人员信息无记录",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void VisibleOrGone(View v,ImageView iv) {

        if(v.getVisibility()==View.GONE){
            iv.setBackgroundResource(R.drawable.listview_open);
            v.setVisibility(View.VISIBLE);
        }else{
            iv.setBackgroundResource(R.drawable.listview_close);
            v.setVisibility(View.GONE);
        }
    }
}


