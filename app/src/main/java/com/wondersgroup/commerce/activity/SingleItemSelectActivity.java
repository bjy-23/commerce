package com.wondersgroup.commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.SingelItemAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.model.KeyValue;
import com.wondersgroup.commerce.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleItemSelectActivity extends BaseActivity implements SingelItemAdapter.OnClickListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String type;
    private List<KeyValue> mData;
    private SingelItemAdapter mAdapter;
    private LinkedHashMap linkedHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_single_item_select);
        ButterKnife.bind(this);

        tvTitle.setText(getIntent().getStringExtra(Constants.TITLE));

        type = Constants.TYPE;
        type = getIntent().getStringExtra(Constants.TYPE);
        DicBean dicBean = Hawk.get(Constants.DIC);
        switch (type){
            default:
                break;
            case Constants.GENDER:
                linkedHashMap = dicBean.getGender();
                break;
            case Constants.POLITICAL_STATUS:
                linkedHashMap = dicBean.getPoliticalStatus();
                break;
            case Constants.ES_WAY:
                linkedHashMap = dicBean.getMapEsWay();
                break;
            case Constants.SOCIAL_DUTY:
                linkedHashMap = dicBean.getMapSocialDuty();
                break;
            case Constants.CERT_TYPE:
                linkedHashMap = dicBean.getCertType();
                break;
            case Constants.NATION:
                linkedHashMap = dicBean.getNation();
                break;
            case Constants.PARTY_MEM_TYPE:
                linkedHashMap = dicBean.getPartyMemType();
                break;
            case Constants.PARTY_POSITION:
                linkedHashMap = dicBean.getPartyPosition();
                break;
            case Constants.EDUCATION:
                linkedHashMap = dicBean.getEducation();
                break;
            case Constants.LEAGUE_MEM_TYPE:
                linkedHashMap = dicBean.getLeagueMemType();
                break;
            case Constants.LEAGUE_POSITION:
                linkedHashMap = dicBean.getLeaguePosition();
                break;
            case Constants.PARTY_LEVEL:
                linkedHashMap = dicBean.getPartyLevel();
                break;
            case Constants.RESULT_HAVE:
                linkedHashMap = dicBean.getMapHasOrNot();
                break;
            case Constants.RESULT_YES:
                linkedHashMap = dicBean.getMapYesOrNo();
                break;
            case Constants.LEAGUE_LEVEL:
                linkedHashMap = dicBean.getLeagueLevel();
                break;
        }
        mData = new ArrayList<>();
        Iterator iterator = linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            KeyValue keyValue = new KeyValue();
            keyValue.setKey(entry.getKey().toString());
            keyValue.setValue(entry.getValue().toString());
            mData.add(keyValue);
        }
        mAdapter = new SingelItemAdapter(this,mData);
        mAdapter.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void clickPosition(int position) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_VALUE,mData.get(position));
        setResult(Constants.RESPONSE_KEY_VALUE,intent);

        finish();
    }
}
