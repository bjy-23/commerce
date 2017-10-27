package com.wondersgroup.commerce.teamwork.wywork.ydjyxz;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YDJYContentActivity extends AppCompatActivity{

    @BindView(R.id.mid_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolTitle;

    @BindView(R.id.ydjy_name1)
    TextView tvName1;
    @BindView(R.id.ydjy_name2)
    TextView tvName2;
    @BindView(R.id.ydjy_name_zch1)
    TextView tvZch1;
    @BindView(R.id.ydjy_name_zch2)
    TextView tvZch2;
    @BindView(R.id.ydjy_qylx1)
    TextView tvQylx1;
    @BindView(R.id.ydjy_qylx2)
    TextView tvQylx2;
    @BindView(R.id.ydjy_ssjjxq1)
    TextView tvSsjjxq1;
    @BindView(R.id.ydjy_ssjjxq2)
    TextView tvSsjjxq2;
    @BindView(R.id.ydjy_ssfj1)
    TextView tvSsfj1;
    @BindView(R.id.ydjy_ssfj2)
    TextView tvSsfj2;
    @BindView(R.id.ydjy_sdgss1)
    TextView tvSdgss1;
    @BindView(R.id.ydjy_sdgss2)
    TextView tvSdgss2;
    @BindView(R.id.ydjy_xzjd1)
    TextView tvXzjd1;
    @BindView(R.id.ydjy_xzjd2)
    TextView tvXzjd2;
    @BindView(R.id.ydjy_dz1)
    TextView tvDz1;
    @BindView(R.id.ydjy_dz2)
    TextView tvDz2;


    private String entityId;
    private int type;
    private List<String> list;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ydjy_conten);
        ButterKnife.bind(this);

        type    =   Integer.parseInt(getIntent().getStringExtra("type"));
        entityId    =   getIntent().getStringExtra("entityId");
        list    =   getIntent().getStringArrayListExtra("list");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolTitle.setText(list.get(0));

        init();
    }

    private void init() {
        mContext    =   YDJYContentActivity.this;

        if(type==0){//企业
            tvName2.setText(list.get(0));
            tvZch2.setText(list.get(1));
            tvQylx2.setText(list.get(2));
            tvSsjjxq2.setText(list.get(3));
            tvSsfj2.setText(list.get(4));
            tvSdgss2.setText(list.get(5));
            tvXzjd2.setText(list.get(6));
            tvDz2.setText(list.get(7));
        }


//        toolAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext,YDJYAddActivity.class);
//                intent.putExtra("type",type);
//                intent.putExtra("entityId",entityId);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
