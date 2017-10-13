package com.wondersgroup.commerce.teamwork.dailycheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;

import java.util.ArrayList;

public class DailyAndUnlicensedFragment extends Fragment {
	private View view;
	private ViewPager pager;
	private FragmentManager fragmentManager;
	MenuInflater mInflater;
	Menu mMenu;
	private SlidingTabLayout tabLayout;
	private ArrayList<Fragment> fragments;
	private AppCompatActivity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tab_viewpager_fragment, container,
				false);
		activity = (AppCompatActivity) getActivity();
		fragmentManager = getChildFragmentManager();
		fragments = new ArrayList<Fragment>();
		fragments.add(new DailyListSearchFragment());
		UnlicensedAddFragment unlicensedAddFragment = new UnlicensedAddFragment();
		Bundle bundle = new Bundle();
		bundle.putString(Constants.TYPE, "2");
		fragments.add(new UnlicensedAddFragment());
		// fragments.add(new ComplainReportSearchFragment());
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

	int[] colors = { 0xFF654321, 0xFF336699, 0xFF654321 };

	class MyAdapte extends FragmentPagerAdapter {

		public MyAdapte(FragmentManager fm) {

			super(fm);

		}

		String[] titles = { "日常检查", "无照管理" };

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

		int id = item.getItemId();

		if (id == android.R.id.home) {
			activity.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
