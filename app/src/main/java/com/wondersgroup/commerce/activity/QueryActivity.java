package com.wondersgroup.commerce.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.MaxHeightView;
import com.wondersgroup.commerce.widget.TableRow;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QueryActivity extends AppCompatActivity {
    @Bind(R.id.maxHeightView)
    MaxHeightView maxHeightView;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.layout_add)
    LinearLayout layoutAdd;
    @Bind(R.id.btn_clear)
    Button btnClear;
    @Bind(R.id.btn_query)
    Button btnQuery;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_back)
    ImageView imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query3);

        ButterKnife.bind(this);

        initView();
    }

    public void initView(){
        tvTitle.setText("广告查询");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //高度限制布局 待测试
        for (int i=0; i<4; i++){
            TableRow djbh = new TableRow.Builder(this)
                    .title("广告发布登记编号")
                    .input("请输入")
                    .build();
            layoutAdd.addView(djbh);

            TableRow dwmc = new TableRow.Builder(this)
                    .title("单位名称")
                    .input("请输入")
                    .build();
            layoutAdd.addView(dwmc);

            TableRow slsj = new TableRow.Builder(this)
                    .title("受理时间")
                    .build();
            layoutAdd.addView(slsj);

            TableRow djjg = new TableRow.Builder(this)
                    .title("登记机关")
                    .arrowSelect()
                    .build();
            layoutAdd.addView(djjg);
        }

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryActivity.this, ViewPagerActivity.class);
                intent.putExtra(Constants.TITLE, "广告详情");
                intent.putExtra(Constants.TYPE, "GGXQ");
                startActivity(intent);
            }
        });
    }

}
