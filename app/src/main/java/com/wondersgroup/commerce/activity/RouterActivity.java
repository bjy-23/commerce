package com.wondersgroup.commerce.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.dailycheck.UnlicensedAddFragment;

public class RouterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);

        UnlicensedAddFragment fragment = new UnlicensedAddFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.parent,fragment)
                .addToBackStack("router")
                .commit();
    }

    @Override
    public void onBackPressed() {
        Log.e("onBack","onBack");
//        super.onBackPressed();
        finish();
    }
}
