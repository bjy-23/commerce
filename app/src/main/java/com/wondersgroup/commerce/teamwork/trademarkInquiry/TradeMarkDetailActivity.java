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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.TradeMarkDetail;
import com.wondersgroup.commerce.model.TradeMarkDetailObjectListBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.widget.MyProgressDialog;

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
 * Created by admin on 2017/7/26.
 */

public class TradeMarkDetailActivity extends AppCompatActivity {
    @BindView(R.id.mid_toolbar)Toolbar toolbar;
    @BindView(R.id.toolbar_title)TextView tvTitle;
    @BindView(R.id.ivBaseInfo4TradeMarkDetail)ImageView ivArrowBaseInfo;    //商标基本信息
    @BindView(R.id.llbaseInfo4TradeMarkDetail)LinearLayout llViewBaseInfo;  //商标基本信息
    @BindView(R.id.ivEntityInfo4TradeMarkDetail)ImageView ivArrowEntityInfo;    //商标主体信息
    @BindView(R.id.llType12EntityInfo4TradeMarkDetail)LinearLayout llViewEntityInfoType12;  //商标主体信息(registerType (01企业，02个体))
    @BindView(R.id.llType345EntityInfo4TradeMarkDetail)LinearLayout llViewEntityInfoType345;  //商标主体信息(registerType (03':'事业单位','04':'社会组织','05':'自然人))
    @BindView(R.id.llTypeNullEntityInfo4TradeMarkDetail)LinearLayout llViewEntityInfoTypeNull;  //商标主体信息(registerType null)
    @BindView(R.id.ivApplierInfo4TradeMarkDetail)ImageView ivArrowApplierInfo;    //商标申请人信息
    @BindView(R.id.llApplierInfo4TradeMarkDetail)LinearLayout llViewApplierInfo;  //商标申请人信息
    @BindView(R.id.ivAgent4TradeMarkDetail)ImageView ivArrowAgentInfo;    //商标代理人信息
    @BindView(R.id.llAgentInfo4TradeMarkDetail)LinearLayout llViewAgentInfo;  //商标代理人信息
    @BindView(R.id.ivPriority4TradeMarkDetail)ImageView ivArrowPriorityInfo;    //商标优先权信息
    @BindView(R.id.llPriorityInfo4TradeMarkDetail)LinearLayout llViewPriorityInfo;  //商标优先权信息
    @BindView(R.id.ivOtherInfo4TradeMarkDetail)ImageView ivArrowOtherInfo;    //商标其他信息
    @BindView(R.id.llOtherInfo4TradeMarkDetail)LinearLayout llViewOtherInfo;  //商标其他信息
    @BindView(R.id.ivGoodsService4TradeMarkDetail)ImageView ivGoodsServiceInfo;    //商品服务信息
    @BindView(R.id.lvGoodsService4TradeMarkDetail)ListView lvGoodsService;                //商品服务信息
    @BindView(R.id.ivCommonOwner4TradeMarkDetail)ImageView ivArrowCommonOwnerInfo;    //商标共有人信息
    @BindView(R.id.lvCommonOwner4TradeMarkDetail)ListView lvCommonOwner;                //商标共有人信息
    @BindView(R.id.ivInterReg4TradeMarkDetail)ImageView ivArrowInterRegInfo;    //商标国际注册信息
    @BindView(R.id.lvInterReg4TradeMarkDetail)ListView lvInterReg;                //商标国际注册信息
    @BindView(R.id.ivPermit4TradeMarkDetail)ImageView ivArrowPermitInfo;    //商标许可信息
    @BindView(R.id.lvPermit4TradeMarkDetail)ListView lvPermit;                //商标许可信息
    @BindView(R.id.ivContactor4TradeMarkDetail)ImageView ivArrowContactorInfo;    //商标联络人信息
    @BindView(R.id.lvContactor4TradeMarkDetail)ListView lvContactor;                //商标联络人信息
    @BindView(R.id.ivPledge4TradeMarkDetail)ImageView ivArrowPledgeInfo;    //商标质押信息
    @BindView(R.id.lvPledge4TradeMarkDetail)ListView lvPledge;                //商标质押信息

    @BindView(R.id.tvTradeName4TradeMarkDetail)TextView tvTradeName4TradeMarkDetail;  //商标基本信息--商标名称
    @BindView(R.id.tvRegisterNo4TradeMarkDetail)TextView tvRegisterNo4TradeMarkDetail;  //商标基本信息--商标注册证号
    @BindView(R.id.tvType4TradeMarkDetail)TextView tvType4TradeMarkDetail;  //商标基本信息--商标类别
    @BindView(R.id.tvComTrade4TradeMarkDetail)TextView tvComTrade4TradeMarkDetail;  //商标基本信息--是否共有商标
    @BindView(R.id.tvGeoGraphyTrade4TradeMarkDetail)TextView tvGeoGraphyTrade4TradeMarkDetail;  //商标基本信息--是否地理标志商标
    @BindView(R.id.tvFamousTrade4TradeMarkDetail)TextView tvFamousTrade4TradeMarkDetail;  //商标基本信息--是否知名著名商标
    @BindView(R.id.tvExameNotifyNo4TradeMarkDetail)TextView tvExameNotifyNo4TradeMarkDetail;  //商标基本信息--初审公告期号
    @BindView(R.id.tvRegisterNotifyNo4TradeMarkDetail)TextView tvRegisterNotifyNo4TradeMarkDetail;  //商标基本信息--注册公告期号
    @BindView(R.id.tvApplyDate4TradeMarkDetail)TextView tvApplyDate4TradeMarkDetail;  //商标基本信息--申请日期
    @BindView(R.id.tvColorSet4TradeMarkDetail)TextView tvColorSet4TradeMarkDetail;  //商标基本信息--指定颜色说明
    @BindView(R.id.tvGiveUpAuthority4TradeMarkDetail)TextView tvGiveUpAuthority4TradeMarkDetail;  //商标基本信息--放弃专用权说明
    @BindView(R.id.tvTransLateName4TradeMarkDetail)TextView tvTransLateName4TradeMarkDetail;  //商标基本信息--商标名称意译
    @BindView(R.id.tvClassic4TradeMarkDetail)TextView tvClassic4TradeMarkDetail;  //商标基本信息--商标类型
    @BindView(R.id.tvFormType4TradeMarkDetail)TextView tvFormType4TradeMarkDetail;  //商标基本信息--商标形式类型
    @BindView(R.id.tv3DTrade4TradeMarkDetail)TextView tv3DTrade4TradeMarkDetail;  //商标基本信息--是否立体商标
    @BindView(R.id.tvTradestate4TradeMarkDetail)TextView tvTradestate4TradeMarkDetail;  //商标基本信息--商标状态
    @BindView(R.id.tvWellKnownTrade4TradeMarkDetail)TextView tvWellKnownTrade4TradeMarkDetail;  //商标基本信息--是否驰名商标
    @BindView(R.id.tvExameNotifyDate4TradeMarkDetail)TextView tvExameNotifyDate4TradeMarkDetail;  //商标基本信息--初审公告日期
    @BindView(R.id.tvRegisterNotifyDate4TradeMarkDetail)TextView tvRegisterNotifyDate4TradeMarkDetail;  //商标基本信息--注册公告日期
    @BindView(R.id.tvPrivateRightDate4TradeMarkDetail)TextView tvPrivateRightDate4TradeMarkDetail;  //商标基本信息--专用权有效期
    @BindView(R.id.tvColorNum4TradeMarkDetail)TextView tvColorNum4TradeMarkDetail;  //商标基本信息--组合颜色数量
    @BindView(R.id.tvComments4TradeMarkDetail)TextView tvComments4TradeMarkDetail;  //商标基本信息--备注
    @BindView(R.id.tvChineseName4TradeMarkApplier)TextView tvChineseName4TradeMarkApplier;  //商标申请人信息--中文名称
    @BindView(R.id.tvEnglishName4TradeMarkApplier)TextView tvEnglishName4TradeMarkApplier;  //商标申请人信息--外文名称
    @BindView(R.id.tvCountary4TradeMarkApplier)TextView tvCountary4TradeMarkApplier;  //商标申请人信息--国家
    @BindView(R.id.tvChineseAddress4TradeMarkApplier)TextView tvChineseAddress4TradeMarkApplier;  //商标申请人信息--中文地址
    @BindView(R.id.tvEnglishAddress4TradeMarkApplier)TextView tvEnglishAddress4TradeMarkApplier;  //商标申请人信息--外文地址
    @BindView(R.id.tvDistrict4TradeMarkApplier)TextView tvDistrict4TradeMarkApplier;  //商标申请人信息--行政区划
    @BindView(R.id.tvEntityType4TradeMarkEntityType12)TextView tvEntityType4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--主体类型
    @BindView(R.id.tvCompanyName4TradeMarkEntityType12)TextView tvCompanyName4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--企业/个体名称
    @BindView(R.id.tvRegisterNo4TradeMarkEntityType12)TextView tvRegisterNo4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--注册号
    @BindView(R.id.tvCreditCode4TradeMarkEntityType12)TextView tvCreditCode4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--统一社会信用代码
    @BindView(R.id.tvContactor4TradeMarkEntityType12)TextView tvContactor4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--联系人
    @BindView(R.id.tvTel4TradeMarkEntityType12)TextView tvTel4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--联系电话
    @BindView(R.id.tvAddress4TradeMarkEntityType12)TextView tvAddress4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--联系地址
    @BindView(R.id.tvPostCode4TradeMarkEntityType12)TextView tvPostCode4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--邮编
    @BindView(R.id.tvRegisterDepartment4TradeMarkEntityType12)TextView tvRegisterDepartment4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--登记机关
    @BindView(R.id.tvCompanyState4TradeMarkEntityType12)TextView tvCompanyState4TradeMarkEntityType12;     //商标主体信息(主体类型01,02)--企业状态
    @BindView(R.id.tvEntityType4TradeMarkEntityType345)TextView tvEntityType4TradeMarkEntityType345;     //商标主体信息(主体类型03,04,05)--主体类型
    @BindView(R.id.tvOrganizeCode4TradeMarkEntityType345)TextView tvOrganizeCode4TradeMarkEntityType345;     //商标主体信息(主体类型03,04,05)--组织机构代码
    @BindView(R.id.tvTel4TradeMarkEntityType345)TextView tvTel4TradeMarkEntityType345;     //商标主体信息(主体类型03,04,05)--联系电话
    @BindView(R.id.tvPostCode4TradeMarkEntityType345)TextView tvPostCode4TradeMarkEntityType345;     //商标主体信息(主体类型03,04,05)--邮编
    @BindView(R.id.tvCompanyName4TradeMarkEntityType345)TextView tvCompanyName4TradeMarkEntityType345;     //商标主体信息(主体类型03,04,05)--单位名称
    @BindView(R.id.tvContactor4TradeMarkEntityType345)TextView tvContactor4TradeMarkEntityType345;     //商标主体信息(主体类型03,04,05)--联系人
    @BindView(R.id.tvAddress4TradeMarkEntityType345)TextView tvAddress4TradeMarkEntityType345;     //商标主体信息(主体类型03,04,05)--联系地址
    @BindView(R.id.tvTabCompanyName4TradeMarkEntityType345)TextView tvTabCompanyName4TradeMarkEntityType345;     //商标主体信息(主体类型03,04,05)--单位名称tabName
    @BindView(R.id.tvTabOrganizeCode4TradeMarkEntityType345)TextView tvTabOrganizeCode4TradeMarkEntityType345;     //商标主体信息(主体类型03,04,05)--组织机构代码tabName
    @BindView(R.id.tvObligeeName4TradeMarkEntityType5)TextView tvObligeeName4TradeMarkEntityType5;      //商标主体信息(主体类型null)--权利人名称
    @BindView(R.id.tvChineseName4TradeMarkAgent)TextView tvChineseName4TradeMarkAgent;  //商标代理人信息--中文名称
    @BindView(R.id.tvEnglishName4TradeMarkAgent)TextView tvEnglishName4TradeMarkAgent;  //商标代理人信息--外文名称
    @BindView(R.id.tvContactor4TradeMarkAgent)TextView tvContactor4TradeMarkAgent;  //商标代理人信息--联系人
    @BindView(R.id.tvAddress4TradeMarkAgent)TextView tvAddress4TradeMarkAgent;  //商标代理人信息--地址
    @BindView(R.id.tvMobile4TradeMarkAgent)TextView tvMobile4TradeMarkAgent;  //商标代理人信息--手机号码
    @BindView(R.id.tvEMail4TradeMarkAgent)TextView tvEMail4TradeMarkAgent;  //商标代理人信息--E_Mail
    @BindView(R.id.tvEconemyState4TradeMarkAgent)TextView tvEconemyState4TradeMarkAgent;  //商标代理人信息--经济性质
    @BindView(R.id.tvCreditCode4TradeMarkAgent)TextView tvCreditCode4TradeMarkAgent;  //商标代理人信息--证件编码
    @BindView(R.id.tvState4TradeMarkAgent)TextView tvState4TradeMarkAgent;  //商标代理人信息--代理人状态
    @BindView(R.id.tvChineseAddress4TradeMarkAgent)TextView tvChineseAddress4TradeMarkAgent;  //商标代理人信息--中文地址
    @BindView(R.id.tvEnglishAddress4TradeMarkAgent)TextView tvEnglishAddress4TradeMarkAgent;  //商标代理人信息--外文地址
    @BindView(R.id.tvPostCode4TradeMarkAgent)TextView tvPostCode4TradeMarkAgent;  //商标代理人信息--邮编
    @BindView(R.id.tvTelephone4TradeMarkAgent)TextView tvTelephone4TradeMarkAgent;  //商标代理人信息--电话
    @BindView(R.id.tvFex4TradeMarkAgent)TextView tvFex4TradeMarkAgent;  //商标代理人信息--传真
    @BindView(R.id.tvAgentStates4TradeMarkAgent)TextView tvAgentStates4TradeMarkAgent;  //商标代理人信息--代理人类型
    @BindView(R.id.tvCertificateName4TradeMarkAgent)TextView tvCertificateName4TradeMarkAgent;  //商标代理人信息--证件名称
    @BindView(R.id.tvDistrict4TradeMarkAgent)TextView tvDistrict4TradeMarkAgent;  //商标代理人信息--行政划区
    @BindView(R.id.tvComment4TradeMarkAgent)TextView tvComment4TradeMarkAgent;  //商标代理人信息--备注
    @BindView(R.id.tvDate4TradeMarkPriority)TextView tvDate4TradeMarkPriority;    //商标优先权信息--优先权日期
    @BindView(R.id.tvGoods4TradeMarkPriority)TextView tvGoods4TradeMarkPriority;    //商标优先权信息--优先权商品
    @BindView(R.id.tvType4TradeMarkPriority)TextView tvType4TradeMarkPriority;    //商标优先权信息--优先权种类
    @BindView(R.id.tvCountry4TradeMarkPriority)TextView tvCountry4TradeMarkPriority;    //商标优先权信息--优先权国家
    @BindView(R.id.tvIsValide4TradeMarkPriority)TextView tvIsValide4TradeMarkPriority;    //商标优先权信息--是否有效
    @BindView(R.id.tvIsFamousMark4TradeMarkOtherInfo)TextView tvIsFamousMark4TradeMarkOtherInfo;    //商标其他信息--是否名牌产品
    @BindView(R.id.tvIsNewGoods4TradeMarkOtherInfo)TextView tvIsNewGoods4TradeMarkOtherInfo;    //商标其他信息--是否地标产品
    @BindView(R.id.tvIsHighTech4TradeMarkOtherInfo)TextView tvIsHighTech4TradeMarkOtherInfo;    //商标其他信息--是否高新技术企业
    @BindView(R.id.tvIsOldMark4TradeMarkOtherInfo)TextView tvIsOldMark4TradeMarkOtherInfo;    //商标其他信息--是否中华老字号
    @BindView(R.id.tvIsProvinceMark4TradeMarkOtherInfo)TextView tvIsProvinceMark4TradeMarkOtherInfo;    //商标其他信息--是否**省/市老字号
    @BindView(R.id.tvIsZoneCompany4TradeMarkOtherInfo)TextView tvIsZoneCompany4TradeMarkOtherInfo;    //商标其他信息--是否园区企业
    @BindView(R.id.tvFamousMarkDate4TradeMarkOtherInfo)TextView tvFamousMarkDate4TradeMarkOtherInfo;    //商标其他信息--获得年份
    @BindView(R.id.tvGetLandMarkDate4TradeMarkOtherInfo)TextView tvGetLandMarkDate4TradeMarkOtherInfo;    //商标其他信息--获得年份
    @BindView(R.id.tvGetHighTec4TradeMarkOtherInfo)TextView tvGetHighTec4TradeMarkOtherInfo;    //商标其他信息--获得年份
    @BindView(R.id.tvGetOldMarkDate4TradeMarkOtherInfo)TextView tvGetOldMarkDate4TradeMarkOtherInfo;    //商标其他信息--获得年份
    @BindView(R.id.tvGetProvinceMarkDate4TradeMarkOtherInfo)TextView tvGetProvinceMarkDate4TradeMarkOtherInfo;    //商标其他信息--获得年份
    @BindView(R.id.tvZoneName4TradeMarkOtherInfo)TextView tvZoneName4TradeMarkOtherInfo;    //商标其他信息--园区名称
    @BindView(R.id.tvComments4TradeMarkOtherInfo)TextView tvComments4TradeMarkOtherInfo;    //商标其他信息--备注

    @BindView(R.id.llBaseInfoTitle4TradeMarkDetail)LinearLayout llBaseInfoTitle4TradeMarkDetail;//商标基本信息
    @BindView(R.id.llApplierInfoTitle4TradeMarkDetail)LinearLayout llApplierInfoTitle4TradeMarkDetail;//商标申请人信息
    @BindView(R.id.llEntityInfoTitle4TradeMarkDetail)LinearLayout llEntityInfoTitle4TradeMarkDetail;//商标主体信息
    @BindView(R.id.llAgentTitle4TradeMarkDetail)LinearLayout llAgentTitle4TradeMarkDetail;//商标代理人信息
    @BindView(R.id.llPriorityTitle4TradeMarkDetail)LinearLayout llPriorityTitle4TradeMarkDetail;//商标优先权信息
    @BindView(R.id.llOtherInfoTitle4TradeMarkDetail)LinearLayout llOtherInfoTitle4TradeMarkDetail;//商标其他信息
    @BindView(R.id.llPledgeTitle4TradeMarkDetail)LinearLayout llPledgeTitle4TradeMarkDetail;//商标质押信息
    @BindView(R.id.llGoodsServiceTitle4TradeMarkDetail)LinearLayout llGoodsServiceTitle4TradeMarkDetail;//商标商品服务信息
    @BindView(R.id.llCommonOwnerTitle4TradeMarkDetail)LinearLayout llCommonOwnerTitle4TradeMarkDetail;//商标共有人信息
    @BindView(R.id.llInterRegTitle4TradeMarkDetail)LinearLayout llInterRegTitle4TradeMarkDetail;//国际注册信息
    @BindView(R.id.llPermitTitle4TradeMarkDetail)LinearLayout llPermitTitle4TradeMarkDetail;//商标许可信息
    @BindView(R.id.llContactor4TradeMarkDetail)LinearLayout llContactor4TradeMarkDetail;//商标联络人信息

    private List<ProductService> goodsServiceList;     //商品服务信息列表
    private GoodsServiceAdapter goodsServiceAdapter;
    private List<Commmer> commonOwnerList;             //商标共有人信息列表
    private CommoonOwnerAdapter commoonOwnerAdapter;
    private List<RegInternational> interRegisterList;             //商标国际注册信息列表
    private InterRegisterAdapter interRegisterAdapter;
    private List<BrandPermit> permitInfoList;             //商标许可信息列表
    private PermitAdapter permitAdapter;
    private List<ContactPerson> contactorsList;             //商标联络人信息列表
    private ContactoreAdapter contactoreAdapter;
    private List<Pledge> pledgesList;             //商标质押信息列表
    private PledgeAdapter pledgeAdapter;
    private TradeMarkDetail tradeMarkDetail;
    private Gson gson = new Gson();
    private String strRegisterType = null;


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
        if(getIntent().hasExtra("tradeMarkId"))
            getDetail(getIntent().getStringExtra("tradeMarkId"));
        else
            Toast.makeText(TradeMarkDetailActivity.this, "无法获取注册商标详情", Toast.LENGTH_SHORT).show();
    }

    private void getDetail(String tradeMarkId) {
        TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
        String userId = loginBean.getResult().getUserId();
        String organId = loginBean.getResult().getOrganId();
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("tmId", tradeMarkId);//商标id
        map.put("organId", organId);
        map.put("wsCodeReq", "03019991");

        Call<TradeMarkDetailObjectListBean> call;
        call = ApiManager.tradeMarkApi.getTradeMarkDetail(map);

        call.enqueue(new Callback<TradeMarkDetailObjectListBean>() {
            @Override
            public void onResponse(Response<TradeMarkDetailObjectListBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    TradeMarkDetailObjectListBean markDetail = response.body();

                    if ((null == markDetail) || (null == markDetail.getResult())) {
                        Toast.makeText(TradeMarkDetailActivity.this, "获得商标详情数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (null != markDetail.getResult()) {
                        for(int i=0; i<markDetail.getResult().size(); i++){
                            String name = markDetail.getResult().get(i).getName();
                            String type = markDetail.getResult().get(i).getType();
                            List<JsonElement> jsonElements = markDetail.getResult().get(i).getValue();
                            parseObject(type, jsonElements);
                        }
                        initView();
                    }

                } else {
                    Toast.makeText(TradeMarkDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(TradeMarkDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //解析json数据
    private void parseObject(String type, List<JsonElement> elementsList) {
        if(elementsList == null || elementsList.get(0) == null)
            return;
        try{
            if(type.equals("tmRegInfo")){//商标基本信息,//商标申请人信息
                Basic basic = gson.fromJson(elementsList.get(0),Basic.class);
                initBasicView(basic);//商标基本信息界面初始化
                initApplierView(basic);//商标申请人信息界面初始化
                llBaseInfoTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
                llApplierInfoTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
            }else if(type.equals("tmRegister")){//商标主体信息
                Entity entity = gson.fromJson(elementsList.get(0), Entity.class);
                if(entity.getRegisterType() == null){//registerType (01企业，02个体)	主体类型
                    return;
                }else if("".equals(entity.getRegisterType())){
                    if(entity.getRegisterName()!=null)
                        tvObligeeName4TradeMarkEntityType5.setText(entity.getRegisterName());//商标主体信息(主体类型null)--权利人名称
                }else if(("01").equals(entity.getRegisterType()) || ("02").equals(entity.getRegisterType())){//商标主体信息(主体类型01,02)，(01企业，02个体)
                    initMainEntityType12View(entity);
                }else if(("03").equals(entity.getRegisterType()) || ("04").equals(entity.getRegisterType()) || ("05").equals(entity.getRegisterType())){
                    initMainEntityType345View(entity);//商标主体信息(主体类型03,04,05)，03"事业单位"，04"社会组织"，05自然人
                }
                strRegisterType = entity.getRegisterType();
                llEntityInfoTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
            }else if(type.equals("agent")){//商标代理人信息
                Agent agent = gson.fromJson(elementsList.get(0), Agent.class);
                initAgentView(agent);
                llAgentTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
            }else if(type.equals("tmPriorityInfo")){//商标优先权信息
                Priority tmPriorityInfo = gson.fromJson(elementsList.get(0), Priority.class);
                initPriorityView(tmPriorityInfo);
                llPriorityTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
            }else if(type.equals("tmOther")){//商标其他信息
                Other tmOther = gson.fromJson(elementsList.get(0), Other.class);
                initOthersInfoView(tmOther);
                llOtherInfoTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
            }else if(type.equals("tmPledgeList")){//商标质押信息
                llPledgeTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
                if(elementsList!=null && elementsList.size()>0){
                    pledgesList = new ArrayList<Pledge>();
                    for(int i=0; i<elementsList.size(); i++){
                        Pledge pledge = gson.fromJson(elementsList.get(i), Pledge.class);
                        pledgesList.add(pledge);
                    }
                    pledgeAdapter = new PledgeAdapter(TradeMarkDetailActivity.this, pledgesList);
                    lvPledge.setAdapter(pledgeAdapter);
                }
            }else if(type.equals("tmProductList")){//商标商品服务信息
                llGoodsServiceTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
                if(elementsList!=null && elementsList.size()>0){
                    goodsServiceList = new ArrayList<ProductService>();
                    for(int i=0; i<elementsList.size(); i++){
                        ProductService productService = gson.fromJson(elementsList.get(i), ProductService.class);
                        goodsServiceList.add(productService);
                    }
                    goodsServiceAdapter = new GoodsServiceAdapter(TradeMarkDetailActivity.this, goodsServiceList);
                    lvGoodsService.setAdapter(goodsServiceAdapter);
                }

            }else if(type.equals("commonerList")){//商标共有人信息
                llCommonOwnerTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
                if(elementsList!=null && elementsList.size()>0){
                    commonOwnerList = new ArrayList<Commmer>();
                    for(int i=0; i<elementsList.size(); i++){
                        Commmer commmer = gson.fromJson(elementsList.get(i), Commmer.class);
                        commonOwnerList.add(commmer);
                    }
                    commoonOwnerAdapter = new CommoonOwnerAdapter(TradeMarkDetailActivity.this, commonOwnerList);
                    lvCommonOwner.setAdapter(commoonOwnerAdapter);
                }

            }else if(type.equals("tmRegInternationalInfoList")){//国际注册信息
                llInterRegTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
                if(elementsList!=null && elementsList.size()>0){
                    interRegisterList = new ArrayList<RegInternational>();
                    for(int i=0; i<elementsList.size(); i++){
                        RegInternational regInternational = gson.fromJson(elementsList.get(i), RegInternational.class);
                        interRegisterList.add(regInternational);
                    }
                    interRegisterAdapter = new InterRegisterAdapter(TradeMarkDetailActivity.this, interRegisterList);
                    lvInterReg.setAdapter(interRegisterAdapter);
                }

            }else if(type.equals("permitList")){//商标许可信息
                llPermitTitle4TradeMarkDetail.setVisibility(View.VISIBLE);
                if(elementsList!=null && elementsList.size()>0){
                    permitInfoList = new ArrayList<BrandPermit>();
                    for(int i=0; i<elementsList.size(); i++){
                        BrandPermit brandPermit = gson.fromJson(elementsList.get(i), BrandPermit.class);
                        permitInfoList.add(brandPermit);
                    }
                    permitAdapter = new PermitAdapter(TradeMarkDetailActivity.this, permitInfoList);
                    lvPermit.setAdapter(permitAdapter);
                }

            }else if(type.equals("contactPersonList")){//商标联络人信息
                llContactor4TradeMarkDetail.setVisibility(View.VISIBLE);
                if(elementsList!=null && elementsList.size()>0){
                    contactorsList = new ArrayList<ContactPerson>();
                    for(int i=0; i<elementsList.size(); i++){
                        ContactPerson contactPerson = gson.fromJson(elementsList.get(i), ContactPerson.class);
                        contactorsList.add(contactPerson);
                    }
                    contactoreAdapter = new ContactoreAdapter(TradeMarkDetailActivity.this, contactorsList);
                    lvContactor.setAdapter(contactoreAdapter);
                }
            }
        }catch (Exception t){
            t.printStackTrace();
        }
    }

    //商标基本信息界面初始化
    private void initBasicView(Basic data){
        if(data.getBrandName()!=null)
            tvTradeName4TradeMarkDetail.setText(data.getBrandName());
        if(data.getRegNo()!=null)
            tvRegisterNo4TradeMarkDetail.setText(data.getRegNo());
        if(data.getClassId()!=null)
            tvType4TradeMarkDetail.setText(data.getClassId());
        if(data.getIsCommon()!=null)
            tvComTrade4TradeMarkDetail.setText(getBooleanValue(data.getIsCommon()));
        if(data.getGeographyRemark()!=null)
            tvGeoGraphyTrade4TradeMarkDetail.setText(getBooleanValue(data.getGeographyRemark()));
        if(data.getFamousId()!=null && !data.getFamousId().equals(""))
            tvFamousTrade4TradeMarkDetail.setText("是");
        else
            tvFamousTrade4TradeMarkDetail.setText("否");
        if(data.getFirstTrailIssue()!=null)
            tvExameNotifyNo4TradeMarkDetail.setText(data.getFirstTrailIssue());
        if(data.getRegIssue()!=null)
            tvRegisterNotifyNo4TradeMarkDetail.setText(data.getRegIssue());
        if(data.getAppDate()!=null)
            tvApplyDate4TradeMarkDetail.setText(data.getAppDate());
        if(data.getColor()!=null){
            if(data.getColor().equals("0"))
                tvColorSet4TradeMarkDetail.setText("0");
            else
                tvColorSet4TradeMarkDetail.setText(data.getColor());
        }
        if(data.getProtect()!=null)
            tvGiveUpAuthority4TradeMarkDetail.setText(data.getProtect());
        if(data.getBrandNameTrans()!=null)
            tvTransLateName4TradeMarkDetail.setText(data.getBrandNameTrans());
        if(data.getTmType()!=null)
            tvClassic4TradeMarkDetail.setText(data.getTmType());
        if(data.getFormType()!=null)
            tvFormType4TradeMarkDetail.setText(data.getFormType());
        if(data.getSolidRemark()!=null)
            tv3DTrade4TradeMarkDetail.setText(getBooleanValue(data.getSolidRemark()));
        if(data.getExistStatus()!=null)
            tvTradestate4TradeMarkDetail.setText(data.getExistStatus());
        if(data.getReputedRemark()!=null)
            tvWellKnownTrade4TradeMarkDetail.setText(getBooleanValue(data.getReputedRemark()));
        if(data.getFirstIssueDate()!=null)
            tvExameNotifyDate4TradeMarkDetail.setText(data.getFirstIssueDate());
        if(data.getRegAppDate()!=null)
            tvRegisterNotifyDate4TradeMarkDetail.setText(data.getRegAppDate());
        if(data.getSpecialStartDate()!=null && data.getSpecialEndDate()!=null)
            tvPrivateRightDate4TradeMarkDetail.setText(data.getSpecialStartDate()+" 至 "+data.getSpecialEndDate());
        if(data.getColorNum()!=null)
            tvColorNum4TradeMarkDetail.setText(data.getColorNum());
        if(data.getMemo()!=null)
            tvComments4TradeMarkDetail.setText(data.getMemo());
    }

    //商标主体信息界面初始化
    private void initMainEntityType12View(Entity data){
        if(data.getRegisterType()!=null)
            tvEntityType4TradeMarkEntityType12.setText(data.getRegisterType().equals("01")?"企业":"个体工商户");
        if(data.getRegisterName()!=null)
            tvCompanyName4TradeMarkEntityType12.setText(data.getRegisterName());
        if(data.getRegisterRegNo()!=null)
            tvRegisterNo4TradeMarkEntityType12.setText(data.getRegisterRegNo());
        if(data.getRegisterUniScid()!=null)
            tvCreditCode4TradeMarkEntityType12.setText(data.getRegisterUniScid());
        if(data.getName()!=null)
            tvContactor4TradeMarkEntityType12.setText(data.getName());
        if(data.getPhone()!=null)
            tvTel4TradeMarkEntityType12.setText(data.getPhone());
        if(data.getAddress()!=null)
            tvAddress4TradeMarkEntityType12.setText(data.getAddress());
        if(data.getPostcode()!=null)
            tvPostCode4TradeMarkEntityType12.setText(data.getPostcode());
        if(data.getRegOrganId()!=null)
            tvRegisterDepartment4TradeMarkEntityType12.setText(data.getRegOrganId());
        if(data.getExistStatus()!=null)
            tvCompanyState4TradeMarkEntityType12.setText(data.getExistStatus());
    }

    private void initMainEntityType345View(Entity data){

        if(data.getRegisterType().equals("05")){
            tvTabOrganizeCode4TradeMarkEntityType345.setVisibility(View.GONE);
            tvOrganizeCode4TradeMarkEntityType345.setVisibility(View.GONE);
            tvTabCompanyName4TradeMarkEntityType345.setText("姓名");
            tvEntityType4TradeMarkEntityType345.setText("自然人");
        }else{//registerType 03,04
            tvEntityType4TradeMarkEntityType345.setText(data.getRegisterType().equals("03")?"事业单位":"社会组织");
            if(data.getOrganizationCode()!=null)
                tvOrganizeCode4TradeMarkEntityType345.setText(data.getOrganizationCode());
        }
        if(data.getRegisterName()!=null)
            tvCompanyName4TradeMarkEntityType345.setText(data.getRegisterName());

        if(data.getPhone()!=null)
            tvTel4TradeMarkEntityType345.setText(data.getPhone());

        if(data.getPostcode()!=null)
            tvPostCode4TradeMarkEntityType345.setText(data.getPostcode());

        if(data.getName()!=null)
            tvContactor4TradeMarkEntityType345.setText(data.getName());

        if(data.getAddress()!=null)
            tvAddress4TradeMarkEntityType345.setText(data.getAddress());
    }

    //商标申请人信息界面初始化
    private void initApplierView(Basic data){
        if(data.getChProposer()!=null)
            tvChineseName4TradeMarkApplier.setText(data.getChProposer());
        if(data.getEnProposer()!=null)
            tvEnglishName4TradeMarkApplier.setText(data.getEnProposer());
        if(data.getNationalityName()!=null)
            tvCountary4TradeMarkApplier.setText(data.getNationalityName());
        if(data.getChProposerAddr()!=null)
            tvChineseAddress4TradeMarkApplier.setText(data.getChProposerAddr());
        if(data.getEnProposerAddr()!=null)
            tvEnglishAddress4TradeMarkApplier.setText(data.getEnProposerAddr());
        if(data.getDistrictName()!=null)
            tvDistrict4TradeMarkApplier.setText(data.getDistrictName());
    }

    //商标代理人信息界面初始化
    private void initAgentView(Agent data){

        if(data.getChName()!=null)
            tvChineseName4TradeMarkAgent.setText(data.getChName());
        if(data.getEnName()!=null)
            tvEnglishName4TradeMarkAgent.setText(data.getEnName());
        if(data.getContact()!=null)
            tvContactor4TradeMarkAgent.setText(data.getContact());
        if(data.getAddr()!=null)
            tvAddress4TradeMarkAgent.setText(data.getAddr());
        if(data.getMobile()!=null)
            tvMobile4TradeMarkAgent.setText(data.getMobile());
        if(data.getEmail()!=null)
            tvEMail4TradeMarkAgent.setText(data.getEmail());
        if(data.getEconomyType()!=null)
            tvEconemyState4TradeMarkAgent.setText(data.getEconomyType());
        if(data.getCetfNo()!=null)
            tvCreditCode4TradeMarkAgent.setText(data.getCetfNo());
        if(data.getState()!=null)
            tvState4TradeMarkAgent.setText(data.getState());
        if(data.getChAddress()!=null)
            tvChineseAddress4TradeMarkAgent.setText(data.getChAddress());
        if(data.getEnAddress()!=null)
            tvEnglishAddress4TradeMarkAgent.setText(data.getEnAddress());
        if(data.getPostalCode()!=null)
            tvPostCode4TradeMarkAgent.setText(data.getPostalCode());
        if(data.getPhone()!=null)
            tvTelephone4TradeMarkAgent.setText(data.getPhone());
        if(data.getFax()!=null)
            tvFex4TradeMarkAgent.setText(data.getFax());
        if(data.getAgentType()!=null)
            tvAgentStates4TradeMarkAgent.setText(data.getAgentType());
        if(data.getCetfName()!=null)
            tvCertificateName4TradeMarkAgent.setText(data.getCetfName());
        if(data.getDistrictName()!=null)
            tvDistrict4TradeMarkAgent.setText(data.getDistrictName());
        if(data.getMemo()!=null)
            tvComment4TradeMarkAgent.setText(data.getMemo());
    }

    //商标优先权信息界面初始化
    private void initPriorityView(Priority data){
        if(data.getPriorityDate()!=null)
            tvDate4TradeMarkPriority.setText(data.getPriorityDate());
        if(data.getPriorityGoods()!=null)
            tvGoods4TradeMarkPriority.setText(data.getPriorityGoods());
        if(data.getPriorityType()!=null)
            tvType4TradeMarkPriority.setText(data.getPriorityType());
        if(data.getPriorityCtry()!=null)
            tvCountry4TradeMarkPriority.setText(data.getPriorityCtry());
        if(data.getPriorityValidInfo()!=null)
            tvIsValide4TradeMarkPriority.setText(getBooleanValue(data.getPriorityValidInfo()));
    }

    //商标其他信息界面初始化
    private void initOthersInfoView(Other data){
        if(data.getIsMingpai()!=null)
            tvIsFamousMark4TradeMarkOtherInfo.setText(getBooleanValue(data.getIsMingpai()));
        if(data.getIsDibiao()!=null)
            tvIsNewGoods4TradeMarkOtherInfo.setText(getBooleanValue(data.getIsDibiao()));
        if(data.getIsGaoxin()!=null)
            tvIsHighTech4TradeMarkOtherInfo.setText(getBooleanValue(data.getIsGaoxin()));
        if(data.getIsZhonghua()!=null)
            tvIsOldMark4TradeMarkOtherInfo.setText(getBooleanValue(data.getIsZhonghua()));
        if(data.getIsShengshi()!=null)
            tvIsProvinceMark4TradeMarkOtherInfo.setText(getBooleanValue(data.getIsShengshi()));
        if(data.getIsYuanqu()!=null)
            tvIsZoneCompany4TradeMarkOtherInfo.setText(getBooleanValue(data.getIsYuanqu()));
        if(data.getMingpaiDate()!=null)
            tvFamousMarkDate4TradeMarkOtherInfo.setText(data.getMingpaiDate());
        if(data.getDibiaoDate()!=null)
            tvGetLandMarkDate4TradeMarkOtherInfo.setText(data.getDibiaoDate());
        if(data.getGaoxinDate()!=null)
            tvGetHighTec4TradeMarkOtherInfo.setText(data.getGaoxinDate());
        if(data.getZhonghuaDate()!=null)
            tvGetOldMarkDate4TradeMarkOtherInfo.setText(data.getZhonghuaDate());
        if(data.getShengshiDate()!=null)
            tvGetProvinceMarkDate4TradeMarkOtherInfo.setText(data.getShengshiDate());
        if(data.getYuanquName()!=null)
            tvZoneName4TradeMarkOtherInfo.setText(data.getYuanquName());
        if(data.getMemo()!=null)
            tvComments4TradeMarkOtherInfo.setText(data.getMemo());
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
                if(strRegisterType.equals("")){
                    changeView(llViewEntityInfoTypeNull, ivArrowEntityInfo);
                    return;
                }else if(("01").equals(strRegisterType) || ("02").equals(strRegisterType)){
                    changeView(llViewEntityInfoType12, ivArrowEntityInfo);
                }else if(("03").equals(strRegisterType) || ("04").equals(strRegisterType) || ("05").equals(strRegisterType)){
                    changeView(llViewEntityInfoType345, ivArrowEntityInfo);
                }
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
        private List<ProductService> marksList;

        public GoodsServiceAdapter(Context context, List<ProductService> dataList) {
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

            holder.tv_GoodsServiceNum.setText("");//序号--待定--
            holder.tv_SimilarGroup.setText(marksList.get(position).getKey()!=null?marksList.get(position).getKey():"");
            holder.tv_Goods.setText(marksList.get(position).getValue()!=null?marksList.get(position).getValue():"");

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
        private List<Commmer> marksList;
        private int itemCount = 2;

        public CommoonOwnerAdapter(Context context, List<Commmer> dataList) {
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

            holder.tv_OwnerNum.setText("");//序号--待定--
            holder.tv_OwnerChineseName.setText(marksList.get(position).getCommonerName()!=null?marksList.get(position).getCommonerName():"");
            holder.tv_OwnerEnghlishName.setText(marksList.get(position).getCommonerEnName()!=null?marksList.get(position).getCommonerEnName():"");
            holder.tv_OwnerChineseAddress.setText(marksList.get(position).getCommonerAddr()!=null?marksList.get(position).getCommonerAddr():"");
            holder.tv_OwnerEnglishAddress.setText(marksList.get(position).getCommonerEnAddr()!=null?marksList.get(position).getCommonerEnAddr():"");

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
        private List<RegInternational> marksList;

        public InterRegisterAdapter(Context context, List<RegInternational> dataList) {
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

            holder.tv_Mumber.setText("");//序号--待定--
            holder.tv_RegName.setText(marksList.get(position).getBrandName()!=null?marksList.get(position).getBrandName():"");
            holder.tv_RegNo.setText(marksList.get(position).getInternationalRegNo()!=null?marksList.get(position).getInternationalRegNo():"");
            holder.tv_TypeNo.setText(marksList.get(position).getClassId()!=null?marksList.get(position).getClassId():"");
            holder.tv_RegWay.setText(marksList.get(position).getRegType()!=null?marksList.get(position).getRegType():"");
            holder.tv_RegCountry.setText(marksList.get(position).getRegCountryCode()!=null?marksList.get(position).getRegCountryCode():"");
            holder.tv_RegDate.setText(marksList.get(position).getRegDate()!=null?marksList.get(position).getRegDate():"");

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
        private List<BrandPermit> marksList;

        public PermitAdapter(Context context, List<BrandPermit> dataList) {
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

            holder.tv_permitNum.setText("");//序号--待定--
            holder.tv_permitName.setText(marksList.get(position).getLicense()!=null?marksList.get(position).getLicense():"");
            holder.tv_permitType.setText(marksList.get(position).getPermitType()!=null?marksList.get(position).getPermitType():"");
            holder.tv_permitGoods.setText(marksList.get(position).getPermitProduct()!=null?marksList.get(position).getPermitProduct():"");
            holder.tv_permitStartDate.setText(marksList.get(position).getStartDate()!=null?marksList.get(position).getStartDate():"");
            holder.tv_permitEndDate.setText(marksList.get(position).getEndDate()!=null?marksList.get(position).getEndDate():"");

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
        private List<ContactPerson> marksList;

        public ContactoreAdapter(Context context, List<ContactPerson> dataList) {
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

            holder.tv_contactorNum.setText("");//序号--待定--
            holder.tv_Name.setText(marksList.get(position).getContactName()!=null?marksList.get(position).getContactName():"");
            holder.tv_Telephone.setText(marksList.get(position).getContactTel()!=null?marksList.get(position).getContactTel():"");
            holder.tv_Mobile.setText(marksList.get(position).getContactMobile()!=null?marksList.get(position).getContactMobile():"");
            holder.tv_Address.setText(marksList.get(position).getContactAddress()!=null?marksList.get(position).getContactAddress():"");
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
        private List<Pledge> marksList;

        public PledgeAdapter(Context context, List<Pledge> dataList) {
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

            holder.tv_pledgor.setText(marksList.get(position).getPledgor()!=null?marksList.get(position).getPledgor():"");
            holder.tv_pawnee.setText(marksList.get(position).getPledgee()!=null?marksList.get(position).getPledgee():"");
            holder.tv_reason.setText(marksList.get(position).getPledgeReason()!=null?marksList.get(position).getPledgeReason():"");
            holder.tv_limitTime.setText(marksList.get(position).getPledgePeriod()!=null?marksList.get(position).getPledgePeriod():"");
            holder.tv_notifyDate.setText(marksList.get(position).getPledgeNoticeDate()!=null?marksList.get(position).getPledgeNoticeDate():"");
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

    private String getBooleanValue(String strValue){
        if(strValue == null || strValue.equals(""))
            return "";
        else if(strValue.equals("0"))
            return "否";
        else
            return "是";
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //商标基本信息
    public class Basic{
        private String brandName;   //	商标名称
        private String brandNameTrans;   //	商标名称意译
        private String regNo;   //	商标注册证号
        private String tmType;   //	商标类型
        private String classId;   //	商标类别
        private String formType;   //	商标形式类型
        private String isCommon;   //	是否共有商标
        private String solidRemark;   //	是否立体商标
        private String geographyRemark;   //	是否地理标志商标
        private String existStatus;   //	商标状态
        private String famousId;   //	是否知名著名商标
        private String reputedRemark;   //	是否驰名商标
        //        待定	商标形式类型
        private String firstTrailIssue;   //	初审公告期号
        private String firstIssueDate;   //	初审公告日期
        private String regIssue;   //	注册公告期号
        private String regAppDate;   //	注册公告日期
        private String appDate;   //	申请日期
        private String specialStartDate;   //
        private String specialEndDate;   //	专用权有效期
        private String Color;   //（颜色组合商标/非颜色组合商标）判断改值是否为0	指定颜色说明
        private String colorNum;   //	组合颜色数量
        private String protect;   //	放弃专用权说明
        private String memo;   //	备注
        private String imageFilePath;   //	商标图片附件id
        private String enProposer;   //	外文名称
        private String enProposerAddr;   //	外文地址
        private String chProposer;  //	中文名称
        private String chProposerAddr;  //		中文地址
        private String nationalityName;  //		国家
        private String districtName;  //		行政区划

        public String getChProposer() {
            return chProposer;
        }

        public void setChProposer(String chProposer) {
            this.chProposer = chProposer;
        }

        public String getChProposerAddr() {
            return chProposerAddr;
        }

        public void setChProposerAddr(String chProposerAddr) {
            this.chProposerAddr = chProposerAddr;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getNationalityName() {
            return nationalityName;
        }

        public void setNationalityName(String nationalityName) {
            this.nationalityName = nationalityName;
        }

        public String getEnProposer() {
            return enProposer;
        }

        public void setEnProposer(String enProposer) {
            this.enProposer = enProposer;
        }

        public String getEnProposerAddr() {
            return enProposerAddr;
        }

        public void setEnProposerAddr(String enProposerAddr) {
            this.enProposerAddr = enProposerAddr;
        }

        public String getAppDate() {
            return appDate;
        }

        public void setAppDate(String appDate) {
            this.appDate = appDate;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandNameTrans() {
            return brandNameTrans;
        }

        public void setBrandNameTrans(String brandNameTrans) {
            this.brandNameTrans = brandNameTrans;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getColor() {
            return Color;
        }

        public void setColor(String color) {
            Color = color;
        }

        public String getColorNum() {
            return colorNum;
        }

        public void setColorNum(String colorNum) {
            this.colorNum = colorNum;
        }

        public String getExistStatus() {
            return existStatus;
        }

        public void setExistStatus(String existStatus) {
            this.existStatus = existStatus;
        }

        public String getFamousId() {
            return famousId;
        }

        public void setFamousId(String famousId) {
            this.famousId = famousId;
        }

        public String getFirstIssueDate() {
            return firstIssueDate;
        }

        public void setFirstIssueDate(String firstIssueDate) {
            this.firstIssueDate = firstIssueDate;
        }

        public String getFirstTrailIssue() {
            return firstTrailIssue;
        }

        public void setFirstTrailIssue(String firstTrailIssue) {
            this.firstTrailIssue = firstTrailIssue;
        }

        public String getFormType() {
            return formType;
        }

        public void setFormType(String formType) {
            this.formType = formType;
        }

        public String getGeographyRemark() {
            return geographyRemark;
        }

        public void setGeographyRemark(String geographyRemark) {
            this.geographyRemark = geographyRemark;
        }

        public String getImageFilePath() {
            return imageFilePath;
        }

        public void setImageFilePath(String imageFilePath) {
            this.imageFilePath = imageFilePath;
        }

        public String getIsCommon() {
            return isCommon;
        }

        public void setIsCommon(String isCommon) {
            this.isCommon = isCommon;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getProtect() {
            return protect;
        }

        public void setProtect(String protect) {
            this.protect = protect;
        }

        public String getRegAppDate() {
            return regAppDate;
        }

        public void setRegAppDate(String regAppDate) {
            this.regAppDate = regAppDate;
        }

        public String getRegIssue() {
            return regIssue;
        }

        public void setRegIssue(String regIssue) {
            this.regIssue = regIssue;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getReputedRemark() {
            return reputedRemark;
        }

        public void setReputedRemark(String reputedRemark) {
            this.reputedRemark = reputedRemark;
        }

        public String getSolidRemark() {
            return solidRemark;
        }

        public void setSolidRemark(String solidRemark) {
            this.solidRemark = solidRemark;
        }

        public String getSpecialEndDate() {
            return specialEndDate;
        }

        public void setSpecialEndDate(String specialEndDate) {
            this.specialEndDate = specialEndDate;
        }

        public String getSpecialStartDate() {
            return specialStartDate;
        }

        public void setSpecialStartDate(String specialStartDate) {
            this.specialStartDate = specialStartDate;
        }

        public String getTmType() {
            return tmType;
        }

        public void setTmType(String tmType) {
            this.tmType = tmType;
        }
    }

    //商标主体信息
    public class Entity{
        private String registerType;   // //商标主体信息(主体类型01,02)，(01企业，02个体),(03"事业单位"，04"社会组织"，05自然人)
        private String registerName;   //	企业/个体名称
        private String registerRegNo;   //	注册号
        private String registerUniScid;   //	统一社会信用代码
        private String organizationCode;    //组织机构代码
        private String name;   //	联系人
        private String phone;   //	联系电话
        private String address;   //	联系地址
        private String postcode;   //	邮编
        private String regOrganId;   //	登记机关
        private String existStatus;   //	企业状态

        public String getOrganizationCode() {
            return organizationCode;
        }

        public void setOrganizationCode(String organizationCode) {
            this.organizationCode = organizationCode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getExistStatus() {
            return existStatus;
        }

        public void setExistStatus(String existStatus) {
            this.existStatus = existStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getRegisterName() {
            return registerName;
        }

        public void setRegisterName(String registerName) {
            this.registerName = registerName;
        }

        public String getRegisterRegNo() {
            return registerRegNo;
        }

        public void setRegisterRegNo(String registerRegNo) {
            this.registerRegNo = registerRegNo;
        }

        public String getRegisterType() {
            return registerType;
        }

        public void setRegisterType(String registerType) {
            this.registerType = registerType;
        }

        public String getRegisterUniScid() {
            return registerUniScid;
        }

        public void setRegisterUniScid(String registerUniScid) {
            this.registerUniScid = registerUniScid;
        }

        public String getRegOrganId() {
            return regOrganId;
        }

        public void setRegOrganId(String regOrganId) {
            this.regOrganId = regOrganId;
        }
    }

    //商标代理人信息
    public class Agent{
        private String chName;   //	中文名称
        private String chAddress;   //	中文地址
        private String enName;   //	外文名称
        private String enAddress;   //	外文地址
        private String contact;   //	联系人
        private String postalCode;   //	邮编
        private String addr;   //	地址
        private String phone;   //	电话
        private String mobile;   //	手机号码
        private String fax;   //	传真
        private String email;   //	E-Mail
        private String agentType;   //	代理人类型
        private String economyType;   //	经济性质
        private String cetfName;   //	证件名称
        private String cetfNo;   //	证件编号
        private String districtName;   //	行政区划
        private String state;   //	代理人状态
        private String memo;   //	备注

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAgentType() {
            return agentType;
        }

        public void setAgentType(String agentType) {
            this.agentType = agentType;
        }

        public String getCetfName() {
            return cetfName;
        }

        public void setCetfName(String cetfName) {
            this.cetfName = cetfName;
        }

        public String getCetfNo() {
            return cetfNo;
        }

        public void setCetfNo(String cetfNo) {
            this.cetfNo = cetfNo;
        }

        public String getChAddress() {
            return chAddress;
        }

        public void setChAddress(String chAddress) {
            this.chAddress = chAddress;
        }

        public String getChName() {
            return chName;
        }

        public void setChName(String chName) {
            this.chName = chName;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getEconomyType() {
            return economyType;
        }

        public void setEconomyType(String economyType) {
            this.economyType = economyType;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEnAddress() {
            return enAddress;
        }

        public void setEnAddress(String enAddress) {
            this.enAddress = enAddress;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    //商标优先权信息
    public class Priority{
        private String priorityNum;   //	优先权编号
        private String priorityType;   //	优先权种类
        private String priorityDate;   //	优先权日期
        private String priorityCtry;   //	优先权国家
        private String priorityGoods;   //	优先权商品
        private String priorityValidInfo;   //	是否有效

        public String getPriorityCtry() {
            return priorityCtry;
        }

        public void setPriorityCtry(String priorityCtry) {
            this.priorityCtry = priorityCtry;
        }

        public String getPriorityDate() {
            return priorityDate;
        }

        public void setPriorityDate(String priorityDate) {
            this.priorityDate = priorityDate;
        }

        public String getPriorityGoods() {
            return priorityGoods;
        }

        public void setPriorityGoods(String priorityGoods) {
            this.priorityGoods = priorityGoods;
        }

        public String getPriorityNum() {
            return priorityNum;
        }

        public void setPriorityNum(String priorityNum) {
            this.priorityNum = priorityNum;
        }

        public String getPriorityType() {
            return priorityType;
        }

        public void setPriorityType(String priorityType) {
            this.priorityType = priorityType;
        }

        public String getPriorityValidInfo() {
            return priorityValidInfo;
        }

        public void setPriorityValidInfo(String priorityValidInfo) {
            this.priorityValidInfo = priorityValidInfo;
        }
    }

    //商标其他信息
    public class Other{
        private String isMingpai;   //	是否名牌产品
        private String mingpaiDate;   //	获得年份
        private String isDibiao;   //	是否地标产品
        private String dibiaoDate;   //	获得年份
        private String isGaoxin;   //	是否高新技术企业
        private String gaoxinDate;   //	获得年份
        private String isZhonghua;   //	是否中华老字号
        private String zhonghuaDate;   //	获得年份
        private String isShengshi;   //	是否省市老字号
        private String shengshiDate;   //	获得年份
        private String isYuanqu;   //	是否园区企业
        private String yuanquName;   //	园区名称
        private String memo;   //	备注

        public String getDibiaoDate() {
            return dibiaoDate;
        }

        public void setDibiaoDate(String dibiaoDate) {
            this.dibiaoDate = dibiaoDate;
        }

        public String getGaoxinDate() {
            return gaoxinDate;
        }

        public void setGaoxinDate(String gaoxinDate) {
            this.gaoxinDate = gaoxinDate;
        }

        public String getIsDibiao() {
            return isDibiao;
        }

        public void setIsDibiao(String isDibiao) {
            this.isDibiao = isDibiao;
        }

        public String getIsGaoxin() {
            return isGaoxin;
        }

        public void setIsGaoxin(String isGaoxin) {
            this.isGaoxin = isGaoxin;
        }

        public String getIsMingpai() {
            return isMingpai;
        }

        public void setIsMingpai(String isMingpai) {
            this.isMingpai = isMingpai;
        }

        public String getIsShengshi() {
            return isShengshi;
        }

        public void setIsShengshi(String isShengshi) {
            this.isShengshi = isShengshi;
        }

        public String getIsYuanqu() {
            return isYuanqu;
        }

        public void setIsYuanqu(String isYuanqu) {
            this.isYuanqu = isYuanqu;
        }

        public String getIsZhonghua() {
            return isZhonghua;
        }

        public void setIsZhonghua(String isZhonghua) {
            this.isZhonghua = isZhonghua;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getMingpaiDate() {
            return mingpaiDate;
        }

        public void setMingpaiDate(String mingpaiDate) {
            this.mingpaiDate = mingpaiDate;
        }

        public String getShengshiDate() {
            return shengshiDate;
        }

        public void setShengshiDate(String shengshiDate) {
            this.shengshiDate = shengshiDate;
        }

        public String getYuanquName() {
            return yuanquName;
        }

        public void setYuanquName(String yuanquName) {
            this.yuanquName = yuanquName;
        }

        public String getZhonghuaDate() {
            return zhonghuaDate;
        }

        public void setZhonghuaDate(String zhonghuaDate) {
            this.zhonghuaDate = zhonghuaDate;
        }
    }

    //商标质押信息
    public class Pledge{
        private String pledgor;   //	出质人
        private String pledgee;   //	质权人
        private String pledgeReason;   //	质押原因
        private String pledgePeriod;   //	质押期限
        private String pledgeNoticeDate;   //	质押公告日期

        public String getPledgee() {
            return pledgee;
        }

        public void setPledgee(String pledgee) {
            this.pledgee = pledgee;
        }

        public String getPledgeNoticeDate() {
            return pledgeNoticeDate;
        }

        public void setPledgeNoticeDate(String pledgeNoticeDate) {
            this.pledgeNoticeDate = pledgeNoticeDate;
        }

        public String getPledgePeriod() {
            return pledgePeriod;
        }

        public void setPledgePeriod(String pledgePeriod) {
            this.pledgePeriod = pledgePeriod;
        }

        public String getPledgeReason() {
            return pledgeReason;
        }

        public void setPledgeReason(String pledgeReason) {
            this.pledgeReason = pledgeReason;
        }

        public String getPledgor() {
            return pledgor;
        }

        public void setPledgor(String pledgor) {
            this.pledgor = pledgor;
        }
    }

    //商标商品服务信息
    public class ProductService{
        private String key;     //	类似群
        private String value;   //	商品

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    //商标共有人信息
    public class Commmer{
        private String commonerName;   //	中文名称
        private String commonerEnName;   //	外文名称
        private String commonerAddr;   //	中文地址
        private String commonerEnAddr;   //	外文地址

        public String getCommonerAddr() {
            return commonerAddr;
        }
        public void setCommonerAddr(String commonerAddr) {
            this.commonerAddr = commonerAddr;
        }
        public String getCommonerEnAddr() {
            return commonerEnAddr;
        }
        public void setCommonerEnAddr(String commonerEnAddr) {
            this.commonerEnAddr = commonerEnAddr;
        }
        public String getCommonerEnName() {
            return commonerEnName;
        }
        public void setCommonerEnName(String commonerEnName) {
            this.commonerEnName = commonerEnName;
        }
        public String getCommonerName() {
            return commonerName;
        }
        public void setCommonerName(String commonerName) {
            this.commonerName = commonerName;
        }
    }

    //国际注册信息
    public class RegInternational{
        private String brandName;   //	国际注册名称	均为String
        private String internationalRegNo;   //	国际注册号	均为String
        private String classId;   //	国际分类号	均为String
        private String regType;   //	注册途径	均为String
        private String regCountryCode;   //	注册国家	均为String
        private String regDate;   //	注册日期	均为String

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getInternationalRegNo() {
            return internationalRegNo;
        }

        public void setInternationalRegNo(String internationalRegNo) {
            this.internationalRegNo = internationalRegNo;
        }

        public String getRegCountryCode() {
            return regCountryCode;
        }

        public void setRegCountryCode(String regCountryCode) {
            this.regCountryCode = regCountryCode;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        public String getRegType() {
            return regType;
        }

        public void setRegType(String regType) {
            this.regType = regType;
        }
    }

    //商标许可信息
    public class BrandPermit{
        private String license;   //	被许可人
        private String permitType;   //	许可类型
        private String permitProduct;   //	许可使用商品
        private String startDate;   //	许可期限(起)
        private String endDate;   //	许可期限(止)

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getPermitProduct() {
            return permitProduct;
        }

        public void setPermitProduct(String permitProduct) {
            this.permitProduct = permitProduct;
        }

        public String getPermitType() {
            return permitType;
        }

        public void setPermitType(String permitType) {
            this.permitType = permitType;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }

    //商标联络人信息
    public class ContactPerson{
        private String contactName;   //	联络人名称
        private String contactTel;   //	联络人电话
        private String contactMobile;   //	联络人手机
        private String contactAddress;   //	联络人地址

        public String getContactAddress() {
            return contactAddress;
        }

        public void setContactAddress(String contactAddress) {
            this.contactAddress = contactAddress;
        }

        public String getContactMobile() {
            return contactMobile;
        }

        public void setContactMobile(String contactMobile) {
            this.contactMobile = contactMobile;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }
    }

}
