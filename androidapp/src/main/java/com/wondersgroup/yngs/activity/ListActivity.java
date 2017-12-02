package com.wondersgroup.yngs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.entity.DicItem;
import com.wondersgroup.yngs.entity.DicResult;
import com.wondersgroup.yngs.entity.DicT;
import com.wondersgroup.yngs.service.ApiManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ListActivity extends AppCompatActivity {
    @Bind(R.id.list_list)ListView list;
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    private ArrayList<String> items=new ArrayList<>();
    private List<DicItem> dicItems=new ArrayList<>();
    private String type;
    private String errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String[] sqTypes=getResources().getStringArray(R.array.shenqingTypes);
        String[] sqStatus=getResources().getStringArray(R.array.shenqingStatus);
        title.setText(getIntent().getStringExtra("title"));
        type=getIntent().getStringExtra("type");
        if("SQLX".equals(type)){
            errorMsg ="获取申请类型失败";
        }else if("SQZT".equals(type)){
            errorMsg ="获取申请状态失败";
        }else if("CDXZ".equals(type)){
            errorMsg="获取场地性质失败";
        }
        DicT dic= Hawk.get("Dic");
        if(dic==null){
            SharedPreferences sp=getSharedPreferences("Default",MODE_PRIVATE);
            Map<String,String> body =new HashMap<>();
            int selection=sp.getInt("selection", 0);
            body.put("deptId", sp.getString("deptId", "").split(",")[selection]);
            body.put("organId", sp.getString("organId", "").split(",")[selection]);
            body.put("wsCodeReq",ApiManager.getWsCodeReq());
            Call<DicResult> call = ApiManager.yunNanApi.getDic(body);
            call.enqueue(new Callback<DicResult>() {
                @Override
                public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        DicResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            Hawk.put("Dic", result.getResult());
                            setTypes(result.getResult());
                        } else {
                            Toast.makeText(ListActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(ListActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(ListActivity.this,errorMsg,Toast.LENGTH_LONG).show();
                }
            });
        }else {
            setTypes(dic);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.view_list_item,items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent();
                intent.putExtra("typeCode", dicItems.get(position).getName());
                if("其它".equals(items.get(position))){
                    new MaterialDialog.Builder(ListActivity.this)
                            .title("请输入类型名")
                            .inputType(InputType.TYPE_CLASS_TEXT)
                            .input("输入类型名", "", new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                    intent.putExtra(type,input.toString());
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            })
                            .cancelable(false)
                            .show();
                }else {
                    intent.putExtra(type, items.get(position));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void setTypes(DicT dic){
        if("SQLX".equals(type)){
            dicItems.addAll(dic.getDicAppType());
        }else if("SQZT".equals(type)){
            dicItems.addAll(dic.getFlowStatus());
        }else if("CDXZ".equals(type)){
            dicItems.addAll(dic.getRunSpaceType());
        }
        for (DicItem i :
                dicItems) {
            items.add(i.getValue());
        }
    }
}
