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
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.teamwork.addressbox.TxlDeptActivity;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.MaxHeightView;
import com.wondersgroup.commerce.widget.TableRow;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private TableRow djbh, dwmc, djjg, slsj;
    private TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);

    private String timeStart = "", timeEnd = "";
    private HashMap<String, String> params = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query3);
        ButterKnife.bind(this);
        params.put("organId", loginBean.getResult().getOrganId());
        initView();
    }

    public void initView() {
        tvTitle.setText("广告查询");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //高度限制布局 待测试
        for (int i = 0; i < 1; i++) {
            djbh = new TableRow.Builder(this)
                    .title("广告发布登记编号")
                    .input("请输入")
                    .build();
            layoutAdd.addView(djbh);

            dwmc = new TableRow.Builder(this)
                    .title("单位名称")
                    .input("请输入")
                    .build();
            layoutAdd.addView(dwmc);


            slsj = new TableRow.Builder(this)
                    .title("受理时间")
                    .time("开始时间", "结束时间")
                    .timeBack(new TableRow.Builder.TimeListener() {
                        @Override
                        public void timeBack(String key, String value) {
                            if ("开始时间".equals(key)) {
                                timeStart = value;
                            } else if ("结束时间".equals(key)) {
                                timeEnd = value;
                            }
                        }
                    })
                    .build();
            layoutAdd.addView(slsj);

            djjg = new TableRow.Builder(this)
                    .title("登记机关")
                    .arrowSelect()
                    .content(loginBean.getResult().getOrganName())
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            startActivityForResult(new Intent(QueryActivity.this, TxlDeptActivity.class), 0);
                        }
                    })
                    .build();
            layoutAdd.addView(djjg);
        }

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.put("certNo", djbh.getInput());
                params.put("adIssEnt", dwmc.getInput());
                params.put("appDateBegin", timeStart);
                params.put("appDateEnd", timeEnd);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARAMS", params);
                Intent intent = new Intent(QueryActivity.this, RecyclerActivity.class);
                intent.putExtra(Constants.TYPE, "gglb");
                intent.putExtra(Constants.TITLE, "查询结果");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.btn_clear)
    public void clear(View v) {
        djbh.setInput("");
        dwmc.setInput("");
        slsj.clearTime();
        timeStart = "";
        timeEnd = "";
        djjg.setTvContent(loginBean.getResult().getOrganName());
        params.put("organId", loginBean.getResult().getOrganId());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b = data.getExtras(); //data为B中回传的Intent
                String organId = b.getString("organId");//str即为回传的值
                String organName = b.getString("organName");
                djjg.setTvContent(organName);
                params.put("organId", organId);
                break;
            default:
                break;
        }
    }

}
