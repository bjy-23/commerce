package com.wondersgroup.commerce.fgdj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.TableRow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListQueryActivity extends AppCompatActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_query)
    LinearLayout layoutQuery;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_query);

        ButterKnife.bind(this);

        final TableRow nameRow =new TableRow.Builder(this)
                .title("名称")
                .input("请输入名称")
                .build();
        layoutQuery.addView(nameRow);

        final TableRow codeRow =new TableRow.Builder(this)
                .title("注册号/统一社会信用代码")
                .input("请输入注册号/统一社会信用代码")
                .build();
        layoutQuery.addView(codeRow);

        generateButton("查询");
        layoutQuery.addView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListQueryActivity.this,ListInfoActivity.class);
                intent.putExtra(Constants.TYPE, Constants.CHA_XUN);
                intent.putExtra(Constants.ENT_NAME,nameRow.getInput());
                intent.putExtra(Constants.REG_NO,codeRow.getInput());
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText("非公党建信息查询");
    }

    public void generateButton(String text){
        btn=new Button(this);
        btn.setText(text);
        btn.setTextColor(ContextCompat.getColor(this, R.color.white));
        LinearLayout.LayoutParams contentParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.setMargins((int) DWZH.dp2pt(this, 16), (int) DWZH.dp2pt(this, 50), (int) DWZH.dp2pt(this, 16), 0);
        btn.setLayoutParams(contentParams);
        btn.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
    }
}
