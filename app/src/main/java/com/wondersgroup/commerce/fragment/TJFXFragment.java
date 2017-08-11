package com.wondersgroup.commerce.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.TjActivity;
import com.wondersgroup.commerce.widget.InfoSelectBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TJFXFragment extends Fragment {

//    @Bind({R.id.neizi_hushu, R.id.neizi_zhuceziben, R.id.neizi_guimo, R.id.waizi_zhuti,
//            R.id.waizi_chengbaoshang, R.id.waizi_guimo, R.id.weiquan_zixun,
//            R.id.weiquan_shangpin, R.id.weiquan_fuwu, R.id.weiquan_qushi,R.id.chufa_bqja,
//            R.id.chufa_fgaj,R.id.chufa_lnaj})
//    List<InfoSelectBar> layoutList;

//    private int[] iconList = {R.drawable.icons_0, R.drawable.icons_1, R.drawable.icons_2,
//            R.drawable.icons_3, R.drawable.icons_4, R.drawable.icons_5, R.drawable.icons_6,
//            R.drawable.icons_7, R.drawable.icons_8, R.drawable.icons_9, R.drawable.icons_10,
//            R.drawable.icons_10,R.drawable.icons_10,R.drawable.icons_10,R.drawable.icons_10,
//            R.drawable.icons_10,R.drawable.icons_10,R.drawable.icons_10};

    @Bind({R.id.qynb_link_1,R.id.qynb_link_2,R.id.qynb_link_3,R.id.qynb_link_4,R.id.qynb_link_5,
            R.id.qynb_link_6,R.id.qynb_link_7,R.id.qynb_link_8,R.id.xwxx_link_1,R.id.xwxx_link_2,
            R.id.xwxx_link_3,R.id.xwxx_link_4,R.id.xwxx_link_5,R.id.jyyc_link_1,R.id.jyyc_link_2,
            R.id.jyyc_link_3,R.id.jyyc_link_4,R.id.jyyc_link_5,R.id.jyyc_link_6,R.id.gtgsh_link_1,
            R.id.gtgsh_link_2,R.id.gtgsh_link_3,R.id.gtgsh_link_4,R.id.gtgsh_link_5,R.id.gtgsh_link_6,
            R.id.gtgsh_link_7,R.id.gtgsh_link_8})
    List<InfoSelectBar> layoutList;

    private int[] iconList = {R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0
    ,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,
            R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0
    ,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0
    ,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0,R.drawable.icons_0};

    private String[] tjTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        tjTitle = getResources().getStringArray(R.array.tj_item);
        tjTitle = getResources().getStringArray(R.array.tj_item_yn);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tjfx, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for(int i=0; i<tjTitle.length; i++){
            layoutList.get(i).setText(tjTitle[i]);
            layoutList.get(i).setIcon(iconList[i]);
        }
    }

//    @OnClick({R.id.neizi_hushu,R.id.neizi_zhuceziben, R.id.neizi_guimo,
//            R.id.waizi_zhuti, R.id.waizi_chengbaoshang, R.id.waizi_guimo,
//            R.id.weiquan_zixun, R.id.weiquan_shangpin, R.id.weiquan_fuwu, R.id.weiquan_qushi,
//            R.id.chufa_bqja,R.id.chufa_fgaj,R.id.chufa_lnaj,R.id.gtgsh_link_1})
    @OnClick({R.id.qynb_link_1,R.id.qynb_link_2,R.id.qynb_link_3,R.id.qynb_link_4,R.id.qynb_link_5,
            R.id.qynb_link_6,R.id.qynb_link_7,R.id.qynb_link_8,R.id.xwxx_link_1,R.id.xwxx_link_2,
            R.id.xwxx_link_3,R.id.xwxx_link_4,R.id.xwxx_link_5,R.id.jyyc_link_1,R.id.jyyc_link_2,
            R.id.jyyc_link_3,R.id.jyyc_link_4,R.id.jyyc_link_5,R.id.jyyc_link_6,R.id.gtgsh_link_1,
            R.id.gtgsh_link_2,R.id.gtgsh_link_3,R.id.gtgsh_link_4,R.id.gtgsh_link_5,R.id.gtgsh_link_6,
            R.id.gtgsh_link_7,R.id.gtgsh_link_8})
    public void onClick(View v){
        Intent intent;
        String title = "";
        switch (v.getId()){
         /*   case R.id.neizi_hushu:
                title = tjTitle[0];
                break;
            case R.id.neizi_zhuceziben:
                title = tjTitle[1];
                break;
            case R.id.neizi_guimo:
                title = tjTitle[2];
                break;
            case R.id.waizi_zhuti:
                title = tjTitle[3];
                break;
            case R.id.waizi_chengbaoshang:
                title = tjTitle[4];
                break;
            case R.id.waizi_guimo:
                title = tjTitle[5];
                break;
            case R.id.weiquan_zixun:
                title = tjTitle[6];
                break;
            case R.id.weiquan_shangpin:
                title = tjTitle[7];
                break;
            case R.id.weiquan_fuwu:
                title = tjTitle[8];
                break;
            case R.id.weiquan_qushi:
                title = tjTitle[9];
                break;
            case R.id.chufa_bqja:
                title = tjTitle[10];
                break;
            case R.id.chufa_fgaj:
                title = tjTitle[11];
                break;
            case R.id.chufa_lnaj:
                title = tjTitle[12];
                break;*/
            case R.id.gtgsh_link_1:
                title = tjTitle[19];
                break;
        }
        intent = new Intent(getActivity(), TjActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

}
