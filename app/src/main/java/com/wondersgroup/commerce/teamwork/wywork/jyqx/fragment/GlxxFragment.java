package com.wondersgroup.commerce.teamwork.wywork.jyqx.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.Bean.JyqxdqGlxxBean;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.JyqxdqGlxxAdapter;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.JyqxdqQyxxJgjlActivity;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wy on 2016/4/14.
 */
public class GlxxFragment extends Fragment implements View.OnClickListener{

    @Bind(R.id.fragment_glxx_tv_gllb)
    TextView tvGllb;
    @Bind(R.id.fragment_glxx_tv_xyfl)
    TextView tvXyfl;
    @Bind(R.id.fragment_glxx_tv_zdjgdx)
    TextView tvZdjgdx;
    @Bind(R.id.fragment_glxx_tv_tsbs)
    TextView tvTsbs;
    @Bind(R.id.fragment_glxx_tv_rdhy)
    TextView tvRdhy;
    @Bind(R.id.fragment_glxx_tv_zdqy)
    TextView tvZdqy;

//    @Bind(R.id.glxx_jgjl_listview)
//    ListView lvJgjl;
//    @Bind(R.id.glxx_njqk_listview)
//    ListView lvNjqk;
//    @Bind(R.id.glxx_jkzk_listview)
//    ListView lvJkzk;
//    @Bind(R.id.glxx_wfwg_listview)
//    ListView lvWfwg;
//    @Bind(R.id.glxx_frdbgj_listview)
//    ListView lvFrdbgj;
//    @Bind(R.id.glxx_frdbsh_listview)
//    ListView lvFrdbsh;
    private String entityId;
    private JyqxdqGlxxBean jyqxdqGlxxBean;
    private JyqxdqGlxxAdapter   glxxAdapter;
    private ArrayList<ArrayList<ArrayList<String>>> datas;

    private ImageView ivOne;
    private ImageView ivTwo;
    private ImageView ivThree;
    private ImageView ivFour;
    private ImageView ivFive;
    private ImageView ivSix;
    private ImageView ivServen;

    private LinearLayout llTop;
    private ListView lvJgjl;
    private ListView lvNjqk;
    private ListView lvJkzk;
    private ListView lvWfwg;
    private ListView lvFrdbgj;
    private ListView lvFrdbsh;

    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;
    private RelativeLayout rl4;
    private RelativeLayout rl5;
    private RelativeLayout rl6;
    private RelativeLayout rl7;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view   =   inflater.inflate(R.layout.fragment_qyxx_glxx,container,false);
//        ButterKnife.bind(view);
        initData(view);
        init();
        initListener();
        return view;
    }

    private void initListener() {
        lvJgjl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String planId   =   datas.get(0).get(position).get(5);
                Intent intent   =   new Intent(getActivity(),JyqxdqQyxxJgjlActivity.class);
                intent.putExtra("entityTypeId","02");
                intent.putExtra("actnId","0003");
                intent.putExtra("operType","05");
                intent.putExtra("entityId",entityId);
                intent.putExtra("planId",planId);
                startActivity(intent);
            }
        });
    }

    private void initData(View view) {

        //基本信息
        tvGllb  =  (TextView) view.findViewById(R.id.fragment_glxx_tv_gllb);
        tvXyfl  =  (TextView) view.findViewById(R.id.fragment_glxx_tv_xyfl);
        tvZdjgdx  =  (TextView) view.findViewById(R.id.fragment_glxx_tv_zdjgdx);
        tvTsbs  =  (TextView) view.findViewById(R.id.fragment_glxx_tv_tsbs);
        tvRdhy  =  (TextView) view.findViewById(R.id.fragment_glxx_tv_rdhy);
        tvZdqy  =  (TextView) view.findViewById(R.id.fragment_glxx_tv_zdqy);

        //布局
        llTop   =   (LinearLayout) view.findViewById(R.id.sl_ll_top);
        lvJgjl  =   (ListView) view.findViewById(R.id.glxx_jgjl_listview);
        lvNjqk  =   (ListView) view.findViewById(R.id.glxx_njqk_listview);
        lvJkzk  =   (ListView) view.findViewById(R.id.glxx_jkzk_listview);
        lvWfwg  =   (ListView) view.findViewById(R.id.glxx_wfwg_listview);
        lvFrdbgj  =   (ListView) view.findViewById(R.id.glxx_frdbgj_listview);
        lvFrdbsh  =   (ListView) view.findViewById(R.id.glxx_frdbsh_listview);

        ivOne   =  (ImageView) view.findViewById(R.id.sl_iv_one);
        ivTwo   =  (ImageView) view.findViewById(R.id.sl_iv_two);
        ivThree   =  (ImageView) view.findViewById(R.id.sl_iv_three);
        ivFour   =  (ImageView) view.findViewById(R.id.sl_iv_four);
        ivFive   =  (ImageView) view.findViewById(R.id.sl_iv_five);
        ivSix   =  (ImageView) view.findViewById(R.id.sl_iv_six);
        ivServen   =  (ImageView) view.findViewById(R.id.sl_iv_seven);

        rl1 =   (RelativeLayout)view.findViewById(R.id.rl_open_close1);
        rl2 =   (RelativeLayout)view.findViewById(R.id.rl_open_close2);
        rl3 =   (RelativeLayout)view.findViewById(R.id.rl_open_close3);
        rl4 =   (RelativeLayout)view.findViewById(R.id.rl_open_close4);
        rl5 =   (RelativeLayout)view.findViewById(R.id.rl_open_close5);
        rl6 =   (RelativeLayout)view.findViewById(R.id.rl_open_close6);
        rl7 =   (RelativeLayout)view.findViewById(R.id.rl_open_close7);

        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);
        rl5.setOnClickListener(this);
        rl6.setOnClickListener(this);
        rl7.setOnClickListener(this);

    }


    private void init() {
        entityId = getActivity().getIntent().getStringExtra("entityId");
        datas   =   new ArrayList<>();
        callNet();
    }

    private void initListView(){
            //监管记录
        ArrayList<ArrayList<String>> ass1    =   new ArrayList<>();
        for (int i=0;i<jyqxdqGlxxBean.getValues().getCheckList().size();i++){
            ArrayList<String> as    =   new ArrayList<>();
            as.add("记录人" + "##" + jyqxdqGlxxBean.getValues().getCheckList().get(i).getRecordUserName());
            as.add("检查类型" + "##" + jyqxdqGlxxBean.getValues().getCheckList().get(i).getCheckType());
            as.add("检查机关" + "##" + jyqxdqGlxxBean.getValues().getCheckList().get(i).getCheckOrganId());
            as.add("检查结果" + "##" + jyqxdqGlxxBean.getValues().getCheckList().get(i).getCheckResultId());
            as.add("检查日期" + "##" + jyqxdqGlxxBean.getValues().getCheckList().get(i).getRecordDate());
            as.add(jyqxdqGlxxBean.getValues().getCheckList().get(i).getPlanId());
            ass1.add(as);
        }
        datas.add(ass1);
        glxxAdapter =   new JyqxdqGlxxAdapter(datas.get(0),getActivity());
        lvJgjl.setAdapter(glxxAdapter);

        //年检情况
        ArrayList<ArrayList<String>> ass2    =   new ArrayList<>();
        for (int i=0;i<jyqxdqGlxxBean.getValues().getAnnlList().size();i++){
            ArrayList<String> as    =   new ArrayList<>();
            as.add("年检年度" + "##" + jyqxdqGlxxBean.getValues().getAnnlList().get(i).getPlanId());
            as.add("年检状态" + "##" + jyqxdqGlxxBean.getValues().getAnnlList().get(i).getResultName());
            as.add(i+"");
            ass2.add(as);
        }
        datas.add(ass2);
        glxxAdapter =   new JyqxdqGlxxAdapter(datas.get(1),getActivity());
        lvNjqk.setAdapter(glxxAdapter);


         //被监控状况
        ArrayList<ArrayList<String>> ass3    =   new ArrayList<>();
        for (int i=0;i<jyqxdqGlxxBean.getValues().getBlackList().size();i++){
            ArrayList<String> as    =   new ArrayList<>();
            as.add("监控日期" + "##" + jyqxdqGlxxBean.getValues().getBlackList().get(i).getStartDate());
            as.add("申请方" + "##" + jyqxdqGlxxBean.getValues().getBlackList().get(i).getSurveillApplicant());
            as.add("监控级别" + "##" + jyqxdqGlxxBean.getValues().getBlackList().get(i).getSurveillLevel());
            as.add("监控类别" + "##" + jyqxdqGlxxBean.getValues().getBlackList().get(i).getSurveilSortName());
            as.add("监控原因" + "##" + jyqxdqGlxxBean.getValues().getBlackList().get(i).getSurveillReson());
            as.add("监控措施" + "##" + jyqxdqGlxxBean.getValues().getBlackList().get(i).getSurveillMeasure());
            as.add(i+"");
            ass3.add(as);
        }
        datas.add(ass3);
        glxxAdapter =   new JyqxdqGlxxAdapter(datas.get(2),getActivity());
        lvJkzk.setAdapter(glxxAdapter);

        //违法违规情况
        ArrayList<ArrayList<String>> ass4    =   new ArrayList<>();
        for (int i=0;i<jyqxdqGlxxBean.getValues().getCaseList().size();i++){
            ArrayList<String> as    =   new ArrayList<>();
            as.add("记录类型" + "##" + jyqxdqGlxxBean.getValues().getCaseList().get(i).getIlglSort());
            as.add("违法级别" + "##" + jyqxdqGlxxBean.getValues().getCaseList().get(i).getIlglLevel());
            as.add("结案日期" + "##" + jyqxdqGlxxBean.getValues().getCaseList().get(i).getStartDate());
            as.add("立案号" + "##" + jyqxdqGlxxBean.getValues().getCaseList().get(i).getCaseNo());
            as.add(i+"");
            ass4.add(as);
        }
        datas.add(ass4);
        glxxAdapter =   new JyqxdqGlxxAdapter(datas.get(3),getActivity());
        lvWfwg.setAdapter(glxxAdapter);

        //法人代表监控(国家)


        //法人代表监控(上海)
        ArrayList<ArrayList<String>> ass6    =   new ArrayList<>();
        for (int i=0;i<jyqxdqGlxxBean.getValues().getLerepInBlackList().size();i++){
            ArrayList<String> as    =   new ArrayList<>();
            as.add("监控日期(起始)" + "##" + jyqxdqGlxxBean.getValues().getLerepInBlackList().get(i).getAppDate());
            as.add("监控日期(截至)" + "##" + jyqxdqGlxxBean.getValues().getLerepInBlackList().get(i).getEndDate());
            as.add("法定代表人" + "##" + jyqxdqGlxxBean.getValues().getLerepInBlackList().get(i).getCPersonName());
            as.add("证件号码" + "##" + jyqxdqGlxxBean.getValues().getLerepInBlackList().get(i).getCetfNo());
            as.add("申请方" + "##" + jyqxdqGlxxBean.getValues().getLerepInBlackList().get(i).getSurveillApplicant());
            as.add("监控原因" + "##" + jyqxdqGlxxBean.getValues().getLerepInBlackList().get(i).getSurveillReson());
            as.add(i+"");
            ass6.add(as);
        }
        datas.add(ass6);
        glxxAdapter =   new JyqxdqGlxxAdapter(datas.get(4),getActivity());
        lvFrdbsh.setAdapter(glxxAdapter);
    }

    private void callNet(){
        MyProgressDialog.show(getActivity());
        HashMap<String,String>  map =  new HashMap<String,String>();
        map.put("entityId", entityId);
        Call<JyqxdqGlxxBean> call = ApiManager.shApi.jyqxdq_glxx(map);
        call.enqueue(new Callback<JyqxdqGlxxBean>() {
            @Override
            public void onResponse(Response<JyqxdqGlxxBean> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                if (response.isSuccess()) {
                    jyqxdqGlxxBean = response.body();
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
            }
        });
    }

    private void initTextView() {
        tvGllb.setText(jyqxdqGlxxBean.getValues().getList().get(0).getManageLevel());
        tvXyfl.setText(jyqxdqGlxxBean.getValues().getList().get(0).getCreditLevelGb());
        tvZdjgdx.setText(jyqxdqGlxxBean.getValues().getList().get(0).getImportant());
        tvTsbs.setText("");
        tvRdhy.setText("");
        tvZdqy.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_open_close1:
                VisibleOrGone(llTop, ivOne);break;

            case R.id.rl_open_close2:
            if(lvJgjl.getCount()>0) {
                VisibleOrGone(lvJgjl, ivTwo);
            }else{
                Toast.makeText(getActivity(),"监管记录无记录",Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.rl_open_close3:
            if(lvNjqk.getCount()>0) {
                VisibleOrGone(lvNjqk, ivThree);
            }else{
                Toast.makeText(getActivity(),"年检情况无记录",Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.rl_open_close4:
            if(lvJkzk.getCount()>0) {
                VisibleOrGone(lvJkzk, ivFour);
            }else{
                Toast.makeText(getActivity(),"被监控状况无记录",Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.rl_open_close5:
            if(lvWfwg.getCount()>0) {
                VisibleOrGone(lvWfwg, ivFive);
            }else{
                Toast.makeText(getActivity(),"违法违规情况无记录",Toast.LENGTH_SHORT).show();
            }
            break;


            case R.id.rl_open_close6:
            if(lvFrdbgj.getCount()>0) {
                VisibleOrGone(lvFrdbgj, ivSix);
            }else{
                Toast.makeText(getActivity(),"法人代表监控(国家局)无记录",Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.rl_open_close7:
            if(lvFrdbsh.getCount()>0) {
                VisibleOrGone(lvFrdbsh, ivServen);
            }else{
                Toast.makeText(getActivity(),"法人代表监控(上海局)无记录",Toast.LENGTH_SHORT).show();
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
