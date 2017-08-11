package com.wondersgroup.commerce.teamwork.statistics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cjn on 7/12/17.
 */

public class AnnalsDatePopup extends PopupWindow {
    private View mParentView;
    private EditText mBeginDate, mEndDate;
    private TextView mConfirm, mCancel;
    private DatePicker picker;
    private String minDate;
    private String maxDate;
    private String startDate;
    private String endDate;
    private DateFormat dateFormat, yyrDateFormat;
    private WeakReference<Activity> mContextRef;
    private Calendar c = Calendar.getInstance();
    private static String separate = "/";
    private OnDismissListener listener;

    public AnnalsDatePopup(Activity context) {
        super(context);
        mContextRef = new WeakReference<>(context);
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        yyrDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        minDate = "1970/01/01";
        maxDate = c.get(Calendar.YEAR) + separate + (c.get(Calendar.MONTH) + 1) + separate + c.get(Calendar.DAY_OF_MONTH);
        initView();
    }

    private void initView() {
        if (null != mContextRef.get()) {
            mParentView = LayoutInflater.from(mContextRef.get()).inflate(R.layout.popup_annals_date, null, false);
            mBeginDate = (EditText) mParentView.findViewById(R.id.begin);
            mEndDate = (EditText) mParentView.findViewById(R.id.end);
            hideSoftInputMethod(mContextRef.get(), mBeginDate);
            hideSoftInputMethod(mContextRef.get(), mEndDate);
            disableClipBoard(mBeginDate);
            disableClipBoard(mEndDate);
            mConfirm = (TextView) mParentView.findViewById(R.id.btn_confirm);
            mCancel = (TextView) mParentView.findViewById(R.id.btn_cancel);
            picker = (DatePicker) mParentView.findViewById(R.id.picker);
            View empty = mParentView.findViewById(R.id.empty_view);
            empty.setOnTouchListener(new PopupOnTouchListener(this));
            setContentView(mParentView);
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            setBackgroundDrawable(new ColorDrawable(Color.parseColor("#007F7F7F")));
            setFocusable(true);
            setOutsideTouchable(true);
            update();
            initPicker();
            mBeginDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus && !TextUtils.isEmpty(mBeginDate.getText().toString())) {
                        setText(mBeginDate);
                    }
                }
            });
            mEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus && !TextUtils.isEmpty(mEndDate.getText().toString())) {
                        setText(mEndDate);
                    }
                }
            });
            mConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener) {
                        String startDate = mBeginDate.getText().toString();
                        String endDate = mEndDate.getText().toString();
                        if (!TextUtils.isEmpty(startDate) && !startDate.equals("请选择")) {
                            startDate = StringToDate(startDate, "yyyy年MM月dd日", "yyyy/MM/dd");
                        }
                        if (!TextUtils.isEmpty(endDate) && !endDate.equals("请选择")) {
                            endDate = StringToDate(endDate, "yyyy年MM月dd日", "yyyy/MM/dd");
                        }
                        listener.onDismiss(startDate, endDate);
                    }
                    dismiss();
                }
            });

            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBeginDate.setText("");
                    mEndDate.setText("");
                }
            });
        }
    }

    private void initPicker() {
        try {
            Date date = dateFormat.parse(minDate);
            picker.setMinDate(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Date date = dateFormat.parse(maxDate);
            picker.setMaxDate(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        picker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String dateS = picker.getYear() + separate
                        + String.format("%02d", picker.getMonth() + 1)
                        + separate + String.format("%02d", picker.getDayOfMonth());
                switch (mParentView.findFocus().getId()) {
                    case R.id.begin:
                        mBeginDate.setText(StringToDate(dateS, "yyyy/MM/dd", "yyyy年MM月dd日"));
                        break;
                    case R.id.end:
                        mEndDate.setText(StringToDate(dateS, "yyyy/MM/dd", "yyyy年MM月dd日"));
                        break;
                }
            }
        });
    }

    public AnnalsDatePopup setStartDate(String startDate) {
        this.startDate = startDate;
        if (!TextUtils.isEmpty(startDate) && !startDate.equals("请选择")) {
            mBeginDate.setText(StringToDate(this.startDate, "yyyy/MM/dd", "yyyy年MM月dd日"));
            setText(mBeginDate);
        }
        return this;
    }

    public AnnalsDatePopup setEndDate(String endDate) {
        this.endDate = endDate;
        if (!TextUtils.isEmpty(endDate) && !endDate.equals("请选择")) {
            mEndDate.setText(StringToDate(this.endDate, "yyyy/MM/dd", "yyyy年MM月dd日"));
        }
        return this;
    }

    private void setText(EditText edit) {
        String text = edit.getText().toString();
        edit.setSelection(text.length());
        if (!TextUtils.isEmpty(text)) {
            try {
                Date date = yyrDateFormat.parse(text);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                picker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDismissListener(OnDismissListener l) {
        this.listener = l;
    }

    /**
     * 显示
     *
     * @param parent
     */
    public void show(View parent) {
        if (!isShowing()) {
            showAsDropDown(parent);
        } else {
            dismiss();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
//        if (null != listener) {
//            listener.onDismiss(StringToDate(mBeginDate.getText().toString(), "yyyy年MM月dd日", "yyyy/MM/dd"),
//                    StringToDate(mEndDate.getText().toString(), "yyyy年MM月dd日", "yyyy/MM/dd"));
//        }
    }

    /**
     * 判断当前系统版本，选择使用何种方式隐藏默认键盘
     */
    public static void hideSoftInputMethod(Activity act, EditText editText) {
        act.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }

        if (methodName == null) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (NoSuchMethodException e) {
                editText.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void disableClipBoard(EditText editText) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        if (currentVersion >= 11) {
            editText.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
            editText.setLongClickable(false);
            editText.setTextIsSelectable(false);
            editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                public void onDestroyActionMode(ActionMode mode) {
                }

                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }
            });
        } else {
            editText.setLongClickable(false);
            editText.setTextIsSelectable(false);
            editText.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.clear();
                }
            });
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String StringToDate(String dateSrc, String formatSrc, String formatDes) {
        SimpleDateFormat format = new SimpleDateFormat(formatSrc);
        Date date = null;
        try {
            date = format.parse(dateSrc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatDes);
        return s.format(date);
    }

    public class PopupOnTouchListener implements View.OnTouchListener {
        PopupWindow mPopupWindow;

        public PopupOnTouchListener(PopupWindow mPopupWindow) {
            this.mPopupWindow = mPopupWindow;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.view.View.OnTouchListener#onTouch(android.view.View,
         * android.view.MotionEvent)
         */
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            }
            return false;
        }
    }

    public interface OnDismissListener {
        void onDismiss(String startDate, String endDate);
    }
}
