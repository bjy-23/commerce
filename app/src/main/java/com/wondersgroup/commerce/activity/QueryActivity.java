package com.wondersgroup.commerce.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Dept;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.addressbox.TxlDeptActivity;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.widget.MaxHeightView;
import com.wondersgroup.commerce.widget.TableRow;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 广告查询
 */
public class QueryActivity extends AppCompatActivity {
    @BindView(R.id.maxHeightView)
    MaxHeightView maxHeightView;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.layout_add)
    LinearLayout layoutAdd;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_back)
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
        if (Hawk.get("ORGAN_INFO") == null)
            getAllDept();
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
                    .timeHints("开始时间", "结束时间")
                    .time("", "")
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
                    .select("")
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

    private void getAllDept() {
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(QueryActivity.this)
                .build();
        loadingDialog.show();
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000001");
        map.put("loginName", Hawk.get(Constants.LOGIN_NAME).toString());
        map.put("password", Hawk.get(Constants.PASSWORD).toString());
        map.put("userId", loginBean.getResult().getUserId());
        map.put("version", "1.0.2");
        map.put("organId", loginBean.getResult().getOrganId());
        map.put("deptId", loginBean.getResult().getDeptId());

        Call<Dept> call = ApiManager.hbApi.deptAll(map);
        call.enqueue(new Callback<Dept>() {
            @Override
            public void onResponse(Response<Dept> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                if (response.isSuccess()) {
                    Dept dept = response.body();
                    if (dept.getResult() != null) {
                        Hawk.put("ORGAN_INFO", dept.getResult().getOrganInfo());
                    } else {
                        Toast.makeText(QueryActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(QueryActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(QueryActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
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
