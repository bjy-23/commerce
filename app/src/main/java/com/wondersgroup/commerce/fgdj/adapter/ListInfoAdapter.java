package com.wondersgroup.commerce.fgdj.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.activity.DjxxActivity;
import com.wondersgroup.commerce.fgdj.bean.BaseInfoBean;
import com.wondersgroup.commerce.fgdj.bean.EntBaseInfo;
import com.wondersgroup.commerce.fgdj.bean.FgdjEntListBean;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.widget.LoadingDialog;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/4/26.
 */

public class ListInfoAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    private static final int TYPE_MORMAL = 0;
    private static final int TYPE_FOOTER = 1;

    private List<FgdjEntListBean> data;
    private Context mContext;
    private View footerView;
    private int type = 1;
    private TotalLoginBean loginBean;
    private String entType;

    public ListInfoAdapter(List data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
    }

    @Override
    public int getItemViewType(int position) {
        if (footerView == null) {
            return TYPE_MORMAL;
        }else {
          if (position == getItemCount()-1)
              return TYPE_FOOTER;
        }
        return TYPE_MORMAL;
}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER){
            return new ListInfoViewHolder(footerView);
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_info,parent,false);
        return new ListInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount()-1 && footerView!=null){
            return;
        }
        ListInfoViewHolder viewHolder = (ListInfoViewHolder) holder;
        viewHolder.position = position;

        FgdjEntListBean bean = data.get(position);

        viewHolder.tvEntName.setText(bean.getEntName());
        if ("1".equals(bean.getFgdjEntType()))
            entType = "企业";
        else
            entType = "个体工商户";

        viewHolder.tvEntType.setText("("+entType+")");
        if (!TextUtils.isEmpty(bean.getUniScid()))
            viewHolder.tvEntId.setText(bean.getUniScid());
        else
            viewHolder.tvEntId.setText(bean.getRegNo());
        viewHolder.tvOperTime.setText(bean.getOperTime());
//        viewHolder.imgStatus.setImageLevel(Integer.parseInt(bean.getExistStatus()));//方法失效，原因暂时不知
        int resId =R.drawable.ent_status1 ;
        switch (bean.getExistStatus()){
            case "1":
                resId = R.drawable.ent_status1;
                break;
            case "2":
                resId = R.drawable.ent_status2;
                break;
            case "3":
                resId = R.drawable.ent_status3;
                break;
            case "4":
                resId = R.drawable.ent_status4;
                break;
            case "5":
                resId = R.drawable.ent_status5;
                break;

        }
        viewHolder.imgStatus.setImageResource(resId);
        if ("1".equals(bean.getClaimValid())){
            viewHolder.tvOperation1.setVisibility(View.VISIBLE);
            viewHolder.tvOperation1.setText("退回");
            viewHolder.tvOperation2.setText("编辑");
        }else {
            viewHolder.tvOperation1.setVisibility(View.INVISIBLE);
            viewHolder.tvOperation2.setText(mContext.getResources().getString(R.string.fgdj_subscribe));
        }
        if (type == 0)
            viewHolder.layoutOperation.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        if (footerView!=null)
            return data.size()+1;

        return data.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }

    class ListInfoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvEntName;
        private TextView tvEntType;
        private TextView tvEntId;
        private TextView tvOperTime;
        private ImageView imgStatus;
        private TextView tvOperation1,tvOperation2,tvOperation3;
        private View layoutOperation;
        private int position;

        public ListInfoViewHolder(View itemView) {
            super(itemView);

            if (itemView == footerView){
                return;
            }

            tvEntName = (TextView) itemView.findViewById(R.id.tv_ent_name);
            tvEntType = (TextView) itemView.findViewById(R.id.tv_ent_type);
            tvEntId = (TextView) itemView.findViewById(R.id.tv_ent_id);
            tvOperTime = (TextView) itemView.findViewById(R.id.tv_oper_time);
            imgStatus = (ImageView) itemView.findViewById(R.id.img_stattus);
            layoutOperation = itemView.findViewById(R.id.layout_operation);
            tvOperation1 = (TextView) itemView.findViewById(R.id.tv_operation_1);
            tvOperation1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(Constants.CUR_ORGAN_ID, Constants.CUR_ORGAN_ID_VALUE);
                    hashMap.put(Constants.ENT_ID,data.get(position).getEntId());
                    hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
                    hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
                    hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
                    Call<Result<String>> call = ApiManager.fgdjApi.cancelClaimEnt(hashMap);
                    call.enqueue(new Callback<Result<String>>() {
                        @Override
                        public void onResponse(Response<Result<String>> response, Retrofit retrofit) {
                            if (response.body().getCode() == 0){
                                data.get(position).setClaimValid("0");
                                tvOperation1.setVisibility(View.INVISIBLE);
                                tvOperation2.setText(mContext.getResources().getString(R.string.fgdj_subscribe));
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                }
            });
            tvOperation2 = (TextView) itemView.findViewById(R.id.tv_operation_2);
            tvOperation2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext.getResources().getString(R.string.fgdj_edit).equals(tvOperation2.getText())){
                       getDataEdit(data.get(position).getEntId(), Constants.EDIT);
                    }else if (mContext.getResources().getString(R.string.fgdj_subscribe).equals(tvOperation2.getText())){
                        HashMap hashMap = new HashMap();
                        hashMap.put(Constants.CUR_ORGAN_ID, Constants.CUR_ORGAN_ID_VALUE);
                        hashMap.put(Constants.ENT_ID,data.get(position).getEntId());
                        hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
                        hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
                        hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
                        Call<Result<String>> call = ApiManager.fgdjApi.claimEnt(hashMap);
                        call.enqueue(new Callback<Result<String>>() {
                            @Override
                            public void onResponse(Response<Result<String>> response, Retrofit retrofit) {
                                if (response.body().getCode() == 0){
                                    data.get(position).setClaimValid("1");
                                    tvOperation1.setVisibility(View.VISIBLE);
                                    tvOperation2.setText(mContext.getResources().getString(R.string.fgdj_edit));
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {

                            }
                        });
                    }
                }
            });
            tvOperation3 = (TextView) itemView.findViewById(R.id.tv_operation_3);
            tvOperation3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData(data.get(position).getEntId(), Constants.LOOK);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData(data.get(position).getEntId(), Constants.LOOK);
                }
            });
        }
    }


    public void addFooterView(View view){
        this.footerView = view;
        notifyItemInserted(getItemCount()-1);
    }

    public void removeFooterView(){
        this.footerView = null;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void getData(final String entId,final String type){
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.ENT_ID,entId);
        hashMap.put(Constants.MODULE_ID,"00");
        hashMap.put(Constants.USER_ID,loginBean.getResult().getUserId());
        hashMap.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
        hashMap.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
        Call<Result<BaseInfoBean>> call = ApiManager.fgdjApi.getFgdjEntDetail(hashMap);
        call.enqueue(new Callback<Result<BaseInfoBean>>() {
            @Override
            public void onResponse(Response<Result<BaseInfoBean>> response, Retrofit retrofit) {
                BaseInfoBean baseInfoBean = response.body().getObject();
                EntBaseInfo entBaseInfo = baseInfoBean.getEntBaseInfo();
                if (entBaseInfo!=null){
                    boolean isAddParty = false;
                    boolean isAddLeague = false;
                    if ("1".equals(entBaseInfo.getIsBuildParty())){
                        isAddParty = true;
                    }
                    if ("1".equals(entBaseInfo.getIsBuildLeague())){
                        isAddLeague = true;
                    }

                    Intent intent = new Intent(mContext,DjxxActivity.class);
                    intent.putExtra(Constants.ENT_ID,entId);
                    intent.putExtra(Constants.ENT_TYPE,entType);
                    intent.putExtra(Constants.OPERATION,type);
                    intent.putExtra(Constants.ADD_PARTY,isAddParty);
                    intent.putExtra(Constants.ADD_LEAGUE,isAddLeague);
                    intent.putExtra(Constants.BASE_INFO,baseInfoBean);
                    mContext.startActivity(intent);
                }else {
                    Toast.makeText(mContext,"获取信息失败",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(mContext,"获取信息失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取暂存数据
    public void getDataEdit(final String entId,final String type){
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(mContext).build();
        loadingDialog.show();
        HashMap map = new HashMap();
        map.put(Constants.USER_ID,loginBean.getResult().getUserId());
        map.put(Constants.ORGAN_ID,loginBean.getResult().getOrganId());
        map.put(Constants.DEPT_ID,loginBean.getResult().getDeptId());
        map.put(Constants.ENT_ID,entId);
        Call<Result<BaseInfoBean>> ca = ApiManager.fgdjApi.getTemp(map);
        ca.enqueue(new Callback<Result<BaseInfoBean>>() {
            @Override
            public void onResponse(Response<Result<BaseInfoBean>> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                BaseInfoBean baseInfoBean = response.body().getObject();
                EntBaseInfo entBaseInfo = baseInfoBean.getEntBaseInfo();
                if (entBaseInfo!=null){
                    boolean isAddParty = false;
                    boolean isAddLeague = false;
                    if ("1".equals(entBaseInfo.getIsBuildParty())){
                        isAddParty = true;
                    }
                    if ("1".equals(entBaseInfo.getIsBuildLeague())){
                        isAddLeague = true;
                    }
                    Intent intent = new Intent(mContext,DjxxActivity.class);
                    intent.putExtra(Constants.ENT_ID,entId);
                    intent.putExtra(Constants.ENT_TYPE,entType);
                    intent.putExtra(Constants.OPERATION,type);
                    intent.putExtra(Constants.ADD_PARTY,isAddParty);
                    intent.putExtra(Constants.ADD_LEAGUE,isAddLeague);
                    intent.putExtra(Constants.BASE_INFO,baseInfoBean);
                    mContext.startActivity(intent);
                }else {
                    Toast.makeText(mContext,"获取信息失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(mContext,"获取信息失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
