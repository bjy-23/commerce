package com.wondersgroup.commerce.teamwork.trademarkInquiry;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.TradeMarkContactorVoList;
import com.wondersgroup.commerce.model.TradeMarkGoodsServiceVoList;
import com.wondersgroup.commerce.model.TradeMarkInterRegisterVoList;
import com.wondersgroup.commerce.model.TradeMarkOwnerVoList;
import com.wondersgroup.commerce.model.TradeMarkPermitVoList;
import com.wondersgroup.commerce.model.TradeMarkPledgeVoList;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by admin on 2017/7/26.
 */

public class TradeMarkDetailActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView tvTitle;
    @Bind(R.id.ivBaseInfo4TradeMarkDetail)ImageView ivArrowBaseInfo;    //商标基本信息
    @Bind(R.id.llbaseInfo4TradeMarkDetail)LinearLayout llViewBaseInfo;  //商标基本信息
    @Bind(R.id.ivEntityInfo4TradeMarkDetail)ImageView ivArrowEntityInfo;    //商标主体信息
    @Bind(R.id.llEntityInfo4TradeMarkDetail)LinearLayout llViewEntityInfo;  //商标主体信息
    @Bind(R.id.ivApplierInfo4TradeMarkDetail)ImageView ivArrowApplierInfo;    //商标申请人信息
    @Bind(R.id.llApplierInfo4TradeMarkDetail)LinearLayout llViewApplierInfo;  //商标申请人信息
    @Bind(R.id.ivGoodsService4TradeMarkDetail)ImageView ivGoodsServiceInfo;    //商品服务信息
    @Bind(R.id.lvGoodsService4TradeMarkDetail)ListView lvGoodsService;                //商品服务信息
    @Bind(R.id.ivCommonOwner4TradeMarkDetail)ImageView ivArrowCommonOwnerInfo;    //商标共有人信息
    @Bind(R.id.lvCommonOwner4TradeMarkDetail)ListView lvCommonOwner;                //商标共有人信息
    @Bind(R.id.ivAgent4TradeMarkDetail)ImageView ivArrowAgentInfo;    //商标代理人信息
    @Bind(R.id.llAgentInfo4TradeMarkDetail)LinearLayout llViewAgentInfo;  //商标代理人信息
    @Bind(R.id.ivPriority4TradeMarkDetail)ImageView ivArrowPriorityInfo;    //商标优先权信息
    @Bind(R.id.llPriorityInfo4TradeMarkDetail)LinearLayout llViewPriorityInfo;  //商标优先权信息
    @Bind(R.id.ivInterReg4TradeMarkDetail)ImageView ivArrowInterRegInfo;    //商标国际注册信息
    @Bind(R.id.lvInterReg4TradeMarkDetail)ListView lvInterReg;                //商标国际注册信息
    @Bind(R.id.ivPermit4TradeMarkDetail)ImageView ivArrowPermitInfo;    //商标许可信息
    @Bind(R.id.lvPermit4TradeMarkDetail)ListView lvPermit;                //商标许可信息
    @Bind(R.id.ivContactor4TradeMarkDetail)ImageView ivArrowContactorInfo;    //商标联络人信息
    @Bind(R.id.lvContactor4TradeMarkDetail)ListView lvContactor;                //商标联络人信息
    @Bind(R.id.ivOtherInfo4TradeMarkDetail)ImageView ivArrowOtherInfo;    //商标其他信息
    @Bind(R.id.llOtherInfo4TradeMarkDetail)LinearLayout llViewOtherInfo;  //商标其他信息
    @Bind(R.id.ivPledge4TradeMarkDetail)ImageView ivArrowPledgeInfo;    //商标质押信息
    @Bind(R.id.lvPledge4TradeMarkDetail)ListView lvPledge;                //商标质押信息

    private List<TradeMarkGoodsServiceVoList> goodsServiceList;     //商品服务信息列表
    private GoodsServiceAdapter goodsServiceAdapter;
    private List<TradeMarkOwnerVoList> commonOwnerList;             //商标共有人信息列表
    private CommoonOwnerAdapter commoonOwnerAdapter;
    private List<TradeMarkInterRegisterVoList> interRegisterList;             //商标国际注册信息列表
    private InterRegisterAdapter interRegisterAdapter;
    private List<TradeMarkPermitVoList> permitInfoList;             //商标许可信息列表
    private PermitAdapter permitAdapter;
    private List<TradeMarkContactorVoList> contactorsList;             //商标联络人信息列表
    private ContactoreAdapter contactoreAdapter;
    private List<TradeMarkPledgeVoList> pledgesList;             //商标质押信息列表
    private PledgeAdapter pledgeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_mark_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        /*ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);*/
        initView();
        initData();
    }

    private void initView() {
        tvTitle.setText("注册商标信息");
    }

    @OnClick({R.id.tv_option, R.id.llBaseInfoTitle4TradeMarkDetail, R.id.llEntityInfoTitle4TradeMarkDetail, R.id.llApplierInfoTitle4TradeMarkDetail,
    R.id.llCommonOwnerTitle4TradeMarkDetail, R.id.llGoodsServiceTitle4TradeMarkDetail, R.id.llAgentTitle4TradeMarkDetail,
    R.id.llPriorityTitle4TradeMarkDetail, R.id.llInterRegTitle4TradeMarkDetail, R.id.llPermitTitle4TradeMarkDetail, R.id.llContactor4TradeMarkDetail,
    R.id.llPledgeTitle4TradeMarkDetail, R.id.llOtherInfoTitle4TradeMarkDetail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_option:
                finish();
                break;
            case R.id.llBaseInfoTitle4TradeMarkDetail:
                changeView(llViewBaseInfo, ivArrowBaseInfo);
                break;
            case R.id.llEntityInfoTitle4TradeMarkDetail:
                changeView(llViewEntityInfo, ivArrowEntityInfo);
                break;
            case R.id.llApplierInfoTitle4TradeMarkDetail:
                changeView(llViewApplierInfo, ivArrowApplierInfo);
                break;
            case R.id.llCommonOwnerTitle4TradeMarkDetail:
                changeView(lvCommonOwner, ivArrowCommonOwnerInfo);
                break;
            case R.id.llGoodsServiceTitle4TradeMarkDetail:
                changeView(lvGoodsService, ivGoodsServiceInfo);
                break;
            case R.id.llAgentTitle4TradeMarkDetail:
                changeView(llViewAgentInfo, ivArrowAgentInfo);
                break;
            case R.id.llPriorityTitle4TradeMarkDetail:
                changeView(llViewPriorityInfo, ivArrowPriorityInfo);
                break;
            case R.id.llInterRegTitle4TradeMarkDetail:
                changeView(lvInterReg, ivArrowInterRegInfo);
                break;
            case R.id.llPermitTitle4TradeMarkDetail:
                changeView(lvPermit, ivArrowPermitInfo);
                break;
            case R.id.llContactor4TradeMarkDetail:
                changeView(lvContactor, ivArrowContactorInfo);
                break;
            case R.id.llPledgeTitle4TradeMarkDetail:
                changeView(lvPledge, ivArrowPledgeInfo);
                break;
            case R.id.llOtherInfoTitle4TradeMarkDetail:
                changeView(llViewOtherInfo, ivArrowOtherInfo);
                break;
        }
    }

    private void initData(){

        goodsServiceList = new ArrayList<TradeMarkGoodsServiceVoList>();
        TradeMarkGoodsServiceVoList serviceData1 = new TradeMarkGoodsServiceVoList();
        serviceData1.setGoodsServiceNumber("狮跑");
        serviceData1.setSimilarGroup("路虎");
        serviceData1.setGoods("翼虎");
        goodsServiceList.add(serviceData1);
        TradeMarkGoodsServiceVoList serviceData2 = new TradeMarkGoodsServiceVoList();
        serviceData2.setGoodsServiceNumber("狮跑");
        serviceData2.setSimilarGroup("路虎");
        serviceData2.setGoods("翼虎");
        goodsServiceList.add(serviceData2);
        TradeMarkGoodsServiceVoList serviceData3 = new TradeMarkGoodsServiceVoList();
        serviceData3.setGoodsServiceNumber("狮跑");
        serviceData3.setSimilarGroup("路虎");
        serviceData3.setGoods("翼虎");
        goodsServiceList.add(serviceData3);
        TradeMarkGoodsServiceVoList serviceData4 = new TradeMarkGoodsServiceVoList();
        serviceData4.setGoodsServiceNumber("狮跑");
        serviceData4.setSimilarGroup("路虎");
        serviceData4.setGoods("翼虎");
        goodsServiceList.add(serviceData4);
        goodsServiceAdapter = new GoodsServiceAdapter(TradeMarkDetailActivity.this, goodsServiceList);
        lvGoodsService.setAdapter(goodsServiceAdapter);

        commonOwnerList = new ArrayList<TradeMarkOwnerVoList>();
        TradeMarkOwnerVoList data1 = new TradeMarkOwnerVoList();
        data1.setOwnerNumber("蒙氏");
        data1.setChineseName("蒙氏数学");
        data1.setEnglishName("MengShiMath");
        data1.setChineseAddress("湖南场合是基督山");
        data1.setEnghlishAddress("Shanghai xuhui district Lianhang Road.");
        commonOwnerList.add(data1);
        TradeMarkOwnerVoList data2 = new TradeMarkOwnerVoList();
        data2.setOwnerNumber("蒙氏");
        data2.setChineseName("蒙氏数学");
        data2.setEnglishName("MengShiMath");
        data2.setChineseAddress("湖南场合是基督山");
        data2.setEnghlishAddress("Shanghai xuhui district Lianhang Road.");
        commonOwnerList.add(data2);
        TradeMarkOwnerVoList data3 = new TradeMarkOwnerVoList();
        data3.setOwnerNumber("蒙氏");
        data3.setChineseName("蒙氏数学");
        data3.setEnglishName("MengShiMath");
        data3.setChineseAddress("湖南场合是基督山");
        data3.setEnghlishAddress("Shanghai xuhui district Lianhang Road.");
        commonOwnerList.add(data3);
        commoonOwnerAdapter = new CommoonOwnerAdapter(TradeMarkDetailActivity.this, commonOwnerList);
        lvCommonOwner.setAdapter(commoonOwnerAdapter);

        interRegisterList = new ArrayList<TradeMarkInterRegisterVoList>();
        TradeMarkInterRegisterVoList regData1 = new TradeMarkInterRegisterVoList();
        regData1.setNumber("狮跑");
        regData1.setRegisterName("太阳光");
        regData1.setRegisterNo("1112223");
        regData1.setTypeNo("4356");
        regData1.setRegisterWay("上网注册");
        regData1.setRegisterCountry("China");
        regData1.setRegisterDate("2017-09-19");
        interRegisterList.add(regData1);
        TradeMarkInterRegisterVoList regData2 = new TradeMarkInterRegisterVoList();
        regData2.setNumber("狗刨");
        regData2.setRegisterName("太阳光强");
        regData2.setRegisterNo("111223455");
        regData2.setTypeNo("4356");
        regData2.setRegisterWay("上网注册");
        regData2.setRegisterCountry("China");
        regData2.setRegisterDate("2017-09-19");
        interRegisterList.add(regData2);
        interRegisterAdapter = new InterRegisterAdapter(TradeMarkDetailActivity.this, interRegisterList);
        lvInterReg.setAdapter(interRegisterAdapter);

        permitInfoList = new ArrayList<TradeMarkPermitVoList>();
        TradeMarkPermitVoList permitData1 = new TradeMarkPermitVoList();
        permitData1.setPermitNumber("枕头");
        permitData1.setPermitName("垃圾桶");
        permitData1.setGoods("窗帘");
        permitData1.setType("活用例句");
        permitData1.setStartDate("2019-09-09");
        permitData1.setEndDate("2020-02-02");
        permitInfoList.add(permitData1);
        TradeMarkPermitVoList permitData2 = new TradeMarkPermitVoList();
        permitData2.setPermitNumber("电话");
        permitData2.setPermitName("电视");
        permitData2.setGoods("录像机");
        permitData2.setType("冰箱日历");
        permitData2.setStartDate("2011-09-09");
        permitData2.setEndDate("2019-02-02");
        permitInfoList.add(permitData2);
        TradeMarkPermitVoList permitData3 = new TradeMarkPermitVoList();
        permitData3.setPermitNumber("枕头");
        permitData3.setPermitName("垃圾桶");
        permitData3.setGoods("窗帘");
        permitData3.setType("活用例句");
        permitData3.setStartDate("2019-09-09");
        permitData3.setEndDate("2020-02-02");
        permitInfoList.add(permitData3);
        permitAdapter = new PermitAdapter(TradeMarkDetailActivity.this, permitInfoList);
        lvPermit.setAdapter(permitAdapter);

        contactorsList = new ArrayList<TradeMarkContactorVoList>();
        TradeMarkContactorVoList contactData1 = new TradeMarkContactorVoList();
        contactData1.setContactorNumber("餐具");
        contactData1.setName("茶碗");
        contactData1.setTelephone("78768889");
        contactData1.setMobile("137479876576");
        contactData1.setAddress("叉子餐刀小岛");
        contactorsList.add(contactData1);
        TradeMarkContactorVoList contactData2 = new TradeMarkContactorVoList();
        contactData2.setContactorNumber("餐具");
        contactData2.setName("茶碗");
        contactData2.setTelephone("78768889");
        contactData2.setMobile("137479876576");
        contactData2.setAddress("叉子餐刀小岛");
        contactorsList.add(contactData2);
        TradeMarkContactorVoList contactData3 = new TradeMarkContactorVoList();
        contactData3.setContactorNumber("餐具");
        contactData3.setName("茶碗");
        contactData3.setTelephone("78768889");
        contactData3.setMobile("137479876576");
        contactData3.setAddress("叉子餐刀小岛");
        contactorsList.add(contactData3);
        contactoreAdapter = new ContactoreAdapter(TradeMarkDetailActivity.this, contactorsList);
        lvContactor.setAdapter(contactoreAdapter);

        pledgesList = new ArrayList<TradeMarkPledgeVoList>();
        TradeMarkPledgeVoList pledgeData1 = new TradeMarkPledgeVoList();
        pledgeData1.setPledgor("身体构造");
        pledgeData1.setPawnee("脖子");
        pledgeData1.setReason("身体不适");
        pledgeData1.setPledgeTime("2020-10-10");
        pledgeData1.setNotifyDate("2020-02-02");
        pledgesList.add(pledgeData1);
        TradeMarkPledgeVoList pledgeData2 = new TradeMarkPledgeVoList();
        pledgeData2.setPledgor("身体构造");
        pledgeData2.setPawnee("脖子");
        pledgeData2.setReason("身体不适");
        pledgeData2.setPledgeTime("2020-10-10");
        pledgeData2.setNotifyDate("2020-02-02");
        pledgesList.add(pledgeData2);
        pledgeAdapter = new PledgeAdapter(TradeMarkDetailActivity.this, pledgesList);
        lvPledge.setAdapter(pledgeAdapter);
    }

    //切换隐藏和显示各模块
    private void changeView(LinearLayout linearLayout, ImageView imageView){
        if(linearLayout.getVisibility() == View.GONE){
            linearLayout.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.icon_more_up);
        }else{
            linearLayout.setVisibility(View.GONE);
            imageView.setImageResource(R.mipmap.icon_more_down);
        }
    }
    //切换隐藏和显示各模块
    private void changeView(ListView listView, ImageView imageView){
        if(listView.getVisibility() == View.GONE){
            setListViewHeightBasedOnChildren(listView);
            listView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.icon_more_up);
        }else{
            listView.setVisibility(View.GONE);
            imageView.setImageResource(R.mipmap.icon_more_down);
        }
    }

    //商品服务Adapter
    public class GoodsServiceAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<TradeMarkGoodsServiceVoList> marksList;

        public GoodsServiceAdapter(Context context, List<TradeMarkGoodsServiceVoList> dataList) {
            this.adpterContext = context;
            inflater = (LayoutInflater) adpterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            marksList = dataList;
        }

        @Override
        public int getCount() { return marksList.size();}

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
                convertView = inflater.inflate(R.layout.item_trade_mark_goods_service_list, parent, false);
                holder.tv_GoodsServiceNum = (TextView) convertView.findViewById(R.id.tvGoodsServiceNumber4ItemTradeMarkGoodsServiceList);
                holder.tv_SimilarGroup = (TextView) convertView.findViewById(R.id.tvSimilarGroup4ItemTradeMarkGoodsServiceList);
                holder.tv_Goods = (TextView) convertView.findViewById(R.id.tvGoods4ItemTradeMarkGoodsServiceList);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_GoodsServiceNum.setText(marksList.get(position).getGoodsServiceNumber());
            holder.tv_SimilarGroup.setText(marksList.get(position).getGoodsServiceNumber());
            holder.tv_Goods.setText(marksList.get(position).getGoods());

            return convertView;
        }

        private class ViewHolder {
            private TextView tv_GoodsServiceNum;
            private TextView tv_SimilarGroup;
            private TextView tv_Goods;
        }
    }

    //共有人Adapter
    public class CommoonOwnerAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<TradeMarkOwnerVoList> marksList;
        private int itemCount = 2;

        public CommoonOwnerAdapter(Context context, List<TradeMarkOwnerVoList> dataList) {
            this.adpterContext = context;
            inflater = (LayoutInflater) adpterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            marksList = dataList;
        }

        @Override
        public int getCount() { return marksList.size();}

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
                convertView = inflater.inflate(R.layout.item_trade_mark_owner_list, parent, false);
                holder.tv_OwnerNum = (TextView) convertView.findViewById(R.id.tvOwnerNumber4ItemTradeMarkOwnerList);
                holder.tv_OwnerChineseName = (TextView) convertView.findViewById(R.id.tvChineseName4ItemTradeMarkOwnerList);
                holder.tv_OwnerEnghlishName = (TextView) convertView.findViewById(R.id.tvEnglishName4ItemTradeMarkOwnerList);
                holder.tv_OwnerChineseAddress = (TextView) convertView.findViewById(R.id.tvChineseAddress4ItemTradeMarkOwnerList);
                holder.tv_OwnerEnglishAddress = (TextView) convertView.findViewById(R.id.tvEnglishAddress4ItemTradeMarkOwnerList);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_OwnerNum.setText(marksList.get(position).getOwnerNumber());
            holder.tv_OwnerChineseName.setText(marksList.get(position).getChineseName());
            holder.tv_OwnerEnghlishName.setText(marksList.get(position).getEnglishName());
            holder.tv_OwnerChineseAddress.setText(marksList.get(position).getChineseAddress());
            holder.tv_OwnerEnglishAddress.setText(marksList.get(position).getEnghlishAddress());

            return convertView;
        }

        private class ViewHolder {
            private TextView tv_OwnerNum;
            private TextView tv_OwnerChineseName;
            private TextView tv_OwnerEnghlishName;
            private TextView tv_OwnerChineseAddress;
            private TextView tv_OwnerEnglishAddress;
        }
    }

    //国际注册信息Adapter
    public class InterRegisterAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<TradeMarkInterRegisterVoList> marksList;

        public InterRegisterAdapter(Context context, List<TradeMarkInterRegisterVoList> dataList) {
            this.adpterContext = context;
            inflater = (LayoutInflater) adpterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            marksList = dataList;
        }

        @Override
        public int getCount() { return marksList.size();}

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
                convertView = inflater.inflate(R.layout.item_trade_mark_inter_register_list, parent, false);
                holder.tv_Mumber = (TextView) convertView.findViewById(R.id.tvRegNumber4ItemTradeMarkInterRegList);
                holder.tv_RegName = (TextView) convertView.findViewById(R.id.tvRegName4ItemTradeMarkInterRegList);
                holder.tv_RegNo = (TextView) convertView.findViewById(R.id.tvRegNo4ItemTradeMarkInterRegList);
                holder.tv_TypeNo = (TextView) convertView.findViewById(R.id.tvTypeNo4ItemTradeMarkInterRegList);
                holder.tv_RegWay = (TextView) convertView.findViewById(R.id.tvRegWay4ItemTradeMarkInterRegList);
                holder.tv_RegCountry = (TextView) convertView.findViewById(R.id.tvCountry4ItemTradeMarkInterRegList);
                holder.tv_RegDate = (TextView) convertView.findViewById(R.id.tvRegDate4ItemTradeMarkInterRegList);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_Mumber.setText(marksList.get(position).getNumber());
            holder.tv_RegName.setText(marksList.get(position).getRegisterName());
            holder.tv_RegNo.setText(marksList.get(position).getRegisterNo());
            holder.tv_TypeNo.setText(marksList.get(position).getTypeNo());
            holder.tv_RegWay.setText(marksList.get(position).getRegisterWay());
            holder.tv_RegCountry.setText(marksList.get(position).getRegisterCountry());
            holder.tv_RegDate.setText(marksList.get(position).getRegisterDate());

            return convertView;
        }

        private class ViewHolder {
            private TextView tv_Mumber;
            private TextView tv_RegName;
            private TextView tv_RegNo;
            private TextView tv_TypeNo;
            private TextView tv_RegWay;
            private TextView tv_RegCountry;
            private TextView tv_RegDate;
        }
    }

    //商标许可信息Adapter
    public class PermitAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<TradeMarkPermitVoList> marksList;

        public PermitAdapter(Context context, List<TradeMarkPermitVoList> dataList) {
            this.adpterContext = context;
            inflater = (LayoutInflater) adpterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            marksList = dataList;
        }

        @Override
        public int getCount() { return marksList.size();}

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
                convertView = inflater.inflate(R.layout.item_trade_mark_permit_list, parent, false);
                holder.tv_permitNum = (TextView) convertView.findViewById(R.id.tvPermitNumber4ItemTradeMarkPermitList);
                holder.tv_permitName = (TextView) convertView.findViewById(R.id.tvName4ItemTradeMarkPermitList);
                holder.tv_permitType = (TextView) convertView.findViewById(R.id.tvType4ItemTradeMarkPermitList);
                holder.tv_permitGoods = (TextView) convertView.findViewById(R.id.tvGoods4ItemTradeMarkPermitList);
                holder.tv_permitStartDate = (TextView) convertView.findViewById(R.id.tvStartDate4ItemTradeMarkPermitList);
                holder.tv_permitEndDate = (TextView) convertView.findViewById(R.id.tvEndDate4ItemTradeMarkPermitList);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_permitNum.setText(marksList.get(position).getPermitNumber());
            holder.tv_permitName.setText(marksList.get(position).getPermitName());
            holder.tv_permitType.setText(marksList.get(position).getType());
            holder.tv_permitGoods.setText(marksList.get(position).getGoods());
            holder.tv_permitStartDate.setText(marksList.get(position).getStartDate());
            holder.tv_permitEndDate.setText(marksList.get(position).getEndDate());

            return convertView;
        }

        private class ViewHolder {
            private TextView tv_permitNum;
            private TextView tv_permitName;
            private TextView tv_permitType;
            private TextView tv_permitGoods;
            private TextView tv_permitStartDate;
            private TextView tv_permitEndDate;
        }
    }

    //商标联络人信息Adapter
    public class ContactoreAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<TradeMarkContactorVoList> marksList;

        public ContactoreAdapter(Context context, List<TradeMarkContactorVoList> dataList) {
            this.adpterContext = context;
            inflater = (LayoutInflater) adpterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            marksList = dataList;
        }

        @Override
        public int getCount() { return marksList.size();}

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
                convertView = inflater.inflate(R.layout.item_trade_mark_contactor_list, parent, false);
                holder.tv_contactorNum = (TextView) convertView.findViewById(R.id.tvContactorNumber4ItemTradeMarkContactorList);
                holder.tv_Name = (TextView) convertView.findViewById(R.id.tvName4ItemTradeMarkContactorList);
                holder.tv_Telephone = (TextView) convertView.findViewById(R.id.tvTelephone4ItemTradeMarkContactorList);
                holder.tv_Mobile = (TextView) convertView.findViewById(R.id.tvMobile4ItemTradeMarkContactorList);
                holder.tv_Address = (TextView) convertView.findViewById(R.id.tvAddress4ItemTradeMarkContactorList);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_contactorNum.setText(marksList.get(position).getContactorNumber());
            holder.tv_Name.setText(marksList.get(position).getName());
            holder.tv_Telephone.setText(marksList.get(position).getTelephone());
            holder.tv_Mobile.setText(marksList.get(position).getMobile());
            holder.tv_Address.setText(marksList.get(position).getAddress());
            return convertView;
        }

        private class ViewHolder {
            private TextView tv_contactorNum;
            private TextView tv_Name;
            private TextView tv_Telephone;
            private TextView tv_Mobile;
            private TextView tv_Address;
        }
    }

    //商标质押信息Adapter
    public class PledgeAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context adpterContext;
        private List<TradeMarkPledgeVoList> marksList;

        public PledgeAdapter(Context context, List<TradeMarkPledgeVoList> dataList) {
            this.adpterContext = context;
            inflater = (LayoutInflater) adpterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            marksList = dataList;
        }

        @Override
        public int getCount() { return marksList.size();}

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
                convertView = inflater.inflate(R.layout.item_trade_mark_pledge_list, parent, false);
                holder.tv_pledgor = (TextView) convertView.findViewById(R.id.tvPledgor4ItemTradeMarkPledgeList);
                holder.tv_pawnee = (TextView) convertView.findViewById(R.id.tvPawnee4ItemTradeMarkPledgeList);
                holder.tv_reason = (TextView) convertView.findViewById(R.id.tvReason4ItemTradeMarkPledgeList);
                holder.tv_limitTime = (TextView) convertView.findViewById(R.id.tvTime4ItemTradeMarkPledgeList);
                holder.tv_notifyDate = (TextView) convertView.findViewById(R.id.tvNotifyDate4ItemTradeMarkPledgeList);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_pledgor.setText(marksList.get(position).getPledgor());
            holder.tv_pawnee.setText(marksList.get(position).getPawnee());
            holder.tv_reason.setText(marksList.get(position).getReason());
            holder.tv_limitTime.setText(marksList.get(position).getPledgeTime());
            holder.tv_notifyDate.setText(marksList.get(position).getNotifyDate());
            return convertView;
        }

        private class ViewHolder {
            private TextView tv_pledgor;
            private TextView tv_pawnee;
            private TextView tv_reason;
            private TextView tv_limitTime;
            private TextView tv_notifyDate;
        }
    }


    /**
     114      * 当ListView外层有ScrollView时，需要动态设置ListView高度
     115      * @param listView
     116      */
    protected void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
