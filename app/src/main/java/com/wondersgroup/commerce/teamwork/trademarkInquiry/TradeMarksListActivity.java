package com.wondersgroup.commerce.teamwork.trademarkInquiry;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.MoreBtnAdapter;
import com.wondersgroup.commerce.adapter.SingleAdapter;
import com.wondersgroup.commerce.adapter.Title4RowAdapter;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.Title4RowItem;
import com.wondersgroup.commerce.model.TradeMarkVoList;
import com.wondersgroup.commerce.widget.CusDatePickerDialog;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 注册商标 列表页面
* by Lee
* */

public class TradeMarksListActivity extends AppCompatActivity {

    private final String TAG = "TradeMarksListActivity";
    private final String MARK_CHOOSE_OPTION_NO_LIMITED = "不限";
    private final String MARK_CHOOSE_OPTION_YES = "是";
    private final String MARK_CHOOSE_OPTION_NO = "不是";
    @Bind(R.id.toolbar_edit)
    EditText toolbarEdit;
    @Bind(R.id.toolbar_clear)
    ImageView toolbarClear;
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.searchresult_list)
    ListView searchRecyclerView;
    @Bind(R.id.type_txt)
    TextView typeTxt;
    @Bind(R.id.searchresult_type)
    LinearLayout searchresultType;
    @Bind(R.id.txt_obligeetype)
    TextView obligeetypeTxt;
    @Bind(R.id.txt_claim_state)
    TextView claimStateTxt;
    @Bind(R.id.txt_more)
    TextView moreTxt;
    @Bind(R.id.gray_view)
    View grayBg;
    @Bind(R.id.right_btn)
    LinearLayout qrBtn;
    @Bind(R.id.drawer_choose_list)
    ListView chooseMenuOptionsList;
    @Bind(R.id.date1txt)
    TextView date1txt;
    @Bind(R.id.date1)
    LinearLayout date1;
    @Bind(R.id.date2txt)
    TextView date2txt;
    @Bind(R.id.date2)
    LinearLayout date2;
    @Bind(R.id.simple_navigation_drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.llDomination4Trademarks)
    LinearLayout llDomination4Trademarks;
    @Bind(R.id.tvDominationName)
    TextView tvDominationNameSelected;

    private String type;
    private RootAppcation app;

    //筛选相关
    private List<String> typeList;
    private List<String> checkTypeList;
    private List<String> checkResultList;
    private SingleAdapter typeAdapter;
    private SingleAdapter checkTypeAdapter;
    private SingleAdapter checkResultAdapter;
    private PopupWindow typePopup;
    private PopupWindow checkTypePopup;
    private PopupWindow checkResultPopup;
    private int typePos = 0;
    private int checkTypePos = 0;
    private int checkResultPos = 0;

    //更多筛选相关
    private MoreBtnAdapter moreBtnAdapter;
    private List<Boolean> itemsIsClick = new ArrayList<>();
    private List<String> adBtnStr = new ArrayList<>();        //administrative division
    private String division;
    private boolean isDate1null = true;
    private boolean isDate2null = true;
    private boolean isDate3null = true;
    private boolean isDate4null = true;
    private String eStartDate;   //成立日期始
    private String eEndDate;     //成立日期止
    private String cStartDate;   //抽查检查日期始
    private String cEndDate;     //抽查检查日期止


    //搜索结果相关
//    private List<JGQueryRes.ResultBean.ResultListBean>
//            resultList;
//    private List<JGRecordQRes.ResultBean.ResultListBean>
//            resList;
    private List<Title4RowItem> dataList;
    private Title4RowAdapter title4RowAdapter;
//    private List<Title5RowItem> row5List;
//    private Title5RowAdapter title5RowAdapter;
    private List<TradeMarkVoList> marksList;
    private RegisterMarksAdapter marksAdapter;
    private List<MarkChooseOption> menuChooseOption;
    private MarkChooseMenAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trademarks_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();

        initView();
        initData();
    }

    private void initView() {
        menuChooseOption = new ArrayList<MarkChooseOption>();
        searchRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(TradeMarksListActivity.this, TradeMarkDetailActivity.class));
            }
        });
    }

    private void initData(){
        //key+"="+map.get(key);
        checkTypeList = new ArrayList<String>();
        checkTypeList.add("1=企业");
        checkTypeList.add("2=个体工商户");
        checkTypeList.add("3=事业单位");
        checkTypeList.add("4=社会组织");
        checkTypeList.add("5=自然人");

        MarkChooseOption option1 = new MarkChooseOption();
        option1.setOptionName("是否共有商标");
        menuChooseOption.add(option1);
        MarkChooseOption option2 = new MarkChooseOption();
        option2.setOptionName("是否立体商标");
        menuChooseOption.add(option2);
        MarkChooseOption option3 = new MarkChooseOption();
        option3.setOptionName("是否地理商标");
        menuChooseOption.add(option3);
        MarkChooseOption option4 = new MarkChooseOption();
        option4.setOptionName("是否国际注册");
        menuChooseOption.add(option4);
        MarkChooseOption option5 = new MarkChooseOption();
        option5.setOptionName("是否有效商标");
        menuChooseOption.add(option5);

        menuAdapter = new MarkChooseMenAdapter(TradeMarksListActivity.this, menuChooseOption);
        chooseMenuOptionsList.setAdapter(menuAdapter);

        marksList = new ArrayList<TradeMarkVoList>();
        TradeMarkVoList data1 = new TradeMarkVoList();
        data1.setMarkName("蒙氏");
        data1.setRegisterNo("11010510");
        data1.setMarkOwner("长沙啤酒厂");
        data1.setOwnerAddress("湖南场合是基督山");
        data1.setDominationName("四川行政管理局");
        marksList.add(data1);
        TradeMarkVoList data2 = new TradeMarkVoList();
        data2.setMarkName("蒙氏试试2");
        data2.setRegisterNo("11010510");
        data2.setMarkOwner("长沙啤酒厂");
        data2.setOwnerAddress("湖南场合是基督山");
        data2.setDominationName("四川行政管理局");
        marksList.add(data2);
        TradeMarkVoList data3 = new TradeMarkVoList();
        data3.setMarkName("蒙氏开花");
        data3.setRegisterNo("11010510");
        data3.setMarkOwner("长沙啤酒厂");
        data3.setOwnerAddress("湖南场合是基督山");
        data3.setDominationName("四川行政管理局");
        marksList.add(data3);
        marksAdapter = new RegisterMarksAdapter(TradeMarksListActivity.this, marksList);
        searchRecyclerView.setAdapter(marksAdapter);

    }

    @OnClick({R.id.toolbar_clear, R.id.right_btn, R.id.searchresult_type, R.id.searchobligee_checktype,
            R.id.searchresult_result, R.id.searchresult_more, R.id.date1, R.id.date2, R.id.resetbtn, R.id.submitbtn,
            R.id.llDomination4Trademarks})
    public void onClick(View view) {
        final CusDatePickerDialog dateDialog;
        switch (view.getId()) {
            case R.id.toolbar_clear:
                toolbarEdit.setText("");
                toolbarClear.setVisibility(View.INVISIBLE);
                break;
            case R.id.right_btn:
                break;
            case R.id.searchresult_type:
                if (checkTypeList == null) return;
                //typeTxt.setTextColor(ContextCompat.getColor(this, R.color.red));
                View view1 = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_style01, null);
                RecyclerView typeRecyclerView = (RecyclerView) view1.findViewById(R.id.pop_recyclerview);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                typeRecyclerView.setLayoutManager(layoutManager);
                typeRecyclerView.setItemAnimator(new DefaultItemAnimator());
                typeAdapter = new SingleAdapter(this, checkTypeList);
                typeRecyclerView.setAdapter(typeAdapter);
                typeAdapter.setOnItemClickListener(new SingleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        typePopup.dismiss();
                        typeTxt.setText(checkTypeList.get(position).split("=")[1].trim() + " ▼");
                        typePos = position;
//                        getData(1);
                    }
                });
                if (typePopup == null) {
                    typePopup = new PopupWindow(this);
                    typePopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    typePopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    typePopup.setContentView(view1);
                }
                // 设置背景颜色变暗
                grayBg.setVisibility(View.VISIBLE);
                grayBg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_popin_bg));
                typePopup.setFocusable(true);// 取得焦点
                typePopup.setBackgroundDrawable(new ColorDrawable(0x00000000));
                typePopup.showAsDropDown(searchresultType);
                typePopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        grayBg.setVisibility(View.GONE);
                        grayBg.setAnimation(AnimationUtils.loadAnimation(TradeMarksListActivity.this, R.anim.anim_popout_bg));
                    }
                });
                break;
            case R.id.searchobligee_checktype:

                break;
            case R.id.searchresult_result:

                break;
            case R.id.searchresult_more:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.date1:
                dateDialog = CusDatePickerDialog.newInstance("选择起始时间");
                dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void OnDateSet(String dateString) {
                        if (!"".equals(date2txt.getText().toString())
                                && Date.valueOf(dateString).after(Date.valueOf(date2txt.getText().toString()))) {
                            Toast.makeText(TradeMarksListActivity.this, "起始时间不能大于截止时间", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (isDate1null) {
                            date1.setBackgroundResource(R.drawable.rounded_rect_blueline);
                            isDate1null = false;
                        }
                        date1txt.setText(dateString);
                        eStartDate = dateString;
                    }
                });
                dateDialog.show(getSupportFragmentManager(), "CLRQS");
                break;
            case R.id.date2:
                dateDialog = CusDatePickerDialog.newInstance("选择截止时间");
                dateDialog.setOnDateSetListener(new CusDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void OnDateSet(String dateString) {
                        if (!"".equals(date1txt.getText().toString())
                                && Date.valueOf(dateString).before(Date.valueOf(date1txt.getText().toString()))) {
                            Toast.makeText(TradeMarksListActivity.this, "截止时间不能小于起始时间", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (isDate2null) {
                            date2.setBackgroundResource(R.drawable.rounded_rect_blueline);
                            isDate2null = false;
                        }
                        date2txt.setText(dateString);
                        eEndDate = dateString;
                    }
                });
                dateDialog.show(getSupportFragmentManager(), "CLRQZ");
                break;


            case R.id.resetbtn:
                break;
            case R.id.submitbtn:
                drawerLayout.closeDrawers();
                break;
            case R.id.llDomination4Trademarks:
                Intent intent = new Intent(TradeMarksListActivity.this, DominationSelectActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1){
            if(data != null){
                tvDominationNameSelected.setText(data.getStringExtra("DominationName"));
            }
        }
    }


    ////检查editText是否输入
    public boolean isEditTextEmpty(EditText tv) {
        boolean flag = false;
        if (tv.getText() == null
                || tv.getText().toString().trim().equals("")) {
            flag = true;
        }
        return flag;
    }

    public class MarkChooseMenAdapter extends BaseAdapter{

        private LayoutInflater inflater;
        private Context adapterContext;
        private List<MarkChooseOption> markChooseOptionList;

        public MarkChooseMenAdapter(Context mContext, List<MarkChooseOption> mList){
            adapterContext = mContext;
            markChooseOptionList = mList;
            inflater = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return markChooseOptionList.size();
        }

        @Override
        public Object getItem(int i) {
            return markChooseOptionList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int pos, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if(viewHolder == null){
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_trade_mark_inqury_menu, viewGroup, false);
                viewHolder.tvOptionName = (TextView)convertView.findViewById(R.id.tvName4ItemTradeMarkInquiryMenu);
                viewHolder.llOptionNoLimit = (LinearLayout)convertView.findViewById(R.id.llOptionNoLimit4ItemTradeMarkInquiryMenu);
                viewHolder.llOptionYes = (LinearLayout)convertView.findViewById(R.id.llOptionYes4ItemTradeMarkInquiryMenu);
                viewHolder.llOptionNo = (LinearLayout)convertView.findViewById(R.id.llOptionNo4ItemTradeMarkInquiryMenu);
                viewHolder.tvOptionNoLimit = (TextView)convertView.findViewById(R.id.tvOptionNoLimit4ItemTradeMarkInquiryMenu);
                viewHolder.tvOptionYes = (TextView)convertView.findViewById(R.id.tvOptionYes4ItemTradeMarkInquiryMenu);
                viewHolder.tvOptionNo = (TextView)convertView.findViewById(R.id.tvOptionNo4ItemTradeMarkInquiryMenu);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.tvOptionName.setText(markChooseOptionList.get(pos).getOptionName());
            if(markChooseOptionList.get(pos).getOptionValue().equals(MARK_CHOOSE_OPTION_NO_LIMITED)){
                viewHolder.llOptionNoLimit.setBackgroundResource(R.drawable.rounded_rect_blueline);
                viewHolder.tvOptionNoLimit.setTextColor(ContextCompat.getColor(adapterContext, R.color.blue));
            }else if(markChooseOptionList.get(pos).getOptionValue().equals(MARK_CHOOSE_OPTION_YES)){
                viewHolder.llOptionYes.setBackgroundResource(R.drawable.rounded_rect_blueline);
                viewHolder.tvOptionYes.setTextColor(ContextCompat.getColor(adapterContext, R.color.blue));
            }else{
                viewHolder.llOptionNo.setBackgroundResource(R.drawable.rounded_rect_blueline);
                viewHolder.tvOptionNo.setTextColor(ContextCompat.getColor(adapterContext, R.color.blue));
            }
            viewHolder.llOptionNoLimit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    markChooseOptionList.get(pos).setOptionValue(MARK_CHOOSE_OPTION_NO_LIMITED);
                    notifyDataSetChanged();
                }
            });
            viewHolder.llOptionYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    markChooseOptionList.get(pos).setOptionValue(MARK_CHOOSE_OPTION_YES);
                    notifyDataSetChanged();
                }
            });
            viewHolder.llOptionNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    markChooseOptionList.get(pos).setOptionValue(MARK_CHOOSE_OPTION_NO);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }

        private class ViewHolder{
            private TextView tvOptionName;
            private LinearLayout llOptionNoLimit;
            private LinearLayout llOptionYes;
            private LinearLayout llOptionNo;
            private TextView tvOptionNoLimit;
            private TextView tvOptionYes;
            private TextView tvOptionNo;
        }
    }

    public class MarkChooseOption{
        private String optionName;
        private String optionValue = MARK_CHOOSE_OPTION_NO_LIMITED;

        public String getOptionName() {
            return optionName;
        }
        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }
        public String getOptionValue() {
            return optionValue;
        }
        public void setOptionValue(String optionValue) {
            this.optionValue = optionValue;
        }
    }

    public class RegisterMarksAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<TradeMarkVoList> marksList;

        public RegisterMarksAdapter(Context context, List<TradeMarkVoList> dataList) {
            this.adpterContext = context;
            inflater = (LayoutInflater) adpterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            marksList = dataList;
        }

        @Override
        public int getCount() {
            return marksList.size();
        }

        @Override
        public Object getItem(int position) {
            return marksList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_trade_mark_list, parent, false);
                holder.tv_MarkName = (TextView) convertView.findViewById(R.id.tvMarkName4ItemTradeMarkList);
                holder.tv_RegisterNum = (TextView) convertView.findViewById(R.id.tvRegisterNum4ItemTradeMarkList);
                holder.tv_OwnerName = (TextView) convertView.findViewById(R.id.tvOwnerName4ItemTradeMarkList);
                holder.tv_OwnerAddress = (TextView) convertView.findViewById(R.id.tvOwnerAddress4ItemTradeMarkList);
                holder.tv_DominationName = (TextView) convertView.findViewById(R.id.tvDominationName4ItemTradeMarkList);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_MarkName.setText(marksList.get(position).getMarkName());
            holder.tv_RegisterNum.setText(marksList.get(position).getRegisterNo());
            holder.tv_OwnerName.setText(marksList.get(position).getMarkOwner());
            holder.tv_OwnerAddress.setText(marksList.get(position).getOwnerAddress());
            holder.tv_DominationName.setText(marksList.get(position).getDominationName());

            return convertView;
        }

        private class ViewHolder {
            private TextView tv_MarkName;
            private TextView tv_RegisterNum;
            private TextView tv_OwnerName;
            private TextView tv_OwnerAddress;
            private TextView tv_DominationName;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

