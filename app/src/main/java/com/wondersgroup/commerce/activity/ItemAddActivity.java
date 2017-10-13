package com.wondersgroup.commerce.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.teamwork.casedeal.CaseQueryDetailActivity;
import com.wondersgroup.commerce.utils.TableRowUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemAddActivity extends BaseActivity {
    @Bind(R.id.layout_add)
    LinearLayout layoutAdd;

    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_item_add);

        ButterKnife.bind(this);
        type = getIntent().getStringExtra(Constants.TYPE);
        if (type != null){
            switch (type){
                default:
                    tvTitle.setText("详情");
                    break;
                case "dataVolume":
                    tvTitle.setText("当事人信息");
                    addDataVolume((List<DataVolume>)getIntent().getSerializableExtra("list"));
                    break;
            }
        }
    }

    public void addDataVolume(List<DataVolume> list){
        TableRowUtils utils = new TableRowUtils(this, layoutAdd, list);
        utils.build();
    }
}
