package com.wondersgroup.commerce.teamwork.addressbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjy on 2017/4/5.
 * Updated by yclli on 2017/8/15
 */

public class SearchActivity extends AppCompatActivity implements TextWatcher{
    private EditText editText;
    private RecyclerView recyclerView;

    private List<Address.AddlistPersonalInfo> data;
    private List<Address.AddlistPersonalInfo> data2;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_name);

        data = (List<Address.AddlistPersonalInfo>) getIntent().getSerializableExtra("data");
        data2 = new ArrayList<>();

        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new SearchAdapter(data2,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new SearchAdapter.OnClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(SearchActivity.this, TxlDetailActivity.class);

                intent.putExtra("userId", data2.get(position).getAddlistId());

                startActivity(intent);
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        CharSequence s1 = s;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        CharSequence s1 =  s;
    }

    @Override
    public void afterTextChanged(Editable s) {
        CharSequence c = s;

        data2.clear();
        if (!c.toString().equals("")){
            for (Address.AddlistPersonalInfo datum : data){
                if (datum.getName().contains(c)){
                    data2.add(datum);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}
