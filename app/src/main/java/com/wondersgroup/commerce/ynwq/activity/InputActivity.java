package com.wondersgroup.commerce.ynwq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.input_edit)EditText input;
    @Bind(R.id.input_submit)Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText(getIntent().getStringExtra("title"));
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.putExtra("input_string",input.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
