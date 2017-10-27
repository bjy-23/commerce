package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Dept;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.TjApi;
import com.wondersgroup.commerce.service.YnApi;
import com.wondersgroup.commerce.teamwork.statistics.bean.BanJie;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 消费维权投诉举报统计
 */
public class BanJieActivity extends AppCompatActivity {
    @BindView(R.id.mid_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.startDate)
    TextView mStartDate;
    @BindView(R.id.endDate)
    TextView mEndDate;
    @BindView(R.id.linear_date)
    LinearLayout mLinearDate;
    @BindView(R.id.totalNum)
    TextView totalNum;
    @BindView(R.id.bjl_ts)
    TextView bjlTs;
    @BindView(R.id.bjl_jb)
    TextView bjlJb;
    @BindView(R.id.bjli_ts)
    TextView bjliTs;
    @BindView(R.id.bjli_jb)
    TextView bjliJb;
    @BindView(R.id.wbjl_ts)
    TextView wbjlTs;
    @BindView(R.id.wbjl_jb)
    TextView wbjlJb;
    @BindView(R.id.djl_ts)
    TextView djlTs;
    @BindView(R.id.djl_jb)
    TextView djlJb;
    @BindView(R.id.fsl_ts)
    TextView fslTs;
    @BindView(R.id.fsl_jb)
    TextView fslJb;
    @BindView(R.id.iv_select)
    ImageView mIVSelect;
    private TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
    private HashMap<String, String> params = new HashMap<>();
    private List<Dept.OrganInfo> organInfoList = new ArrayList<>();
    private List<String> organNameList = new ArrayList<>();
    private boolean isZCBJ = true;//是否只查本级
    private AnnalsDatePopup popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_jie);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("消费维权投诉举报统计");
        location.setText(loginBean.getResult().getOrganName());
        mStartDate.setText(getFirstDay());
        mEndDate.setText(getToday());
        params.put("organId", loginBean.getResult().getOrganId());
        params.put("startDate", AnnalsDatePopup.StringToDate(getFirstDay(), "yyyy/MM/dd", "yyyy-MM-dd"));
        params.put("endDate", AnnalsDatePopup.StringToDate(getToday(), "yyyy/MM/dd", "yyyy-MM-dd"));
        params.put("allFlag", isZCBJ ? "0" : "1");//1表示获取全局数据，0表示只查本级。
        initData();
    }

    @OnClick(R.id.location)
    void OnLocationClick(View view) {
//        MaterialDialog dialog = new MaterialDialog.Builder(this)
//                .items(organNameList)
//                .itemsCallback(new MaterialDialog.ListCallback() {
//                    @Override
//                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
//                        location.setText(organNameList.get(which));
//                        params.put("organId", organInfoList.get(which).getOrganId());
//                        initData();
//                    }
//                }).build();
//        dialog.show();
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
        startActivityForResult(new Intent(this, DeptActivity.class), 0);
    }

    @OnClick(R.id.only_local)
    void onZCBJClick(View view) {
        if (isZCBJ) {
            mIVSelect.setImageResource(R.mipmap.dx_unselected);
            isZCBJ = false;
        } else {
            mIVSelect.setImageResource(R.mipmap.dx_selected);
            isZCBJ = true;
        }
        params.put("allFlag", isZCBJ ? "0" : "1");
        initData();
    }

    @OnClick(R.id.linear_date)
    void onDateClick(View view) {
        String dateS = mStartDate.getText().toString();
        String dateE = mEndDate.getText().toString();
        popup = new AnnalsDatePopup(this)
                .setEndDate(dateE)
                .setStartDate(dateS);
        popup.setDismissListener(new AnnalsDatePopup.OnDismissListener() {
            @Override
            public void onDismiss(String startDate, String endDate) {
                if (TextUtils.isEmpty(startDate)) {
                    mStartDate.setText("请选择");
                } else {
                    mStartDate.setText(startDate);
                    startDate = AnnalsDatePopup.StringToDate(startDate, "yyyy/MM/dd", "yyyy-MM-dd");
                }
                if (TextUtils.isEmpty(endDate)) {
                    mEndDate.setText("请选择");
                } else {
                    mEndDate.setText(endDate);
                    endDate = AnnalsDatePopup.StringToDate(endDate, "yyyy/MM/dd", "yyyy-MM-dd");
                }
                params.put("startDate", startDate);
                params.put("endDate", endDate);
                initData();
            }
        });
        popup.show(divider);
    }

    private TjApi createApi() {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("User-Agent", "yn-Android")
                        .header("Content-Type", "application/json;charset=UTF-8")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.1.241.119:8080/mds/services/mdsWebService/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(TjApi.class);
    }

    private String decimalFormat(String value) {
        if (!TextUtils.isEmpty(value)) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
            return df.format(Double.valueOf(value) * 100);
        }
        return "0";
    }

    private void initData() {
        MyProgressDialog.show(this);
        ApiManager.tjApi.getConsumerInfo(params).enqueue(new Callback<BanJie>() {
            @Override
            public void onResponse(Response<BanJie> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                BanJie result = response.body();
                if (result != null && result.getResult() != null) {
                    BanJie.BanJieResult detail = result.getResult();
                    totalNum.setText(detail.getTOTAL());
                    bjlTs.setText(decimalFormat(detail.getTS_END_PERCENT()));
                    bjlJb.setText(decimalFormat(detail.getJB_END_PERCENT()));
                    bjliTs.setText(detail.getTS_Y_DEAL());
                    bjliJb.setText(detail.getJB_Y_DEAL());
                    wbjlTs.setText(detail.getTS_N_DEAL());
                    wbjlJb.setText(detail.getJB_N_DEAL());
                    djlTs.setText(detail.getTS_REG());
                    djlJb.setText(detail.getJB_REG());
                    fslTs.setText(detail.getTS_ASS());
                    fslJb.setText(detail.getJB_ASS());
                } else {
                    showMsg("");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                showMsg(getResources().getString(R.string.error_connect));
            }
        });
    }

    private void getAllDept() {
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
                        showMsg(getResources().getString(R.string.error_connect));
                    }
                } else {
                    getResources().getString(R.string.error_data);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showMsg(getResources().getString(R.string.error_connect));
            }
        });
    }

    private void assambleDept(List<Dept.OrganInfo> list) {
        Dept.OrganInfo organInfo = new Dept().new OrganInfo();
        organInfo.setOrganId(loginBean.getResult().getOrganId());
        organInfo.setOrganName(loginBean.getResult().getOrganName());
        organInfoList.add(organInfo);
        organNameList.add(organInfo.getOrganName());
        for (Dept.OrganInfo info : list) {
            if (info.getRank().equals("2")) {
                organInfoList.add(info);
                organNameList.add(info.getOrganName());
            }
        }
    }

    private void showMsg(String msg) {
        Toast.makeText(BanJieActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回当前年份的第一天
     *
     * @return
     */
    public static String getFirstDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        String firstDay = calendar.get(Calendar.YEAR) + "/01/01";
        return firstDay;
    }

    private String getToday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Date());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b = data.getExtras(); //data为B中回传的Intent
                String organId = b.getString("organId");//str即为回传的值
                String organName = b.getString("organName");
                location.setText(organName);
                params.put("organId", organId);
                initData();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popup != null) {
            popup.dismiss();
        }
    }
}
