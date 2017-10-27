package com.wondersgroup.commerce.teamwork.myspecialcheck;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsAdapter;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsBean;
import com.wondersgroup.commerce.teamwork.dailycheck.InfoBean;
import com.wondersgroup.commerce.teamwork.mysupervision.EtpsFirstBean;
import com.wondersgroup.commerce.teamwork.mysupervision.HttpCallbackListener;
import com.wondersgroup.commerce.teamwork.mysupervision.HttpClientUtil;
import com.wondersgroup.commerce.teamwork.mysupervision.Url;
import com.wondersgroup.commerce.teamwork.mysupervision.UtilForFragment;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.widget.MyProgressDialog;
import com.wondersgroup.commerce.utils.CheckUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SpecialListSearchFragment extends Fragment {

    private View view;
    private Button queryButton;
    private AppCompatActivity activity;

    private Gson gson = new Gson();
    public static final int SHOW_RESPONSE = 1;
    public static final int SHOW_ERROR = 2;
    private RootAppcation application;

    // selfList
    public static final int SHOW_RESPONSE_SELF = 3;
    public static final int SHOW_RESPONSE_INFO = 4;
    private List<EtpsBean> etpsBeans = new ArrayList<EtpsBean>();
    private ListView listView;
    private InfoBean infoBean = new InfoBean();
    private TotalLoginBean loginBean;

    // new
    private String recordId = "";
    private TextView searchWord;
    private Dialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mode_searchandlist, null);
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        activity = (AppCompatActivity) getActivity();
        application = (RootAppcation) activity.getApplication();
//		progressDialog = LoadingDialog.createLoadingDialog(getActivity(),
//				"loading");
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
//		actionBar.setTitle("监督管理—专项整治");
        TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
        title.setText("监督管理—专项整治");
        searchWord = (TextView) view.findViewById(R.id.searchWord);
        CheckUtil.limitCheckMinCount(searchWord, Constants.inputMinCount);
        CheckUtil.limitCheckMaxCount(searchWord, Constants.inputMaxCount);
        loadingDialog = new LoadingDialog.Builder(getActivity()).build();
        loadingDialog.show();
        queryButton = (Button) view.findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (searchWord.getText().toString().equals("") || searchWord.getError() != null) {
                    if (searchWord.getError() != null) {
                        CheckUtil.showSoftInput(searchWord, getActivity());
                    } else {
                        searchWord.setError("输入字数不得少于" + Constants.inputMinCount);
                    }
                } else {
                    loadingDialog =  new LoadingDialog.Builder(getActivity()).build();
                    loadingDialog.show();
                    String keyWord = "";
                    try {
                        keyWord = URLEncoder.encode(searchWord.getText()
                                .toString(), "UTF-8");
                    } catch (UnsupportedEncodingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    Call<ResponseBody> call = ApiManager.consumerwApi.getEtpsList(loginBean.getResult().getOrganId(), keyWord);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {

                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                    String address = Url.QJ_IN_USE + "getEtpsList/"
                            + loginBean.getResult().getOrganId() + "/"
                            + keyWord;
                    Log.e("address", address);
                    HttpClientUtil.callWebServiceForGet(address,
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
                                    message.what = SHOW_ERROR;
                                    message.obj = e.toString();
                                    handler.sendMessage(message);

                                }
                            });
                }

            }
        });
        initSelfList();
        setHasOptionsMenu(true);
        return view;
    }

    private void initSelfList() {
        loadingDialog.show();
        HashMap<String, String> param = new HashMap<>();
        param.put("submitUser", loginBean.getResult().getUserId());
        param.put("checkType", "3");
        param.put("organId", loginBean.getResult().getOrganId());
        param.put("tmpFlag", "1");
        Call<ResponseBody> call = ApiManager.consumerwApi.searchList(param);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
//                loadingDialog.show();
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                loadingDialog.show();
//            }
//        });
        String address = Url.QJ_IN_USE + "searchList";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("checkType", "3");

            jsonObject.put("organId", loginBean.getResult().getOrganId());
            jsonObject.put("tmpFlag", 1);
            jsonObject.put("submitUser", loginBean.getResult().getUserId());
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        HttpClientUtil.callWebService(jsonObject.toString(), address,
                new HttpCallbackListener() {

                    @Override
                    public void onFinish(String response) {
                        Message message = new Message();
                        message.what = SHOW_RESPONSE_SELF;
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
        listView = (ListView) view.findViewById(R.id.demo_list);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                loadingDialog.show();
                recordId = etpsBeans.get(position).getRecordId();
                Call<ResponseBody> call = ApiManager.consumerwApi.getRecordInfo(loginBean.getResult().getDeptId(), recordId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        loadingDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        loadingDialog.dismiss();
                    }
                });
                String netAddress = Url.QJ_IN_USE + "getRecordInfo/2/"
                        + loginBean.getResult().getDeptId() + "/"
                        + recordId;
                Log.e("netAddress", netAddress);
                HttpClientUtil.callWebServiceForGet(netAddress,
                        new HttpCallbackListener() {

                            @Override
                            public void onFinish(String response) {
                                Message message = new Message();
                                message.what = SHOW_RESPONSE_INFO;
                                message.obj = response.toString();
                                handler.sendMessage(message);
                                Log.e("info", response);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            activity.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            if (getActivity() != null) {
                switch (msg.what) {
                    case SHOW_RESPONSE:
                        loadingDialog.dismiss();
                        EtpsFirstBean firstBean = gson.fromJson(msg.obj.toString(),
                                EtpsFirstBean.class);

                        if (firstBean.getCode() == 200) {
                            application.setEtpsBeans(firstBean.getResult());
//					progressDialog.cancel();
                            loadingDialog.dismiss();
                            UtilForFragment.switchContentWithStack(activity,
                                    new SpecialListFragment(), R.id.content);

                        } else {

                            Toast.makeText(getActivity(), "未查询到企业,请更换关键字",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case SHOW_ERROR:
                        loadingDialog.dismiss();
                        Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT)
                                .show();
                        break;

                    case SHOW_RESPONSE_SELF:
                        loadingDialog.dismiss();
                        EtpsFirstBean firstSelfBean = gson.fromJson(msg.obj.toString(),
                                EtpsFirstBean.class);

                        if (firstSelfBean.getCode() == 200) {

                            etpsBeans = firstSelfBean.getResult();

                            EtpsAdapter adapter = new EtpsAdapter(getActivity(),
                                    R.layout.mode_list_item1, etpsBeans);
                            listView.setAdapter(adapter);

                        }
                        break;

                    case SHOW_RESPONSE_INFO:
                        loadingDialog.dismiss();

                        JSONObject caseInfo = null;
                        try {
                            caseInfo = new JSONObject(msg.obj.toString());
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        String code = "";
                        try {
                            code = caseInfo.getString("code");
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        String result = "";
                        try {
                            result = caseInfo.getString("result");
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        // InfoFirstBean infoFirstBean =
                        // gson.fromJson(msg.obj.toString(),
                        // InfoFirstBean.class);

                        if (code.equals("200")) {

                            // infoBean = infoFirstBean.getResult();

                            // new
                            infoBean = gson.fromJson(result, InfoBean.class);
                            application.setInfoBean(infoBean);
                            application.setRecordId(recordId);
                            UtilForFragment.switchContentWithStack(activity,
                                    new SpecialTempFragment(), R.id.content);

                        } else {

                            Toast.makeText(getActivity(), "没有记录详情", Toast.LENGTH_SHORT)
                                    .show();

                        }
                        break;
                }

            }
        }
    };
}
