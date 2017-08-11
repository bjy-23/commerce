package com.wondersgroup.commerce.teamwork.wywork.fwqy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;


public class EntServiceActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mode_activity);

		Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
		TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
		// toolbar.inflateMenu(R.menu.menu_main);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
		toolbarText.setText(Constants.fwqy);


		UtilForFragment.switchContent(this, new EntListSearchFragment(),
				R.id.content);

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent arg2) {
		switch (requestCode) {

		default:
			Log.e("default", "-------------------");
			Log.e("requestCode", requestCode + "");
			if (resultCode == RESULT_OK
					&& (requestCode == 51 || requestCode == 52 || requestCode == 53)) {
				EntFragment entFragment = (EntFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content);
				
				entFragment.onActivityResult(requestCode,
						resultCode, arg2);
			}
			if (resultCode == RESULT_OK
					&& (requestCode == 61 || requestCode == 62 || requestCode == 63)) {
				EntTempFragment entTempFragment = (EntTempFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content);

				entTempFragment.onActivityResult(requestCode, resultCode,
						arg2);
			}
		}

	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}
}
