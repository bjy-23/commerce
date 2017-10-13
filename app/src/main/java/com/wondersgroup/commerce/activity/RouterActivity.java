package com.wondersgroup.commerce.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpCallbackListener;
import com.wondersgroup.commerce.teamwork.dailycheck.HttpClientUtil;
import com.wondersgroup.commerce.teamwork.dailycheck.UnlicensedAddFragment;
import com.wondersgroup.commerce.teamwork.dailycheck.Url;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RouterActivity extends BaseActivity{

    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_router);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        tvTitle.setText(Constants.WZJY_NAME);
        tvOption.setText("添加");
        tvOption.setVisibility(View.VISIBLE);
//        tvOption.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getData();
//            }
//        });

        UnlicensedAddFragment fragment = new UnlicensedAddFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.parent,fragment)
                .commit();
    }

    public void getData(){
        Call<ResponseBody> call = ApiManager.consumerwApi.doCheck(loginBean.getResult().getDeptId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        String hebeiAddress = Url.QJ_IN_USE + "doCheck/%20/4/"
                + loginBean.getResult().getDeptId();
        HttpClientUtil.callWebServiceForGet(hebeiAddress,
                new HttpCallbackListener() {

                    @Override
                    public void onFinish(String response) {
                        Log.d("response", response);
                        Message message = new Message();
//                        message.what = SHOW_RESPONSE;
//                        message.obj = response.toString();
//                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {
                        Message message = new Message();
//                        message.what = SHOW_ERROR;
//                        message.obj = e.toString();
//                        handler.sendMessage(message);

                    }
                });
    }
}
