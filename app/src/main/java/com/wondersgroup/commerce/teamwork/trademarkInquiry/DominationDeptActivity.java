package com.wondersgroup.commerce.teamwork.trademarkInquiry;

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

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RootActivity;
import com.wondersgroup.commerce.model.Dept;
import com.wondersgroup.commerce.model.DeptOne;
import com.wondersgroup.commerce.model.DeptTwo;
import com.wondersgroup.commerce.model.DominationModel;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by kangrenhui on 2015/12/8.
 */
public class DominationDeptActivity extends RootActivity {
    private LinearLayout backBtn;
    private TextView titleTv;
    private ListView oneLv;

    private Context context;

    private ChoiceListAdapter oneAdapter;
    private int selectItem = -1;
    private ArrayList<DominationModel> organList = new ArrayList<DominationModel>();
    private int levelNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        setContentView(R.layout.activity_domination_dept);
//        getAllDept();
        findView();
        initData();
    }

    private void initData() {
        switch (levelNo){
            case 2:
            case 3:
                DominationModel data1 = new DominationModel();
                data1.setId("1");
                data1.setValue("成都市工商行政管理局"+"--层"+levelNo);
                data1.setEndValue(false);
                organList.add(data1);
                DominationModel data2 = new DominationModel();
                data2.setId("2");
                data2.setValue("攀枝花市工商行政管理局"+"--层"+levelNo);
                data2.setEndValue(false);
                organList.add(data2);
                DominationModel data3 = new DominationModel();
                data3.setId("3");
                data3.setValue("阳德市工商行政管理局"+"--层"+levelNo);
                data3.setEndValue(false);
                organList.add(data3);
                break;
            case 4:
                DominationModel data4 = new DominationModel();
                data4.setId("1");
                data4.setValue("成都市工商行政管理局"+"--层"+levelNo);
                data4.setEndValue(true);
                organList.add(data4);
                DominationModel data5 = new DominationModel();
                data5.setId("2");
                data5.setValue("攀枝花市工商行政管理局"+"--层"+levelNo);
                data5.setEndValue(true);
                organList.add(data5);
                DominationModel data6 = new DominationModel();
                data6.setId("3");
                data6.setValue("阳德市工商行政管理局"+"--层"+levelNo);
                data6.setEndValue(true);
                organList.add(data6);
                break;
        }
        oneAdapter.setData(organList);
        oneAdapter.notifyDataSetChanged();
    }

    private void findView() {
        if(getIntent().getStringExtra("levelNo")!=null){
            String strLevelNum = getIntent().getStringExtra("levelNo");
            levelNo = Integer.parseInt(strLevelNum);
        }
        oneAdapter = new ChoiceListAdapter();

        backBtn = (LinearLayout) findViewById(R.id.ll_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DominationDeptActivity.this.finish();
            }
        });
        titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText("管辖机关选择");

        oneLv = (ListView) findViewById(R.id.lv_first);
        oneLv.setAdapter(oneAdapter);
//        oneLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                         @Override
//                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                             selectItem = -1;
//                                             if(organList.get(position).isEndValue()==true){
//                                                 Intent intent = new Intent();
//                                                 intent.putExtra("DominationName", organList.get(position).getValue());
//                                                 setResult(levelNo, intent);
//                                                 finish();
//                                             }else{
//                                                 int nextLevel = levelNo+1;
//                                                 Intent intent = new Intent(context, DominationDeptActivity.class);
//                                                 intent.putExtra("levelNo", String.valueOf(nextLevel));
//                                                 startActivityForResult(intent, nextLevel);
//                                             }
//                                         }
//                                     }
//        );
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == levelNo+1 && resultCode == levelNo+1){
            setResult(levelNo, data);
            finish();
        }
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

            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("DominationName", organList.get(position).getValue());
                    setResult(levelNo, intent);
                    finish();
                }
            });

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(organList.get(position).isEndValue()==true){
                        Intent intent = new Intent();
                        intent.putExtra("DominationName", organList.get(position).getValue());
                        setResult(levelNo, intent);
                        finish();
                    }else{
                        int nextLevel = levelNo+1;
                        Intent intent = new Intent(context, DominationDeptActivity.class);
                        intent.putExtra("levelNo", String.valueOf(nextLevel));
                        startActivityForResult(intent, nextLevel);
                    }
                }
            });

            return convertView;
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
