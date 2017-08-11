package com.wondersgroup.commerce.teamwork.addressbox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RootActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Dept;
import com.wondersgroup.commerce.model.DeptOne;
import com.wondersgroup.commerce.model.DeptTwo;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by kangrenhui on 2015/12/8.
 */
public class TxlDeptActivity extends RootActivity {
    private LinearLayout backBtn;
    private TextView titleTv;
    private ListView oneLv;
    private Button confirmBtn;

    private Context context;

    private FirstAdapter oneAdapter;
    private int selectItem = -1;
    private String deptId;

    private ArrayList<Dept.OrganInfo> organList = new ArrayList<Dept.OrganInfo>();

    private int one = -1;
    private int two = -1;


    private int currentState = 0;
    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        loginBean = Hawk.get(Constants.LOGIN_BEAN);

        setContentView(R.layout.activity_txl_dept);

        getAllDept();

        findView();
    }

    private void findView() {
        oneAdapter = new FirstAdapter();

        confirmBtn = (Button) findViewById(R.id.btn_confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectItem == -1) {
                    TxlDeptActivity.this.finish();

                    return;
                }

                Intent intent;
                switch (currentState) {
                    case 0:
                        Toast.makeText(TxlDeptActivity.this, organList.get(selectItem).getOrganName(), Toast.LENGTH_SHORT).show();

                        intent = new Intent();
                        intent.putExtra("organName", organList.get(selectItem).getOrganName());
                        intent.putExtra("organId", organList.get(selectItem).getOrganId());

                        setResult(RESULT_OK, intent);

                        finish();

                        break;

                    case 1:
                        Toast.makeText(TxlDeptActivity.this, organList.get(one).getOrganList().get(selectItem).getOrganName(), Toast.LENGTH_SHORT).show();

                        intent = new Intent();
                        intent.putExtra("organName", organList.get(one).getOrganList().get(selectItem).getOrganName());
                        intent.putExtra("organId", organList.get(one).getOrganList().get(selectItem).getOrganId());

                        setResult(RESULT_OK, intent);

                        finish();

                        break;

                    case 2:
                        Toast.makeText(TxlDeptActivity.this, organList.get(one).getOrganList().get(two).getOrganList().get(selectItem).getOrganName(), Toast.LENGTH_SHORT).show();

                        intent = new Intent();
                        intent.putExtra("organName", organList.get(one).getOrganList().get(two).getOrganList().get(selectItem).getOrganName());
                        intent.putExtra("organId", organList.get(one).getOrganList().get(two).getOrganList().get(selectItem).getOrganId());

                        setResult(RESULT_OK, intent);

                        finish();


                        break;
                }

            }
        });

        backBtn = (LinearLayout) findViewById(R.id.ll_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Dept.OrganInfo> temp;
                selectItem = -1;


                switch (currentState) {
                    case 0:
                        finish();
                        break;

                    case 1:
                        titleTv.setText("所有联系人");

                        temp = organList;

                        oneAdapter.setData(temp);

                        oneAdapter.notifyDataSetChanged();

                        currentState--;
                        break;

                    case 2:
                        titleTv.setText(organList.get(one).getOrganName());

                        temp = organList.get(one).getOrganList();

                        oneAdapter.setData(temp);

                        oneAdapter.notifyDataSetChanged();

                        currentState--;

                        break;

                }
            }
        });
        titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText("单位选择");
        titleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Dept.OrganInfo> temp;
                selectItem = -1;


                switch (currentState) {
                    case 0:
                        finish();
                        break;

                    case 1:
                        titleTv.setText("所有联系人");

                        temp = organList;


                        oneAdapter.setData(temp);

                        oneAdapter.notifyDataSetChanged();

                        currentState--;
                        break;

                    case 2:
                        titleTv.setText(organList.get(one).getOrganName());

                        temp = organList.get(one).getOrganList();

                        oneAdapter.setData(temp);

                        oneAdapter.notifyDataSetChanged();

                        currentState--;

                        break;

                }

            }
        });

        oneLv = (ListView) findViewById(R.id.lv_first);
        oneLv.setAdapter(oneAdapter);
        oneLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                         @Override
                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                             selectItem = -1;

//                selectItem = position;
//                deptId = lvDataOne.get(position).getDeptId();
//                oneAdapter.notifyDataSetChanged();
//
//                getSecondData(lvDataOne.get(position).getOrganId(), lvDataOne.get(position).getDeptId());

                                             if (currentState == 0) {
                                                 if (organList.get(position).getOrganList() == null || organList.get(position).getOrganList().size() == 0) {
                                                     Toast.makeText(TxlDeptActivity.this, "没有下一级", Toast.LENGTH_SHORT).show();
                                                     return;
                                                 }

                                             } else if (currentState == 1) {
                                                 if (organList.get(one).getOrganList().get(position).getOrganList() == null || organList.get(one).getOrganList().get(position).getOrganList().size() == 0) {
                                                     Toast.makeText(TxlDeptActivity.this, "没有下一级", Toast.LENGTH_SHORT).show();
                                                     return;
                                                 }

                                             }

                                             ArrayList<Dept.OrganInfo> temp;


                                             switch (currentState) {
                                                 case 0:
                                                     one = position;

                                                     temp = organList.get(one).getOrganList();

                                                     titleTv.setText(organList.get(one).getOrganName());

                                                     oneAdapter.setData(temp);

                                                     oneAdapter.notifyDataSetChanged();

                                                     currentState++;
                                                     break;
                                                 case 1:
                                                     titleTv.setText("所有联系人");

                                                     two = position;

                                                     temp = organList.get(one).getOrganList().get(two).getOrganList();

                                                     titleTv.setText(organList.get(one).getOrganList().get(two).getOrganName());


                                                     oneAdapter.setData(temp);

                                                     oneAdapter.notifyDataSetChanged();

                                                     currentState++;
                                                     break;
                                                 case 2:
                                                     Toast.makeText(TxlDeptActivity.this, "没有下属分局", Toast.LENGTH_SHORT).show();
                                                     break;
                                             }


                                         }
                                     }


        );


    }

    private void getSecondData(String organId, String deptId) {
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(mContext);

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000001");
        map.put("loginName", "xtywz");
        map.put("password", "xdrcft56");
        map.put("userId", "a0a0e39441ad45050141b068ab9803a8");
        map.put("version", "1.0.2");
        map.put("organId", organId);
        map.put("deptId", deptId);

        Call<DeptTwo> call = ApiManager.hbApi.deptOrganId(map);
        call.enqueue(new Callback<DeptTwo>() {
            @Override
            public void onResponse(Response<DeptTwo> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Toast.makeText(mContext, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFirstData() {
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(mContext);


        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000001");
        map.put("loginName", "xtywz");
        map.put("password", "xdrcft56");
        map.put("userId", "a0a0e39441ad45050141b068ab9803a8");
        map.put("version", "1.0.2");
        map.put("organId", "130000000");

        Call<DeptOne> call = ApiManager.hbApi.deptOrganDept(map);
        call.enqueue(new Callback<DeptOne>() {
            @Override
            public void onResponse(Response<DeptOne> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    oneAdapter.notifyDataSetChanged();

                    dialog.dismiss();
                } else {
                    getResources().getString(R.string.error_data);

                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Toast.makeText(mContext, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllDept() {
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(mContext);

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000001");
        map.put("loginName", Hawk.get(Constants.LOGIN_NAME).toString());
        map.put("password", Hawk.get(Constants.PASSWORD).toString());
        map.put("userId", loginBean.getResult().getUserId());
        map.put("version", "1.0.2");
        map.put("organId", loginBean.getResult().getOrganId());
        map.put("deptId", loginBean.getResult().getDeptId());

        Call<Dept> call = ApiManager.hbApi.deptAll(map);
        call.enqueue(new Callback<Dept>() {
            @Override
            public void onResponse(Response<Dept> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    Dept dept = response.body();

                    if (dept.getResult() != null) {
                        assambleDept(dept.getResult().getOrganInfo());

                    } else {
                        Toast.makeText(context, "服务器数据出错", Toast.LENGTH_SHORT).show();
                    }

                    dialog.dismiss();
                } else {
                    getResources().getString(R.string.error_data);

                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Toast.makeText(mContext, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    class FirstAdapter extends BaseAdapter {
        private ArrayList<Dept.OrganInfo> dataList;

        public void setData(ArrayList<Dept.OrganInfo> setData) {
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

            tv.setText(dataList.get(position).getOrganName());

            if (dataList.get(position).getOrganList() == null || dataList.get(position).getOrganList().size() == 0) {
                more.setVisibility(View.GONE);
            } else {
                more.setVisibility(View.VISIBLE);
            }

            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectItem = position;
                    oneAdapter.notifyDataSetChanged();
                }
            });

            if (selectItem != -1 && position == selectItem) {
                select.setImageResource(R.mipmap.dx_selected);
                tv.setTextColor(Color.parseColor("#2196F3"));
            } else {
                select.setImageResource(R.mipmap.dx_unselected);
                tv.setTextColor(Color.parseColor("#999999"));
            }

            return convertView;
        }

    }


    private void assambleDept(List<Dept.OrganInfo> list) {
        ArrayList<Dept.OrganInfo> listOne = new ArrayList<Dept.OrganInfo>();
        ArrayList<Dept.OrganInfo> listTwo = new ArrayList<Dept.OrganInfo>();
        ArrayList<Dept.OrganInfo> listThree = new ArrayList<Dept.OrganInfo>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRank().equals("2")) {
                listOne.add(list.get(i));
            } else if (list.get(i).getRank().equals("3")) {
                listTwo.add(list.get(i));
            } else if (list.get(i).getRank().equals("4")) {
                listThree.add(list.get(i));
            }
        }

        for (int i = 0; i < listThree.size(); i++) {
            for (int j = 0; j < listTwo.size(); j++) {
                if (listThree.get(i).getParentOrganId()!=null && listTwo.get(j).getOrganId()!=null){
                    if (listThree.get(i).getParentOrganId().equals(listTwo.get(j).getOrganId())) {
                        if (listTwo.get(j).getOrganList() == null) {
                            listTwo.get(j).setOrganList(new ArrayList<Dept.OrganInfo>());
                        }
                        listTwo.get(j).getOrganList().add(listThree.get(i));
                    }
                }
            }
        }

        for (int i = 0; i < listTwo.size(); i++) {
            for (int j = 0; j < listOne.size(); j++) {
                if (listTwo.get(i).getParentOrganId()!=null && listOne.get(j).getOrganId()!=null){
                    if (listTwo.get(i).getParentOrganId().equals(listOne.get(j).getOrganId())) {
                        if (listOne.get(j).getOrganList() == null) {
                            listOne.get(j).setOrganList(new ArrayList<Dept.OrganInfo>());
                        }
                        listOne.get(j).getOrganList().add(listTwo.get(i));
                    }
                }
            }
        }


        organList = listOne;

        oneAdapter.setData(organList);
        oneAdapter.notifyDataSetChanged();

    }

}
