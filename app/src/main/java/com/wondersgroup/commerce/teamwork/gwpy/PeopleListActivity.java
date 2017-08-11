package com.wondersgroup.commerce.teamwork.gwpy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.NextPeoListBean;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;

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
 * Created by yclli on 2015/12/9.
 */
public class PeopleListActivity extends AppCompatActivity {

    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.activity_checklist_list) ListView listView;


    private NextPeoListBean nextPeoListBean;
    private List<Map<String, String>> resultList;
    private int level;
    private int isTwo;
    private int maxLevel;
    private String authority;
    private String userflag;
    private String authId;
    private String areaCode = "";
    private String organCode = "";
    private TotalLoginBean loginBean;
    private String userId,organId,deptId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        String titleString = getIntent().getStringExtra("title");
        if (titleString == null) titleString = "后续操作对象";
        title.setText(titleString);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        userId = loginBean.getResult().getUserId();
        organId = loginBean.getResult().getOrganId();
        deptId = loginBean.getResult().getDeptId();
        Intent data = getIntent();
        level = data.getIntExtra("level", 0);
        isTwo = data.getIntExtra("isTwo", 0);
        authority = (level==0)?data.getStringExtra("authority"):NextPeoListBean.authority;
        userflag = (level==0)?data.getStringExtra("userflag"):NextPeoListBean.userflag;
        authId = (level==0)?data.getStringExtra("authId"):NextPeoListBean.authId;
        if("0".equals(authority)){
            maxLevel = 3;
        }else if("1".equals(authority)){
            maxLevel = 2;
        }else{
            maxLevel = 1;
        }
        if(level!=0)    areaCode = NextPeoListBean.areacode;
        if(level>1)     organCode = NextPeoListBean.organcode;

        getResultList();
    }

    @OnItemClick(R.id.activity_checklist_list)
    public void onItemClick(int position){
        if(level == 0){
            NextPeoListBean.areacode = nextPeoListBean.getResult().getAreaVoList().get(position).getAreaCode();
            Intent intent = new Intent(PeopleListActivity.this, PeopleListActivity.class);
            intent.putExtra("level", 1);
            if(isTwo == 0)   intent.putExtra("isTwo", 0);
            else    intent.putExtra("isTwo", 1);
            startActivityForResult(intent, 2);
        }else if(level == 1 && maxLevel == 3){
            NextPeoListBean.organcode = nextPeoListBean.getResult().getOrganVoList().get(position).getOrganId();
            Intent intent = new Intent(PeopleListActivity.this, PeopleListActivity.class);
            intent.putExtra("level", 2);
            if(isTwo == 0)  intent.putExtra("isTwo", 0);
            else    intent.putExtra("isTwo", 1);
            startActivityForResult(intent, 2);

        }else if(level == 1 && maxLevel == 2){
            Intent intent = new Intent(PeopleListActivity.this, CheckBoxListActivity.class);
            intent.putExtra("level", 2);
            if(isTwo == 0)     intent.putExtra("isTwo", 0);
            else   intent.putExtra("isTwo", 1);
            startActivityForResult(intent, 2);

        }else if(level == 2){
            Intent intent = new Intent(PeopleListActivity.this, CheckBoxListActivity.class);
            intent.putExtra("level", 2);
            if(isTwo == 0)     intent.putExtra("isTwo", 0);
            else   intent.putExtra("isTwo", 1);
            intent.putExtra("pId", resultList.get(position).get("id"));
            startActivityForResult(intent, 2);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(2 == requestCode) {
            if(data!=null){
                Intent intent = new Intent();
                intent.putExtra("back", data.getStringExtra("back"));
                setResult(2, intent);
                finish();
            }
        }else if(4 == requestCode){
            Intent intent = new Intent();
            intent.putExtra("back", data.getStringExtra("back"));
            setResult(4, intent);
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getResultList(){
        final List<String> data = new ArrayList<String>();
        Map<String, String> map = new HashMap<String, String>();
        if(level == 0) {
            map.put("wsCodeReq", "07010011");
            map.put("userId", userId);
            map.put("deptId", deptId);
            map.put("authority", authority);
            map.put("userflag", userflag);
            map.put("authId", authId);
            Call<NextPeoListBean> call = ApiManager.oaApi.apiNextPeoList(map);
            call.enqueue(new Callback<NextPeoListBean>() {
                @Override
                public void onResponse(Response<NextPeoListBean> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        NextPeoListBean.authority = authority;
                        NextPeoListBean.userflag = userflag;
                        NextPeoListBean.authId = authId;
                        nextPeoListBean = response.body();
                        if (response.body().getResult() == null) {
                            return;
                        }
                        for (int i = 0; i < response.body().getResult().getAreaVoList().size(); i++) {
                            data.add(response.body().getResult().getAreaVoList().get(i).getAreaName());
                        }
                        listView.setAdapter(new ArrayAdapter<String>(PeopleListActivity.this,
                                android.R.layout.simple_list_item_1, data));
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(PeopleListActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                }
            });
        }else if(level == 1){
            map.put("wsCodeReq", "07010011");
            map.put("userId", userId);
            map.put("deptId", deptId);
            map.put("authority", authority);
            map.put("userflag", userflag);
            map.put("authId", authId);
            map.put("areaCode", areaCode);
            Call<NextPeoListBean> call = ApiManager.oaApi.apiNextPeoList(map);
            call.enqueue(new Callback<NextPeoListBean>() {
                @Override
                public void onResponse(Response<NextPeoListBean> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        nextPeoListBean = response.body();
                        for (int i = 0; i < response.body().getResult().getOrganVoList().size(); i++) {
                            data.add(response.body().getResult().getOrganVoList().get(i).getOrganName());
                        }
                        listView.setAdapter(new ArrayAdapter<String>(PeopleListActivity.this,
                                android.R.layout.simple_list_item_1, data));
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(PeopleListActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                }
            });
        }else if(level == 2){
            map.put("wsCodeReq", "07010011");
            map.put("userId", userId);
            map.put("deptId", deptId);
            map.put("authority", authority);
            map.put("userflag", userflag);
            map.put("authId", authId);
            map.put("areaCode", areaCode);
            map.put("organCode", organCode);
            Call<NextPeoListBean> call = ApiManager.oaApi.apiNextPeoList(map);
            call.enqueue(new Callback<NextPeoListBean>() {
                @Override
                public void onResponse(Response<NextPeoListBean> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        NextPeoListBean.nextPeoListBean = response.body();
                        nextPeoListBean = response.body();
                        resultList = new ArrayList<Map<String, String>>();
                        for (int i = 0; i < response.body().getResult().getResult().size(); i++) {
                            if ("1".equals(response.body().getResult().getResult().get(i).getType())) {
                                Map<String, String> m = new HashMap<String, String>();
                                m.put("name", response.body().getResult().getResult().get(i).getName());
                                m.put("id", response.body().getResult().getResult().get(i).getId());
                                resultList.add(m);
                                data.add(response.body().getResult().getResult().get(i).getName());
                            }
                        }
                        listView.setAdapter(new ArrayAdapter<String>(PeopleListActivity.this,
                                android.R.layout.simple_list_item_1, data));
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(PeopleListActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
