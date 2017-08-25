package com.wondersgroup.commerce.teamwork.statistics;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.wondersgroup.commerce.R;

import java.util.Calendar;

/**
 * Created by chan on 2017/08/10.
 */

public class YearPickerDialog extends Dialog {
    private Context mContext;

    public YearPickerDialog(Context context) {
        super(context);
    }

    public YearPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected YearPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setLayout(double width, double height) {
        getWindow().setLayout((int) width, (int) height);
    }

    public void setAnimStyle(int styleId) {
        if (getWindow() != null) {
            getWindow().setWindowAnimations(styleId);
        }
    }

    public static class Builder {
        private LinearLayout mButtonPanel;
        private Button mNegativeButton;
        private Button mPositiveButton;
        private static final int DEFAULT_STYLE = R.style.loading_dialog;
        private Context mContext;
        private Calendar calendar;
        private CharSequence mPositiveText;
        private CharSequence mNegativeText;
        private int mYear, mCurrentYear;
        private NumberPicker npYear;
        private OnYearPickListener mPositiveButtonListener;
        private OnClickListener mNegativeButtonListener;
        private YearPickerDialog mDialog;

        public Builder(Context context) {
            this.mContext = context;
            calendar = Calendar.getInstance();
        }

        public Builder setYear(int year) {
            this.mYear = year;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            this.mNegativeText = text;
            this.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, OnYearPickListener listener) {
            this.mPositiveText = text;
            this.mPositiveButtonListener = listener;
            return this;
        }

        public YearPickerDialog create() {
            mDialog = new YearPickerDialog(mContext, DEFAULT_STYLE);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View contentView = inflater.inflate(R.layout.dialog_year_picker, null);

            setupDatePanel(contentView);

            setupButtonPanel(contentView);

            mDialog.setContentView(contentView);

            setupWindowParams();

            return mDialog;
        }

        public void setupDatePanel(View contentView) {
            npYear = (NumberPicker) contentView.findViewById(R.id.np_year);
            mCurrentYear = calendar.get(Calendar.YEAR);
            npYear.setMaxValue(mCurrentYear);
            npYear.setMinValue(2001);
            npYear.setValue(mYear);
        }

        private void setupButtonPanel(View contentView) {
            mPositiveButton = (Button) contentView.findViewById(R.id.btn_positive);
            mNegativeButton = (Button) contentView.findViewById(R.id.btn_negative);
            mButtonPanel = (LinearLayout) contentView.findViewById(R.id.button_panel);

            boolean showPositive = false;
            boolean showNegative = false;

            // set the confirm button visible
            if (!TextUtils.isEmpty(mPositiveText)) {
                showPositive = true;
                mPositiveButton.setText(mPositiveText);
                mPositiveButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (mPositiveButtonListener != null) {

                            mPositiveButtonListener.onYearPick(mDialog, DialogInterface.BUTTON_POSITIVE,
                                    npYear.getValue());

                        }
                        mDialog.dismiss();
                    }
                });
            } else {
                mPositiveButton.setVisibility(View.GONE);
            }

            // set the cancel button visible
            if (!TextUtils.isEmpty(mNegativeText)) {
                showNegative = true;
                mNegativeButton.setText(mNegativeText);
                mNegativeButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (mNegativeButtonListener != null) {
                            mNegativeButtonListener.onClick(mDialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                        mDialog.dismiss();
                    }
                });
            } else {
                mNegativeButton.setVisibility(View.GONE);
            }

            // set the button panel layout visible
            if (!showPositive && !showNegative) {
                mButtonPanel.setVisibility(View.GONE);
            }
        }

        private void setupWindowParams() {
            mDialog.setAnimStyle(R.style.loading_dialog);

            WindowManager wManager = mDialog.getWindow().getWindowManager();
            double width = wManager.getDefaultDisplay().getWidth() * 0.8;
            mDialog.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        public interface OnYearPickListener {
            void onYearPick(DialogInterface dialog, int witch, int... args);
        }
    }

}
