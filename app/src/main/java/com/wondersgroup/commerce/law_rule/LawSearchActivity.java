package com.wondersgroup.commerce.law_rule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.interface_.TextChanger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LawSearchActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.layout_search)
    LinearLayout layoutSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.img_del)
    ImageView imgDel;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.layout_search_item)
    LinearLayout layoutSearchItem;
    @BindView(R.id.tv_search_content)
    TextView tvSearchContent;

    private boolean isShowing;
    private boolean firstShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_search);

        ButterKnife.bind(this);

        etSearch.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(String.valueOf(s))){
                    if (!isShowing){
                        layoutSearchItem.setVisibility(View.VISIBLE);
                        isShowing = true;
                    }
                    tvSearchContent.setText(String.valueOf(s));
                }else {
                    if (isShowing){
                        isShowing = false;
                        layoutSearchItem.setVisibility(View.GONE);
                    }
                }
            }
        });
        tvCancel.setOnClickListener(this);
        layoutSearchItem.setOnClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!firstShow){
            firstShow = true;
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (!imm.hideSoftInputFromWindow(etSearch.getWindowToken(),0))
                imm.showSoftInput(etSearch, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.layout_search_item:
                Intent intent = new Intent(LawSearchActivity.this,LawQueryActivity.class);
                intent.putExtra(Constants.LAW_NAME,tvSearchContent.getText().toString());
                intent.putExtra(Constants.TYPE,Constants.QUERY_NAME);
                startActivity(intent);
                break;
        }
    }
}
