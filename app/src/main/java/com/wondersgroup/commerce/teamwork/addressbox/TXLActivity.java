package com.wondersgroup.commerce.teamwork.addressbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.*;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Address;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.widget.SearchBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class TXLActivity extends RootActivity implements View.OnClickListener {
    private Context context;
    // 控件
    private IndexView.OnIndexSelectListener listener;
    private IndexView indexView;
    private LinearLayout backBtn;
    private TextView tvTitle;
    private ListView lv;
    private LinearLayout deptLayout;
    private TextView deptSelect;
    private List<Address.AddlistPersonalInfo> lvData;
    private List<Address.AddlistPersonalInfo> lvData2;

    @BindView(R.id.search_bar)
    SearchBar searchBar;

    private Comparator pinyinComparator = new PinyinComparator();
    private SortAdapter adapter = new SortAdapter();
    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txl);
        ButterKnife.bind(this);
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        context = this;

        findView();

        getDataWithNet(loginBean.getResult().getOrganId(), "");
    }

    @Override
    protected void onDestroy() {
        Log.e("TXLActivity","onDestroy");
        indexView.clean();

        super.onDestroy();
    }

    private void findView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("通讯录");

        deptSelect = (TextView) findViewById(R.id.tv_dept_select);
        deptSelect.setText(loginBean.getResult().getOrganName());

        deptLayout = (LinearLayout) findViewById(R.id.layout_dept);
        deptLayout.setOnClickListener(this);

        searchBar.setSearchListener(new SearchBar.SearchListener() {
            @Override
            public void search(String content) {
                lvData.clear();
                if (TextUtils.isEmpty(content)){
                    lvData.addAll(lvData2);
                }else {
                    for (Address.AddlistPersonalInfo datum: lvData2){
                        if (datum.getName().contains(content)){
                            lvData.add(datum);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        backBtn = (LinearLayout) findViewById(R.id.ll_back);
        backBtn.setOnClickListener(this);

        listener = new IndexView.OnIndexSelectListener() {
            @Override
            public void onItemSelect(int position, String value) {
                int p = adapter.getPositionForSection(value.charAt(0));
                if (p != -1) {
                    lv.setSelection(position);
                }
            }
        };
        indexView = (IndexView) findViewById(R.id.index_view);
        indexView.setListener(listener);

        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, TxlDetailActivity.class);

                intent.putExtra("userId", lvData.get(position).getAddlistId());

                startActivity(intent);
            }
        });
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ll_back:
                finish();
                break;
            case R.id.layout_dept:
                createDeptDialog();
                break;
        }
    }

    private class AddressHolder {
        private TextView nameTv;
        private TextView phoneTv;
        private TextView deptTv;
        private TextView letterTv;

        public TextView getNameTv() {
            return nameTv;
        }

        public void setNameTv(TextView nameTv) {
            this.nameTv = nameTv;
        }

        public TextView getDeptTv() {
            return deptTv;
        }

        public void setDeptTv(TextView deptTv) {
            this.deptTv = deptTv;
        }

        public TextView getPhoneTv() {
            return phoneTv;
        }

        public void setPhoneTv(TextView phoneTv) {
            this.phoneTv = phoneTv;
        }

        public TextView getLetterTv() {
            return letterTv;
        }

        public void setLetterTv(TextView letterTv) {
            this.letterTv = letterTv;
        }
    }

    private void createDeptDialog() {

        Intent intent = new Intent(context, TxlDeptActivity.class);
        startActivityForResult(intent, 0);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b = data.getExtras(); //data为B中回传的Intent
                String organId = b.getString("organId");//str即为回传的值
                String organName = b.getString("organName");

                deptSelect.setText(organName);
//                String deptId = b.getString("deptId");
                getDataWithNet(organId, organName);

                break;
            default:
                break;
        }
    }

    private void getDataWithNet(String organId, String name) {
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(TXLActivity.this)
                .build();
        loadingDialog.show();
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010003");
        map.put("pageNo", "1");
        map.put("pageSize", "100");
        map.put("contactOrganId", organId);
        map.put("contactUserName", name);

        Call<Address> call = ApiManager.oaApi.address(map);
        call.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Response<Address> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                if (response.isSuccess()) {
                    Address address = response.body();

                    if (address == null) {
                        Toast.makeText(TXLActivity.this, "没有返回数据", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (address.getResult().getAddlistPersonalInfo() == null) {
                        lvData.clear();
                        Toast.makeText(context, "没有找到相关人员", Toast.LENGTH_SHORT).show();

                        adapter.notifyDataSetChanged();

                        return;
                    }

                    lvData2 = address.getResult().getAddlistPersonalInfo();
                    setPinyinLetter();
                    lvData = new ArrayList<>();
                    for (Address.AddlistPersonalInfo personalInfo: lvData2){
                        Address.AddlistPersonalInfo temp = null;
                        try {
                            temp = (Address.AddlistPersonalInfo) personalInfo.clone();
                            lvData.add(temp);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (Address.AddlistPersonalInfo personalInfo: lvData){
                        personalInfo.setShow(true);
                    }

                    Collections.sort(lvData, pinyinComparator);

                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(mContext, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(mContext, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setPinyinLetter() {
        CharacterParser characterParser = new CharacterParser();
        for (int i = 0; i < lvData2.size(); i++) {

            String pinyin = characterParser.getSelling(lvData2.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                lvData2.get(i).setSortLetters(sortString.toUpperCase());
            } else {
                lvData2.get(i).setSortLetters("#");
            }
        }
    }

    class SortAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (lvData == null) {
                return 0;
            }

            return lvData.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            AddressHolder holder;
            boolean isShow = lvData.get(position).isShow();
            if (convertView == null) {
                convertView = View.inflate(TXLActivity.this, R.layout.item_address_list, null );
                holder = new AddressHolder();
                holder.setDeptTv((TextView) convertView.findViewById(R.id.tv_dept));
                holder.setNameTv((TextView) convertView.findViewById(R.id.tv_name));
                holder.setPhoneTv((TextView) convertView.findViewById(R.id.tv_phone));
                holder.setLetterTv((TextView) convertView.findViewById(R.id.tv_letter));

                convertView.setTag(holder);
            } else {
                holder = (AddressHolder) convertView.getTag();
            }

            convertView.setVisibility(View.VISIBLE);
//            if (!isShow){
//                convertView.setVisibility(View.GONE);
//            }

            holder.nameTv.setText(lvData.get(position).getName());
            holder.deptTv.setText(lvData.get(position).getDept());
            holder.phoneTv.setText(lvData.get(position).getTel());

            //根据position获取分类的首字母的char ascii值
            int section = getSectionForPosition(position);

            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if (position == getPositionForSection(section)) {
                holder.letterTv.setVisibility(View.VISIBLE);
                holder.letterTv.setText(lvData.get(position).getSortLetters());
            } else {
                holder.letterTv.setVisibility(View.GONE);
            }


            return convertView;
        }

        /**
         * 根据ListView的当前位置获取分类的首字母的char ascii值
         */
        public int getSectionForPosition(int position) {
            return lvData.get(position).getSortLetters().charAt(0);
        }

        /**
         * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
         */
        public int getPositionForSection(int section) {
            for (int i = 0; i < getCount(); i++) {
                String sortStr = lvData.get(i).getSortLetters();
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }

            return -1;
        }
    }

}
