package com.wondersgroup.commerce.recyclerView.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.GSActivity;
import com.wondersgroup.commerce.activity.QueryActivity;
import com.wondersgroup.commerce.activity.QueryCountActivity;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.activity.RouterActivity;
import com.wondersgroup.commerce.activity.ViewPagerActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.recyclerView.ViewModel;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.addressbox.TXLActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseInvestigateActivity;
import com.wondersgroup.commerce.teamwork.clue.activity.ClueQueryActivity;
import com.wondersgroup.commerce.teamwork.dailycheck.DailyCheckActivity;
import com.wondersgroup.commerce.teamwork.myspecialcheck.SpecialCheckActivity;
import com.wondersgroup.commerce.teamwork.simpleprocedurecase.ProcedureCaseListActivity;
import com.wondersgroup.commerce.teamwork.trademarkInquiry.TradeMarksListActivity;
import com.wondersgroup.commerce.teamwork.tztg.TZTGActivity;

/**
 * Created by bjy on 2017/11/8.
 */

public class FirstPageScItemViewModel extends ViewModel implements View.OnClickListener {
    private Context context;
    private MenuBean menuBean;

    public FirstPageScItemViewModel(Context context, MenuBean menuBean) {
        this.context = context;
        this.menuBean = menuBean;
    }

    @Override
    public int type() {
        return R.layout.item_sc;
    }

    @Override
    public RecyclerView.ViewHolder viewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void dataBind(RecyclerView.ViewHolder viewHolder) {
        ViewHolder holder = (ViewHolder) viewHolder;
        if (menuBean != null) {
            holder.imgIcon.setBackgroundResource(menuBean.getResId());
            holder.tvName.setText(menuBean.getMenuName());
            holder.view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        Bundle bundle;
        switch (menuBean.getMenuId()) {
            case Constants.COMMON_ID:
                switch (menuBean.getMenuName()) {
                    case Constants.TXL_NAME_SC:
                        intent = new Intent(context, TXLActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.TZGG_NAME_SC:
                        intent = new Intent(context, TZTGActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.EMAIL_NAME_SC:
                        intent = new Intent(context, RecyclerActivity.class);
                        intent.putExtra(Constants.TITLE, Constants.EMAIL_NAME);
                        intent.putExtra(Constants.TYPE, "email");
                        context.startActivity(intent);
                        break;
                    case Constants.RCJG_NAME_SC:
                        intent = new Intent(context, DailyCheckActivity.class);
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
                    case Constants.GGCX_NAME_SC:
                        intent = new Intent(context, QueryActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.TJ_NAME_SC:
                        intent = new Intent(context, QueryCountActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.GSXX_NAME_SC:
                        intent = new Intent(context, GSActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.XSDJ_NAME_SC:
                        intent = new Intent(context, RecyclerActivity.class);
                        intent.putExtra(Constants.TYPE, Constants.XSDJ_NAME_SC);
                        context.startActivity(intent);
                        break;
                    case Constants.XSWH_NAME_SC:
                        intent = new Intent(context, RecyclerActivity.class);
                        intent.putExtra(Constants.TYPE, Constants.XSWH_NAME_SC);
                        context.startActivity(intent);
                        break;
                    case Constants.XSCX_NAME_SC:
                        intent = new Intent(context, ClueQueryActivity.class);
                        context.startActivity(intent);
                        break;
                }
                break;
            case Constants.GW_ID_SC:
                switch (menuBean.getMenuName()) {
                    case Constants.GWPY_NAME_SC:
                        intent = new Intent(context, ViewPagerActivity.class);
                        intent.putExtra("title", "公文批阅");
                        intent.putExtra("type", "GWPY");
                        context.startActivity(intent);
                        break;
                    case Constants.GWJS_NAME_SC:
                        intent = new Intent(context, ViewPagerActivity.class);
                        intent.putExtra("title", "公文检索");
                        intent.putExtra("type", "GWJS");
                        context.startActivity(intent);
                        break;
                }
                break;
            case Constants.AJDC_ID_SC:
                ApiManager.caseType = 1;
                bundle = new Bundle();
                bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                intent = new Intent(context, CaseInvestigateActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case Constants.AJCX_ID_SC:
                intent = new Intent(context, CaseEnquireActivity.class);
                intent.putExtra(Constants.TYPE, Constants.AJCX_ID);
                context.startActivity(intent);
                break;
            case Constants.JYCX_ID_SC:
                bundle = new Bundle();
                bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                intent = new Intent(context, ProcedureCaseListActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case Constants.SSJLR_ID_SC:
                Toast.makeText(context, "功能正在开发中！", Toast.LENGTH_SHORT).show();
                break;
            case Constants.SBCX_ID_SC:
                intent = new Intent(context, TradeMarksListActivity.class);
                context.startActivity(intent);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        private TextView tvName;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            imgIcon = itemView.findViewById(R.id.img_icon);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public MenuBean getMenuBean() {
        return menuBean;
    }

    public void setMenuBean(MenuBean menuBean) {
        this.menuBean = menuBean;
    }
}
