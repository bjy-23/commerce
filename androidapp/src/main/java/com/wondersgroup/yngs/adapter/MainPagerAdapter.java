package com.wondersgroup.yngs.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.fragment.BurNingFragment;
import com.wondersgroup.yngs.fragment.SettingFragment;
import com.wondersgroup.yngs.fragment.ViewPagerFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 薛定猫 on 2015/12/8.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    public static final int NUM=4;
    private String [] titles={"首页","综合查询","统计","设置"};
    private int [] drwables={R.drawable.tab_burning,R.drawable.tab_sumail,R.drawable.tab_cold,R.drawable.tab_maybe};
    private Context context;
    public MainPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return BurNingFragment.newInstance(""+position);
            case 1:
                return ViewPagerFragment.newInstance("ZHCX");
            case 2:
                return ViewPagerFragment.newInstance("TJ");
                //return BlankFragment.newInstance("");
            case 3:
                return SettingFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM;
    }
    public View getTabView(int position){
        View view= View.inflate(context,R.layout.view_cus_tab,null);
        ViewHolder holder=new ViewHolder(view);
        view.setTag(holder);
        holder.icon.setImageDrawable(ContextCompat.getDrawable(context,drwables[position]));
        holder.text.setText(titles[position]);
        return view;
    }
    static class ViewHolder{
        @Bind(R.id.icon)ImageView icon;
        @Bind(R.id.text1)TextView text;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
