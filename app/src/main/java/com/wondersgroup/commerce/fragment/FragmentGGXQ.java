package com.wondersgroup.commerce.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.TableRow;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bjy on 2017/9/15.
 * 广告详情
 */

public class FragmentGGXQ extends Fragment {
    @Bind(R.id.layout_root)
    LinearLayout layoutRoot;

    public static FragmentGGXQ newInstance(String type){
        FragmentGGXQ fragmentGGXQ = new FragmentGGXQ();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        fragmentGGXQ.setArguments(bundle);
        return fragmentGGXQ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ggxq, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        switch (getArguments().getString(Constants.TYPE)){
            case "jydw":
                jydw();//经营单位
                break;
            case "cyry":
                cyry();//从业人员
                break;
            case "tjcl":
                tjcl();//提交材料
                break;
        }
    }

    public void jydw(){
        TableRow jbxx = new TableRow.Builder(getActivity())
                .asTitle("广告经营单位基本信息")
                .build();
        layoutRoot.addView(jbxx);

        TableRow dwmc = new TableRow.Builder(getActivity())
                .title("单位名称")
                .content("")
                .build();
        layoutRoot.addView(dwmc);

        TableRow zzjgdm = new TableRow.Builder(getActivity())
                .title("组织机构代码")
                .content("")
                .build();
        layoutRoot.addView(zzjgdm);

        TableRow dwxz = new TableRow.Builder(getActivity())
                .title("单位性质")
                .content("")
                .build();
        layoutRoot.addView(dwxz);

        TableRow dwlx = new TableRow.Builder(getActivity())
                .title("单位类型")
                .content("")
                .build();
        layoutRoot.addView(dwlx);

        TableRow zgdw = new TableRow.Builder(getActivity())
                .title("主管单位")
                .content("")
                .build();
        layoutRoot.addView(zgdw);

        TableRow djjg = new TableRow.Builder(getActivity())
                .title("登记机关")
                .content("")
                .build();
        layoutRoot.addView(djjg);

        TableRow zs = new TableRow.Builder(getActivity())
                .title("住所（经营场所)")
                .content("")
                .build();
        layoutRoot.addView(zs);

        TableRow lxr = new TableRow.Builder(getActivity())
                .title("联系人")
                .content("")
                .build();
        layoutRoot.addView(lxr);

        TableRow dh = new TableRow.Builder(getActivity())
                .title("移动电话")
                .content("")
                .build();
        layoutRoot.addView(dh);

        TableRow yddh = new TableRow.Builder(getActivity())
                .title("移动电话")
                .content("")
                .build();
        layoutRoot.addView(yddh);

        TableRow email = new TableRow.Builder(getActivity())
                .title("Email")
                .content("")
                .build();
        layoutRoot.addView(email);

        TableRow sqlxr = new TableRow.Builder(getActivity())
                .title("申请联系人")
                .content("")
                .build();
        layoutRoot.addView(sqlxr);

        TableRow sqlxdh = new TableRow.Builder(getActivity())
                .title("申请联系电话")
                .content("")
                .build();
        layoutRoot.addView(sqlxdh);

        TableRow lxfs = new TableRow.Builder(getActivity())
                .title("申请方式")
                .content("")
                .build();
        layoutRoot.addView(lxfs);

        TableRow fddbr = new TableRow.Builder(getActivity())
                .asTitle("法定代表人情况")
                .build();
        layoutRoot.addView(fddbr);

        TableRow xm = new TableRow.Builder(getActivity())
                .title("姓名")
                .content("")
                .build();
        layoutRoot.addView(xm);

        TableRow xb = new TableRow.Builder(getActivity())
                .title("性别")
                .content("")
                .build();
        layoutRoot.addView(xb);

        TableRow zjlx = new TableRow.Builder(getActivity())
                .title("证件类型")
                .content("")
                .build();
        layoutRoot.addView(zjlx);

        TableRow zjhm = new TableRow.Builder(getActivity())
                .title("证件号码")
                .content("")
                .build();
        layoutRoot.addView(zjhm);

        TableRow lxdh = new TableRow.Builder(getActivity())
                .title("联系电话")
                .content("")
                .build();
        layoutRoot.addView(lxdh);

        TableRow zz = new TableRow.Builder(getActivity())
                .title("住址")
                .content("")
                .build();
        layoutRoot.addView(zz);

        TableRow mjxx = new TableRow.Builder(getActivity())
                .asTitle("广告发布媒介信息")
                .build();
        layoutRoot.addView(mjxx);

        layoutRoot.addView(divide());

        for (int i=0; i<2; i++){
            TableRow mjmc = new TableRow.Builder(getActivity())
                    .title("媒介名称")
                    .content("")
                    .build();
            layoutRoot.addView(mjmc);

            TableRow yxq = new TableRow.Builder(getActivity())
                    .title("有效期")
                    .content("")
                    .build();
            layoutRoot.addView(yxq);

            TableRow bz = new TableRow.Builder(getActivity())
                    .title("备注")
                    .content("")
                    .build();
            layoutRoot.addView(bz);

            layoutRoot.addView(divide());
        }
    }

    public void cyry(){
        layoutRoot.addView(divide());

        for (int i=0; i<5; i++){
            TableRow xm = new TableRow.Builder(getActivity())
                    .title("姓名")
                    .content("")
                    .build();
            layoutRoot.addView(xm);

            TableRow rylx = new TableRow.Builder(getActivity())
                    .title("人员类型")
                    .content("")
                    .build();
            layoutRoot.addView(rylx);

            TableRow lxfs = new TableRow.Builder(getActivity())
                    .title("联系方式")
                    .content("")
                    .build();
            layoutRoot.addView(lxfs);

            TableRow zsbh = new TableRow.Builder(getActivity())
                    .title("广告专业技术岗位培训证书编号")
                    .content("")
                    .build();
            layoutRoot.addView(zsbh);

            TableRow zjlx = new TableRow.Builder(getActivity())
                    .title("证件类型")
                    .content("")
                    .build();
            layoutRoot.addView(zjlx);

            TableRow zjhm = new TableRow.Builder(getActivity())
                    .title("证件号码")
                    .content("")
                    .build();
            layoutRoot.addView(zjhm);

            layoutRoot.addView(divide());
        }
    }

    public void tjcl(){
        layoutRoot.addView(divide());

        for (int i=0; i<10; i++){
            TableRow wjmc = new TableRow.Builder(getActivity())
                    .title("文件名称")
                    .content("")
                    .build();
            layoutRoot.addView(wjmc);

            TableRow ys = new TableRow.Builder(getActivity())
                    .title("页数")
                    .content("")
                    .build();
            layoutRoot.addView(ys);

            TableRow sftj = new TableRow.Builder(getActivity())
                    .title("是否提交")
                    .content("")
                    .build();
            layoutRoot.addView(sftj);

            TableRow tjsj = new TableRow.Builder(getActivity())
                    .title("提交时间")
                    .content("")
                    .build();
            layoutRoot.addView(tjsj);

            TableRow bz = new TableRow.Builder(getActivity())
                    .title("备注")
                    .content("")
                    .build();
            layoutRoot.addView(bz);

            TableRow wjsc = new TableRow.Builder(getActivity())
                    .title("文件上传 ")
                    .content("")
                    .build();
            layoutRoot.addView(wjsc);

            layoutRoot.addView(divide());
        }
    }

    public View divide(){
        View view = new View(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DWZH.dp(5));
        view.setLayoutParams(lp);
        view.setBackgroundColor(getResources().getColor(R.color.light_gray));

        return view;
    }
}
