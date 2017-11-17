package com.wondersgroup.commerce.recyclerView.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.wondersgroup.commerce.model.MenuInfo;
import com.wondersgroup.commerce.recyclerView.CommonAdapter;
import com.wondersgroup.commerce.recyclerView.ViewModel;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.addressbox.TXLActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseInvestigateActivity;
import com.wondersgroup.commerce.teamwork.dailycheck.DailyCheckActivity;
import com.wondersgroup.commerce.teamwork.myspecialcheck.SpecialCheckActivity;
import com.wondersgroup.commerce.teamwork.simpleprocedurecase.ProcedureCaseListActivity;
import com.wondersgroup.commerce.teamwork.trademarkInquiry.TradeMarksListActivity;
import com.wondersgroup.commerce.teamwork.tztg.TZTGActivity;
import com.wondersgroup.commerce.widget.ExpandableRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjy on 2017/11/8.
 */

public class FirstPageScViewModel extends ViewModel {
    private Context context;
    private MenuInfo menuInfo;

    public FirstPageScViewModel(Context context, MenuInfo menuInfo) {
        this.context = context;
        this.menuInfo = menuInfo;
    }

    @Override
    public int type() {
        return R.layout.first_page_sc_item;
    }

    @Override
    public RecyclerView.ViewHolder viewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void dataBind(RecyclerView.ViewHolder viewHolder) {
        ViewHolder holder = (ViewHolder) viewHolder;
        if (menuInfo != null){
            holder.tvTitle.setText(menuInfo.getTitle());

            //组装recyclerView
            final List<ViewModel> viewModels = new ArrayList<>();
            if (menuInfo.getMenus() != null && menuInfo.getMenus().size() != 0){
                for (MenuBean menuBean: menuInfo.getMenus()){
                    FirstPageScItemViewModel firstPageScItemViewModel = new FirstPageScItemViewModel(context, menuBean);
                    viewModels.add(firstPageScItemViewModel);
                }
                CommonAdapter commonAdapter = new CommonAdapter(context, viewModels);
                commonAdapter.setOnItemClick(new CommonAdapter.OnItemClick() {
                    @Override
                    public void onClick(int position) {
                        onMenuClick(((FirstPageScItemViewModel)viewModels.get(position)).getMenuBean());
                    }
                });
                holder.recyclerView.setAdapter(commonAdapter);
                RecyclerView.LayoutManager manager = new GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false);
                holder.recyclerView.setLayoutManager(manager);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private ExpandableRecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            recyclerView = itemView.findViewById(R.id.layout_content);
        }
    }

    public void onMenuClick(MenuBean menuBean){
    }
}
