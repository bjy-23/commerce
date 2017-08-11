package com.wondersgroup.commerce.teamwork.wywork.fwqy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class UtilForFragment {
	static public void popBackStack(FragmentActivity activity) {
		activity.getSupportFragmentManager().popBackStack();
	}

	static public void popBackStackTwo(FragmentActivity activity) {
		activity.getSupportFragmentManager().popBackStack();
		activity.getSupportFragmentManager().popBackStack();
	}

	static public void switchContent(FragmentActivity activity,
			Fragment fragment, int sourceId) {
		// try {
		//
		// if (imm != null) {
		// imm.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus()
		// .getWindowToken(), 0);
		//
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		activity.getSupportFragmentManager().beginTransaction()
				.replace(sourceId, fragment).commitAllowingStateLoss();

	}

	static public void switchContentWithStack(FragmentActivity activity,
			Fragment fragment, int sourceId) {
		// try {
		//
		// if (imm != null) {
		// imm.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus()
		// .getWindowToken(), 0);
		//
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		FragmentManager fragmentManager = activity.getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.addToBackStack(null);
		transaction.replace(sourceId, fragment).commitAllowingStateLoss();
	}
}
