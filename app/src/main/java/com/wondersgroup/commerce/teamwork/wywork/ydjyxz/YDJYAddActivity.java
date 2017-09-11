package com.wondersgroup.commerce.teamwork.wywork.ydjyxz;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.wywork.ydjyxz.bean.YDJYTreeBean;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class YDJYAddActivity extends AppCompatActivity{

    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolTitle;

    @Bind(R.id.ydjy_ssfj1)
    TextView tvSsfj1;
    @Bind(R.id.ydjy_ssfj2)
    TextView tvSsfj2;
    @Bind(R.id.ydjy_ssgss1)
    TextView tvSsgss1;
    @Bind(R.id.ydjy_ssgss2)
    TextView tvSsgss2;
    @Bind(R.id.ydjy_ssxzjd1)
    TextView tvSsxzjd1;
    @Bind(R.id.ydjy_ssxzjd2)
    TextView tvSsxzjd2;
    @Bind(R.id.ydjy_pbmc1)
    TextView tvPbmc1;
    @Bind(R.id.ydjy_pbmc2)
    TextView tvPbmc2;
    @Bind(R.id.ydjy_bzhdz)
    TextView tvBzhdz;
    @Bind(R.id.ydjy_lu)
    TextView tvLu;
    @Bind(R.id.ydjy_long)
    TextView tvLong;
    @Bind(R.id.ydjy_hao1)
    TextView tvHao1;
    @Bind(R.id.ydjy_hao2)
    TextView tvHao2;
    @Bind(R.id.ydjy_shi)
    TextView tvShi;
    @Bind(R.id.ydjy_haopuwei)
    TextView tvHaopuwei;
    @Bind(R.id.ydjy_fjdz1)
    TextView tvFjdz1;
    @Bind(R.id.ydjy_fjdz2)
    TextView tvFjdz2;
    @Bind(R.id.ydjy_fjdz3)
    TextView tvFjdz3;
    @Bind(R.id.ydjy_fjdz4)
    TextView tvFjdz4;
    @Bind(R.id.ydjy_mplx1)
    TextView tvMplx1;
    @Bind(R.id.ydjy_mplx2)
    TextView tvMplx2;
    @Bind(R.id.ydjy_fxrq1)
    TextView tvFxrq1;
    @Bind(R.id.ydjy_fxrq2)
    TextView tvFxrq2;
    @Bind(R.id.ydjy_cljg1)
    TextView tvCljg1;
    @Bind(R.id.ydjy_cljg2)
    TextView tvCljg2;
    @Bind(R.id.ydjy_fxr1)
    TextView tvFxr1;
    @Bind(R.id.ydjy_fxr2)
    TextView tvFxr2;
    @Bind(R.id.ydjy_bz1)
    TextView tvBz1;
    @Bind(R.id.ydjy_bz2)
    TextView tvBz2;
    @Bind(R.id.ydjy_lxfs1)
    TextView tvLxfs1;
    @Bind(R.id.ydjy_lxfs2)
    TextView tvLxfs2;


    private String entityId;
    private int type;
    private List<String> list;
    private Context mContext;

    private YDJYTreeBean ydjyTreeBean;

    private int choose1 =   -1;//分局
    private int choose2 =   -1;//工商所
    private int choose3 =   -1;//街道

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ydjy_add);
        ButterKnife.bind(this);

        type    =   getIntent().getIntExtra("type", 0);
        entityId    =   getIntent().getStringExtra("entityId");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolTitle.setText("新增");

        init();
        initThree();
    }

    private void initThree() {
        MyProgressDialog.show(mContext);
        Call<YDJYTreeBean> call = ApiManager.shApi.queryYDJYTree(new HashMap<String, String>());
        call.enqueue(new Callback<YDJYTreeBean>() {
            @Override
            public void onResponse(Response<YDJYTreeBean> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                if (response.isSuccess()) {
                    ydjyTreeBean = response.body();
//                    initThree();
                    initQy1();
                } else {
                    Toast.makeText(mContext, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(mContext, getResources().getString(R.string.error_connect)+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        mContext    =   YDJYAddActivity.this;
//        if(type==0){//企业
//            initQy1();
//        }


    }

    private void initQy1() {
        tvSsfj1.setText(Html.fromHtml(tvSsfj1.getText().toString()+"<font color=red>*</font>"));
        tvSsgss1.setText(Html.fromHtml(tvSsgss1.getText().toString()+"<font color=red>*</font>"));
        tvSsxzjd1.setText(Html.fromHtml(tvSsxzjd1.getText().toString()+"<font color=red>*</font>"));
        tvFxrq1.setText(Html.fromHtml(tvFxrq1.getText().toString()+"<font color=red>*</font>"));
        tvCljg1.setText(Html.fromHtml(tvCljg1.getText().toString()+"<font color=red>*</font>"));
        tvFxr1.setText(Html.fromHtml(tvFxr1.getText().toString() + "<font color=red>*</font>"));

        tvSsfj2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = ydjyTreeBean.getValues().getOrganAreaId().size();
                String[] ssfj = new String[size];
                for (int i = 0; i < size; i++) {
                    ssfj[i] = ydjyTreeBean.getValues().getOrganAreaId().get(i).getValue();
                }
                DialogUnit.chooseDialog(mContext, 0, "选择所属分局", ssfj, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choose1 = which;
                        tvSsfj2.setText(ydjyTreeBean.getValues().getOrganAreaId().get(which).getValue());
                        choose2 =   -1;
                        tvSsgss2.setText("");
                        choose3 =   -1;
                        tvSsxzjd2.setText("");
                    }
                });
            }
        });

        tvSsgss2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choose1 != -1) {
                    int size = ydjyTreeBean.getValues().getOrganAreaId().get(choose1).getChildren().size();
                    String[] ssgss = new String[size];
                    for (int i = 0; i < size; i++) {
                        ssgss[i] = ydjyTreeBean.getValues().getOrganAreaId().get(choose1).getChildren().get(i).getValue();
                    }
                    DialogUnit.chooseDialog(mContext, 0, "选择所属工商所", ssgss, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            choose2 =   which;
                            tvSsgss2.setText(ydjyTreeBean.getValues().getOrganAreaId().get(choose1).getChildren().get(which).getValue());
                            choose3 =   -1;
                            tvSsxzjd2.setText("");
                        }
                    });
                }else{
                    Toast.makeText(mContext,"请选择所属分局",Toast.LENGTH_LONG).show();
                }
            }
        });

        tvSsxzjd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choose2 != -1) {
                    if(ydjyTreeBean.getValues().getOrganAreaId().get(choose1).getChildren().get(choose2).getChildren()!=null) {
                        int size = ydjyTreeBean.getValues().getOrganAreaId().get(choose1).getChildren().get(choose2).getChildren().size();
                        String[] ssxzjd = new String[size];
                        for (int i = 0; i < size; i++) {
                            ssxzjd[i] = ydjyTreeBean.getValues().getOrganAreaId().get(choose1).getChildren().get(choose2).getChildren().get(i).getValue();
                        }
                        DialogUnit.chooseDialog(mContext, 0, "选择所属乡镇街道", ssxzjd, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                choose3 = which;
                                tvSsxzjd2.setText(ydjyTreeBean.getValues().getOrganAreaId().get(choose1).getChildren().get(choose2).getChildren().get(which).getValue());
                            }
                        });
                    }else{
                        DialogUnit.chooseDialog(mContext, 0, "选择所属乡镇街道", null, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                choose3 = which;
                                tvSsxzjd2.setText(ydjyTreeBean.getValues().getOrganAreaId().get(choose1).getChildren().get(choose2).getChildren().get(which).getValue());
                            }
                        });
                    }
                }else{
                    Toast.makeText(mContext,"请选择所属工商所",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
