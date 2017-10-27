package com.wondersgroup.commerce.teamwork.wywork.jyqx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.dailycheck.UtilForFragment;
import com.wondersgroup.commerce.teamwork.myxqzgdq.CheckJGJLBean;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.fragment.DetailJGJLFragment;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Administrator on 2016/4/21 0021.
 */
public class JyqxdqQyxxJgjlActivity extends AppCompatActivity {
    @BindView(R.id.mid_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView title;

    public static int currTab = 0;
    public static CheckJGJLBean checkJGJLBean;
    private AppCompatActivity context;

    private String entityId;
    private String planId;
    private String entityTypeId;
    private String operType;
    private String actnId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_activity);
        context = this;
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("监管记录详情");

        actnId = "0003";
        entityId = context.getIntent().getStringExtra("entityId");
        planId = context.getIntent().getStringExtra("planId");
        entityTypeId = context.getIntent().getStringExtra("entityTypeId");
        operType = context.getIntent().getStringExtra("operType");

        if(entityTypeId.equals("02")){
            currTab = 0;
        }else {
            currTab = 1;
        }

        initData();



    }

    private void initData() {

        MyProgressDialog.show(context);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("entityId", entityId);
        map.put("planId", planId);
        map.put("entityTypeId", entityTypeId);
        map.put("operType", operType);
        map.put("actnId", actnId);
        Call<CheckJGJLBean> call = ApiManager.shApi.queryCheckJGJLDetail(map);

        call.enqueue(new Callback<CheckJGJLBean>() {
            @Override
            public void onResponse(Response<CheckJGJLBean> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                if (response.isSuccess()) {
                    checkJGJLBean = response.body();
                    JyqxdqQyxxJgjlActivity.setCheckJGJLBean(checkJGJLBean);
                    UtilForFragment.switchContent(context, new DetailJGJLFragment(), R.id.content);

                } else {
                    MyProgressDialog.dismiss();
                    Toast.makeText(context,  getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                    context.finish();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(context, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                Log.d("okhttp#onFailure###", t.getMessage());

                context.finish();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public static void setCurrTab(int tab){
        currTab = tab;
    }
    public static int getCurrTab(){
        return currTab;
    }

    public static void setCheckJGJLBean(CheckJGJLBean bean){
        checkJGJLBean = bean;
    }
    public static CheckJGJLBean getCheckJGJLBean(){
        return  checkJGJLBean;
    }
}
