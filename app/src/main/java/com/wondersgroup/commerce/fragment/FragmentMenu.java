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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bjy on 2017/6/20.
 * 业务办理、信息查询（云南）
 */

public class FragmentMenu extends Fragment {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.recyclerView)
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
        if (Constants.ywbl.equals(title) && Hawk.get(Constants.YWBL_MNEUINFO) != null){
            data = Hawk.get(Constants.YWBL_MNEUINFO);
//            data = RootAppcation.getInstance().getYwblMenuInfos();
        }else if (Constants.xxcx.equals(title) && Hawk.get(Constants.MESSAGE_MENUINFO) != null){
            data = Hawk.get(Constants.MESSAGE_MENUINFO);
        }

        adapter = new MenuAdapter(getActivity(),data);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void makeData(MenuBean menuBean, String menuId, List<MenuBean> list){
        for (MenuBean bean : list){
            if (menuId.equals(bean.getMenuId())){
                boolean isHave = false;
                int index = 0;
                String title = "";
                switch (menuBean.getMenuName()){
                    case Constants.AJDC_NAME:
                    case Constants.AJCX_NAME:
                        title = "执法办案";
                        break;
                    case Constants.CCJCLR_NAME:
                    case Constants.CCJCCX_NAME:
                        title = "公示抽查检查";
                        break;
                    case Constants.TSJBCL_NAME:
                    case Constants.TSJBCX_NAME:
                        title = "消费维权";
                        break;
                    case Constants.FGDJGL_NAME:
                    case Constants.FGDJCX_NAME:
                        title = "非公党建";
                        break;
                    case Constants.WQCB_NAME:
                        title = "小微企业扶持";
                        break;
                    case Constants.GSXX_NAME:
                    case Constants.FLFG_NAME:
                        title = "查询";
                        break;
                    case Constants.CXTJ_NAME:
                        title = "统计";
                        break;
                }
                for (int i=0;i<data.size();i++){
                    if (data.get(i).getTitle().equals(title)){
                        isHave = true;
                        index = i;
                        break;
                    }
                }
                if (isHave){
                    List<MenuBean> beanList = data.get(index).getMenus();
                    boolean have = false;
                    for (MenuBean bean1 : beanList){
                        if (bean1.getMenuName().equals(menuBean.getMenuName())){
                            have = true;
                            break;
                        }
                    }
                    if (!have){
                        data.get(index).getMenus().add(menuBean);
                    }

                }else {
                    MenuInfo menuInfo1 = new MenuInfo();
                    menuInfo1.setTitle(title);
                    ArrayList<MenuBean> arrayList1 = new ArrayList<>();
                    arrayList1.add(menuBean);
                    menuInfo1.setMenus(arrayList1);
                    data.add(menuInfo1);
                }

                break;
            }
        }
    }
}
