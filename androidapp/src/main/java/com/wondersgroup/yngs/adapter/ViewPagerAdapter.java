package com.wondersgroup.yngs.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.wondersgroup.yngs.entity.VpType;
import com.wondersgroup.yngs.fragment.BmtjFragment;
import com.wondersgroup.yngs.fragment.JinduQueryFragment;
import com.wondersgroup.yngs.fragment.ProcessFragment;
import com.wondersgroup.yngs.fragment.QueryFragment;
import com.wondersgroup.yngs.fragment.QutjFragment;
import com.wondersgroup.yngs.fragment.TJFragment;
import com.wondersgroup.yngs.fragment.XCKCFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/23.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    private FragmentManager fm;
    private List<VpType> data;
    private String type;
    private String meId;
    private String etpsId;
    private String xckcType;
    public ViewPagerAdapter(FragmentManager fm,String type,@Nullable String XCKCType) {
        super(fm);
        if("DBXW".equals(type)){
            this.xckcType=XCKCType;
            if("1".equals(XCKCType)||"2".equals(XCKCType)) {
                data = new ArrayList<>(Arrays.asList(
                        new VpType("小微企业信息", "XWXQ"),
                        new VpType("办理信息", "BLXX"),
                        new VpType("现场勘察记录", "XCKC")
                ));
            }else {
                data = new ArrayList<>(Arrays.asList(
                        new VpType("小微企业信息", "XWXQ"),
                        new VpType("办理信息", "BLXX")
                ));
            }
        }else if("ZHCX".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("办理进度","JDCX"),
                    new VpType("微企信息","WQCX"),
                    new VpType("扶持情况","WQFCCX"),
                    new VpType("企业公示","QYCX")
            ));
        }else if("WQCX".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("基本信息","JBXX"),
                    new VpType("扶持情况","FCQK")
            ));
        }else if("TJ".equals(type)){
            data=new ArrayList<>(Arrays.asList(
                    new VpType("所在部门","SZBM"),
                    new VpType("所在区域","SZQY"),
                    new VpType("所在行业","SZHY"),
                    new VpType("进展情况","JZQK")
            ));
        }
        this.fm=fm;
        this.type=type;
    }

    @Override
    public Fragment getItem(int position) {
        if("DBXW".equals(type)){
            switch (position){
                case 0:
                case 1:
                    return ProcessFragment.newInstance(data.get(position).getType(),position==0?etpsId:meId);
                case 2:
                    return XCKCFragment.newInstance(meId,xckcType);
            }
        }else if ("ZHCX".equals(type)){
            switch (position){
                case 0:
                    return JinduQueryFragment.newInstance(data.get(position).getType());
                case 1:
                case 2:
                case 3:
                    return QueryFragment.newInstance(data.get(position).getType());
            }
        }else if("WQCX".equals(type)){
            return ProcessFragment.newInstance(data.get(position).getType(),position==0?etpsId:meId);
        }else if ("TJ".equals(type)){
            switch (position){
                case 0:
                    return BmtjFragment.newInstance();
                case 1:
                    return QutjFragment.newInstance();
                case 2:
                case 3:
                    return TJFragment.newInstance(data.get(position).getType());
            }
        }
        return null;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }


    public void setMeId(String meId) {
        this.meId = meId;
    }

    public void setEtpsId(String etpsId) {
        this.etpsId = etpsId;
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
