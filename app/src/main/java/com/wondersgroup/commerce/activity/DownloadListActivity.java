package com.wondersgroup.commerce.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.FileBean;
import com.wondersgroup.commerce.model.ReceiveDetailBean;
import com.wondersgroup.commerce.model.SendDetailBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.wywork.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DownloadListActivity extends AppCompatActivity {

    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;

    private List<SendDetailBean.Result.DocAttachFile> dataList;
    private DlistAdapter dlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.app_back);
        title.setText(getIntent().getStringExtra("title"));

        initView();
    }

    private void initView(){
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataList = Hawk.get("fjlist");
        dlistAdapter = new DlistAdapter(this, dataList);
        recyclerView.setAdapter(dlistAdapter);
        dlistAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                getDoc(position);
            }
        });
    }

    public class DlistAdapter extends RecyclerView.Adapter {
        private Context mContext;
        private List<SendDetailBean.Result.DocAttachFile> mData;
        private LayoutInflater inflater;

        private OnClickListener onClickListener;

        public DlistAdapter(Context mContext, List mData) {
            this.mContext = mContext;
            this.mData = mData;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_download_list,parent,false);
            return new SingleItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            SingleItemViewHolder viewHolder = (SingleItemViewHolder) holder;
            viewHolder.tvValue.setText(mData.get(position).getAttachName());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class SingleItemViewHolder extends RecyclerView.ViewHolder{
            private TextView tvValue;
            private TextView downloadBtn;

            public SingleItemViewHolder(View itemView) {
                super(itemView);

                tvValue = (TextView) itemView.findViewById(R.id.tv_content);
                downloadBtn = (TextView) itemView.findViewById(R.id.download);
                downloadBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickListener!=null){
                            onClickListener.OnItemClick(v,getLayoutPosition());
                        }
                    }
                });
            }
        }

        public void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
    }

    public interface OnClickListener{
        void OnItemClick(View view, int position);
    }

    //下载附件
    public void getDoc(int pos){
        Map<String, String> body = new HashMap<>();
        body.put("wsCodeReq", "07010013");
        body.put("attachId", dataList.get(pos).getAttachId());
        Call<FileBean> call = ApiManager.oaApi.apiDownload(body);
        call.enqueue(new Callback<FileBean>() {
            @Override
            public void onResponse(Response<FileBean> response, Retrofit retrofit) {
                if(response!=null && response.body()!=null){
                    if("200".equals(response.body().getCode())){
                        FileUtils fileUtils = new FileUtils();
                        try {
                            fileUtils.decoderBase64File(DownloadListActivity.this,
                                    response.body().getResult().getAttachFile().getAttachFileStr(),
                                    DownloadListActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                                            + "/"+response.body().getResult().getAttachFile().getAttachName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(DownloadListActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(DownloadListActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
