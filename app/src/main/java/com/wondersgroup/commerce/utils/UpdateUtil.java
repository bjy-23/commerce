package com.wondersgroup.commerce.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.SplashActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.yn.Version;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.TjApi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/8/1.
 */

public class UpdateUtil {
    private Context context;
    private TextView tvSpeed, tvProgress, tvTotal,tvVersion;
    private ProgressBar progressBar;

    private int totalSize;
    private int size, sum;
    private String downloadUrl = "", updateContent = "", versionName = "";
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
                    tvTotal.setTextColor(context.getResources().getColor(R.color.blue));
                    break;
            }
        }
    };

    public UpdateUtil(Context context) {
        this.context = context;
    }

    public void ynUpdate() {
        createApi().apiUpdate().enqueue(new Callback<Version>() {
            @Override
            public void onResponse(Response<Version> response, Retrofit retrofit) {
                if (response != null && response.body() != null && response.body().getApkInfo() != null) {
                    Version.ApkInfo info = response.body().getApkInfo();
                    if (info.getVersionName().compareTo(getVersionName()) > 0) {
                        downloadUrl = response.body().getApkInfo().getUrl();
                        totalSize = Integer.parseInt(response.body().getApkInfo().getSize());
                        for (String item : info.getUpdateList()) {
                            updateContent += item + "\n";
                        }
                        versionName = info.getVersionName();
                        update();
                    } else {
//                        handler.postDelayed(new SplashActivity.splashhandler(), 500);
                    }
                } else {
//                    handler.postDelayed(new SplashActivity.splashhandler(), 500);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("commerceInfo","获取新版本失败");
//                handler.postDelayed(new SplashActivity.splashhandler(), 500);
            }
        });
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
                .baseUrl(ApiManager.VERSION_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(TjApi.class);
    }

    /**
     * 获取当前版本号
     */
    private String getVersionName() {
        PackageManager packageManager = RootAppcation.getInstance().getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(RootAppcation.getInstance().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    private void update() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(R.layout.update_dialog, null, false);
        final ViewGroup layoutProgress = (ViewGroup) view.findViewById(R.id.layout_progress);
        TextView content = (TextView) view.findViewById(R.id.content);
        content.setText(updateContent);
        TextView version = (TextView) view.findViewById(R.id.version);
        version.setText("V" + versionName);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setMax(100);
        final ViewGroup layoutChoose = (ViewGroup) view.findViewById(R.id.layout_choose);
        tvSpeed = (TextView) view.findViewById(R.id.tv_speed);
        tvProgress = (TextView) view.findViewById(R.id.tv_progress);
        tvTotal = (TextView) view.findViewById(R.id.tv_total);
        TextView tvRefuse = (TextView) view.findViewById(R.id.tv_refuse);
        tvRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                finish();
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
                            File file = new File(RootAppcation.getInstance().getCachePath(), "update.apk");
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
                            dialog.dismiss();
                        } catch (Exception e) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (dialog != null) {
//                                        dialog.dismiss();
//                                    }
//                                    Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            finish();
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

    protected void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
