package com.wondersgroup.commerce.teamwork.dailycheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.wondersgroup.commerce.R;


public class UnlicensedActivity extends ActionBarActivity {

	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mode_activity);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.inflateMenu(R.menu.menu_main);
		setSupportActionBar(toolbar);

		// 打開 up bottom

		// customView = this.getLayoutInflater().inflate(R.layout.custom_view,
		// null);
		actionBar = getSupportActionBar();
		// actionBar.setCustomView(customView);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
		// getSupportActionBar().setDisplayOptions(
		// ActionBar.DISPLAY_SHOW_CUSTOM,
		// ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME
		// | ActionBar.DISPLAY_SHOW_TITLE);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
				| ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_CUSTOM);
		UtilForFragment.switchContent(this, new UnlicensedAddFragment(),
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
				UnlicensedFragment unlicensedFragment = (UnlicensedFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content);

				unlicensedFragment.onActivityResult(requestCode, resultCode,
						arg2);
			}
			if (resultCode == RESULT_OK
					&& (requestCode == 61 || requestCode == 62 || requestCode == 63)) {
				UnlicensedTempFragment unlicensedTempFragment = (UnlicensedTempFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content);

				unlicensedTempFragment.onActivityResult(requestCode, resultCode, arg2);
			}

		}

	}

}
