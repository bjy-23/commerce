package com.wondersgroup.commerce.activity;

import android.Manifest;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fragment.FragmentFirstPage;
import com.wondersgroup.commerce.fragment.FragmentMenu;
import com.wondersgroup.commerce.fragment.FragmentOne;
import com.wondersgroup.commerce.fragment.FragmentSc;
import com.wondersgroup.commerce.fragment.FragmentSetting;
import com.wondersgroup.commerce.fragment.FragmentSix;
import com.wondersgroup.commerce.utils.FragmentHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends RootActivity {
    @Bind(R.id.menu_layout)
    public TabLayout menuLayout;

    RootAppcation appcation;
    FragmentManager fm;

    private ImageView homeImg;
    private ImageView mineImg;
    private ImageView infoImg;
    private ImageView caseImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getFragmentManager();

        ButterKnife.bind(this);

        appcation = (RootAppcation) getApplicationContext();

        //添加权限
        addPermission();

        // 底部菜单配置
        if(appcation.getBottomMenus().size() != 1) {
            for (int i = 0; i < appcation.getBottomMenus().size(); i++) {
                View view = View.inflate(this, R.layout.custom_tab, null);
                view.setTag(appcation.getBottomMenus().get(i));
                TextView nameTv = (TextView) view.findViewById(R.id.tv_menu);

                nameTv.setText(appcation.getBottomMenus().get(i));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight = 1;
                view.setLayoutParams(params);

                //设置图标
                switch (appcation.getBottomMenus().get(i)) {
                    case Constants.SY:
                        homeImg = (ImageView) view.findViewById(R.id.icon);
                        homeImg.setBackgroundResource(R.drawable.sy_icons);
                        break;
                    case Constants.YWBL:
                        infoImg = (ImageView) view.findViewById(R.id.icon);
                        infoImg.setBackgroundResource(R.drawable.ywbl_icons);
                        break;
                    case Constants.XXCX:
                        infoImg = (ImageView) view.findViewById(R.id.icon);
                        infoImg.setBackgroundResource(R.drawable.info_icons);
                        break;
                    case Constants.WD:
                        mineImg = (ImageView) view.findViewById(R.id.icon);
                        mineImg.setBackgroundResource(R.drawable.mine_icons);
                        break;
                }
                menuLayout.addTab(menuLayout.newTab().setCustomView(view));
            }
            menuLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getCustomView().getTag().equals(Constants.SY)) {
                        FragmentFirstPage fragmentFirstPage = new FragmentFirstPage();
                        FragmentHelper.replaceFragmentNoBack(fm,fragmentFirstPage,tab.getCustomView().getTag() + "",null,R.id.layout_fragment);
                    }else if (tab.getCustomView().getTag().equals(Constants.YWBL)){
                        FragmentMenu fragmentMenu = new FragmentMenu();
                        fragmentMenu.setTitle(Constants.YWBL);
                        FragmentHelper.replaceFragmentNoBack(fm, fragmentMenu,tab.getCustomView().getTag() + "", null, R.id.layout_fragment);
                    } else if (tab.getCustomView().getTag().equals(Constants.XXCX)){
                        FragmentMenu fragmentMenu = new FragmentMenu();
                        fragmentMenu.setTitle(Constants.XXCX);
                        FragmentHelper.replaceFragmentNoBack(fm, fragmentMenu,tab.getCustomView().getTag() + "", null, R.id.layout_fragment);
                    } else if (tab.getCustomView().getTag().equals(Constants.WD)) {
                        FragmentSetting fragmentSetting = new FragmentSetting();
                        FragmentHelper.replaceFragmentNoBack(fm, fragmentSetting, tab.getCustomView().getTag() + "", null, R.id.layout_fragment);
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }else{
            menuLayout.setVisibility(View.GONE);
        }

        if (Constants.SC.equals(appcation.getVersion())){
            FragmentSc fragmentSc = new FragmentSc();
            FragmentHelper.replaceFragmentNoBack(fm,fragmentSc,"",null,R.id.layout_fragment);
            menuLayout.setVisibility(View.GONE);
        }else if (Constants.YN.equals(appcation.getVersion())){
            FragmentOne fOne = new FragmentOne();
            FragmentHelper.replaceFragmentNoBack(fm, fOne, "首页", null, R.id.layout_fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //禁止：grantResults[] = -1；允许：0；
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void addPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,1);
        }
    }
}
