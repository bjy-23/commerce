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

    private ArrayList<Dept.OrganInfo> organList = new ArrayList<Dept.OrganInfo>();
    private List<Integer> stateList = new ArrayList<>(4);
    private Dept.OrganInfo selectOrganInfo;

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
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString(Constants.TITLE, "");
        oneAdapter = new FirstAdapter();
        confirmBtn = (Button) findViewById(R.id.btn_confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectOrganInfo != null) {
                    Toast.makeText(DeptActivity.this, selectOrganInfo.getOrganName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("organName", selectOrganInfo.getOrganName());
                    intent.putExtra("organId", selectOrganInfo.getOrganId());
                    intent.putExtra("gbCode", selectOrganInfo.getGbCode());
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    DeptActivity.this.finish();
                }
            }
        });

        backBtn = (LinearLayout) findViewById(R.id.ll_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOrganInfo = null;
                selectItem = -1;
                if (currentState == 0) {
                    finish();
                } else {
                    ArrayList<Dept.OrganInfo> tmpOrganList = organList;
                    if (stateList.size() > 0)
                        stateList.remove(stateList.size() - 1);//移除最后一个
                    for (Integer index : stateList) {
                        tmpOrganList = tmpOrganList.get(index).getOrganList();
                    }
                    oneAdapter.setData(tmpOrganList);
                    oneAdapter.notifyDataSetChanged();
                    currentState--;
                }
            }
        });
        titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText(title);

        oneLv = (ListView) findViewById(R.id.lv_first);
        oneLv.setAdapter(oneAdapter);
        oneLv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectOrganInfo = null;
                        selectItem = -1;
                        ArrayList<Dept.OrganInfo> tmpOrganList = organList;
                        int oldSize = stateList.size();

                        if (stateList.size() == (currentState + 1))
                            stateList.set(currentState, position);
                        else stateList.add(position);
                        for (Integer index : stateList) {
                            tmpOrganList = tmpOrganList.get(index).getOrganList();
                        }
                        if (tmpOrganList == null || tmpOrganList.size() == 0) {
                            //若点击的节点没有下一级,则移除该位置
                            if (oldSize != (currentState + 1) && stateList.size() > 0) {
                                stateList.remove(stateList.size() - 1);
                            }
                            Toast.makeText(DeptActivity.this, "没有下一级", Toast.LENGTH_SHORT).show();
                        } else {
                            currentState++;
                            if (!isContain(tmpOrganList, oneAdapter.getItem(position).getOrganId()))
                                tmpOrganList.add(0, createOrganInfo(oneAdapter.getItem(position)));
                            oneAdapter.setData(tmpOrganList);
                            oneAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }

    private void getAllDept() {
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(DeptActivity.this)
                .build();

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
                loadingDialog.dismiss();
                if (response.isSuccess()) {
                    Dept dept = response.body();
                    if (dept.getResult() != null) {
                        assambleDept(dept.getResult().getOrganInfo());
                    } else {
                        Toast.makeText(context, "服务器数据出错", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
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
            return dataList == null ? 0 : dataList.size();
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
                    selectOrganInfo = getItem(position);
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
     * 是否包含对象
     *
     * @param res
     * @param organId
     * @return
     */
    private boolean isContain(List<Dept.OrganInfo> res, String organId) {
        for (Dept.OrganInfo info : res) {
            if (info.getOrganId().equals(organId)) {
                return true;
            }
        }
        return false;
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
        for (int i = 0; i < src.size(); i++) {
            Dept.OrganInfo info = src.get(i);
            if (info.getParentOrganId() != null
                    && info.getOrganId() != null
                    && info.getValid().equals("1")
                    && info.getParentOrganId().equals(organId)
                    && res.add(info)) {
                src.remove(i);
                i--;
            }
        }
        sortByOrganId(res);
    }

    /**
     * 根据organId排序
     *
     * @param res
     */
    private void sortByOrganId(List<Dept.OrganInfo> res) {
        Collections.sort(res, new Comparator<Dept.OrganInfo>() {
            @Override
            public int compare(Dept.OrganInfo o1, Dept.OrganInfo o2) {
                return o1.getOrganId().compareTo(o2.getOrganId());
            }
        });
    }

    /**
     * 递归封装数据
     *
     * @param rank
     * @param empty
     * @param list
     */
    private void recursion(int rank, List<Dept.OrganInfo> empty, List<Dept.OrganInfo> list) {
        if (empty.size() != 0) {
            rank++;
            for (Dept.OrganInfo info : empty) {
                ArrayList<Dept.OrganInfo> temp = new ArrayList<>();
                getChildDept(temp, list, info.getOrganId());
                info.setOrganList(temp);
                recursion(rank, temp, list);
            }

//            Log.e("recursion", "rank:" + rank + "organId:" + organId);
        }
    }

    private void assambleDept(List<Dept.OrganInfo> list) {
        ArrayList<Dept.OrganInfo> listOne = new ArrayList<>();
        getChildDept(listOne, list, loginBean.getResult().getOrganId());//第一层
        int rank = 1;
        recursion(rank, listOne, list);//获取数据
        listOne.add(0, createOrganInfo(loginBean.getResult().getOrganId(), loginBean.getResult().getOrganName()));
        organList = listOne;
        oneAdapter.setData(organList);
        oneAdapter.notifyDataSetChanged();
    }

    /**
     * 创建新organInfo
     *
     * @param src
     * @return
     */
    private Dept.OrganInfo createOrganInfo(Dept.OrganInfo src) {
        Dept.OrganInfo organInfo = new Dept().new OrganInfo();
        organInfo.setOrganName(src.getOrganName());
        organInfo.setOrganId(src.getOrganId());
        return organInfo;
    }

    /**
     * 创建新organInfo
     *
     * @param organId
     * @param organName
     * @return
     */
    private Dept.OrganInfo createOrganInfo(String organId, String organName) {
        Dept.OrganInfo organInfo = new Dept().new OrganInfo();
        organInfo.setOrganName(organName);
        organInfo.setOrganId(organId);
        return organInfo;
    }
}
