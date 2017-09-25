package com.wondersgroup.commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.statistics.AnnalsActivity;
import com.wondersgroup.commerce.teamwork.statistics.BanJieActivity;
import com.wondersgroup.commerce.teamwork.statistics.BanLiActivity;
import com.wondersgroup.commerce.teamwork.statistics.InActivity;
import com.wondersgroup.commerce.teamwork.statistics.OutActivity;
import com.wondersgroup.commerce.teamwork.statistics.QueryActivity;
import com.wondersgroup.commerce.widget.InfoSelectBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bjy on 2017/6/28.
 */

public class QueryCountActivity extends BaseActivity {

    @Bind({R.id.qynb_link_1, R.id.qynb_link_2, R.id.qynb_link_3, R.id.qynb_link_4, R.id.qynb_link_5,
            R.id.qynb_link_6, R.id.qynb_link_7, R.id.qynb_link_8, R.id.xwxx_link_1, R.id.xwxx_link_2,
            R.id.xwxx_link_3, R.id.xwxx_link_4, R.id.xwxx_link_5, R.id.jyyc_link_1, R.id.jyyc_link_2,
            R.id.jyyc_link_3, R.id.jyyc_link_4, R.id.jyyc_link_5, R.id.jyyc_link_6, R.id.gtgsh_link_1,
            R.id.gtgsh_link_2, R.id.gtgsh_link_3, R.id.gtgsh_link_4, R.id.gtgsh_link_5, R.id.gtgsh_link_6,
            R.id.gtgsh_link_7, R.id.gtgsh_link_8, R.id.bj, R.id.bl})
    List<InfoSelectBar> layoutList;
    @Bind(R.id.nb)
    InfoSelectBar nb;
    @Bind(R.id.bj)
    InfoSelectBar bj;
    @Bind(R.id.bl)
    InfoSelectBar bl;
    @Bind(R.id.agr_annal)
    InfoSelectBar agrAnnal;
    @Bind(R.id.ywcx)
    InfoSelectBar ywcx;

    private int[] iconList = {R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0
            , R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0,
            R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0
            , R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0
            , R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0, R.drawable.icons_0};

    private String[] tjTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addContentView(R.layout.fragment_tjfx);
        ButterKnife.bind(this);
        tvTitle.setText("统计分析");

        //        tjTitle = getResources().getStringArray(R.array.tj_item);
        tjTitle = getResources().getStringArray(R.array.tj_item_yn);
        for (int i = 0; i < tjTitle.length; i++) {
            layoutList.get(i).setText(tjTitle[i]);
            layoutList.get(i).setIcon(iconList[i]);
        }

        bj.setText("消费维权投诉举报统计");
        bj.setIcon(R.drawable.icons_0);
        bl.setText("工商案件系统办理数统计");
        bl.setIcon(R.drawable.icons_0);
        agrAnnal.setText("农专年报进度统计");
        ywcx.setText("市场主体户数统计");
        nb.setText("年报情况统计");
    }

    @OnClick({R.id.qynb_link_1, R.id.qynb_link_2, R.id.qynb_link_3, R.id.qynb_link_4, R.id.qynb_link_5,
            R.id.qynb_link_6, R.id.qynb_link_7, R.id.qynb_link_8, R.id.xwxx_link_1, R.id.xwxx_link_2,
            R.id.xwxx_link_3, R.id.xwxx_link_4, R.id.xwxx_link_5, R.id.jyyc_link_1, R.id.jyyc_link_2,
            R.id.jyyc_link_3, R.id.jyyc_link_4, R.id.jyyc_link_5, R.id.jyyc_link_6, R.id.gtgsh_link_1,
            R.id.gtgsh_link_2, R.id.gtgsh_link_3, R.id.gtgsh_link_4, R.id.gtgsh_link_5, R.id.gtgsh_link_6,
            R.id.gtgsh_link_7, R.id.gtgsh_link_8})
    public void onClick(View v) {
        Intent intent;
        String title = "";
        switch (v.getId()) {
            case R.id.qynb_link_1:
                title = tjTitle[0];
                break;
            case R.id.qynb_link_2:
                title = tjTitle[1];
                break;
            case R.id.qynb_link_3:
                title = tjTitle[2];
                break;
            case R.id.qynb_link_4:
                title = tjTitle[3];
                break;
            case R.id.qynb_link_5:
                title = tjTitle[4];
                break;
            case R.id.qynb_link_6:
                title = tjTitle[5];
                break;
            case R.id.qynb_link_7:
                title = tjTitle[6];
                break;
            case R.id.qynb_link_8:
                title = tjTitle[7];
                break;
            case R.id.xwxx_link_1:
                title = tjTitle[8];
                break;
            case R.id.xwxx_link_2:
                title = tjTitle[9];
                break;
            case R.id.xwxx_link_3:
                title = tjTitle[10];
                break;
            case R.id.xwxx_link_4:
                title = tjTitle[11];
                break;
            case R.id.xwxx_link_5:
                title = tjTitle[12];
                break;
            case R.id.jyyc_link_1:
                title = tjTitle[13];
                break;
            case R.id.jyyc_link_2:
                title = tjTitle[14];
                break;
            case R.id.jyyc_link_3:
                title = tjTitle[15];
                break;
            case R.id.jyyc_link_4:
                title = tjTitle[16];
                break;
            case R.id.jyyc_link_5:
                title = tjTitle[17];
                break;
            case R.id.jyyc_link_6:
                title = tjTitle[18];
                break;
            case R.id.gtgsh_link_1:
                title = tjTitle[19];
                break;
            case R.id.gtgsh_link_2:
                title = tjTitle[20];
                break;
            case R.id.gtgsh_link_3:
                title = tjTitle[21];
                break;
            case R.id.gtgsh_link_4:
                title = tjTitle[22];
                break;
            case R.id.gtgsh_link_5:
                title = tjTitle[23];
                break;
            case R.id.gtgsh_link_6:
                title = tjTitle[24];
                break;
            case R.id.gtgsh_link_7:
                title = tjTitle[25];
                break;
            case R.id.gtgsh_link_8:
                title = tjTitle[26];
                break;
        }
        intent = new Intent(this, TjActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

@OnClick({R.id.ywcx, R.id.nb, R.id.bj, R.id.bl, R.id.shy, R.id.agr_annal, R.id.yc, R.id.shjg, R.id.lryc
            })    void onTextClick(View view) {        switch (view.getId()) {
            case R.id.ywcx://业务查询
                startActivity(new Intent(this, QueryActivity.class));
                break;
            case R.id.nb://年报
                startActivity(new Intent(this, AnnalsActivity.class));
                break;
            case R.id.bj://办结
                startActivity(new Intent(this, BanJieActivity.class));
                break;
            case R.id.bl://办理
                startActivity(new Intent(this, BanLiActivity.class));
                break;
            case R.id.shy://三合一案件系统办理数统计
                Intent intent = new Intent(this, BanLiActivity.class);
                intent.putExtra("KEY_TYPE", "SHY");
                startActivity(intent);
                break;
            case R.id.agr_annal://农专年报
//                startActivity(new Intent(this, AgrAnnalActivity.class));
                break;
            case R.id.lryc://列入移出
                startActivity(new Intent(this, InActivity.class));
                break;
            case R.id.yc:
                startActivity(new Intent(this, OutActivity.class));
                break;
            case R.id.shjg:
                Toast.makeText(QueryCountActivity.this, "功能正在开发中", Toast.LENGTH_SHORT).show();
            break;
        }
    }

}
