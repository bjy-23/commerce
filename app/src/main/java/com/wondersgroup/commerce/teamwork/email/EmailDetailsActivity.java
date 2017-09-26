package com.wondersgroup.commerce.teamwork.email;

import android.app.Dialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.FileBean;
import com.wondersgroup.commerce.model.ReceiveDetailBean;
import com.wondersgroup.commerce.model.SendDetailBean;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.utils.DateUtil;
import com.wondersgroup.commerce.utils.FileUtils;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class EmailDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.tv_send_name)
    TextView tvSendName;
    @Bind(R.id.tv_send_email)
    TextView tvSendEmail;
    @Bind(R.id.tv_receieve_name)
    TextView tvReceieveName;
    @Bind(R.id.tv_receieve_email)
    TextView tvReceieveEmail;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.layout_attach)
    LinearLayout layoutAttach;
    @Bind(R.id.layout_back)
    LinearLayout layoutBack;
    @Bind(R.id.img_up)
    ImageView imgUp;
    @Bind(R.id.img_down)
    ImageView imgDown;
    @Bind(R.id.layout_content)
    LinearLayout layoutContent;
    @Bind(R.id.layout_error)
    View viewError;
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.tv_attach)
    TextView tvAttach;
    private HashMap<String, String> param;
    private TotalLoginBean loginBean;
    private int position;
    private ArrayList<EmailBean> emailBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_details);
        ButterKnife.bind(this);

        initData();
        initView();
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_back:
                finish();
                break;
            case R.id.img_up:
                if (R.mipmap.arrow_top_light == Integer.parseInt(String.valueOf(imgUp.getTag()))){
                    layoutContent.setVisibility(View.GONE);
                    viewError.setVisibility(View.GONE);
                    position--;
                    param.put(Constants.ID, emailBeanList.get(position).getMailId());
                    getData();
                    setImgState();
                }
                break;
            case R.id.img_down:
                if (R.mipmap.arrow_bottom_light == Integer.parseInt(String.valueOf(imgDown.getTag()))){
                    layoutContent.setVisibility(View.GONE);
                    viewError.setVisibility(View.GONE);
                    position++;
                    param.put(Constants.ID, emailBeanList.get(position).getMailId());
                    getData();
                    setImgState();
                }
                break;
        }
    }

    public void initView(){
        layoutBack.setOnClickListener(this);
        imgUp.setOnClickListener(this);
        imgUp.setImageResource(R.mipmap.arrow_top_light);
        imgUp.setTag(R.mipmap.arrow_top_light);
        imgDown.setOnClickListener(this);
        imgDown.setImageResource(R.mipmap.arrow_bottom_light);
        imgDown.setTag(R.mipmap.arrow_bottom_light);
        layoutContent.setVisibility(View.GONE);

        setImgState();
    }

    public void initData(){
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        position = getIntent().getIntExtra(Constants.POSITION,0);
        emailBeanList = getIntent().getParcelableArrayListExtra("emailBeanList");
        param = new HashMap<>();
        param.put(Constants.WS_CODE_REQ, "07010016");
        param.put(Constants.USER_ID, loginBean.getResult().getUserId());
        param.put(Constants.USER_ID, "2c9b02065901bba4015914a4e7b20002");
        param.put(Constants.DEPT_ID, loginBean.getResult().getDeptId());
        param.put(Constants.DEPT_ID, "51000000099");
        param.put(Constants.ID, emailBeanList.get(position).getMailId());
    }

    public void getData(){
        final Dialog dialog = LoadingDialog.showCanCancelable(this);
        dialog.show();
        Call<Result<EmailDetailResult>> call = ApiManager.oaApi.getEmailDetail(param);
        call.enqueue(new Callback<Result<EmailDetailResult>>() {
            @Override
            public void onResponse(Response<Result<EmailDetailResult>> response, Retrofit retrofit) {
                dialog.dismiss();
                if (response.body() != null
                        && response.body().getObject() != null
                        && response.body().getObject().getEmailDetailBean() != null
                        ){
                    EmailDetailResult emailDetailResult = response.body().getObject();
                    setData(emailDetailResult);
                    layoutContent.setVisibility(View.VISIBLE);
                }else{
                    dialog.dismiss();
                    viewError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                viewError.setVisibility(View.VISIBLE);
            }
        });
    }

    public void setData(EmailDetailResult emailDetailResult){
        EmailDetailBean emailDetailBean = emailDetailResult.getEmailDetailBean();
        tvHead.setText(emailDetailBean.getTitle());
        tvSendName.setText(emailDetailBean.getSendName());
        tvSendEmail.setText("bjy@1229.com");
        tvReceieveName.setText(loginBean.getResult().getUserName());
        tvReceieveEmail.setText("bjy@1229.com");
        tvTime.setText(DateUtil.getYMDHM(emailDetailBean.getSendtime()));
        tvMessage.setText(emailDetailBean.getContent());
        if (emailDetailResult.getAttachList() != null && emailDetailResult.getAttachList().size() != 0){
            layoutAttach.removeAllViews();
            for (final AttachBean attachBean : emailDetailResult.getAttachList()){
                View view = View.inflate(this,R.layout.item_attach,null);
                view.setPadding(DWZH.dp(5), DWZH.dp(5), DWZH.dp(5), DWZH.dp(5));
                TextView tvName = (TextView) view.findViewById(R.id.tv_name);
                tvName.setText(attachBean.getAttachName());
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getDownLoad(attachBean);
                    }
                });
                layoutAttach.addView(view);
            }
            tvAttach.setVisibility(View.GONE);
            layoutAttach.setVisibility(View.VISIBLE);
        }else {
            layoutAttach.setVisibility(View.GONE);
            tvAttach.setVisibility(View.VISIBLE);
        }
    }

    public int getImageId(ImageView imageView){
        Field[] fields = imageView.getClass().getDeclaredFields();
        int id = 0;
        for (Field field : fields){
            if (field.getName().equals("mResource")){
                field.setAccessible(true);
                try {
                    id = field.getInt(imageView);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return id;
    }

    public void setImgState(){
        if (position == 0){
            imgUp.setImageResource(R.mipmap.arrow_top_gray);
        }else{
            imgUp.setImageResource(R.mipmap.arrow_top_light);
        }

        if (position == emailBeanList.size() -1){
            imgDown.setImageResource(R.mipmap.arrow_bottom_gray);
        }else {
            imgDown.setImageResource(R.mipmap.arrow_bottom_light);
        }
    }

    public void getDownLoad(AttachBean attachBean){
        final Dialog dialog = LoadingDialog.showCanCancelable(this);
        dialog.show();
        Map<String, String> body = new HashMap<>();
        body.put("wsCodeReq", "07010013");
        body.put("attachId", attachBean.getAttachId());
        Call<FileBean> call = ApiManager.oaApi.apiDownload(body);
        call.enqueue(new Callback<FileBean>() {
            @Override
            public void onResponse(Response<FileBean> response, Retrofit retrofit) {
                dialog.dismiss();
                if(response!=null && response.body()!=null){
                    if("200".equals(response.body().getCode())){
                        FileUtils fileUtils = new FileUtils();
                        try {
                            fileUtils.decoderBase64File(EmailDetailsActivity.this,
                                    response.body().getResult().getAttachFile().getAttachFileStr(),
                                    getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                                            + "/"+response.body().getResult().getAttachFile().getAttachName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(EmailDetailsActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Toast.makeText(EmailDetailsActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
