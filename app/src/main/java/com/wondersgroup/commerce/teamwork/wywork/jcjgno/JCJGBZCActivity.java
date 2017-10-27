package com.wondersgroup.commerce.teamwork.wywork.jcjgno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.dailycheck.DateOnTouch;
import com.wondersgroup.commerce.teamwork.mysupervision.KeyValue;
import com.wondersgroup.commerce.teamwork.mysupervision.MapToListUtil;
import com.wondersgroup.commerce.teamwork.mysupervision.ShowSingleDialogListListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JCJGBZCActivity extends AppCompatActivity{

    @BindView(R.id.check_date_start)
    TextView startDate;
    @BindView(R.id.check_date_end)
    TextView endDate;
    @BindView(R.id.btn_check)
    TextView btnCheck;
    @BindView(R.id.checkType)
    TextView checkType;

    private String strType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_result_no);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolbarText.setText("检查结果不正常");

        init();
    }

    private void init() {

        checkType.setTag("");
        Map<String, String> map = new HashMap<>();
        map.put("qy","企业");
        map.put("gr","个人");
        MapToListUtil checkUtil = new MapToListUtil(map);
        List<KeyValue> checkKeyValues = checkUtil.mapToKeyValues();
        checkType.setOnClickListener(new ShowSingleDialogListListener(
                        checkKeyValues, this, checkType));


        endDate.setText("");
        startDate.setText("");
        endDate.setOnTouchListener(new DateOnTouch(this, endDate));
        startDate.setOnTouchListener(new DateOnTouch(this, startDate));

        btnCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                strType = checkType.getText().toString();

                Intent intent = new Intent(JCJGBZCActivity.this,JCJGBZCListActivity.class);
                if(strType.equals("企业")) {
                    intent.putExtra("jcjgbzc_type", "0");
                    intent.putExtra("checkDateBegin", startDate.getText().toString().trim());
                    intent.putExtra("checkDateEnd", endDate.getText().toString().trim());
                    startActivity(intent);
                }else if(strType.equals("个人")){
                    intent.putExtra("jcjgbzc_type", "1");
                    intent.putExtra("checkDateBegin", startDate.getText().toString().trim());
                    intent.putExtra("checkDateEnd", endDate.getText().toString().trim());
                    startActivity(intent);
                }else{
                    Toast.makeText(JCJGBZCActivity.this,"请选择检查类型",Toast.LENGTH_LONG).show();
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
