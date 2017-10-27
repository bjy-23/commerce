package com.wondersgroup.commerce.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.adapter.MenuAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Menu;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.model.MenuInfo;
import com.wondersgroup.commerce.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bjy on 2017/6/20.
 * 业务办理、信息查询（云南）
 */

public class FragmentMenu extends Fragment {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<MenuInfo> data;
    private MenuAdapter adapter;
    private String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imgBack.setVisibility(View.GONE);
        tvTitle.setText(title);

        data = new ArrayList<>();
        if (Constants.YWBL.equals(title) && Hawk.get(Constants.YWBL_MNEUINFO) != null){
            data = Hawk.get(Constants.YWBL_MNEUINFO);
//            data = RootAppcation.getInstance().getYwblMenuInfos();
        }else if (Constants.XXCX.equals(title) && Hawk.get(Constants.MESSAGE_MENUINFO) != null){
            data = Hawk.get(Constants.MESSAGE_MENUINFO);
        }

        adapter = new MenuAdapter(getActivity(), data, 1);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
