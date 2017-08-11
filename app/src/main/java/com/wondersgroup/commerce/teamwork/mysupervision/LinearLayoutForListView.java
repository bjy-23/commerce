package com.wondersgroup.commerce.teamwork.mysupervision;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

public class LinearLayoutForListView<T> extends LinearLayout {

	private ArrayAdapter<T> adapter;

	public LinearLayoutForListView(Context context) {
		super(context);
	}

	public LinearLayoutForListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LinearLayoutForListView(Context context, AttributeSet attrs,
								   int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public void bindLinearLayout() {
		// setOrientation(LinearLayout.VERTICAL);
		int count = adapter.getCount();
		for (int i = 0; i < count; i++) {
			View v = adapter.getView(i, null, null);

			// v.setOnClickListener(this.onClickListener);
			if (i == count - 1) {
				LinearLayout ly = (LinearLayout) v;
				// ly.setOrientation(LinearLayout.VERTICAL);
				// ly.removeViewAt(2);
			}
			addView(v, i);
		}
		Log.v("countTAG", "" + count);
	}

	

	public ArrayAdapter<T> getAdapter() {
		return adapter;
	}

	public void setAdapter(ArrayAdapter<T> adpater) {
		this.adapter = adpater;
		bindLinearLayout();
	}
}
