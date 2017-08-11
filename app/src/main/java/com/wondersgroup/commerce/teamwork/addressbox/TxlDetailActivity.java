package com.wondersgroup.commerce.teamwork.addressbox;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RootActivity;
import com.wondersgroup.commerce.model.AddressDetail;
import com.wondersgroup.commerce.model.Dept;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TxlDetailActivity extends RootActivity {
    private TextView phoneTv;
    private TextView addressTv;
    private TextView deptTv;
    private TextView zwTv;
    private TextView czTv;
    private TextView zzdhTv;
    private TextView bgsdhTv;
    private TextView sjTv;
    private TextView ybTv;
    private TextView dwTv;
    private TextView nameTv;
    private TextView titleTv;

    private LinearLayout callBtn;
    private LinearLayout msgBtn;

    private LinearLayout backBtn;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getIntent().getStringExtra("userId");

        setContentView(R.layout.activity_txl_detail);
        findView();
        getTxlDetail();


    }

    private void getTxlDetail() {
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(mContext);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010004");
        map.put("addlistId", userId);
        Call<AddressDetail> call = ApiManager.oaApi.deptOrganDetail(map);
        call.enqueue(new Callback<AddressDetail>() {
                         @Override
                         public void onResponse(Response<AddressDetail> response, Retrofit retrofit) {

                             if (response.isSuccess()) {

                                 AddressDetail address = response.body();

                                 if (address.getResult() != null) {
                                     phoneTv.setText(address.getResult().getCellphone());
                                     addressTv.setText(address.getResult().getAddress());
                                     deptTv.setText(address.getResult().getDept());
                                     zwTv.setText(address.getResult().getDuty());
                                     czTv.setText(address.getResult().getFax());
                                     zzdhTv.setText(address.getResult().getHousetel());
                                     bgsdhTv.setText(address.getResult().getOfficetel());
                                     sjTv.setText(address.getResult().getCellphone());
                                     ybTv.setText(address.getResult().getPostalcode());
                                     dwTv.setText(address.getResult().getUnit());
                                     nameTv.setText(address.getResult().getName());

                                     dialog.dismiss();
                                 } else {
                                     getResources().getString(R.string.error_data);

                                     dialog.dismiss();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Throwable t) {
                             dialog.dismiss();
                             Toast.makeText(mContext, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                         }
                     }

        );
    }

    private void findView() {
        backBtn = (LinearLayout) findViewById(R.id.ll_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText("联系人信息");
        titleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nameTv = (TextView) findViewById(R.id.tv_name);
        phoneTv = (TextView) findViewById(R.id.tv_phone);
        addressTv = (TextView) findViewById(R.id.tv_address);
        deptTv = (TextView) findViewById(R.id.tv_dept);
        zwTv = (TextView) findViewById(R.id.tv_zw);
        czTv = (TextView) findViewById(R.id.tv_cz);
        zzdhTv = (TextView) findViewById(R.id.tv_zzdh);
        bgsdhTv = (TextView) findViewById(R.id.tv_bgsdh);
        sjTv = (TextView) findViewById(R.id.tv_sj);
        ybTv = (TextView) findViewById(R.id.tv_yb);
        dwTv = (TextView) findViewById(R.id.tv_dw);
        callBtn = (LinearLayout) findViewById(R.id.layout_phonecall);
        msgBtn = (LinearLayout) findViewById(R.id.layout_message);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用intent启动拨打电话
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phoneTv.getText().toString());
                intent.setData(data);
                startActivity(intent);
            }
        });

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto://" + phoneTv.getText().toString());
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "");
                startActivity(intent);
            }
        });
    }

}
