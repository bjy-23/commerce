package com.wondersgroup.commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.SingleChoiceAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.AreaBean;
import com.wondersgroup.commerce.model.KeyValue;
import com.wondersgroup.commerce.utils.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SingleChoiceActivity extends BaseActivity implements SingleChoiceAdapter.OnClickListener{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<AreaBean> data;
    private SingleChoiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_single_choice);
        ButterKnife.bind(this);

        tvTitle.setText(getIntent().getStringExtra(Constants.TITLE));
        data =  getIntent().getParcelableArrayListExtra(Constants.AREA_LIST);
        adapter = new SingleChoiceAdapter(this,data);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.RESPONSE_AREA_CODE){
            setResult(Constants.RESPONSE_AREA_CODE,data);
            finish();
        }
    }

    @Override
    public void onNext(String id,String title) {
        ArrayList<AreaBean> list = new ArrayList<>();
        ArrayList<AreaBean> arrayList;
        if (Constants.BASE_AREA_LIST.equals(getIntent().getStringExtra(Constants.TYPE)))
            arrayList = Hawk.get(Constants.BASE_AREA_LIST);
        else
            arrayList = Hawk.get(Constants.PARTY_AREA_LIST);

        for (int i=0;i<arrayList.size();i++){
            if (id.equals(arrayList.get(i).getpId())){
                list.add(arrayList.get(i));
            }
        }

        for (int i=0;i<list.size();i++){
            for (int j=0;j<arrayList.size();j++){
                if (arrayList.get(j).getpId()!=null){
                    if ((arrayList.get(j).getpId()).equals(list.get(i).getId())){
                        list.get(i).setHasChild(true);
                        break;
                    }
                }
            }
        }
        Intent intent = new Intent(this, SingleChoiceActivity.class);
        intent.putExtra(Constants.TITLE,title);
        intent.putExtra(Constants.TYPE,getIntent().getStringExtra(Constants.TYPE));
        intent.putExtra(Constants.AREA_LIST,list);
        startActivityForResult(intent,Constants.REQUEST_AREA_CODE);
    }

    @Override
    public void onBack(KeyValue keyValue) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_VALUE,keyValue);
        setResult(Constants.RESPONSE_AREA_CODE,intent);
        finish();
    }
}
