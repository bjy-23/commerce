package com.wondersgroup.commerce.teamwork.mysupervision;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsBean;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class SupervisionFragment extends Fragment {

    private AppCompatActivity activity;
    private View view;
    private Gson gson = new Gson();
    public static final int SHOW_RESPONSE = 1;
    public static final int SHOW_LIST_RESPONSE = 4;
    public static final int SHOW_ERROR = 2;
    public static final int SHOW_DIC_ERROR = 3;
    private Dialog progressDialog;
    private RootAppcation application;

    // selfList
    private List<EtpsBean> etpsBeans = new ArrayList<EtpsBean>();

    private Map<String, String> checkSuperviseTypeMap;
    private Map<String, String> checkTypeMap;
    private Map<String, String> dealModeMap;
    private Map<String, String> etpsTypeFirstMap;
    private Map<String, String> unLicensedTypeMap;

    private TextView checkSuperviseType;
    private TextView checkType;
    private TextView dealMode;
    private TextView etpsTypeFirst;
    private TextView unLicensedType;
    private TextView specialFileName;
    private TextView startCheckDate;
    private TextView endCheckDate;

    private Button button;

    private AlertDialog.Builder dialog;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.acitivity_supervision, container,
                false);
        activity = (AppCompatActivity) getActivity();
        application = (RootAppcation) activity.getApplication();
//        progressDialog = LoadingDialog.createLoadingDialog(getActivity(),
//                "loading");
        TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
        title.setText("业务查询-监督管理");
        dialog = new AlertDialog.Builder(activity);
        endCheckDate = (TextView) view.findViewById(R.id.endCheckDate);
        endCheckDate.setText("");
        endCheckDate.setOnTouchListener(new DateOnTouch(getActivity(),
                endCheckDate));
        startCheckDate = (TextView) view.findViewById(R.id.startCheckDate);
        startCheckDate.setText("");
        startCheckDate.setOnTouchListener(new DateOnTouch(getActivity(),
                startCheckDate));

        checkSuperviseType = (TextView) view
                .findViewById(R.id.checkSuperviseType);
        checkType = (TextView) view.findViewById(R.id.checkType);
        dealMode = (TextView) view.findViewById(R.id.dealMode);
        etpsTypeFirst = (TextView) view.findViewById(R.id.etpsTypeFirst);
        unLicensedType = (TextView) view.findViewById(R.id.unLicensedType);

        checkSuperviseType.setTag("");
        checkType.setTag("");
        dealMode.setTag("");
        etpsTypeFirst.setTag("");
        unLicensedType.setTag("");

        specialFileName = (TextView) view.findViewById(R.id.specialFileName);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!compareDate(startCheckDate.getText().toString(),
                        endCheckDate.getText().toString())) {
                    Toast.makeText(activity, "请确认终止时间晚于起始时间",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("checkType", checkType.getTag().toString());

                    jsonObject.put("abbuseType", unLicensedType.getTag()
                            .toString());
                    jsonObject.put("checkType", checkType.getTag().toString());
                    jsonObject.put("eptsTypeGb", etpsTypeFirst.getTag()
                            .toString());
                    jsonObject.put("inspectedFlag", checkSuperviseType.getTag()
                            .toString());
                    jsonObject.put("treatment", dealMode.getTag().toString());

                    jsonObject.put("specialFileName", specialFileName.getText()
                            .toString());
                    jsonObject.put("startCheckDate", startCheckDate.getText()
                            .toString());
                    jsonObject.put("endCheckDate", endCheckDate.getText()
                            .toString());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    jsonObject.put("tmpFlag", "0");
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    jsonObject.put("submitUser", application.getLoginUserInfo()
                            .getUserId());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                String address = Url.QJ_IN_USE + "searchList";
                // JSONObject jsonObject = new JSONObject();
                // try {
                // jsonObject.put("checkType", "1");
                //
                // jsonObject.put("organId", application.getLoginUserInfo()
                // .getOrganId());
                // } catch (JSONException e1) {
                // // TODO Auto-generated catch block
                // e1.printStackTrace();
                // }
                HttpClientUtil.callWebService(jsonObject.toString(), address,
                        new HttpCallbackListener() {

                            @Override
                            public void onFinish(String response) {
                                Message message = new Message();
                                message.what = SHOW_LIST_RESPONSE;
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

//        progressDialog.show();
        MyProgressDialog.show(activity);
        String queryAddress = Url.QJ_IN_USE + "query";
        HttpClientUtil.callWebServiceForGet(queryAddress,
                new HttpCallbackListener() {

                    @Override
                    public void onFinish(String response) {
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {
                        Message message = new Message();
                        message.what = SHOW_DIC_ERROR;
                        message.obj = e.toString();
                        handler.sendMessage(message);

                    }
                });

        setHasOptionsMenu(true);
        return view;
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_ERROR:
//                    if (progressDialog.isShowing()) {
//                        progressDialog.cancel();
//                    }
                    MyProgressDialog.dismiss();
                    Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT)
                            .show();
                    break;

                case SHOW_RESPONSE:
//                    if (progressDialog.isShowing()) {
//                        progressDialog.cancel();
//                    }
                    MyProgressDialog.dismiss();
                    FirstQueryBean firstQueryBean = gson.fromJson(
                            msg.obj.toString(), FirstQueryBean.class);
                    if (firstQueryBean.getCode() == 200) {
                        QueryBean queryBean = firstQueryBean.getResult();

                        checkSuperviseTypeMap = queryBean.getCheckSuperviseType();
                        checkTypeMap = queryBean.getCheckType();
                        dealModeMap = queryBean.getDealMode();
                        etpsTypeFirstMap = queryBean.getEtpsTypeFirst();
                        unLicensedTypeMap = queryBean.getUnLicensedType();

                        MapToListUtil checkSuperviseUtil = new MapToListUtil(
                                checkSuperviseTypeMap);
                        List<KeyValue> checkSuperviseKeyValues = checkSuperviseUtil
                                .mapToKeyValues();
                        checkSuperviseType
                                .setOnClickListener(new ShowSingleDialogListListener(
                                        checkSuperviseKeyValues, activity,
                                        checkSuperviseType));

                        MapToListUtil checkUtil = new MapToListUtil(checkTypeMap);
                        List<KeyValue> checkKeyValues = checkUtil.mapToKeyValues();
                        checkType
                                .setOnClickListener(new ShowSingleDialogListListener(
                                        checkKeyValues, activity, checkType));

                        MapToListUtil dealModeUtil = new MapToListUtil(dealModeMap);
                        List<KeyValue> dealModeKeyValues = dealModeUtil
                                .mapToKeyValues();
                        dealMode.setOnClickListener(new ShowSingleDialogListListener(
                                dealModeKeyValues, activity, dealMode));

                        MapToListUtil etpsTypeFirstUtil = new MapToListUtil(
                                etpsTypeFirstMap);
                        List<KeyValue> etpsTypeFirstKeyValues = etpsTypeFirstUtil
                                .mapToKeyValues();
                        etpsTypeFirst
                                .setOnClickListener(new ShowSingleDialogListListener(
                                        etpsTypeFirstKeyValues, activity,
                                        etpsTypeFirst));

                        MapToListUtil unLicensedTypeUtil = new MapToListUtil(
                                unLicensedTypeMap);
                        List<KeyValue> unLicensedTypeKeyValues = unLicensedTypeUtil
                                .mapToKeyValues();
                        unLicensedType
                                .setOnClickListener(new ShowSingleDialogListListener(
                                        unLicensedTypeKeyValues, activity,
                                        unLicensedType));

                    }
                    break;

                case SHOW_LIST_RESPONSE:
                    EtpsFirstBean firstSelfBean = gson.fromJson(msg.obj.toString(),
                            EtpsFirstBean.class);

                    if (firstSelfBean.getCode() == 200) {
                        // if (progressDialog.isShowing()) {
                        // progressDialog.cancel();
                        // }
                        etpsBeans = firstSelfBean.getResult();
                        application.setEtpsBeans(etpsBeans);
                        UtilForFragment.switchContentWithStack(activity,
                                new ZongheListFragment(), R.id.content);

                    } else {
//                        if (progressDialog.isShowing()) {
//                            progressDialog.cancel();
//                        }
                        MyProgressDialog.dismiss();
                        Toast.makeText(getActivity(), "未找到符合条件的记录", Toast.LENGTH_SHORT)
                                .show();

                    }
                    break;

                case SHOW_DIC_ERROR:
//                    progressDialog.cancel();
                    MyProgressDialog.dismiss();
                    Toast.makeText(activity, "网络出现问题", Toast.LENGTH_SHORT).show();
                    dialog.setTitle("获取配置文件失败");
                    dialog.setMessage("请重试或者返回");
                    dialog.setNegativeButton("重试",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
//                                    progressDialog.show();
                                    MyProgressDialog.show(activity);

                                    String queryAddress = Url.QJ_IN_USE + "query";
                                    HttpClientUtil.callWebServiceForGet(
                                            queryAddress,
                                            new HttpCallbackListener() {

                                                @Override
                                                public void onFinish(String response) {
                                                    Message message = new Message();
                                                    message.what = SHOW_RESPONSE;
                                                    message.obj = response
                                                            .toString();
                                                    handler.sendMessage(message);
                                                }

                                                @Override
                                                public void onError(Exception e) {
                                                    Message message = new Message();
                                                    message.what = SHOW_DIC_ERROR;
                                                    message.obj = e.toString();
                                                    handler.sendMessage(message);

                                                }
                                            });

                                }
                            });

                    dialog.setPositiveButton("返回",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    activity.finish();

                                }
                            });
                    dialog.show();

                    break;

            }

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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

}