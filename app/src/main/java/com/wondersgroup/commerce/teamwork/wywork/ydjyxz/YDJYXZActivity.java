package com.wondersgroup.commerce.teamwork.wywork.ydjyxz;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.CheckUtil;


import butterknife.Bind;
import butterknife.ButterKnife;

public class YDJYXZActivity extends AppCompatActivity{

    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolTitle;
    @Bind(R.id.btn_check)
    Button btnCheck;
    @Bind(R.id.name)
    EditText etName;
    @Bind(R.id.zch)
    EditText etZch;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydjy_add);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolTitle.setText("全市主体查询");

        init();
    }

    private void init() {
        type = Integer.parseInt(getIntent().getStringExtra("type"));
        if(type==0) {
            etName.setHint("企业名称");
            CheckUtil.limitCheckMinCount(etName, 4);
        }else if(type==1){
            etName.setHint("经营者");
            CheckUtil.limitCheckMinCount(etName, 3);
        }
        etZch.setHint("注册号");
        etZch.setInputType(InputType.TYPE_CLASS_NUMBER);

        btnCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(etName.getError()==null) {
                    Intent intent = new Intent(YDJYXZActivity.this, YDJYXZListActivity.class);
                    intent.putExtra("type", type + "");
                    intent.putExtra("name", etName.getText().toString().trim());
                    intent.putExtra("zch", etZch.getText().toString().trim());
                    startActivity(intent);
                }else{
                    etName.requestFocus();
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
