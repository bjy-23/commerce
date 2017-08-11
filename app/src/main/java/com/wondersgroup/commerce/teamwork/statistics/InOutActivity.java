package com.wondersgroup.commerce.teamwork.statistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 列入移出
 */
public class InOutActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("列入移出");
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new InOutAdapter(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
