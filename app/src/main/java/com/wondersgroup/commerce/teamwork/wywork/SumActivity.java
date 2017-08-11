package com.wondersgroup.commerce.teamwork.wywork;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SumActivity extends AppCompatActivity {

	@Bind(R.id.mid_toolbar)Toolbar toolbar;
	@Bind(R.id.toolbar_title)TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mode_activity);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setHomeAsUpIndicator(R.mipmap.app_back);

		UtilForFragment.switchContent(this, new SumListFragment(),
				R.id.content);
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}

}
