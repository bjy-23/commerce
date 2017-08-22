package com.wondersgroup.commerce.teamwork.tztg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.InfoItemListAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Ggcx;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 1229 on 2015/12/11.
 */
public class TZTGActivity extends AppCompatActivity {

    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.activity_gg_list) ListView list;
    @Bind(R.id.layout_error)
    LinearLayout layoutError;

    private String TAG = "TZTGActivity";
    private InfoItemListAdapter adapter;
    private ImageView backIv;
    private TotalLoginBean loginBean;

    ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_gg);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("通知公告");

        loginBean = Hawk.get(Constants.LOGIN_BEAN);

        getData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnItemClick(R.id.activity_gg_list)
    public void onItenClick(int position){
        Intent intent = new Intent(this, GGDetailActivity.class);
        intent.putExtra("bulletinId",arrayList.get(position));
        startActivity(intent);
    }

    public void getData(){
        final List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010001");
        map.put("userId", loginBean.getResult().getUserId());
        map.put("deptId", loginBean.getResult().getDeptId());
        map.put("organId", loginBean.getResult().getOrganId());
        Call<Ggcx> call = ApiManager.oaApi.getBulletinList(map);
        call.enqueue(new Callback<Ggcx>() {
            @Override
            public void onResponse(Response<Ggcx> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    Ggcx ggcx = response.body();

                    if ((null == ggcx) || (null == ggcx.getResult()) ||
                            (ggcx.getResult().getBulletinInfo() == null)) {
                        layoutError.setVisibility(View.VISIBLE);
                        return;
                    }
                    for (int i = 0; i < ggcx.getResult().getBulletinInfo().size(); i++) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("name", ggcx.getResult().getBulletinInfo().get(i).getTitle());
                        map.put("rangeName", ggcx.getResult().getBulletinInfo().get(i).getOpenrange());
                		map.put("rangeImg", ("仅本局可见".equals((String) ggcx.getResult().getBulletinInfo().get(i).getOpenrange())) ? R.mipmap.icons_bureau : R.mipmap.icons_custorm);                        map.put("date", ggcx.getResult().getBulletinInfo().get(i).getRegdate());
                        arrayList.add(i, ggcx.getResult().getBulletinInfo().get(i).getBulletinId());
                        datalist.add(map);
                    }
                    if(datalist.size()!=0){
                    adapter = new InfoItemListAdapter(TZTGActivity.this, datalist);
                    list.setAdapter(adapter);
                    }
                } else {
                    layoutError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                layoutError.setVisibility(View.VISIBLE);
            }
        });

    }
}
