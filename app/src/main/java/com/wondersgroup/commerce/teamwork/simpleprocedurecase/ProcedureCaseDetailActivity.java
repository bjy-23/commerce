package com.wondersgroup.commerce.teamwork.simpleprocedurecase;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.AttachmentDTO;
import com.wondersgroup.commerce.model.CaseInvestigateTitle;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.model.ProcedureCaseActnItemsVo;
import com.wondersgroup.commerce.model.ProcedureCaseAttachMentVo;
import com.wondersgroup.commerce.model.ProcedureCaseAttachResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseCompanyItemsVo;
import com.wondersgroup.commerce.model.ProcedureCaseDetail;
import com.wondersgroup.commerce.model.ProcedureCaseDicVo;
import com.wondersgroup.commerce.model.ProcedureCaseIllegalCodeResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseIllegalSmalTypeResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseLegalBasis;
import com.wondersgroup.commerce.model.ProcedureCaseLitigtInfoVo;
import com.wondersgroup.commerce.model.ProcedureCasePunishVo;
import com.wondersgroup.commerce.model.ProcedureCaseQueryCompanySelectedResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseQueryPersonSelectedResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseSubmit;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.utils.DynamicWidgetUtils;
import com.wondersgroup.commerce.utils.FileUtils;
import com.wondersgroup.commerce.widget.MyProgressDialog;
import com.wondersgroup.commerce.widget.TableRow;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by Lee on 2017/3/13.
 * 简易程序新增修改
 */
public class ProcedureCaseDetailActivity extends AppCompatActivity {

    private String TAG = "ProcedureCaseDetailActivity";
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.picLayout)
    LinearLayout picLayout;                                  //附件显示部分
    @Bind(R.id.pic1)
    ImageView pic1;
    @Bind(R.id.pic2)
    ImageView pic2;
    @Bind(R.id.pic3)
    ImageView pic3;
    @Bind(R.id.commit_record_Button)
    Button submitButton;
    private CaseInvestigateTitle caseInvestigateTitle;  //查询案件目录对象
    private ScrollView caseBrowseScrollView;            //案件总览ScrollView
    private LinearLayout caseBrowseLinearlayout;        //案件总览LinearLayout(动态控件)
    private LinearLayout personOrCompanyLinearlayout;                //自然人
    private LinearLayout districtAreaLinearlayout;                //本省登记个体工商户
    private LinearLayout illegalLinearLayout;                       //违法行为小类
    private LinearLayout illegalCodeLinearLayout;                   //违法行为代码

    private int viewTagNo = 0;                          //立案信息动态控件tag
    private List<DataVolume> componentCaseBrowseList;   //案件总览动态控件对象列表
    private ArrayList<EditText> arrayBrowseEdittext = new ArrayList<EditText>();
    private DynamicWidgetUtils dynamicWidgetUtils;      //动态加载控件对象
    private ArrayList<EditText> arrayRegisterEdittext = new ArrayList<EditText>();
    LinearLayout.LayoutParams paramsDivider = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramstemp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramsedittext = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);

    private ProcedureCaseDetail caseDetail;           //简易程序信息对象
    private ProcedureCaseSubmit submitCase = null;           //简易程序信息提交对象
    private ProcedureCaseDicVo dicVo;                  //简易程序字典
    private SerializableMap serializableOrganIdMapMap;  //机关字典
    private Map<String, String> districtMap;            //二级区域列表
    private Map<String, String> illegalSmallTypeMap;    //违法行为小类map
    private Map<String, String> illegalCodeMap;         //违法行为代码map
    private List<ProcedureCaseActnItemsVo> illegalLawList;         //违法行为定性和处罚依据List
    private ProcedureCasePunishVo submitPunishVo = null;       //处罚部分
    private RootAppcation app;
    private TableRow personType, inquiryNameRow, inquiryRegisterRow, idTypeRow, idNumber, sexTypeRow, caseAreaCityRow, caseAreaDistrictRow, caseSourceRow,
            illegalBigTypeRow, illegalSmallTypeRow, illegalCodeRow, illegalBehaviorType, qualitativeTypeRow, punishTypeRow,
            punishValue, penaltyDateRow, penaltyLetterNumRow, handlePersonRow;
    private RadioGroup punishTypeRadioGroup;
    private CheckBox checkBoxWarning, checkBoxPunishment;
    private TextView txtWarning, txtPunishment;
    //图片部分
    private final static int ONE_IMG = 100;
    private final static int TWO_IMG = 101;
    private final static int THREE_IMG = 102;
    private final static int ONE_CAMERA = 103;
    private final static int TWO_CAMERA = 104;
    private final static int THREE_CAMERA = 105;
    public final static int qualitativeTypeRowId = 106;
    private static String picOneName;
    private static String picTwoName;
    private static String picThreeName;
    private static String picOneId;
    private static String picTwoId;
    private static String picThreeId;
    private ArrayList<AttachmentDTO> attachList = new ArrayList<AttachmentDTO>();        //附件列表
    private final String[] personTypeArray = {"自然人", "本省登记个体工商户", "非本省登记个体工商户", "其他组织", "本省登记企业", "非本省登记企业"};
    private List<ProcedureCaseLegalBasis> referenceBasisesList; //定性依据选项列表 basisType : "0"
    private List<ProcedureCaseLegalBasis> publishBasisesList; //处罚依据选项列表 basisType : "1"
    private String ilglActId;
    private String caseId = null;  //  简易案件id
    private String litigantType = null;//{0-自然人分类、1-企业分类}
    private String documentType = null;//1--本省登记过的个体工商户(litigantType=0)\本省登记过企业，
    // {litigantType=0，documentType=0非本省登记个体工商户，documentType=2自然人}
    // {litigantType=1，documentType=0非本省登记企业，documentType=2其他组织}
    private String regEntityId, regOrganId, regEtpsTypeGb, regUniScid, regPripId;//登记过的个人/企业信息---个体主键\个体/企业登记机关\企业类型\主体身份代码
    private String selectedLawCode = null;  //已选择的定性依据
    private String selectedPunishCode = null;  //已选择的处罚依据
    private List<ProcedureCaseAttachMentVo> caseAttachList;
    private TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
    private Boolean bCanNotModify = false;  //当前登录人员不是办案人员，不能修改案件信息时 bCanNotModify = true
//    private ImageTableView fj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_case_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();

        paramstemp.weight = 2;
        paramsedittext.weight = 5;
        caseBrowseScrollView = (ScrollView) this.findViewById(R.id.case_browse_ScrollView);
        caseBrowseLinearlayout = (LinearLayout) this.findViewById(R.id.case_browse_linearlayout);

        initView();
    }

    private void initData(String clueNo) {
//        Log.d(TAG, "initData()------------- = clueNo = " + clueNo);
        caseId = clueNo;
        String userId = loginBean.getResult().getUserId();
        String deptId = loginBean.getResult().getDeptId();
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010102");
        map.put("clueNo", clueNo);
        map.put("userId", userId);
        map.put("deptId", deptId);
        Call<ProcedureCaseDetail> call;
        ApiManager.getInstance().unitTestInit();
        call = ApiManager.caseModule.getProcedureCaseDetail(map);
        call.enqueue(new Callback<ProcedureCaseDetail>() {
            @Override
            public void onResponse(Response<ProcedureCaseDetail> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    caseDetail = response.body();

                    if ((null == caseDetail) || (null == caseDetail.getResult())) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "获取简易案件详情数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else {//添加动态控件
                        dicVo = caseDetail.getResult().getmDicVo();
                        submitPunishVo = caseDetail.getResult().getmPunishVo();
                        if(bCanNotModify == false)
                            addBaseView();
                        else
                            addBaseViewWithNoModify();
                        if (dicVo.getiOrganIdMap() != null) {
                            serializableOrganIdMapMap = new SerializableMap();
                            serializableOrganIdMapMap.setMap(dicVo.getiOrganIdMap());
                        }
                        caseAttachList = caseDetail.getResult().getAttachList();
                        if (caseAttachList != null) {
                            if(caseAttachList.size() == 0){
                                pic1.setVisibility(View.GONE);
                                pic2.setVisibility(View.GONE);
                                pic3.setVisibility(View.GONE);
                            }else if(caseAttachList.size() == 1){
                                pic2.setVisibility(View.GONE);
                                pic3.setVisibility(View.GONE);
                            }else if(caseAttachList.size() == 2){
                                pic3.setVisibility(View.GONE);
                            }
                            int count = 0;
                            for (ProcedureCaseAttachMentVo attch : caseAttachList) {
//                                Log.d(TAG, "initData()------------- attch.getKey() = " + attch.getKey());
//                                Log.d(TAG, "initData()------------- attch.getName() = " + attch.getName());
                                putPicinPos(attch.getKey(), count);
                                if (count == 0)
                                    picOneId = attch.getKey();
                                else if (count == 1)
                                    picTwoId = attch.getKey();
                                else
                                    picThreeId = attch.getKey();
                                count++;
                            }
                        }else{
                            pic1.setVisibility(View.GONE);
                            pic2.setVisibility(View.GONE);
                            pic3.setVisibility(View.GONE);
                        }
                    }
                } else {
//                    Log.d(TAG, "myCaseToInvestigate --------------- response.is not Success()");
                    Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure() ------4--------- 服务器连接错误");
                Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }


    //获得案发地二级列表
    private void getDistrictMap(String firstSpotItem) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010112");
        map.put("cedistrictId", firstSpotItem);

        Call<ProcedureCaseIllegalSmalTypeResultObject> call;
        call = ApiManager.caseModule.getCaseDistrictsType(map);
        call.enqueue(new Callback<ProcedureCaseIllegalSmalTypeResultObject>() {
            @Override
            public void onResponse(Response<ProcedureCaseIllegalSmalTypeResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    final ProcedureCaseIllegalSmalTypeResultObject districtType = response.body();

                    if (null == districtType) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "获得案发地区错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        districtMap = districtType.getSubMap();
                        if(bCanNotModify == false)
                            addCityAreaRowLinearLayout();
                        else
                            addCityAreaRowLinearLayoutWithNoModify();
                    }
                } else {
//                    Log.d(TAG, "myCaseToInvestigate --------------- response.is not Success()");
                    Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure() ------1--------- 服务器连接错误");
                Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //5.3.4. 个体工商户选择
    private void queryPersonSelected(String entityId) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010104");
        map.put("entityId", entityId);

        Call<ProcedureCaseQueryPersonSelectedResultObject> call;
        call = ApiManager.caseModule.queryPersonSelected(map);

        call.enqueue(new Callback<ProcedureCaseQueryPersonSelectedResultObject>() {
            @Override
            public void onResponse(Response<ProcedureCaseQueryPersonSelectedResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    ProcedureCaseQueryPersonSelectedResultObject resultObject = response.body();

                    if ((null == resultObject) || (null == resultObject.getPersVo())) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "查询个体工商户信息错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

//                    if(caseDetail == null)
//                        caseDetail = new ProcedureCaseDetail();
//                    caseDetail.getResult().setmLitigtInfoVo(resultObject.getPersVo());
                    initPersonData(resultObject.getPersVo());
                    //todo 赋值

                } else {
//                    Log.d(TAG, "AICRegisterInquireListActivity --------------- 5");
                    Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d(TAG, "myCaseToInvestigate --------------- 5t.getMessage() = " + t.getMessage());
                Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //5.3.4. 企业工商户选择
    private void queryEtpsSelected(String entityId) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010106");
        map.put("entityId", entityId);

        Call<ProcedureCaseQueryCompanySelectedResultObject> call;
        call = ApiManager.caseModule.queryEtpsSelected(map);

        call.enqueue(new Callback<ProcedureCaseQueryCompanySelectedResultObject>() {
            @Override
            public void onResponse(Response<ProcedureCaseQueryCompanySelectedResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    ProcedureCaseQueryCompanySelectedResultObject resultObject = response.body();

                    if ((null == resultObject) || (null == resultObject.getGetEtpsVo())) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "查询企业工商户信息错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    initCompanyData(resultObject.getGetEtpsVo());

                } else {
//                    Log.d(TAG, "AICRegisterInquireListActivity --------------- 5");
                    Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d(TAG, "myCaseToInvestigate --------------- 5t.getMessage() = " + t.getMessage());
                Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //获得违法行为小类
    private void getIllegalType(String topItems) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010007");
        map.put("topItems", topItems);

        Call<ProcedureCaseIllegalSmalTypeResultObject> call;
        call = ApiManager.caseModule.getProcedureCaseIllegalSmallType(map);
        call.enqueue(new Callback<ProcedureCaseIllegalSmalTypeResultObject>() {
            @Override
            public void onResponse(Response<ProcedureCaseIllegalSmalTypeResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    final ProcedureCaseIllegalSmalTypeResultObject illegalType = response.body();

                    if (null == illegalType) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "获得违法行为小类错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        illegalSmallTypeMap = illegalType.getSubMap();
                        if(bCanNotModify == false)
                            addIllegalSmallTypeLinearLayout();
//                        else
//                            addIllegalSmallTypeLinearLayoutWithNoModify();
                    }
                } else {
//                    Log.d(TAG, "myCaseToInvestigate --------------- response.is not Success()");
                    Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure() ------1--------- 服务器连接错误");
                Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获得违法行为代码
    private void getIllegalCode(String subItems) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010013");
        map.put("subItems", subItems);

        Call<ProcedureCaseIllegalSmalTypeResultObject> call;
        call = ApiManager.caseModule.getProcedureCaseIllegalCode(map);
        call.enqueue(new Callback<ProcedureCaseIllegalSmalTypeResultObject>() {
            @Override
            public void onResponse(Response<ProcedureCaseIllegalSmalTypeResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    final ProcedureCaseIllegalSmalTypeResultObject illegalCode = response.body();

                    if (null == illegalCode) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "获得违法行为代码错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        illegalCodeMap = illegalCode.getSubMap();
                        if(bCanNotModify == false)
                            addIllegalCodeLinearLayout();
                        else
                            addIllegalCodeLinearLayoutWithNoModify();
                    }
                } else {
//                    Log.d(TAG, "myCaseToInvestigate --------------- response.is not Success()");
                    Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure() ------2--------- 服务器连接错误");
                Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获得违法行为定性和处罚依据
    private void getIllegalLaw(String subItems) {

        ilglActId = subItems;
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010008");
        map.put("ilglActId", subItems);

        Call<ProcedureCaseIllegalCodeResultObject> call;
        call = ApiManager.caseModule.getProcedureCaseIllegalLaw(map);
        call.enqueue(new Callback<ProcedureCaseIllegalCodeResultObject>() {
            @Override
            public void onResponse(Response<ProcedureCaseIllegalCodeResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    final ProcedureCaseIllegalCodeResultObject illegalLaw = response.body();

                    if (null == illegalLaw) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "获得违法行为依据错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        illegalLawList = illegalLaw.getActnItemsVo();
                        if (illegalLawList == null || illegalLawList.size() < 1) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "定性依据字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            referenceBasisesList = new ArrayList<ProcedureCaseLegalBasis>();
                            publishBasisesList = new ArrayList<ProcedureCaseLegalBasis>();
                            for (ProcedureCaseActnItemsVo data : illegalLawList) {
                                ProcedureCaseLegalBasis legalBasis = new ProcedureCaseLegalBasis();
                                legalBasis.setLawCode(ilglActId);
                                legalBasis.setBasisType(data.getBasisType());
                                legalBasis.setMajorBasis(false);
                                legalBasis.setChecked(false);
                                if (data.getBasisType().equals("0")) {
                                    legalBasis.setLawCode(data.getQuabasis());
                                    legalBasis.setLawContent(data.getReferenceLawCon());
                                    referenceBasisesList.add(legalBasis);
                                } else {
                                    legalBasis.setLawCode(data.getPenbasis());
                                    legalBasis.setLawContent(data.getPunishLawCon());
                                    publishBasisesList.add(legalBasis);
                                }
                            }
                        }
//                        addIllegalCodeLinearLayout();
                    }
                } else {
//                    Log.d(TAG, "myCaseToInvestigate --------------- response.is not Success()");
                    Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure() ------2--------- 服务器连接错误");
                Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //新增时，获得字典列表
    private void getVolumesList() {
        String userId = loginBean.getResult().getUserId();
        String deptId = loginBean.getResult().getDeptId();
        String organId = loginBean.getResult().getOrganId();

        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("deptId", deptId);
        map.put("organId", organId);
        map.put("wsCodeReq", "03010102");

        Call<ProcedureCaseDetail> call;
        call = ApiManager.caseModule.getProcedureCaseVols(map);
        call.enqueue(new Callback<ProcedureCaseDetail>() {
            @Override
            public void onResponse(Response<ProcedureCaseDetail> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();

                    if (null == response.body().getResult() || null == response.body().getResult().getmDicVo()) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "获取字典列表数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        dicVo = response.body().getResult().getmDicVo();
                        if (dicVo.getiOrganIdMap() != null) {
                            serializableOrganIdMapMap = new SerializableMap();
                            serializableOrganIdMapMap.setMap(dicVo.getiOrganIdMap());
                        }
                        regEntityId = response.body().getResult().getmLitigtInfoVo().getEntityId();
                        regOrganId = response.body().getResult().getmLitigtInfoVo().getRegOrganId();
                        regEtpsTypeGb = response.body().getResult().getmLitigtInfoVo().getEtpsTypeGb();
                        regUniScid = response.body().getResult().getmLitigtInfoVo().getUniScid();
                        regPripId = response.body().getResult().getmLitigtInfoVo().getPripid();
                    }
                } else {
//                    Log.d(TAG, "myCaseToInvestigate --------------- get volumes list not Success()");
                    Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure() ------3--------- 服务器连接错误");
                Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //在指定位置加载服务端图片
    public void putPicinPos(String picId, final int pos) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010023");
        map.put("attachId", picId);
        Call<ProcedureCaseAttachResultObject> call;
        call = ApiManager.caseModule.procedureCaseDownLoadAttchment(map);
        call.enqueue(new Callback<ProcedureCaseAttachResultObject>() {
            @Override
            public void onResponse(Response<ProcedureCaseAttachResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    final ProcedureCaseAttachResultObject attachObject = response.body();

                    if (null == attachObject || null == attachObject.getResult()) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "下载附件数据错误", Toast.LENGTH_SHORT).show();
                    } else {
                        String fileStr = attachObject.getResult().getAttachFileStr();
                        if (fileStr == null || fileStr == "")
                            return;
                        attachList.get(pos).setAttachFileStr(fileStr);
                        Bitmap pic = FileUtils.stringtoBitmap(fileStr);
                        File file;
                        switch (pos) {
                            case 0:
                                file = new File(app.getCachePath(), "pic1.jpg");
                                try {
                                    OutputStream stream = new FileOutputStream(file);
                                    pic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                    stream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                picOneName = app.getCachePath() + "/pic1.jpg";
                                Picasso.with(ProcedureCaseDetailActivity.this).load(file)
                                        .resize(120, 120).centerCrop().into(pic1);
                                break;
                            case 1:
                                file = new File(app.getCachePath(), "pic2.jpg");
                                try {
                                    OutputStream stream = new FileOutputStream(file);
                                    pic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                    stream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                picTwoName = app.getCachePath() + "/pic2.jpg";
                                Picasso.with(ProcedureCaseDetailActivity.this).load(file)
                                        .resize(120, 120).centerCrop().into(pic2);
                                break;
                            case 2:
                                file = new File(app.getCachePath(), "pic3.jpg");
                                try {
                                    OutputStream stream = new FileOutputStream(file);
                                    pic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                    stream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                picThreeName = app.getCachePath() + "/pic3.jpg";
                                Picasso.with(ProcedureCaseDetailActivity.this).load(file)
                                        .resize(120, 120).centerCrop().into(pic3);
                                break;
                        }


                    }
                } else {
                    Toast.makeText(ProcedureCaseDetailActivity.this, "服务器信息有误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(ProcedureCaseDetailActivity.this, "服务器连接错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //提交简易案件
    private void submitCase() {

        if (checkValue() == false)
            return;
        MyProgressDialog.show(this);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010011");
        Gson gson = new GsonBuilder().create();
//        map.put("sampleCaseVo", submitCase.toString());
        map.put("sampleCaseVo", gson.toJson(submitCase));
        String userId = loginBean.getResult().getUserId();
        String deptId = loginBean.getResult().getDeptId();
        String organId = loginBean.getResult().getOrganId();
        map.put("userId", userId);
        map.put("deptId", deptId);
        map.put("organId", organId);
        if (caseId != null)
            map.put("clueNo", caseId);//新增和修改的区别
        addAdditionalFile(map);

        Call<ProcedureCaseDetail> call;
        call = ApiManager.caseModule.submitProcedureCase(map);
        call.enqueue(new Callback<ProcedureCaseDetail>() {
            @Override
            public void onResponse(Response<ProcedureCaseDetail> response, Retrofit retrofit) {
                if (response.isSuccess()) {
//                    Log.d(TAG, "myCaseToInvestigate --------------- response.is Success()");
                    ProcedureCaseDetailActivity.this.finish();

                } else {
//                    Log.d(TAG, "myCaseToInvestigate --------------- response.is not Success()");
                    Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
                MyProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure() ------3--------- 服务器连接错误");
                MyProgressDialog.dismiss();
                Toast.makeText(ProcedureCaseDetailActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addAdditionalFile(Map<String, String> map) {

        if (picOneName == null) {
//            Log.d(TAG, "addAdditionalFile()-------------- picOneName = null");
            return;
        } else
            Log.d(TAG, "addAdditionalFile()----- picOneName = " + picOneName);

        String additional1 = null;
        String additional2 = null;
        String additional3 = null;
        File file1 = new File(picOneName);
        String fileName1 = file1.getName();
//        Log.d(TAG, "addAdditionalFile()-------------- fileName = " + fileName1);
        if (file1.exists()) {
            Bitmap bm1 = BitmapFactory.decodeFile(picOneName);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
//                Log.d(TAG, "testFj()------1-------- picOneName.size() = " + bm1.getByteCount());
//                Log.d(TAG, "testFj()------1-------- picOneName.getWidth() = " + bm1.getWidth());
//                Log.d(TAG, "testFj()------1-------- picOneName.getHeight() = " + bm1.getHeight());
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                out.flush();
                bm1.compress(Bitmap.CompressFormat.PNG, 100, out);//转换为png格式的
                out.close();
//                Log.d(TAG, "testFj()------2-------- picOneName.size() = " + bm1.getByteCount());
            } catch (IOException e) {
                e.printStackTrace();
            }
//            byte[] buffer=out.toByteArray();
//            byte[] encode= Base64.decode(buffer, Base64.DEFAULT);

            String photo = Base64.encodeToString(bitmapToBytes(bm1), Base64.DEFAULT);
            AttachmentDTO addition1 = new AttachmentDTO();
            if (picOneId != null)
                addition1.setAttachId(picOneId);
            addition1.setAttachName(fileName1);
            addition1.setAttachFileStr(photo);
            Gson gson = new GsonBuilder().create();
            additional1 = gson.toJson(addition1);
        }

        if (picTwoName != null) {
//            Log.d(TAG, "addAdditionalFile()-------------- picTwoName = " + picTwoName);
            File file2 = new File(picTwoName);
            String fileName2 = file2.getName();
//            Log.d(TAG, "addAdditionalFile()-------------- fileName2 = " + fileName2);
            if (file2.exists()) {
                Bitmap bm2 = BitmapFactory.decodeFile(picTwoName);
//                Log.d(TAG, "testFj()------2-------- picTwoName.size() = " + bm2.getByteCount());
//                Log.d(TAG, "testFj()------2-------- picTwoName.getWidth() = " + bm2.getWidth());
//                Log.d(TAG, "testFj()------2-------- picTwoName.getHeight() = " + bm2.getHeight());
                ByteArrayOutputStream out2 = new ByteArrayOutputStream();
                try {
                    out2.flush();
                    bm2.compress(Bitmap.CompressFormat.PNG, 100, out2);//转换为png格式的
                    out2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String photo2 = Base64.encodeToString(bitmapToBytes(bm2), Base64.DEFAULT);
                AttachmentDTO addition2 = new AttachmentDTO();
                if (picTwoId != null)
                    addition2.setAttachId(picTwoId);
                addition2.setAttachName(fileName2);
                addition2.setAttachFileStr(photo2);
                Gson gson = new GsonBuilder().create();
                additional2 = gson.toJson(addition2);
            }

        }

        if (picThreeName != null) {
//            Log.d(TAG, "addAdditionalFile()-------------- picThreeName = " + picThreeName);
            File file3 = new File(picThreeName);
            String fileName3 = file3.getName();
//            Log.d(TAG, "addAdditionalFile()-------------- fileName3 = " + fileName3);
            if (file3.exists()) {
                Bitmap bm3 = BitmapFactory.decodeFile(picThreeName);
//                Log.d(TAG, "testFj()------3-------- picThreeName.size() = " + bm3.getByteCount());
                ByteArrayOutputStream out3 = new ByteArrayOutputStream();
                try {
                    out3.flush();
                    bm3.compress(Bitmap.CompressFormat.PNG, 100, out3);//转换为png格式的
                    out3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String photo3 = Base64.encodeToString(bitmapToBytes(bm3), Base64.DEFAULT);
                AttachmentDTO addition3 = new AttachmentDTO();
                if (picThreeId != null)
                    addition3.setAttachId(picThreeId);
                addition3.setAttachName(fileName3);
                addition3.setAttachFileStr(photo3);
                Gson gson = new GsonBuilder().create();
                additional3 = gson.toJson(addition3);
            }

        }
        String additional = additional1;
        if (additional2 != null)
            additional = additional + "*" + additional2;
        if (additional3 != null)
            additional = additional + "*" + additional3;
        map.put("attachListStr", additional);

    }

    public static byte[] bitmapToBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        return os.toByteArray();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent.hasExtra("clueNo")) {
            initData(intent.getStringExtra("clueNo"));
            String status = intent.getStringExtra("status");
            if (status.equals("0")) {
                bCanNotModify = true;
                submitButton.setVisibility(View.GONE);
                Toast.makeText(ProcedureCaseDetailActivity.this, "当前登录人员不是办案人员，不能修改案件信息！", Toast.LENGTH_SHORT).show();
            }
        } else {
            addBaseView();
            getVolumesList();
        }

        // 从bundle数据包中取出数据
//        caseInvestigateTitle = (CaseInvestigateTitle) bundle.getSerializable("CaseInvestigateTitle");

//        if(null == caseInvestigateTitle)
//            return;

//        Log.d(TAG, "caseInvestigateTitle.getClueNo() = " + caseInvestigateTitle.getClueNo());
        title.setText("简易程序案件");
        for (int j = 0; j < 3; j++) {
            AttachmentDTO attchment = new AttachmentDTO();
            attachList.add(attchment);
        }
    }

    //5.3.6. 企业选择，初始化选择的企业内容
    private void initCompanyData(ProcedureCaseCompanyItemsVo companyData) {

        for (int i = 0; i < personOrCompanyLinearlayout.getChildCount(); i++) {
            Object view = personOrCompanyLinearlayout.getChildAt(i);
            if (view instanceof TableRow) {
//                Log.d(TAG, "checkValue() --------------- view is instanceof TableRow");
                TableRow widget = (TableRow) view;
//                Log.d(TAG, "checkValue() --------------- widget.getTitle() = " + widget.getTitle());
                String title = widget.getTitle();
                if (widget.getTitle() == null)//蓝色标题，不用取值------------1960(父一级)
                    continue;
                if (widget.getTitle().equals("单位名称*")) {
                    widget.setContent(companyData.getLitigtName());
                } else if (widget.getTitle().equals("注册号*")) {
                    widget.setContent(companyData.getRegNo());
                } else if (widget.getTitle().equals("法定代表人经营者*")) {
                    widget.setContent(companyData.getLegalName());
                } else if (widget.getTitle().equals("现居住地/经营场所*")) {
                    widget.setContent(companyData.getAddress());
                } else if (widget.getTitle().equals("主体身份代码(G)*")) {
                    widget.setContent(companyData.getPripId());
                }
            }
        }
        if (dicVo != null && dicVo.getDicCetfMap() != null) {
            String idType = dicVo.getDicCetfMap().get(companyData.getCerType());
            idTypeRow.setTvContent(idType);
            idNumber.setContent(companyData.getCerNo());
        }

        regEntityId = companyData.getEntityId();
        regOrganId = companyData.getRegOrganId();
        regEtpsTypeGb = companyData.getEtpsTypeGb();
        regUniScid = companyData.getUniScid();
    }

    //5.3.4. 个体工商户选择，初始化选择的个人内容
    private void initPersonData(ProcedureCaseLitigtInfoVo personData) {

        for (int i = 0; i < personOrCompanyLinearlayout.getChildCount(); i++) {
            Object view = personOrCompanyLinearlayout.getChildAt(i);
            if (view instanceof TableRow) {
//                Log.d(TAG, "checkValue() --------------- view is instanceof TableRow");
                TableRow widget = (TableRow) view;
//                Log.d(TAG, "checkValue() --------------- widget.getTitle() = " + widget.getTitle());
                String title = widget.getTitle();
                if (widget.getTitle() == null)//蓝色标题，不用取值------------1960(父一级)
                    continue;

                if (widget.getTitle().equals("个体工商户名称*")) {
                    widget.setContent(personData.getLitigtName());
                } else if (widget.getTitle().equals("注册号*")) {
                    widget.setContent(personData.getRegNo());
                } else if (widget.getTitle().equals("年龄(G)*")) {
                    widget.setContent(personData.getAge());
                } else if (widget.getTitle().equals("性别(G)*")) {
                    if (dicVo != null && dicVo.getDicSexMap() != null)
                        widget.setContent(dicVo.getDicSexMap().get(personData.getSex()));
                } else if (widget.getTitle().equals("联系电话(G)*")) {
                    widget.setContent(personData.getTel());
                } else if (widget.getTitle().equals("工作单位(G)*")) {
                    widget.setContent(personData.getWorkunit());
                } else if (widget.getTitle().equals("住所(G)*")) {
                    widget.setContent(personData.getHouse());
                } else if (widget.getTitle().equals("职业(G)*")) {
                    widget.setContent(personData.getOccupation());
                } else if (widget.getTitle().equals("邮政编码(G)*")) {
                    widget.setContent(personData.getPostalcode());
                } else if (widget.getTitle().equals("法定代表人经营者*")) {
                    widget.setContent(personData.getLegalName());
                } else if (widget.getTitle().equals("现居住地/经营场所*")) {
                    widget.setContent(personData.getAddress());
                }
            }
        }

        if (dicVo != null && dicVo.getDicCetfMap() != null) {
            String idType = dicVo.getDicCetfMap().get(personData.getCerType());
            idTypeRow.setTvContent(idType);
            idNumber.setContent(personData.getCerNo());
        }

        regEntityId = personData.getEntityId();
        regOrganId = personData.getRegOrganId();
        regEtpsTypeGb = personData.getEtpsTypeGb();
        regUniScid = personData.getUniScid();
        regPripId = personData.getPripid();
    }

    //添加基本信息
    private void addBaseView() {
//        Log.d(TAG, "caseInvestigateTitle.getClueNo() = addBaseView----------1");
        TableRow caseResourceTitle = new TableRow.Builder(this)
                .asTitle("立案来源")
                .build();
        caseBrowseLinearlayout.addView(caseResourceTitle);

        TableRow caseName = new TableRow.Builder(this)
                .title("案件名称")
                .input("请输入案件名称")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmCaseDetail().getCaseName())
                .build();
        caseBrowseLinearlayout.addView(caseName);

        personType = new TableRow.Builder(this)
                .title("当事人分类")
                .input("请选择当事人类别")
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                .items(personTypeArray)
                                .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                        int organFlag = which;
                                        switch (which) {
                                            case 0://自然人
                                                litigantType = "0";
                                                documentType = "2";
                                                break;
                                            case 1://本省登记个体工商户
                                                litigantType = "0";
                                                documentType = "1";
                                                Toast.makeText(ProcedureCaseDetailActivity.this, "请以查询方式录入个体工商户信息！", Toast.LENGTH_SHORT).show();
                                                break;
                                            case 2://非本省登记个体工商户
                                                litigantType = "0";
                                                documentType = "0";
                                                break;
                                            case 3://其他组织
                                                litigantType = "1";
                                                documentType = "2";
                                                break;
                                            case 4://本省登记企业
                                                litigantType = "1";
                                                documentType = "1";
                                                Toast.makeText(ProcedureCaseDetailActivity.this, "请以查询方式录入企业用户信息！", Toast.LENGTH_SHORT).show();
                                                break;
                                            case 5://非本省登记企业
                                                litigantType = "1";
                                                documentType = "0";
                                                break;
                                        }
                                        personType.setTvContent(personTypeArray[which]);
                                        addPersonOrCompanyLinearLayout();
                                        regEntityId = null;
                                        regOrganId = null;
                                        regEtpsTypeGb = null;
                                        regUniScid = null;
                                        regPripId = null;
                                        return true;
                                    }
                                })
                                .positiveText("确定")
                                .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                .show();
                    }
                })
                .build();
        caseBrowseLinearlayout.addView(personType);

        personOrCompanyLinearlayout = new LinearLayout(this);
        personOrCompanyLinearlayout.setOrientation(LinearLayout.VERTICAL);
        caseBrowseLinearlayout.addView(personOrCompanyLinearlayout);
        ////{0-自然人分类:{自然人:2、本省登记个体工商户:1、非本省登记个体工商户:0}、1-企业分类：{非本省登记企业:0、本省登记企业:1、其他组织:2}}

        if (caseDetail != null) {
            litigantType = caseDetail.getResult().getmLitigtInfoVo().getAssort();
            documentType = caseDetail.getResult().getmLitigtInfoVo().getIsDomination();
            if (litigantType.equals("0")) {//0-自然人分类
                if (documentType.equals("2")) {
                    personType.setTvContent("自然人");
                } else if (documentType.equals("1")) {
                    personType.setTvContent("本省登记个体工商户");
                } else if (documentType.equals("0")) {
                    personType.setTvContent("非本省登记个体工商户");
                }
            } else if (litigantType.equals("1")) {//1-企业分类
                if (documentType.equals("0")) {
                    personType.setTvContent("非本省登记企业");
                } else if (documentType.equals("1")) {
                    personType.setTvContent("本省登记企业");
                } else if (documentType.equals("2")) {
                    personType.setTvContent("其他组织");
                }
            }
            addPersonOrCompanyLinearLayout();
        }
        //
        idTypeRow = new TableRow.Builder(this)
                .title("证件类型(G)")
                .input("请选择证件类型")
                .required()
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (dicVo == null) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "证件类型字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
//                            final String[] typeStringArray = {"中华人民共和国居民身份证", "中华人民共和国军官证", "中华人民共和国居民警官证",
//                                    "外地（地区）护照", "其他有效身份证件" };
                            final String typeStringArray[] = new String[dicVo.getDicCetfMap().size()];
                            int i = 0;
                            for (Map.Entry<String, String> entry : dicVo.getDicCetfMap().entrySet())
                                typeStringArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                    .items(typeStringArray)
                                    .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            int organFlag = which;
                                            idTypeRow.setTvContent(typeStringArray[organFlag]);
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .show();
                        }
                    }
                })
                .build();
        caseBrowseLinearlayout.addView(idTypeRow);
        if (caseDetail != null && dicVo != null && dicVo.getDicCetfMap() != null) {
            idTypeRow.setTvContent(dicVo.getDicCetfMap().get(caseDetail.getResult().getmLitigtInfoVo().getCerType()));
        }

        idNumber = new TableRow.Builder(this)
                .title("证件号码(G)")
                .input("请输入证件号码")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getCerNo())
                .build();
        caseBrowseLinearlayout.addView(idNumber);


        caseAreaCityRow = new TableRow.Builder(this)
                .title("案发地区（市）")
                .input("请选择案发地区")
                .required()
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (dicVo == null) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "案发地区（市）字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            final String ciityStringArray[] = new String[dicVo.getDistrictMap().size()];
                            int i = 0;//final String[] ciityStringArray = {"石家庄市", "唐山市", "邯郸市", "承德市", "张家口市"};
                            for (Map.Entry<String, String> entry : dicVo.getDistrictMap().entrySet())
                                ciityStringArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                    .items(ciityStringArray)
                                    .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            caseAreaCityRow.setTvContent(ciityStringArray[which]);
                                            for (Map.Entry<String, String> entry : dicVo.getDistrictMap().entrySet())
                                                if (ciityStringArray[which].equals(entry.getValue()))
                                                    getDistrictMap(entry.getKey());
//                                            addCityAreaRowLinearLayout();
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .show();
                        }
                    }
                })
                .build();
        caseBrowseLinearlayout.addView(caseAreaCityRow);
        districtAreaLinearlayout = new LinearLayout(this);
        districtAreaLinearlayout.setOrientation(LinearLayout.VERTICAL);
        caseBrowseLinearlayout.addView(districtAreaLinearlayout);
        if (caseDetail != null && caseDetail.getResult().getmCaseDetail().getCeCity() != null && caseDetail.getResult().getmDicVo().getDistrictMap() != null) {
            String key = caseDetail.getResult().getmCaseDetail().getCeCity();
            caseAreaCityRow.setTvContent(caseDetail.getResult().getmDicVo().getDistrictMap().get(key));
            getDistrictMap(key);
        }

        TableRow casePlace = new TableRow.Builder(this)
                .title("案发地点(G)")
                .input("请输入案发地点")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmCaseDetail().getCaseSpot())
                .build();
        caseBrowseLinearlayout.addView(casePlace);

        handlePersonRow = new TableRow.Builder(this)
                .title("办案人员")
                .input("请选择办案人员")
                .required()
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (dicVo == null) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "办案人员字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            final String personStringArray[] = new String[dicVo.getUserIdMainMap().size()];
                            int i = 0;
                            for (Map.Entry<String, String> entry : dicVo.getUserIdMainMap().entrySet())
                                personStringArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                    .items(personStringArray)
                                    .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            handlePersonRow.setTvContent(personStringArray[which]);
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .show();
                        }
                    }
                })
                .build();
        caseBrowseLinearlayout.addView(handlePersonRow);
        if (caseDetail != null && dicVo != null && dicVo.getUserIdMainMap() != null) {
            handlePersonRow.setTvContent(dicVo.getUserIdMainMap().get(caseDetail.getResult().getmCaseDetail().getUserIdMainName()));
        }


        caseSourceRow = new TableRow.Builder(this)
                .title("案件来源")
                .input("请选择案件来源")
                .required()
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (dicVo == null) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "案件来源字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            final String sourceTypeStringArray[] = new String[dicVo.getClueTypeMap().size()];
                            int i = 0;//final String[] sourceTypeStringArray = {"监督检查","投诉、申诉、举报","其他机关移送","上级机关交办"};
                            for (Map.Entry<String, String> entry : dicVo.getClueTypeMap().entrySet())
                                sourceTypeStringArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                    .items(sourceTypeStringArray)
                                    .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            caseSourceRow.setTvContent(sourceTypeStringArray[which]);
//                                            addCityAreaRowLinearLayout();
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .show();
                        }
                    }
                })
                .build();
        caseBrowseLinearlayout.addView(caseSourceRow);
        if (caseDetail != null && dicVo != null && dicVo.getClueTypeMap() != null) {
            caseSourceRow.setTvContent(dicVo.getClueTypeMap().get(caseDetail.getResult().getmCaseDetail().getCasesou()));
        }

        TableRow caseValue = new TableRow.Builder(this)
                .title("案值估计（元）")
                .input("请输入案值估计")
                .required()
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .content(caseDetail == null ? "" : caseDetail.getResult().getmCaseDetail().getCaseVal())
                .build();
        caseBrowseLinearlayout.addView(caseValue);


        TableRow illegalActTitle = new TableRow.Builder(this)
                .asTitle("当事人涉嫌违法行为（非必录）")
                .build();
        caseBrowseLinearlayout.addView(illegalActTitle);
        illegalBigTypeRow = new TableRow.Builder(this)
                .title("违法行为大类")
                .input("请选择违法行为")
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (dicVo == null) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "违法行为大类字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            final String bigIllegalTypeStringArray[] = new String[dicVo.getIlglCateMap().size()];
                            int i = 0;//final String[] bigIllegalTypeStringArray = {"违反登记管理行为","不正当竞争行为","侵害消费者权益行为","产品质量违法行为"};
                            for (Map.Entry<String, String> entry : dicVo.getIlglCateMap().entrySet())
                                bigIllegalTypeStringArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                    .items(bigIllegalTypeStringArray)
                                    .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            illegalBigTypeRow.setTvContent(bigIllegalTypeStringArray[which]);
                                            for (Map.Entry<String, String> entry : dicVo.getIlglCateMap().entrySet()) {
                                                if (entry.getValue().equals(bigIllegalTypeStringArray[which])) {
                                                    getIllegalType(entry.getKey());
                                                    if (illegalSmallTypeRow != null)
                                                        illegalSmallTypeRow.setTvContent("");
                                                    if (illegalCodeRow != null)
                                                        illegalCodeRow.setContent("");
                                                    if (submitPunishVo == null) {
                                                        submitPunishVo = new ProcedureCasePunishVo();
                                                    }
                                                    submitPunishVo.setIlglActId("");
                                                    submitPunishVo.setIllegTypeCon("");
                                                    submitPunishVo.setReferenceLawCon("");
                                                    submitPunishVo.setQuabasis("");
                                                    submitPunishVo.setPunishLawCon("");
                                                    submitPunishVo.setPenbasis("");
                                                    return true;
                                                }
                                            }
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .show();
                        }
                    }
                })
                .build();
        caseBrowseLinearlayout.addView(illegalBigTypeRow);
        if (submitPunishVo != null && dicVo != null && dicVo.getIlglCateMap() != null) {
            illegalBigTypeRow.setTvContent(dicVo.getIlglCateMap().get(submitPunishVo.getIlglActId().substring(0, 2)));
        }

        //违法行为小类LinearLayout
        illegalLinearLayout = new LinearLayout(this);
        illegalLinearLayout.setOrientation(LinearLayout.VERTICAL);
        caseBrowseLinearlayout.addView(illegalLinearLayout);

        //违法行为代码LinearLayout
        illegalCodeLinearLayout = new LinearLayout(this);
        illegalCodeLinearLayout.setOrientation(LinearLayout.VERTICAL);
        caseBrowseLinearlayout.addView(illegalCodeLinearLayout);

        if (submitPunishVo != null) {
            String ilglActId = submitPunishVo.getIlglActId();
            if (ilglActId != null && !ilglActId.equals("")) {
                getIllegalType(ilglActId.substring(0, 2));
                getIllegalCode(ilglActId.substring(0, 4));
            }
        }

        illegalBehaviorType = new TableRow.Builder(this)
                .title("违法行为种类(G)")
                .content(submitPunishVo == null ? "" : submitPunishVo.getIllegTypeCon())
                .build();
        caseBrowseLinearlayout.addView(illegalBehaviorType);

        qualitativeTypeRow = new TableRow.Builder(this)
                .title("定性依据(G)")
                .input("请选择定性依据")
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (referenceBasisesList == null || referenceBasisesList.size() < 2) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "定性依据字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString("activityType", "legalBasis");
                            bundle.putSerializable("ProcedureCaseLegalBasis", (Serializable) referenceBasisesList);
                            Intent intent = new Intent(ProcedureCaseDetailActivity.this, ProcedureCaseLegalBasisListActivity.class);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, ProcedureCaseLegalBasisListActivity.LEGAL_BASIS);
                        }
                    }
                })
                .build();
        caseBrowseLinearlayout.addView(qualitativeTypeRow);
        if (submitPunishVo != null) {
            selectedLawCode = submitPunishVo.getQuabasis();
            qualitativeTypeRow.setTvContent(submitPunishVo.getReferenceLawCon());
        }

        punishTypeRow = new TableRow.Builder(this)
                .title("处罚依据(G)")
                .input("请选择处罚依据")
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (publishBasisesList == null || publishBasisesList.size() < 2) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "定性依据字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString("activityType", "penaltyBasis");
                            bundle.putSerializable("ProcedureCaseLegalBasis", (Serializable) publishBasisesList);
                            Intent intent = new Intent(ProcedureCaseDetailActivity.this, ProcedureCaseLegalBasisListActivity.class);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, ProcedureCaseLegalBasisListActivity.PENALTY_BASIS);
                        }
                    }
                })
                .build();
        caseBrowseLinearlayout.addView(punishTypeRow);
        if (submitPunishVo != null) {
            punishTypeRow.setTvContent(submitPunishVo.getPunishLawCon());
            selectedPunishCode = submitPunishVo.getPenbasis();
        }


        TableRow caseReason = new TableRow.Builder(this)
                .title("案由(G)")
                .input("请输入案由")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmCaseDetail().getCaseReason())
                .build();
        caseBrowseLinearlayout.addView(caseReason);


        TableRow punishMentTitle = new TableRow.Builder(this)
                .asTitle("处罚方式")
                .build();
        caseBrowseLinearlayout.addView(punishMentTitle);

        LinearLayout radioLinearlayout = new LinearLayout(this);
        LinearLayout.LayoutParams paramRadioLinearLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramRadioLinearLayout.bottomMargin = 20;
        paramRadioLinearLayout.topMargin = 20;
        radioLinearlayout.setLayoutParams(paramRadioLinearLayout);
        radioLinearlayout.setOrientation(LinearLayout.HORIZONTAL);
        caseBrowseLinearlayout.addView(radioLinearlayout);
        TextView radioTitleTextView = new TextView(this);
        radioTitleTextView.setText("处罚方式");//字段名称
        radioTitleTextView.setTextColor(this.getResources().getColor(R.color.gray));
        radioTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14); //SP
        LinearLayout.LayoutParams paramLinearLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramLinearLayout.leftMargin = 50;
        paramLinearLayout.rightMargin = 50;
        paramLinearLayout.topMargin = 10;
        radioTitleTextView.setLayoutParams(paramLinearLayout);
        radioLinearlayout.addView(radioTitleTextView);
        LinearLayout.LayoutParams checkBoxParamLinearLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramLinearLayout.rightMargin = 20;
        paramLinearLayout.topMargin = 10;
        checkBoxWarning = new CheckBox(this);
        checkBoxWarning.setText("警告");
        checkBoxWarning.setLayoutParams(checkBoxParamLinearLayout);
        radioLinearlayout.addView(checkBoxWarning);
        if (litigantType == null) {
            if (caseDetail != null)
                Toast.makeText(ProcedureCaseDetailActivity.this, "数据有误！当事人分类不明确！", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(ProcedureCaseDetailActivity.this, "请选择当事人分类！", Toast.LENGTH_SHORT).show();
        }

        checkBoxPunishment = new CheckBox(this);
        if (litigantType == null || litigantType.equals("0"))//个人
            checkBoxPunishment.setText("罚款(0~50)元");
        else//企业
            checkBoxPunishment.setText("罚款(0~1000)元");
        checkBoxPunishment.setLayoutParams(checkBoxParamLinearLayout);
        radioLinearlayout.addView(checkBoxPunishment);
        checkBoxPunishment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true)
                    punishValue.setVisibility(View.VISIBLE);
                else
                    punishValue.setVisibility(View.GONE);
            }
        });
        checkBoxWarning.setChecked(true);

        View view = new View(ProcedureCaseDetailActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params.setMargins((int) DWZH.dp2pt(ProcedureCaseDetailActivity.this, 5), 0, (int) DWZH.dp2pt(ProcedureCaseDetailActivity.this, 5), 0);
        view.setLayoutParams(params);
        view.setBackgroundColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.linecolor));
        caseBrowseLinearlayout.addView(view);

        punishValue = new TableRow.Builder(this)
                .title("罚款金额")
                .input("请输入罚款金额")
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .content(submitPunishVo == null ? "" : submitPunishVo.getPenam())
                .build();
        caseBrowseLinearlayout.addView(punishValue);

        if (submitPunishVo != null) {
            if (submitPunishVo.getPunishType().equals("01")) {//警告
                checkBoxWarning.setChecked(true);
                punishValue.setVisibility(View.GONE);
            } else
                checkBoxPunishment.setChecked(true);
        }

        penaltyDateRow = new TableRow.Builder(this)
                .title("处罚日期")
                .input("请选择处罚日期")
                .arrowSelect()
                .onSelect(null)
                .build();
        caseBrowseLinearlayout.addView(penaltyDateRow);
        penaltyDateRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ProcedureCaseDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        System.out.println(String.format("%d-%d-%d", i, i1 + 1, i2));
                        if(i1 + 1<10 && i2<10)
                            penaltyDateRow.setTvContent(String.format("%d-0%d-0%d", i, i1 + 1, i2));
                        else if(i1 + 1<10 && i2>9)
                            penaltyDateRow.setTvContent(String.format("%d-0%d-%d", i, i1 + 1, i2));
                        else if(i1 + 1>9 && i2<10)
                            penaltyDateRow.setTvContent(String.format("%d-%d-0%d", i, i1 + 1, i2));
                        else
                            penaltyDateRow.setTvContent(String.format("%d-%d-%d", i, i1 + 1, i2));
                    }
                }, 2016, 1, 1).show();
            }
        });

        if (submitPunishVo != null)
            penaltyDateRow.setTvContent(submitPunishVo.getCaseFidate());

        penaltyLetterNumRow = new TableRow.Builder(this)
                .title("当场处罚决定书文号")
                .input("请输入当场处罚决定书文号")
                .required()
                .content(submitPunishVo == null ? "" : submitPunishVo.getPenDecWriteNo())
                .build();
        caseBrowseLinearlayout.addView(penaltyLetterNumRow);

//        fj =new ImageTableView(ProcedureCaseDetailActivity.this);
//        fj.setTitle("附件");
//        fj.setCanAdd(true);
//        caseBrowseLinearlayout.addView(fj);
    }

    //添加无法修改的基本信息
    private void addBaseViewWithNoModify() {
//        Log.d(TAG, "caseInvestigateTitle.getClueNo() = addBaseView----------1");
        TableRow caseResourceTitle = new TableRow.Builder(this)
                .asTitle("立案来源")
                .build();
        caseBrowseLinearlayout.addView(caseResourceTitle);

        TableRow caseNameTxt = new TableRow.Builder(this)
                .title("案件名称")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmCaseDetail().getCaseName())
                .build();
        caseBrowseLinearlayout.addView(caseNameTxt);

        personType = new TableRow.Builder(this)
                .title("当事人分类")
                .msgWithTitle("")
                .build();
        caseBrowseLinearlayout.addView(personType);

        personOrCompanyLinearlayout = new LinearLayout(this);
        personOrCompanyLinearlayout.setOrientation(LinearLayout.VERTICAL);
        caseBrowseLinearlayout.addView(personOrCompanyLinearlayout);
        ////{0-自然人分类:{自然人:2、本省登记个体工商户:1、非本省登记个体工商户:0}、1-企业分类：{非本省登记企业:0、本省登记企业:1、其他组织:2}}

        if (caseDetail != null) {
            litigantType = caseDetail.getResult().getmLitigtInfoVo().getAssort();
            documentType = caseDetail.getResult().getmLitigtInfoVo().getIsDomination();
            if (litigantType.equals("0")) {//0-自然人分类
                if (documentType.equals("2")) {
                    personType.setContent("自然人");
                } else if (documentType.equals("1")) {
                    personType.setContent("本省登记个体工商户");
                } else if (documentType.equals("0")) {
                    personType.setContent("非本省登记个体工商户");
                }
            } else if (litigantType.equals("1")) {//1-企业分类
                if (documentType.equals("0")) {
                    personType.setContent("非本省登记企业");
                } else if (documentType.equals("1")) {
                    personType.setContent("本省登记企业");
                } else if (documentType.equals("2")) {
                    personType.setContent("其他组织");
                }
            }
            addPersonOrCompanyLinearLayout();
        }
        //
        idTypeRow = new TableRow.Builder(this)
                .title("证件类型(G)")
                .msgWithTitle("")
                .build();
        caseBrowseLinearlayout.addView(idTypeRow);
        if (caseDetail != null && dicVo != null && dicVo.getDicCetfMap() != null) {
            idTypeRow.setContent(dicVo.getDicCetfMap().get(caseDetail.getResult().getmLitigtInfoVo().getCerType()));
        }

        idNumber = new TableRow.Builder(this)
                .title("证件号码(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getCerNo())
                .build();
        caseBrowseLinearlayout.addView(idNumber);


        caseAreaCityRow = new TableRow.Builder(this)
                .title("案发地区（市）")
                .msgWithTitle("")
                .build();
        caseBrowseLinearlayout.addView(caseAreaCityRow);
        districtAreaLinearlayout = new LinearLayout(this);
        districtAreaLinearlayout.setOrientation(LinearLayout.VERTICAL);
        caseBrowseLinearlayout.addView(districtAreaLinearlayout);
        if (caseDetail != null && caseDetail.getResult().getmCaseDetail().getCeCity() != null && caseDetail.getResult().getmDicVo().getDistrictMap() != null) {
            String key = caseDetail.getResult().getmCaseDetail().getCeCity();
            caseAreaCityRow.setContent(caseDetail.getResult().getmDicVo().getDistrictMap().get(key));
            getDistrictMap(key);
        }

        TableRow casePlace = new TableRow.Builder(this)
                .title("案发地点(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmCaseDetail().getCaseSpot())
                .build();
        caseBrowseLinearlayout.addView(casePlace);

        handlePersonRow = new TableRow.Builder(this)
                .title("办案人员")
                .msgWithTitle("")
                .build();
        caseBrowseLinearlayout.addView(handlePersonRow);
        if (caseDetail != null && dicVo != null && dicVo.getUserIdMainMap() != null) {
            String persons = loginBean.getResult().getUserName() + "、" + dicVo.getUserIdMainMap().get(caseDetail.getResult().getmCaseDetail().getUserIdMainName());
            handlePersonRow.setContent(persons);
        }

        caseSourceRow = new TableRow.Builder(this)
                .title("案件来源")
                .msgWithTitle("")
                .build();
        caseBrowseLinearlayout.addView(caseSourceRow);
        if (caseDetail != null && dicVo != null && dicVo.getClueTypeMap() != null) {
            caseSourceRow.setContent(dicVo.getClueTypeMap().get(caseDetail.getResult().getmCaseDetail().getCasesou()));
        }

        TableRow caseValue = new TableRow.Builder(this)
                .title("案值估计（元）")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmCaseDetail().getCaseVal())
                .build();
        caseBrowseLinearlayout.addView(caseValue);


        TableRow illegalActTitle = new TableRow.Builder(this)
                .asTitle("当事人涉嫌违法行为（非必录）")
                .build();
        caseBrowseLinearlayout.addView(illegalActTitle);
        //web上查看详情的时候不需要显示“违法行为大类”
//        illegalBigTypeRow = new TableRow.Builder(this)
//                .title("违法行为大类")
//                .msgWithTitle("")
//                .build();
//        caseBrowseLinearlayout.addView(illegalBigTypeRow);
//        if (submitPunishVo != null && dicVo != null && dicVo.getIlglCateMap() != null) {
//            illegalBigTypeRow.setContent(dicVo.getIlglCateMap().get(submitPunishVo.getIlglActId().substring(0, 2)));
//        }

        //web上查看详情的时候不需要显示“违法行为小类”
//        illegalLinearLayout = new LinearLayout(this);
//        illegalLinearLayout.setOrientation(LinearLayout.VERTICAL);
//        caseBrowseLinearlayout.addView(illegalLinearLayout);

        //违法行为代码LinearLayout
        illegalCodeLinearLayout = new LinearLayout(this);
        illegalCodeLinearLayout.setOrientation(LinearLayout.VERTICAL);
        caseBrowseLinearlayout.addView(illegalCodeLinearLayout);

        if (submitPunishVo != null) {
            String ilglActId = submitPunishVo.getIlglActId();
            if (ilglActId != null && !ilglActId.equals("")) {
                getIllegalType(ilglActId.substring(0, 2));
                getIllegalCode(ilglActId.substring(0, 4));
            }
        }

        illegalBehaviorType = new TableRow.Builder(this)
                .title("违法行为种类(G)")
                .msgWithTitle(submitPunishVo == null ? "" : submitPunishVo.getIllegTypeCon())
                .build();
        caseBrowseLinearlayout.addView(illegalBehaviorType);

        qualitativeTypeRow = new TableRow.Builder(this)
                .title("定性依据(G)")
                .msgWithMutiplyLine("")
                .build();
        if (submitPunishVo != null) {
            selectedLawCode = submitPunishVo.getQuabasis();
            qualitativeTypeRow.setTvContent(submitPunishVo.getReferenceLawCon());
        }
        caseBrowseLinearlayout.addView(qualitativeTypeRow);
        qualitativeTypeRow.getTvContentView().setOnClickListener(new View.OnClickListener() {
            Boolean flag = true;
            @Override
            public void onClick(View view) {
                if(flag){
                    flag = false;
                    qualitativeTypeRow.getTvContentView().setMaxLines(100); // 展开
                }else{
                    flag = true;
                    qualitativeTypeRow.getTvContentView().setMaxLines(4); // 收缩
                }
            }
        });

        punishTypeRow = new TableRow.Builder(this)
                .title("处罚依据(G)")
                .msgWithMutiplyLine("")
                .build();
        if (submitPunishVo != null) {
            selectedPunishCode = submitPunishVo.getPenbasis();
            punishTypeRow.setTvContent(submitPunishVo.getPunishLawCon());
        }
        caseBrowseLinearlayout.addView(punishTypeRow);
        punishTypeRow.getTvContentView().setOnClickListener(new View.OnClickListener() {
            Boolean flag = true;
            @Override
            public void onClick(View view) {
                if(flag){
                    flag = false;
                    punishTypeRow.getTvContentView().setMaxLines(100); // 展开
                }else{
                    flag = true;
                    punishTypeRow.getTvContentView().setMaxLines(4); // 收缩
                }
            }
        });


        TableRow caseReason = new TableRow.Builder(this)
                .title("案由(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmCaseDetail().getCaseReason())
                .build();
        caseBrowseLinearlayout.addView(caseReason);


        TableRow punishMentTitle = new TableRow.Builder(this)
                .asTitle("处罚方式")
                .build();
        caseBrowseLinearlayout.addView(punishMentTitle);

        if (submitPunishVo != null) {
            String punishStyle = "";
            if(submitPunishVo.getPunishType().contains("01")){
                punishStyle = "警告、";
            }
            if(submitPunishVo.getPunishType().contains("02")){
                punishStyle += "罚款：";
                if(submitPunishVo.getPenam()!=null)
                    punishStyle += (submitPunishVo.getPenam() + "元");
            }
            TableRow punishStyleRow = new TableRow.Builder(this)
                    .title("处罚方式")
                    .msgWithTitle(punishStyle)
                    .build();
            caseBrowseLinearlayout.addView(punishStyleRow);
        }

        penaltyDateRow = new TableRow.Builder(this)
                .title("处罚日期")
                .msgWithTitle("")
                .build();
        caseBrowseLinearlayout.addView(penaltyDateRow);

        if (submitPunishVo != null)
            penaltyDateRow.setContent(submitPunishVo.getCaseFidate());

        penaltyLetterNumRow = new TableRow.Builder(this)
                .title("当场处罚决定书文号")
                .msgWithTitle(submitPunishVo == null ? "" : submitPunishVo.getPenDecWriteNo())
                .build();
        caseBrowseLinearlayout.addView(penaltyLetterNumRow);

//        fj =new ImageTableView(ProcedureCaseDetailActivity.this);
//        fj.setTitle("附件");
//        fj.setCanAdd(true);
//        caseBrowseLinearlayout.addView(fj);
    }


    //添加自然人类型时的公共控件
    private void addPersonTypePublicView() {
        TableRow age = new TableRow.Builder(this)
                .title("年龄(G)")
                .input("请输入年龄")
                .required()
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getAge())
                .build();
        personOrCompanyLinearlayout.addView(age);

        sexTypeRow = new TableRow.Builder(this)
                .title("性别(G)")
                .input("性别选择")
                .required()
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (dicVo == null) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "性别类型字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {//final String[]sexStringArray  = {"男", "女"};
                            final String sexStringArray[] = new String[dicVo.getDicSexMap().size()];
                            int i = 0;
                            for (Map.Entry<String, String> entry : dicVo.getDicSexMap().entrySet())
                                sexStringArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                    .items(sexStringArray)
                                    .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            sexTypeRow.setTvContent(sexStringArray[which]);
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .show();
                        }
                    }
                })
                .build();
        personOrCompanyLinearlayout.addView(sexTypeRow);
        if (caseDetail != null && dicVo != null && dicVo.getDicSexMap() != null) {
            sexTypeRow.setTvContent(dicVo.getDicSexMap().get(caseDetail.getResult().getmLitigtInfoVo().getSex()));
        }

        TableRow telephoneNum = new TableRow.Builder(this)
                .title("联系电话(G)")
                .input("输入联系电话")
                .inputType(InputType.TYPE_CLASS_PHONE)
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getTel())
                .build();
        personOrCompanyLinearlayout.addView(telephoneNum);
        TableRow company = new TableRow.Builder(this)
                .title("工作单位(G)")
                .input("输入工作单位")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getWorkunit())
                .build();
        personOrCompanyLinearlayout.addView(company);
        TableRow house = new TableRow.Builder(this)
                .title("住所(G)")
                .input("输入住所地址")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getHouse())
                .build();
        personOrCompanyLinearlayout.addView(house);
        TableRow work = new TableRow.Builder(this)
                .title("职业(G)")
                .input("输入职业")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getOccupation())
                .build();
        personOrCompanyLinearlayout.addView(work);
        TableRow code = new TableRow.Builder(this)
                .title("邮政编码(G)")
                .input("输入邮编")
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getPostalcode())
                .build();
        personOrCompanyLinearlayout.addView(code);
    }

    //添加自然人类型时的公共控件（不能修改
    private void addPersonTypePublicViewWithNoModify() {
        TableRow age = new TableRow.Builder(this)
                .title("年龄(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getAge())
                .build();
        personOrCompanyLinearlayout.addView(age);

        sexTypeRow = new TableRow.Builder(this)
                .title("性别(G)")
                .msgWithTitle("")
                .build();
        personOrCompanyLinearlayout.addView(sexTypeRow);
        if (caseDetail != null && dicVo != null && dicVo.getDicSexMap() != null) {
            sexTypeRow.setContent(dicVo.getDicSexMap().get(caseDetail.getResult().getmLitigtInfoVo().getSex()));
        }

        TableRow telephoneNum = new TableRow.Builder(this)
                .title("联系电话(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getTel())
                .build();
        personOrCompanyLinearlayout.addView(telephoneNum);
        TableRow company = new TableRow.Builder(this)
                .title("工作单位(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getWorkunit())
                .build();
        personOrCompanyLinearlayout.addView(company);
        TableRow house = new TableRow.Builder(this)
                .title("住所(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getHouse())
                .build();
        personOrCompanyLinearlayout.addView(house);
        TableRow work = new TableRow.Builder(this)
                .title("职业(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getOccupation())
                .build();
        personOrCompanyLinearlayout.addView(work);
        TableRow code = new TableRow.Builder(this)
                .title("邮政编码(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getPostalcode())
                .build();
        personOrCompanyLinearlayout.addView(code);
    }

    private void addPersonOrCompanyLinearLayout() {
        if (litigantType == null) {
            Toast.makeText(ProcedureCaseDetailActivity.this, "数据有误！当事人分类不明确！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (litigantType.equals("0")) {//个人
            if (documentType.equals("2"))//自然人
                addPersonalTypeLinearLayout();
            else{//本省登记个体工商户\非本省登记个体工商户
                if(bCanNotModify == false)
                    addRegisterTypeLinearLayout();
                else
                    addRegisterTypeLinearLayoutWithNoModify();
            }

            if (checkBoxPunishment != null)
                checkBoxPunishment.setText("罚款(0~50)元");
        } else {//litigantType.equals("1")--企业
            if(bCanNotModify == false)
                addCompanyTypeLinearLayout();
            else
                addCompanyTypeLinearLayoutWithNoModify();
            if (checkBoxPunishment != null)
                checkBoxPunishment.setText("罚款(0~1000)元");
        }
    }

    //当事人分类:自然人/自然人
    private void addPersonalTypeLinearLayout() {
//        Log.d(TAG, "addPersonalTypeLinearLayout()--------------------2");
        personOrCompanyLinearlayout.removeAllViews();
        if(bCanNotModify == false){
            TableRow typeNaturalPersonName = new TableRow.Builder(this)
                    .title("姓名")
                    .input("请输入姓名")
                    .required()
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLitigtName())
                    .build();
            personOrCompanyLinearlayout.addView(typeNaturalPersonName);
            TableRow typeNaturalPersonAdrress = new TableRow.Builder(this)
                    .title("现居住地/经营场所")
                    .input("请输入现居住地/经营场所")
                    .required()
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getAddress())
                    .build();
            personOrCompanyLinearlayout.addView(typeNaturalPersonAdrress);
            addPersonTypePublicView();
        }else{//bCanNotModify = true
            TableRow typeNaturalPersonName = new TableRow.Builder(this)
                    .title("姓名")
                    .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLitigtName())
                    .build();
            personOrCompanyLinearlayout.addView(typeNaturalPersonName);
            TableRow typeNaturalPersonAdrress = new TableRow.Builder(this)
                    .title("现居住地/经营场所")
                    .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getAddress())
                    .build();
            personOrCompanyLinearlayout.addView(typeNaturalPersonAdrress);
            addPersonTypePublicViewWithNoModify();
        }
    }

    //自然人/个体工商户，自然人/非本省登记个体工商户
    private void addRegisterTypeLinearLayout() {
//        Log.d(TAG, "addRegisterTypeLinearLayout()--------------------1");
        personOrCompanyLinearlayout.removeAllViews();

        if (documentType.equals("1")) {//"本省登记个体工商户"
            inquiryNameRow = new TableRow.Builder(this)
                    .title("个体工商户名称")
                    .editTextSelectorHint("请输入至少四个汉字！ ")
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLitigtName())
                    .arrowSelectWithEditText()
                    .required()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            if (isEditTextEmpty(inquiryNameRow)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "查询名称不能为空！", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("activityType", AICRegisterInquireListActivity.TYPE_PERSONAL_NAME);
                            bundle.putString("param", inquiryNameRow.getContent().toString());
                            bundle.putSerializable("iOrganIdMap", serializableOrganIdMapMap);
                            Intent intent = new Intent(ProcedureCaseDetailActivity.this, AICRegisterInquireListActivity.class);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, AICRegisterInquireListActivity.REQUEST);
                        }
                    })
                    .build();
            personOrCompanyLinearlayout.addView(inquiryNameRow);
            inquiryRegisterRow = new TableRow.Builder(this)
                    .title("注册号")
                    .arrowSelectWithEditText()
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getRegNo())
                    .required()
                    .editTextSelectorHint("请输入完整的注册号！")
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            if (isEditTextEmpty(inquiryRegisterRow)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "查询注册号不能为空！", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("activityType", AICRegisterInquireListActivity.TYPE_PERSONAL_REGISTER);
//                            bundle.putString("param", "130100600000014"); //for test
                            bundle.putString("param", inquiryRegisterRow.getContent().toString());
                            bundle.putSerializable("iOrganIdMap", serializableOrganIdMapMap);
                            Intent intent = new Intent(ProcedureCaseDetailActivity.this, AICRegisterInquireListActivity.class);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, AICRegisterInquireListActivity.REQUEST);

                        }
                    })
                    .build();
            personOrCompanyLinearlayout.addView(inquiryRegisterRow);
        } else {//"非本省登记个体工商户"
            TableRow typeRegisterName = new TableRow.Builder(this)
                    .title("个体工商户名称")
                    .input("请输入个体工商户名称")
                    .required()
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLitigtName())
                    .build();
            personOrCompanyLinearlayout.addView(typeRegisterName);
            TableRow typeRegisterNo = new TableRow.Builder(this)
                    .title("注册号")
                    .input("请输入注册号")
                    .required()
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getRegNo())
                    .build();
            personOrCompanyLinearlayout.addView(typeRegisterNo);
        }
        TableRow typeRegisterCorporation = new TableRow.Builder(this)
                .title("法定代表人经营者")
                .input("请输入法定代表人经营者")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLegalName())
                .build();
        personOrCompanyLinearlayout.addView(typeRegisterCorporation);
        TableRow typeRegisterAddress = new TableRow.Builder(this)
                .title("现居住地/经营场所")
                .input("请输入现居住地/经营场所")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getAddress())
                .build();
        personOrCompanyLinearlayout.addView(typeRegisterAddress);
        addPersonTypePublicView();
    }

    //自然人/个体工商户，自然人/非本省登记个体工商户----无法修改
    private void addRegisterTypeLinearLayoutWithNoModify() {
//        Log.d(TAG, "addRegisterTypeLinearLayout()--------------------1");
        personOrCompanyLinearlayout.removeAllViews();
        //"本省登记个体工商户"\ "非本省登记个体工商户"--都一样，因为只需要显示，不能修改
        inquiryNameRow = new TableRow.Builder(this)
                .title("个体工商户名称")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLitigtName())
                .build();
        personOrCompanyLinearlayout.addView(inquiryNameRow);
        inquiryRegisterRow = new TableRow.Builder(this)
                .title("注册号")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getRegNo())
                .build();
        personOrCompanyLinearlayout.addView(inquiryRegisterRow);

        TableRow typeRegisterCorporation = new TableRow.Builder(this)
                .title("法定代表人经营者")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLegalName())
                .build();
        personOrCompanyLinearlayout.addView(typeRegisterCorporation);
        TableRow typeRegisterAddress = new TableRow.Builder(this)
                .title("现居住地/经营场所")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getAddress())
                .build();
        personOrCompanyLinearlayout.addView(typeRegisterAddress);
        addPersonTypePublicViewWithNoModify();
    }

    //企业/其他组织，企业/非本省登记企业，企业/本省登记企业，
    private void addCompanyTypeLinearLayout() {
//        Log.d(TAG, "addRegisterTypeLinearLayout()--------------------1");
        personOrCompanyLinearlayout.removeAllViews();
        if (documentType.equals("1")) {//"本省登记企业"
            inquiryNameRow = new TableRow.Builder(this)
                    .title("单位名称")
                    .editTextSelectorHint("请输入至少四个汉字！ ")
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLegalName())
                    .arrowSelectWithEditText()
                    .required()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            if (isEditTextEmpty(inquiryNameRow)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "查询单位名称不能为空！", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("activityType", AICRegisterInquireListActivity.TYPE_COMPANY_NAME);
                            bundle.putString("param", inquiryNameRow.getContent().toString());
                            bundle.putSerializable("iOrganIdMap", serializableOrganIdMapMap);
                            Intent intent = new Intent(ProcedureCaseDetailActivity.this, AICRegisterInquireListActivity.class);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, AICRegisterInquireListActivity.REQUEST);
                        }
                    })
                    .build();
            personOrCompanyLinearlayout.addView(inquiryNameRow);
            inquiryRegisterRow = new TableRow.Builder(this)
                    .title("注册号")
                    .arrowSelectWithEditText()
                    .required()
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getRegNo())
                    .editTextSelectorHint("请输入完整的注册号！")
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            if (isEditTextEmpty(inquiryRegisterRow)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "查询注册号不能为空！", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("activityType", AICRegisterInquireListActivity.TYPE_COMPANY_REGISTER);
                            bundle.putString("param", inquiryRegisterRow.getContent().toString());
                            bundle.putSerializable("iOrganIdMap", serializableOrganIdMapMap);
                            Intent intent = new Intent(ProcedureCaseDetailActivity.this, AICRegisterInquireListActivity.class);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, AICRegisterInquireListActivity.REQUEST);
                        }
                    })
                    .build();
            personOrCompanyLinearlayout.addView(inquiryRegisterRow);
        } else {//其他组织/其他组织，其他组织/非本省登记企业
            TableRow typeRegisterName = new TableRow.Builder(this)
                    .title("单位名称")
                    .input("请输入单位名称")
                    .required()
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLegalName())
                    .build();
            personOrCompanyLinearlayout.addView(typeRegisterName);
            TableRow typeRegisterNo = new TableRow.Builder(this)
                    .title("注册号")
                    .input("请输入注册号")
                    .required()
                    .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getRegNo())
                    .build();
            personOrCompanyLinearlayout.addView(typeRegisterNo);
        }
        TableRow typeRegisterCorporation = new TableRow.Builder(this)
                .title("法定代表人经营者")
                .input("请输入法定代表人经营者")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLegalName())
                .build();
        personOrCompanyLinearlayout.addView(typeRegisterCorporation);
        TableRow typeRegisterAddress = new TableRow.Builder(this)
                .title("现居住地/经营场所")
                .input("请输入现居住地/经营场所")
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getAddress())
                .build();
        personOrCompanyLinearlayout.addView(typeRegisterAddress);
        TableRow typeMainCerCode = new TableRow.Builder(this)
                .title("主体身份代码(G)")
                .input("请输入主体身份代码")
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .required()
                .content(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getPripid())
                .build();
        personOrCompanyLinearlayout.addView(typeMainCerCode);
    }

    //企业/其他组织，企业/非本省登记企业，企业/本省登记企业，-----无法修改
    private void addCompanyTypeLinearLayoutWithNoModify() {
        personOrCompanyLinearlayout.removeAllViews();
        //"本省登记企业",其他组织/其他组织，其他组织/非本省登记企业----都一样显示，因为不能修改
        inquiryNameRow = new TableRow.Builder(this)
                .title("单位名称")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLegalName())
                .build();
        personOrCompanyLinearlayout.addView(inquiryNameRow);
        inquiryRegisterRow = new TableRow.Builder(this)
                .title("注册号")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getRegNo())
                .build();
        personOrCompanyLinearlayout.addView(inquiryRegisterRow);
        TableRow typeRegisterCorporation = new TableRow.Builder(this)
                .title("法定代表人经营者")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getLegalName())
                .build();
        personOrCompanyLinearlayout.addView(typeRegisterCorporation);
        TableRow typeRegisterAddress = new TableRow.Builder(this)
                .title("现居住地/经营场所")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getAddress())
                .build();
        personOrCompanyLinearlayout.addView(typeRegisterAddress);
        TableRow typeMainCerCode = new TableRow.Builder(this)
                .title("主体身份代码(G)")
                .msgWithTitle(caseDetail == null ? "" : caseDetail.getResult().getmLitigtInfoVo().getPripid())
                .build();
        personOrCompanyLinearlayout.addView(typeMainCerCode);
    }


    //案发地区（区一级选择）
    private void addCityAreaRowLinearLayout() {
        districtAreaLinearlayout.removeAllViews();
        caseAreaDistrictRow = new TableRow.Builder(this)
                .title("案发地区（区）")
                .content("请选择案发地区")
                .required()
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (districtMap == null) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "案发地区（区）字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            final String districtStringArray[] = new String[districtMap.size()];
                            int i = 0;//final String[] ciityStringArray = {"高新区", "", "长安区", "桥东区", "桥西区"};
                            for (Map.Entry<String, String> entry : districtMap.entrySet())
                                districtStringArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                    .items(districtStringArray)
                                    .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            caseAreaDistrictRow.setTvContent(districtStringArray[which]);
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .show();
                        }
                    }
                })
                .build();
        if (caseDetail != null && caseDetail.getResult().getmCaseDetail().getCedistrictid() != null)
            caseAreaDistrictRow.setTvContent(districtMap.get(caseDetail.getResult().getmCaseDetail().getCedistrictid()));
        districtAreaLinearlayout.addView(caseAreaDistrictRow);
    }

    //案发地区（区一级选择）--无法修改
    private void addCityAreaRowLinearLayoutWithNoModify() {
        districtAreaLinearlayout.removeAllViews();
        caseAreaDistrictRow = new TableRow.Builder(this)
                .title("案发地区（区）")
                .msgWithTitle("")
                .build();
        if (caseDetail != null && caseDetail.getResult().getmCaseDetail().getCedistrictid() != null)
            caseAreaDistrictRow.setContent(districtMap.get(caseDetail.getResult().getmCaseDetail().getCedistrictid()));
        districtAreaLinearlayout.addView(caseAreaDistrictRow);
    }

    //违法行为小类列表
    private void addIllegalSmallTypeLinearLayout() {
        illegalLinearLayout.removeAllViews();
        illegalSmallTypeRow = new TableRow.Builder(this)
                .title("违法行为小类")
                .input("请选择违法行为")
                .arrowSelect()
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        if (illegalSmallTypeMap == null) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "违法行为小类字典为空！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            final String smallIllegalTypeStringArray[] = new String[illegalSmallTypeMap.size()];
                            if (smallIllegalTypeStringArray.length == 0) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "违法行为小类字典为空！", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (submitPunishVo == null || (illegalBigTypeRow != null && illegalBigTypeRow.getTvContent() == null)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请先选择违法行为大类！", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            int i = 0;//final String[] smallIllegalTypeStringArray = {"违反登记管理行为","不正当竞争行为","侵害消费者权益行为","产品质量违法行为"};
                            for (Map.Entry<String, String> entry : illegalSmallTypeMap.entrySet())
                                smallIllegalTypeStringArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                    .items(smallIllegalTypeStringArray)
                                    .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            illegalSmallTypeRow.setTvContent(smallIllegalTypeStringArray[which]);
                                            for (Map.Entry<String, String> entry : illegalSmallTypeMap.entrySet()) {
                                                if (entry.getValue().equals(smallIllegalTypeStringArray[which])) {
                                                    getIllegalCode(entry.getKey());
                                                    if (illegalCodeRow != null)
                                                        illegalCodeRow.setContent("");
                                                    if (submitPunishVo == null) {
                                                        submitPunishVo = new ProcedureCasePunishVo();
                                                    }
                                                    submitPunishVo.setIlglActId("");
                                                }
                                            }
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .show();
                        }
                    }
                })
                .build();
        illegalLinearLayout.addView(illegalSmallTypeRow);
        String ilglActId = submitPunishVo.getIlglActId();
        if (caseDetail != null && illegalSmallTypeMap != null && ilglActId != null && !ilglActId.equals("")) {
            illegalSmallTypeRow.setTvContent(illegalSmallTypeMap.get(ilglActId.substring(0, 4)));
        }
    }

    //违法行为小类列表--无法修改
    private void addIllegalSmallTypeLinearLayoutWithNoModify() {
        illegalLinearLayout.removeAllViews();
        illegalSmallTypeRow = new TableRow.Builder(this)
                .title("违法行为小类")
                .msgWithTitle("")
                .build();
        illegalLinearLayout.addView(illegalSmallTypeRow);
        String ilglActId = submitPunishVo.getIlglActId();
        if (caseDetail != null && illegalSmallTypeMap != null && ilglActId != null && !ilglActId.equals("")) {
            illegalSmallTypeRow.setContent(illegalSmallTypeMap.get(ilglActId.substring(0, 4)));
        }
    }

    //违法行为代码
    private void addIllegalCodeLinearLayout() {
        illegalCodeLinearLayout.removeAllViews();
        if (illegalCodeMap == null) {
            Toast.makeText(ProcedureCaseDetailActivity.this, "违法行为代码字典为空!", Toast.LENGTH_SHORT).show();
            illegalCodeRow = new TableRow.Builder(this)
                    .title("违法行为代码")
                    .build();
        } else {
            illegalCodeRow = new TableRow.Builder(this)
                    .title("违法行为代码")
                    .arrowSelectWithEditText()
                    .onSelect(new TableRow.SelectCallBack() {
                        @Override
                        public void onSelect(TableRow row, int which) {
                            final String illegalCodeArray[] = new String[illegalCodeMap.size()];
                            if (illegalCodeArray.length == 0) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "违法行为代码字典为空!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            int i = 0;
                            for (Map.Entry<String, String> entry : illegalCodeMap.entrySet())
                                illegalCodeArray[i++] = entry.getValue();
                            new MaterialDialog.Builder(ProcedureCaseDetailActivity.this)
                                    .items(illegalCodeArray)
                                    .widgetColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            String code = illegalCodeArray[which];
                                            illegalCodeRow.setContent(code);
                                            illegalBehaviorType.setContent(code.substring(6, code.length()));
                                            for (Map.Entry<String, String> entry : illegalCodeMap.entrySet())
                                                if (illegalCodeArray[which].equals(entry.getValue())) {
                                                    getIllegalLaw(entry.getKey());
                                                    if (submitPunishVo == null) {
                                                        submitPunishVo = new ProcedureCasePunishVo();
                                                    }
                                                    submitPunishVo.setIlglActId(entry.getKey());
                                                }
                                            return true;
                                        }
                                    })
                                    .positiveText("确定")
                                    .positiveColor(ContextCompat.getColor(ProcedureCaseDetailActivity.this, R.color.blue))
                                    .show();
                        }
                    })
                    .build();
        }
        illegalCodeLinearLayout.addView(illegalCodeRow);
        String ilglActId = submitPunishVo.getIlglActId();
        if (caseDetail != null && illegalCodeMap != null && ilglActId != null && !ilglActId.equals("")) {
            illegalCodeRow.setContent(illegalCodeMap.get(ilglActId));
        }

    }

    //违法行为代码--无法修改
    private void addIllegalCodeLinearLayoutWithNoModify() {
        illegalCodeLinearLayout.removeAllViews();
        if (illegalCodeMap == null) {
            Toast.makeText(ProcedureCaseDetailActivity.this, "违法行为代码字典为空!", Toast.LENGTH_SHORT).show();
            illegalCodeRow = new TableRow.Builder(this)
                    .title("违法行为代码")
                    .build();
        } else {
            illegalCodeRow = new TableRow.Builder(this)
                    .title("违法行为代码")
                    .msgWithTitle("")
                    .build();
        }
        illegalCodeLinearLayout.addView(illegalCodeRow);
        String ilglActId = submitPunishVo.getIlglActId();
        if (caseDetail != null && illegalCodeMap != null && ilglActId != null && !ilglActId.equals("")) {
            String content = illegalCodeMap.get(ilglActId);
            illegalCodeRow.setContent(content.substring(0,6));
        }
    }

    @OnClick({R.id.pic1, R.id.pic2, R.id.pic3, R.id.commit_record_Button})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pic1:
                if (attachList != null && attachList.get(0).getAttachFileStr() != null)
                    showPic(1);
                else
                    pickPic(1);
                break;
            case R.id.pic2:
                if (attachList != null && attachList.get(1).getAttachFileStr() != null)
                    showPic(2);
                else
                    pickPic(2);
                break;
            case R.id.pic3:
                if (attachList != null && attachList.get(2).getAttachFileStr() != null)
                    showPic(3);
                else
                    pickPic(3);
                break;
            case R.id.commit_record_Button:
//                if(fj != null){
//                    List<Uri> uriList = fj.getImgSrcs();
//                    for(Uri uri:uriList)
//                        Log.d(TAG, "R.id.commit_record_Button: uri-----uri="+uri);
//                }
                testFj();
                submitCase();
                break;
        }
    }

    private void testFj() {
        if (picOneName == null) {
//            Log.d(TAG, "testFj()-------------- picOneName = null");
            return;
        } else
            Log.d(TAG, "testFj()-------------- picOneName = " + picOneName);
        File file = new File(picOneName);
        if (file.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(picOneName);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
//                Log.d(TAG, "testFj()-------------- picOneName.size() = " + bm.getByteCount());
            }
        }
    }

    //查看图片详情
    public void showPic(final int picNo) {
        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_picdetail, null);
        ImageView picImg = (ImageView) view.findViewById(R.id.img);
        Button pickBtn = (Button) view.findViewById(R.id.btn_one);
        Button deleteBtn = (Button) view.findViewById(R.id.btn_two);
        Button cancelBtn = (Button) view.findViewById(R.id.btn_three);
        picImg.setImageBitmap(FileUtils.stringtoBitmap(attachList.get(picNo - 1).getAttachFileStr()));
        MaterialDialog.Builder builer = new MaterialDialog.Builder(this);
        builer.customView(view, false);
        final MaterialDialog dialog = builer.build();
        dialog.show();
        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                pickPic(picNo);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attachList.get(picNo - 1).setAttachFileStr(null);
                attachList.get(picNo - 1).setAttachId(null);
                attachList.get(picNo - 1).setAttachName(null);
                setBlankPic(picNo);
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    //放置空白图片
    public void setBlankPic(int picNo) {
        switch (picNo) {
            case 1:
                pic1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.addf));
                break;
            case 2:
                pic2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.addf));
                break;
            case 3:
                pic3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.addf));
                break;
        }
    }

    //图片选取对话框
    public void pickPic(final int picNo) {
        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_camera, null);
        Button photoBtn = (Button) view.findViewById(R.id.btn_one);
        Button picBtn = (Button) view.findViewById(R.id.btn_two);
        Button cancelBtn = (Button) view.findViewById(R.id.btn_three);
        MaterialDialog.Builder builer = new MaterialDialog.Builder(this);
        builer.customView(view, false);
        final MaterialDialog dialog = builer.build();
        dialog.show();
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (picNo == 1) {
                    picOneName = FileUtils.saveImageName();
                    File out = new File(picOneName);
                    Uri uri = Uri.fromFile(out);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, ONE_CAMERA);
                } else if (picNo == 2) {
                    picTwoName = FileUtils.saveImageName();
                    File out = new File(picTwoName);
                    Uri uri = Uri.fromFile(out);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, TWO_CAMERA);
                } else if (picNo == 3) {
                    picThreeName = FileUtils.saveImageName();
                    File out = new File(picThreeName);
                    Uri uri = Uri.fromFile(out);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, THREE_CAMERA);
                }
                dialog.dismiss();
            }
        });
        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (picNo == 1) {
                    startActivityForResult(intent, ONE_IMG);
                } else if (picNo == 2) {
                    startActivityForResult(intent, TWO_IMG);
                } else if (picNo == 3) {
                    startActivityForResult(intent, THREE_IMG);
                }
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d(TAG, "onActivityResult!,requestCode = " + requestCode);
//        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
//            case RESULT_OK:
//                Bundle b=data.getExtras(); //data为B中回传的Intent
//                ArrayList<ImageEntry> imageArrayList =(ArrayList<ImageEntry>)b.getSerializable("characterStream");//str即为回传的bitmap的字符流
//                if(imageArrayList!=null && imageArrayList.size()>0)
//                    dynamicWidgetUtils.refreshGridView(imageArrayList);
//                break;
//            default:
//                break;
//        }

        super.onActivityResult(requestCode, resultCode, data);
        //第一个相机
        if (requestCode == ONE_CAMERA && resultCode == Activity.RESULT_OK) {
            saveCameraPic(picOneName);
            FileUtils.saveImageToGallery(this, new File(picOneName));
            return;
        }
        //第二个相机
        if (requestCode == TWO_CAMERA && resultCode == Activity.RESULT_OK) {
            saveCameraPic(picTwoName);
            FileUtils.saveImageToGallery(this, new File(picOneName));
            return;
        }
        //第三个相机
        if (requestCode == THREE_CAMERA && resultCode == Activity.RESULT_OK) {
            saveCameraPic(picThreeName);
            FileUtils.saveImageToGallery(this, new File(picOneName));
            return;
        }
        //图库
        if (resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            if (c == null) {
                Toast.makeText(this, "图片存在问题！", Toast.LENGTH_SHORT).show();
                return;
            }
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            // 获取图片并显示
            String[] nameStr = picturePath.split("/");
            getStrFromFile(picturePath, requestCode);
            if (requestCode == ONE_IMG) {
                picOneName = picturePath;
                attachList.get(0).setAttachName(nameStr[nameStr.length - 1]);
                attachList.get(0).setAttachId(null);
                Picasso.with(this).load(new File(picturePath))
                        .resize(120, 120).centerCrop().into(pic1);
            } else if (requestCode == TWO_IMG) {
                picTwoName = picturePath;
                attachList.get(1).setAttachName(nameStr[nameStr.length - 1]);
                attachList.get(1).setAttachId(null);
                Picasso.with(this).load(new File(picturePath))
                        .resize(120, 120).centerCrop().into(pic2);
            } else {
                picThreeName = picturePath;
                attachList.get(2).setAttachName(nameStr[nameStr.length - 1]);
                attachList.get(2).setAttachId(null);
                Picasso.with(this).load(new File(picturePath))
                        .resize(120, 120).centerCrop().into(pic3);
            }
        }

        if (resultCode == ProcedureCaseLegalBasisListActivity.RESULT) {
            if (requestCode == ProcedureCaseLegalBasisListActivity.LEGAL_BASIS) {//定性依据
                Bundle bundle = data.getExtras();
                List<ProcedureCaseLegalBasis> choiceDataList = (ArrayList<ProcedureCaseLegalBasis>) bundle.getSerializable("ProcedureCaseLegalBasis");
                String text = "";
                selectedLawCode = "";
                for (ProcedureCaseLegalBasis basis : choiceDataList) {
                    text += basis.getLawContent();
                    if (selectedLawCode.equals(""))
                        selectedLawCode = basis.getLawCode();
                    else {
                        if (basis.isMajorBasis()) {
                            selectedLawCode = basis.getLawCode() + "," + selectedLawCode;
                        } else
                            selectedLawCode = selectedLawCode + "," + basis.getLawCode();
                    }
//                    Log.d(TAG, "resultCode == ProcedureCaseLegalBasisListActivity.RESULT-------------- selectedLawCode = " + selectedLawCode);
                }

                qualitativeTypeRow.setTvContent(text);
                submitPunishVo.setQuabasis(selectedLawCode);
                submitPunishVo.setReferenceLawCon(text);
            }
            if (requestCode == ProcedureCaseLegalBasisListActivity.PENALTY_BASIS) {//处罚依据
                Bundle bundle = data.getExtras();
                List<ProcedureCaseLegalBasis> choiceDataList = (ArrayList<ProcedureCaseLegalBasis>) bundle.getSerializable("ProcedureCaseLegalBasis");
                String text = "";
                selectedPunishCode = "";
                for (ProcedureCaseLegalBasis basis : choiceDataList) {
                    if (selectedPunishCode.equals(""))
                        selectedPunishCode = basis.getLawCode();
                    else
                        selectedPunishCode = basis.getLawCode() + "," + selectedPunishCode;
                    text += basis.getLawContent();
                }
                punishTypeRow.setTvContent(text);
                submitPunishVo.setPenbasis(selectedPunishCode);
                submitPunishVo.setPunishLawCon(text);
            }
//            Log.d(TAG, "resultCode == ProcedureCaseLegalBasisListActivity.RESULT-------------- selectedPunishCode = " + selectedPunishCode);

        }

        if (requestCode == AICRegisterInquireListActivity.REQUEST && data != null) {
            Bundle bundle = data.getExtras();
            String type = bundle.getString("activityType");
            String entityId = bundle.getString("entityId");
//            Log.d(TAG, "checkValue() --------------- AICRegisterInquireListActivity .entityId = " + entityId);
            if (type.equals(AICRegisterInquireListActivity.TYPE_PERSONAL_NAME) || type.equals(AICRegisterInquireListActivity.TYPE_PERSONAL_REGISTER))
                queryPersonSelected(entityId);
            else
                queryEtpsSelected(entityId);
        }
    }

    public void saveCameraPic(final String name) {
//        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(ProcedureCaseDetailActivity.this);

        String imageUri = "file://" + name;
        ImageLoader.getInstance().loadImage(imageUri, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // 获取当前时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String timeString = formatter.format(curDate);

                // 添加水印
                Bitmap resizedBitmap = loadedImage.copy(Bitmap.Config.ARGB_8888, true);
                Canvas cv = new Canvas(resizedBitmap);
                Paint p = new Paint();
                Typeface font = Typeface.create("宋体", Typeface.BOLD);
                p.setColor(Color.BLUE);
                p.setTypeface(font);
                p.setTextSize(36);
                cv.drawBitmap(resizedBitmap, 0, 0, p);
                cv.drawText(timeString, 40, cv.getHeight() - 100, p);
                cv.save(Canvas.ALL_SAVE_FLAG);
                cv.restore();

                try {
                    OutputStream stream = new FileOutputStream(new File(name));
                    resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageView pic;
                String[] nameStr = name.split("/");
                if (name.equals(picOneName)) {
                    pic = pic1;
                    attachList.get(0).setAttachName(nameStr[nameStr.length - 1]);
                    attachList.get(0).setAttachFileStr(FileUtils.bitmaptoString(resizedBitmap, 80));
                    attachList.get(0).setAttachId(null);
                } else if (name.equals(picTwoName)) {
                    pic = pic2;
                    attachList.get(1).setAttachName(nameStr[nameStr.length - 1]);
                    attachList.get(1).setAttachFileStr(FileUtils.bitmaptoString(resizedBitmap, 80));
                    attachList.get(1).setAttachId(null);
                } else {
                    pic = pic3;
                    attachList.get(2).setAttachName(nameStr[nameStr.length - 1]);
                    attachList.get(2).setAttachFileStr(FileUtils.bitmaptoString(resizedBitmap, 80));
                    attachList.get(2).setAttachId(null);
                }

                // 显示图
                Picasso.with(ProcedureCaseDetailActivity.this)
                        .load(new File(name))
                        .skipMemoryCache().resize(120, 120)
                        .centerCrop().into(pic);
                // 清除缓存
                resizedBitmap.recycle();
                resizedBitmap = null;
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
            }
        });
    }

    //读取文件获取为字符串
    public void getStrFromFile(final String name, final int code) {

        String imageUri = "file://" + name;
        ImageLoader.getInstance().loadImage(imageUri, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                switch (code) {
                    case ONE_IMG:
                        attachList.get(0).setAttachFileStr(FileUtils.bitmaptoString(loadedImage, 80));
                        break;
                    case TWO_IMG:
                        attachList.get(1).setAttachFileStr(FileUtils.bitmaptoString(loadedImage, 80));
                        break;
                    case THREE_IMG:
                        attachList.get(2).setAttachFileStr(FileUtils.bitmaptoString(loadedImage, 80));
                        break;
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    ////检查ClearEditText(带X的输入框)是否输入
    public boolean isClearEditTextEmpty(TableRow tv) {
        boolean flag = false;
        if (tv.getInput() == null
                || tv.getInput().trim().equals("")) {
            flag = true;
        }
        return flag;
    }

    //检查EditText是否输入
    public boolean isEditTextEmpty(TableRow tv) {
        boolean flag = false;
        if (tv.getContent() == null
                || tv.getContent().trim().equals("")) {
            flag = true;
        }
        return flag;
    }

    ////检查selectText是否输入
    public boolean isSelectTextEmpty(TableRow tv) {
        boolean flag = false;
        if (tv.getTvContent() == null
                || tv.getTvContent().trim().equals("")) {
            flag = true;
        }
        return flag;
    }

    private String getKeyByValue(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value))
                return entry.getKey();
        }
        return "";
    }

    private boolean checkValue() {

        if (submitCase == null)
            submitCase = new ProcedureCaseSubmit();

        boolean isPerson;//是否为个人，true---个人， false--企业
        if (litigantType == null) {
            Toast.makeText(ProcedureCaseDetailActivity.this, "请选择当事人分类", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (litigantType.equals("0"))
                isPerson = true;
            else
                isPerson = false;

            submitCase.getmLitigtInfoVo().setAssort(litigantType);
            submitCase.getmLitigtInfoVo().setIsDomination(documentType);
            if (documentType.equals("1")) {//已注册的个人或者企业
                if ((regEntityId == null) || (regOrganId == null) || (regEtpsTypeGb == null)) {
                    Toast.makeText(ProcedureCaseDetailActivity.this, "请通过查询输入当事人信息！！！", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    submitCase.getmLitigtInfoVo().setEntityId(regEntityId);
                    submitCase.getmLitigtInfoVo().setRegOrganId(regOrganId);
                    submitCase.getmLitigtInfoVo().setEtpsTypeGb(regEtpsTypeGb);
                    submitCase.getmLitigtInfoVo().setUniScid(regUniScid);
                    if (litigantType.equals("0"))
                        submitCase.getmLitigtInfoVo().setPripid(regPripId);
                }
            }
        }
//        Log.d(TAG, "checkValue() --------------- caseBrowseLinearlayout.getChildCount() = " + caseBrowseLinearlayout.getChildCount());
        for (int i = 0; i < caseBrowseLinearlayout.getChildCount(); i++) {
            Object view = caseBrowseLinearlayout.getChildAt(i);
            if (view instanceof TableRow) {
//                Log.d(TAG, "checkValue() --------------- view is instanceof TableRow");
                TableRow widget = (TableRow) view;
                Log.d(TAG, "checkValue() --------------- widget.getTitle() = " + widget.getTitle());
//                Log.d(TAG, "checkValue() --------------- widget.getContent() = " + widget.getContent());
                String title = widget.getTitle();
                if (widget.getTitle() == null)//蓝色标题，不用取值------------1960(父一级)
                    continue;

                if (widget.getTitle().equals("案件名称*")) {
                    if (isClearEditTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请输入案件名称", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        submitCase.getmCaseDetail().setCaseName(widget.getClearEditText());
                        continue;
                    }
                } else if (widget.getTitle().equals("当事人分类")) {
                    if (isSelectTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请选择当事人分类", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
//                        submitCase.getmLitigtInfoVo().setAssort(widget.getContent());
                        continue;
                    }
                } else if (widget.getTitle().equals("证件类型(G)*")) {
                    if (isSelectTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请选择证件类型", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        submitCase.getmLitigtInfoVo().setCerType(getKeyByValue(dicVo.getDicCetfMap(), widget.getTvContent()));
                        continue;
                    }
                } else if (widget.getTitle().equals("证件号码(G)*")) {
                    if (isClearEditTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请输入证件号码", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        submitCase.getmLitigtInfoVo().setCerNo(widget.getClearEditText());
                        continue;
                    }
                } else if (widget.getTitle().equals("案发地区（市）*")) {
                    if (isSelectTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请选择案发地区（市）", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        if (dicVo != null) {
                            for (Map.Entry<String, String> entry : dicVo.getDistrictMap().entrySet())
                                if (widget.getTvContent().equals(entry.getValue()))
                                    submitCase.getmCaseDetail().setCeCity(entry.getKey());
                        } else if (caseDetail != null && caseDetail.getResult().getmDicVo().getDistrictMap() != null) {
                            for (Map.Entry<String, String> entry : caseDetail.getResult().getmDicVo().getDistrictMap().entrySet())
                                if (widget.getTvContent().equals(entry.getValue()))
                                    submitCase.getmCaseDetail().setCeCity(entry.getKey());
                        } else
                            submitCase.getmCaseDetail().setCeCity(widget.getTvContent());

                        if (isSelectTextEmpty(caseAreaDistrictRow)) {
                            Toast.makeText(ProcedureCaseDetailActivity.this, "请选择案发地区（区）", Toast.LENGTH_SHORT).show();
                            return false;
                        } else {
                            String district = caseAreaDistrictRow.getTvContent();
                            if (districtMap != null) {
                                for (Map.Entry<String, String> entry : districtMap.entrySet())
                                    if (caseAreaDistrictRow.getTvContent().equals(entry.getValue()))
                                        submitCase.getmCaseDetail().setCeCity(entry.getKey());
                            }
                        }
                        continue;
                    }
                } else if (widget.getTitle().equals("案发地点(G)*")) {
                    if (isClearEditTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请输入案发地点", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        submitCase.getmCaseDetail().setCaseSpot(widget.getClearEditText());
                        continue;
                    }
                } else if (widget.getTitle().equals("办案人员*")) {
                    if (isSelectTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请选择办案人员", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        submitCase.getmCaseDetail().setUserIdMainName(getKeyByValue(dicVo.getUserIdMainMap(), widget.getTvContent()));
                        continue;
                    }
                } else if (widget.getTitle().equals("案件来源*")) {
                    if (isSelectTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请选择案件来源", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        submitCase.getmCaseDetail().setCasesou(getKeyByValue(dicVo.getClueTypeMap(), widget.getTvContent()));
                        continue;
                    }
                } else if (widget.getTitle().equals("案值估计（元）*")) {
                    if (isClearEditTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请输入案值估计", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        submitCase.getmCaseDetail().setCaseVal(widget.getClearEditText());
                        continue;
                    }
                } else if (widget.getTitle().equals("案由(G)*")) {
                    if (isClearEditTextEmpty(widget)) {
                        Toast.makeText(ProcedureCaseDetailActivity.this, "请输入案由", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        submitCase.getmCaseDetail().setCaseReason(widget.getClearEditText());
                        continue;
                    }
                }


            } else if (view instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) view;
//                Log.d(TAG, "checkValue() --------view instanceof LinearLayout------- linearLayout.getChildCount() = " + linearLayout.getChildCount());
                for (int j = 0; j < linearLayout.getChildCount(); j++) {
                    Object subjectView = linearLayout.getChildAt(j);
                    if (subjectView instanceof TableRow) {
//                        Log.d(TAG, "checkValue() --------------- subjectView is instanceof TableRow");
                        TableRow widget = (TableRow) subjectView;
                        String title = widget.getTitle();
//                        Log.d(TAG, "checkValue() --------------- widget.getTitle() = " + widget.getTitle());
                        if (widget.getTitle() == null)//蓝色标题，不用取值------------------2116（子一级）
                            continue;
                        //当事人分类 ------- start
                        if (widget.getTitle().equals("姓名*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setLitigtName(widget.getClearEditText());
                                continue;
                            }
                        }else if (widget.getTitle().equals("单位名称*")) {
                            if (documentType.equals("1")) {//"本省登记过的企业"
                                if (isEditTextEmpty(widget)) {
                                    Toast.makeText(ProcedureCaseDetailActivity.this, "请输入单位名称", Toast.LENGTH_SHORT).show();
                                    return false;
                                } else {
                                    submitCase.getmLitigtInfoVo().setLitigtName(widget.getContent());
                                    continue;
                                }
                            }else{
                                if (isClearEditTextEmpty(widget)) {
                                    Toast.makeText(ProcedureCaseDetailActivity.this, "请输入单位名称", Toast.LENGTH_SHORT).show();
                                    return false;
                                } else {
                                    submitCase.getmLitigtInfoVo().setLitigtName(widget.getClearEditText());
                                    continue;
                                }
                            }
                        }else if (widget.getTitle().equals("现居住地/经营场所*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入现居住地/经营场所", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setAddress(widget.getClearEditText());
                                continue;
                            }
                        } else if (widget.getTitle().equals("个体工商户名称*")) {
                            if (documentType.equals("1")) {//"本省登记过的工商户"
                                if (isEditTextEmpty(widget)) {
                                    Toast.makeText(ProcedureCaseDetailActivity.this, "请输入个体工商户名称", Toast.LENGTH_SHORT).show();
                                    return false;
                                } else {
                                    submitCase.getmLitigtInfoVo().setLitigtName(widget.getContent());
                                    continue;
                                }
                            }else{
                                if (isClearEditTextEmpty(widget)) {
                                    Toast.makeText(ProcedureCaseDetailActivity.this, "请输入个体工商户名称", Toast.LENGTH_SHORT).show();
                                    return false;
                                } else {
                                    submitCase.getmLitigtInfoVo().setLitigtName(widget.getClearEditText());
                                    continue;
                                }
                            }
                        } else if (widget.getTitle().equals("注册号*")) {
                            if (documentType.equals("1")) {//"本省登记过商户"
                                if (isEditTextEmpty(widget)) {
                                    Toast.makeText(ProcedureCaseDetailActivity.this, "请输入注册号", Toast.LENGTH_SHORT).show();
                                    return false;
                                } else {
                                    submitCase.getmLitigtInfoVo().setRegNo(widget.getContent());
                                    continue;
                                }
                            }else{
                                if (isClearEditTextEmpty(widget)) {
                                    Toast.makeText(ProcedureCaseDetailActivity.this, "请输入注册号", Toast.LENGTH_SHORT).show();
                                    return false;
                                } else {
                                    submitCase.getmLitigtInfoVo().setRegNo(widget.getClearEditText());
                                    continue;
                                }
                            }
                        } else if (widget.getTitle().equals("法定代表人经营者*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入法定代表人经营者", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setLegalName(widget.getClearEditText());
                                continue;
                            }
                        }  else if (widget.getTitle().equals("主体身份代码(G)*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入主体身份代码", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setPripid(widget.getClearEditText());
                                continue;
                            }
                        } else if (widget.getTitle().equals("案发地区（区）*")) {
                            if (isSelectTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请选择案发地区（区）", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                if (districtMap != null) {
                                    for (Map.Entry<String, String> entry : districtMap.entrySet())
                                        if (widget.getTvContent().equals(entry.getValue()))
                                            submitCase.getmCaseDetail().setCedistrictid(entry.getKey());
                                } else
                                    submitCase.getmCaseDetail().setCedistrictid(widget.getTvContent());
                                continue;
                            }
                        } else if (isPerson == true && widget.getTitle().equals("年龄(G)*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入年龄", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setAge(widget.getClearEditText());
                                continue;
                            }
                        } else if (isPerson == true && widget.getTitle().equals("性别(G)*")) {
                            if (isSelectTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                if (dicVo != null && dicVo.getDicSexMap() != null)
                                    submitCase.getmLitigtInfoVo().setSex(getKeyByValue(dicVo.getDicSexMap(), widget.getTvContent()));
                                continue;
                            }
                        } else if (isPerson == true && widget.getTitle().equals("联系电话(G)*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入联系电话", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setTel(widget.getClearEditText());
                                continue;
                            }
                        } else if (isPerson == true && widget.getTitle().equals("工作单位(G)*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入工作单位", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setWorkunit(widget.getClearEditText());
                                continue;
                            }
                        } else if (isPerson == true && widget.getTitle().equals("住所(G)*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入住所地址", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setHouse(widget.getClearEditText());
                                continue;
                            }
                        } else if (isPerson == true && widget.getTitle().equals("职业(G)*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入职业", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setOccupation(widget.getClearEditText());
                                continue;
                            }
                        } else if (isPerson == true && widget.getTitle().equals("邮政编码(G)*")) {
                            if (isClearEditTextEmpty(widget)) {
                                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入邮政编码", Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                submitCase.getmLitigtInfoVo().setPostalcode(widget.getClearEditText());
                                continue;
                            }
                        }
                        //当事人分类 ------- end
                    }
                }
            }
        }

        //处罚定性基本信息检查
        if (submitPunishVo == null)
            submitPunishVo = new ProcedureCasePunishVo();

        if (illegalBehaviorType.getContent() != null)
            submitPunishVo.setIllegTypeCon(illegalBehaviorType.getContent());

        //处罚方式信息检查
        String punishVo = "";
        if (checkBoxWarning.isChecked())
            punishVo = "01";
        if (checkBoxPunishment.isChecked()) {
            if(!punishVo.equals(""))
                punishVo += ",";
            punishVo += "02";
            submitPunishVo.setPunishType(punishVo);
            if (isClearEditTextEmpty(punishValue)) {
                Toast.makeText(ProcedureCaseDetailActivity.this, "请输入罚款金额", Toast.LENGTH_SHORT).show();
                return false;
            } else
                submitPunishVo.setPenam(punishValue.getClearEditText());
        }
        if (submitPunishVo.getPunishType() == null || submitPunishVo.getPunishType().equals("")) {
            Toast.makeText(ProcedureCaseDetailActivity.this, "请选择处罚方式", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (penaltyDateRow.getTvContent() == null || penaltyDateRow.getTvContent().equals("")) {
            Toast.makeText(ProcedureCaseDetailActivity.this, "请选择处罚日期", Toast.LENGTH_SHORT).show();
            return false;
        } else
            submitPunishVo.setCaseFidate(penaltyDateRow.getTvContent());

        if (isClearEditTextEmpty(penaltyLetterNumRow)) {
            Toast.makeText(ProcedureCaseDetailActivity.this, "请输入当场处罚决定书文号", Toast.LENGTH_SHORT).show();
            return false;
        } else
            submitPunishVo.setPenDecWriteNo(penaltyLetterNumRow.getClearEditText());
        submitCase.setmPunishVo(submitPunishVo);

        return true;
    }

    /**
     * EditText竖直方向是否可以滚动
     * @param editText  需要判断的EditText
     * @return  true：可以滚动   false：不可以滚动
     */
    private boolean canVerticalScroll(TextView editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() -editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if(scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

}
