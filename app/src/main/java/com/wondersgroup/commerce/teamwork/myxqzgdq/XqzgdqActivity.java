package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.dailycheck.UtilForFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jiajiangyi on 2016/4/8 0008.
 */
public class XqzgdqActivity extends AppCompatActivity {

    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;

    public static int currTab = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("限期整改到期");

        UtilForFragment.switchContent(this, new XqzgdqFragment(), R.id.content);

        }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public static void setCurrTab(int tab){
        currTab = tab;
    }
    public static int getCurrTab(){
        return currTab;
    }

}