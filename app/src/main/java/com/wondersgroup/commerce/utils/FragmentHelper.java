package com.wondersgroup.commerce.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class FragmentHelper {
	public static void turnToFragment(FragmentManager fm, Class fragmentClass,
			String tag, Bundle args, int id) {
		boolean isExist = true;

		Fragment fragment = fm.findFragmentByTag(tag);

		if (fragment == null) {
			try {
				isExist = false;
				fragment = (Fragment) fragmentClass.newInstance();
				fragment.setArguments(new Bundle());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (fragment.isAdded()) {
			return;
		}

		if (args != null && !args.isEmpty()) {
			fragment.getArguments().putAll(args);
		}

		FragmentTransaction ft = fm.beginTransaction();
//		ft.setCustomAnimations(R.anim.fragment_slide_enter,
//				R.anim.fragment_slide_exit);
		if (isExist) {
			ft.replace(id, fragment);
		} else {
			ft.replace(id, fragment, tag);
		}
		ft.addToBackStack(tag);
		ft.commitAllowingStateLoss();
	}

	public static void replaceFragment(FragmentManager fm, Fragment f,
			String tag, Bundle args, int id) {
		FragmentTransaction ft = fm.beginTransaction();
//		ft.setCustomAnimations(R.anim.fragment_slide_enter,
//				R.anim.fragment_slide_exit);
//		Fragment fragment = fm.findFragmentByTag(tag);
//		if (fragment == null) {
//			ft.add(id, f, tag);
//		} else {
//			ft.remove(fragment);
//			ft.add(id, f, tag);
//		}
		ft.replace(id,f);
		ft.addToBackStack(tag);
		ft.commitAllowingStateLoss();
	}

	public static void clearFragment(FragmentManager fm){
		int backStackCount = fm.getBackStackEntryCount();
		for(int i = 0; i < backStackCount; i++) {
			fm.popBackStack();
		}
	}

	public static void replaceFragmentNoBack(FragmentManager fm, Fragment f,
			String tag, Bundle args, int id) {
		FragmentTransaction ft = fm.beginTransaction();
//		ft.setCustomAnimations(R.anim.fragment_slide_enter,
//				R.anim.fragment_slide_exit);


		/*Fragment fragment = fm.findFragmentByTag(tag);
		if (fragment == null) {
			ft.add(id, f, tag);
		} else {
			ft.remove(fragment);
			ft.add(id, f, tag);
		}*/
		ft.replace(id,f);

		ft.commitAllowingStateLoss();
	}
}
