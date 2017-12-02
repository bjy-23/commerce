package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.wondersgroup.commerce.model.MenuInfo;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.recyclerView.CommonAdapter;
import com.wondersgroup.commerce.recyclerView.ViewModel;
import com.wondersgroup.commerce.recyclerView.viewModel.MenuViewModel;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.addressbox.TXLActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseInvestigateActivity;
import com.wondersgroup.commerce.teamwork.myspecialcheck.SpecialCheckActivity;
import com.wondersgroup.commerce.teamwork.simpleprocedurecase.ProcedureCaseListActivity;
import com.wondersgroup.commerce.teamwork.trademarkInquiry.TradeMarksListActivity;
import com.wondersgroup.commerce.teamwork.tztg.TZTGActivity;
import com.wondersgroup.commerce.utils.DWZH;
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
            return new FirstPageViewHolder2(inflater.inflate(R.layout.first_page_sc_item, parent, false));

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (type == 1){
            FirstPageViewHolder viewHolder = (FirstPageViewHolder) holder;
            MenuInfo menuInfo = data.get(position);
            viewHolder.tvTitle.setText(menuInfo.getTitle());
            List<ViewModel> list = new ArrayList<>();
            for (MenuBean menuBean: menuInfo.getMenus()){
                MenuViewModel menuViewModel = new MenuViewModel(menuBean, context);
                list.add(menuViewModel);
            }
            CommonAdapter commonAdapter = new CommonAdapter(context, list);
            viewHolder.recyclerView.setAdapter(commonAdapter);
            viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false));
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
        private ExpandableRecyclerView recyclerView;

        public FirstPageViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            recyclerView = itemView.findViewById(R.id.recyclerView);
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
}
