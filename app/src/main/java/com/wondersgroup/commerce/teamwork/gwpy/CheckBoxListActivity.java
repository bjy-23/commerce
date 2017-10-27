package com.wondersgroup.commerce.teamwork.gwpy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by yclli on 2015/12/11.
 */
public class CheckBoxListActivity extends AppCompatActivity {
    @BindView(R.id.mid_toolbar)Toolbar toolbar;
    @BindView(R.id.toolbar_title)TextView title;
    @BindView(R.id.list_checkbox_select_all) Button allBtn;
    @BindView(R.id.list_checkbox_confirm) Button confirmBtn;
    @BindView(R.id.activity_peoplelist_list) ListView listView;

    private CheckBoxListAdapter adapter;
    private NextPeoListBean nextPeoListBean;
    private List<String> resultList = new ArrayList<String>();
    private List<Boolean> checkStates = new ArrayList<Boolean>();
    private List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();
    private List<Map<String, String>> chooseList = new ArrayList<Map<String, String>>();
    private int flag = 0;

    private int maxLevel;
    private int isTwo;
    private String authority;
    private String userflag;
    private String authId;
    private String areaCode = "";
    private String organCode = "";
    private String pId;
    private TotalLoginBean loginBean;
    private String userId,deptId,organId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkboxlist);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        String titleString = getIntent().getStringExtra("title");
        if (titleString == null) titleString = "后续操作对象";
        title.setText(titleString);

        Intent data = getIntent();
        isTwo = data.getIntExtra("isTwo", 0);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        userId = loginBean.getResult().getUserId();
        deptId = loginBean.getResult().getDeptId();
        organId = loginBean.getResult().getOrganId();
        authority = NextPeoListBean.authority;
        userflag = NextPeoListBean.userflag;
        authId = NextPeoListBean.authId;
        areaCode = NextPeoListBean.areacode;
        organCode = NextPeoListBean.organcode;

        if("0".equals(authority)){
            maxLevel = 3;
        }else if("1".equals(authority)){
            maxLevel = 2;
        }else{
            maxLevel = 1;
        }
        if(maxLevel == 2){
            Map<String, String> map = new HashMap<String, String>();
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
                        nextPeoListBean = response.body();
                        NextPeoListBean.nextPeoListBean = response.body();
                        if (response.body().getResult() != null) {
                            for (int i = 0; i < response.body().getResult().getResult().size(); i++) {
                                if ("1".equals(nextPeoListBean.getResult().getResult().get(i).getType())) {
                                    Map<String, String> m = new HashMap<String, String>();
                                    m.put("name", response.body().getResult().getResult().get(i).getName());
                                    m.put("id", response.body().getResult().getResult().get(i).getId());
                                    itemList.add(m);
                                    checkStates.add(false);
                                    resultList.add(response.body().getResult().getResult().get(i).getName());
                                }
                            }
                            adapter = new CheckBoxListAdapter(CheckBoxListActivity.this, resultList, checkStates);
                            listView.setAdapter(adapter);
                            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(CheckBoxListActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            nextPeoListBean = NextPeoListBean.nextPeoListBean;
            Intent intent = getIntent();
            pId = intent.getStringExtra("pId");
            if(nextPeoListBean.getResult()!=null){
                for(int i=0; i<nextPeoListBean.getResult().getResult().size(); i++){
                    if("0".equals(nextPeoListBean.getResult().getResult().get(i).getType()) &&
                            pId.equals(nextPeoListBean.getResult().getResult().get(i).getPId())){
                        Map<String, String> m = new HashMap<String, String>();
                        m.put("name" , nextPeoListBean.getResult().getResult().get(i).getName());
                        m.put("id", nextPeoListBean.getResult().getResult().get(i).getId());
                        itemList.add(m);
                        checkStates.add(false);
                        resultList.add(nextPeoListBean.getResult().getResult().get(i).getName());
                    }
                }
                adapter = new CheckBoxListAdapter(CheckBoxListActivity.this, resultList, checkStates);
                listView.setAdapter(adapter);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBoxListAdapter.ViewHolder holder=(CheckBoxListAdapter.ViewHolder)view.getTag();
                holder.checkbox.toggle();
                checkStates.set(position,!checkStates.get(position));
            }
        });
    }

    @OnClick({R.id.list_checkbox_select_all, R.id.list_checkbox_confirm})
    public void onClick(View v) {
        if (allBtn == v) {
            if(flag == 0){
                for (int i = 0; i < checkStates.size(); i++) {
                    checkStates.set(i, true);
                    adapter.notifyDataSetChanged();
                }
                allBtn.setText("反选");
                flag = 1;
            }else {
                for (int i = 0; i < checkStates.size(); i++) {
                    checkStates.set(i, false);
                    adapter.notifyDataSetChanged();
                }
                allBtn.setText("全选");
                flag = 0;
            }

        }else if(confirmBtn == v){
            NextPeoListBean.areacode = "";
            NextPeoListBean.organcode = "";
            NextPeoListBean.nextPeoListBean = null;
            for(int i=0; i<checkStates.size(); i++){
                if(checkStates.get(i).booleanValue()){
                    chooseList.add(itemList.get(i));
                }
            }
            if(isTwo == 0) {
                NextPeoListBean.chooseList = chooseList;
                Intent intent = new Intent();
                intent.putExtra("back", 1);
                setResult(2, intent);
                finish();
            }else {
                NextPeoListBean.chooseList2 = chooseList;
                Intent intent = new Intent();
                intent.putExtra("back", 1);
                setResult(4, intent);
                finish();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public class CheckBoxListAdapter extends BaseAdapter {

        List<String> items;
        private Context context;
        private List<Boolean> checkStates;
        public CheckBoxListAdapter(Context context, List<String> items, List<Boolean> checkStates) {
            this.context=context;
            this.items = items;
            this.checkStates=checkStates;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public String getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.list_checkbox_item,null);
                holder=new ViewHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder=(ViewHolder)convertView.getTag();
            }
        /*holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkStates.set(position,isChecked);
            }
        });*/
            holder.checkbox.setChecked(checkStates.get(position).booleanValue());
            holder.name.setText(items.get(position));
            return convertView;
        }

        public class ViewHolder{
            public @BindView(R.id.list_checkbox_check)CheckBox checkbox;
            @BindView(R.id.list_checkbox_name)TextView name;

            public ViewHolder(View view) {
                ButterKnife.bind(this,view);
            }
        }
    }
}
