package com.wondersgroup.commerce.teamwork.trademarkInquiry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RootActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Address;
import com.wondersgroup.commerce.model.DominationModel;
import com.wondersgroup.commerce.model.KeyValue;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.addressbox.CharacterParser;
import com.wondersgroup.commerce.teamwork.addressbox.IndexView;
import com.wondersgroup.commerce.teamwork.addressbox.PinyinComparator;
import com.wondersgroup.commerce.teamwork.addressbox.SearchActivity;
import com.wondersgroup.commerce.teamwork.addressbox.TxlDeptActivity;
import com.wondersgroup.commerce.teamwork.addressbox.TxlDetailActivity;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

//管辖机关选择列表页面
public class DominationSelectActivity extends RootActivity implements View.OnClickListener {
    private Context context;

    // 控件
    private LinearLayout backBtn;

    private TextView tvTitle;
    private ListView lv;

    private LinearLayout layoutFind;
    private TotalLoginBean loginBean;

    private ChoiceListAdapter dataAdapter;
    private int selectItem = -1;
    private ArrayList<DominationModel> lvData = new ArrayList<DominationModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domination_select);
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        context = this;

        findView();
//        getDataWithNet("1", "");
        initData();

    }

    private void initData() {
        lvData = new ArrayList<DominationModel>();
        DominationModel data1 = new DominationModel();
        data1.setId("1");
        data1.setValue("四川工商行政管理局");
        data1.setEndValue(false);
        lvData.add(data1);
        DominationModel data2 = new DominationModel();
        data2.setId("2");
        data2.setValue("云南工商行政管理局");
        data2.setEndValue(false);
        lvData.add(data2);
        DominationModel data3 = new DominationModel();
        data3.setId("3");
        data3.setValue("河北工商行政管理局");
        data3.setEndValue(false);
        lvData.add(data3);
        dataAdapter.setData(lvData);
        dataAdapter.notifyDataSetChanged();

    }

    private void findView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("机关选择");

        layoutFind = (LinearLayout) findViewById(R.id.layout_find);
        layoutFind.setOnClickListener(this);

        backBtn = (LinearLayout) findViewById(R.id.ll_back);
        backBtn.setOnClickListener(this);

        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, DominationDeptActivity.class);
                intent.putExtra("levelNo", "2");
                startActivityForResult(intent, 2);
            }
        });
        dataAdapter = new ChoiceListAdapter();
        lv.setAdapter(dataAdapter);
    }

    protected void onActivityResult(int RequestCode, int ResultCode, Intent data){
        super.onActivityResult(RequestCode, ResultCode, data);
        if(RequestCode == 2 && ResultCode == 2){
            setResult(1, data);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.layout_find:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra("data", (Serializable) lvData);
                startActivity(intent);
                break;
            case R.id.ll_back:
                finish();
                break;
        }
    }


    private void createDeptDialog() {

        Intent intent = new Intent(context, DominationDeptActivity.class);
        startActivityForResult(intent, 0);

    }

    private void getDataWithNet(String orginId, String name) {
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(mContext);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010003");
        map.put("pageNo", "1");
        map.put("pageSize", "100");
        map.put("contactOrganId", "1");
        map.put("contactUserName", "");
        map.put("userId", loginBean.getResult().getUserId());
        map.put("deptId", loginBean.getResult().getDeptId());
        map.put("organId", loginBean.getResult().getOrganId());


        Call<Address> call = ApiManager.oaApi.address(map);
//        call.enqueue(new Callback<Address>() {
//            @Override
//            public void onResponse(Response<Address> response, Retrofit retrofit) {
//
//                if (response.isSuccess()) {
//                    Address address = response.body();
//
//
//                    if (address == null) {
//                        dialog.dismiss();
//                        Toast.makeText(DominationSelectActivity.this, "没有返回数据", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (address.getResult().getAddlistPersonalInfo() == null) {
//                        dialog.dismiss();
//                        lvData.clear();
//                        Toast.makeText(context, "没有找到相关人员", Toast.LENGTH_SHORT).show();
//
//                        adapter.notifyDataSetChanged();
//
//                        return;
//                    }
//
//                    lvData = address.getResult().getAddlistPersonalInfo();
//
//                    adapter.notifyDataSetChanged();
//
//                    dialog.dismiss();
//                } else {
//                    getResources().getString(R.string.error_data);
//
//                    dialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                dialog.dismiss();
//                Toast.makeText(mContext, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    class ChoiceListAdapter extends BaseAdapter {
        private ArrayList<DominationModel> dataList;

        public void setData(ArrayList<DominationModel> setData) {
            dataList = setData;
        }

        @Override
        public int getCount() {
            if (dataList == null) {
                return 0;
            }

            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_dept_list, null);
            }

            TextView tv = (TextView) convertView.findViewById(R.id.dept_one);
            ImageView select = (ImageView) convertView.findViewById(R.id.iv_select);
            ImageView more = (ImageView) convertView.findViewById(R.id.iv_more);

            tv.setText(dataList.get(position).getValue());

            if(dataList.get(position).isEndValue()){
                more.setVisibility(View.GONE);
            }else{
                more.setVisibility(View.VISIBLE);
            }

            select.setVisibility(View.GONE);

            return convertView;
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
