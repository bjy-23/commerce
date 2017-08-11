package com.wondersgroup.commerce.fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.LoginActivity;
import com.wondersgroup.commerce.activity.MainActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.UpdateBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.systemsetting.AboutActivity;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.utils.FileUtils;
import com.wondersgroup.commerce.widget.LoadingDialog;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kangrenhui on 2016/1/19.
 */
public class FragmentFour extends RootFragment {
    @Bind(R.id.img_head)
    ImageView imgHead;
    @Bind(R.id.setting_name)
    TextView nameTxt;
    @Bind(R.id.setting_organ)
    TextView organTxt;
    @Bind(R.id.setting_dept)
    TextView deptTxt;
    @Bind(R.id.setting_dept_img)
    ImageView deptImg;
    @Bind(R.id.btn_check)
    TextView btnCheck;
    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.btn_about)
    TextView btnAbout;
    @Bind(R.id.btn_exit)
    TextView btnExit;

    FragmentManager fm;
    private RootAppcation rootApplication;
    private DataShared dataShared;
    private MainActivity mainActivity;
    private Toolbar toolbar;
    private TextView title;
    private Button msgBtn;
    private List<String> deptList;
    private int pos;
    private String downloadUrl = "";
    private int totalSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.activity_setting, null);
        fm = getFragmentManager();

        ButterKnife.bind(this, view);
        rootApplication = (RootAppcation) getActivity().getApplicationContext();
        deptList = rootApplication.getTotalDeptList();
        if(deptList!=null)  deptImg.setVisibility(View.VISIBLE);
        dataShared = new DataShared(getActivity());
        nameTxt.setText((String)dataShared.get("userName", ""));
        organTxt.setText((String)dataShared.get("organName", ""));
        deptTxt.setText((String)dataShared.get("deptName", ""));
        if("湖南".equals(rootApplication.getVersion())) {
            mainActivity = (MainActivity) getActivity();
            toolbar = (Toolbar) mainActivity.findViewById(R.id.toolbar);
            toolbar.setVisibility(View.VISIBLE);
            title = (TextView) toolbar.findViewById(R.id.toolbar_txt);
            title.setText("我的");
            msgBtn = (Button) toolbar.findViewById(R.id.toolbar_btn);
            msgBtn.setVisibility(View.GONE);
            mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            mainActivity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.setting_dept_layout, R.id.btn_check, R.id.btn_about, R.id.btn_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_dept_layout:
                if(deptList!=null){
                    showDeptDialog();
                }
                break;
            case R.id.btn_check:
                Map<String, String> map = new HashMap<>();
                map.put("wsCodeReq", "00000003");
                map.put("version", getVersionName());
                Call<UpdateBean> call;
                if("湖南".equals(rootApplication.getVersion())) {
                    call = ApiManager.hnApi.apiUpdate(map);
                }else {
                    call = ApiManager.hbApi.apiUpdate(map);
                }
                call.enqueue(new Callback<UpdateBean>() {
                    @Override
                    public void onResponse(Response<UpdateBean> response, Retrofit retrofit) {
                        if(response!=null && response.body()!=null && response.body().getResult()!=null){
                            if(response.body().getResult().getPath() == null){
                                Toast.makeText(getActivity(), "目前已经是最新版本", Toast.LENGTH_SHORT).show();
                            }else{
                                downloadUrl = response.body().getResult().getPath();
                                totalSize = Integer.parseInt(response.body().getResult().getSize());
                                showUpdateDialog();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.btn_about:
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_exit:
                rootApplication.setTotalDeptList(null);
                rootApplication.setTotalLoginBean(null);
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent2);
                getActivity().finish();
                break;
        }
    }



    /**
     * 部门选择dialog
     */
    private void showDeptDialog() {
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < deptList.size(); i++) {
            items.add(deptList.get(i).split(",")[1]);
        }
        final String[] deptStringArray = new String[items.size()];
        items.toArray(deptStringArray);
        new MaterialDialog.Builder(getActivity())
                .items(deptStringArray)
                .widgetColor(ContextCompat.getColor(getActivity(), R.color.blue))
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        pos = which;
                        deptNet();
                        return true;
                    }
                })
                .positiveText("确定")
                .positiveColor(ContextCompat.getColor(getActivity(), R.color.blue))
                .show();
    }


    /**
     * 工商切换部门网络请求
     */
    private void deptNet() {
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(getActivity());

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000001");
        map.put("loginName", (String) dataShared.get("name", ""));
        map.put("password", (String) dataShared.get("pwd", ""));
        map.put("deptId", deptList.get(pos).split(",")[0]);

        Call<TotalLoginBean> call;
        if("湖南".equals(rootApplication.getVersion())) {
            call = ApiManager.hnApi.login(map);
        }else {
            call = ApiManager.hbApi.login(map);
        }
        call.enqueue(new Callback<TotalLoginBean>() {
            @Override
            public void onResponse(Response<TotalLoginBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    TotalLoginBean login = response.body();

                    if ((null == login) || (null == login.getResult()) || !(null == login.getResult().getErrorMsg())) {
                        Toast.makeText(getActivity(), "出现未知错误", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        return;
                    }else{
                        DataShared dataShared = new DataShared(getActivity());
                        dataShared.put("userId",login.getResult().getUserId());
                        dataShared.put("deptId",login.getResult().getDeptId());
                        dataShared.put("organId",login.getResult().getOrganId());
                        dataShared.put("userName",login.getResult().getUserName());
                        dataShared.put("deptName",login.getResult().getDeptName());
                        dataShared.put("organName",login.getResult().getOrganName());
                    }
                    rootApplication.setTotalLoginBean(login);
                    organTxt.setText(login.getResult().getOrganName());
                    deptTxt.setText(login.getResult().getDeptName());

                } else {

                    Toast.makeText(getActivity(), getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                if(dialog.isShowing())dialog.dismiss();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 获取当前版本号
     */
    private String getVersionName(){
        PackageManager packageManager = getActivity().getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    protected void showUpdateDialog(){
        MaterialDialog.Builder builer = new MaterialDialog.Builder(getActivity());
        builer.title("版本升级");
        builer.content("当前有新版本，请升级");
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.positiveText("确定");
        builer.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                downloadApk();
            }
        });
        builer.negativeText("取消");
        builer.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                dialog.dismiss();
            }
        });
        MaterialDialog dialog = builer.build();
        dialog.show();
    }

    protected void downloadApk(){
        final MaterialDialog pd;    //进度条对话框
        pd = new MaterialDialog.Builder(getActivity())
                .content("正在下载更新")
                .progress(false, 100, false)
                .cancelable(false)
                .show();
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = FileUtils.getFileFromServer(rootApplication.getCachePath(), totalSize, downloadUrl, pd);
                    //sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "下载失败", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    e.printStackTrace();
                }
            }}.start();
    }

    protected void installApk(File file){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("FragmentFour","onResume");
    }
}
