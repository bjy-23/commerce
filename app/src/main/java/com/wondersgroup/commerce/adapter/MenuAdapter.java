package com.wondersgroup.commerce.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.wondersgroup.commerce.fragment.TJFXFragment;
import com.wondersgroup.commerce.law_rule.LawQueryActivity;
import com.wondersgroup.commerce.model.Menu;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.model.MenuFirstPage;
import com.wondersgroup.commerce.model.MenuInfo;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.teamwork.addressbox.TXLActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseInvestigateActivity;
import com.wondersgroup.commerce.teamwork.dailycheck.DailyCheckActivity;
import com.wondersgroup.commerce.teamwork.myspecialcheck.SpecialCheckActivity;
import com.wondersgroup.commerce.teamwork.simpleprocedurecase.ProcedureCaseListActivity;
import com.wondersgroup.commerce.teamwork.trademarkInquiry.TradeMarksListActivity;
import com.wondersgroup.commerce.teamwork.tztg.TZTGActivity;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.utils.FragmentHelper;
import com.wondersgroup.commerce.widget.ExpandableRecyclerView;
import com.wondersgroup.commerce.ynwq.activity.ToDoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bjy on 2017/6/20.
 */

public class MenuAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<MenuInfo> data;
    private int type;
    private LayoutInflater inflater;

    public MenuAdapter(Context context, List<MenuInfo> data, int type) {
        this.context = context;
        this.data = data;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type == 1)
            return new FirstPageViewHolder(inflater.inflate(R.layout.item_fragment_menu, parent, false));
        else if (type ==2)
            return new FirstPageViewHolder2(inflater.inflate(R.layout.item_fragment_menu_2, parent, false));

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (type == 1){
            FirstPageViewHolder viewHolder = (FirstPageViewHolder) holder;
            MenuInfo menuInfo = data.get(position);
            viewHolder.tvTitle.setText(menuInfo.getTitle());
            List<MenuBean> menus = menuInfo.getMenus();
            for (int i = 0; i < menus.size(); i++) {
                MenuBean bean = menus.get(i);
                View view = inflater.inflate(R.layout.item_fragment_menu_item, null, false);
                view.setLayoutParams(new LinearLayout.LayoutParams((int) DWZH.dp2pt(context, 80)
                        , (int) DWZH.dp2pt(context, 80)));
                view.setTag(bean);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doOnClick(v);
                    }
                });
                ((ImageView) view.findViewById(R.id.img_icon)).setBackgroundResource(bean.getResId());
                ((TextView) view.findViewById(R.id.tv_name)).setText(bean.getMenuName());
                viewHolder.layoutContent.addView(view);
                View viewDivide = new View(context);
                viewDivide.setLayoutParams(new LinearLayout.LayoutParams((int) DWZH.dp2pt(context, 30),
                        ViewGroup.LayoutParams.MATCH_PARENT));
                viewHolder.layoutContent.addView(viewDivide);
            }
        }else if (type == 2){
            FirstPageViewHolder2 viewHolder2 = (FirstPageViewHolder2) holder;
            MenuInfo menuInfo = data.get(position);
            viewHolder2.tvTitle.setText(menuInfo.getTitle());
            FirstPageAdapter adapter = new FirstPageAdapter(context, menuInfo.getMenus(), 2);
            viewHolder2.recyclerView.setAdapter(adapter);
            RecyclerView.LayoutManager manager = new GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false);
            viewHolder2.recyclerView.setLayoutManager(manager);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FirstPageViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private LinearLayout layoutContent;

        public FirstPageViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            layoutContent = (LinearLayout) itemView.findViewById(R.id.layout_content);
        }
    }

    class FirstPageViewHolder2 extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ExpandableRecyclerView recyclerView;

        public FirstPageViewHolder2(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            recyclerView = (ExpandableRecyclerView) itemView.findViewById(R.id.layout_content);
        }
    }

    public void doOnClick(View v) {
        Intent intent = null;
        Bundle bundle = null;
        MenuBean bean = (MenuBean) v.getTag();
        switch (bean.getMenuId()) {
            default:
                break;
            case Constants.COMMON_ID:
                switch (bean.getMenuName()){
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
                    case Constants.GGCX_NAME:
                        intent = new Intent(context, QueryActivity.class);
                        context.startActivity(intent);
                        break;
                    case Constants.tj:
                        intent = new Intent(context, QueryCountActivity.class);
                        context.startActivity(intent);
                        break;
                }
                break;
            //我的案件
            case Constants.AJDC_ID:
                ApiManager.caseType = 1;
                if (Constants.AJDC_NAME.equals(bean.getMenuName())){
                    //案件调查
                    bundle = new Bundle();
                    bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                    intent = new Intent(context, CaseInvestigateActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }else if (Constants.WDAJCX_NAME.equals(bean.getMenuName())){
                    //我的案件查询
                    intent = new Intent(context, CaseEnquireActivity.class);
                    intent.putExtra(Constants.TYPE,Constants.WDAJCX_ID);
                    context.startActivity(intent);
                }else if(Constants.FLFG_NAME.equals(bean.getMenuName())){
                    //法律法规
                    intent = new Intent(context, LawQueryActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.TYPE);
                    context.startActivity(intent);
                }

                break;
            case Constants.AJDC_ID_2:
                ApiManager.caseType = 2;
                if (Constants.AJDC_NAME_2.equals(bean.getMenuName())){
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
            case Constants.JYCX_ID://简易程序处理
                bundle = new Bundle();
                bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                intent = new Intent(context, ProcedureCaseListActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case Constants.GWPY_ID:
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
            case Constants.TXL_ID:
                intent = new Intent(context, TXLActivity.class);
                context.startActivity(intent);
                break;
            case Constants.TZGG_ID:
                intent = new Intent(context, TZTGActivity.class);
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
            //抽查检查录入
            case Constants.CCJCLR_ID:
                ApiManager.getInstance().ccInit();
                intent = new Intent(context, RecyclerActivity.class);
                intent.putExtra("type", "CCJCDB");
                intent.putExtra("title", Constants.ccjcdb);
                context.startActivity(intent);
                break;
            //抽查检查查询
            case Constants.CCJCCX_ID:
                ApiManager.getInstance().ccInit();
                intent = new Intent(context, TableListActivity.class);
                intent.putExtra("title", Constants.ccjccx);
                intent.putExtra("type", "CCJCCX");
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
            //公示信息
            case Constants.GSXX_ID:
                intent = new Intent(context, GSActivity.class);
                context.startActivity(intent);
                break;
            //查询统计
            case Constants.CXTJ_ID:
                intent = new Intent(context, QueryCountActivity.class);
                context.startActivity(intent);
                break;
            case Constants.SBCX_ID:
                intent = new Intent(context, TradeMarksListActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
