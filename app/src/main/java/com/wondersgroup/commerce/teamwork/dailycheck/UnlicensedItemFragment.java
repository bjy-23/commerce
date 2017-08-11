package com.wondersgroup.commerce.teamwork.dailycheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.wondersgroup.commerce.R;

import java.util.ArrayList;

public class UnlicensedItemFragment extends Fragment {

	private View view;
	private ViewPager pager;
	private FragmentManager fragmentManager;
	private ActionBarActivity activity;

	private SlidingTabLayout tabLayout;
	private ArrayList<Fragment> fragments;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tab_viewpager_fragment, container,
				false);
		activity = (ActionBarActivity) getActivity();
		fragmentManager = getChildFragmentManager();
		fragments = new ArrayList<Fragment>();
		fragments.add(new UnlicensedItemBaseInfoFragment());
//		fragments.add(new UnlicensedItemResultFragment());
		pager = (ViewPager) view.findViewById(R.id.pager);
		// SlidingTabLayout tab = (SlidingTabLayout)
		// findViewById(R.id.sunhapper);

		MyAdapte myAdapter = new MyAdapte(fragmentManager);
		pager.setAdapter(myAdapter);
		tabLayout = (SlidingTabLayout) view.findViewById(R.id.tab);
		tabLayout.setCustomTabView(R.layout.mode_tab_title, 0);
		tabLayout.setViewPager(pager);

		tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return colors[position];
			}

			@Override
			public int getDividerColor(int position) {
				return 0x00FFFFFF;
			}
		});
		setHasOptionsMenu(true);
		return view;
	}

	int[] colors = { 0xFF654321, 0xFF336699 };

	class MyAdapte extends FragmentPagerAdapter {

		public MyAdapte(FragmentManager fm) {

			super(fm);

		}

		String[] titles = { "检查主体", "检查录入" };

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragments.get(arg0);
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// // noinspection SimplifiableIfStatement
		// if (id == R.id.register) {
		// Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
		// return true;
		// }
		// if (id == R.id.forget_passwd) {
		// Toast.makeText(LoginActivity.this, "忘记密码",
		// Toast.LENGTH_SHORT).show();
		// return true;
		// }
		if (id == android.R.id.home) {
			UtilForFragment.popBackStack(activity);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
