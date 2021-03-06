package com.wondersgroup.commerce.fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.LoginActivity;
import com.wondersgroup.commerce.activity.SplashActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.UpdateBean;
import com.wondersgroup.commerce.model.yn.Version;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.TjApi;
import com.wondersgroup.commerce.teamwork.systemsetting.AboutActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
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
 * Created by bjy on 2017/6/20.
 */

public class FragmentSetting extends Fragment implements View.OnClickListener {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_organ)
    TextView tvOrgan;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.layout_about)
    View viewAbout;
    @BindView(R.id.layout_update)
    View viewUpdate;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.img_back)
    ImageView mImgBack;

    private ProgressBar progressBar;
    private TextView tvSpeed, tvProgress, tvTotal,tvVersion;
    private String downloadUrl = "", version;
    private int totalSize;
    private int size, sum;
    private RootAppcation app;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    tvTotal.setText(msg.obj.toString());
                    break;
                case 200:
                    progressBar.setProgress(100);
                    tvProgress.setVisibility(View.GONE);
                    tvTotal.setTextColor(getResources().getColor(R.color.blue));
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        app = RootAppcation.getInstance();
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getBoolean("IS_SC")) {
            mImgBack.setVisibility(View.VISIBLE);
        }

        TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
        if(loginBean != null){
            tvName.setText(loginBean.getResult().getUserName());
            tvOrgan.setText(loginBean.getResult().getOrganName());
            tvDepartment.setText(loginBean.getResult().getDeptName());
        }
        viewAbout.setOnClickListener(this);
        viewAbout.findViewById(R.id.img_icon).setBackgroundResource(R.mipmap.icon_aboutus);
        ((TextView) viewAbout.findViewById(R.id.tv_content)).setText(Constants.ABOUT_US);

        viewUpdate.setOnClickListener(this);
        viewUpdate.findViewById(R.id.img_icon).setBackgroundResource(R.mipmap.icon_update);
        TextView tvVersion = ((TextView) viewUpdate.findViewById(R.id.tv_version));
        tvVersion.setText("V" + RootAppcation.getInstance().getVersionName());
        tvVersion.setVisibility(View.VISIBLE);
        ((TextView) viewUpdate.findViewById(R.id.tv_content)).setText(Constants.UPDATE);

        tvExit.setOnClickListener(this);
    }

    @OnClick(R.id.img_back)
    void onBackClick(View view) {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.layout_about:
                about();
                break;
            case R.id.layout_update:
                ynUpdate();
                break;
            case R.id.tv_exit:
                exit();
        }
    }

    public void about() {
        Intent intent = new Intent(getActivity(), AboutActivity.class);
        startActivity(intent);
    }

    public void update() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.update_dialog, null, false);
        final ViewGroup layoutProgress = (ViewGroup) view.findViewById(R.id.layout_progress);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setMax(100);
        final ViewGroup layoutChoose = (ViewGroup) view.findViewById(R.id.layout_choose);
        tvVersion = (TextView) view.findViewById(R.id.version);
        tvVersion.setText("V" + version);
        tvSpeed = (TextView) view.findViewById(R.id.tv_speed);
        tvProgress = (TextView) view.findViewById(R.id.tv_progress);
        tvTotal = (TextView) view.findViewById(R.id.tv_total);
        TextView tvRefuse = (TextView) view.findViewById(R.id.tv_refuse);
        tvRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView tvUpdate = (TextView) view.findViewById(R.id.tv_update);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutChoose.setVisibility(View.GONE);
                layoutProgress.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(downloadUrl);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setConnectTimeout(10000);
                            InputStream is = conn.getInputStream();
                            size = conn.getContentLength();
//                            progressBar.setMax(size);
                            float total = (float) Math.round((float) size / 1024 / 1024 * 100) / 100;//转化为两位小数
                            Message message = new Message();
                            message.obj = total + "M";
                            message.what = 100;
                            handler.sendMessage(message);
                            File file = new File(app.getCachePath(), "update.apk");
                            FileOutputStream fos = new FileOutputStream(file);
                            BufferedInputStream bis = new BufferedInputStream(is);
                            byte[] buffer = new byte[1024];
                            int len;
                            sum = 0;
                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(sum * 100 / size);
                                    Log.e("progress:", "sum:" + sum + "size:" + size + "sum / size:" + sum * 100 / size);
                                    float progress = (float) Math.round((float) sum / 1024 / 1024 * 100) / 100;
                                    tvProgress.setText(progress + "M/");
                                    handler.postDelayed(this, 1000);
                                }
                            };
                            handler.postDelayed(r, 1000);
                            while ((len = bis.read(buffer)) != -1) {
                                fos.write(buffer, 0, len);
                                sum += len;
                                //获取当前下载量
                            }
                            handler.sendEmptyMessage(200);
                            handler.removeCallbacks(r);
                            fos.close();
                            bis.close();
                            is.close();

                            installApk(file);
                        } catch (Exception e) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (dialog != null) {
                                        dialog.dismiss();
                                    }
                                    Toast.makeText(getActivity(), "下载失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
//        dialog.setContentView(R.layout.update_dialog);
//        dialog.setContentView(view);
        dialog.addContentView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (RootAppcation.getInstance().getWidthPixels() * 0.8);
        window.setAttributes(params);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void ynUpdate() {
        ApiManager.commonApi.apiUpdate().enqueue(new Callback<Version>() {
            @Override
            public void onResponse(Response<Version> response, Retrofit retrofit) {
                if (response != null && response.body() != null && response.body().getApkInfo() != null) {
                    Version.ApkInfo info = response.body().getApkInfo();
                    version = info.getVersionName();
                    if (info.getVersionName().compareTo(getVersionName()) > 0) {
                        downloadUrl = response.body().getApkInfo().getUrl();
                        totalSize = Integer.parseInt(response.body().getApkInfo().getSize());
                        showUpdateDialog();
                    }else {
                        Toast.makeText(app, "当前已是最新版本！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(app, "服务器返回数据异常！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(app, "连接服务器失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getVersionName() {
        PackageManager packageManager = getActivity().getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    protected void showUpdateDialog() {
        MaterialDialog.Builder builer = new MaterialDialog.Builder(getActivity());
        builer.title("版本升级");
        builer.content("当前有新版本，请升级");
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.positiveText("确定");
        builer.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                update();
                dialog.dismiss();
            }
        });
        builer.negativeText("取消");
        builer.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                dialog.dismiss();
            }
        });
        builer.canceledOnTouchOutside(false);
        MaterialDialog dialog = builer.build();
        dialog.show();
    }

    protected void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    public void exit() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

        getActivity().finish();
    }
}
