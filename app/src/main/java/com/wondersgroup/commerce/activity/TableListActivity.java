package com.wondersgroup.commerce.activity;

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
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.ccjc.DicResult;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.ListDialog;
import com.wondersgroup.commerce.widget.TableRow;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TableListActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.rootLayout)LinearLayout rootLayout;

    private Button btn;
    private String organId;
    private String pcdm;
    private String ztdm;

    private RootAppcation app;
    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.app_back);
        app = (RootAppcation) getApplication();
        loginBean = Hawk.get(Constants.LOGIN_BEAN);

        String titleString=getIntent().getStringExtra("title");
        if(titleString!=null)title.setText(titleString);
        String type=getIntent().getStringExtra("type");
        organId=loginBean.getResult().getOrganId();
//        if (organId.length()>5){
//            organId = organId.substring(0,6);
//        }
        Map<String,String> body =new HashMap<>();
        body.put("organId", organId);
        body.put("wsCodeReq", "020001");
        Call<DicResult> call= ApiManager.ccjcApi.ccDic(body);
        call.enqueue(new Callback<DicResult>() {
            @Override
            public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                if (response.isSuccess()&&response.body()!=null) {
                    DicResult result = response.body();
                    if ("200".equals(result.getCode())) {
                        Hawk.put("Dic", result.getResult());
                    } else {
                        Toast.makeText(TableListActivity.this, "获取字典失败", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(TableListActivity.this, "获取字典失败", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(TableListActivity.this, "获取字典失败", Toast.LENGTH_LONG).show();
            }
        });
        generateLayout(type);
    }

    public void generateLayout(String type){
        if("CCJCCX".equals(type)){
            final TableRow uniCode=new TableRow.Builder(this)
                    .title("统一社会信用代码")
                    .input("请输入统一社会信用代码")
                    .build();
            final TableRow entName=new TableRow.Builder(this)
                    .title("主体名称")
                    .input("请输入主体全称或名称前面完整部分")
                    .build();
            final TableRow regNo=new TableRow.Builder(this)
                    .title("注册号")
                    .input("请输入注册号")
                    .build();
            final TableRow pici=new TableRow.Builder(this)
                    .title("抽查批次")
                    .select("请选择抽查批次")
                    .build();
            pici.setSelect(new TableRow.SelectCallBack() {
                @Override
                public void onSelect(TableRow row, int which) {
                    ListDialog dialog=ListDialog.newInsance("请选择抽查批次","CCPC");
                    dialog.setListener(new ListDialog.OnSelected() {
                        @Override
                        public void onSelected(String type, String typeCode) {
                            if("-1".equals(typeCode)){
                                pici.setContent("");
                            }else {
                                pici.setContent(type);
                                pcdm = typeCode;
                            }
                        }
                    });
                    dialog.show(getSupportFragmentManager(),"CCPCXZ");
                }
            });
            final TableRow zhuangtai=new TableRow.Builder(this)
                    .title("抽查状态")
                    .select("请选择抽查状态")
                    .required()
                    .build();
            zhuangtai.setSelect(new TableRow.SelectCallBack() {
                @Override
                public void onSelect(TableRow row, int which) {
                    ListDialog dialog=ListDialog.newInsance("请选择抽查状态","CCZT");
                    dialog.setListener(new ListDialog.OnSelected() {
                        @Override
                        public void onSelected(String type, String typeCode) {
                            zhuangtai.setContent(type);
                            ztdm=typeCode;
                        }
                    });
                    dialog.show(getSupportFragmentManager(),"CCZTXZ");
                }
            });
            generateButton("查询");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(zhuangtai.getContent().isEmpty()){
                        Toast.makeText(TableListActivity.this,"请选择抽查状态",Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent = new Intent(TableListActivity.this, RecyclerActivity.class);
                        intent.putExtra("type", "CCJCCX");
                        intent.putExtra("title", Constants.ccjccx);
                        HashMap<String,String> body=new HashMap<>();
                        body.put("organId",organId);
                        body.put(Constants.USER_ID,loginBean.getResult().getUserId());
                        body.put("flowStatus",ztdm);
                        body.put("wsCodeReq","03010016");
                        body.put("uniScid",uniCode.getInput());
                        body.put("entName",entName.getInput());
                        body.put("regNo",regNo.getInput());
                        body.put("checkBatchId",pcdm);
                        intent.putExtra("body",body);
                        startActivity(intent);
                    }
                }
            });

            rootLayout.addView(uniCode);
            rootLayout.addView(entName);
            rootLayout.addView(regNo);
            rootLayout.addView(pici);
            rootLayout.addView(zhuangtai);
            rootLayout.addView(btn);
        }
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
