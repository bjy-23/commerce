package com.wondersgroup.commerce.recyclerView.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.GSActivity;
import com.wondersgroup.commerce.activity.QueryActivity;
import com.wondersgroup.commerce.activity.QueryCountActivity;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.activity.RouterActivity;
import com.wondersgroup.commerce.activity.TableListActivity;
import com.wondersgroup.commerce.activity.ViewPagerActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.activity.ListInfoActivity;
import com.wondersgroup.commerce.fgdj.activity.ListQueryActivity;
import com.wondersgroup.commerce.law_rule.LawQueryActivity;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.recyclerView.ViewModel;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.addressbox.TXLActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseInvestigateActivity;
import com.wondersgroup.commerce.teamwork.myspecialcheck.SpecialCheckActivity;
import com.wondersgroup.commerce.teamwork.simpleprocedurecase.ProcedureCaseListActivity;
import com.wondersgroup.commerce.teamwork.trademarkInquiry.TradeMarksListActivity;
import com.wondersgroup.commerce.teamwork.tztg.TZTGActivity;
import com.wondersgroup.commerce.ynwq.FragmentWrapperActivity;
import com.wondersgroup.commerce.ynwq.activity.ToDoActivity;

import java.util.HashMap;

/**
 * Created by bjy on 2017/11/30.
 */

public class MenuViewModel extends ViewModel {
    private MenuBean menuBean;
    private Context context;

    public MenuViewModel(MenuBean menuBean) {
        this.menuBean = menuBean;
    }

    public MenuViewModel(MenuBean menuBean, Context context) {
        this.menuBean = menuBean;
        this.context = context;
    }

    @Override
    public int type() {
        return R.layout.item_fragment_menu_item;
    }

    @Override
    public RecyclerView.ViewHolder viewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void dataBind(RecyclerView.ViewHolder viewHolder) {
        ViewHolder holder = (ViewHolder) viewHolder;

        holder.imgIcon.setBackgroundResource(menuBean.getResId());
        holder.tvName.setText(menuBean.getMenuName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOnClick(menuBean);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private ImageView imgIcon;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imgIcon = itemView.findViewById(R.id.img_icon);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public void doOnClick(MenuBean menuBean) {
        Intent intent = null;
        Bundle bundle = null;
        switch (menuBean.getMenuId()) {
            default:
                break;
            case Constants.COMMON_ID:
                switch (menuBean.getMenuName()){
                    case Constants.GSXX_NAME_YN:
                        intent = new Intent(context, GSActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.TJFX_NAME_YN:
                        intent = new Intent(context, QueryCountActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.BLJDCX_NAME_YN:
                        intent = new Intent(context, FragmentWrapperActivity.class);
                        intent.putExtra(Constants.TYPE, "办理进度");
                        context.startActivity(intent);
                        break;
                    case Constants.WQXXCX_NAME_YN:
                        intent = new Intent(context, FragmentWrapperActivity.class);
                        intent.putExtra(Constants.TYPE, "微企信息");
                        context.startActivity(intent);
                        break;
                    case Constants.FCQKCX_NAME_YN:
                        intent = new Intent(context, FragmentWrapperActivity.class);
                        intent.putExtra(Constants.TYPE, "扶持情况");
                        context.startActivity(intent);
                        break;
                    case Constants.TJWQ_NAME_YN:
                        intent = new Intent(context, com.wondersgroup.commerce.ynwq.activity.ViewPagerActivity.class);
                        intent.putExtra(Constants.TYPE, "TJ");
                        context.startActivity(intent);
                        break;
                }
                break;
            //我的案件
            case Constants.AJDC_ID:
                ApiManager.caseType = 1;
                if (Constants.AJDC_NAME.equals(menuBean.getMenuName())){
                    //案件调查
                    bundle = new Bundle();
                    bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                    intent = new Intent(context, CaseInvestigateActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }else if (Constants.WDAJCX_NAME.equals(menuBean.getMenuName())){
                    //我的案件查询
                    intent = new Intent(context, CaseEnquireActivity.class);
                    intent.putExtra(Constants.TYPE,Constants.WDAJCX_ID);
                    context.startActivity(intent);
                }else if(Constants.FLFG_NAME.equals(menuBean.getMenuName())){
                    //法律法规
                    intent = new Intent(context, LawQueryActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.TYPE);
                    context.startActivity(intent);
                }

                break;
            case Constants.AJDC_ID_2:
                ApiManager.caseType = 2;
                if (Constants.AJDC_NAME_2.equals(menuBean.getMenuName())){
                    //案件调查
                    bundle = new Bundle();
                    bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                    intent = new Intent(context, CaseInvestigateActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }else {
                    //我的案件查询
                    intent = new Intent(context, CaseEnquireActivity.class);
                    intent.putExtra(Constants.TYPE,Constants.WDAJCX_ID_2);
                    context.startActivity(intent);
                }

                break;
            //案件查询
            case Constants.AJCX_ID:
                //案件查询
                intent = new Intent(context, CaseEnquireActivity.class);
                intent.putExtra(Constants.TYPE,Constants.AJCX_ID);
                context.startActivity(intent);
                break;

            //微企财补初审
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
            //投诉举报处理
            case Constants.TSJBCL_ID:
                intent = new Intent(context, ViewPagerActivity.class);
                intent.putExtra("type", "TSJBCL");
                context.startActivity(intent);

                break;
            //投诉举报查询
            case Constants.TSJBCX_ID:
                intent = new Intent(context, ViewPagerActivity.class);
                intent.putExtra("type", "TSJBCX");
                intent.putExtra("title", "投诉举报查询");
                context.startActivity(intent);

                break;
            //非公党建查询
            case Constants.FGDJCX_ID:
                intent = new Intent(context, ListQueryActivity.class);
                context.startActivity(intent);

                break;
            //非公党建管理
            case Constants.FGDJGL_ID:
                intent = new Intent(context, ListInfoActivity.class);
                intent.putExtra(Constants.TYPE, Constants.GUAN_LI);
                context.startActivity(intent);
//                intent = new Intent(context, com.example.fgdj.ExampleActivity.class);
//                intent.putExtra(Constants.TYPE, Constants.GUAN_LI);
//                context.startActivity(intent);
                break;
        }
    }

}
