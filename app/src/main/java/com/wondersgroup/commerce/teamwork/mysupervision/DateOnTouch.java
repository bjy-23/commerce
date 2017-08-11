package com.wondersgroup.commerce.teamwork.mysupervision;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.DatePicker;
import android.widget.TextView;


import com.wondersgroup.commerce.R;

import java.util.Calendar;

public class DateOnTouch implements OnTouchListener {

	private Context context;
	private TextView occurDate;

	public DateOnTouch(Context context, TextView occurDate) {
		super();
		this.context = context;
		this.occurDate = occurDate;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			View view = View.inflate(context, R.layout.date_dialog, null);
			final DatePicker datePicker = (DatePicker) view
					.findViewById(R.id.date_picker);
			builder.setView(view);

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					cal.get(Calendar.DAY_OF_MONTH), null);
			final int inType = occurDate.getInputType();
			occurDate.setInputType(InputType.TYPE_NULL);
			occurDate.setInputType(inType);
			occurDate.onTouchEvent(event);

			builder.setTitle("选取事发时间");
			builder.setPositiveButton("确  定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							StringBuffer sb = new StringBuffer();
							Calendar calendar = Calendar.getInstance();
							Calendar calendar2 = Calendar.getInstance();
							calendar2.setTimeInMillis(System
									.currentTimeMillis());
							calendar.set(datePicker.getYear(),
									datePicker.getMonth(),
									datePicker.getDayOfMonth(), 0, 0, 0);
							if (calendar.after(calendar2)) {
								occurDate.setHint("时间超出当前时间");
								occurDate.setText(null);
								dialog.cancel();
							} else {
								sb.append(String.format("%d-%02d-%02d",
										datePicker.getYear(),
										datePicker.getMonth() + 1,
										datePicker.getDayOfMonth()));

								occurDate.setText(sb);

								dialog.cancel();
							}
						}
					});
			Dialog dialog = builder.create();
			dialog.show();

		}

		return false;
	}

}
