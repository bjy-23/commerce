package com.wondersgroup.commerce.teamwork.ztxxcx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.TableRow;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yclli on 2016/3/22.
 */
public class InputActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.rootLayout)LinearLayout rootLayout;

    private TableRow nameView;
    private TableRow uidView;
    private TableRow registrationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_input);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("主体信息查询");

        nameView = new TableRow.Builder(this)
                .title("企业名称")
                .input("请输入查询企业名称")
                .hideBtmLine()
                .build();
        rootLayout.addView(nameView);
        uidView = new TableRow.Builder(this)
                .title("统一社会信用代码")
                .input("请输入统一社会信用代码")
                .hideBtmLine()
                .build();
        rootLayout.addView(uidView);
        registrationView = new TableRow.Builder(this)
                .title("注册号")
                .input("请输入查询注册号")
                .hideBtmLine()
                .build();
        rootLayout.addView(registrationView);
        final Button btn=new Button(this);
        btn.setText("查询");
        btn.setTextColor(ContextCompat.getColor(this, R.color.white));
        LinearLayout.LayoutParams contentParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.setMargins((int) DWZH.dp2pt(this, 16), (int) DWZH.dp2pt(this, 50), (int) DWZH.dp2pt(this, 16), 0);
        btn.setLayoutParams(contentParams);
        btn.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this, RecyclerActivity.class);
                intent.putExtra("title", "查询结果");
                intent.putExtra("type", "ZTCX");
                intent.putExtra("name", nameView.getContent());
                intent.putExtra("uid", uidView.getContent());
                intent.putExtra("register", registrationView.getContent());
                startActivity(intent);
            }
        });
        rootLayout.addView(btn);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
