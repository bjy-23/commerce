package com.wondersgroup.yngs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.entity.DicResult;
import com.wondersgroup.yngs.entity.UpgradeResult;
import com.wondersgroup.yngs.service.ApiManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SplashActivity extends AppCompatActivity {
    private long size=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        checkLogin();
    }
    public void getDic(){
        final SharedPreferences sp=getSharedPreferences("Default",MODE_PRIVATE);
        String deptId=sp.getString("deptId", "");
        String organId=sp.getString("organId", "");
        int selection=sp.getInt("selection", 0);
        Map<String,String> body=new HashMap<>();
        body.put("deptId",deptId.split(",")[selection]);
        body.put("organId",organId.split(",")[selection]);
        body.put("wsCodeReq",ApiManager.getWsCodeReq());
        Call<DicResult> call=ApiManager.yunNanApi.getDic(body);
        call.enqueue(new Callback<DicResult>() {
            @Override
            public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                if(response.isSuccess()&&response.body()!=null){
                    DicResult result=response.body();
                    if("200".equals(result.getResultCode())){
                        Hawk.put("Dic",result.getResult());
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                    }else if("403".equals(result.getResultCode())) {
                        SharedPreferences.Editor ed=sp.edit();
                        ed.clear();
                        ed.apply();
                        new MaterialDialog.Builder(SplashActivity.this)
                                .title("登录过期")
                                .content("请重新登录")
                                .cancelable(false)
                                .positiveText("确定")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                                        finish();
                                    }
                                })
                                .show();
                    }else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                }else {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SplashActivity.this, "请检查网络连接", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });
    }
    public void checkLogin(){
        SharedPreferences sp=getSharedPreferences("Default",MODE_PRIVATE);
        String token=sp.getString("token","");
        if(token.isEmpty()){
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            finish();
        }else {
            update();
        }

    }
    public void update(){
        Map<String,String> body=new HashMap<>();
        body.put("wsCodeReq", ApiManager.getWsCodeReq());
        Call<UpgradeResult> call=ApiManager.yunNanApi.upgrade(body);
        call.enqueue(new Callback<UpgradeResult>() {
            @Override
            public void onResponse(Response<UpgradeResult> response, Retrofit retrofit) {
                if (response.isSuccess()&&response.body()!=null) {
                    final UpgradeResult result = response.body();
                    if ("200".equals(result.getResultCode())) {
                        if (shouldUpgrade(result.getResult().getVersion())) {
                            size=Long.parseLong(result.getResult().getSize());
                            new MaterialDialog.Builder(SplashActivity.this)
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
                                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            getDic();
                                        }
                                    })
                                    .show();

                        }else {
                            getDic();
                        }
                    } else {
                        Toast.makeText(SplashActivity.this, "更新出错", Toast.LENGTH_SHORT).show();
                        getDic();
                    }
                } else {
                    Toast.makeText(SplashActivity.this, "更新出错", Toast.LENGTH_SHORT).show();
                    getDic();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SplashActivity.this, "检查更新出错", Toast.LENGTH_SHORT).show();
                getDic();
            }
        });
    }

    private String getOldVersionName() {
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    public boolean shouldUpgrade(String newVersion){
        String oldVersion= getOldVersionName();
        String[] oldVersions=oldVersion.split("\\.");
        String[] newVersions=newVersion.split("\\.");
        int length=Math.max(oldVersions.length, newVersions.length);
        for(int i=0;i<length;i++){
            int oldInt=i<oldVersions.length?Integer.parseInt(oldVersions[i]):0;
            int newInt=i<newVersions.length?Integer.parseInt(newVersions[i]):0;
            if(oldInt<newInt)return true;
        }
        return false;
    }

    public void upgrade(String path){
        final String fileName=path.split("/")[path.split("/").length-1];
        final MaterialDialog downDialog=new MaterialDialog.Builder(this)
                .title("下载中")
                .content("请稍等")
                .cancelable(false)
                .negativeText("取消")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        getDic();
                    }
                })
                .progress(false, 100, true)
                .show();
        if(!path.startsWith("http://")){
            path= ApiManager.getApiRoot()+path;
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
                SplashActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SplashActivity.this, "更新下载失败", Toast.LENGTH_SHORT).show();
                        getDic();
                    }
                });
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                if(response.code()==200) {
                    File downloadedFile = new File(getExternalCacheDir(), fileName);
                    BufferedSink sink = Okio.buffer(Okio.sink(downloadedFile));
                    BufferedSource source = response.body().source();
                    long bytesRead = 0;
                    while (source.read(sink.buffer(),2048)!=-1){
                        bytesRead+=2048;
                        downDialog.setProgress((int) ((100 * bytesRead) / size));
                    }
                    sink.writeAll(source);
                    sink.close();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(downloadedFile), "application/vnd.android.package-archive");
                    startActivity(intent);
                    finish();
                }else {
                    downDialog.dismiss();
                    SplashActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SplashActivity.this, "更新下载失败", Toast.LENGTH_SHORT).show();
                            getDic();
                        }
                    });
                }
            }
        });
    }
}
