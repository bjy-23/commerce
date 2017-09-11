package com.wondersgroup.commerce.teamwork.tztg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.FJXZAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.FileBean;
import com.wondersgroup.commerce.model.GgDetails;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.CustomApplication;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by yclli on 2015/11/26.
 */
public class GGDetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_txt) TextView title;
    @Bind(R.id.toolbar_btn) Button btn;
    @Bind(R.id.tv1) TextView tv1;
    @Bind(R.id.tv2) TextView tv2;
    @Bind(R.id.tv3) TextView tv3;
    @Bind(R.id.tv4) TextView tv4;
    @Bind(R.id.tv5) TextView tv5;
    @Bind(R.id.fujianTitle) TextView doTitle;
    @Bind(R.id.listview)
    ListView listView;

    private final String TAG = "GGDetailActivity";
    private String bulletinId;
    private CustomApplication app;
    private BaseAdapter adapter;
    private ArrayList<GgDetails.DocAttachVoList> docAttachVoList;
    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ggdetail);
        ButterKnife.bind(this);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
//        app = (CustomApplication) getApplication();
        Intent intent = getIntent();
        bulletinId = intent.getStringExtra("bulletinId");
        title.setText("通知通告详情");
        toolbar.setTitle("");
        btn.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010002");
        map.put("bulletinId", bulletinId);
        map.put("userId", loginBean.getResult().getUserId());
        map.put("deptId", loginBean.getResult().getDeptId());
        map.put("organId", loginBean.getResult().getOrganId());

        Call<GgDetails> call = ApiManager.oaApi.getBulletinDetail(map);
        Log.d(TAG, "map.toString() = " + map.toString());

        call.enqueue(new Callback<GgDetails>() {
            @Override
            public void onResponse(Response<GgDetails> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    GgDetails ggDetails = response.body();
                    tv1.setText(ggDetails.getResult().getResult().getTitle());
                    tv2.setText("发布范围：" + ggDetails.getResult().getResult().getOpenrange());
                    tv3.setText(ggDetails.getResult().getResult().getContent());
                    tv4.setText(ggDetails.getResult().getResult().getOrgname() + "    " + ggDetails.getResult().getResult().getDeptname());
                    tv5.setText(ggDetails.getResult().getResult().getRegdate());
                    docAttachVoList = ggDetails.getResult().getDocAttachVoList();
                    if (docAttachVoList.size() == 0) {
                        doTitle.setVisibility(View.GONE);
                    }
                    adapter = new FJXZAdapter(GGDetailActivity.this, docAttachVoList);
                    listView.setAdapter(adapter);

                } else {
                    Log.d(TAG, "GGDetailActivity --------------- 5");
                    Toast.makeText(GGDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "myCaseToInvestigate --------------- 5t.getMessage() = " + t.getMessage());
                Toast.makeText(GGDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyProgressDialog.show(GGDetailActivity.this);
                Map<String, String> map = new HashMap<String, String>();
                map.put("wsCodeReq", "07010013");
                map.put("attachId", docAttachVoList.get(position).getAttachId());

                Call<FileBean> call = ApiManager.oaApi.downloadAttachment(map);
                Log.d(TAG, "map.toString() = " + map.toString());

                call.enqueue(new Callback<FileBean>() {
                    @Override
                    public void onResponse(Response<FileBean> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            MyProgressDialog.dismiss();
                            FileBean fileBean = response.body();

                            if (fileBean == null ||
                                    fileBean.getResult().getAttachFile() == null || fileBean.getResult().getAttachFile().getAttachFileStr() == null) {
                                Toast.makeText(GGDetailActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            String abc = fileBean.getResult().getAttachFile().getAttachFileStr();
                            try {
                                String cachePath = getApplicationContext().getExternalCacheDir().toString();
                                Log.d(TAG, "cachePath = " + cachePath);
//                                fileUtils.decoderBase64File(GGDetailActivity.this, abc, cachePath + "/" + fileBean.getResult().getAttachFile().getAttachName());
//                                fileUtils.decoderBase64File(GGDetailActivity.this, abc, app.getCachePath() + "/" + fileBean.getAttachFile().getAttachName());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            Log.d(TAG, "GGDetailActivity --------------- 5");
                            Toast.makeText(GGDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d(TAG, "myCaseToInvestigate --------------- 5t.getMessage() = " + t.getMessage());
                        Toast.makeText(GGDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

