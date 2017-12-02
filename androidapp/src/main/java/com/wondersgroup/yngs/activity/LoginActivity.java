package com.wondersgroup.yngs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.entity.LoginResult;
import com.wondersgroup.yngs.entity.UserResult;
import com.wondersgroup.yngs.service.ApiManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.login_user_name)EditText userName;
    @Bind(R.id.login_password)EditText password;
    @Bind(R.id.login_login)Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        login.setOnClickListener(this);
        SharedPreferences sp=getApplicationContext().getSharedPreferences("Default", MODE_PRIVATE);
        String token=sp.getString("token", "");
        if(!token.equals("")){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if(login==v){
//            if(userName.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
//               new MaterialDialog.Builder(LoginActivity.this)
//                       .title("出错")
//                       .content("用户名或密码不能为空")
//                       .positiveText("确定")
//                       .show();
//            }else {
                final MaterialDialog progress=new MaterialDialog.Builder(this)
                        .title("请稍候")
                        .content("正在登录中...")
                        .progress(true,0)
                        .show();
                final Map<String, String> body = new HashMap<>();
//                body.put("loginName", "xtywz");
//                body.put("password", "xdrcft56"); sj_qianb/Qb1234
            body.put("loginName", userName.getText().toString());
            body.put("password", password.getText().toString());
                body.put("wsCodeReq",ApiManager.getWsCodeReq());
                ApiManager.getInstance().init("",true);
                Call<LoginResult> call = ApiManager.yunNanApi.login(body);
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Response<LoginResult> response, Retrofit retrofit) {
                        if (response.isSuccess()&&response.body()!=null) {
                            LoginResult result = response.body();
                            if ("200".equals(result.getResultCode())) {
                                ApiManager.setToken(result.getResult().getToken());
                                boolean isReRoot=false;
                                if("2".equals(result.getResult().getType())){
                                    isReRoot=true;
                                    ApiManager.getInstance().init(result.getResult().getToken(),isReRoot);
                                }
                                SharedPreferences sp = getApplicationContext().getSharedPreferences("Default", MODE_PRIVATE);
                                final SharedPreferences.Editor ed = sp.edit();
                                ed.putString("token", result.getResult().getToken());
                                ed.putString("userId", result.getResult().getUserId());
                                ed.putBoolean("isReRoot", isReRoot);
                                ed.apply();
                                //ApiManager.getInstance().init(result.getResult().getToken(),isReRoot);

                                Map<String,String> userInfoBody=new HashMap<>();
                                userInfoBody.put("userId", result.getResult().getUserId());
                                userInfoBody.put("wsCodeReq",ApiManager.getWsCodeReq());
                                progress.setContent("获取用户信息中...");
                                final Call<UserResult> userCall=ApiManager.yunNanApi.getUserInfo(userInfoBody);
                                userCall.enqueue(new Callback<UserResult>() {
                                    @Override
                                    public void onResponse(Response<UserResult> response, Retrofit retrofit) {
                                        progress.dismiss();
                                        if (response.isSuccess()) {
                                            UserResult userResult = response.body();
                                            if ("200".equals(userResult.getResultCode())) {
                                                String[] deptNames = userResult.getResult().getDeptName().split(",");
                                                ed.putString("userName",userResult.getResult().getUserName());
                                                ed.putString("deptId", userResult.getResult().getDeptId());
                                                ed.putString("deptName", userResult.getResult().getDeptName());
                                                ed.putString("organId", userResult.getResult().getOrganId());
                                                ed.putString("organName", userResult.getResult().getOrganName());
                                                if (deptNames.length > 1) {
                                                    new MaterialDialog.Builder(LoginActivity.this)
                                                            .title("选择部门")
                                                            .items(deptNames)
                                                            .cancelable(false)
                                                            .positiveText("确定")
                                                            .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                                                @Override
                                                                public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                                                    ed.putInt("selection", which);
                                                                    ed.apply();
                                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                                    finish();
                                                                    return true;
                                                                }
                                                            })
                                                            .show();
                                                } else {
                                                    ed.putInt("selection", 0);
                                                    ed.apply();
                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                    finish();
                                                }
                                            }else {
                                                new MaterialDialog.Builder(LoginActivity.this)
                                                        .title("出错")
                                                        .content(userResult.getMessage())
                                                        .positiveText("确定")
                                                        .show();
                                                ed.clear();
                                                ed.apply();
                                            }
                                        }else {
                                            new MaterialDialog.Builder(LoginActivity.this)
                                                    .title("出错")
                                                    .content("登录失败")
                                                    .positiveText("确定")
                                                    .show();
                                            ed.clear();
                                            ed.apply();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        progress.hide();
                                        new MaterialDialog.Builder(LoginActivity.this)
                                                .title("出错")
                                                .content("登录失败")
                                                .positiveText("确定")
                                                .show();
                                        ed.clear();
                                        ed.apply();
                                    }
                                });
                            }else if("404".equals(result.getResultCode())){
                                boolean isReRoot=true;
                                SharedPreferences sp=getSharedPreferences("Default",MODE_PRIVATE);
                                final SharedPreferences.Editor ed=sp.edit();
                                ApiManager.getInstance().init("",isReRoot);
                                Call<LoginResult> reCall=ApiManager.yunNanApi.login(body);
                                reCall.enqueue(new Callback<LoginResult>() {
                                    @Override
                                    public void onResponse(Response<LoginResult> response, Retrofit retrofit) {
                                        if(response.isSuccess()&&response.body()!=null){
                                            LoginResult reResult=response.body();
                                            if("200".equals(reResult.getResultCode())){
                                                ApiManager.setToken(reResult.getResult().getToken());

                                                ed.putString("token", reResult.getResult().getToken());
                                                ed.putString("userId", reResult.getResult().getUserId());
                                                ed.putBoolean("isReRoot", true);
                                                ed.apply();
                                                //ApiManager.getInstance().init(result.getResult().getToken(),isReRoot);

                                                Map<String,String> userInfoBody=new HashMap<>();
                                                userInfoBody.put("userId", reResult.getResult().getUserId());
                                                userInfoBody.put("wsCodeReq",ApiManager.getWsCodeReq());
                                                progress.setContent("获取用户信息中...");
                                                final Call<UserResult> userCall=ApiManager.yunNanApi.getUserInfo(userInfoBody);
                                                userCall.enqueue(new Callback<UserResult>() {
                                                    @Override
                                                    public void onResponse(Response<UserResult> response, Retrofit retrofit) {
                                                        progress.hide();
                                                        if (response.isSuccess()&&response.body()!=null) {
                                                            UserResult userResult = response.body();
                                                            if ("200".equals(userResult.getResultCode())) {
                                                                String[] deptNames = userResult.getResult().getDeptName().split(",");
                                                                ed.putString("userName", userResult.getResult().getUserName());
                                                                ed.putString("deptId", userResult.getResult().getDeptId());
                                                                ed.putString("deptName", userResult.getResult().getDeptName());
                                                                ed.putString("organId", userResult.getResult().getOrganId());
                                                                ed.putString("organName", userResult.getResult().getOrganName());
                                                                if (deptNames.length > 1) {
                                                                    new MaterialDialog.Builder(LoginActivity.this)
                                                                            .title("选择部门")
                                                                            .items(deptNames)
                                                                            .cancelable(false)
                                                                            .positiveText("确定")
                                                                            .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                                                                                @Override
                                                                                public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                                                                    ed.putInt("selection", which);
                                                                                    ed.apply();
                                                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                                                    finish();
                                                                                    return true;
                                                                                }
                                                                            })
                                                                            .show();
                                                                } else {
                                                                    ed.putInt("selection", 0);
                                                                    ed.apply();
                                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                                    finish();
                                                                }
                                                            } else {
                                                                new MaterialDialog.Builder(LoginActivity.this)
                                                                        .title("出错")
                                                                        .content(userResult.getMessage())
                                                                        .positiveText("确定")
                                                                        .show();
                                                                ed.clear();
                                                                ed.apply();
                                                            }
                                                        } else {
                                                            new MaterialDialog.Builder(LoginActivity.this)
                                                                    .title("出错")
                                                                    .content("登录失败")
                                                                    .positiveText("确定")
                                                                    .show();
                                                            ed.clear();
                                                            ed.apply();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Throwable t) {
                                                        progress.hide();
                                                        new MaterialDialog.Builder(LoginActivity.this)
                                                                .title("出错")
                                                                .content("登录失败")
                                                                .positiveText("确定")
                                                                .show();
                                                        ed.clear();
                                                        ed.apply();
                                                    }
                                                });
                                            }else {
                                                progress.hide();
                                                new MaterialDialog.Builder(LoginActivity.this)
                                                        .title("出错")
                                                        .content(reResult.getMessage())
                                                        .positiveText("确定")
                                                        .show();
                                            }
                                        }else {
                                            progress.hide();
                                            new MaterialDialog.Builder(LoginActivity.this)
                                                    .title("出错")
                                                    .content(response.message())
                                                    .positiveText("确定")
                                                    .show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        progress.hide();
                                        new MaterialDialog.Builder(LoginActivity.this)
                                                .title("出错")
                                                .content("网络错误")
                                                .positiveText("确定")
                                                .show();
                                    }
                                });
                            }else {
                                progress.hide();
                                new MaterialDialog.Builder(LoginActivity.this)
                                        .title("出错")
                                        .content(result.getMessage())
                                        .positiveText("确定")
                                        .show();
                            }
                        } else {
                            progress.hide();
                            new MaterialDialog.Builder(LoginActivity.this)
                                    .title("出错")
                                    .content(response.message())
                                    .positiveText("确定")
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        progress.hide();
                        new MaterialDialog.Builder(LoginActivity.this)
                                .title("出错")
                                .content("网络错误")
                                .positiveText("确定")
                                .show();
                    }
                });
            }
        }
//    }
}
