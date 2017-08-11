package com.wondersgroup.commerce.widget;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.wondersgroup.commerce.R;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CusDatePickerDialog extends DialogFragment implements View.OnClickListener,DialogInterface.OnShowListener{
    Button dismiss;
    Button ok;
    TextView titleView;
    DatePicker picker;
    TimePicker timePicker;
    OnDateSetListener listener;

    private static final String ARG_TITLE = "title";

    private String title;
    private String minDate;
    private String maxDate;
    private String initDate;

    private boolean shouldReset=false;
    private boolean isNeedTime=false;

    private DateFormat dateFormat;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment CusDatePickerDialog.
     */
    public static CusDatePickerDialog newInstance(String title) {
        CusDatePickerDialog fragment = new CusDatePickerDialog();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public CusDatePickerDialog() {
        // Required empty public constructor
    }
    public interface OnDateSetListener{
        void OnDateSet(String dateString);
    }

    public void setOnDateSetListener(OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cus_datepicker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleView=(TextView)view.findViewById(R.id.dialog_title);
        titleView.setText(title);
        ok=(Button)view.findViewById(R.id.dialog_ok);
        dismiss=(Button)view.findViewById(R.id.dialog_dismiss);
        ok.setOnClickListener(this);
        dismiss.setOnClickListener(this);
        picker=(DatePicker)view.findViewById(R.id.dialog_picker);
        picker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        if(shouldReset){
            minDate=null;
            maxDate=null;
        }
        if(initDate!=null){
            String[] dates=initDate.split("-");
            int year=Integer.parseInt(dates[0]);
            int month=Integer.parseInt(dates[1]);
            int day=Integer.parseInt(dates[2]);
            picker.updateDate(year,month,day);
        }
        if(minDate!=null){
            try {
                Date date=dateFormat.parse(minDate);
                picker.setMinDate(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(maxDate!=null){
            try {
                Date date=dateFormat.parse(maxDate);
                picker.setMaxDate(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        LinearLayout ll = (LinearLayout)((LinearLayout)picker.getChildAt(0)).getChildAt(0);
        for(int i=0;i<ll.getChildCount();i++){
            NumberPicker numberPicker=(NumberPicker)ll.getChildAt(i);
            Field divider =null;
            try {
                divider=NumberPicker.class.getDeclaredField("mSelectionDivider");
            }catch (NoSuchFieldException e){
                e.printStackTrace();
            }
            try{
                divider.setAccessible(true);
                divider.set(numberPicker,null);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }catch (Resources.NotFoundException e){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }

        timePicker = (TimePicker) view.findViewById(R.id.dialog_time);
        timePicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        if(isNeedTime){
           timePicker.setVisibility(View.VISIBLE);
        }
        NumberPicker numberPicker0 = (NumberPicker)((LinearLayout)timePicker.getChildAt(0)).getChildAt(0);
        Field divider0 =null;
        try {
            divider0=NumberPicker.class.getDeclaredField("mSelectionDivider");
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }
        try{
            divider0.setAccessible(true);
            divider0.set(numberPicker0,null);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (Resources.NotFoundException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }

        ll = (LinearLayout)((LinearLayout)timePicker.getChildAt(0)).getChildAt(1);
        for(int j=0;j<ll.getChildCount();j++){
            if(j==1){
                continue;
            }
            NumberPicker numberPicker=(NumberPicker)ll.getChildAt(j);
            Field divider =null;
            try {
                divider=NumberPicker.class.getDeclaredField("mSelectionDivider");
            }catch (NoSuchFieldException e){
                e.printStackTrace();
            }
            try{
                divider.setAccessible(true);
                divider.set(numberPicker,null);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }catch (Resources.NotFoundException e){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
    public void resetPicker(){
        shouldReset=true;
    }

    @Override
    public void onClick(View v) {
        if(ok==v){
            //listener = (OnDateSetListener)getActivity();
            String dateString = "";
            if(isNeedTime){
                dateString = picker.getYear() + "-" + (picker.getMonth() + 1) + "-" + picker.getDayOfMonth()
                        + " " + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute() + ":00";
            }else {
                dateString = picker.getYear() + "-" + changeTime(picker.getMonth() + 1) + "-" + changeTime(picker.getDayOfMonth());
            }
            if(listener!=null)listener.OnDateSet(dateString);
            getDialog().dismiss();
        }else if(dismiss==v){
            getDialog().dismiss();
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if(shouldReset){
            Calendar c= Calendar.getInstance();
            picker.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
            shouldReset=false;
        }
    }
    public void setMinDate(String dateString){
        minDate=dateString;
    }
    public void setMaxDate(String dateString){
        maxDate=dateString;
    }
    public void setInitDate(String dateString){

    }
    public void useTimePicker(boolean isNeedTime) {
        this.isNeedTime=isNeedTime;
    }

    public String changeTime(int num){
        if (num < 10)
            return "0"+num;
        return num+"";
    }
}
