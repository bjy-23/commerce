package com.wondersgroup.yngs.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.activity.LoginActivity;
import com.wondersgroup.yngs.entity.UpgradeResult;
import com.wondersgroup.yngs.entity.UserResult;
import com.wondersgroup.yngs.service.ApiManager;
import com.wondersgroup.yngs.service.MsgIntentService;
import com.wondersgroup.yngs.widget.LabelSelect;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class SettingFragment extends Fragment implements View.OnClickListener{
    /*@Bind(R.id.setting_version)Button version;
    @Bind(R.id.setting_about)Button about;*/
    @Bind(R.id.setting_logout)Button logout;
    @Bind(R.id.setting_switch)SwitchCompat pushSwitch;
    @Bind(R.id.setting_switch_text)TextView switchText;
    @Bind(R.id.setting_office)LabelSelect office;
    @Bind(R.id.setting_version)LabelSelect version;
    @Bind(R.id.setting_about)LabelSelect about;
    @Bind(R.id.user_header_name)TextView userName;
    @Bind(R.id.user_header_dept)TextView userDept;

    long size=0;
    private boolean needFetch=false;

    private String deptId;
    private String deptName;
    private String organId;
    private String organName;
    private int selection=-1;
    private SharedPreferences sp;

    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        office.setTitle("所属部门");
        //office.setIconVisibility(View.GONE);
        version.setTitle("版本检测");
        //version.setIconVisibility(View.GONE);
        about.setTitle("关于");
        //about.setIconVisibility(View.GONE);
        version.setOnClickListener(this);
        about.setOnClickListener(this);
        logout.setOnClickListener(this);
        office.setOnClickListener(this);
        sp=getContext().getSharedPreferences("Default", Context.MODE_PRIVATE);
        deptId=sp.getString("deptId", "");
        deptName=sp.getString("deptName", "");
        organId=sp.getString("organId", "");
        organName=sp.getString("organName", "");
        selection=sp.getInt("selection", 0);
        boolean receivePush=sp.getBoolean("receivePush", true);
        pushSwitch.setChecked(receivePush);
        if(pushSwitch.isChecked()){
            switchText.setTextColor(ContextCompat.getColor(getContext(), R.color.deep_gray));
        }else {
            switchText.setTextColor(ContextCompat.getColor(getContext(),R.color.white_gray));
        }
        pushSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchText.setTextColor(ContextCompat.getColor(getContext(),R.color.deep_gray));
                    MsgIntentService.startActionMsg(getContext(), 6);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.putBoolean("receivePush", true);
                    ed.apply();
                } else {
                    switchText.setTextColor(ContextCompat.getColor(getContext(),R.color.white_gray));
                    MsgIntentService.cancelActionMsg();
                    SharedPreferences.Editor ed = sp.edit();
                    ed.putBoolean("receivePush", false);
                    ed.apply();
                }
            }
        });
        if(needFetch){
            getUserInfo();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            if (getView()!=null) {
                getUserInfo();
                needFetch=false;
            }else {
                needFetch=true;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(version.equals(v)){
            Map<String,String> body=new HashMap<>();
            body.put("wsCodeReq",ApiManager.getWsCodeReq());
            Call<UpgradeResult> call=ApiManager.yunNanApi.upgrade(body);
            call.enqueue(new Callback<UpgradeResult>() {
                @Override
                public void onResponse(Response<UpgradeResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        final UpgradeResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            size=Long.parseLong(result.getResult().getSize());
                            if(shouldUpgrade(result.getResult().getVersion())){
                                new MaterialDialog.Builder(getContext())
                                        .title("有更新")
                                        .content("发现新版本，是否更新?")
                                        .positiveText("是")
                                        .negativeText("否")
                                        .cancelable(false)
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                upgrade(result.getResult().getPath());
                                            }
                                        })
                                        .show();
                            }else{
                                new MaterialDialog.Builder(getContext())
                                        .content("已是最新版本")
                                        .show();
                            }
                        }else {
                            new MaterialDialog.Builder(getContext())
                                    .title("更新出错")
                                    .content(result.getMessage())
                                    .show();
                        }
                    }else {
                        new MaterialDialog.Builder(getContext())
                                .title("更新出错")
                                .content("无法获取最新版本")
                                .show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }else if(about.equals(v)){
            new MaterialDialog.Builder(getContext())
                    .customView(R.layout.view_dialog_about,false)
                    .show();
        }else if(logout.equals(v)){
            ApiManager.clearToken();
            SharedPreferences.Editor ed=sp.edit();
            ed.clear();
            ed.apply();
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }else if(office.equals(v)){
            new MaterialDialog.Builder(getContext())
                    .title("请重新选择部门")
                    .items(deptName.split(","))
                    .itemsCallbackSingleChoice(selection, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            userDept.setText(text);
                            selection = which;
                            Hawk.put("organId", sp.getString("organId", "").split(",")[which]);
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putInt("selection", which);
                            ed.apply();
                            return true;
                        }
                    })
                    .cancelable(false)
                    .positiveText("确定")
                    .show();
        }
    }

    private String getOldVersionName() {
        PackageManager packageManager = getContext().getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getContext().getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    public boolean shouldUpgrade(String newVersion){
        String oldVersion= getOldVersionName();
        String[] oldVersions=oldVersion.split("\\.");
        String[] newVersions=newVersion.split("\\.");
        int length=Math.max(oldVersions.length,newVersions.length);
        for(int i=0;i<length;i++){
            int oldInt=i<oldVersions.length?Integer.parseInt(oldVersions[i]):0;
            int newInt=i<newVersions.length?Integer.parseInt(newVersions[i]):0;
            if(oldInt<newInt)return true;
        }
        return false;
    }

    public void upgrade(String path){
        final String fileName=path.split("/")[path.split("/").length-1];
        final MaterialDialog downDialog=new MaterialDialog.Builder(getContext())
                .title("下载中")
                .content("请稍等")
                .progress(false,100,true)
                .show();
        if(!path.startsWith("http://")){
            path=ApiManager.getApiRoot()+path;
        }
        Request request=new Request.Builder()
                .url(path)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(1000, TimeUnit.MINUTES);
        client.setReadTimeout(1000, TimeUnit.MINUTES);
        client.setWriteTimeout(1000, TimeUnit.MINUTES);
        client.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                downDialog.dismiss();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new MaterialDialog.Builder(getContext())
                                .title("更新失败")
                                .content("请检查网络连接")
                                .show();
                    }
                });
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                if(response.code()==200) {
                    File downloadedFile = new File(getContext().getExternalCacheDir(), fileName);
                    BufferedSink sink = Okio.buffer(Okio.sink(downloadedFile));
                    BufferedSource source = response.body().source();
                    long bytesRead = 0;
                    while (source.read(sink.buffer(),2048)!=-1){
                        bytesRead+=2048;
                        if(size!=0)downDialog.setProgress((int) ((100 * bytesRead) / size));
                    }
                    sink.writeAll(source);
                    sink.close();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(downloadedFile), "application/vnd.android.package-archive");
                    startActivity(intent);
                }else {
                    downDialog.dismiss();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new MaterialDialog.Builder(getContext())
                                    .title("更新失败")
                                    .content("下载更新包出错")
                                    .show();
                        }
                    });
                }
            }
        });
    }

    public void getUserInfo(){
        String userId=sp.getString("userId", "");
        Map<String,String> body=new HashMap<>();
        body.put("userId",userId);
        body.put("wsCodeReq",ApiManager.getWsCodeReq());
        Call<UserResult> call=ApiManager.yunNanApi.getUserInfo(body);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Response<UserResult> response, Retrofit retrofit) {
                if(response.isSuccess()&&response.body()!=null){
                    UserResult result=response.body();
                    if("200".equals(result.getResultCode())){
                        userName.setText(result.getResult().getUserName());
                        userDept.setText(result.getResult().getDeptName().split(",")[selection]);
                        String curDeptId=deptId.split(",")[selection];
                        if(deptId.equals(result.getResult().getDeptId()))return;
                        deptId=result.getResult().getDeptId();
                        deptName=result.getResult().getDeptName();
                        organId= result.getResult().getOrganId();
                        organName=result.getResult().getOrganName();
                        selection=0;

                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("deptId",deptId);
                        ed.putString("organId",organId);
                        ed.putString("deptName", deptName);
                        ed.putString("organName", organName);
                        String[] deptIds=deptId.split(",");
                        boolean shouldRePick=true;
                        if(deptIds.length==1){
                            shouldRePick=false;
                            selection=0;
                        }else {
                            for (int i = 0; i < deptIds.length; i++) {
                                if (curDeptId.equals(deptIds[i])) {
                                    shouldRePick=false;
                                    selection = i;
                                }
                            }
                        }
                        ed.putInt("selection",selection);
                        ed.apply();
                        if(shouldRePick){
                            new MaterialDialog.Builder(getContext())
                                    .title("请重新选择部门")
                                    .items(deptName.split(","))
                                    .itemsCallbackSingleChoice(selection, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                            userDept.setText(text);
                                            selection = which;
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.putInt("selection", which);
                                            ed.apply();
                                            return true;
                                        }
                                    })
                                    .cancelable(false)
                                    .positiveText("确定")
                                    .show();
                        }else {
                            userDept.setText(deptName.split(",")[selection]);
                        }
                    }else if("403".equals(result.getResultCode())){
                        new MaterialDialog.Builder(getContext())
                                .title("登录过期")
                                .content("请重新登录")
                                .positiveText("确定")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        SharedPreferences.Editor ed3=sp.edit();
                                        ed3.clear();
                                        ed3.apply();
                                        startActivity(new Intent(getContext(), LoginActivity.class));
                                        getActivity().finish();
                                    }
                                })
                                .cancelable(false)
                                .show();
                    }else {
                        new MaterialDialog.Builder(getContext())
                                .title("出错")
                                .content(result.getMessage())
                                .positiveText("确定")
                                .show();
                    }
                }else {
                    new MaterialDialog.Builder(getContext())
                            .title("出错")
                            .content("连接服务器失败")
                            .positiveText("确定")
                            .show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                new MaterialDialog.Builder(getContext())
                        .title("出错")
                        .content("网络错误")
                        .positiveText("确定")
                        .show();
            }
        });
    }
}
