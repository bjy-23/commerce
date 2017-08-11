package com.wondersgroup.commerce.teamwork.wywork;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsBean;
import com.wondersgroup.commerce.teamwork.wywork.javabean.EtpsFirstBean;
import com.wondersgroup.commerce.teamwork.wywork.javabean.StatisticsBean;
import com.wondersgroup.commerce.teamwork.wywork.javabean.StatisticsFirstBean;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SumIndexActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarText;
    private TextView endDate;
    private TextView startDate;

    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;
    private LinearLayout layout5;
    private TextView type1;
    private TextView type2;
    private TextView type3;
    private TextView type4;
    private TextView type5;

    private TextView type1_1;
    private TextView type2_1;
    private TextView type3_1;
    private TextView type4_1;
    private TextView type5_1;
    private RootAppcation application;
    public static final int SHOW_RESPONSE = 1;
    public static final int SHOW_ERROR = 2;
    public static final int SHOW_RESPONSE_COUNT = 3;
    private Dialog progressDialog;
    private Gson gson = new Gson();
    private List<EtpsBean> etpsBeans;
    private Button button;
    private String startDateTemp = "";
    private String endDateTemp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ywtj);
        application = (RootAppcation) getApplicationContext();
        //设置toolbar
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        toolbarText.setText(Constants.ywtj);
        //读取对话框
//        progressDialog = LoadingDialog.showNotCancelable(this);
//        progressDialog.dismiss();
        endDate = (TextView) this.findViewById(R.id.endDate);
        endDate.setText("");
        endDate.setOnTouchListener(new DateOnTouch(this, endDate));
        startDate = (TextView) this.findViewById(R.id.startDate);
        startDate.setText("");
        startDate.setOnTouchListener(new DateOnTouch(this, startDate));
        type1 = (TextView) this.findViewById(R.id.type1);
        type2 = (TextView) this.findViewById(R.id.type2);
        type3 = (TextView) this.findViewById(R.id.type3);
        type4 = (TextView) this.findViewById(R.id.type4);
        type5 = (TextView) this.findViewById(R.id.type5);
        layout1 = (LinearLayout) this.findViewById(R.id.layout1);
        layout2 = (LinearLayout) this.findViewById(R.id.layout2);
        layout3 = (LinearLayout) this.findViewById(R.id.layout3);
        layout4 = (LinearLayout) this.findViewById(R.id.layout4);
        layout5 = (LinearLayout) this.findViewById(R.id.layout5);
        type1_1 = (TextView) this.findViewById(R.id.type1_1);
        type2_1 = (TextView) this.findViewById(R.id.type2_1);
        type3_1 = (TextView) this.findViewById(R.id.type3_1);
        type4_1 = (TextView) this.findViewById(R.id.type4_1);
        type5_1 = (TextView) this.findViewById(R.id.type5_1);
        type1.setTag("0");
        type2.setTag("0");
        type3.setTag("0");
        type4.setTag("0");
        type5.setTag("0");
        type1_1.setTag("0");
        type2_1.setTag("0");
        type3_1.setTag("0");
        type4_1.setTag("0");
        type5_1.setTag("0");

        layout1.setOnClickListener(new SumOnClickListener("1", "日常检查", type1,
                type1_1));
        layout2.setOnClickListener(new SumOnClickListener("2", "重点监管", type2,
                type2_1));
        layout3.setOnClickListener(new SumOnClickListener("3", "专项整治", type3,
                type3_1));
        layout4.setOnClickListener(new SumOnClickListener("4", "无照管理", type4,
                type4_1));
        layout5.setOnClickListener(new SumOnClickListener("5", "服务企业", type5,
                type5_1));

        button = (Button) this.findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!compareDate(startDate.getText().toString(), endDate
                        .getText().toString())) {
                    Toast.makeText(SumIndexActivity.this, "请确认终止时间晚于起始时间",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
//                progressDialog.show();
                MyProgressDialog.show(SumIndexActivity.this);
                String netAddress = Url.QJ_IN_USE + "statistics";
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("startDate", startDate.getText().toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    jsonObject.put("endDate", endDate.getText().toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    //TODO 登录人员信息
                    jsonObject.put("submitUser", application.getLoginUserInfo()
                            .getUserId());
                    jsonObject.put("organId", application.getLoginUserInfo()
                            .getOrganId());
                } catch (//JSON
                        Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                HttpClientUtil.callWebService(jsonObject.toString(),
                        netAddress, new HttpCallbackListener() {

                            @Override
                            public void onFinish(String response) {
                                Message message = new Message();
                                message.what = SHOW_RESPONSE_COUNT;
                                message.obj = response.toString();
                                handler.sendMessage(message);
                            }

                            @Override
                            public void onError(Exception e) {
                                Message message = new Message();
                                message.what = SHOW_ERROR;
                                message.obj = e.toString();
                                handler.sendMessage(message);

                            }
                        });

            }
        });
    }

    class SumOnClickListener implements OnClickListener {
        private String checkType;
        private String checkTypeName;

        private TextView countUser;
        private TextView countOrgan;

        public SumOnClickListener(String checkType, String checkTypeName,
                                  TextView countUser, TextView countOrgan) {
            super();
            this.checkType = checkType;
            this.checkTypeName = checkTypeName;
            this.countUser = countUser;
            this.countOrgan = countOrgan;

        }

        @Override
        public void onClick(View v) {


            if (!countUser.getTag().toString().equals("0")) {

//                progressDialog.show();
                MyProgressDialog.show(SumIndexActivity.this);
                String deptId = application.getLoginUserInfo().getDeptId();
                application.setCountUser(countUser.getTag().toString());
                application.setCountOrgan(countOrgan.getTag().toString());
                application.setCheckTypeName(checkTypeName);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("submitUser", application.getLoginUserInfo()
                            .getUserId());
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    jsonObject.put("tmpFlag", "0");
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    jsonObject.put("deptId", deptId);
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    jsonObject.put("startCheckDate", startDateTemp);
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    jsonObject.put("endCheckDate", endDateTemp);
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (!checkType.equals("7")) {
                    try {
                        jsonObject.put("checkType", checkType);
                    } catch (JSONException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                String address = Url.QJ_IN_USE + "searchList";
                HttpClientUtil.callWebService(jsonObject.toString(), address,
                        new HttpCallbackListener() {

                            @Override
                            public void onFinish(String response) {
//                                MyProgressDialog.dismiss();
                                Message message = new Message();
                                message.what = SHOW_RESPONSE;
                                message.obj = response.toString();
                                handler.sendMessage(message);
                            }

                            @Override
                            public void onError(Exception e) {
//                                MyProgressDialog.dismiss();
                                Message message = new Message();
                                message.what = SHOW_ERROR;
                                message.obj = e.toString();
                                handler.sendMessage(message);

                            }
                        });

            } else {
                Toast.makeText(SumIndexActivity.this, "暂时没有您处理过的记录", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
////							progressDialog.cancel();
//                    }
                    MyProgressDialog.dismiss();
                    EtpsFirstBean firstSelfBean = gson.fromJson(msg.obj.toString(),
                            EtpsFirstBean.class);

                    //TODO 正确请求
                    if (firstSelfBean.getCode() == 200) {
//                        if (progressDialog.isShowing()) {
//                            progressDialog.cancel();
//                        }
                        MyProgressDialog.dismiss();
                        etpsBeans = firstSelfBean.getResult();
                        application.setEtpsBeans(etpsBeans);
                        Intent intent = new Intent(SumIndexActivity.this, SumActivity.class);
                        startActivity(intent);
                        // UtilForFragment.switchContentWithStack(activity,
                        // new ZongheListFragment(), R.id.content);

                    } else {
//                        if (progressDialog.isShowing()) {
//                            progressDialog.dismiss();
////								progressDialog.cancel();
//                        }
                        MyProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "没有记录", Toast.LENGTH_SHORT)
                                .show();

                    }
                    // RecordFirstBean firstBean = gson.fromJson(msg.obj.toString(),
                    // RecordFirstBean.class);
                    // if (firstBean.getCode() == 200) {
                    // // BigBean bigBean = gson.fromJson(result, BigBean.class);
                    // application.setRecordBeans(firstBean.getResult());
                    // progressDialog.cancel();
                    // UtilForFragment.switchContentWithStack(activity,
                    // new RecordListFragment(), R.id.content);
                    //
                    // } else {
                    // progressDialog.cancel();
                    // Toast.makeText(getActivity(), "未查询到该记录详情",
                    // Toast.LENGTH_SHORT).show();
                    // }
                    break;

                case SHOW_RESPONSE_COUNT:
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
////								progressDialog.cancel();
//                    }
                    MyProgressDialog.dismiss();
                    StatisticsFirstBean statisticsFirstBean = gson.fromJson(
                            msg.obj.toString(), StatisticsFirstBean.class);

                    if (statisticsFirstBean.getCode() == 200) {
                        // if (progressDialog.isShowing()) {
                        // progressDialog.cancel();
                        // }

                        StatisticsBean statisticsBean = statisticsFirstBean
                                .getResult();
                        if (statisticsBean.getUserStatisticsVo().getDailySt() != null) {
                            type1.setText(statisticsBean.getUserStatisticsVo()
                                    .getDailySt() + "（次）");
                            type1.setTag(statisticsBean.getUserStatisticsVo()
                                    .getDailySt());
                        } else {
                            type1.setText("0（次）");
                            type1.setTag("0");
                        }
                        if (statisticsBean.getUserStatisticsVo().getFocusSt() != null) {
                            type2.setText(statisticsBean.getUserStatisticsVo()
                                    .getFocusSt() + "（次）");
                            type2.setTag(statisticsBean.getUserStatisticsVo()
                                    .getFocusSt());
                        } else {
                            type2.setText("0（次）");
                            type2.setTag("0");
                        }
                        if (statisticsBean.getUserStatisticsVo().getSpecialSt() != null) {
                            type3.setText(statisticsBean.getUserStatisticsVo()
                                    .getSpecialSt() + "（次）");
                            type3.setTag(statisticsBean.getUserStatisticsVo()
                                    .getSpecialSt());
                        } else {
                            type3.setText("0（次）");
                            type3.setTag("0");
                        }
                        if (statisticsBean.getUserStatisticsVo().getAbbuseSt() != null) {
                            type4.setText(statisticsBean.getUserStatisticsVo()
                                    .getAbbuseSt() + "（户）");
                            type4.setTag(statisticsBean.getUserStatisticsVo()
                                    .getAbbuseSt());
                        } else {
                            type4.setText("0（户）");
                            type4.setTag("0");
                        }
                        if (statisticsBean.getUserStatisticsVo().getServiceSt() != null) {
                            type5.setText(statisticsBean.getUserStatisticsVo()
                                    .getServiceSt() + "（次）");
                            type5.setTag(statisticsBean.getUserStatisticsVo()
                                    .getServiceSt());
                        } else {
                            type5.setText("0（次）");
                            type5.setTag("0");
                        }
                        // if (statisticsBean.getUserStatisticsVo().getGroupSt() !=
                        // null) {
                        // type6.setText(statisticsBean.getUserStatisticsVo()
                        // .getGroupSt() + "（次）");
                        // type6.setTag(statisticsBean.getUserStatisticsVo()
                        // .getGroupSt());
                        // } else {
                        // type6.setText("0（次）");
                        // type6.setTag("0");
                        // }
                        // if (statisticsBean.getUserStatisticsVo().getGroupSt() !=
                        // null) {
                        // type7.setText(statisticsBean.getUserStatisticsVo().getTotal());
                        // } else {
                        // type7.setText("0（次）");
                        // }

                        if (statisticsBean.getOrganStatisticsVo().getDailySt() != null) {
                            type1_1.setText(statisticsBean.getOrganStatisticsVo()
                                    .getDailySt() + "（次）");
                            type1_1.setTag(statisticsBean.getOrganStatisticsVo()
                                    .getDailySt());
                        } else {
                            type1_1.setText("0（次）");
                            type1_1.setTag("0");
                        }
                        if (statisticsBean.getOrganStatisticsVo().getFocusSt() != null) {
                            type2_1.setText(statisticsBean.getOrganStatisticsVo()
                                    .getFocusSt() + "（次）");
                            type2_1.setTag(statisticsBean.getOrganStatisticsVo()
                                    .getFocusSt());
                        } else {
                            type2_1.setText("0（次）");
                            type2_1.setTag("0");
                        }
                        if (statisticsBean.getOrganStatisticsVo().getSpecialSt() != null) {
                            type3_1.setText(statisticsBean.getOrganStatisticsVo()
                                    .getSpecialSt() + "（次）");
                            type3_1.setTag(statisticsBean.getOrganStatisticsVo()
                                    .getSpecialSt());
                        } else {
                            type3_1.setText("0（次）");
                            type3_1.setTag("0");
                        }
                        if (statisticsBean.getOrganStatisticsVo().getAbbuseSt() != null) {
                            type4_1.setText(statisticsBean.getOrganStatisticsVo()
                                    .getAbbuseSt() + "（户）");
                            type4_1.setTag(statisticsBean.getOrganStatisticsVo()
                                    .getAbbuseSt());
                        } else {
                            type4_1.setText("0（户）");
                            type4_1.setTag("0");
                        }
                        if (statisticsBean.getOrganStatisticsVo().getServiceSt() != null) {
                            type5_1.setText(statisticsBean.getOrganStatisticsVo()
                                    .getServiceSt() + "（次）");
                            type5_1.setTag(statisticsBean.getOrganStatisticsVo()
                                    .getServiceSt());
                        } else {
                            type5_1.setText("0（次）");
                            type5_1.setTag("0");
                        }
                        // if (statisticsBean.getOrganStatisticsVo().getGroupSt() !=
                        // null) {
                        // type6_1.setText(statisticsBean.getOrganStatisticsVo()
                        // .getGroupSt() + "（次）");
                        // type6_1.setTag(statisticsBean.getOrganStatisticsVo()
                        // .getGroupSt());
                        // } else {
                        // type6_1.setText("0（次）");
                        // type6_1.setTag("0");
                        // }

                        endDateTemp = endDate.getText().toString();
                        startDateTemp = startDate.getText().toString();

                    } else {

                        Toast.makeText(getApplicationContext(), "没有记录", Toast.LENGTH_SHORT)
                                .show();

                    }
                    break;

                case SHOW_ERROR:
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
////								progressDialog.cancel();
//                    }
                    MyProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "网络出现问题", Toast.LENGTH_SHORT)
                            .show();


                    break;
            }

        }
    };

    public boolean compareDate(String startDate, String endDate) {
        if (startDate.equals("") || endDate.equals("")) {
            return true;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate;
        Date eDate;
        try {
            sDate = dateFormat.parse(startDate);
            eDate = dateFormat.parse(endDate);
            if (eDate.before(sDate)) {
                return false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
