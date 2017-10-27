package com.wondersgroup.commerce.teamwork.myhwggdq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by jiajiangyi on 2016/4/26 0026.
 */
public class HwggdqDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_realNo)
    TextView tvRealNo;
    @BindView(R.id.tv_createDate)
    TextView tvCreateDate;
    @BindView(R.id.tv_unitName)
    TextView tvUnitName;
    @BindView(R.id.tv_appName)
    TextView tvAppName;
    @BindView(R.id.tv_customerName)
    TextView tvCustomerName;
    @BindView(R.id.tv_startDate)
    TextView tvStartDate;
    @BindView(R.id.tv_endDate)
    TextView tvEndDate;
    @BindView(R.id.tv_adModeName)
    TextView tvAdModeName;
    @BindView(R.id.tv_adModeId)
    TextView tvAdModeId;
    @BindView(R.id.tv_equStartDate)
    TextView tvEquStartDate;
    @BindView(R.id.tv_equEndDate)
    TextView tvEquEndDate;
    @BindView(R.id.tv_posKindName)
    TextView tvPosKindName;
    @BindView(R.id.tv_posTypeName)
    TextView tvPosTypeName;
    @BindView(R.id.tv_posOwnerName)
    TextView tvPosOwnerName;
    @BindView(R.id.tv_moneyCostStr)
    TextView tvMoneyCostStr;

    private String adNo;
    private HwggdqDetailBean hwggdqDetailBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hqggdq_detail);
        ButterKnife.bind(this);
        adNo = getIntent().getStringExtra("realNo");

        initData();

    }

    private void initData() {

        MyProgressDialog.show(this);
        Map<String, String> map = new HashMap<String, String>();
        map.put("adNo", adNo);
        Call<HwggdqDetailBean> call = ApiManager.shApi.queryHWGGDQDetail(map);
        call.enqueue(new Callback<HwggdqDetailBean>() {
            @Override
            public void onResponse(Response<HwggdqDetailBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    hwggdqDetailBean = response.body();
                    Toast.makeText(HwggdqDetailActivity.this,hwggdqDetailBean.getMsg(),Toast.LENGTH_SHORT).show();
                    setDatas(hwggdqDetailBean);
                } else {
                    Toast.makeText(HwggdqDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                    finish();
                }
                MyProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(HwggdqDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void setDatas(HwggdqDetailBean hwggdqDetailBean) {
         tvRealNo.setText(hwggdqDetailBean.getValues().getRealNo());
         tvCreateDate.setText(hwggdqDetailBean.getValues().getCreateDate());
         tvUnitName.setText(hwggdqDetailBean.getValues().getUnitName());
         tvAppName.setText(hwggdqDetailBean.getValues().getAppName());
         tvCustomerName.setText(hwggdqDetailBean.getValues().getCustomerName());
         tvStartDate.setText(hwggdqDetailBean.getValues().getStartDate());
         tvEndDate.setText(hwggdqDetailBean.getValues().getEndDate());
         tvAdModeName.setText(hwggdqDetailBean.getValues().getAdModeName());
         tvAdModeId.setText(hwggdqDetailBean.getValues().getAdModeId());
         tvEquStartDate.setText(hwggdqDetailBean.getValues().getEquStartDate());
         tvEquEndDate.setText(hwggdqDetailBean.getValues().getEquEndDate());
         tvPosKindName.setText(hwggdqDetailBean.getValues().getPosKindName());
         tvPosTypeName.setText(hwggdqDetailBean.getValues().getPosTypeName());
         tvPosOwnerName.setText(hwggdqDetailBean.getValues().getPosOwnerName());
         tvMoneyCostStr.setText(hwggdqDetailBean.getValues().getMoneyCostStr());
    }
}
