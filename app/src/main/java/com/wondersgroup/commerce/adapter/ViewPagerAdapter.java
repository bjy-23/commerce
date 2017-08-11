package com.wondersgroup.commerce.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        if ("TSJBCL".equals(type)) {
            data = new ArrayList<>(Arrays.asList(
                    new VpType("投诉", "TSDB", "Title4Row"),
                    new VpType("举报", "JBDB", "Title4Row")
            ));
        } else if ("TSXQCL".equals(type)) {
            data = new ArrayList<>(Arrays.asList(
                    new VpType("投诉详情", "TSXQ", "TableList"),
                    new VpType("投诉处理", "TSCL", "TableList")
            ));
        } else if("JBXQCL".equals(type)){
            data = new ArrayList<>(Arrays.asList(
                    new VpType("举报详情", "JBXQ", "TableList"),
                    new VpType("举报处理", "JBCL", "TableList")
            ));
        } else if ("GWPY".equals(type)) {
            data = new ArrayList<>(Arrays.asList(
                    new VpType("待办", "DBLB", "TestWp"),
                    new VpType("待阅", "DYLB", "TestWp")
            ));
        }else if("SPLCYL".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("审批", "SPYM", "TableWinput"),
                    new VpType("流程一览", "PYLC", "LCGraph")
            ));
        }else if("GWJS".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("收文", "SWJS", "Input"),
                    new VpType("发文", "FWJS", "Input")
            ));
        }else if("GWJSXQ".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("详情", "GWXQ", "TableWinput"),
                    new VpType("流程一览", "GWJSLC", "LCGraph")
            ));
        }else if("TSJBCX".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("投诉查询", "TSCX", "TableList"),
                    new VpType("举报查询", "JBCX", "TableList")
            ));
        }else if("TSXQXXLZ".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("投诉详情", "TSXQLZ", "TableList"),
                    new VpType("处理信息", "CLXX", "TableList"),
                    new VpType("流转记录", "LZJL", "LCGraph")
            ));
        }else if("JBXQXXLZ".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("举报详情", "JBXQLZ", "TableList"),
                    new VpType("处理信息", "CLXX", "TableList"),
                    new VpType("流转记录", "LZJL", "LCGraph")
            ));
        }else if ("CCJCDB".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("基本信息","QYJBXX","TableList"),
                    new VpType("实地核查","SDHC","TableList")
            ));
        }else if("CCJCCX".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("基本信息","QYJBXX","TableList"),
                    new VpType("核查结果","CCJG","TableList"),
                    new VpType("办理意见","BLYJ","LCGraph")
            ));
        }
    }

    @Override
    public Fragment getItem(int position) {
        if ("TSJBCL".equals(type)) {//投诉、举报
            switch (position) {
                case 0:
                case 1:
                    return RecyclerFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                default:
            }
        } else if ("TSXQCL".equals(type)||"JBXQCL".equals(type)) {
            switch (position) {
                case 0:
                case 1:
                    return TableListFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                default:
            }
        } else if ("GWPY".equals(type)) {
            switch (position) {
                case 0:
                case 1:
                    return RecyclerFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                default:
            }
        }else if("SPLCYL".equals(type)){
            switch (position){
                case 0:
                    return PyFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                case 1:
                    return RecyclerFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                default:
            }
        }else if("GWJS".equals(type)){
            switch (position) {
                case 0:
                case 1:
                    return InputFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                default:
            }
        }else if("GWJSXQ".equals(type)){
            switch (position){
                case 0:
                    return PyFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                case 1:
                    return RecyclerFragment.newInstance(data.get(position).getType(), data.get(position).getViewType());
                default:
            }
        }else if("TSJBCX".equals(type)){
            switch (position){
                case 0:
                case 1:
                    return TableListFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                default:
            }
        }else if("TSXQXXLZ".equals(type)){
            switch (position){
                case 0:
                case 1:
                    return TableListFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                case 2:
                    return RecyclerFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                default:
            }
        }else if("JBXQXXLZ".equals(type)){
            switch (position){
                case 0:
                case 1:
                    return TableListFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                case 2:
                    return RecyclerFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                default:
            }
        }else if ("CCJCDB".equals(type)){
            switch (position){
                case 0:
                case 1:
                    return TableListFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
            }
        }else if("CCJCCX".equals(type)){
            switch (position){
                case 0:
                case 1:
                    return TableListFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                case 2:
                    return RecyclerFragment.newInstance(data.get(position).getType(),data.get(position).getViewType());
                default:
            }
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
