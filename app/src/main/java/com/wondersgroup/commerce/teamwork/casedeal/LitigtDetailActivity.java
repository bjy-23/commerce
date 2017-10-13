package com.wondersgroup.commerce.teamwork.casedeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.BaseActivity;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.teamwork.casedeal.bean.LitigtBean;
import com.wondersgroup.commerce.widget.TableRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LitigtDetailActivity extends BaseActivity {
    @Bind(R.id.layout_details)
    LinearLayout layoutDetails;
    @Bind(R.id.layout_error)
    LinearLayout layoutError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_litigt_detail);
        ButterKnife.bind(this);

        tvTitle.setText("当事人详情");

        queryDetail();
    }

    public void queryDetail(){
        String url = CaseApi.GET_LITIGT_DETAIL;
        HashMap map = new HashMap();
        map.put("wsCodeReq", "03010017");
        map.put("clueNo", getIntent().getStringExtra("clueNo"));
        map.put("litigtId",getIntent().getStringExtra("litigtId"));
        Call<Result<List<LitigtBean>>> call = ApiManager.shyApi.getLitigtDetail(url,map);
        call.enqueue(new Callback<Result<List<LitigtBean>>>() {
            @Override
            public void onResponse(Response<Result<List<LitigtBean>>> response, Retrofit retrofit) {
                if (response.body().getObject() != null && response.body().getObject().size() != 0){
                    List<LitigtBean> list = response.body().getObject();
                    handleData(list);
                }else {
                    layoutError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                layoutError.setVisibility(View.VISIBLE);
            }
        });
    }

    public void handleData(List<LitigtBean> list){
        for (LitigtBean litigtBean : list){
            if ("tableName".equals(litigtBean.getKey())){
                TableRow title = new TableRow.Builder(this)
                        .asTitle(litigtBean.getName())
                        .build();
                layoutDetails.addView(title);
            }else {
                TableRow item = new TableRow.Builder(this)
                        .title(litigtBean.getName())
                        .content(litigtBean.getValue())
                        .build();
                layoutDetails.addView(item);
            }
        }
    }
}
