package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.GSActivity;
import com.wondersgroup.commerce.activity.QueryActivity;
import com.wondersgroup.commerce.activity.QueryCountActivity;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.activity.RouterActivity;
import com.wondersgroup.commerce.activity.ViewPagerActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.addressbox.TXLActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseInvestigateActivity;
import com.wondersgroup.commerce.teamwork.dailycheck.DailyCheckActivity;
import com.wondersgroup.commerce.teamwork.myspecialcheck.SpecialCheckActivity;
import com.wondersgroup.commerce.teamwork.simpleprocedurecase.ProcedureCaseListActivity;
import com.wondersgroup.commerce.teamwork.trademarkInquiry.TradeMarksListActivity;
import com.wondersgroup.commerce.teamwork.tztg.TZTGActivity;
import com.wondersgroup.commerce.ynwq.activity.ToDoActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bjy on 2017/6/28.
 */

public class FirstPageAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private List<MenuBean> data;
    private LayoutInflater inflater;
    private int type;

    public FirstPageAdapter(Context context, List<MenuBean> data, int type) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (type == 1) {
            view = inflater.inflate(R.layout.item_wddb, parent, false);
            return new FirstPageViewHolder(view);
        } else if (type == 2) {
            view = inflater.inflate(R.layout.item_sc, parent, false);
            return new ScViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (type == 1) {
            FirstPageViewHolder viewHolder = (FirstPageViewHolder) holder;
            MenuBean bean = data.get(position);
            viewHolder.imgBg.setBackgroundResource(bean.getResId());
            viewHolder.tvNumber.setText(bean.getNumber());
            viewHolder.tvName.setText(bean.getMenuName());
            viewHolder.view.setTag(bean);
        } else if (type == 2) {
            ScViewHolder viewHolder = (ScViewHolder) holder;
            MenuBean menuBean = data.get(position);
            viewHolder.imgIcon.setBackgroundResource(menuBean.getResId());
            viewHolder.tvName.setText(menuBean.getMenuName());
            viewHolder.view.setTag(menuBean);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        MenuBean bean = (MenuBean) v.getTag();
        Intent intent;
        Bundle bundle;
        switch (bean.getMenuId()) {
            case Constants.COMMON_ID:
                switch (bean.getMenuName()) {
                    case Constants.SSJLR:
                        Toast.makeText(context, "功能正在开发中！", Toast.LENGTH_SHORT).show();
                        break;
                    case Constants.RCJG:
                        intent = new Intent(context, DailyCheckActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.GGCX_NAME_SC:
                        intent = new Intent(context, QueryActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.ZXZZ_NAME:
                        intent = new Intent(context, SpecialCheckActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.WZJY_NAME:
                        intent = new Intent(context, RouterActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.EMAIL_NAME:
                        intent = new Intent(context, RecyclerActivity.class);
                        intent.putExtra(Constants.TITLE, Constants.EMAIL_NAME);
                        intent.putExtra(Constants.TYPE, "email");
                        context.startActivity(intent);
                        break;
                    case Constants.TJ_NAME_SC:
                        intent = new Intent(context, QueryCountActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.TXL_NAME_SC:
                        intent = new Intent(context, TXLActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.TZGG_NAME_SC:
                        intent = new Intent(context, TZTGActivity.class);
                        context.startActivity(intent);
                        break;
                }

                break;
            case Constants.TSJBCL_ID:
                intent = new Intent(context, ViewPagerActivity.class);
                intent.putExtra("type", "TSJBCL");
                context.startActivity(intent);
                break;
            case Constants.AJDC_ID:
                ApiManager.caseType = 1;
                if (Constants.AJDC_NAME.equals(bean.getMenuName())) {
                    bundle = new Bundle();
                    bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                    intent = new Intent(context, CaseInvestigateActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                } else if (Constants.WDAJCX_NAME.equals(bean.getMenuName())) {
                    intent = new Intent(context, CaseEnquireActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.WDAJCX_ID);
                    context.startActivity(intent);
                }
                break;
            case Constants.AJCX_ID:
            case Constants.AJCX_ID_SC:
                intent = new Intent(context, CaseEnquireActivity.class);
                intent.putExtra(Constants.TYPE,Constants.AJCX_ID);
                context.startActivity(intent);
                break;
            case Constants.JYCX_ID:
            case Constants.JYCX_ID_SC:
                bundle = new Bundle();
                bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                intent = new Intent(context, ProcedureCaseListActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case Constants.CCJCLR_ID:
                intent = new Intent(context, RecyclerActivity.class);
                intent.putExtra("type", "CCJCDB");
                intent.putExtra("title", Constants.ccjcdb);
                context.startActivity(intent);
                break;
            case Constants.WQCB_ID:
                TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
                HashMap<String, String> body = new HashMap<>();
                body.put("userId", loginBean.getResult().getUserId());
                body.put("deptId", loginBean.getResult().getDeptId());
                body.put("flowStatus", "0103");
                body.put("organId", loginBean.getResult().getOrganId());

                intent = new Intent(context, ToDoActivity.class);
                intent.putExtra("title", "财政补助待资格初审");
                intent.putExtra("body", body);
                context.startActivity(intent);
                break;
            case Constants.TXL_ID:
                intent = new Intent(context, TXLActivity.class);
                context.startActivity(intent);
                break;
            case Constants.TZGG_ID:
                intent = new Intent(context, TZTGActivity.class);
                context.startActivity(intent);
                break;
            //公示信息
            case Constants.GSXX_ID:
                intent = new Intent(context, GSActivity.class);
                context.startActivity(intent);
                break;
            case Constants.GWPY_ID:
            case Constants.GWPY_ID_SC:
                intent = new Intent(context, ViewPagerActivity.class);
                intent.putExtra("title", "公文批阅");
                intent.putExtra("type", "GWPY");
                context.startActivity(intent);
                break;
            case Constants.GWJS_ID:
                intent = new Intent(context, ViewPagerActivity.class);
                intent.putExtra("title", "公文检索");
                intent.putExtra("type", "GWJS");
                context.startActivity(intent);
                break;
            case Constants.SBCX_ID:
                intent = new Intent(context, TradeMarksListActivity.class);
                context.startActivity(intent);
                break;
        }
    }

    class FirstPageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBg;
        private TextView tvNumber;
        private TextView tvName;
        private View view;

        public FirstPageViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            view.setOnClickListener(FirstPageAdapter.this);
            imgBg = (ImageView) itemView.findViewById(R.id.img_bg);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    class ScViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        private TextView tvName;
        private View view;

        public ScViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            view.setOnClickListener(FirstPageAdapter.this);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
