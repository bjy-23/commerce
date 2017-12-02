package com.wondersgroup.yngs.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wondersgroup.yngs.R;

import java.util.ArrayList;

/**
 * Created by 薛定猫 on 2016/1/22.
 */
public class SpinnerDropDown extends DialogFragment implements DialogInterface.OnShowListener{
    private static final String ARG_POSITION="position";
    private static final String ARG_ITEMS="items";

    private int position;
    private ArrayList<String> items;

    public interface OnItemClickListener{
        void onItemClick(int position,String value);
    }
    private OnItemClickListener onItemClickListener;
    ListView list;
    View drop;

    public static SpinnerDropDown newInstance(int position,ArrayList<String> items){
        SpinnerDropDown spinnerDropDown=new SpinnerDropDown();
        Bundle args=new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putStringArrayList(ARG_ITEMS,items);
        spinnerDropDown.setArguments(args);
        return spinnerDropDown;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar);
        if (getArguments()!=null){
            position=getArguments().getInt(ARG_POSITION,246);
            items=getArguments().getStringArrayList(ARG_ITEMS);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getContext(), R.layout.view_dialog_spinner_dropdown, null);
        list=(ListView)view.findViewById(R.id.spinner_list);
        drop=view.findViewById(R.id.spinner_drop);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(getContext(),R.layout.view_spinner_dropdown, items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(position,items.get(position));
                    dismiss();
                }
            }
        });
        return view;
    }

    @Override
     public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog=super.onCreateDialog(savedInstanceState);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.TOP|Gravity.CENTER_HORIZONTAL;
        position=position-(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        params.y=position;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnShowListener(this);
        return dialog;
    }


    @Override
    public void onShow(DialogInterface dialog) {
        /*RotateAnimation rotateAnimation=new RotateAnimation(0,45,drop.getWidth()/2, drop.getHeight()/2);
        rotateAnimation.setDuration(400);
        rotateAnimation.setFillAfter(true);
        drop.setAnimation(rotateAnimation);
        drop.animate();*/
    }
}
