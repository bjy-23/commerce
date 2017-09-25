package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.LoadingDialog;

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


/**
 * Created by chan on 2017/07/25.
 * 区局选择
 */
public class DeptActivity extends RootActivity {
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
    private TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
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
                    DeptActivity.this.finish();

                    return;
                }

                Intent intent;
                switch (currentState) {
                    case 0:
                        Toast.makeText(DeptActivity.this, organList.get(selectItem).getOrganName(), Toast.LENGTH_SHORT).show();

                        intent = new Intent();
                        intent.putExtra("organName", organList.get(selectItem).getOrganName());
                        intent.putExtra("organId", organList.get(selectItem).getOrganId());

                        setResult(RESULT_OK, intent);

                        finish();

                        break;

                    case 1:
                        Toast.makeText(DeptActivity.this, organList.get(one).getOrganList().get(selectItem).getOrganName(), Toast.LENGTH_SHORT).show();

                        intent = new Intent();
                        intent.putExtra("organName", organList.get(one).getOrganList().get(selectItem).getOrganName());
                        intent.putExtra("organId", organList.get(one).getOrganList().get(selectItem).getOrganId());
                        setResult(RESULT_OK, intent);
                        finish();
                        break;

                    case 2:
                        Toast.makeText(DeptActivity.this, organList.get(one).getOrganList().get(two).getOrganList().get(selectItem).getOrganName(), Toast.LENGTH_SHORT).show();
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
                        titleTv.setText(loginBean.getResult().getOrganName());

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
        titleTv.setText(loginBean.getResult().getOrganName());
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
                        titleTv.setText(loginBean.getResult().getOrganName());

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
        oneLv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectItem = -1;
                        if (currentState == 0) {
                            if (organList.get(position).getOrganList() == null || organList.get(position).getOrganList().size() == 0) {
                                Toast.makeText(DeptActivity.this, "没有下一级", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } else if (currentState == 1) {
                            if (organList.get(one).getOrganList().get(position).getOrganList() == null || organList.get(one).getOrganList().get(position).getOrganList().size() == 0) {
                                Toast.makeText(DeptActivity.this, "没有下一级", Toast.LENGTH_SHORT).show();
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
                                titleTv.setText(loginBean.getResult().getOrganName());
                                two = position;
                                temp = organList.get(one).getOrganList().get(two).getOrganList();
                                titleTv.setText(organList.get(one).getOrganList().get(two).getOrganName());
                                oneAdapter.setData(temp);
                                oneAdapter.notifyDataSetChanged();
                                currentState++;
                                break;
                            case 2:
                                Toast.makeText(DeptActivity.this, "没有下属分局", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }
        );
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
                        assambleDept(dept.getResult().getOrganInfo(), loginBean.getResult().getOrganId());
                    } else {
                        Toast.makeText(context, "服务器数据出错", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
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
        public Dept.OrganInfo getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
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

    /**
     * 获取子区县
     *
     * @param res
     * @param src
     * @param organId
     * @return
     */
    private void getChildDept(List<Dept.OrganInfo> res, List<Dept.OrganInfo> src, String organId) {
        for (Dept.OrganInfo info : src) {
            if (info.getParentOrganId() != null
                    && info.getOrganId() != null
                    && info.getValid().equals("1")
                    && info.getParentOrganId().equals(organId)) {
                res.add(info);
            }
        }
        sortByOrganId(res);
    }

    private void sortByOrganId(List<Dept.OrganInfo> res) {
        Collections.sort(res, new Comparator<Dept.OrganInfo>() {
            @Override
            public int compare(Dept.OrganInfo o1, Dept.OrganInfo o2) {
                return o1.getOrganId().compareTo(o2.getOrganId());
            }
        });
    }

//    private void m(List<Dept.OrganInfo> list, String organId) {
//        int rank = 2;
//        rank:
//        for (Dept.OrganInfo info : list) {
//            if (info.getOrganId().equals(organId)) {
//                rank = Integer.valueOf(info.getRank());
//                break rank;
//            }
//        }
//        assambleDept(rank, organList, list, organId);
//        oneAdapter.setData(organList);
//        oneAdapter.notifyDataSetChanged();
//    }
//
//    private void assambleDept(int rank, ArrayList<Dept.OrganInfo> res, List<Dept.OrganInfo> src, String organId) {
//        if (rank <= 4) {
//            for (Dept.OrganInfo info : src) {
//                getChildDept(res, src, info.getOrganId());
//                info.setOrganList(res);
//            }
//            ArrayList<Dept.OrganInfo> list = new ArrayList<>();
//            assambleDept(rank + 1, list, res, organId);
//        }
//    }

    private void assambleDept(List<Dept.OrganInfo> list, String organId) {
        ArrayList<Dept.OrganInfo> listOne = new ArrayList<>();
//        Dept.OrganInfo organInfo = new Dept().new OrganInfo();
//        organInfo.setOrganName(loginBean.getResult().getOrganName());
//        organInfo.setOrganId(loginBean.getResult().getOrganId());
//        listOne.add(organInfo);
        getChildDept(listOne, list, organId);//第一层
        for (Dept.OrganInfo info : listOne) {
            ArrayList<Dept.OrganInfo> temp = new ArrayList<>();
            getChildDept(temp, list, info.getOrganId());
            info.setOrganList(temp);
            for (Dept.OrganInfo second : temp) {
                ArrayList<Dept.OrganInfo> inner = new ArrayList<>();
                getChildDept(inner, list, second.getOrganId());
                second.setOrganList(inner);
            }
        }
        Dept.OrganInfo organInfo = new Dept().new OrganInfo();
        organInfo.setOrganName(loginBean.getResult().getOrganName());
        organInfo.setOrganId(loginBean.getResult().getOrganId());
        listOne.add(0, organInfo);
        organList = listOne;
        oneAdapter.setData(organList);
        oneAdapter.notifyDataSetChanged();
    }
}
