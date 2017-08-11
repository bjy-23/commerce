package com.wondersgroup.commerce.teamwork.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Memo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 1229 on 2016/3/21.
 */
public class MemoActivity extends AppCompatActivity implements MemoAdapter.OnItemTouchListener{

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.add)
    Button addbtn;
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;

    private ArrayList<Memo> memos;
    private DbHelper dbHelper;
    private RecyclerView.LayoutManager layoutManager;
    private MemoAdapter adapter;
    private String userid;
    private RootAppcation rootAppcation;
    private int spacingInPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("备忘录");

        rootAppcation = (RootAppcation)MemoActivity.this.getApplication();
        switch(rootAppcation.getVersion()){
            case "河北":
                userid = (rootAppcation.getTotalLoginBean()==null)?"1229":rootAppcation.getTotalLoginBean().getResult().getUserId();
                break;
            case "上海":
                userid = (rootAppcation.getShLoginBean()==null)?"1229":rootAppcation.getShLoginBean().getValues().getUser().getBusinessUserId();
                break;
            case "云南":
                userid = (rootAppcation.getYnLoginBean()==null)?"1229":rootAppcation.getYnLoginBean().getResult().getUserId();
                break;
        }
        Log.d("userid", userid);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(MemoActivity.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        //设置item的边距
//        spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
//        recyclerview.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        dbHelper = DbHelper.create();
        memos = dbHelper.query_memo(userid);
        adapter = new MemoAdapter(MemoActivity.this, memos);

        //增加新的备忘
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Constants.TYPE, Constants.ADD);
                intent.putExtra(Constants.USER_ID,userid);
                intent.setClass(MemoActivity.this, MemoEditActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        getData();
        recyclerview.setAdapter(adapter);
        adapter.setOnItemTouchListener(this);

    }



    public void getData() {
        memos.clear();
        memos = dbHelper.query_memo(userid);

        adapter = new MemoAdapter(this, memos);
    }



    @Override
    public void onItemTouchListener(int position, View view) {
        if(view.getTag()=="1"){
            TextView tv1 = (TextView) view.findViewById(R.id.content);
            TextView tv2 = (TextView) view.findViewById(R.id.time2);
            String time0 = memos.get(position).getMemo_time0();
            Intent intent = new Intent();
            intent.putExtra(Constants.TYPE, Constants.EDIT);
            intent.putExtra(Constants.MEMO_CONTENT, tv1.getText());
            intent.putExtra(Constants.TIME_CLOCK, tv2.getText());
            intent.putExtra("time0", time0);
            intent.setClass(this, MemoEditActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            String time0 = memos.get(position).getMemo_time0();
            dbHelper.delete_memo(time0);
            memos.remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, adapter.getItemCount());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
