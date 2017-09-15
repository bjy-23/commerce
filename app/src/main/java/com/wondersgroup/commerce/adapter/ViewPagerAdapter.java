package com.wondersgroup.commerce.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wondersgroup.commerce.fragment.FragmentGGXQ;
import com.wondersgroup.commerce.fragment.InputFragment;
import com.wondersgroup.commerce.fragment.PyFragment;
import com.wondersgroup.commerce.fragment.RecyclerFragment;
import com.wondersgroup.commerce.fragment.TableListFragment;
import com.wondersgroup.commerce.model.VpType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 薛定猫 on 2016/3/3.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String type;
    private List<VpType> data;

    public ViewPagerAdapter(FragmentManager fm, String type) {
        super(fm);
        this.type = type;
        switch (type){
            case "GGXQ":
                data = new ArrayList<>(Arrays.asList(
                        new VpType("广告经营单位", "jydw"),
                        new VpType("广告从业人员", "cyry"),
                        new VpType("提交材料" ,"tjcl")
                ));
                break;
            case "TSJBCL"://投诉举报处理
                data = new ArrayList<>(Arrays.asList(
                        new VpType("投诉", "TSDB", "Title4Row"),
                        new VpType("举报", "JBDB", "Title4Row")
                ));
                break;
            case "TSXQCL"://投诉详情处理
                data = new ArrayList<>(Arrays.asList(
                        new VpType("投诉详情", "TSXQ", "TableList"),
                        new VpType("投诉处理", "TSCL", "TableList")
                ));
                break;
            case "JBXQCL"://举报详情处理
                data = new ArrayList<>(Arrays.asList(
                        new VpType("举报详情", "JBXQ", "TableList"),
                        new VpType("举报处理", "JBCL", "TableList")
                ));
                break;
            case "GWPY"://公文批阅
                data = new ArrayList<>(Arrays.asList(
                        new VpType("待办", "DBLB", "TestWp"),
                        new VpType("待阅", "DYLB", "TestWp")
                ));
                break;
            case "SPLCYL":
                data=new ArrayList<>(Arrays.asList(
                        new VpType("审批", "SPYM", "TableWinput"),
                        new VpType("流程一览", "PYLC", "LCGraph")
                ));
                break;
            case "GWJS"://公文检索
                data=new ArrayList<>(Arrays.asList(
                        new VpType("收文", "SWJS", "Input"),
                        new VpType("发文", "FWJS", "Input")
                ));
                break;
            case "GWJSXQ"://公文检索详情
                data=new ArrayList<>(Arrays.asList(
                        new VpType("详情", "GWXQ", "TableWinput"),
                        new VpType("流程一览", "GWJSLC", "LCGraph")
                ));
                break;
            case "TSJBCX"://投诉举报查询
                data=new ArrayList<>(Arrays.asList(
                        new VpType("投诉查询", "TSCX", "TableList"),
                        new VpType("举报查询", "JBCX", "TableList")
                ));
                break;
            case "TSXQXXLZ":
                data=new ArrayList<>(Arrays.asList(
                        new VpType("投诉详情", "TSXQLZ", "TableList"),
                        new VpType("处理信息", "CLXX", "TableList"),
                        new VpType("流转记录", "LZJL", "LCGraph")
                ));
                break;
            case "JBXQXXLZ":
                data=new ArrayList<>(Arrays.asList(
                        new VpType("举报详情", "JBXQLZ", "TableList"),
                        new VpType("处理信息", "CLXX", "TableList"),
                        new VpType("流转记录", "LZJL", "LCGraph")
                ));
                break;
            case "CCJCDB":
                data=new ArrayList<>(Arrays.asList(
                        new VpType("基本信息","QYJBXX","TableList"),
                        new VpType("实地核查","SDHC","TableList")
                ));
                break;
            case "CCJCCX":
                data=new ArrayList<>(Arrays.asList(
                        new VpType("基本信息","QYJBXX","TableList"),
                        new VpType("核查结果","CCJG","TableList"),
                        new VpType("办理意见","BLYJ","LCGraph")
                ));
                break;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (type){
            case "GGXQ":
                return FragmentGGXQ.newInstance(data.get(position).getType());
            case "TSJBCL":
                switch (position) {
                    case 0:
                    case 1:
                        return RecyclerFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    default:
                }
                break;
            case "TSXQCL":
            case "JBXQCL":
                switch (position) {
                    case 0:
                    case 1:
                        return TableListFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    default:
                }
                break;
            case "GWPY":
                switch (position) {
                    case 0:
                    case 1:
                        return RecyclerFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    default:
                }
                break;
            case "SPLCYL":
                switch (position){
                    case 0:
                        return PyFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    case 1:
                        return RecyclerFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    default:
                }
                break;
            case "GWJS":
                switch (position) {
                    case 0:
                    case 1:
                        return InputFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    default:
                }
                break;
            case "GWJSXQ":
                switch (position){
                    case 0:
                        return PyFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    case 1:
                        return RecyclerFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    default:
                }
                break;
            case "TSJBCX":
                switch (position){
                    case 0:
                    case 1:
                        return TableListFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                    default:
                }
                break;
            case "TSXQXXLZ":
                switch (position){
                    case 0:
                    case 1:
                        return TableListFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                    case 2:
                        return RecyclerFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                    default:
                }
                break;
            case "JBXQXXLZ":
                switch (position){
                    case 0:
                    case 1:
                        return TableListFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                    case 2:
                        return RecyclerFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                    default:
                }
                break;
            case "CCJCDB":
                switch (position){
                    case 0:
                    case 1:
                        return TableListFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                }
                break;
            case "CCJCCX":
                switch (position) {
                    case 0:
                    case 1:
                        return TableListFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    case 2:
                        return RecyclerFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                    default:
                }
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getTitle();
    }
}
