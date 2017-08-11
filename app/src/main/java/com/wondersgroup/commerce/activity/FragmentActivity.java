package com.wondersgroup.commerce.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.fragment.TJFXFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_txt)
    TextView title;
    @Bind(R.id.toolbar_btn)
    Button button;

    TJFXFragment tjfxFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        title.setText("统计");
        toolbar.setTitle("");
        button.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);

        tjfxFragment = new TJFXFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, tjfxFragment)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
