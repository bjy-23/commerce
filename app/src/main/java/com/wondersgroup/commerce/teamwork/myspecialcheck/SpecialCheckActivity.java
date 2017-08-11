package com.wondersgroup.commerce.teamwork.myspecialcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.mysupervision.UtilForFragment;

public class SpecialCheckActivity extends AppCompatActivity {

	private ActionBar actionBar;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mode_activity);

		title = (TextView)findViewById(R.id.toolbar_title);

		Toolbar toolbar = (Toolbar) findViewById(R.id.mid_toolbar);
		setSupportActionBar(toolbar);

		// 打開 up bottom
		actionBar = getSupportActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);

		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setTitle("");
		actionBar.setHomeAsUpIndicator(R.mipmap.app_back);
		title.setText("监督管理-专项整治");
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
				| ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_CUSTOM);
		UtilForFragment.switchContent(this, new SpecialListSearchFragment(),
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
				SpecialFragment specialFragment = (SpecialFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content);

				specialFragment.onActivityResult(requestCode, resultCode, arg2);
			}

			if (resultCode == RESULT_OK
					&& (requestCode == 61 || requestCode == 62 || requestCode == 63)) {
				SpecialTempFragment specialTempFragment = (SpecialTempFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content);

				specialTempFragment.onActivityResult(requestCode, resultCode, arg2);
			}

		}

	}

}
