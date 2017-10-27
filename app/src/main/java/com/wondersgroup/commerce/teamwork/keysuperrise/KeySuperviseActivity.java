package com.wondersgroup.commerce.teamwork.keysuperrise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.dailycheck.UtilForFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class KeySuperviseActivity extends AppCompatActivity {

	@BindView(R.id.mid_toolbar)Toolbar toolbar;
	@BindView(R.id.toolbar_title)TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mode_activity);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setHomeAsUpIndicator(R.mipmap.app_back);
		title.setText("重点监管");

		// 打開 up bottom

		// customView = this.getLayoutInflater().inflate(R.layout.custom_view,
		// null);
//		actionBar = getSupportActionBar();
		// actionBar.setCustomView(customView);
//		actionBar.setDisplayShowCustomEnabled(true);
//		actionBar.setDisplayShowHomeEnabled(false);
//		actionBar.setDisplayHomeAsUpEnabled(true);
//		actionBar.setDisplayUseLogoEnabled(false);
		// getSupportActionBar().setDisplayOptions(
		// ActionBar.DISPLAY_SHOW_CUSTOM,
		// ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME
		// | ActionBar.DISPLAY_SHOW_TITLE);
//		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
//				| ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_CUSTOM);
		UtilForFragment.switchContent(this, new KeyListSearchFragment(),
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
				KeyFragment keyFragment = (KeyFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content);

				keyFragment.onActivityResult(requestCode, resultCode, arg2);
			}

			if (resultCode == RESULT_OK
					&& (requestCode == 61 || requestCode == 62 || requestCode == 63)) {
				KeyTempFragment keyTempFragment = (KeyTempFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content);

				keyTempFragment.onActivityResult(requestCode, resultCode, arg2);
			}

		}

	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}

}
