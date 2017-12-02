package com.wondersgroup.yngs.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.entity.CatalogFixBean;
import com.wondersgroup.yngs.entity.CompanyAllDataBean;
import com.wondersgroup.yngs.utils.App;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 1229 on 2016/1/13.
 */
public class DjxxActivity extends AppCompatActivity {
    public static CompanyAllDataBean cadb;
    private TextView zchTv;
    private TextView mcTv;
    private TextView lxTv;
    private TextView fddbrTv;
    private TextView zczbTv;
    private TextView clrqTv;
    private TextView zsTv;
    private TextView yyqxziTv;
    private TextView yyqxzhiTv;
    private TextView jyfwTv;
    private TextView djjgTv;
    private TextView hzrqTv;
    private TextView djztTv;

    private TextView zchTitle;
    private TextView mcTitle;
    private TextView lxTitle;
    private TextView fddbrTitle;
    private TextView zczbTitle;
    private TextView clrqTitle;
    private TextView zsTitle;
    private TextView yyqxziTitle;
    private TextView yyqxzhiTitle;
    private TextView jyfwTitle;
    private TextView djjgTitle;
    private TextView hzrqTitle;
    private TextView djztTitle;

    private TextView dxzzTitle;
    private TextView dxzzTv;
    private LinearLayout dxzz;

    private LinearLayout zch;
    private LinearLayout mc;
    private LinearLayout lx;
    private LinearLayout fddbr;
    private LinearLayout zczb;
    private LinearLayout clrq;
    private LinearLayout zs;
    private LinearLayout yyqxzi;
    private LinearLayout yyqxzhi;
    private LinearLayout jyfw;
    private LinearLayout djjg;
    private LinearLayout hzrq;
    private LinearLayout djzt;

    private HashMap<String, String> dicEntTypeHashMap;
    private HashMap<String, ArrayList<CatalogFixBean>> dicLayoutConfigMap;
    public static String typeId;
    private App app;

    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_djxx);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        title.setText("基本信息");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);

        zchTitle = (TextView) findViewById(R.id.zch_title);
        zchTv = (TextView) findViewById(R.id.zch_tv);
        zch = (LinearLayout) findViewById(R.id.zch);
        mcTitle = (TextView) findViewById(R.id.mc_title);
        mcTv = (TextView) findViewById(R.id.mc_tv);
        mc = (LinearLayout) findViewById(R.id.mc);
        lxTitle = (TextView) findViewById(R.id.lx_title);
        lxTv = (TextView) findViewById(R.id.lx_tv);
        lx = (LinearLayout) findViewById(R.id.lx);
        fddbrTitle = (TextView) findViewById(R.id.fddbr_title);
        fddbrTv = (TextView) findViewById(R.id.fddbr_tv);
        fddbr = (LinearLayout) findViewById(R.id.fddbr);
        zczbTitle = (TextView) findViewById(R.id.zczb_title);
        zczbTv = (TextView) findViewById(R.id.zczb_tv);
        zczb = (LinearLayout) findViewById(R.id.zczb);
        clrqTitle = (TextView) findViewById(R.id.clrq_title);
        clrqTv = (TextView) findViewById(R.id.clrq_tv);
        clrq = (LinearLayout) findViewById(R.id.clrq);
        zsTitle = (TextView) findViewById(R.id.zs_title);
        zsTv = (TextView) findViewById(R.id.zs_tv);
        zs = (LinearLayout) findViewById(R.id.zs);
        yyqxziTitle = (TextView) findViewById(R.id.yyqxzi_title);
        yyqxziTv = (TextView) findViewById(R.id.yyqxzi_tv);
        yyqxzi = (LinearLayout) findViewById(R.id.yyqxzi);
        yyqxzhiTitle = (TextView) findViewById(R.id.yyqxzhi_title);
        yyqxzhiTv = (TextView) findViewById(R.id.yyqxzhi_tv);
        yyqxzhi = (LinearLayout) findViewById(R.id.yyqxzhi);
        jyfwTitle = (TextView) findViewById(R.id.jyfw_title);
        jyfwTv = (TextView) findViewById(R.id.jyfw_tv);
        jyfw = (LinearLayout) findViewById(R.id.jyfw);
        djjgTitle = (TextView) findViewById(R.id.djjg_title);
        djjgTv = (TextView) findViewById(R.id.djjg_tv);
        djjg = (LinearLayout) findViewById(R.id.djjg);
        hzrqTitle = (TextView) findViewById(R.id.hzrq_title);
        hzrqTv = (TextView) findViewById(R.id.hzrq_tv);
        hzrq = (LinearLayout) findViewById(R.id.hzrq);
        djztTitle = (TextView) findViewById(R.id.djzt_title);
        djztTv = (TextView) findViewById(R.id.djzt_tv);
        djzt = (LinearLayout) findViewById(R.id.djzt);
        dxzzTitle = (TextView) findViewById(R.id.dxzz_title);
        dxzzTv = (TextView) findViewById(R.id.dxzz_tv);
        dxzz = (LinearLayout) findViewById(R.id.dxzz);

        app = (App) this.getApplicationContext();
        dicEntTypeHashMap = app.getDicEntTypeHashMap();
        dicLayoutConfigMap = app.getDicLayoutConfigMap();

        Log.i("youcando",
                cadb.getCompanyInfoBean().getEntType().subSequence(0, 2)
                        .toString());
        Log.i("youcando", cadb.getCompanyInfoBean().getEntType());
        if (cadb.getCompanyInfoBean().getEntType().subSequence(0, 2).toString()
                .equals("41")
                || cadb.getCompanyInfoBean().getEntType().subSequence(0, 2)
                .toString().equals("42")
                || cadb.getCompanyInfoBean().getEntType().subSequence(0, 2)
                .toString().equals("44")) {
            typeId = "22";
        } else {
            typeId = dicEntTypeHashMap.get(cadb.getCompanyInfoBean()
                    .getEntType());
        }
        Log.i("typeId", typeId + "");

        Log.i("typeId", typeId + "");

        if (cadb.getCompanyInfoBean().getRevokeDate() == null) {
            dxzz.setVisibility(View.GONE);
        } else {
            dxzzTv.setText(cadb.getCompanyInfoBean().getRevokeDate());
        }

        if (typeId.equals("10")
                || typeId.equals("30")) {
            zchTv.setText(cadb.getCompanyInfoBean().getRegNo());
            mcTv.setText(cadb.getCompanyInfoBean().getEntName());
            lxTv.setText(cadb.getCompanyInfoBean().getEntTypeInterpreted());
            fddbrTv.setText(cadb.getCompanyInfoBean().getLerep());
            zczbTv.setText(cadb.getCompanyInfoBean().getRegCapInterpreted());
            clrqTv.setText(cadb.getCompanyInfoBean().getEstDate());
            zsTv.setText(cadb.getCompanyInfoBean().getDom());
            yyqxziTv.setText(cadb.getCompanyInfoBean().getOpFrom());
            yyqxzhiTv.setText(cadb.getCompanyInfoBean().getOpTo());
            jyfwTv.setText(cadb.getCompanyInfoBean().getOpScope());
            djjgTv.setText(cadb.getCompanyInfoBean().getRegOrganInterpreted());
            hzrqTv.setText(cadb.getCompanyInfoBean().getIssBlicDate());
            djztTv.setText(cadb.getCompanyInfoBean().getOpStateInterpreted());

        } else if (typeId.equals("20")) {
            zchTv.setText(cadb.getCompanyInfoBean().getRegNo());
            mcTv.setText(cadb.getCompanyInfoBean().getEntName());
            lxTv.setText(cadb.getCompanyInfoBean().getEntTypeInterpreted());
            fddbrTv.setText(cadb.getCompanyInfoBean().getLerep());
            zczbTv.setText(cadb.getCompanyInfoBean().getRegCapInterpreted());
            clrqTv.setText(cadb.getCompanyInfoBean().getEstDate());
            zsTv.setText(cadb.getCompanyInfoBean().getDom());
            yyqxziTv.setText(cadb.getCompanyInfoBean().getOpFrom());
            yyqxzhiTv.setText(cadb.getCompanyInfoBean().getOpTo());
            jyfwTv.setText(cadb.getCompanyInfoBean().getOpScope());
            djjgTv.setText(cadb.getCompanyInfoBean().getRegOrganInterpreted());
            hzrqTv.setText(cadb.getCompanyInfoBean().getIssBlicDate());
            djztTv.setText(cadb.getCompanyInfoBean().getOpStateInterpreted());

            yyqxziTitle.setText("经营期限自:");
            yyqxzhiTitle.setText("经营期限至:");
        } else if (typeId.equals("11")
                || typeId.equals("21")
                || typeId.equals("33")
                || typeId.equals("34")
                || typeId.equals("50")
                || typeId.equals("71")
                || typeId.equals("81")
                || typeId.equals("P1")
                || typeId.equals("22")) {
            zchTv.setText(cadb.getCompanyInfoBean().getRegNo());
            mcTv.setText(cadb.getCompanyInfoBean().getEntName());
            lxTv.setText(cadb.getCompanyInfoBean().getEntTypeInterpreted());
            fddbrTv.setText(cadb.getCompanyInfoBean().getLerep());
            zczbTv.setText(cadb.getCompanyInfoBean().getRegCapInterpreted());
            clrqTv.setText(cadb.getCompanyInfoBean().getEstDate());
            zsTv.setText(cadb.getCompanyInfoBean().getDom());
            yyqxziTv.setText(cadb.getCompanyInfoBean().getOpFrom());
            yyqxzhiTv.setText(cadb.getCompanyInfoBean().getOpTo());
            jyfwTv.setText(cadb.getCompanyInfoBean().getOpScope());
            djjgTv.setText(cadb.getCompanyInfoBean().getRegOrganInterpreted());
            hzrqTv.setText(cadb.getCompanyInfoBean().getIssBlicDate());
            djztTv.setText(cadb.getCompanyInfoBean().getOpStateInterpreted());
            zczbTitle.setVisibility(View.GONE);
            zczbTv.setVisibility(View.GONE);
            zczb.setVisibility(View.GONE);
            fddbrTitle.setText("负责人:");
            zsTitle.setText("营业场所:");
            djztTitle.setText("登记状态:");
        } else if (typeId.equals("70")) {
            zchTv.setText(cadb.getCompanyInfoBean().getRegNo());
            mcTv.setText(cadb.getCompanyInfoBean().getEntName());
            lxTv.setText(cadb.getCompanyInfoBean().getEntTypeInterpreted());
            fddbrTv.setText(cadb.getCompanyInfoBean().getLerep());
            zczbTv.setText(cadb.getCompanyInfoBean().getRegCapInterpreted());
            clrqTv.setText(cadb.getCompanyInfoBean().getEstDate());
            zsTv.setText(cadb.getCompanyInfoBean().getDom());
            yyqxziTv.setText(cadb.getCompanyInfoBean().getOpFrom());
            yyqxzhiTv.setText(cadb.getCompanyInfoBean().getOpTo());
            jyfwTv.setText(cadb.getCompanyInfoBean().getOpScope());
            djjgTv.setText(cadb.getCompanyInfoBean().getRegOrganInterpreted());
            hzrqTv.setText(cadb.getCompanyInfoBean().getIssBlicDate());
            djztTv.setText(cadb.getCompanyInfoBean().getOpStateInterpreted());
            zczbTitle.setVisibility(View.GONE);
            zczbTv.setVisibility(View.GONE);
            zczb.setVisibility(View.GONE);
            yyqxzhiTitle.setVisibility(View.GONE);
            yyqxzhiTv.setVisibility(View.GONE);
            yyqxzhi.setVisibility(View.GONE);
            yyqxziTitle.setVisibility(View.GONE);
            yyqxziTv.setVisibility(View.GONE);
            yyqxzi.setVisibility(View.GONE);

            fddbrTitle.setText("投资人:");

        } else if (typeId.equals("80")
                || typeId.equals("P0")) {
            zchTv.setText(cadb.getCompanyInfoBean().getRegNo());
            mcTv.setText(cadb.getCompanyInfoBean().getEntName());
            lxTv.setText(cadb.getCompanyInfoBean().getEntTypeInterpreted());
            fddbrTv.setText(cadb.getCompanyInfoBean().getLerep());
            zczbTv.setText(cadb.getCompanyInfoBean().getRegCapInterpreted());
            clrqTv.setText(cadb.getCompanyInfoBean().getEstDate());
            zsTv.setText(cadb.getCompanyInfoBean().getDom());
            yyqxziTv.setText(cadb.getCompanyInfoBean().getOpFrom());
            yyqxzhiTv.setText(cadb.getCompanyInfoBean().getOpTo());
            jyfwTv.setText(cadb.getCompanyInfoBean().getOpScope());
            djjgTv.setText(cadb.getCompanyInfoBean().getRegOrganInterpreted());
            hzrqTv.setText(cadb.getCompanyInfoBean().getIssBlicDate());
            djztTv.setText(cadb.getCompanyInfoBean().getOpStateInterpreted());
            fddbrTitle.setText("执行事务代表人:");
            zczbTitle.setVisibility(View.GONE);
            zczbTv.setVisibility(View.GONE);
            zczb.setVisibility(View.GONE);
            zsTitle.setText("主要经营场所:");
            yyqxziTitle.setText("合伙期限自:");
            yyqxzhiTitle.setText("合伙期限至:");
        } else if (typeId.equals("90")) {
            zchTv.setText(cadb.getCompanyInfoBean().getRegNo());
            mcTv.setText(cadb.getCompanyInfoBean().getEntName());
            lxTv.setText(cadb.getCompanyInfoBean().getEntTypeInterpreted());
            fddbrTv.setText(cadb.getCompanyInfoBean().getLerep());
            zczbTv.setText(cadb.getCompanyInfoBean().getRegCapInterpreted());
            clrqTv.setText(cadb.getCompanyInfoBean().getEstDate());
            zsTv.setText(cadb.getCompanyInfoBean().getDom());
            yyqxziTv.setText(cadb.getCompanyInfoBean().getOpFrom());
            yyqxzhiTv.setText(cadb.getCompanyInfoBean().getOpTo());
            jyfwTv.setText(cadb.getCompanyInfoBean().getOpScope());
            djjgTv.setText(cadb.getCompanyInfoBean().getRegOrganInterpreted());
            hzrqTv.setText(cadb.getCompanyInfoBean().getIssBlicDate());
            djztTv.setText(cadb.getCompanyInfoBean().getOpStateInterpreted());
            zczbTitle.setText("成员出资总额:");
            jyfwTitle.setText("业务范围:");
            yyqxzhiTitle.setVisibility(View.GONE);
            yyqxzhiTv.setVisibility(View.GONE);
            yyqxzhi.setVisibility(View.GONE);
            yyqxziTitle.setVisibility(View.GONE);
            yyqxziTv.setVisibility(View.GONE);
            yyqxzi.setVisibility(View.GONE);
        } else if (typeId.equals("91")) {
            zchTv.setText(cadb.getCompanyInfoBean().getRegNo());
            mcTv.setText(cadb.getCompanyInfoBean().getEntName());
            lxTv.setText(cadb.getCompanyInfoBean().getEntTypeInterpreted());
            fddbrTv.setText(cadb.getCompanyInfoBean().getLerep());
            zczbTv.setText(cadb.getCompanyInfoBean().getRegCapInterpreted());
            clrqTv.setText(cadb.getCompanyInfoBean().getEstDate());
            zsTv.setText(cadb.getCompanyInfoBean().getDom());
            yyqxziTv.setText(cadb.getCompanyInfoBean().getOpFrom());
            yyqxzhiTv.setText(cadb.getCompanyInfoBean().getOpTo());
            jyfwTv.setText(cadb.getCompanyInfoBean().getOpScope());
            djjgTv.setText(cadb.getCompanyInfoBean().getRegOrganInterpreted());
            hzrqTv.setText(cadb.getCompanyInfoBean().getIssBlicDate());
            djztTv.setText(cadb.getCompanyInfoBean().getOpStateInterpreted());
            fddbrTitle.setText("负责人:");
            zczbTitle.setVisibility(View.GONE);
            zczbTv.setVisibility(View.GONE);
            zczb.setVisibility(View.GONE);
            yyqxzhiTitle.setVisibility(View.GONE);
            yyqxzhiTv.setVisibility(View.GONE);
            yyqxzhi.setVisibility(View.GONE);
            yyqxziTitle.setVisibility(View.GONE);
            yyqxziTv.setVisibility(View.GONE);
            yyqxzi.setVisibility(View.GONE);
            zsTitle.setText("经营场所:");
            jyfwTitle.setText("业务范围:");
        } else if (typeId.equals("00")) {
            zchTv.setText(cadb.getCompanyInfoBean().getRegNo());
            mcTv.setText(cadb.getCompanyInfoBean().getEntName());
            lxTv.setText(cadb.getCompanyInfoBean().getEntTypeInterpreted());
            fddbrTv.setText(cadb.getCompanyInfoBean().getLerep());
            zczbTv.setText(cadb.getCompanyInfoBean().getRegCapInterpreted());
            clrqTv.setText(cadb.getCompanyInfoBean().getEstDate());
            zsTv.setText(cadb.getCompanyInfoBean().getDom());
            yyqxziTv.setText(cadb.getCompanyInfoBean().getOpFrom());
            yyqxzhiTv.setText(cadb.getCompanyInfoBean().getOpTo());
            jyfwTv.setText(cadb.getCompanyInfoBean().getOpScope());
            djjgTv.setText(cadb.getCompanyInfoBean().getRegOrganInterpreted());
            hzrqTv.setText(cadb.getCompanyInfoBean().getIssBlicDate());
            djztTv.setText(cadb.getCompanyInfoBean().getOpStateInterpreted());
            fddbrTitle.setText("经营者:");
            zczbTitle.setText("组成形式:");
            zczbTv.setText(cadb.getCompanyInfoBean().getSconFormInterpreted());
            zsTitle.setText("经营场所:");
            yyqxzhiTitle.setVisibility(View.GONE);
            yyqxzhiTv.setVisibility(View.GONE);
            yyqxzhi.setVisibility(View.GONE);
            yyqxziTitle.setVisibility(View.GONE);
            yyqxziTv.setVisibility(View.GONE);
            yyqxzi.setVisibility(View.GONE);
            clrqTitle.setText("注册日期:");

        }
    }

    public void setCadb(CompanyAllDataBean cadb) {
        this.cadb = cadb;
    }

    public CompanyAllDataBean getCadb() {
        return cadb;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}