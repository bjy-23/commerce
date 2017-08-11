package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by jiajiangyi on 2016/4/14.
 */
public class GlxxFragment extends Fragment implements View.OnClickListener {

    private TextView tvGllb;
    private TextView tvXyfl;
    private TextView tvZdjgdx;
    private TextView tvTsbs;
    private TextView tvRdhy;
    private TextView tvZdqy;


    private String entityId;
    private XqzgdqGLXXBean glxxBean;
    private XqzgdqGlxxAdapter glxxAdapter;
    private ArrayList<ArrayList<String>> datas;
    private AppCompatActivity context;

    private ImageView ivOne;
    private ImageView ivTwo;
    private ImageView ivThree;
    private ImageView ivFour;
    private ImageView ivFive;
    private ImageView ivSix;
    private ImageView ivServen;

    private RelativeLayout rlOne;
    private RelativeLayout rlTwo;
    private RelativeLayout rlThree;
    private RelativeLayout rlFour;
    private RelativeLayout rlFive;
    private RelativeLayout rlSix;
    private RelativeLayout rlSeven;


    private LinearLayout llTop;
    private ListView lvJgjl;
    private ListView lvNjqk;
    private ListView lvJkzk;
    private ListView lvWfwg;
    private ListView lvFrdbgj;
    private ListView lvFrdbsh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = (AppCompatActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_xqzg_glxx, container, false);
//        Toast.makeText(getActivity(),"currTab::"+XqzgdqActivity.getCurrTab(),Toast.LENGTH_SHORT).show();

        initView(view);
        init();
        return view;
    }

    private void initView(View view) {

        //基本信息
        tvGllb = (TextView) view.findViewById(R.id.fragment_glxx_tv_gllb);
        tvXyfl = (TextView) view.findViewById(R.id.fragment_glxx_tv_xyfl);
        tvZdjgdx = (TextView) view.findViewById(R.id.fragment_glxx_tv_zdjgdx);
        tvTsbs = (TextView) view.findViewById(R.id.fragment_glxx_tv_tsbs);
        tvRdhy = (TextView) view.findViewById(R.id.fragment_glxx_tv_rdhy);
        tvZdqy = (TextView) view.findViewById(R.id.fragment_glxx_tv_zdqy);

        //布局
        llTop = (LinearLayout) view.findViewById(R.id.sl_ll_top);
        lvJgjl = (ListView) view.findViewById(R.id.glxx_jgjl_listview);
        lvNjqk = (ListView) view.findViewById(R.id.glxx_njqk_listview);
        lvJkzk = (ListView) view.findViewById(R.id.glxx_jkzk_listview);
        lvWfwg = (ListView) view.findViewById(R.id.glxx_wfwg_listview);
        lvFrdbgj = (ListView) view.findViewById(R.id.glxx_frdbgj_listview);
        lvFrdbsh = (ListView) view.findViewById(R.id.glxx_frdbsh_listview);

        ivOne = (ImageView) view.findViewById(R.id.sl_iv_one);
        ivTwo = (ImageView) view.findViewById(R.id.sl_iv_two);
        ivThree = (ImageView) view.findViewById(R.id.sl_iv_three);
        ivFour = (ImageView) view.findViewById(R.id.sl_iv_four);
        ivFive = (ImageView) view.findViewById(R.id.sl_iv_five);
        ivSix = (ImageView) view.findViewById(R.id.sl_iv_six);
        ivServen = (ImageView) view.findViewById(R.id.sl_iv_seven);

        rlOne = (RelativeLayout) view.findViewById(R.id.rl_one);
        rlTwo = (RelativeLayout) view.findViewById(R.id.rl_two);
        rlThree = (RelativeLayout) view.findViewById(R.id.rl_three);
        rlFour = (RelativeLayout) view.findViewById(R.id.rl_four);
        rlFive = (RelativeLayout) view.findViewById(R.id.rl_five);
        rlSix = (RelativeLayout) view.findViewById(R.id.rl_six);
        rlSeven = (RelativeLayout) view.findViewById(R.id.rl_seven);


        rlOne.setOnClickListener(this);
        rlTwo.setOnClickListener(this);
        rlThree.setOnClickListener(this);
        rlFour.setOnClickListener(this);
        rlFive.setOnClickListener(this);
        rlSix.setOnClickListener(this);
        rlSeven.setOnClickListener(this);

    }


    private void init() {
        entityId = getActivity().getIntent().getStringExtra("entityId");
        datas = new ArrayList<>();
        callNet();
    }

    private void initListView() {
        //监管记录
        for (int i = 0; i < glxxBean.getValues().getCheckList().size(); i++) {
            ArrayList<String> as = new ArrayList<>();
            as.add("记录人" + "##" + glxxBean.getValues().getCheckList().get(i).getRecordUserName());
            as.add("检查日期" + "##" + glxxBean.getValues().getCheckList().get(i).getRecordDate());
            as.add("检查类型" + "##" + glxxBean.getValues().getCheckList().get(i).getCheckType());
            as.add("检查机关" + "##" + glxxBean.getValues().getCheckList().get(i).getCheckOrganId());
            as.add("检查结果" + "##" + glxxBean.getValues().getCheckList().get(i).getCheckResultId());
            as.add("jgjl");
            datas.add(as);
        }
        glxxAdapter = new XqzgdqGlxxAdapter(datas, getActivity());
        lvJgjl.setAdapter(glxxAdapter);
        myJGJLItemListener();

        //被监控状况
        ArrayList<ArrayList<String>> datas_jk = new ArrayList<>();
        for (int i = 0; i < glxxBean.getValues().getBlackList().size(); i++) {
            ArrayList<String> as = new ArrayList<>();
            as.add("监控日期" + "##" + glxxBean.getValues().getBlackList().get(i).getStartDate());
            as.add("申请方" + "##" + glxxBean.getValues().getBlackList().get(i).getSurveillApplicant());
            as.add("监控级别" + "##" + glxxBean.getValues().getBlackList().get(i).getSurveillLevel());
            as.add("监控类别" + "##" + glxxBean.getValues().getBlackList().get(i).getSurveilSortName());
            as.add("监控原因" + "##" + glxxBean.getValues().getBlackList().get(i).getSurveillReson());
            as.add("监控措施" + "##" + glxxBean.getValues().getBlackList().get(i).getSurveillMeasure());
            as.add(i + "1");
            datas_jk.add(as);
        }
        glxxAdapter = new XqzgdqGlxxAdapter(datas_jk, getActivity());
        lvJkzk.setAdapter(glxxAdapter);


        //年检情况
        ArrayList<ArrayList<String>> datas_njqk = new ArrayList<>();
        for (int i = 0; i < glxxBean.getValues().getAnnlList().size(); i++) {
            ArrayList<String> as = new ArrayList<>();
            as.add("年检年度" + "##" + glxxBean.getValues().getAnnlList().get(i).getPlanId());
            as.add("年检状态" + "##" + glxxBean.getValues().getAnnlList().get(i).getResultName());
            as.add(glxxBean.getValues().getAnnlList().get(i).getResultId());
            datas_njqk.add(as);
        }
        glxxAdapter = new XqzgdqGlxxAdapter(datas_njqk, getActivity());
        lvNjqk.setAdapter(glxxAdapter);


        //违法违规情况
        ArrayList<ArrayList<String>> datas_wfwg = new ArrayList<>();
        for (int i = 0; i < glxxBean.getValues().getCaseList().size(); i++) {
            ArrayList<String> as = new ArrayList<>();
            as.add("记录类型" + "##" + glxxBean.getValues().getCaseList().get(i).getIlglSort());
            as.add("违法级别" + "##" + glxxBean.getValues().getCaseList().get(i).getIlglLevel());
            as.add("结案日期" + "##" + glxxBean.getValues().getCaseList().get(i).getStartDate());
            as.add("立案号" + "##" + glxxBean.getValues().getCaseList().get(i).getCaseNo());
            as.add(i + "1");
            datas_wfwg.add(as);
        }
        glxxAdapter = new XqzgdqGlxxAdapter(datas_wfwg, getActivity());
        lvWfwg.setAdapter(glxxAdapter);


        //法人代表监控(上海)
        ArrayList<ArrayList<String>> datas_frdbsh = new ArrayList<>();
        for (int i = 0; i < glxxBean.getValues().getLerepInBlackList().size(); i++) {
            ArrayList<String> as = new ArrayList<>();
            as.add("监控日期(起始)" + "##" + glxxBean.getValues().getLerepInBlackList().get(i).getAppDate());
            as.add("监控日期(截至)" + "##" + glxxBean.getValues().getLerepInBlackList().get(i).getEndDate());
            as.add("法定代表人" + "##" + glxxBean.getValues().getLerepInBlackList().get(i).getCPersonName());
            as.add("证件号码" + "##" + glxxBean.getValues().getLerepInBlackList().get(i).getCetfNo());
            as.add("申请方" + "##" + glxxBean.getValues().getLerepInBlackList().get(i).getSurveillApplicant());
            as.add("监控原因" + "##" + glxxBean.getValues().getLerepInBlackList().get(i).getSurveillReson());
            as.add(i + "1");
            datas_frdbsh.add(as);
        }
        glxxAdapter = new XqzgdqGlxxAdapter(datas_frdbsh, getActivity());
        lvFrdbsh.setAdapter(glxxAdapter);


    }

    /**
     * 监管记录的详情点击
     */
    private void myJGJLItemListener() {
        lvJgjl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getActivity(),DetailJGJLActivity.class);
                i.putExtra("operType",glxxBean.getValues().getCheckList().get(position).getOperType());
                i.putExtra("entityId",glxxBean.getValues().getCheckList().get(position).getUnitId());
                i.putExtra("planId",glxxBean.getValues().getCheckList().get(position).getPlanId());
                if (XqzgdqActivity.getCurrTab() == 0) {
                    i.putExtra("entityTypeId","02");
                } else {
                    i.putExtra("entityTypeId","06");
                }
                startActivity(i);
            }
        });
    }

    private void callNet() {
        MyProgressDialog.show(getActivity());
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("entityId", entityId);
        Call<XqzgdqGLXXBean> call;
        if (XqzgdqActivity.getCurrTab() == 0) {
            call = ApiManager.shApi.queryCompanyGLXX(map);
        } else {
            call = ApiManager.shApi.queryPersonGLXX(map);
        }

        call.enqueue(new Callback<XqzgdqGLXXBean>() {
            @Override
            public void onResponse(Response<XqzgdqGLXXBean> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                if (response.isSuccess() && response.body()!=null) {
                    glxxBean = response.body();
//                    Toast.makeText(getActivity(), "haha" + glxxBean.getMsg(), Toast.LENGTH_SHORT).show();
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
//                Log.d("okhttp#onFailure###", t.getMessage());
            }
        });

    }

    private void initTextView() {
        if(glxxBean.getValues().getList()!=null&&glxxBean.getValues().getList().size()>0) {
            tvGllb.setText(glxxBean.getValues().getList().get(0).getManageLevel() + "");
            tvXyfl.setText(glxxBean.getValues().getList().get(0).getCreditLevelGb());
            tvZdjgdx.setText(glxxBean.getValues().getList().get(0).getImportant());
            tvTsbs.setText("");
            tvRdhy.setText("");
            tvZdqy.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        if(glxxBean!=null) {
            switch (v.getId()) {
                case R.id.rl_one:
                    if (glxxBean.getValues().getList().isEmpty()) {
                        Toast.makeText(getActivity(), "无记录", Toast.LENGTH_SHORT).show();
                    } else {
                        VisibleOrGone(llTop, ivOne);
                    }
                    break;
                case R.id.rl_two:
                    if (glxxBean.getValues().getCheckList().isEmpty()) {
                        Toast.makeText(getActivity(), "无记录", Toast.LENGTH_SHORT).show();
                    } else {
                        VisibleOrGone(lvJgjl, ivTwo);
                    }
                    break;
                case R.id.rl_three:
                    if (glxxBean.getValues().getAnnlList().isEmpty()) {
                        Toast.makeText(getActivity(), "无记录", Toast.LENGTH_SHORT).show();
                    } else {
                        VisibleOrGone(lvNjqk, ivThree);
                    }
                    break;
                case R.id.rl_four:
                    if (glxxBean.getValues().getBlackList().isEmpty()) {
                        Toast.makeText(getActivity(), "无记录", Toast.LENGTH_SHORT).show();
                    } else {
                        VisibleOrGone(lvJkzk, ivFour);
                    }
                    break;
                case R.id.rl_five:
                    if (glxxBean.getValues().getCaseList().isEmpty()) {
                        Toast.makeText(getActivity(), "无记录", Toast.LENGTH_SHORT).show();
                    } else {
                        VisibleOrGone(lvWfwg, ivFive);
                    }
                    break;
                case R.id.rl_six:
                    if (glxxBean.getValues().getLerepInGjjList().isEmpty()) {
                        Toast.makeText(getActivity(), "无记录", Toast.LENGTH_SHORT).show();
                    } else {
                        VisibleOrGone(lvFrdbgj, ivSix);
                    }
                    break;
                case R.id.rl_seven:
                    if (glxxBean.getValues().getLerepInBlackList().isEmpty()) {
                        Toast.makeText(getActivity(), "无记录", Toast.LENGTH_SHORT).show();
                    } else {
                        VisibleOrGone(lvFrdbsh, ivServen);
                    }
                    break;
            }
        }
    }

    private void VisibleOrGone(View v, ImageView img) {

        if (v.getVisibility() == View.GONE) {
            v.setVisibility(View.VISIBLE);
            img.setBackgroundResource(R.drawable.list_open);
        } else {
            v.setVisibility(View.GONE);
            img.setBackgroundResource(R.drawable.list_close);
        }
    }
}